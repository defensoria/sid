/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.sid.comun;

import java.io.Serializable;

/**
 *
 * @author carlos
 */
public class ConstantesUtil implements Serializable{
    
    public static final int PAGINADO_5=5;
    
    public static final int PAGINADO_10=10;
    
    public static final int PAGINADO_15=15;
    
    public static final int PAGINADO_20=20;
    
    public static final int PAGINADO_30=30;
    
    //public String FILE_SYSTEM="C:/server/glassfish-4.0/glassfish4/glassfish/domains/domain1/docroot/filesystem/";
    /**LINUX*/
    //public String FILE_SYSTEM="/usr/local/glassfish4/glassfish/domains/domain1/docroot/filesystem/";
    
    //public static final String BASE_URL_REPORT = "C:\\recursos\\reportesSID\\";
    /**LINUX*/
    //public String BASE_URL_REPORT = "/usr/local/recursos/reportes/";
    
    //public static final String BASE_URL_IMAGEPATH = "C:\\recursos\\images\\";
    /**LINUX*/
    public String BASE_URL_IMAGEPATH = "/usr/local/recursos/images/";
    public String BASE_URL_PRODUCCION_IMAGE = "http://localhost/filesystem/";
    
    public static final String LISTA_VALOR_TODOS_CODIGO = "-1000";
    public static final String LISTA_VALOR_TODOS_NOMBRE = "TODOS";

    public static final String LISTA_VALOR_NINGUNO_CODIGO = "-2000";
    public static final String LISTA_VALOR_NINGUNO_NOMBRE = "NINGUNO";

    public static final String LISTA_VALOR_SELECCIONE_CODIGO = "-3000";
    public static final String LISTA_VALOR_SELECCIONE_NOMBRE = "SELECCIONE";
    
    //public static String FILE_DONWLOAD = "C:/filesystem";
    
    public static String FILE_DONWLOAD = "/srv/sid/filesystem";
    
}
