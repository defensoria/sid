/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.entity;

import java.io.Serializable;

/**
 *
 * @author carlos
 */
public class ExpedienteEntidad implements Serializable{
    
    private Expediente expediente;
    
    private Entidad entidad;

    public ExpedienteEntidad() {
    }

    public ExpedienteEntidad(Expediente expediente, Entidad entidad) {
        this.expediente = expediente;
        this.entidad = entidad;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    public Entidad getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }
    
}
