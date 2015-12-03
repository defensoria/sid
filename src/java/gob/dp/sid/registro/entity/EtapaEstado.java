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
public class EtapaEstado {
    
    private Long id;
    
    private Integer idEstado;
    
    private Integer idEtapa;
    
    private Long idExpediente;
    
    private String detalle;
    
    private String numeroExpediente;
    
    private String indicador;
    
    private String indicadorEtapa;
    
    private Integer verEtapa;
    
    /**TEMPORALES*/
    private String ultimaEtapa;
    
    private String estadoDetalle;
    
    private String ultimoEstado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(Integer idEtapa) {
        this.idEtapa = idEtapa;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public Long getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(Long idExpediente) {
        this.idExpediente = idExpediente;
    }

    public String getIndicador() {
        return indicador;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public String getIndicadorEtapa() {
        return indicadorEtapa;
    }

    public void setIndicadorEtapa(String indicadorEtapa) {
        this.indicadorEtapa = indicadorEtapa;
    }

    public Integer getVerEtapa() {
        return verEtapa;
    }

    public void setVerEtapa(Integer verEtapa) {
        this.verEtapa = verEtapa;
    }

    public String getUltimaEtapa() {
        return ultimaEtapa;
    }

    public void setUltimaEtapa(String ultimaEtapa) {
        this.ultimaEtapa = ultimaEtapa;
    }

    public String getEstadoDetalle() {
        return estadoDetalle;
    }

    public void setEstadoDetalle(String estadoDetalle) {
        this.estadoDetalle = estadoDetalle;
    }

    public String getUltimoEstado() {
        return ultimoEstado;
    }

    public void setUltimoEstado(String ultimoEstado) {
        this.ultimoEstado = ultimoEstado;
    }

}
