/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.controller;

import gob.dp.sid.comun.ConstantesUtil;
import gob.dp.sid.comun.controller.AbstractManagedBean;
import gob.dp.sid.comun.type.EtapaType;
import gob.dp.sid.comun.type.ExpedienteType;
import gob.dp.sid.registro.entity.EtapaEstado;
import gob.dp.sid.registro.entity.Expediente;
import gob.dp.sid.registro.entity.ExpedienteGestion;
import gob.dp.sid.registro.entity.GestionEtapa;
import gob.dp.sid.registro.service.EtapaEstadoService;
import gob.dp.sid.registro.service.ExpedienteGestionService;
import gob.dp.sid.registro.service.ExpedienteService;
import gob.dp.sid.registro.service.GestionEtapaService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author carlos
 */
@Named
@Scope("session")
public class ImportarController extends AbstractManagedBean implements Serializable {

    private static final Logger log = Logger.getLogger(ImportarController.class);

    private Part file1;

    Workbook wb;

    private List<ExpedienteGestion> listaGestionesONP;

    @Autowired
    private ExpedienteGestionService expedienteGestionService;

    @Autowired
    private ExpedienteService expedienteService;

    @Autowired
    private EtapaEstadoService etapaEstadoService;

    @Autowired
    private GestionEtapaService gestionEtapaService;

    public String cargarPagina() {
        listaGestionesONP = new ArrayList<>();
        return "importarONP";
    }

    public void cargarExcel() {
        String ruta1 = uploadArchive(file1);
    }

