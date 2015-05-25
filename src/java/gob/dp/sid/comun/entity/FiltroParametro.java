/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.sid.comun.entity;

/**
 *
 * @author carlos
 */
public class FiltroParametro {
    
    private Integer codigoParametro;
    
    private Integer codigoPadreParametro;
    
    private String valorParametro;
    
    private boolean ordenValor;

    public Integer getCodigoPadreParametro() {
        return codigoPadreParametro;
    }

    public void setCodigoPadreParametro(Integer codigoPadreParametro) {
        this.codigoPadreParametro = codigoPadreParametro;
    }

    public Integer getCodigoParametro() {
        return codigoParametro;
    }

    public void setCodigoParametro(Integer codigoParametro) {
        this.codigoParametro = codigoParametro;
    }


	public boolean isOrdenValor() {
		return ordenValor;
	}

	public void setOrdenValor(boolean ordenValor) {
		this.ordenValor = ordenValor;
	}

    public String getValorParametro() {
        return valorParametro;
    }

    public void setValorParametro(String valorParametro) {
        this.valorParametro = valorParametro;
    }
    
}
