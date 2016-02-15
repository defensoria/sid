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

public enum RolType {

	COMISIONADO_OD("ROL0000001", "COMISIONADO OD"),

        APROBADOR_OD("ROL0000002", "SUPERVISOR OD"),
        
        COMISIONADO_AD("ROL0000003", "COMISIONADO AD"),
        
        APROBADOR_AD("ROL0000004", "SUPERVISOR AD");
        
	
	/** La Constante list. */
	private static final List<RolType> list = new ArrayList<>();
	
	/** La Constante lookup. */
	private static final Map<String, RolType> lookup = new HashMap<>();

	static {
		for (RolType s : EnumSet.allOf(RolType.class)) {
			list.add(s);
			lookup.put(s.getKey(), s);
		}
	}
	
	/** El key. */
	private final String key;
	
	/** El value. */
	private final String value;

	
	private RolType(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
        
	public static RolType get(String key) {
		return lookup.get(key);
	}
}
