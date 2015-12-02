/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.comun.type;

/**
 *
 * @author carlos
 */
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum EtapaType {

	CALIFICACION_QUEJA(1, "Calificación", "01"),

        INVESTIGACION_QUEJA(2, "Investigación", "01"),   
        
        PERSUACION_QUEJA(3, "Persuasión", "01"),
        
        SEGUIMIENTO_QUEJA(4, "Seguimiento", "01"),
        
        CALIFICACION_PETITORIO(5, "Calificación", "03"),
        
        GESTION_PETITORIO(6, "Gestión", "03"),
        
        PERSUASION_PETITORIO(7, "Persuasión", "03");
	
	/** La Constante list. */
	private static final List<EtapaType> list = new ArrayList<>();
	
	/** La Constante lookup. */
	private static final Map<Integer, EtapaType> lookup = new HashMap<>();

	static {
		for (EtapaType s : EnumSet.allOf(EtapaType.class)) {
			list.add(s);
			lookup.put(s.getKey(), s);
		}

	}
	
	/** El key. */
	private final Integer key;
	
	/** El value. */
	private final String value;
        
        /** El tipo. */
	private final String tipo;

	
	private EtapaType(Integer key, String value, String tipo) {
		this.key = key;
		this.value = value;
                this.tipo = tipo;
	}

	public Integer getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
        
        public String getTipo() {
		return tipo;
	}

	public static EtapaType get(String key) {
		return lookup.get(key);
	}
}
