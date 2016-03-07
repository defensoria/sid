/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.comun.controller;

import gob.dp.sid.comun.MessagesUtil;
import gob.dp.sid.comun.StringUtil;
import java.io.Serializable;
import javax.faces.context.FacesContext;

/**
 *
 * @author carlos
 */
public abstract class AbstractManagedBean implements Serializable{
    
    protected MessagesUtil msg;
    
    FacesContext context = FacesContext.getCurrentInstance();
    
    protected StringUtil stringUtil;

    public AbstractManagedBean() {
        msg = new MessagesUtil();
        stringUtil = new StringUtil();
    }
    
}
