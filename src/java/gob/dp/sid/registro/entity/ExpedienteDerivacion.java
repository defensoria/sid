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
public class ExpedienteDerivacion implements Serializable{
    
    private Long id;
    
    private Long idExpediente;
    
    private Integer idOficinaDefensorial;
    
    private String detalle;
    
    private String estado;
    
    private String numeroExpediente;
    
    private Integer etapa;
    
    private String nombreUsuario;
    
    private String codigoUsuario;
    
    private String aprueba;
    
    private String codigoUsuarioDerivado;
    
    private Date fecha;
    
    private String ruta;

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

    public Integer getEtapa() {
        return etapa;
    }

    public void setEtapa(Integer etapa) {
        this.etapa = etapa;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getAprueba() {
        return aprueba;
    }

    public void setAprueba(String aprueba) {
        this.aprueba = aprueba;
    }

    public String getCodigoUsuarioDerivado() {
        return codigoUsuarioDerivado;
    }

    public void setCodigoUsuarioDerivado(String codigoUsuarioDerivado) {
        this.codigoUsuarioDerivado = codigoUsuarioDerivado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
}


