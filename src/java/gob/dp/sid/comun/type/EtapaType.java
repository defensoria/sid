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

	CALIFICACION(1, "Calificación"),

        INVESTIGACION(2, "Investigación"),   
        
        PERSUACION(3, "Persuación"),
        
        SEGUIMIENTO(4, "Seguimiento");
	
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
        
        

	
	private EtapaType(Integer key, String value) {
		this.key = key;
		this.value = value;
	}

	public Integer getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public static EtapaType get(String key) {
		return lookup.get(key);
	}
}
