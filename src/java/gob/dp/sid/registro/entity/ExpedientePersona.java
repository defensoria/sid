/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.entity;

import java.io.Serializable;

/**
 *
 * @author carlos
 */
public class ExpedientePersona implements Serializable{
    
    private Long id;
    
    private Expediente expediente;
    
    private Persona persona;
    
    private String tipo;
    
    private Boolean indicadorReserva;
    
    private String nombreCompleto;
    
    private String detalleCargo;
    
    private String nro;
    
    private String direccion;
    
    private String email;
    
    private String telefono1;
    
    private String direccionNotifica;
    
    private String idDepartamento;
    
    private String idProvincia;
    
    private String idDistrito;
    
    private String contacto;
    
    private String tipoLengua;
    
    private Boolean indicadorDiscapacitado;
    
    private String tipoPueblo;

    public ExpedientePersona(Expediente expediente, Persona persona) {
        this.expediente = expediente;
        this.persona = persona;
    }

    public ExpedientePersona() {
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getIndicadorReserva() {
        return indicadorReserva;
    }

    public void setIndicadorReserva(Boolean indicadorReserva) {
        this.indicadorReserva = indicadorReserva;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDetalleCargo() {
        return detalleCargo;
    }

    public void setDetalleCargo(String detalleCargo) {
        this.detalleCargo = detalleCargo;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(String idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(String idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getDireccionNotifica() {
        return direccionNotifica;
    }

    public void setDireccionNotifica(String direccionNotifica) {
        this.direccionNotifica = direccionNotifica;
    }

    public String getTipoLengua() {
        return tipoLengua;
    }

    public void setTipoLengua(String tipoLengua) {
        this.tipoLengua = tipoLengua;
    }

    public Boolean getIndicadorDiscapacitado() {
        return indicadorDiscapacitado;
    }

    public void setIndicadorDiscapacitado(Boolean indicadorDiscapacitado) {
        this.indicadorDiscapacitado = indicadorDiscapacitado;
    }

    public String getTipoPueblo() {
        return tipoPueblo;
    }

    public void setTipoPueblo(String tipoPueblo) {
        this.tipoPueblo = tipoPueblo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
}
