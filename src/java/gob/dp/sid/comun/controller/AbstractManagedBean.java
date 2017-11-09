/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.comun.controller;

import gob.dp.sid.comun.ConstantesUtil;
import gob.dp.sid.comun.MessagesUtil;
import gob.dp.sid.comun.StringUtil;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author carlos
 */
public abstract class AbstractManagedBean implements Serializable{
    
    protected MessagesUtil msg;
    
    FacesContext context = FacesContext.getCurrentInstance();
    
    public ConstantesUtil constantesUtil = new ConstantesUtil();
    
    protected StringUtil stringUtil;
    
    //protected String separador = "/"; //linux
    protected String separador = "\\"; //windows

    protected static String FILE_SYSTEM = ConstantesUtil.FILE_DONWLOAD+"/";
    //protected static String FILE_SYSTEM="/home/glassfish/glassfish4/glassfish/domains/domain1/docroot/filesystem/";//linux
    //protected static String FILE_SYSTEM = "C:/server/glassfish-4.0/glassfish4/glassfish/domains/domain1/docroot/filesystem/";//windows

    public String hostAddress() {
        try {//windows
            return "http://" + InetAddress.getLocalHost().getHostAddress().concat(":8080/filesystem/");
            //linux
            //return "http://" + InetAddress.getLocalHost().getHostAddress().concat("/filesystem/");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String retornapath(String cadena) {
        int cont = 0;
        for (int i = 0; i < cadena.length(); i++) {
            if (separador.equals(cadena.substring(i, i + 1))) {
                cont = i;
            }
        }
        return cadena.substring(0, cont);
    }

    public String retornaRutaPath() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        String path = request.getPathTranslated();
        String ruta = retornapath(retornapath(path));
        return ruta;
    }

    public AbstractManagedBean() {
        msg = new MessagesUtil();
        stringUtil = new StringUtil();
    }
    
    
    
}
