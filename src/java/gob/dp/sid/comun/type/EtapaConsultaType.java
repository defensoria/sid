package gob.dp.sid.comun.type;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum EtapaConsultaType {
    
        CONSULTA_ETAPA_CANCELADO(0, "Se desaprueba en alguna etapa"),

	CONSULTA_ETAPA_ENVIA(1, "Se envia la consulta"),
        
        CONSULTA_ETAPA_APRUEBA(2, "Se aprueba o desaprueba el envio de la consulta"),
        
        CONSULTA_ETAPA_REASIGNA(3, "Se aprueba o desaprueba la recepcion de la consulta"),
        
        CONSULTA_ETAPA_RESPONDE(4, "Se responde la consulta"),
        
        RESPUESTA_ETAPA_APRUEBA(5, "Aprobación de la respuesta"),
        
        RESPUESTA_ETAPA_ACEPTA(6, "Aceptación de la respuesta");
        
        
	/** La Constante list. */
	private static final List<EtapaConsultaType> list = new ArrayList<>();
	
	/** La Constante lookup. */
	private static final Map<Integer, EtapaConsultaType> lookup = new HashMap<>();

	static {
		for (EtapaConsultaType s : EnumSet.allOf(EtapaConsultaType.class)) {
			list.add(s);
			lookup.put(s.getKey(), s);
		}
	}
	
	/** El key. */
	private final int key;
	
	/** El value. */
	private final String value;

	private EtapaConsultaType(int key, String value) {
		this.key = key;
		this.value = value;
	}

	public int getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public String getDescription() {
		return this.getValue();
	}

	public static EtapaConsultaType get(String key) {
		return lookup.get(key);
	}
        
}
