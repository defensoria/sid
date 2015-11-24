package gob.dp.sid.comun.type;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.model.SelectItem;

public enum EstadoExpedienteType {

	CALIFICACION_ADMITIDA(1, "Admitida", 1),

	CALIFICACION_NO_ADMITIDA(2, "No Admitida", 1),
	
	INVESTIGACION_FUNDADO(3, "Fundado", 2),
	
	INVESTIGACION_INFUNDADO(4, "Infundado", 2),
        
        PERSUACION_ACOGIDO(5, "Acogido", 3),
        
        PERSUACION_NO_ACOGIDO(6, "No Acogido", 3),
        
        SEGUIMIENTO_ACOGIDO(7, "Acogido", 4),
        
        SEGUIMIENTO_NO_ACOGIDO(8, "No Acogido", 4);
	
	
	/** La Constante list. */
	private static final List<EstadoExpedienteType> list = new ArrayList<>();
	
	/** La Constante lookup. */
	private static final Map<Integer, EstadoExpedienteType> lookup = new HashMap<>();

	static {
		for (EstadoExpedienteType s : EnumSet.allOf(EstadoExpedienteType.class)) {
			list.add(s);
			lookup.put(s.getKey(), s);
		}

	}
	
	/** El key. */
	private int key;
	
	/** El value. */
	private String value;
	
	/** El tipo. */
	private int tipo;

	/**
	 * Instancia un nuevo tipo via type.
	 *
	 * @param key el key
	 * @param value el value
	 */
	private EstadoExpedienteType(int key, String value, int tipo) {
		this.key = key;
		this.value = value;
		this.tipo = tipo;
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
	public int getTipo() {
		return tipo;
	}

	
	public String getDescription() {
		return this.getValue();
	}


	public static List<SelectItem> getListEstado(int tipo) {
		List<SelectItem> rList = new ArrayList<>();
		for (EstadoExpedienteType s : list) {
			if(s.getTipo() == tipo){
				rList.add(new SelectItem(s.getKey(), s.getValue()));
			}
			
		}
		return rList;
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
	public static EstadoExpedienteType get(String key) {
		return lookup.get(key);
	}
	
	public static String getValues(String keys){
		String val ="";
		
		
		return val;
	}
}
