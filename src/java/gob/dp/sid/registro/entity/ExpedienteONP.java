/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author carlos
 */
public class ExpedienteONP implements Serializable{
    
    private Long id;
    
    private String numeroExpediente;
    
    private String codigoExpedienteONP;
    
    private String nombreCausante;
    
    private String tipoRegimen;
    
    private String tipoSubOrigen;
    
    private String tipoSolicitud;
    
    private Boolean indiceEnvidadoONP;
    
    private Boolean indiceConcluidoONP;
    
    private Date fechaRegistro;
    
    private Date fechaONP;
    
    private String observacion;
    
    private String motivo;
    
    private String documento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoExpedienteONP() {
        return codigoExpedienteONP;
    }

    public void setCodigoExpedienteONP(String codigoExpedienteONP) {
        this.codigoExpedienteONP = codigoExpedienteONP;
    }

    public String getNombreCausante() {
        return nombreCausante;
    }

    public void setNombreCausante(String nombreCausante) {
        this.nombreCausante = nombreCausante;
    }

    public String getTipoRegimen() {
        return tipoRegimen;
    }

    public void setTipoRegimen(String tipoRegimen) {
        this.tipoRegimen = tipoRegimen;
    }

    public String getTipoSubOrigen() {
        return tipoSubOrigen;
    }

    public void setTipoSubOrigen(String tipoSubOrigen) {
        this.tipoSubOrigen = tipoSubOrigen;
    }

    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public Boolean getIndiceEnvidadoONP() {
        return indiceEnvidadoONP;
    }

    public void setIndiceEnvidadoONP(Boolean indiceEnvidadoONP) {
        this.indiceEnvidadoONP = indiceEnvidadoONP;
    }

    public Boolean getIndiceConcluidoONP() {
        return indiceConcluidoONP;
    }

    public void setIndiceConcluidoONP(Boolean indiceConcluidoONP) {
        this.indiceConcluidoONP = indiceConcluidoONP;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaONP() {
        return fechaONP;
    }

    public void setFechaONP(Date fechaONP) {
        this.fechaONP = fechaONP;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
    
    
    
}
