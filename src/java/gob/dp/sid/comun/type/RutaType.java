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

public enum RutaType {
    
    //public String FILE_SYSTEM="C:/server/glassfish-4.0/glassfish4/glassfish/domains/domain1/docroot/filesystem/";
    /**LINUX*/
    
    
    //public static final String BASE_URL_REPORT = "C:\\recursos\\reportesSID\\";
    /**LINUX*/
    
	//URL_FILE_SYSTEM("ANT", "/usr/local/glassfish4/glassfish/domains/domain1/docroot/filesystem/");
        URL_FILE_SYSTEM("ANT", "C:/server/glassfish-4.0/glassfish4/glassfish/domains/domain1/docroot/filesystem/");

        //BASE_URL_REPORT("DES", "/usr/local/recursos/reportes/");   
	
	/** La Constante list. */
	private static final List<RutaType> list = new ArrayList<>();
	
	/** La Constante lookup. */
	private static final Map<String, RutaType> lookup = new HashMap<>();

	static {
		for (RutaType s : EnumSet.allOf(RutaType.class)) {
			list.add(s);
			lookup.put(s.getKey(), s);
		}

	}
	
	/** El key. */
	private String key;
	
	/** El value. */
	private String value;

	
	private RutaType(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
        
	public static List<SelectVO> getList() {
		List<SelectVO> rList = new ArrayList<>();
		for (RutaType s : list) {
			SelectVO select = new SelectVO();
			select.setId(s.getKey());
			select.setValue(s.getValue());
			rList.add(select);
		}
		return rList;
	}
        
	public static RutaType get(String key) {
		return lookup.get(key);
	}
}
