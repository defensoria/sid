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
    
    private String admision;
    
    private Boolean calificacion;
    
    private String usuarioRegistro;
    
    private String estado;
    
    private Integer version;
    
    private Date fechaVersion;
    
    /*agregados*/
    
    private String idEtiqueta;
    
    private String nombreEtiqueta;
    
    private String clasificacionTipoNombre;
    
    private String ingresoTipoNombre;
    
    private String ingresoTipoTema;
    
    private String ingresoTipoSubTema;
    
    

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

    public String getAdmision() {
        return admision;
    }

    public void setAdmision(String admision) {
        this.admision = admision;
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

    public Date getFechaVersion() {
        return fechaVersion;
    }

    public void setFechaVersion(Date fechaVersion) {
        this.fechaVersion = fechaVersion;
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
    
    
}
