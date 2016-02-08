/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.bandeja.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author carlos
 */
public class Bandeja implements Serializable{
    
    private Long id;
    
    private String remitente;
    
    private String titulo;
    
    private Date fechaEnvio;
    
    private String estado;
    
    private Integer tipo;
    
    private String destinatario;
    
    private Long codigoTipo;
    
    private String nombreRemitente;
    
    private String detalleTipo;
    
    private String colorTipo;
    
    private String motivo;
    
    private String numeroExpediente;
    
    private Long idExpediente;
    
    private Long idAccion;
    
    private String tituloMensaje;
    
    private String codigoConsulta;
    
    private String tipoMensaje;
    
    private String activo;
    
    /**agregados*/

    private Boolean indicador;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Long getCodigoTipo() {
        return codigoTipo;
    }

    public void setCodigoTipo(Long codigoTipo) {
        this.codigoTipo = codigoTipo;
    }

    public String getNombreRemitente() {
        return nombreRemitente;
    }

    public void setNombreRemitente(String nombreRemitente) {
        this.nombreRemitente = nombreRemitente;
    }

    public String getDetalleTipo() {
        return detalleTipo;
    }

    public void setDetalleTipo(String detalleTipo) {
        this.detalleTipo = detalleTipo;
    }

    public String getColorTipo() {
        return colorTipo;
    }

    public void setColorTipo(String colorTipo) {
        this.colorTipo = colorTipo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public Long getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(Long idExpediente) {
        this.idExpediente = idExpediente;
    }

    public Long getIdAccion() {
        return idAccion;
    }

    public void setIdAccion(Long idAccion) {
        this.idAccion = idAccion;
    }

    public String getTituloMensaje() {
        return tituloMensaje;
    }

    public void setTituloMensaje(String tituloMensaje) {
        this.tituloMensaje = tituloMensaje;
    }

    public String getCodigoConsulta() {
        return codigoConsulta;
    }

    public void setCodigoConsulta(String codigoConsulta) {
        this.codigoConsulta = codigoConsulta;
    }

    public String getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public Boolean getIndicador() {
        return indicador;
    }

    public void setIndicador(Boolean indicador) {
        this.indicador = indicador;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }
    
}
