/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.reporte.controller;

import gob.dp.sid.administracion.seguridad.controller.LoginController;
import gob.dp.sid.administracion.seguridad.entity.Usuario;
import gob.dp.sid.administracion.seguridad.service.UsuarioService;
import gob.dp.sid.comun.ConstantesUtil;
import gob.dp.sid.comun.controller.AbstractManagedBean;
import gob.dp.sid.comun.entity.Distrito;
import gob.dp.sid.comun.entity.FiltroParametro;
import gob.dp.sid.comun.entity.Provincia;
import gob.dp.sid.comun.service.ParametroService;
import gob.dp.sid.comun.service.UbigeoService;
import gob.dp.sid.registro.entity.Entidad;
import gob.dp.sid.registro.entity.ExpedienteClasificacion;
import gob.dp.sid.registro.entity.ExpedienteConsulta;
import gob.dp.sid.registro.entity.ExpedienteNivel;
import gob.dp.sid.registro.service.EntidadService;
import gob.dp.sid.registro.service.ExpedienteClasificacionService;
import gob.dp.sid.registro.service.ExpedienteConsultaService;
import gob.dp.sid.registro.service.ExpedienteNivelService;
import gob.dp.sid.registro.service.PersonaService;
import gob.dp.sid.reporte.entity.ReporteSidConteo;
import gob.dp.sid.reporte.entity.ReporteSidExpediente;
import gob.dp.sid.reporte.entity.StringReport;
import gob.dp.sid.reporte.service.ReporteSidExpedienteService;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author carlos
 */
@Named
@Scope("session")
public class ReporteSidController extends AbstractManagedBean implements Serializable {

    private static final Logger log = Logger.getLogger(ReporteSidController.class);

    private ReporteSidExpediente reporteSidExpediente;

    private List<ReporteSidExpediente> listaReporteSidExpediente;
    
    private List<ReporteSidExpediente> listaReporteSidGestion;
    
    private List<ReporteSidExpediente> listaReporteSidGestionInterna;
    
    private List<ReporteSidConteo> listaReporteSidEntidad;
    
    private List<ReporteSidConteo> listaReporteSidClasificacion;
    
    private List<ReporteSidConteo> listaReporteSidComisionado;
    
    private List<ReporteSidConteo> listaReporteSidDependencia;

    private Long nroPaginaEntidad = 1L;

    public Entidad entidadReporte;

    private List<Entidad> entidadPopover;

    private String nombreEntidad;

    private List<Usuario> listaUsuarioOD;

    private List<SelectItem> listaProvincia;

    private List<SelectItem> listaDistrito;

    private Usuario usuarioSession;

    private String usuarioPalabra;

    private String clasificacionPalabra;

    private List<SelectItem> listaClasificacionPrimerLevel;

    private List<SelectItem> listaClasificacionSegundoLevel;

    private List<SelectItem> listaClasificacionTercerLevel;

    private List<SelectItem> listaClasificacionCuartoLevel;

    private List<SelectItem> listaClasificacionQuintoLevel;

    private List<SelectItem> listaClasificacionSextoLevel;

    private ExpedienteNivel expedienteNivel;

    private ExpedienteClasificacion expedienteClasificacionBusqueda;

    private List<ExpedienteNivel> listaExpedienteNivelModal;

    private Integer nroPaginaModal = 1;

    private Integer pagina;

    private Integer tipoReporte;

    JasperPrint jasperPrint;

    @Autowired
    private ReporteSidExpedienteService expedienteReporteService;

    @Autowired
    private EntidadService entidadService;

    @Autowired
    private UbigeoService ubigeoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ExpedienteClasificacionService expedienteClasificacionService;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private ExpedienteNivelService expedienteNivelService;

    @Autowired
    private ParametroService parametroService;

    @Autowired
    private ExpedienteConsultaService expedienteConsultaService;

    public String cargarPaginaExpedienteReporte() {
        reporteSidExpediente = new ReporteSidExpediente();
        expedienteClasificacionBusqueda = new ExpedienteClasificacion();
        listaReporteSidExpediente = null;
        entidadReporte = new Entidad();
        usuarioSession();
        expedienteNivel = new ExpedienteNivel();
        setTipoReporte(1);
        return "expedienteReporte";
    }
    
    public String cargarPaginaGestionReporte() {
        reporteSidExpediente = new ReporteSidExpediente();
        expedienteClasificacionBusqueda = new ExpedienteClasificacion();
        listaReporteSidGestion = null;
        entidadReporte = new Entidad();
        usuarioSession();
        expedienteNivel = new ExpedienteNivel();
        setTipoReporte(1);
        return "gestionReporte";
    }
    
    public String cargarPaginaEntidadReporte() {
        reporteSidExpediente = new ReporteSidExpediente();
        expedienteClasificacionBusqueda = new ExpedienteClasificacion();
        listaReporteSidEntidad = null;
        entidadReporte = new Entidad();
        usuarioSession();
        expedienteNivel = new ExpedienteNivel();
        setTipoReporte(1);
        return "entidadReporte";
    }
    
    public String cargarPaginaDependenciaReporte() {
        reporteSidExpediente = new ReporteSidExpediente();
        expedienteClasificacionBusqueda = new ExpedienteClasificacion();
        listaReporteSidGestion = null;
        listaReporteSidDependencia = null;
        entidadReporte = new Entidad();
        usuarioSession();
        expedienteNivel = new ExpedienteNivel();
        setTipoReporte(1);
        return "dependenciaReporte";
    }
    
    public String cargarPaginaGestionInternaReporte() {
        reporteSidExpediente = new ReporteSidExpediente();
        expedienteClasificacionBusqueda = new ExpedienteClasificacion();
        listaReporteSidGestion = null;
        listaReporteSidGestionInterna = null;
        entidadReporte = new Entidad();
        usuarioSession();
        expedienteNivel = new ExpedienteNivel();
        setTipoReporte(1);
        return "gestionInternaReporte";
    }
    
    public String cargarPaginaTematicaReporte() {
        reporteSidExpediente = new ReporteSidExpediente();
        expedienteClasificacionBusqueda = new ExpedienteClasificacion();
        listaReporteSidGestion = null;
        listaReporteSidClasificacion = null;
        entidadReporte = new Entidad();
        usuarioSession();
        expedienteNivel = new ExpedienteNivel();
        setTipoReporte(1);
        return "tematicaReporte";
    }
    
    public String cargarPaginaComisionadoReporte() {
        reporteSidExpediente = new ReporteSidExpediente();
        expedienteClasificacionBusqueda = new ExpedienteClasificacion();
        listaReporteSidComisionado = null;
        listaReporteSidGestion = null;
        entidadReporte = new Entidad();
        usuarioSession();
        expedienteNivel = new ExpedienteNivel();
        setTipoReporte(1);
        return "comisionadoReporte";
    }
    
    

    public void limpiaListasReporte() {
        System.out.println(entidadReporte);
        entidadPopover = null;
    }

    public void reporte(Integer tipoReporte, Integer pag) {
        setTipoReporte(tipoReporte);
        if (tipoReporte == 1) {
            buscarReporteExpediente(pag);
        }
        if (tipoReporte == 2) {
            buscarReporteExpedienteClasificacion(pag);
        }
        if (tipoReporte == 3) {
            buscarReporteExpedienteRecurrente(pag);
        }
        if (tipoReporte == 4) {
            buscarReporteExpedienteAfectado(pag);
        }
    }
    
    public void reporteGestion(Integer tipoReporte, Integer pag) {
        setTipoReporte(tipoReporte);
        if (tipoReporte == 1) {
            buscarReporteExpediente(pag);
        }
        if (tipoReporte == 2) {
            buscarReporteExpedienteClasificacion(pag);
        }
        if (tipoReporte == 3) {
            buscarReporteExpedienteRecurrente(pag);
        }
        if (tipoReporte == 4) {
            buscarReporteExpedienteAfectado(pag);
        }
    }

    public void reporte(Integer pag) {
        if (tipoReporte == 1) {
            buscarReporteExpediente(pag);
        }
        if (tipoReporte == 2) {
            buscarReporteExpedienteClasificacion(pag);
        }
        if (tipoReporte == 3) {
            buscarReporteExpedienteRecurrente(pag);
        }
        if (tipoReporte == 4) {
            buscarReporteExpedienteAfectado(pag);
        }
    }

