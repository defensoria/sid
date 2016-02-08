/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.entity;

/**
 *
 * @author carlos
 */
public class ExpedienteTiempo {
                
    private String numeroExpediente;
    
    private Integer etapa;
    
    private Integer diasRestante;
    
    private Integer diasAlerta;
    
    private String tipoExpediente;
    
    private String estado;

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

    public Integer getDiasRestante() {
        return diasRestante;
    }

    public void setDiasRestante(Integer diasRestante) {
        this.diasRestante = diasRestante;
    }

    public String getTipoExpediente() {
        return tipoExpediente;
    }

    public void setTipoExpediente(String tipoExpediente) {
        this.tipoExpediente = tipoExpediente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getDiasAlerta() {
        return diasAlerta;
    }

    public void setDiasAlerta(Integer diasAlerta) {
        this.diasAlerta = diasAlerta;
    }
    
}
