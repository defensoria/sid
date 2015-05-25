package gob.dp.sid.administracion.seguridad.entity;

import java.io.Serializable;

/**
 * @author Dante
 *
 * Clase utilizada en el registro de Recurso
 * @author Dante
 * @version 1.0 - 02/12/2011
 */
public class Recurso implements Serializable {
    private String  codigo;
    private String  nombre;
    private String  descripcion;
    private String  estado;
    private String  codigoPadre;
    private String  url;
    private Integer orden;
    
    private boolean seleccionado=false;

    public String getCodigo() {
        return codigo;
    }

    public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

	public String getCodigoPadre() {
		return codigoPadre;
	}

	public void setCodigoPadre(String codigoPadre) {
		this.codigoPadre = codigoPadre;
	}

    


}