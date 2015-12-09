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
public class ExpedienteDerivacion implements Serializable{
    
    private Long id;
    
    private Long idExpediente;
    
    private Integer idOficinaDefensorial;
    
    private String detalle;
    
    private String estado;
    
    private String numeroExpediente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(Long idExpediente) {
        this.idExpediente = idExpediente;
    }

    public Integer getIdOficinaDefensorial() {
        return idOficinaDefensorial;
    }

    public void setIdOficinaDefensorial(Integer idOficinaDefensorial) {
        this.idOficinaDefensorial = idOficinaDefensorial;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }
    
    
    
}


