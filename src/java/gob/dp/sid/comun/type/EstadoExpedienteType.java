package gob.dp.sid.comun.type;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.model.SelectItem;

public enum EstadoExpedienteType {

	CALIFICACION_ADMITIDA_QUEJA(1, "Admitida", 1),

	CALIFICACION_NO_ADMITIDA_QUEJA(2, "No Admitida", 1),
	
	INVESTIGACION_FUNDADO_QUEJA(3, "Fundado", 2),
	
	INVESTIGACION_INFUNDADO_QUEJA(4, "Infundado", 2),
        
        PERSUACION_ACOGIDO_QUEJA(5, "Acogido", 3),
        
        PERSUACION_NO_ACOGIDO_QUEJA(6, "No Acogido", 3),
        
        SEGUIMIENTO_ACOGIDO_QUEJA(7, "Acogido", 4),
        
        SEGUIMIENTO_NO_ACOGIDO_QUEJA(8, "No Acogido", 4),
        
        CALIFICACION_ADMITIDA_PETITORIO(9, "Admitida", 5),

	CALIFICACION_NO_ADMITIDA_PETITORIO(10, "No Admitida", 5),
	
	GESTION_SOLUCIONADO_PETITORIO(11, "Solucionado", 6),
	
	GESTION_NO_SOLUCIONADO_PETITORIO(12, "No solucionado", 6),
        
        GESTION_CONCLUIDO_PETITORIO(13, "Concluido por desestimiento", 6),
        
        PERSUACION_DEVIENEN_PETITORIO(14, "Devienen en solucionados", 7),
        
        PERSUACION_NEGATIVA_PETITORIO(15, "Por negativa expresa", 7),
        
        PERSUACION_FALTA_RESPUESTA_PETITORIO(16, "Falta de respuesta", 7);
	
	
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
