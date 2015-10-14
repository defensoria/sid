/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.reporte.entity;

import java.io.Serializable;

/**
 *
 * @author carlos
 */
public class Reporte implements Serializable{
    
    private String usuario;
    
    private Integer quejaTramite;
    
    private Integer quejaConcluido;
    
    private Integer consultaTramite;
    
    private Integer consultaConcluido;
    
    private Integer petitorioTramite;
    
    private Integer petitorioConcluido;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getQuejaTramite() {
        return quejaTramite;
    }

    public void setQuejaTramite(Integer quejaTramite) {
        this.quejaTramite = quejaTramite;
    }

    public Integer getQuejaConcluido() {
        return quejaConcluido;
    }

    public void setQuejaConcluido(Integer quejaConcluido) {
        this.quejaConcluido = quejaConcluido;
    }

    public Integer getConsultaTramite() {
        return consultaTramite;
    }

    public void setConsultaTramite(Integer consultaTramite) {
        this.consultaTramite = consultaTramite;
    }

    public Integer getConsultaConcluido() {
        return consultaConcluido;
    }

    public void setConsultaConcluido(Integer consultaConcluido) {
        this.consultaConcluido = consultaConcluido;
    }

    public Integer getPetitorioTramite() {
        return petitorioTramite;
    }

    public void setPetitorioTramite(Integer petitorioTramite) {
        this.petitorioTramite = petitorioTramite;
    }

    public Integer getPetitorioConcluido() {
        return petitorioConcluido;
    }

    public void setPetitorioConcluido(Integer petitorioConcluido) {
        this.petitorioConcluido = petitorioConcluido;
    }
    
    
    
}