    private void initJasper(int tipo) throws JRException, IOException {
        if (StringUtils.isNotBlank(reporteSidExpediente.getGrupoVulnerable())) {
            List<String> lis = new ArrayList<>();
            String[] adArray = reporteSidExpediente.getGrupoVulnerable().split(",");
            for (String adArray1 : adArray) {
                lis.add(adArray1);
            }

            if (lis.size() > 0) {
                reporteSidExpediente.setGruposVulnerables(lis);
            }
        }
        List<ReporteSidExpediente> list = expedienteReporteService.listaExpedienteReporteExport(reporteSidExpediente);
        for (ReporteSidExpediente ec : list) {
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            StringBuilder sb3 = new StringBuilder();
            StringBuilder sb4 = new StringBuilder();
            StringBuilder sb5 = new StringBuilder();
            ec.setPersonasRecurrentes(personaService.personaPorExpedienteRecurrente(ec.getIdExpediente()));
            ec.setPersonasAfectados(personaService.personaPorExpedienteAfectado(ec.getIdExpediente()));
            ec.setEntidades(entidadService.entidadPorExpediente(ec.getIdExpediente()));
            if (ec.getNumeroExpediente() != null) {
                ec.setNiveles(expedienteNivelService.expedienteNivelPorExpediente(ec.getNumeroExpediente()));
            }

            for(StringReport sr : ec.getPersonasRecurrentes()){
                sb3.append(sr.getValor().trim()+", ");
            }
            if(StringUtils.isNotBlank(sb3)){
                ec.setPersonasRecurrentesString(sb3.toString().trim().substring(0, sb3.toString().length() - 2));
            }else{
                ec.setPersonasRecurrentesString("");
            }
            
            for(StringReport sr : ec.getPersonasAfectados()){
                sb4.append(sr.getValor().trim()+", ");
            }
            if(StringUtils.isNotBlank(sb4)){
                ec.setPersonasAfectadosString(sb4.toString().trim().substring(0, sb4.toString().length() - 2));
            }else{
                ec.setPersonasAfectadosString("");
            }
            
            for(String sr : ec.getEntidades()){
                sb5.append(sr.trim()+", ");
            }
            if(StringUtils.isNotBlank(sb5)){
                ec.setEntidadesString(sb5.toString().trim().substring(0, sb5.toString().length() - 2));
            }else{
                ec.setEntidadesString("");
            }
            
            if (StringUtils.isNotBlank(ec.getTipoGrupoVulnerable())) {
                String[] adArray = ec.getTipoGrupoVulnerable().split(",");
                for (String adArray1 : adArray) {
                    if (StringUtils.isNotBlank(adArray1)) {
                        FiltroParametro fp = new FiltroParametro();
                        fp.setCodigoPadreParametro(130);
                        fp.setValorParametro(adArray1);
                        sb1.append(parametroService.consultarParametroValor(fp).getNombreParametro().trim() + ", ");
                        ec.setGruposVulnerablesString(sb1.toString().trim().substring(0, sb1.toString().length() - 2));
                    }
                }
            }
            if (StringUtils.isNotBlank(ec.getEtiqueta())) {
                String[] adArray2 = ec.getEtiqueta().split(",");
                for (String adArray1 : adArray2) {
                    if (StringUtils.isNotBlank(adArray1)) {
                        FiltroParametro fp = new FiltroParametro();
                        fp.setCodigoPadreParametro(60);
                        fp.setValorParametro(adArray1);
                        sb2.append(parametroService.consultarParametroValor(fp).getNombreParametro().trim() + ", ");
                        ec.setEtiquetasString(sb2.toString().trim().substring(0, sb2.toString().length() - 2));
                    }
                }
            }

            if (StringUtils.isNotBlank(ec.getNumeroExpediente())) {
                if (expedienteConsultaService.expedienteConsultaPorExpediente(ec.getNumeroExpediente()).size() > 0) {
                    ExpedienteConsulta ec1 = expedienteConsultaService.expedienteConsultaPorExpediente(ec.getNumeroExpediente()).get(0);
                    if (ec1 != null) {
                        if (ec1.getEtapa() > 0 && ec1.getEtapa() < 6) {
                            reporteSidExpediente.setEstaEnConsulta("Si");
                        } else {
                            reporteSidExpediente.setEstaEnConsulta("No");
                        }
                    }
                    reporteSidExpediente.setEstaEnConsulta("No");
                }
            }

        }

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(list);
        if (tipo == 1) {
            jasperPrint = JasperFillManager.fillReport(retornaRutaPath().concat("/jasper/reporteSidExpedientePDF.jasper"), new HashMap(), beanCollectionDataSource);
        }
        if (tipo == 2) {
            String ruta = retornaRutaPath().concat("/jasper/reporteSidExpedienteExcel.jasper");
            jasperPrint = JasperFillManager.fillReport(ruta, new HashMap(), beanCollectionDataSource);
        }
    }
    
    private void initJasperGestion(int tipo) throws JRException, IOException {
        
            DateFormat format = new SimpleDateFormat("yyyyMMdd");
            if (reporteSidExpediente.getFechaIngresoDesde() != null && reporteSidExpediente.getFechaIngresoHasta() != null) {
                reporteSidExpediente.setFechaIngresoDesdeString(format.format(reporteSidExpediente.getFechaIngresoDesde()));
                reporteSidExpediente.setFechaIngresoHastaString(format.format(reporteSidExpediente.getFechaIngresoHasta()));
            }

            if (reporteSidExpediente.getFechaConclusionDesde() != null && reporteSidExpediente.getFechaConclusionHasta() != null) {
                reporteSidExpediente.setFechaConclusionDesdeString(format.format(reporteSidExpediente.getFechaConclusionDesde()));
                reporteSidExpediente.setFechaConclusionHastaString(format.format(reporteSidExpediente.getFechaConclusionHasta()));
            }
            
        List<ReporteSidExpediente> list = expedienteReporteService.listaGestionReporteExport(reporteSidExpediente);
        
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(list);
        if (tipo == 1) {
            jasperPrint = JasperFillManager.fillReport(retornaRutaPath().concat("/jasper/reporteSidExpedienteGestionPDF.jasper"), new HashMap(), beanCollectionDataSource);
        }
        if (tipo == 2) {
            String ruta = retornaRutaPath().concat("/jasper/reporteSidExpedienteGestionExcel.jasper");
            jasperPrint = JasperFillManager.fillReport(ruta, new HashMap(), beanCollectionDataSource);
        }
    }
    
    private void initJasperEntidad(int tipo) throws JRException, IOException {
        
            DateFormat format = new SimpleDateFormat("yyyyMMdd");
            if (reporteSidExpediente.getFechaIngresoDesde() != null && reporteSidExpediente.getFechaIngresoHasta() != null) {
                reporteSidExpediente.setFechaIngresoDesdeString(format.format(reporteSidExpediente.getFechaIngresoDesde()));
                reporteSidExpediente.setFechaIngresoHastaString(format.format(reporteSidExpediente.getFechaIngresoHasta()));
            }

            if (reporteSidExpediente.getFechaConclusionDesde() != null && reporteSidExpediente.getFechaConclusionHasta() != null) {
                reporteSidExpediente.setFechaConclusionDesdeString(format.format(reporteSidExpediente.getFechaConclusionDesde()));
                reporteSidExpediente.setFechaConclusionHastaString(format.format(reporteSidExpediente.getFechaConclusionHasta()));
            }
            
        List<ReporteSidConteo> list = expedienteReporteService.listaEntidadReporteExport(reporteSidExpediente);
        
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(list);
        if (tipo == 1) {
            jasperPrint = JasperFillManager.fillReport(retornaRutaPath().concat("/jasper/reporteSidExpedienteEntidadPDF.jasper"), new HashMap(), beanCollectionDataSource);
        }
        if (tipo == 2) {
            String ruta = retornaRutaPath().concat("/jasper/reporteSidExpedienteEntidadExcel.jasper");
            jasperPrint = JasperFillManager.fillReport(ruta, new HashMap(), beanCollectionDataSource);
        }
    }
    
    private void initJasperComisionado(int tipo) throws JRException, IOException {
        
            DateFormat format = new SimpleDateFormat("yyyyMMdd");
            if (reporteSidExpediente.getFechaIngresoDesde() != null && reporteSidExpediente.getFechaIngresoHasta() != null) {
                reporteSidExpediente.setFechaIngresoDesdeString(format.format(reporteSidExpediente.getFechaIngresoDesde()));
                reporteSidExpediente.setFechaIngresoHastaString(format.format(reporteSidExpediente.getFechaIngresoHasta()));
            }

            if (reporteSidExpediente.getFechaConclusionDesde() != null && reporteSidExpediente.getFechaConclusionHasta() != null) {
                reporteSidExpediente.setFechaConclusionDesdeString(format.format(reporteSidExpediente.getFechaConclusionDesde()));
                reporteSidExpediente.setFechaConclusionHastaString(format.format(reporteSidExpediente.getFechaConclusionHasta()));
            }
            
            
                List<ReporteSidConteo> list = expedienteReporteService.listaExpedienteReporteComisionado(reporteSidExpediente);
                
          
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(list);
        if (tipo == 1) {
            jasperPrint = JasperFillManager.fillReport(retornaRutaPath().concat("/jasper/reporteSidExpedienteComisionadoPDF.jasper"), new HashMap(), beanCollectionDataSource);
        }
        if (tipo == 2) {
            String ruta = retornaRutaPath().concat("/jasper/reporteSidExpedienteComisionadoExcel.jasper");
            jasperPrint = JasperFillManager.fillReport(ruta, new HashMap(), beanCollectionDataSource);
        }
    }
    
