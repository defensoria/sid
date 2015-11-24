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
public class GestionEtapa {
    
    private Long idGestion;
    
    private Long idExpediente;
    
    private Integer idEtapa;
    
    private String numeroExpediente;

    public GestionEtapa() {
    }

    public GestionEtapa(Long idGestion, Long idExpediente, Integer idEtapa, String numeroExpediente) {
        this.idGestion = idGestion;
        this.idExpediente = idExpediente;
        this.idEtapa = idEtapa;
        this.numeroExpediente = numeroExpediente;
    }

    public Integer getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(Integer idEtapa) {
        this.idEtapa = idEtapa;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public Long getIdGestion() {
        return idGestion;
    }

    public void setIdGestion(Long idGestion) {
        this.idGestion = idGestion;
    }

    public Long getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(Long idExpediente) {
        this.idExpediente = idExpediente;
    }

}
