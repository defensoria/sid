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
public class ExpedienteConsulta implements Serializable{
    
    private Long id;
    
    private Long idExpediente;
    
    private Integer idAdjuntiaDefensorial;
    
    private String detalle;
    
    private String estado;
    
    private String numeroExpediente;
    
    private Integer etapa;
    
    private String nombreUsuario;
    
    private String codigoUsuario;
    
    private String aprueba;
    
    private String ruta;
    
    private String codigo;
    
    private Integer tipo;
    
    private String codigoUsuarioReasignado;
    
    private String nombreAdjuntia;
    
    private Date fecha;
    
    private String codigoUsuarioRetorno;
    
    private Long idPadre;
    
    private String respuesta;
    
    private String rutaRespuesta;

    public ExpedienteConsulta() {
    }

    public ExpedienteConsulta(Integer etapa, Long idPadre) {
        this.etapa = etapa;
        this.idPadre = idPadre;
    }

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

    public Integer getIdAdjuntiaDefensorial() {
        return idAdjuntiaDefensorial;
    }

    public void setIdAdjuntiaDefensorial(Integer idAdjuntiaDefensorial) {
        this.idAdjuntiaDefensorial = idAdjuntiaDefensorial;
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

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getNombreAdjuntia() {
        return nombreAdjuntia;
    }

    public void setNombreAdjuntia(String nombreAdjuntia) {
        this.nombreAdjuntia = nombreAdjuntia;
    }

    public String getCodigoUsuarioReasignado() {
        return codigoUsuarioReasignado;
    }

    public void setCodigoUsuarioReasignado(String codigoUsuarioReasignado) {
        this.codigoUsuarioReasignado = codigoUsuarioReasignado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCodigoUsuarioRetorno() {
        return codigoUsuarioRetorno;
    }

    public void setCodigoUsuarioRetorno(String codigoUsuarioRetorno) {
        this.codigoUsuarioRetorno = codigoUsuarioRetorno;
    }

    public Long getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Long idPadre) {
        this.idPadre = idPadre;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getRutaRespuesta() {
        return rutaRespuesta;
    }

    public void setRutaRespuesta(String rutaRespuesta) {
        this.rutaRespuesta = rutaRespuesta;
    }

}