    private void initJasperDependencia(int tipo) throws JRException, IOException {
        
            DateFormat format = new SimpleDateFormat("yyyyMMdd");
            if (reporteSidExpediente.getFechaIngresoDesde() != null && reporteSidExpediente.getFechaIngresoHasta() != null) {
                reporteSidExpediente.setFechaIngresoDesdeString(format.format(reporteSidExpediente.getFechaIngresoDesde()));
                reporteSidExpediente.setFechaIngresoHastaString(format.format(reporteSidExpediente.getFechaIngresoHasta()));
            }

            if (reporteSidExpediente.getFechaConclusionDesde() != null && reporteSidExpediente.getFechaConclusionHasta() != null) {
                reporteSidExpediente.setFechaConclusionDesdeString(format.format(reporteSidExpediente.getFechaConclusionDesde()));
                reporteSidExpediente.setFechaConclusionHastaString(format.format(reporteSidExpediente.getFechaConclusionHasta()));
            }
            
            
                List<ReporteSidConteo> list = expedienteReporteService.listaExpedienteReporteDependencia(reporteSidExpediente);
                
          
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(list);
        if (tipo == 1) {
            jasperPrint = JasperFillManager.fillReport(retornaRutaPath().concat("/jasper/reporteSidExpedienteDependenciaPDF.jasper"), new HashMap(), beanCollectionDataSource);
        }
        if (tipo == 2) {
            String ruta = retornaRutaPath().concat("/jasper/reporteSidExpedienteDependenciaExcel.jasper");
            jasperPrint = JasperFillManager.fillReport(ruta, new HashMap(), beanCollectionDataSource);
        }
    }
    
    private void initJasperGestionInterna(int tipo) throws JRException, IOException {
        
            DateFormat format = new SimpleDateFormat("yyyyMMdd");
            if (reporteSidExpediente.getFechaIngresoDesde() != null && reporteSidExpediente.getFechaIngresoHasta() != null) {
                reporteSidExpediente.setFechaIngresoDesdeString(format.format(reporteSidExpediente.getFechaIngresoDesde()));
                reporteSidExpediente.setFechaIngresoHastaString(format.format(reporteSidExpediente.getFechaIngresoHasta()));
            }

            if (reporteSidExpediente.getFechaConclusionDesde() != null && reporteSidExpediente.getFechaConclusionHasta() != null) {
                reporteSidExpediente.setFechaConclusionDesdeString(format.format(reporteSidExpediente.getFechaConclusionDesde()));
                reporteSidExpediente.setFechaConclusionHastaString(format.format(reporteSidExpediente.getFechaConclusionHasta()));
            }
            
            
                List<ReporteSidExpediente> list = expedienteReporteService.listaExpedienteReporteGestionInternaExport(reporteSidExpediente);
                
          
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(list);
        if (tipo == 1) {
            jasperPrint = JasperFillManager.fillReport(retornaRutaPath().concat("/jasper/reporteSidExpedienteGestionInternaPDF.jasper"), new HashMap(), beanCollectionDataSource);
        }
        if (tipo == 2) {
            String ruta = retornaRutaPath().concat("/jasper/reporteSidExpedienteGestionInternaExcel.jasper");
            jasperPrint = JasperFillManager.fillReport(ruta, new HashMap(), beanCollectionDataSource);
        }
    }
    
    
    private void initJasperClasificacion(int tipo) throws JRException, IOException {
        
            DateFormat format = new SimpleDateFormat("yyyyMMdd");
            if (reporteSidExpediente.getFechaIngresoDesde() != null && reporteSidExpediente.getFechaIngresoHasta() != null) {
                reporteSidExpediente.setFechaIngresoDesdeString(format.format(reporteSidExpediente.getFechaIngresoDesde()));
                reporteSidExpediente.setFechaIngresoHastaString(format.format(reporteSidExpediente.getFechaIngresoHasta()));
            }

            if (reporteSidExpediente.getFechaConclusionDesde() != null && reporteSidExpediente.getFechaConclusionHasta() != null) {
                reporteSidExpediente.setFechaConclusionDesdeString(format.format(reporteSidExpediente.getFechaConclusionDesde()));
                reporteSidExpediente.setFechaConclusionHastaString(format.format(reporteSidExpediente.getFechaConclusionHasta()));
            }
            
        List<ReporteSidConteo> list = expedienteReporteService.listaExpedienteReporteClasificacionExport(reporteSidExpediente);
        
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(list);
        if (tipo == 1) {
            jasperPrint = JasperFillManager.fillReport(retornaRutaPath().concat("/jasper/reporteSidExpedienteClasificacionPDF.jasper"), new HashMap(), beanCollectionDataSource);
        }
        if (tipo == 2) {
            String ruta = retornaRutaPath().concat("/jasper/reporteSidExpedienteClasificacionExcel.jasper");
            jasperPrint = JasperFillManager.fillReport(ruta, new HashMap(), beanCollectionDataSource);
        }
    }

