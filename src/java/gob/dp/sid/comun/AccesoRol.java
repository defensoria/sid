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
public class AccesoRol implements Serializable{
    
    private Boolean accesoDerivacionEnviar;
    
    private Boolean accesoDerivacionAprobar;

    public Boolean getAccesoDerivacionEnviar() {
        return accesoDerivacionEnviar;
    }

    public void setAccesoDerivacionEnviar(Boolean accesoDerivacionEnviar) {
        this.accesoDerivacionEnviar = accesoDerivacionEnviar;
    }

    public Boolean getAccesoDerivacionAprobar() {
        return accesoDerivacionAprobar;
    }

    public void setAccesoDerivacionAprobar(Boolean accesoDerivacionAprobar) {
        this.accesoDerivacionAprobar = accesoDerivacionAprobar;
    }
    
    
    
}
