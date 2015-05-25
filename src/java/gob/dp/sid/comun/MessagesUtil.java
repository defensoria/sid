/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.comun;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author carlos
 */
public class MessagesUtil {
    
    public void messageAlert(String details, String title){
        if(title == null){
            title = "Alerta!";
        }
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, details, title);
        context.addMessage(null, message);
    }
    
    public void messageInfo(String details, String title){
        if(title == null){
            title = "Informe:";
        }
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, details, title);
        context.addMessage(null, message);
    }
    
    public void messageError(String details, String title){
        if(title == null){
            title = "Error:";
        }
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, details, title);
        context.addMessage(null, message);
    }
    
}
