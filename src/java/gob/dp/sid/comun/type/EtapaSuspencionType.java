package gob.dp.sid.comun.type;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum EtapaSuspencionType {

	SUSPENCION_ETAPA_ENVIA(1, "Se envia la Suspencion"),
        
        SUSPENCION_ETAPA_APRUEBA(2, "Se aprueba o desaprueba el envio de la Suspencion"),
        
        SUSPENCION_ETAPA_ACEPTA(3, "Acepta la Suspencion");
        
        
	/** La Constante list. */
	private static final List<EtapaSuspencionType> list = new ArrayList<>();
	
	/** La Constante lookup. */
	private static final Map<Integer, EtapaSuspencionType> lookup = new HashMap<>();

	static {
		for (EtapaSuspencionType s : EnumSet.allOf(EtapaSuspencionType.class)) {
			list.add(s);
			lookup.put(s.getKey(), s);
		}

	}
	
	/** El key. */
	private int key;
	
	/** El value. */
	private String value;

	/**
	 * Instancia un nuevo tipo via type.
	 *
	 * @param key el key
	 * @param value el value
	 */
	private EtapaSuspencionType(int key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Obtiene key.
	 *
	 * @return Retorna un valor de tipo String para el key del tipo de via.
	 */
	public int getKey() {
		return key;
	}

	/**
	 * Obtiene value.
	 *
	 * @return Retorna un valor de tipo String para el valor del tipo de via.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Obtiene description.
	 *
	 * @param locale Par&aacute;metro de tipo Locale que determina la localidad.
	 * @return Retorna un valor de tipo String con la descripci&oacute;n del
	 * tipo de via.
	 */
	
	public String getDescription() {
		return this.getValue();
	}
	
	/**
	 * 
	 * Metodo constructor del Enum TipoViaType con par&aacute;metro.
	 * 
	 * @param key
	 *            Par&aacute;metro de tipo String que determina el key del tipo
	 *            de via.
	 * @return void.
	 */
	public static EtapaSuspencionType get(String key) {
		return lookup.get(key);
	}
        
}
