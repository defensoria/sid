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
    
    private String institucion;
    
    private String nombre;
    
    private String apellidoPaterno;
    
    private String apellidoMaterno;
    
    private String cargo;
    
    private Boolean indEntidadQuejada;
    
    private String usuarioRegistro;
    
    private String usuarioModificacion;
    
    private Date fechaRegistro;
    
    private Date fechaModificacion;
    
    /**SEGUIMIENTO*/
    
    private boolean indAlertar;
    
    private Date inicioSeguimiento;
    
    private Date finSeguimiento;
    
    private String tipoTiempoIni;
    
    private String tipoTiempoFin;
    
    private Integer numeroInicial;
    
    private Integer numeroFinal;
    
    private String indicadorAntesDespuesIni;
    
    private String indicadorAntesDespuesFin;
    
    private Date inicioDefinitivo;
    
    private Date finDefinitivo;
    
    private String indicadorLunesViernes;
    
    private Date ultimaEjecucion;
    
    private String indiceRepeticion;
    
    private String estado;
    
    private Boolean indicadorSeleccionHoraIni;
    
    private Boolean indicadorSeleccionHoraFin;
    
    private Boolean indicadorRecomendacion;
    
    private String documentoRespuesta;
    
    private String tipoAcogimiento;
    
    private String ruta1;
    
    private String ruta2;
    
    private String documentoGestion;
    
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

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
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

    public Date getInicioSeguimiento() {
        return inicioSeguimiento;
    }

    public void setInicioSeguimiento(Date inicioSeguimiento) {
        this.inicioSeguimiento = inicioSeguimiento;
    }

    public Date getFinSeguimiento() {
        return finSeguimiento;
    }

    public void setFinSeguimiento(Date finSeguimiento) {
        this.finSeguimiento = finSeguimiento;
    }

    public String getTipoTiempoIni() {
        return tipoTiempoIni;
    }

    public void setTipoTiempoIni(String tipoTiempoIni) {
        this.tipoTiempoIni = tipoTiempoIni;
    }

    public String getTipoTiempoFin() {
        return tipoTiempoFin;
    }

    public void setTipoTiempoFin(String tipoTiempoFin) {
        this.tipoTiempoFin = tipoTiempoFin;
    }

    public Integer getNumeroInicial() {
        return numeroInicial;
    }

    public void setNumeroInicial(Integer numeroInicial) {
        this.numeroInicial = numeroInicial;
    }

    public Integer getNumeroFinal() {
        return numeroFinal;
    }

    public void setNumeroFinal(Integer numeroFinal) {
        this.numeroFinal = numeroFinal;
    }

    public String getIndicadorAntesDespuesIni() {
        return indicadorAntesDespuesIni;
    }

    public void setIndicadorAntesDespuesIni(String indicadorAntesDespuesIni) {
        this.indicadorAntesDespuesIni = indicadorAntesDespuesIni;
    }

    public String getIndicadorAntesDespuesFin() {
        return indicadorAntesDespuesFin;
    }

    public void setIndicadorAntesDespuesFin(String indicadorAntesDespuesFin) {
        this.indicadorAntesDespuesFin = indicadorAntesDespuesFin;
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

    public String getIndicadorLunesViernes() {
        return indicadorLunesViernes;
    }

    public void setIndicadorLunesViernes(String indicadorLunesViernes) {
        this.indicadorLunesViernes = indicadorLunesViernes;
    }

    public Date getUltimaEjecucion() {
        return ultimaEjecucion;
    }

    public void setUltimaEjecucion(Date ultimaEjecucion) {
        this.ultimaEjecucion = ultimaEjecucion;
    }

    public String getIndiceRepeticion() {
        return indiceRepeticion;
    }

    public void setIndiceRepeticion(String indiceRepeticion) {
        this.indiceRepeticion = indiceRepeticion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Boolean getIndicadorSeleccionHoraIni() {
        return indicadorSeleccionHoraIni;
    }

    public void setIndicadorSeleccionHoraIni(Boolean indicadorSeleccionHoraIni) {
        this.indicadorSeleccionHoraIni = indicadorSeleccionHoraIni;
    }

    public Boolean getIndicadorSeleccionHoraFin() {
        return indicadorSeleccionHoraFin;
    }

    public void setIndicadorSeleccionHoraFin(Boolean indicadorSeleccionHoraFin) {
        this.indicadorSeleccionHoraFin = indicadorSeleccionHoraFin;
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

    public boolean isIndAlertar() {
        return indAlertar;
    }

    public void setIndAlertar(boolean indAlertar) {
        this.indAlertar = indAlertar;
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
    
}
