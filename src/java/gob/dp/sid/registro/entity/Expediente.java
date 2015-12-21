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
public class Expediente implements Serializable{

    private Long id;
    
    private String numero;
    
    private String tipoClasificion;
    
    private String tipoIngreso;
    
    private String tipoTema;
    
    private String tipoSubtema;
    
    private Date fechaIngreso;
    
    private String sumilla;
    
    private String observacion;
    
    private Integer estadoCalificacion;
    
    private Integer estadoInvestigacion;
    
    private Integer estadoPersuacion;
    
    private Integer estadoSeguimiento;
    
    private Integer estadoGestion;
    
    private Boolean calificacion;
    
    private String usuarioRegistro;
    
    private String estado;
    
    private Integer version;
    
    private Date fechaRegistro;
    
    private String etiqueta;
    
    private String usuarioModificacion;
    
    private Date fechaModificacion;
    
    private String general;
    
    private Integer indDerivado;
    
    private Integer codigoOD;
    
    private Long consecutivo;
    
    private Integer idPrimerNivel;
    
    private Integer idSegundoNivel;
    
    private Integer idTercerNivel;
    
    private Integer idCuartoNivel;
    
    private Integer idQuintoNivel;
    
    private Integer idSextoNivel;
    /*agregados*/
    
    private String idEtiqueta;
    
    private String nombreEtiqueta;
    
    private String clasificacionTipoNombre;
    
    private String ingresoTipoNombre;
    
    private String ingresoTipoTema;
    
    private String ingresoTipoSubTema;
    
    private Integer ini;
    
    private Integer fin;
    
    private Integer count;
    
    private String mes;
    
    private String usuarioCompleto;
    
    private Integer idEtapa;
    
    private String etapaDetalle;
    
    private String indicadorEtapa;
    
    private String estadoDetalle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipoClasificion() {
        return tipoClasificion;
    }

    public void setTipoClasificion(String tipoClasificion) {
        this.tipoClasificion = tipoClasificion;
    }

    public String getTipoIngreso() {
        return tipoIngreso;
    }

    public void setTipoIngreso(String tipoIngreso) {
        this.tipoIngreso = tipoIngreso;
    }

    public String getTipoTema() {
        return tipoTema;
    }

    public void setTipoTema(String tipoTema) {
        this.tipoTema = tipoTema;
    }

    public String getTipoSubtema() {
        return tipoSubtema;
    }

