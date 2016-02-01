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
public class ExpedienteClasiTipo implements Serializable{
    
    private Long idClasi;
    
    private Integer idTipo;

    public ExpedienteClasiTipo() {
    }

    public ExpedienteClasiTipo(Long idClasi, Integer idTipo) {
        this.idClasi = idClasi;
        this.idTipo = idTipo;
    }
    
    public Long getIdClasi() {
        return idClasi;
    }

    public void setIdClasi(Long idClasi) {
        this.idClasi = idClasi;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }
}
