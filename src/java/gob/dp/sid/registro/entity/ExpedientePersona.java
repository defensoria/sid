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
public class ExpedientePersona implements Serializable{
    
    private Expediente expediente;
    
    private Persona persona;
    
    private String tipo;
    
    private Boolean indicadorReserva;
    
    private String nombreCompleto;
    
    private String detalleCargo;
    
    private String nro;

    public ExpedientePersona(Expediente expediente, Persona persona) {
        this.expediente = expediente;
        this.persona = persona;
    }

    public ExpedientePersona() {
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getIndicadorReserva() {
        return indicadorReserva;
    }

    public void setIndicadorReserva(Boolean indicadorReserva) {
        this.indicadorReserva = indicadorReserva;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDetalleCargo() {
        return detalleCargo;
    }

    public void setDetalleCargo(String detalleCargo) {
        this.detalleCargo = detalleCargo;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }
    
    
    
}
