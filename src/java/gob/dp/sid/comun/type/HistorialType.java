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
        
        HISTORIAL_DERIVAR_RECHAZAR("Derivar", "Se rechaza la solicitud de derivación "),
        
        HISTORIAL_CONSULTA_ENVIA("Consulta", "Se crea y envia la consulta "),
        
        HISTORIAL_CONSULTA_APRUEBA("Consulta", "Aprueba la consulta "),
        
        HISTORIAL_CONSULTA_DESAPRUEBA("Consulta", "Desaprueba la consulta "),
        
        HISTORIAL_CONSULTA_ACEPTA("Consulta", "Se acepta y asigna la consulta derivación "),
        
        HISTORIAL_CONSULTA_RECHAZA("Consulta", "Se rechaza la consulta "),
        
        HISTORIAL_CONSULTA_RESPONDE("Consulta", "Responde la consulta "),
        
        HISTORIAL_CONSULTA_APRUEBA_RESPUESTA("Consulta", "Aprueba la respuesta "),
        
        HISTORIAL_CONSULTA_DESAPRUEBA_RESPUESTA("Consulta", "Desaprueba la respuesta "),
        
        HISTORIAL_CONSULTA_ACEPTA_RESPUESTA("Consulta", "Acepta la respuesta y se finaliza la consulta"),
        
        HISTORIAL_CONSULTA_RECHAZA_RESPUESTA("Consulta", "Rechaza la consulta "),
        
        HISTORIAL_SUSPENCION_ENVIA("Suspención", "Envia la solicitud de suspención "),
        
        HISTORIAL_SUSPENCION_APRUEBA("Suspención", "Aprueba la solicitud de suspención "),
        
        HISTORIAL_SUSPENCION_DESAPRUEBA("Suspención", "Desaprueba la solicitud de suspención "),
        
        HISTORIAL_SUSPENCION_ACEPTA("Suspención", "Acepta la solicitud de suspención "),
        
        HISTORIAL_SUSPENCION_RECHAZA("Suspención", "Rechaza la solicitud de suspención "),
        
        HISTORIAL_AMPLIACION_ENVIA("Ampliación", "Envia la solictud de ampliación "),
        
        HISTORIAL_AMPLIACION_APRUEBA("Ampliación", "Aprueba la solictud de ampliación "),
        
        HISTORIAL_AMPLIACION_DESAPRUEBA("Ampliación", "Desaprueba la solictud de ampliación "),
        
        HISTORIAL_AMPLIACION_ACEPTA("Ampliación", "Acepta la solictud de ampliación "),
        
        HISTORIAL_AMPLIACION_RECHAZA("Consulta", "Rechaza la solictud de ampliación "),
        
        HISTORIAL_COMPARTIR_EXPEDIENTE("Compartir", "Se ha compartido el expediente "),
        
        HISTORIAL_CONCLUSION_MANUAL("Conclusión manual", "Se concluye el expediente manualmente ");
	
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
