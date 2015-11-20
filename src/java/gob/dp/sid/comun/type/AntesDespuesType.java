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
import gob.dp.sid.comun.SelectVO;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum AntesDespuesType {

	ANTES("ANT", "Antes de"),

        DESPUES("DES", "Despues de");   
	
	/** La Constante list. */
	private static final List<AntesDespuesType> list = new ArrayList<AntesDespuesType>();
	
	/** La Constante lookup. */
	private static final Map<String, AntesDespuesType> lookup = new HashMap<String, AntesDespuesType>();

	static {
		for (AntesDespuesType s : EnumSet.allOf(AntesDespuesType.class)) {
			list.add(s);
			lookup.put(s.getKey(), s);
		}

	}
	
	/** El key. */
	private String key;
	
	/** El value. */
	private String value;

	
	private AntesDespuesType(String key, String value) {
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
		for (AntesDespuesType s : list) {
			SelectVO select = new SelectVO();
			select.setId(s.getKey());
			select.setValue(s.getValue());
			rList.add(select);
		}
		return rList;
	}
        
	public static AntesDespuesType get(String key) {
		return lookup.get(key);
	}
}
