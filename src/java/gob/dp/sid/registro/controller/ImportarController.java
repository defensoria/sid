/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.controller;

import gob.dp.sid.comun.ConstantesUtil;
import gob.dp.sid.comun.controller.AbstractManagedBean;
import gob.dp.sid.registro.service.ExpedienteGestionService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
    
    
    @Autowired
    private ExpedienteGestionService expedienteGestionService;
    
    public String cargarPagina(){
        return "importarONP";
    }
    
    public void cargarExcel(){
        String ruta1 = uploadArchive(file1);
    }
    
    private void importar(File archivo){
        Integer i = 0;
        DefaultTableModel modeloT = new DefaultTableModel();
        List<Object[]> listaObjetos = new ArrayList<>();
        try {
            wb = WorkbookFactory.create(new FileInputStream(archivo));
            Sheet hoja = wb.getSheetAt(0);
            Iterator filaIterator = hoja.rowIterator();
            int indiceFila=-1;
            while (filaIterator.hasNext()) {                
                indiceFila++;
                Row fila = (Row) filaIterator.next();
                Iterator columnaIterator = fila.cellIterator();
                Object[] listaColumna = new Object[7];
                
                int indiceColumna=-1;
                while (columnaIterator.hasNext()) {                    
                    indiceColumna++;
                    Cell celda = (Cell) columnaIterator.next();
                    if(indiceFila==0){
                        modeloT.addColumn(celda.getStringCellValue());
                    }else{
                        if(celda!=null){
                            switch(celda.getCellType()){
                                case Cell.CELL_TYPE_NUMERIC:
                                    listaColumna[indiceColumna]= (int)Math.round(celda.getNumericCellValue());
                                    break;
                                case Cell.CELL_TYPE_STRING:
                                    listaColumna[indiceColumna]= celda.getStringCellValue();
                                    break;
                                case Cell.CELL_TYPE_BOOLEAN:
                                    listaColumna[indiceColumna]= celda.getBooleanCellValue();
                                    break;
                                default:
                                    listaColumna[indiceColumna]=null;
                                    break;
                            }
                        }
                    }
                }
                if(indiceFila!=0)listaObjetos.add(listaColumna);
                
                
            }
            cargarGestiones(listaObjetos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void cargarGestiones(List<Object[]> lista){
        for(Object[] os : lista){
            System.out.println(os[0]);
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


    
}