    public void setTipoSubtema(String tipoSubtema) {
        this.tipoSubtema = tipoSubtema;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getSumilla() {
        return sumilla;
    }

    public void setSumilla(String sumilla) {
        this.sumilla = sumilla;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getEstadoCalificacion() {
        return estadoCalificacion;
    }

    public void setEstadoCalificacion(Integer estadoCalificacion) {
        this.estadoCalificacion = estadoCalificacion;
    }

    public Boolean getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Boolean calificacion) {
        this.calificacion = calificacion;
    }

    

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getIdEtiqueta() {
        return idEtiqueta;
    }

    public void setIdEtiqueta(String idEtiqueta) {
        this.idEtiqueta = idEtiqueta;
    }

    public String getNombreEtiqueta() {
        return nombreEtiqueta;
    }

    public void setNombreEtiqueta(String nombreEtiqueta) {
        this.nombreEtiqueta = nombreEtiqueta;
    }

    public String getClasificacionTipoNombre() {
        return clasificacionTipoNombre;
    }

    public void setClasificacionTipoNombre(String clasificacionTipoNombre) {
        this.clasificacionTipoNombre = clasificacionTipoNombre;
    }

    public String getIngresoTipoNombre() {
        return ingresoTipoNombre;
    }

    public void setIngresoTipoNombre(String ingresoTipoNombre) {
        this.ingresoTipoNombre = ingresoTipoNombre;
    }

    public String getIngresoTipoTema() {
        return ingresoTipoTema;
    }

    public void setIngresoTipoTema(String ingresoTipoTema) {
        this.ingresoTipoTema = ingresoTipoTema;
    }

    public String getIngresoTipoSubTema() {
        return ingresoTipoSubTema;
    }

    public void setIngresoTipoSubTema(String ingresoTipoSubTema) {
        this.ingresoTipoSubTema = ingresoTipoSubTema;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    public Integer getIni() {
        return ini;
    }

    public void setIni(Integer ini) {
        this.ini = ini;
    }

    public Integer getFin() {
        return fin;
    }

    public void setFin(Integer fin) {
        this.fin = fin;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getUsuarioCompleto() {
        return usuarioCompleto;
    }

    public void setUsuarioCompleto(String usuarioCompleto) {
        this.usuarioCompleto = usuarioCompleto;
    }

    public Integer getEstadoInvestigacion() {
        return estadoInvestigacion;
    }

    public void setEstadoInvestigacion(Integer estadoInvestigacion) {
        this.estadoInvestigacion = estadoInvestigacion;
    }

    public Integer getEstadoPersuacion() {
        return estadoPersuacion;
    }

    public void setEstadoPersuacion(Integer estadoPersuacion) {
        this.estadoPersuacion = estadoPersuacion;
    }

    public Integer getEstadoSeguimiento() {
        return estadoSeguimiento;
    }

    public void setEstadoSeguimiento(Integer estadoSeguimiento) {
        this.estadoSeguimiento = estadoSeguimiento;
    }

    public Integer getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(Integer idEtapa) {
        this.idEtapa = idEtapa;
    }

    public String getEtapaDetalle() {
        return etapaDetalle;
    }

    public void setEtapaDetalle(String etapaDetalle) {
        this.etapaDetalle = etapaDetalle;
    }

    public String getIndicadorEtapa() {
        return indicadorEtapa;
    }

    public void setIndicadorEtapa(String indicadorEtapa) {
        this.indicadorEtapa = indicadorEtapa;
    }

    public String getEstadoDetalle() {
        return estadoDetalle;
    }

    public void setEstadoDetalle(String estadoDetalle) {
        this.estadoDetalle = estadoDetalle;
    }

    public Integer getEstadoGestion() {
        return estadoGestion;
    }

    public void setEstadoGestion(Integer estadoGestion) {
        this.estadoGestion = estadoGestion;
    }

    public Integer getIndDerivado() {
        return indDerivado;
    }

    public void setIndDerivado(Integer indDerivado) {
        this.indDerivado = indDerivado;
    }

    public Integer getCodigoOD() {
        return codigoOD;
    }

    public void setCodigoOD(Integer codigoOD) {
        this.codigoOD = codigoOD;
    }

    public Long getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Long consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Integer getIdPrimerNivel() {
        return idPrimerNivel;
    }

    public void setIdPrimerNivel(Integer idPrimerNivel) {
        this.idPrimerNivel = idPrimerNivel;
    }

    public Integer getIdSegundoNivel() {
        return idSegundoNivel;
    }

    public void setIdSegundoNivel(Integer idSegundoNivel) {
        this.idSegundoNivel = idSegundoNivel;
    }

    public Integer getIdTercerNivel() {
        return idTercerNivel;
    }

    public void setIdTercerNivel(Integer idTercerNivel) {
        this.idTercerNivel = idTercerNivel;
    }

    public Integer getIdCuartoNivel() {
        return idCuartoNivel;
    }

    public void setIdCuartoNivel(Integer idCuartoNivel) {
        this.idCuartoNivel = idCuartoNivel;
    }

    public Integer getIdQuintoNivel() {
        return idQuintoNivel;
    }

    public void setIdQuintoNivel(Integer idQuintoNivel) {
        this.idQuintoNivel = idQuintoNivel;
    }

    public Integer getIdSextoNivel() {
        return idSextoNivel;
    }

    public void setIdSextoNivel(Integer idSextoNivel) {
        this.idSextoNivel = idSextoNivel;
    }
    
    
    
}
