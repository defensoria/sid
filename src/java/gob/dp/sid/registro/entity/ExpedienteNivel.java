/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.entity;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author carlos
 */
public class ExpedienteNivel implements Serializable{
    
    private Long id;
    
    private String numeroExpediente;
    
    private Integer idPrimerNivel;
            
    private Integer idSegundoNivel;
            
    private Integer idTercerNivel;
    
    private Integer idCuartoNivel;
                    
    private Integer idQuintoNivel;
                            
    private Integer idSextoNivel;
                            
    private String estado;
    
    
    /**TEMPORALES*/
    
    private String primerNivel;
    
    private String segundoNivel;
    
    private String tercerNivel;
    
    private String cuartoNivel;
    
    private String quintoNivel;
    
    private String sextoNivel;
    
    private String indicadorSegundoTipo;
    
    private List<ExpedienteClasificacionTipo> listaClasificacionTipo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPrimerNivel() {
        return primerNivel;
    }

    public void setPrimerNivel(String primerNivel) {
        this.primerNivel = primerNivel;
    }

    public String getSegundoNivel() {
        return segundoNivel;
    }

    public void setSegundoNivel(String segundoNivel) {
        this.segundoNivel = segundoNivel;
    }

    public String getTercerNivel() {
        return tercerNivel;
    }

    public void setTercerNivel(String tercerNivel) {
        this.tercerNivel = tercerNivel;
    }

    public String getCuartoNivel() {
        return cuartoNivel;
    }

    public void setCuartoNivel(String cuartoNivel) {
        this.cuartoNivel = cuartoNivel;
    }

    public String getQuintoNivel() {
        return quintoNivel;
    }

    public void setQuintoNivel(String quintoNivel) {
        this.quintoNivel = quintoNivel;
    }

    public String getSextoNivel() {
        return sextoNivel;
    }

    public void setSextoNivel(String sextoNivel) {
        this.sextoNivel = sextoNivel;
    }

    public List<ExpedienteClasificacionTipo> getListaClasificacionTipo() {
        return listaClasificacionTipo;
    }

    public void setListaClasificacionTipo(List<ExpedienteClasificacionTipo> listaClasificacionTipo) {
        this.listaClasificacionTipo = listaClasificacionTipo;
    }

    public String getIndicadorSegundoTipo() {
        return indicadorSegundoTipo;
    }

    public void setIndicadorSegundoTipo(String indicadorSegundoTipo) {
        this.indicadorSegundoTipo = indicadorSegundoTipo;
    }
    
}
