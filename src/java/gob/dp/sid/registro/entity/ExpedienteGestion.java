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
    
    private String tipo;
    
    private Date fecha;
    
    private String descripcion;
    
    private String institucion;
    
    private String nombre;
    
    private String apellidoPaterno;
    
    private String apellidoMaterno;
    
    private Boolean indEntidadQuejada;
    
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
    
    /**RESPUESTA**/
    private String nota;
    
    private String detalleRespuesta;
    
    private String observacionRespuesta;
    
    private String respuesta;
    
    private String tipoCalidad;
    
    private Date fechaRespuesta;

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
    
}
