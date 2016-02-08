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
public class ExpedienteEtapa {
    
    private Integer idEtapa;
    
    private String valor;
    
    private String tipo;
    
    private Integer diasTotal;
    
    private Integer diasAlerta;

    public Integer getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(Integer idEtapa) {
        this.idEtapa = idEtapa;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getDiasTotal() {
        return diasTotal;
    }

    public void setDiasTotal(Integer diasTotal) {
        this.diasTotal = diasTotal;
    }

    public Integer getDiasAlerta() {
        return diasAlerta;
    }

    public void setDiasAlerta(Integer diasAlerta) {
        this.diasAlerta = diasAlerta;
    }
    
}