    public void reporteExpdientePdf() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String fecha = simpleDateFormat.format(date);
        initJasper(1);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse;
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_expediente.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        facesContext.responseComplete();
        facesContext.renderResponse();
    }

    public void reporteExpdienteExcel() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = simpleDateFormat.format(date);
        initJasper(2);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse = (HttpServletResponse) facesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_expediente.xlsx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRXlsxExporter jrXlsxExporter = new JRXlsxExporter();
        jrXlsxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.FALSE);
        jrXlsxExporter.exportReport();
        facesContext.responseComplete();
        facesContext.renderResponse();
    }

    public void reporteGestionExcel() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = simpleDateFormat.format(date);
        initJasperGestion(2);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse = (HttpServletResponse) facesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_gestion.xlsx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRXlsxExporter jrXlsxExporter = new JRXlsxExporter();
        jrXlsxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.FALSE);
        jrXlsxExporter.exportReport();
        facesContext.responseComplete();
        facesContext.renderResponse();
    }
    
    public void reporteGestionPdf() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String fecha = simpleDateFormat.format(date);
        initJasperGestion(1);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse;
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_gestion.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        facesContext.responseComplete();
        facesContext.renderResponse();
    }
    
    public void reporteEntidadExcel() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = simpleDateFormat.format(date);
        initJasperEntidad(2);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse = (HttpServletResponse) facesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_entidad.xlsx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRXlsxExporter jrXlsxExporter = new JRXlsxExporter();
        jrXlsxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.FALSE);
        jrXlsxExporter.exportReport();
        facesContext.responseComplete();
        facesContext.renderResponse();
    }
    
    public void reporteEntidadPdf() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String fecha = simpleDateFormat.format(date);
        initJasperEntidad(1);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse;
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_entidad.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        facesContext.responseComplete();
        facesContext.renderResponse();
    }
    
    public void reporteClasificacionExcel() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = simpleDateFormat.format(date);
        initJasperClasificacion(2);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse = (HttpServletResponse) facesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_clasificacion.xlsx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRXlsxExporter jrXlsxExporter = new JRXlsxExporter();
        jrXlsxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.FALSE);
        jrXlsxExporter.exportReport();
        facesContext.responseComplete();
        facesContext.renderResponse();
    }
    
    public void reporteClasificacionPdf() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String fecha = simpleDateFormat.format(date);
        initJasperClasificacion(1);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse;
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_clasificacion.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        facesContext.responseComplete();
        facesContext.renderResponse();
    }
    
    public void reporteComisionadoExcel() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = simpleDateFormat.format(date);
        initJasperComisionado(2);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse = (HttpServletResponse) facesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_comisionado.xlsx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRXlsxExporter jrXlsxExporter = new JRXlsxExporter();
        jrXlsxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.FALSE);
        jrXlsxExporter.exportReport();
        facesContext.responseComplete();
        facesContext.renderResponse();
    }
    
    public void reporteComisionadoPdf() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String fecha = simpleDateFormat.format(date);
        initJasperComisionado(1);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse;
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_comisionado.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        facesContext.responseComplete();
        facesContext.renderResponse();
    }
    
    public void reporteDependenciaExcel() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = simpleDateFormat.format(date);
        initJasperDependencia(2);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse = (HttpServletResponse) facesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_dependencia.xlsx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRXlsxExporter jrXlsxExporter = new JRXlsxExporter();
        jrXlsxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.FALSE);
        jrXlsxExporter.exportReport();
        facesContext.responseComplete();
        facesContext.renderResponse();
    }
    
    public void reporteDependenciaPdf() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String fecha = simpleDateFormat.format(date);
        initJasperDependencia(1);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse;
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_dependencia.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        facesContext.responseComplete();
        facesContext.renderResponse();
    }
    
    public void reporteGestionInternaExcel() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = simpleDateFormat.format(date);
        initJasperGestionInterna(2);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse = (HttpServletResponse) facesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_gestionInterna.xlsx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRXlsxExporter jrXlsxExporter = new JRXlsxExporter();
        jrXlsxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        jrXlsxExporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.FALSE);
        jrXlsxExporter.exportReport();
        facesContext.responseComplete();
        facesContext.renderResponse();
    }
    
    public void reporteGestionInternaPdf() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String fecha = simpleDateFormat.format(date);
        initJasperGestionInterna(1);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse;
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_gestionInterna.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        facesContext.responseComplete();
        facesContext.renderResponse();
    }
    
    public boolean buscarReporteExpediente(Integer pag) {
        try {
            if (reporteSidExpediente.getFechaIngresoDesde() == null && reporteSidExpediente.getFechaIngresoHasta() == null && reporteSidExpediente.getFechaConclusionDesde() == null && reporteSidExpediente.getFechaConclusionHasta() == null) {
                msg.messageAlert("Debe ingresar un rango de fechas ingreso / conclusión", null);
                return false;
            }
            if (reporteSidExpediente.getFechaIngresoDesde() == null && reporteSidExpediente.getFechaIngresoHasta() != null) {
                msg.messageAlert("Debe ingresar la fecha de inicio de ingreso", null);
                return false;
            }
            if (reporteSidExpediente.getFechaIngresoDesde() != null && reporteSidExpediente.getFechaIngresoHasta() == null) {
                msg.messageAlert("Debe ingresar la fecha de fin de ingreso", null);
                return false;
            }
            DateFormat format = new SimpleDateFormat("yyyyMMdd");
            if (reporteSidExpediente.getFechaIngresoDesde() != null && reporteSidExpediente.getFechaIngresoHasta() != null) {
                reporteSidExpediente.setFechaIngresoDesdeString(format.format(reporteSidExpediente.getFechaIngresoDesde()));
                reporteSidExpediente.setFechaIngresoHastaString(format.format(reporteSidExpediente.getFechaIngresoHasta()));
            }

            if (reporteSidExpediente.getFechaConclusionDesde() == null && reporteSidExpediente.getFechaConclusionHasta() != null) {
                msg.messageAlert("Debe ingresar la fecha de inicio de conclusión", null);
                return false;
            }
            if (reporteSidExpediente.getFechaConclusionDesde() != null && reporteSidExpediente.getFechaConclusionHasta() == null) {
                msg.messageAlert("Debe ingresar la fecha de fin de conclusión", null);
                return false;
            }

            if (reporteSidExpediente.getFechaConclusionDesde() != null && reporteSidExpediente.getFechaConclusionHasta() != null) {
                reporteSidExpediente.setFechaConclusionDesdeString(format.format(reporteSidExpediente.getFechaConclusionDesde()));
                reporteSidExpediente.setFechaConclusionHastaString(format.format(reporteSidExpediente.getFechaConclusionHasta()));
            }
            if (StringUtils.isNotBlank(reporteSidExpediente.getGrupoVulnerable())) {
                List<String> lis = new ArrayList<>();
                String[] adArray = reporteSidExpediente.getGrupoVulnerable().split(",");
                for (String adArray1 : adArray) {
                    lis.add(adArray1);
                }
                if (lis.size() > 0) {
                    reporteSidExpediente.setGruposVulnerables(lis);
                }
            }
            if (pag > 0) {
                int paginado = ConstantesUtil.PAGINADO_10;
                Integer ini = paginado * (pag - 1) + 1;
                Integer fin = paginado * pag;
                if (pag == 0) {
                    ini = 1;
                    fin = 10;
                }
                reporteSidExpediente.setIni(ini);
                reporteSidExpediente.setFin(fin);
                List<ReporteSidExpediente> list = expedienteReporteService.listaExpedienteReporte(reporteSidExpediente);
                for (ReporteSidExpediente ec : list) {
                    ec.setPersonasRecurrentes(personaService.personaPorExpedienteRecurrente(ec.getIdExpediente()));
                    ec.setPersonasAfectados(personaService.personaPorExpedienteAfectado(ec.getIdExpediente()));
                    ec.setEntidades(entidadService.entidadPorExpediente(ec.getIdExpediente()));
                    if (ec.getNumeroExpediente() != null) {
                        ec.setNiveles(expedienteNivelService.expedienteNivelPorExpediente(ec.getNumeroExpediente()));
                    }
                }
                if (list.size() > 0) {
                    listaReporteSidExpediente = list;
                    pagina = pag;
                } else {
                    if (reporteSidExpediente.getIni() == 1) {
                        listaReporteSidExpediente = null;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            log.error("ERROR - buscarClasificacion()" + e);
        }
        return false;
    }
    
    public boolean buscarReporteGestion(Integer pag) {
        try {
            if (reporteSidExpediente.getFechaIngresoDesde() == null && reporteSidExpediente.getFechaIngresoHasta() == null && reporteSidExpediente.getFechaConclusionDesde() == null && reporteSidExpediente.getFechaConclusionHasta() == null) {
                msg.messageAlert("Debe ingresar un rango de fechas ingreso / conclusión", null);
                return false;
            }
            if (reporteSidExpediente.getFechaIngresoDesde() == null && reporteSidExpediente.getFechaIngresoHasta() != null) {
                msg.messageAlert("Debe ingresar la fecha de inicio de ingreso", null);
                return false;
            }
            if (reporteSidExpediente.getFechaIngresoDesde() != null && reporteSidExpediente.getFechaIngresoHasta() == null) {
                msg.messageAlert("Debe ingresar la fecha de fin de ingreso", null);
                return false;
            }
            DateFormat format = new SimpleDateFormat("yyyyMMdd");
            if (reporteSidExpediente.getFechaIngresoDesde() != null && reporteSidExpediente.getFechaIngresoHasta() != null) {
                reporteSidExpediente.setFechaIngresoDesdeString(format.format(reporteSidExpediente.getFechaIngresoDesde()));
                reporteSidExpediente.setFechaIngresoHastaString(format.format(reporteSidExpediente.getFechaIngresoHasta()));
            }

            if (reporteSidExpediente.getFechaConclusionDesde() == null && reporteSidExpediente.getFechaConclusionHasta() != null) {
                msg.messageAlert("Debe ingresar la fecha de inicio de conclusión", null);
                return false;
            }
            if (reporteSidExpediente.getFechaConclusionDesde() != null && reporteSidExpediente.getFechaConclusionHasta() == null) {
                msg.messageAlert("Debe ingresar la fecha de fin de conclusión", null);
                return false;
            }

            if (reporteSidExpediente.getFechaConclusionDesde() != null && reporteSidExpediente.getFechaConclusionHasta() != null) {
                reporteSidExpediente.setFechaConclusionDesdeString(format.format(reporteSidExpediente.getFechaConclusionDesde()));
                reporteSidExpediente.setFechaConclusionHastaString(format.format(reporteSidExpediente.getFechaConclusionHasta()));
            }
            if (pag > 0) {
                int paginado = ConstantesUtil.PAGINADO_10;
                Integer ini = paginado * (pag - 1) + 1;
                Integer fin = paginado * pag;
                if (pag == 0) {
                    ini = 1;
                    fin = 10;
                }
                reporteSidExpediente.setIni(ini);
                reporteSidExpediente.setFin(fin);
                List<ReporteSidExpediente> list = expedienteReporteService.listaGestionReporte(reporteSidExpediente);
                if (list.size() > 0) {
                    listaReporteSidGestion = list;
                    pagina = pag;
                } else {
                    if (reporteSidExpediente.getIni() == 1) {
                        listaReporteSidGestion = null;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            log.error("ERROR - buscarClasificacion()" + e);
        }
        return false;
    }
    
    public boolean buscarReporteEntidad(Integer pag) {
        try {
            if (reporteSidExpediente.getFechaIngresoDesde() != null && reporteSidExpediente.getFechaIngresoHasta() == null) {
                msg.messageAlert("Debe ingresar la fecha de fin de ingreso", null);
                return false;
            }
            if (reporteSidExpediente.getFechaIngresoDesde() == null && reporteSidExpediente.getFechaIngresoHasta() != null) {
                msg.messageAlert("Debe ingresar la fecha de inicio de ingreso", null);
                return false;
            }
            
            DateFormat format = new SimpleDateFormat("yyyyMMdd");
            if (reporteSidExpediente.getFechaIngresoDesde() != null && reporteSidExpediente.getFechaIngresoHasta() != null) {
                reporteSidExpediente.setFechaIngresoDesdeString(format.format(reporteSidExpediente.getFechaIngresoDesde()));
                reporteSidExpediente.setFechaIngresoHastaString(format.format(reporteSidExpediente.getFechaIngresoHasta()));
            }

            if (reporteSidExpediente.getFechaConclusionDesde() == null && reporteSidExpediente.getFechaConclusionHasta() != null) {
                msg.messageAlert("Debe ingresar la fecha de inicio de conclusión", null);
                return false;
            }
            if (reporteSidExpediente.getFechaConclusionDesde() != null && reporteSidExpediente.getFechaConclusionHasta() == null) {
                msg.messageAlert("Debe ingresar la fecha de fin de conclusión", null);
                return false;
            }

            if (reporteSidExpediente.getFechaConclusionDesde() != null && reporteSidExpediente.getFechaConclusionHasta() != null) {
                reporteSidExpediente.setFechaConclusionDesdeString(format.format(reporteSidExpediente.getFechaConclusionDesde()));
                reporteSidExpediente.setFechaConclusionHastaString(format.format(reporteSidExpediente.getFechaConclusionHasta()));
            }
            if (StringUtils.isNotBlank(reporteSidExpediente.getGrupoVulnerable())) {
                List<String> lis = new ArrayList<>();
                String[] adArray = reporteSidExpediente.getGrupoVulnerable().split(",");
                for (String adArray1 : adArray) {
                    lis.add(adArray1);
                }
                if (lis.size() > 0) {
                    reporteSidExpediente.setGruposVulnerables(lis);
                }
            }
            if (pag > 0) {
                int paginado = ConstantesUtil.PAGINADO_10;
                Integer ini = paginado * (pag - 1) + 1;
                Integer fin = paginado * pag;
                if (pag == 0) {
                    ini = 1;
                    fin = 10;
                }
                reporteSidExpediente.setIni(ini);
                reporteSidExpediente.setFin(fin);
                List<ReporteSidConteo> list = expedienteReporteService.listaEntidadReporte(reporteSidExpediente);
                if (list.size() > 0) {
                    listaReporteSidEntidad = list;
                    pagina = pag;
                } else {
                    if (reporteSidExpediente.getIni() == 1) {
                        listaReporteSidEntidad = null;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            log.error("ERROR - buscarClasificacion()" + e);
        }
        return false;
    }
    
    public boolean buscarReporteExpedienteClasificacion(Integer pag) {
        try {
            if (reporteSidExpediente.getFechaIngresoDesde() != null && reporteSidExpediente.getFechaIngresoHasta() == null) {
                msg.messageAlert("Debe ingresar la fecha de fin de ingreso", null);
                return false;
            }
            if (reporteSidExpediente.getFechaIngresoDesde() == null && reporteSidExpediente.getFechaIngresoHasta() != null) {
                msg.messageAlert("Debe ingresar la fecha de inicio de ingreso", null);
                return false;
            }
            if (reporteSidExpediente.getFechaConclusionDesde() != null && reporteSidExpediente.getFechaConclusionHasta() == null) {
                msg.messageAlert("Debe ingresar la fecha de fin de conclusión", null);
                return false;
            }
            if (reporteSidExpediente.getFechaConclusionDesde() == null && reporteSidExpediente.getFechaConclusionHasta() != null) {
                msg.messageAlert("Debe ingresar la fecha de inicio de conclusión", null);
                return false;
            }
            
            DateFormat format = new SimpleDateFormat("yyyyMMdd");
            if (reporteSidExpediente.getFechaIngresoDesde() != null && reporteSidExpediente.getFechaIngresoHasta() != null) {
                reporteSidExpediente.setFechaIngresoDesdeString(format.format(reporteSidExpediente.getFechaIngresoDesde()));
                reporteSidExpediente.setFechaIngresoHastaString(format.format(reporteSidExpediente.getFechaIngresoHasta()));
            }

            if (reporteSidExpediente.getFechaConclusionDesde() != null && reporteSidExpediente.getFechaConclusionHasta() != null) {
                reporteSidExpediente.setFechaConclusionDesdeString(format.format(reporteSidExpediente.getFechaConclusionDesde()));
                reporteSidExpediente.setFechaConclusionHastaString(format.format(reporteSidExpediente.getFechaConclusionHasta()));
            }
            if (pag > 0) {
                int paginado = ConstantesUtil.PAGINADO_10;
                Integer ini = paginado * (pag - 1) + 1;
                Integer fin = paginado * pag;
                if (pag == 0) {
                    ini = 1;
                    fin = 10;
                }
                reporteSidExpediente.setIni(ini);
                reporteSidExpediente.setFin(fin);
                List<ReporteSidConteo> list = expedienteReporteService.listaExpedienteReporteClasificacion(reporteSidExpediente);
                
                if (list.size() > 0) {
                    listaReporteSidClasificacion = list;
                    pagina = pag;
                } else {
                    if (reporteSidExpediente.getIni() == 1) {
                        listaReporteSidClasificacion = null;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            log.error("ERROR - buscarClasificacion()" + e);
        }
        return false;
    }
    
    public boolean buscarReporteExpedienteComisionado(Integer pag) {
        try {
            if (reporteSidExpediente.getFechaIngresoDesde() != null && reporteSidExpediente.getFechaIngresoHasta() == null) {
                msg.messageAlert("Debe ingresar la fecha de fin de ingreso", null);
                return false;
            }
            if (reporteSidExpediente.getFechaIngresoDesde() == null && reporteSidExpediente.getFechaIngresoHasta() != null) {
                msg.messageAlert("Debe ingresar la fecha de inicio de ingreso", null);
                return false;
            }
            if (reporteSidExpediente.getFechaConclusionDesde() != null && reporteSidExpediente.getFechaConclusionHasta() == null) {
                msg.messageAlert("Debe ingresar la fecha de fin de conclusión", null);
                return false;
            }
            if (reporteSidExpediente.getFechaConclusionDesde() == null && reporteSidExpediente.getFechaConclusionHasta() != null) {
                msg.messageAlert("Debe ingresar la fecha de inicio de conclusión", null);
                return false;
            }
            
            DateFormat format = new SimpleDateFormat("yyyyMMdd");
            if (reporteSidExpediente.getFechaIngresoDesde() != null && reporteSidExpediente.getFechaIngresoHasta() != null) {
                reporteSidExpediente.setFechaIngresoDesdeString(format.format(reporteSidExpediente.getFechaIngresoDesde()));
                reporteSidExpediente.setFechaIngresoHastaString(format.format(reporteSidExpediente.getFechaIngresoHasta()));
            }

            if (reporteSidExpediente.getFechaConclusionDesde() != null && reporteSidExpediente.getFechaConclusionHasta() != null) {
                reporteSidExpediente.setFechaConclusionDesdeString(format.format(reporteSidExpediente.getFechaConclusionDesde()));
                reporteSidExpediente.setFechaConclusionHastaString(format.format(reporteSidExpediente.getFechaConclusionHasta()));
            }
            
            if (pag > 0) {
                int paginado = ConstantesUtil.PAGINADO_10;
                Integer ini = paginado * (pag - 1) + 1;
                Integer fin = paginado * pag;
                if (pag == 0) {
                    ini = 1;
                    fin = 10;
                }
                reporteSidExpediente.setIni(ini);
                reporteSidExpediente.setFin(fin);
                List<ReporteSidConteo> list = expedienteReporteService.listaExpedienteReporteComisionado(reporteSidExpediente);
                
                if (list.size() > 0) {
                    listaReporteSidComisionado = list;
                    pagina = pag;
                } else {
                    if (reporteSidExpediente.getIni() == 1) {
                        listaReporteSidComisionado = null;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            log.error("ERROR - buscarClasificacion()" + e);
        }
        return false;
    }
    
    public boolean buscarReporteExpedienteDependencia(Integer pag) {
        try {
            if (reporteSidExpediente.getFechaIngresoDesde() != null && reporteSidExpediente.getFechaIngresoHasta() == null) {
                msg.messageAlert("Debe ingresar la fecha de fin de ingreso", null);
                return false;
            }
            if (reporteSidExpediente.getFechaIngresoDesde() == null && reporteSidExpediente.getFechaIngresoHasta() != null) {
                msg.messageAlert("Debe ingresar la fecha de inicio de ingreso", null);
                return false;
            }
            if (reporteSidExpediente.getFechaConclusionDesde() != null && reporteSidExpediente.getFechaConclusionHasta() == null) {
                msg.messageAlert("Debe ingresar la fecha de fin de conclusión", null);
                return false;
            }
            if (reporteSidExpediente.getFechaConclusionDesde() == null && reporteSidExpediente.getFechaConclusionHasta() != null) {
                msg.messageAlert("Debe ingresar la fecha de inicio de conclusión", null);
                return false;
            }
            
            DateFormat format = new SimpleDateFormat("yyyyMMdd");
            if (reporteSidExpediente.getFechaIngresoDesde() != null && reporteSidExpediente.getFechaIngresoHasta() != null) {
                reporteSidExpediente.setFechaIngresoDesdeString(format.format(reporteSidExpediente.getFechaIngresoDesde()));
                reporteSidExpediente.setFechaIngresoHastaString(format.format(reporteSidExpediente.getFechaIngresoHasta()));
            }

            if (reporteSidExpediente.getFechaConclusionDesde() != null && reporteSidExpediente.getFechaConclusionHasta() != null) {
                reporteSidExpediente.setFechaConclusionDesdeString(format.format(reporteSidExpediente.getFechaConclusionDesde()));
                reporteSidExpediente.setFechaConclusionHastaString(format.format(reporteSidExpediente.getFechaConclusionHasta()));
            }
            
            if (pag > 0) {
                int paginado = ConstantesUtil.PAGINADO_10;
                Integer ini = paginado * (pag - 1) + 1;
                Integer fin = paginado * pag;
                if (pag == 0) {
                    ini = 1;
                    fin = 10;
                }
                reporteSidExpediente.setIni(ini);
                reporteSidExpediente.setFin(fin);
                List<ReporteSidConteo> list = expedienteReporteService.listaExpedienteReporteDependencia(reporteSidExpediente);
                
                if (list.size() > 0) {
                    listaReporteSidDependencia = list;
                    pagina = pag;
                } else {
                    if (reporteSidExpediente.getIni() == 1) {
                        listaReporteSidDependencia = null;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            log.error("ERROR - buscarClasificacion()" + e);
        }
        return false;
    }
    
    public boolean buscarReporteSidGestionInterna(Integer pag) {
        if (reporteSidExpediente.getFechaIngresoDesde() != null && reporteSidExpediente.getFechaIngresoHasta() == null) {
                msg.messageAlert("Debe ingresar la fecha de fin de ingreso", null);
                return false;
            }
            if (reporteSidExpediente.getFechaIngresoDesde() == null && reporteSidExpediente.getFechaIngresoHasta() != null) {
                msg.messageAlert("Debe ingresar la fecha de inicio de ingreso", null);
                return false;
            }
            if (reporteSidExpediente.getFechaConclusionDesde() != null && reporteSidExpediente.getFechaConclusionHasta() == null) {
                msg.messageAlert("Debe ingresar la fecha de fin de conclusión", null);
                return false;
            }
            if (reporteSidExpediente.getFechaConclusionDesde() == null && reporteSidExpediente.getFechaConclusionHasta() != null) {
                msg.messageAlert("Debe ingresar la fecha de inicio de conclusión", null);
                return false;
            }
            
            DateFormat format = new SimpleDateFormat("yyyyMMdd");
            if (reporteSidExpediente.getFechaIngresoDesde() != null && reporteSidExpediente.getFechaIngresoHasta() != null) {
                reporteSidExpediente.setFechaIngresoDesdeString(format.format(reporteSidExpediente.getFechaIngresoDesde()));
                reporteSidExpediente.setFechaIngresoHastaString(format.format(reporteSidExpediente.getFechaIngresoHasta()));
            }

            if (reporteSidExpediente.getFechaConclusionDesde() != null && reporteSidExpediente.getFechaConclusionHasta() != null) {
                reporteSidExpediente.setFechaConclusionDesdeString(format.format(reporteSidExpediente.getFechaConclusionDesde()));
                reporteSidExpediente.setFechaConclusionHastaString(format.format(reporteSidExpediente.getFechaConclusionHasta()));
            }
        try {
            if (pag > 0) {
                int paginado = ConstantesUtil.PAGINADO_10;
                Integer ini = paginado * (pag - 1) + 1;
                Integer fin = paginado * pag;
                if (pag == 0) {
                    ini = 1;
                    fin = 10;
                }
                reporteSidExpediente.setIni(ini);
                reporteSidExpediente.setFin(fin);
                List<ReporteSidExpediente> list = expedienteReporteService.listaExpedienteReporteGestionInterna(reporteSidExpediente);
                
                if (list.size() > 0) {
                    listaReporteSidGestionInterna = list;
                    pagina = pag;
                } else {
                    if (reporteSidExpediente.getIni() == 1) {
                        listaReporteSidGestionInterna = null;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            log.error("ERROR - buscarClasificacion()" + e);
        }
        return false;
    }

    private boolean buscarReporteExpedienteRecurrente(Integer pag) {
        try {
            if (StringUtils.isBlank(reporteSidExpediente.getIdDepartamentoRecurrente()) && StringUtils.isBlank(reporteSidExpediente.getEsDiscapacitadoRecurrente()) && StringUtils.isBlank(reporteSidExpediente.getTipoLenguaRecurrente()) && StringUtils.isBlank(reporteSidExpediente.getTipoPuebloRecurrente())) {
                msg.messageAlert("Debe de ingresar como mínimo un dato del recurrente ", null);
                return false;
            }
            if (pag > 0) {
                int paginado = ConstantesUtil.PAGINADO_10;
                Integer ini = paginado * (pag - 1) + 1;
                Integer fin = paginado * pag;
                if (pag == 0) {
                    ini = 1;
                    fin = 10;
                }
                reporteSidExpediente.setIni(ini);
                reporteSidExpediente.setFin(fin);
                List<ReporteSidExpediente> list = expedienteReporteService.listaExpedienteReporteRecurrente(reporteSidExpediente);
                for (ReporteSidExpediente ec : list) {
                    ec.setPersonasRecurrentes(personaService.personaPorExpedienteRecurrente(ec.getIdExpediente()));
                    ec.setPersonasAfectados(personaService.personaPorExpedienteAfectado(ec.getIdExpediente()));
                    ec.setEntidades(entidadService.entidadPorExpediente(ec.getIdExpediente()));
                    if (ec.getNumeroExpediente() != null) {
                        ec.setNiveles(expedienteNivelService.expedienteNivelPorExpediente(ec.getNumeroExpediente()));
                    }
                }
                if (list.size() > 0) {
                    listaReporteSidExpediente = list;
                    pagina = pag;
                } else {
                    if (reporteSidExpediente.getIni() == 1) {
                        listaReporteSidExpediente = null;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            log.error("ERROR - buscarClasificacion()" + e);
        }
        return false;
    }

    private boolean buscarReporteExpedienteAfectado(Integer pag) {
        try {
            if (StringUtils.isBlank(reporteSidExpediente.getIdDepartamentoAfectado()) && StringUtils.isBlank(reporteSidExpediente.getEsDiscapacitadoAfectado()) && StringUtils.isBlank(reporteSidExpediente.getTipoLenguaAfectado()) && StringUtils.isBlank(reporteSidExpediente.getTipoPuebloAfectado())) {
                msg.messageAlert("Debe de ingresar como mínimo un dato del afectado ", null);
                return false;
            }
            if (pag > 0) {
                int paginado = ConstantesUtil.PAGINADO_10;
                Integer ini = paginado * (pag - 1) + 1;
                Integer fin = paginado * pag;
                if (pag == 0) {
                    ini = 1;
                    fin = 10;
                }
                reporteSidExpediente.setIni(ini);
                reporteSidExpediente.setFin(fin);
                List<ReporteSidExpediente> list = expedienteReporteService.listaExpedienteReporteAfectado(reporteSidExpediente);
                for (ReporteSidExpediente ec : list) {
                    ec.setPersonasRecurrentes(personaService.personaPorExpedienteRecurrente(ec.getIdExpediente()));
                    ec.setPersonasAfectados(personaService.personaPorExpedienteAfectado(ec.getIdExpediente()));
                    ec.setEntidades(entidadService.entidadPorExpediente(ec.getIdExpediente()));
                    if (ec.getNumeroExpediente() != null) {
                        ec.setNiveles(expedienteNivelService.expedienteNivelPorExpediente(ec.getNumeroExpediente()));
                    }
                }
                if (list.size() > 0) {
                    listaReporteSidExpediente = list;
                    pagina = pag;
                } else {
                    if (reporteSidExpediente.getIni() == 1) {
                        listaReporteSidExpediente = null;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            log.error("ERROR - buscarClasificacion()" + e);
        }
        return false;
    }

    private void usuarioSession() {
        try {
            usuarioSession = new Usuario();
            FacesContext context = FacesContext.getCurrentInstance();
            LoginController loginController = (LoginController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "loginController");
            usuarioSession = loginController.getUsuarioSesion();
            usuarioSession.setNombreDepartamento(ubigeoService.departamentoOne(usuarioSession.getIdDepartamento()).getDescripcion());
        } catch (Exception e) {
            log.error("ERROR - usuarioSession()" + e);
        }
    }

    public boolean cargarPopoverEntidad(Long pagina) {
        try {
            if (pagina == 1) {
                nroPaginaEntidad = 1L;
            }
            if (pagina > 0) {
                int paginado = ConstantesUtil.PAGINADO_5;
                Long ini = paginado * (pagina - 1) + 1;
                Long fin = paginado * pagina;
                if (pagina == 0) {
                    ini = 1L;
                    fin = 5L;
                }
                entidadReporte.setIni(ini);
                entidadReporte.setFin(fin);
                entidadReporte.setIdDepartamento(0);
                entidadReporte.setTipo("0");
                try {
                    List<Entidad> list = entidadService.entidadBuscarCadena(entidadReporte);
                    if (list.size() > 0) {
                        entidadPopover = list;
                        nroPaginaEntidad = pagina;
                    } else {
                        if (entidadReporte.getIni() == 1) {
                            entidadPopover = null;
                        }
                    }
                } catch (Exception e) {

                }
            }
            if (entidadPopover == null) {
                msg.messageAlert("No se han encontrado Personas", null);
            }
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public void comboProvinciaEntidad() {
        try {
            listaProvincia = new ArrayList<>();
            listaDistrito = new ArrayList<>();
            String idDepartamento = reporteSidExpediente.getIdDepartamentoEntidad();
            if (StringUtils.equals(idDepartamento, "0")) {
                listaProvincia.clear();
            } else {
                List<Provincia> list = ubigeoService.provinciaLista(idDepartamento);
                if (list.size() > 0) {
                    for (Provincia provincia : list) {
                        listaProvincia.add(new SelectItem(provincia.getIdProvincia(), provincia.getDescripcion()));
                    }
                }
            }
        } catch (Exception e) {
            log.error("ERROR - comboProvincia()" + e);
        }
    }

    public void comboDistritoEntidad() {
        try {
            listaDistrito = new ArrayList<>();
            String idProvincia = reporteSidExpediente.getIdProvinciaEntidad();
            String idDepartamento = reporteSidExpediente.getIdDepartamentoEntidad();
            if (StringUtils.equals(idProvincia, "0") || StringUtils.equals(idDepartamento, "0")) {
                listaDistrito.clear();
            } else {
                Distrito d = new Distrito();
                d.setIdDepartamento(idDepartamento);
                d.setIdProvincia(idProvincia);
                List<Distrito> list = ubigeoService.distritoLista(d);
                if (list.size() > 0) {
                    for (Distrito distrito : list) {
                        listaDistrito.add(new SelectItem(distrito.getIdDistrito(), distrito.getDescripcion()));
                    }
                }
            }
        } catch (Exception e) {
            log.error("ERROR - comboDistrito()" + e);
        }
    }

    public void comboProvinciaRecurrente() {
        try {
            listaProvincia = new ArrayList<>();
            listaDistrito = new ArrayList<>();
            String idDepartamento = reporteSidExpediente.getIdDepartamentoRecurrente();
            if (StringUtils.equals(idDepartamento, "0")) {
                listaProvincia.clear();
            } else {
                List<Provincia> list = ubigeoService.provinciaLista(idDepartamento);
                if (list.size() > 0) {
                    for (Provincia provincia : list) {
                        listaProvincia.add(new SelectItem(provincia.getIdProvincia(), provincia.getDescripcion()));
                    }
                }
            }
        } catch (Exception e) {
            log.error("ERROR - comboProvincia()" + e);
        }
    }

    public void comboDistritoRecurrente() {
        try {
            listaDistrito = new ArrayList<>();
            String idProvincia = reporteSidExpediente.getIdProvinciaRecurrente();
            String idDepartamento = reporteSidExpediente.getIdDepartamentoRecurrente();
            if (StringUtils.equals(idProvincia, "0") || StringUtils.equals(idDepartamento, "0")) {
                listaDistrito.clear();
            } else {
                Distrito d = new Distrito();
                d.setIdDepartamento(idDepartamento);
                d.setIdProvincia(idProvincia);
                List<Distrito> list = ubigeoService.distritoLista(d);
                if (list.size() > 0) {
                    for (Distrito distrito : list) {
                        listaDistrito.add(new SelectItem(distrito.getIdDistrito(), distrito.getDescripcion()));
                    }
                }
            }
        } catch (Exception e) {
            log.error("ERROR - comboDistrito()" + e);
        }
    }

    public void comboProvinciaAfectado() {
        try {
            listaProvincia = new ArrayList<>();
            listaDistrito = new ArrayList<>();
            String idDepartamento = reporteSidExpediente.getIdDepartamentoAfectado();
            if (StringUtils.equals(idDepartamento, "0")) {
                listaProvincia.clear();
            } else {
                List<Provincia> list = ubigeoService.provinciaLista(idDepartamento);
                if (list.size() > 0) {
                    for (Provincia provincia : list) {
                        listaProvincia.add(new SelectItem(provincia.getIdProvincia(), provincia.getDescripcion()));
                    }
                }
            }
        } catch (Exception e) {
            log.error("ERROR - comboProvincia()" + e);
        }
    }

    public void comboDistritoAfectado() {
        try {
            listaDistrito = new ArrayList<>();
            String idProvincia = reporteSidExpediente.getIdProvinciaAfectado();
            String idDepartamento = reporteSidExpediente.getIdDepartamentoAfectado();
            if (StringUtils.equals(idProvincia, "0") || StringUtils.equals(idDepartamento, "0")) {
                listaDistrito.clear();
            } else {
                Distrito d = new Distrito();
                d.setIdDepartamento(idDepartamento);
                d.setIdProvincia(idProvincia);
                List<Distrito> list = ubigeoService.distritoLista(d);
                if (list.size() > 0) {
                    for (Distrito distrito : list) {
                        listaDistrito.add(new SelectItem(distrito.getIdDistrito(), distrito.getDescripcion()));
                    }
                }
            }
        } catch (Exception e) {
            log.error("ERROR - comboDistrito()" + e);
        }
    }

    public void setearEntidad(Entidad e) {
        setEntidadReporte(e);
        reporteSidExpediente.setCodigoEntidad(e.getId());
        reporteSidExpediente.setNombreEntidad(e.getNombre());
    }

    public void limpiarComisionado() {
        try {
            Usuario u = new Usuario();
            u.setCodigoOD(usuarioSession.getCodigoOD());
            listaUsuarioOD = null;
            listaUsuarioOD = usuarioService.listaUsuariosPorOD(u);
        } catch (Exception e) {
            log.error("ERROR - limpiarModalAsignar()" + e);
        }
    }

    public void seleccionarUsuarioComisionado() {
        if (StringUtils.isNotBlank(usuarioPalabra)) {
            Usuario u = usuarioService.buscarUsuarioOne(usuarioPalabra);
            reporteSidExpediente.setUsuarioCodigo(u.getCodigo());
            reporteSidExpediente.setUsuarioNombre(u.getNombre() + " " + u.getApellidoPaterno() + " " + u.getApellidoMaterno());
        }
    }

    public ReporteSidExpediente getReporteSidExpediente() {
        return reporteSidExpediente;
    }

    public void setReporteSidExpediente(ReporteSidExpediente reporteSidExpediente) {
        this.reporteSidExpediente = reporteSidExpediente;
    }

    public List<ReporteSidExpediente> getListaReporteSidExpediente() {
        return listaReporteSidExpediente;
    }

    public void setListaReporteSidExpediente(List<ReporteSidExpediente> listaReporteSidExpediente) {
        this.listaReporteSidExpediente = listaReporteSidExpediente;
    }

    public Long getNroPaginaEntidad() {
        return nroPaginaEntidad;
    }

    public void setNroPaginaEntidad(Long nroPaginaEntidad) {
        this.nroPaginaEntidad = nroPaginaEntidad;
    }

    public Entidad getEntidadReporte() {
        return entidadReporte;
    }

    public void setEntidadReporte(Entidad entidadReporte) {
        this.entidadReporte = entidadReporte;
    }

    public List<Entidad> getEntidadPopover() {
        return entidadPopover;
    }

    public void setEntidadPopover(List<Entidad> entidadPopover) {
        this.entidadPopover = entidadPopover;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public List<Usuario> getListaUsuarioOD() {
        return listaUsuarioOD;
    }

    public void setListaUsuarioOD(List<Usuario> listaUsuarioOD) {
        this.listaUsuarioOD = listaUsuarioOD;
    }

    public List<SelectItem> getListaProvincia() {
        return listaProvincia;
    }

    public void setListaProvincia(List<SelectItem> listaProvincia) {
        this.listaProvincia = listaProvincia;
    }

    public List<SelectItem> getListaDistrito() {
        return listaDistrito;
    }

    public void setListaDistrito(List<SelectItem> listaDistrito) {
        this.listaDistrito = listaDistrito;
    }

    public Usuario getUsuarioSession() {
        return usuarioSession;
    }

    public void setUsuarioSession(Usuario usuarioSession) {
        this.usuarioSession = usuarioSession;
    }

    public String getUsuarioPalabra() {
        return usuarioPalabra;
    }

    public void setUsuarioPalabra(String usuarioPalabra) {
        this.usuarioPalabra = usuarioPalabra;
    }

    public List<SelectItem> getListaClasificacionPrimerLevel() {
        List<ExpedienteClasificacion> listaClasi = expedienteClasificacionService.listaExpedienteClasificacion(new ExpedienteClasificacion(0, 1, "ACT"));
        List<SelectItem> listaClasificacion = new ArrayList<>();
        for (ExpedienteClasificacion ec : listaClasi) {
            listaClasificacion.add(new SelectItem(ec.getId(), ec.getNombre()));
        }
        listaClasificacionPrimerLevel = listaClasificacion;
        return listaClasificacionPrimerLevel;
    }

    public void limpiarModalBusquedaClasificacion() {
        try {
            expedienteClasificacionBusqueda = new ExpedienteClasificacion();
            listaExpedienteNivelModal = new ArrayList<>();
            nroPaginaModal = 1;
            tipoReporte = 2;
        } catch (Exception e) {
            log.error("ERROR - limpiarModalBusquedaClasificacion()" + e);
        }
    }

    public void seleccionarNivel(ExpedienteNivel en) {
        try {
            expedienteNivel.setId(en.getId());
            if (en.getIdPrimerNivel() != null && en.getIdPrimerNivel() != 0) {
                cargarNivelesClasificacion(en.getIdPrimerNivel(), 2);
                reporteSidExpediente.setIdPrimerNivel(en.getIdPrimerNivel().toString());
            }
            if (en.getIdSegundoNivel() != null && en.getIdSegundoNivel() != 0) {
                cargarNivelesClasificacion(en.getIdSegundoNivel(), 3);
                reporteSidExpediente.setIdSegundoNivel(en.getIdSegundoNivel().toString());
            }
            if (en.getIdTercerNivel() != null && en.getIdTercerNivel() != 0) {
                cargarNivelesClasificacion(en.getIdTercerNivel(), 4);
                reporteSidExpediente.setIdTercerNivel(en.getIdTercerNivel().toString());
            }
            if (en.getIdCuartoNivel() != null && en.getIdCuartoNivel() != 0) {
                cargarNivelesClasificacion(en.getIdCuartoNivel(), 5);
                reporteSidExpediente.setIdCuartoNivel(en.getIdCuartoNivel().toString());
            }
            if (en.getIdQuintoNivel() != null && en.getIdQuintoNivel() != 0) {
                cargarNivelesClasificacion(en.getIdQuintoNivel(), 6);
                reporteSidExpediente.setIdQuintoNivel(en.getIdQuintoNivel().toString());
            }
            if (en.getIdSextoNivel() != null && en.getIdSextoNivel() != 0) {
                reporteSidExpediente.setIdSextoNivel(en.getIdSextoNivel().toString());
            }
        } catch (Exception e) {
            log.error("ERROR - editarNivel()" + e);
        }
    }

    public boolean buscarClasificacion(Integer pagina) {
        try {
            List<ExpedienteNivel> ens = new ArrayList<>();
            if (pagina > 0) {
                int paginado = ConstantesUtil.PAGINADO_10;
                Integer ini = paginado * (pagina - 1) + 1;
                Integer fin = paginado * pagina;
                if (pagina == 0) {
                    ini = 1;
                    fin = 10;
                }
                expedienteClasificacionBusqueda.setIni(ini);
                expedienteClasificacionBusqueda.setFin(fin);

                List<ExpedienteClasificacion> list = expedienteClasificacionService.expedienteClasificacionBusqueda(expedienteClasificacionBusqueda);
                for (ExpedienteClasificacion ec : list) {
                    ExpedienteNivel en = new ExpedienteNivel();
                    clasificarNivel(ec, en);
                    Integer idPadre = ec.getPadre();

                    while (idPadre != 0) {
                        ExpedienteClasificacion ec1 = expedienteClasificacionService.expedienteClasificacionOne(idPadre);
                        idPadre = ec1.getPadre();
                        clasificarNivel(ec1, en);
                    }
                    ens.add(en);
                }
                if (list.size() > 0) {
                    listaExpedienteNivelModal = ens;
                    nroPaginaModal = pagina;
                } else {
                    if (expedienteClasificacionBusqueda.getIni() == 1) {
                        listaExpedienteNivelModal = null;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            log.error("ERROR - buscarClasificacion()" + e);
        }
        return false;
    }

    private void clasificarNivel(ExpedienteClasificacion ec2, ExpedienteNivel en) {
        try {
            if (ec2.getGrupo() == 1) {
                en.setIdPrimerNivel(ec2.getId());
                en.setPrimerNivel(ec2.getNombre());
            }
            if (ec2.getGrupo() == 2) {
                en.setIdSegundoNivel(ec2.getId());
                en.setSegundoNivel(ec2.getNombre());
            }
            if (ec2.getGrupo() == 3) {
                en.setIdTercerNivel(ec2.getId());
                en.setTercerNivel(ec2.getNombre());
            }
            if (ec2.getGrupo() == 4) {
                en.setIdCuartoNivel(ec2.getId());
                en.setCuartoNivel(ec2.getNombre());
            }
            if (ec2.getGrupo() == 5) {
                en.setIdQuintoNivel(ec2.getId());
                en.setQuintoNivel(ec2.getNombre());
            }
            if (ec2.getGrupo() == 6) {
                en.setIdSextoNivel(ec2.getId());
                en.setSextoNivel(ec2.getNombre());
            }
        } catch (Exception e) {
            log.error("ERROR - clasificarNivel()" + e);
        }
    }

    public void cargarNivelesClasificacion(Integer idPadre, Integer grupo) {
        if (idPadre != null) {
            try {
                List<ExpedienteClasificacion> listaClasi = expedienteClasificacionService.listaExpedienteClasificacion(new ExpedienteClasificacion(idPadre, grupo, "ACT"));
                if (grupo == 2) {
                    listaClasificacionSegundoLevel = new ArrayList<>();
                    listaClasificacionTercerLevel = new ArrayList<>();
                    listaClasificacionCuartoLevel = new ArrayList<>();
                    listaClasificacionQuintoLevel = new ArrayList<>();
                    listaClasificacionSextoLevel = new ArrayList<>();
                    for (ExpedienteClasificacion ec : listaClasi) {
                        listaClasificacionSegundoLevel.add(new SelectItem(ec.getId(), ec.getNombre()));
                    }
                }
                if (grupo == 3) {
                    listaClasificacionTercerLevel = new ArrayList<>();
                    listaClasificacionCuartoLevel = new ArrayList<>();
                    listaClasificacionQuintoLevel = new ArrayList<>();
                    listaClasificacionSextoLevel = new ArrayList<>();
                    for (ExpedienteClasificacion ec : listaClasi) {
                        listaClasificacionTercerLevel.add(new SelectItem(ec.getId(), ec.getNombre()));
                    }
                }
                if (grupo == 4) {
                    listaClasificacionCuartoLevel = new ArrayList<>();
                    listaClasificacionQuintoLevel = new ArrayList<>();
                    listaClasificacionSextoLevel = new ArrayList<>();
                    for (ExpedienteClasificacion ec : listaClasi) {
                        listaClasificacionCuartoLevel.add(new SelectItem(ec.getId(), ec.getNombre()));
                    }
                }
                if (grupo == 5) {
                    listaClasificacionQuintoLevel = new ArrayList<>();
                    listaClasificacionSextoLevel = new ArrayList<>();
                    for (ExpedienteClasificacion ec : listaClasi) {
                        listaClasificacionQuintoLevel.add(new SelectItem(ec.getId(), ec.getNombre()));
                    }
                }
                if (grupo == 6) {
                    listaClasificacionSextoLevel = new ArrayList<>();
                    for (ExpedienteClasificacion ec : listaClasi) {
                        listaClasificacionSextoLevel.add(new SelectItem(ec.getId(), ec.getNombre()));
                    }
                }
            } catch (Exception e) {
                log.error("ERROR - cargarNivelesClasificacion()" + e);
            }
        }
    }

    public void setListaClasificacionPrimerLevel(List<SelectItem> listaClasificacionPrimerLevel) {
        this.listaClasificacionPrimerLevel = listaClasificacionPrimerLevel;
    }

    public List<SelectItem> getListaClasificacionSegundoLevel() {
        return listaClasificacionSegundoLevel;
    }

    public void setListaClasificacionSegundoLevel(List<SelectItem> listaClasificacionSegundoLevel) {
        this.listaClasificacionSegundoLevel = listaClasificacionSegundoLevel;
    }

    public List<SelectItem> getListaClasificacionTercerLevel() {
        return listaClasificacionTercerLevel;
    }

    public void setListaClasificacionTercerLevel(List<SelectItem> listaClasificacionTercerLevel) {
        this.listaClasificacionTercerLevel = listaClasificacionTercerLevel;
    }

    public List<SelectItem> getListaClasificacionCuartoLevel() {
        return listaClasificacionCuartoLevel;
    }

    public void setListaClasificacionCuartoLevel(List<SelectItem> listaClasificacionCuartoLevel) {
        this.listaClasificacionCuartoLevel = listaClasificacionCuartoLevel;
    }

    public List<SelectItem> getListaClasificacionQuintoLevel() {
        return listaClasificacionQuintoLevel;
    }

    public void setListaClasificacionQuintoLevel(List<SelectItem> listaClasificacionQuintoLevel) {
        this.listaClasificacionQuintoLevel = listaClasificacionQuintoLevel;
    }

    public List<SelectItem> getListaClasificacionSextoLevel() {
        return listaClasificacionSextoLevel;
    }

    public void setListaClasificacionSextoLevel(List<SelectItem> listaClasificacionSextoLevel) {
        this.listaClasificacionSextoLevel = listaClasificacionSextoLevel;
    }

    public ExpedienteNivel getExpedienteNivel() {
        return expedienteNivel;
    }

    public void setExpedienteNivel(ExpedienteNivel expedienteNivel) {
        this.expedienteNivel = expedienteNivel;
    }

    public ExpedienteClasificacion getExpedienteClasificacionBusqueda() {
        return expedienteClasificacionBusqueda;
    }

    public void setExpedienteClasificacionBusqueda(ExpedienteClasificacion expedienteClasificacionBusqueda) {
        this.expedienteClasificacionBusqueda = expedienteClasificacionBusqueda;
    }

    public List<ExpedienteNivel> getListaExpedienteNivelModal() {
        return listaExpedienteNivelModal;
    }

    public void setListaExpedienteNivelModal(List<ExpedienteNivel> listaExpedienteNivelModal) {
        this.listaExpedienteNivelModal = listaExpedienteNivelModal;
    }

    public Integer getNroPaginaModal() {
        return nroPaginaModal;
    }

    public void setNroPaginaModal(Integer nroPaginaModal) {
        this.nroPaginaModal = nroPaginaModal;
    }

    public String getClasificacionPalabra() {
        return clasificacionPalabra;
    }

    public void setClasificacionPalabra(String clasificacionPalabra) {
        this.clasificacionPalabra = clasificacionPalabra;
    }

    public Integer getPagina() {
        return pagina;
    }

    public void setPagina(Integer pagina) {
        this.pagina = pagina;
    }

    public Integer getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(Integer tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public List<ReporteSidExpediente> getListaReporteSidGestion() {
        return listaReporteSidGestion;
    }

    public void setListaReporteSidGestion(List<ReporteSidExpediente> listaReporteSidGestion) {
        this.listaReporteSidGestion = listaReporteSidGestion;
    }

    public List<ReporteSidConteo> getListaReporteSidEntidad() {
        return listaReporteSidEntidad;
    }

    public void setListaReporteSidEntidad(List<ReporteSidConteo> listaReporteSidEntidad) {
        this.listaReporteSidEntidad = listaReporteSidEntidad;
    }

    public List<ReporteSidConteo> getListaReporteSidClasificacion() {
        return listaReporteSidClasificacion;
    }

    public void setListaReporteSidClasificacion(List<ReporteSidConteo> listaReporteSidClasificacion) {
        this.listaReporteSidClasificacion = listaReporteSidClasificacion;
    }

    public List<ReporteSidConteo> getListaReporteSidComisionado() {
        return listaReporteSidComisionado;
    }

    public void setListaReporteSidComisionado(List<ReporteSidConteo> listaReporteSidComisionado) {
        this.listaReporteSidComisionado = listaReporteSidComisionado;
    }

    public List<ReporteSidConteo> getListaReporteSidDependencia() {
        return listaReporteSidDependencia;
    }

    public void setListaReporteSidDependencia(List<ReporteSidConteo> listaReporteSidDependencia) {
        this.listaReporteSidDependencia = listaReporteSidDependencia;
    }

    public List<ReporteSidExpediente> getListaReporteSidGestionInterna() {
        return listaReporteSidGestionInterna;
    }

    public void setListaReporteSidGestionInterna(List<ReporteSidExpediente> listaReporteSidGestionInterna) {
        this.listaReporteSidGestionInterna = listaReporteSidGestionInterna;
    }
    
    

}
