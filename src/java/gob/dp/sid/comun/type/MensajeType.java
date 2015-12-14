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

public enum MensajeType {

    MENSAJE_DERIVACION(1, "Mensaje por Derivación", "Derivación", "green");

    /**
     * La Constante list.
     */
    private static final List<MensajeType> list = new ArrayList<>();

    /**
     * La Constante lookup.
     */
    private static final Map<Integer, MensajeType> lookup = new HashMap<>();

    static {
        for (MensajeType s : EnumSet.allOf(MensajeType.class)) {
            list.add(s);
            lookup.put(s.getKey(), s);
        }

    }

    /**
     * El key.
     */
    private final Integer key;

    /**
     * El value.
     */
    private final String value;

    private final String detalle;

    private final String color;

    private MensajeType(Integer key, String value, String detalle, String color) {
        this.key = key;
        this.value = value;
        this.detalle = detalle;
        this.color = color;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getDetalle() {
        return detalle;
    }

    public String getColor() {
        return color;
    }

    public static MensajeType get(Integer key) {
        return lookup.get(key);
    }
}