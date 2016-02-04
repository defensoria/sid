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
public class ExpedienteGestion implements Serializable{
    
    private Long id;
    
    private String codigoGestion;
    
    private String tipo;
    
    private String nombreParametroTipo;
    
    private Date fecha;
    
    private Date fechaRecepcion;
    
    private String descripcion;
    
    private Integer idEntidad;
    
    private String nombreEntidad;
    
    private String nombre;
    
    private String apellidoPaterno;
    
    private String apellidoMaterno;
    
    private String cargo;
    
    private Boolean indEntidadQuejada;
    
    private String usuarioRegistro;
    
    private String usuarioModificacion;
    
    private Date fechaRegistro;
    
    private Date fechaModificacion;
    
    private String codigoONP;
    
    /**SEGUIMIENTO*/
    
    private Date inicioDefinitivo;
    
    private Date finDefinitivo;
    
    private Date ultimaEjecucion;
    
    private String estado;

    private Boolean indicadorRecomendacion;
    
    private String documentoRespuesta;
    
    private String tipoAcogimiento;
    
    private String ruta1;
    
    private String ruta2;
    
    private String documentoGestion;
    
    private String documentoGestionDetalle;
    
    /**RESPUESTA**/
    private String nota;
    
    private String detalleRespuesta;
    
    private String observacionRespuesta;
    
    private String respuesta;
    
    private String tipoCalidad;
    
    private Date fechaRespuesta;
    
    /*AGREGADOS*/
    private Integer idEtapa;
    
    private String detalleTipo;
    
    private String fechaString;
    
    private String destino;
    
    private String tipoAccionString;
    
    private String nro;
    
    private String numeroExpediente;
    
    private String validaExpediente;
    
    private String existeGestion;
    
    private Integer verEtapa;
    
    private Long idExpediente;
    
    private String codigoGestionOrigen;
    
    private String codigoGestionRespuesta;
    
    private String destinoONP;
    
    private Date fechaONP;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(Integer idEntidad) {
        this.idEntidad = idEntidad;
    }

    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Date getInicioDefinitivo() {
        return inicioDefinitivo;
    }

    public void setInicioDefinitivo(Date inicioDefinitivo) {
        this.inicioDefinitivo = inicioDefinitivo;
    }

    public Date getFinDefinitivo() {
        return finDefinitivo;
    }

    public void setFinDefinitivo(Date finDefinitivo) {
        this.finDefinitivo = finDefinitivo;
    }

    public Date getUltimaEjecucion() {
        return ultimaEjecucion;
    }

    public void setUltimaEjecucion(Date ultimaEjecucion) {
        this.ultimaEjecucion = ultimaEjecucion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getIndEntidadQuejada() {
        return indEntidadQuejada;
    }

    public void setIndEntidadQuejada(Boolean indEntidadQuejada) {
        this.indEntidadQuejada = indEntidadQuejada;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getDetalleRespuesta() {
        return detalleRespuesta;
    }

    public void setDetalleRespuesta(String detalleRespuesta) {
        this.detalleRespuesta = detalleRespuesta;
    }

    public String getObservacionRespuesta() {
        return observacionRespuesta;
    }

    public void setObservacionRespuesta(String observacionRespuesta) {
        this.observacionRespuesta = observacionRespuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getTipoCalidad() {
        return tipoCalidad;
    }

    public void setTipoCalidad(String tipoCalidad) {
        this.tipoCalidad = tipoCalidad;
    }

    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public String getCodigoGestion() {
        return codigoGestion;
    }

    public void setCodigoGestion(String codigoGestion) {
        this.codigoGestion = codigoGestion;
    }

    public String getNombreParametroTipo() {
        return nombreParametroTipo;
    }

    public void setNombreParametroTipo(String nombreParametroTipo) {
        this.nombreParametroTipo = nombreParametroTipo;
    }

    public Integer getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(Integer idEtapa) {
        this.idEtapa = idEtapa;
    }

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getDetalleTipo() {
        return detalleTipo;
    }

    public void setDetalleTipo(String detalleTipo) {
        this.detalleTipo = detalleTipo;
    }

    public Boolean getIndicadorRecomendacion() {
        return indicadorRecomendacion;
    }

    public void setIndicadorRecomendacion(Boolean indicadorRecomendacion) {
        this.indicadorRecomendacion = indicadorRecomendacion;
    }

    public String getDocumentoRespuesta() {
        return documentoRespuesta;
    }

    public void setDocumentoRespuesta(String documentoRespuesta) {
        this.documentoRespuesta = documentoRespuesta;
    }

    public String getTipoAcogimiento() {
        return tipoAcogimiento;
    }

    public void setTipoAcogimiento(String tipoAcogimiento) {
        this.tipoAcogimiento = tipoAcogimiento;
    }

    public String getRuta1() {
        return ruta1;
    }

    public void setRuta1(String ruta1) {
        this.ruta1 = ruta1;
    }

    public String getRuta2() {
        return ruta2;
    }

    public void setRuta2(String ruta2) {
        this.ruta2 = ruta2;
    }

    public String getDocumentoGestion() {
        return documentoGestion;
    }

    public void setDocumentoGestion(String documentoGestion) {
        this.documentoGestion = documentoGestion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public String getFechaString() {
        return fechaString;
    }

    public void setFechaString(String fechaString) {
        this.fechaString = fechaString;
    }

    public String getTipoAccionString() {
        return tipoAccionString;
    }

    public void setTipoAccionString(String tipoAccionString) {
        this.tipoAccionString = tipoAccionString;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public String getCodigoONP() {
        return codigoONP;
    }

    public void setCodigoONP(String codigoONP) {
        this.codigoONP = codigoONP;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public String getValidaExpediente() {
        return validaExpediente;
    }

    public void setValidaExpediente(String validaExpediente) {
        this.validaExpediente = validaExpediente;
    }

    public String getExisteGestion() {
        return existeGestion;
    }

    public void setExisteGestion(String existeGestion) {
        this.existeGestion = existeGestion;
    }

    public Integer getVerEtapa() {
        return verEtapa;
    }

    public void setVerEtapa(Integer verEtapa) {
        this.verEtapa = verEtapa;
    }

    public Long getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(Long idExpediente) {
        this.idExpediente = idExpediente;
    }

    public String getCodigoGestionOrigen() {
        return codigoGestionOrigen;
    }

    public void setCodigoGestionOrigen(String codigoGestionOrigen) {
        this.codigoGestionOrigen = codigoGestionOrigen;
    }

    public String getDocumentoGestionDetalle() {
        return documentoGestionDetalle;
    }

    public void setDocumentoGestionDetalle(String documentoGestionDetalle) {
        this.documentoGestionDetalle = documentoGestionDetalle;
    }

    public String getCodigoGestionRespuesta() {
        return codigoGestionRespuesta;
    }

    public void setCodigoGestionRespuesta(String codigoGestionRespuesta) {
        this.codigoGestionRespuesta = codigoGestionRespuesta;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDestinoONP() {
        return destinoONP;
    }

    public void setDestinoONP(String destinoONP) {
        this.destinoONP = destinoONP;
    }

    public Date getFechaONP() {
        return fechaONP;
    }

    public void setFechaONP(Date fechaONP) {
        this.fechaONP = fechaONP;
    }
    
}