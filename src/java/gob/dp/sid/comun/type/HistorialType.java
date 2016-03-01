/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.comun.type;

import gob.dp.sid.comun.SelectVO;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum HistorialType {
        
        HISTORIAL_GUARDAR_VERSION("Registro", "Se registra una nueva versión"),
        
        HISTORIAL_CONCLUIR_ETAPA("Registro", "Se concluye la etapa"),
        
        HISTORIAL_CONCLUIR_EXPEDIENTE("Registro", "Se concluye el expediente"),
        
        HISTORIAL_LEVANTAR_CONCLUSION("Registro", "Se levanta la conclusion"),
        
        HISTORIAL_CAMBIAR_TIPO("Registro", "Se cambio el tipo de expediente"),
        
        HISTORIAL_DESISTIR("Registro", "Se desiste el expediente"),
        
        HISTORIAL_ASIGNAR_EXPEDIENTE("Asignar", "Se asigno el expedediente al usuario "),
        
        HISTORIAL_GESTION_GUARDAR("Gestión", "Se creo la gestión "),
        
        HISTORIAL_GESTION_MODIFICAR("Gestión", "Se modifico la gestión "),
        
        HISTORIAL_DERIVAR_ENVIAR("Derivar", "Se creo la solicitud de derivación "),
        
        HISTORIAL_DERIVAR_APROBAR("Derivar", "Se aprobo la solicitud de derivación "),
        
        HISTORIAL_DERIVAR_DESAPROBAR("Derivar", "Se desaprobo la solicitud de derivación "),
        
        HISTORIAL_DERIVAR_ACEPTAR("Derivar", "Se acepta y reasigna la solicitud de derivación "),
        
        HISTORIAL_DERIVAR_RECHAZAR("Derivar", "Se rechaza la solicitud de derivación ");
	
	/** La Constante list. */
	private static final List<HistorialType> list = new ArrayList<>();
	
	/** La Constante lookup. */
	private static final Map<String, HistorialType> lookup = new HashMap<>();

	static {
		for (HistorialType s : EnumSet.allOf(HistorialType.class)) {
			list.add(s);
			lookup.put(s.getKey(), s);
		}

	}
	
	/** El key. */
	private final String key;
	
	/** El value. */
	private final String value;

	
	private HistorialType(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public String getDescription() {
		return this.getValue();
	}

	public static List<SelectVO> getList() {
		List<SelectVO> rList = new ArrayList<>();
		for (HistorialType s : list) {
			SelectVO select = new SelectVO();
			select.setId(s.getKey());
			select.setValue(s.getValue());
			rList.add(select);
		}
		return rList;
	}
        
	public static HistorialType get(String key) {
		return lookup.get(key);
	}
}
