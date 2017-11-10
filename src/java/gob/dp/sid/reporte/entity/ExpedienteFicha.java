/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.reporte.entity;

import gob.dp.sid.comun.controller.AbstractManagedBean;
import gob.dp.sid.registro.entity.ExpedienteEntidad;
import gob.dp.sid.registro.entity.ExpedienteGestion;
import gob.dp.sid.registro.entity.ExpedienteNivel;
import gob.dp.sid.registro.entity.ExpedientePersona;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author carlos
 */
public class ExpedienteFicha extends AbstractManagedBean implements Serializable {
    
    private String oficinaDefensorial;
    
    private String numeroExpediente;
    
    private String fechaIngreso;
    
    private String fechaRegistro;
    
    private String claseExpediente;
    
    private String formaIngreso;
    
    private String lugarRecepcion;
    
    private String direccion;
    
    private String descripcion;
    
    private String conclusion;
    
    private String fechaConclusion;
    
    private String codigoUsuario;
    
    private String recurrente;
    
    private String representanteDF;
    
    private String comisionado;
    
    private String estadoGeneral;
    
    private List<ExpedientePersona> expedientePersonas;
    
    private List<ExpedienteEntidad> expedienteEntidades;
    
    private List<ExpedienteNivel> expedienteNiveles;
    
    private List<ExpedienteGestion> expedienteGestions;
    
    private String basePathImage = retornaRutaPath().concat("/images/defensoria.jpg");
    
    private String tieneOrientacion;
    
    private String orientacion;
    
    private String rutaJasper = retornaRutaPath().concat("/jasper/");

    public String getOficinaDefensorial() {
        return oficinaDefensorial;
    }

    public void setOficinaDefensorial(String oficinaDefensorial) {
        this.oficinaDefensorial = oficinaDefensorial;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public List<ExpedientePersona> getExpedientePersonas() {
        return expedientePersonas;
    }

    public void setExpedientePersonas(List<ExpedientePersona> expedientePersonas) {
        this.expedientePersonas = expedientePersonas;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getClaseExpediente() {
        return claseExpediente;
    }

    public void setClaseExpediente(String claseExpediente) {
        this.claseExpediente = claseExpediente;
    }

    public String getFormaIngreso() {
        return formaIngreso;
    }

    public void setFormaIngreso(String formaIngreso) {
        this.formaIngreso = formaIngreso;
    }

    public String getLugarRecepcion() {
        return lugarRecepcion;
    }

    public void setLugarRecepcion(String lugarRecepcion) {
        this.lugarRecepcion = lugarRecepcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getFechaConclusion() {
        return fechaConclusion;
    }

    public void setFechaConclusion(String fechaConclusion) {
        this.fechaConclusion = fechaConclusion;
    }

    public List<ExpedienteGestion> getExpedienteGestions() {
        return expedienteGestions;
    }

    public void setExpedienteGestions(List<ExpedienteGestion> expedienteGestions) {
        this.expedienteGestions = expedienteGestions;
    }

    public List<ExpedienteEntidad> getExpedienteEntidades() {
        return expedienteEntidades;
    }

    public void setExpedienteEntidades(List<ExpedienteEntidad> expedienteEntidades) {
        this.expedienteEntidades = expedienteEntidades;
    }

    public List<ExpedienteNivel> getExpedienteNiveles() {
        return expedienteNiveles;
    }

    public void setExpedienteNiveles(List<ExpedienteNivel> expedienteNiveles) {
        this.expedienteNiveles = expedienteNiveles;
    }

    public String getRecurrente() {
        return recurrente;
    }

    public void setRecurrente(String recurrente) {
        this.recurrente = recurrente;
    }

    public String getRepresentanteDF() {
        return representanteDF;
    }

    public void setRepresentanteDF(String representanteDF) {
        this.representanteDF = representanteDF;
    }

    public String getComisionado() {
        return comisionado;
    }

    public void setComisionado(String comisionado) {
        this.comisionado = comisionado;
    }

    public String getEstadoGeneral() {
        return estadoGeneral;
    }

    public void setEstadoGeneral(String estadoGeneral) {
        this.estadoGeneral = estadoGeneral;
    }

    public String getBasePathImage() {
        return basePathImage;
    }

    public void setBasePathImage(String basePathImage) {
        this.basePathImage = basePathImage;
    }

    public String getTieneOrientacion() {
        return tieneOrientacion;
    }

    public void setTieneOrientacion(String tieneOrientacion) {
        this.tieneOrientacion = tieneOrientacion;
    }

    public String getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(String orientacion) {
        this.orientacion = orientacion;
    }

    public String getRutaJasper() {
        return rutaJasper;
    }

    public void setRutaJasper(String rutaJasper) {
        this.rutaJasper = rutaJasper;
    }
    
}
