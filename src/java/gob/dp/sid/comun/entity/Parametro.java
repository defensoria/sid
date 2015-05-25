/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.sid.comun.entity;

import gob.dp.sid.comun.Auditoria;
import java.io.Serializable;

/**
 *
 * @author Administrador
 */
public class Parametro extends Auditoria implements Serializable
{
    private Integer codigoParametro;
    
    private String nombreParametro;
    
    private String valorParametro;
    
    private Parametro padreParametro;

    public Parametro() {
    }

    public Parametro(String nombreParametro, String valorParametro) {
        this.nombreParametro = nombreParametro;
        this.valorParametro = valorParametro;
    }
    
    public Integer getCodigoParametro() {
        return codigoParametro;
    }

    public void setCodigoParametro(Integer codigoParametro) {
        this.codigoParametro = codigoParametro;
    }

    public String getNombreParametro() {
        return nombreParametro;
    }

    public void setNombreParametro(String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }

    public Parametro getPadreParametro() {
        return padreParametro;
    }

    public void setPadreParametro(Parametro padreParametro) {
        this.padreParametro = padreParametro;
    }

    public String getValorParametro() {
        return valorParametro;
    }

    public void setValorParametro(String valorParametro) {
        this.valorParametro = valorParametro;
    }
    

}
