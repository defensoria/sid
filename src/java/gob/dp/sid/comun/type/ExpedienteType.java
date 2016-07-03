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
import org.apache.commons.lang3.StringUtils;

public enum ExpedienteType {
        
        QUEJA("01", "Queja"),
                
        CONSULTA("02", "Consulta"),
        
        PETITORIO("03", "Petitorio"),
        
        SIN_CLASIFICACION("04", "Sin clasificación");
	
	/** La Constante list. */
	private static final List<ExpedienteType> list = new ArrayList<>();
	
	/** La Constante lookup. */
	private static final Map<String, ExpedienteType> lookup = new HashMap<>();

	static {
		for (ExpedienteType s : EnumSet.allOf(ExpedienteType.class)) {
			list.add(s);
			lookup.put(s.getKey(), s);
		}

	}
	
	/** El key. */
	private final String key;
	
	/** El value. */
	private final String value;

	
	private ExpedienteType(String key, String value) {
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
		for (ExpedienteType s : list) {
			SelectVO select = new SelectVO();
			select.setId(s.getKey());
			select.setValue(s.getValue());
			rList.add(select);
		}
		return rList;
	}
        
        public static String tipoClasificacionNombre(String codigo){
            if(StringUtils.equals(codigo, QUEJA.key))
                return QUEJA.value;
            if(StringUtils.equals(codigo, CONSULTA.key))
                return CONSULTA.value;
            if(StringUtils.equals(codigo, PETITORIO.key))
                return PETITORIO.value;
            return "";
        }
        
	public static ExpedienteType get(String key) {
		return lookup.get(key);
	}
}
