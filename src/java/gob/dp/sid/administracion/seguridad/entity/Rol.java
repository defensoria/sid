package gob.dp.sid.administracion.seguridad.entity;

import java.io.Serializable;

/**
 * @author Dante
 *
 * Clase utilizada en el registro de Rol
 * @author Dante
 * @version 1.0 - 02/12/2011
 */
public class Rol implements Serializable {
    
    private String codigo;
    private String nombre;
    private String descripcion;
    private String estado;

    public Rol() {
    }

    public Rol(String codigo) {
        this.codigo = codigo;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}