    private void importar(File archivo) {
        Integer i = 0;
        DefaultTableModel modeloT = new DefaultTableModel();
        List<Object[]> listaObjetos = new ArrayList<>();
        try {
            wb = WorkbookFactory.create(new FileInputStream(archivo));
            Sheet hoja = wb.getSheetAt(0);
            Iterator filaIterator = hoja.rowIterator();
            int indiceFila = -1;
            while (filaIterator.hasNext()) {
                indiceFila++;
                Row fila = (Row) filaIterator.next();
                Iterator columnaIterator = fila.cellIterator();
                Object[] listaColumna = new Object[7];

                int indiceColumna = -1;
                while (columnaIterator.hasNext()) {
                    indiceColumna++;
                    Cell celda = (Cell) columnaIterator.next();
                    if (indiceFila == 0) {
                        modeloT.addColumn(celda.getStringCellValue());
                    } else {
                        if (celda != null) {
                            switch (celda.getCellType()) {
                                case Cell.CELL_TYPE_NUMERIC:
                                    //listaColumna[indiceColumna]= (int)Math.round(celda.getNumericCellValue());
                                    listaColumna[indiceColumna] = celda.getDateCellValue();
                                    break;
                                case Cell.CELL_TYPE_STRING:
                                    listaColumna[indiceColumna] = celda.getStringCellValue();
                                    break;
                                case Cell.CELL_TYPE_BOOLEAN:
                                    listaColumna[indiceColumna] = celda.getBooleanCellValue();
                                    break;
                                default:
                                    listaColumna[indiceColumna] = null;
                                    break;
                            }
                        }
                    }
                }
                if (indiceFila != 0) {
                    listaObjetos.add(listaColumna);
                }

            }
            cargarGestiones(listaObjetos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarGestiones(List<Object[]> lista) {
        listaGestionesONP = new ArrayList<>();
        for (Object[] os : lista) {
            ExpedienteGestion eg = new ExpedienteGestion();
            eg.setNumeroExpediente(os[0] == null ? null : os[0].toString());
            eg.setCodigoONP(os[1] == null ? null : os[1].toString());
            eg.setNombreEntidad(os[2] == null ? null : os[2].toString());
            eg.setFecha((Date) os[3]);
            eg.setFechaModificacion((Date) os[4]);
            eg.setDocumentoRespuesta(os[5] == null ? null : os[5].toString());
            eg.setFechaRespuesta((Date) os[6]);
            listaGestionesONP.add(eg);
        }

        for (ExpedienteGestion gestion : listaGestionesONP) {
            if (gestion.getNumeroExpediente() != null) {
                Expediente e = expedienteService.expedienteBuscarPorNumero(gestion.getNumeroExpediente());
                if (e != null) {
                    gestion.setValidaExpediente("SI");
                    gestion.setVerEtapa(inicializarEtapaEstado(e));
                    gestion.setIdExpediente(e.getId());
                } else {
                    gestion.setValidaExpediente("NO");
                }
            }

            if (gestion.getCodigoONP() != null) {
                Integer contador = expedienteGestionService.expedienteGestionCountONP(gestion.getCodigoONP());
                if (contador > 0) {
                    gestion.setExisteGestion("SI");
                } else {
                    gestion.setExisteGestion("NO");
                }
            }
        }
    }

    public void insertarActualizarGestiones() {
        List<ExpedienteGestion> egs = new ArrayList<>();
        int i = 0;
        for (ExpedienteGestion eg : listaGestionesONP) {
            try {
                if (StringUtils.equals(eg.getValidaExpediente(), "SI")) {
                    if (eg.getVerEtapa() > 0) {
                        if (StringUtils.equals(eg.getExisteGestion(), "NO")) {
                            DateFormat format = new SimpleDateFormat("yyMMddHHmmss");
                            String formato = format.format(new Date());
                            i++;
                            eg.setDescripcion("Oficio ONP");
                            eg.setCodigoGestion("GES" + i + formato);
                            eg.setFechaRegistro(new Date());
                            expedienteGestionService.expedienteGestionInsertar(eg);
                            GestionEtapa ge = new GestionEtapa();
                            ge.setIdEtapa(eg.getVerEtapa());
                            ge.setIdExpediente(eg.getIdExpediente());
                            ge.setIdGestion(eg.getId());
                            ge.setNumeroExpediente(eg.getNumeroExpediente());
                            gestionEtapaService.gestionEtapaInsertar(ge);
                        } else {
                            expedienteGestionService.expedienteGestionUpdate(eg);
                        }

                    }else{
                        egs.add(eg);
                    }
                } else {
                    egs.add(eg);
                }
            } catch (Exception e) {
                egs.add(eg);
                log.error(e);
            }
            msg.messageInfo("Se cargaron las gestiones", null);
        }
        listaGestionesONP = new ArrayList<>();
        listaGestionesONP = egs;
    }

    private Integer inicializarEtapaEstado(Expediente expediente) {
        try {
            EtapaEstado etapaEstado = etapaEstadoService.etapaEstadoVigente(expediente.getId());
            if (etapaEstado == null) {
                etapaEstado = etapaEstadoService.etapaEstadoInicial(expediente.getId());
            }
            if (etapaEstado == null) {
                etapaEstado = new EtapaEstado();
            }
            /**
             * QUEJA
             */
            if (StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.QUEJA.getKey())) {
                if (etapaEstado.getIdEtapa() == null) {
                    etapaEstado.setVerEtapa(EtapaType.CALIFICACION_QUEJA.getKey());
                }
                if (Objects.equals(etapaEstado.getIdEtapa(), EtapaType.CALIFICACION_QUEJA.getKey())) {
                    if (StringUtils.equals(etapaEstado.getIndicadorEtapa(), "VIG")) {
                        etapaEstado.setVerEtapa(EtapaType.INVESTIGACION_QUEJA.getKey());
                        if (StringUtils.equals(expediente.getGeneral(), "C")) {
                            etapaEstado.setVerEtapa(EtapaType.CALIFICACION_QUEJA.getKey());
                        }
                    } else {
                        etapaEstado.setVerEtapa(EtapaType.CALIFICACION_QUEJA.getKey());
                    }
                }
                if (Objects.equals(etapaEstado.getIdEtapa(), EtapaType.INVESTIGACION_QUEJA.getKey())) {
                    if (StringUtils.equals(etapaEstado.getIndicadorEtapa(), "VIG")) {
                        etapaEstado.setVerEtapa(EtapaType.PERSUACION_QUEJA.getKey());
                        if (StringUtils.equals(expediente.getGeneral(), "C")) {
                            etapaEstado.setVerEtapa(EtapaType.INVESTIGACION_QUEJA.getKey());
                        }
                    } else {
                        etapaEstado.setVerEtapa(EtapaType.INVESTIGACION_QUEJA.getKey());
                    }
                }
                if (Objects.equals(etapaEstado.getIdEtapa(), EtapaType.PERSUACION_QUEJA.getKey())) {
                    if (StringUtils.equals(etapaEstado.getIndicadorEtapa(), "VIG")) {
                        etapaEstado.setVerEtapa(EtapaType.SEGUIMIENTO_QUEJA.getKey());
                        if (StringUtils.equals(expediente.getGeneral(), "C")) {
                            etapaEstado.setVerEtapa(EtapaType.PERSUACION_QUEJA.getKey());
                        }
                    } else {
                        etapaEstado.setVerEtapa(EtapaType.PERSUACION_QUEJA.getKey());
                    }
                }
                if (Objects.equals(etapaEstado.getIdEtapa(), EtapaType.SEGUIMIENTO_QUEJA.getKey())) {
                    etapaEstado.setVerEtapa(EtapaType.SEGUIMIENTO_QUEJA.getKey());
                }
            }
            /**
             * PETITORIO
             */
            if (StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.PETITORIO.getKey())) {
                if (etapaEstado.getIdEtapa() == null) {
                    etapaEstado.setVerEtapa(EtapaType.CALIFICACION_PETITORIO.getKey());
                }
                if (Objects.equals(etapaEstado.getIdEtapa(), EtapaType.CALIFICACION_PETITORIO.getKey())) {
                    if (StringUtils.equals(etapaEstado.getIndicadorEtapa(), "VIG")) {
                        etapaEstado.setVerEtapa(EtapaType.GESTION_PETITORIO.getKey());
                        if (StringUtils.equals(expediente.getGeneral(), "C")) {
                            etapaEstado.setVerEtapa(EtapaType.CALIFICACION_PETITORIO.getKey());
                        }
                    } else {
                        etapaEstado.setVerEtapa(EtapaType.CALIFICACION_PETITORIO.getKey());
                    }
                }
                if (Objects.equals(etapaEstado.getIdEtapa(), EtapaType.GESTION_PETITORIO.getKey())) {
                    if (StringUtils.equals(etapaEstado.getIndicadorEtapa(), "VIG")) {
                        etapaEstado.setVerEtapa(EtapaType.PERSUASION_PETITORIO.getKey());
                        if (StringUtils.equals(expediente.getGeneral(), "C")) {
                            etapaEstado.setVerEtapa(EtapaType.GESTION_PETITORIO.getKey());
                        }
                    } else {
                        etapaEstado.setVerEtapa(EtapaType.GESTION_PETITORIO.getKey());
                    }
                }
                if (Objects.equals(etapaEstado.getIdEtapa(), EtapaType.PERSUASION_PETITORIO.getKey())) {
                    etapaEstado.setVerEtapa(EtapaType.PERSUASION_PETITORIO.getKey());
                }
            }
            return etapaEstado.getVerEtapa();
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    private String uploadArchive(Part fil) {
        String nameArchive = getFilename(fil);
        String extencion = getFileExtension(getFilename(fil));
        if (StringUtils.isNoneBlank(nameArchive)) {
            String formato = RandomStringUtils.random(32, 0, 20, true, true, "qw32rfHIJk9iQ8Ud7h0X".toCharArray());
            String ruta = formato + extencion;
            File file = new File(ConstantesUtil.FILE_SYSTEM + ruta);
            try (InputStream input = fil.getInputStream()) {
                Files.copy(input, file.toPath());
                importar(file);
            } catch (IOException ex) {
                log.error(ex.getCause());
            }
            return ruta;
        }
        return null;
    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf("=") + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }

    private String getFileExtension(String name) {
        try {
            return name.substring(name.lastIndexOf("."));
        } catch (Exception e) {
            return "";
        }
    }

    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }

    public List<ExpedienteGestion> getListaGestionesONP() {
        return listaGestionesONP;
    }

    public void setListaGestionesONP(List<ExpedienteGestion> listaGestionesONP) {
        this.listaGestionesONP = listaGestionesONP;
    }

}
