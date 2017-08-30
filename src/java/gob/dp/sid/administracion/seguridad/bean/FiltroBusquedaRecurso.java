package gob.dp.sid.administracion.seguridad.bean;

/**
 * @author Dante
 *
 * Clase utilizada como filtro para la busqueda de recurso
 * @author Dante
 * @version 1.0 - 01/12/2011
 */
public class FiltroBusquedaRecurso {
    private String codRecurso;
    private String nombre;
    private String descripcion;
    private String estado;
    private String tipo;
    private String padreRecurso;
    private String dirUrl;
    private Integer orden;
    private String usuarioSession;
    private String descripcionEstado;

    /**
     * @return Retorna el orden.
     */
    public Integer getOrden() {
            return orden;
    }
    /**
     * @param orden El orden a ingresar.
     */
    public void setOrden(Integer orden) {
            this.orden = orden;
    }

    /**
     * @return Retorna el usuarioSession.
     */
    public String getUsuarioSession() {
            return usuarioSession;
    }
    /**
     * @param usuarioSession El usuarioSession a ingresar.
     */
    public void setUsuarioSession(String usuarioSession) {
            this.usuarioSession = usuarioSession;
    }
    /**
     * @return Returns the codRecurso.
     */
    public String getCodRecurso() {
            return codRecurso;
    }
    /**
     * @param codRecurso The codRecurso to set.
     */
    public void setCodRecurso(String codRecurso) {
            this.codRecurso = codRecurso;
    }
    /**
     * @return Returns the descripcion.
     */
    public String getDescripcion() {
            return descripcion;
    }
    /**
     * @param descripcion The descripcion to set.
     */
    public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
    }
    /**
     * @return Returns the dirUrl.
     */
    public String getDirUrl() {
            return dirUrl;
    }
    /**
     * @param dirUrl The dirUrl to set.
     */
    public void setDirUrl(String dirUrl) {
            this.dirUrl = dirUrl;
    }
    /**
     * @return Returns the estado.
     */
    public String getEstado() {
            return estado;
    }
    /**
     * @param estado The estado to set.
     */
    public void setEstado(String estado) {
            this.estado = estado;
    }
    /**
     * @return Returns the nombre.
     */
    public String getNombre() {
            return nombre;
    }
    /**
     * @param nombre The nombre to set.
     */
    public void setNombre(String nombre) {
            this.nombre = nombre;
    }
    /**
     * @return Returns the padreRecurso.
     */
    public String getPadreRecurso() {
            return padreRecurso;
    }
    /**
     * @param padreRecurso The padreRecurso to set.
     */
    public void setPadreRecurso(String padreRecurso) {
            this.padreRecurso = padreRecurso;
    }
    /**
     * @return Returns the tipo.
     */
    public String getTipo() {
            return tipo;
    }
    /**
     * @param tipo The tipo to set.
     */
    public void setTipo(String tipo) {
            this.tipo = tipo;
    }
    /**
     * @return Retorna el descripcionEstado.
     */
    public String getDescripcionEstado() {
            if(estado != null && estado.equals("1")){
                    this.descripcionEstado="Activo";
            }else if(estado != null && estado.equals("0")){
                    this.descripcionEstado="Inactivo";
            }else if(estado !=null && estado.equals("2")){
                    this.descripcionEstado="Eliminado";
            }
            return this.descripcionEstado;
    }
    /**
     * @param descripcionEstado El descripcionEstado a ingresar.
     */
    public void setDescripcionEstado(String descripcionEstado) {
            this.descripcionEstado = descripcionEstado;
    }
}