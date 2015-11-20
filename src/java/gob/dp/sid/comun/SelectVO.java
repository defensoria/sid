/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.comun;

/**
 *
 * @author carlos
 */
import java.io.Serializable;

 
public class SelectVO implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 784984275157238660L;
	
	/** El id. */
	private String id;
	
	/** El value. */
	private String value;

	/**
	 * Constructor de la clase.
	 */
	public SelectVO() {

	}

	/**
	 * Constructor de la clase.
	 *
	 * @param id Representa al identificador de la instancia de la clase
	 * @param value Representa al valor de la instancia de la clase
	 */
	public SelectVO(String id, String value) {
		super();
		this.id = id;
		this.value = value;
	}

	/**
	 * Metodo para obtener el identificador.
	 *
	 * @return Retorna un String con el identificador
	 */
	public String getId() {
		return id;
	}

	/**
	 * Metodo para asignar el identificador.
	 *
	 * @param id Recibe un String con el Identificador
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Metodo para obtener el valor.
	 *
	 * @return Retorna un String con el valor
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Metodo para asignar el valor.
	 *
	 * @param value String con el valor
	 */
	public void setValue(String value) {
		this.value = value;
	}

}