/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.sid.administracion.seguridad.bean;

/**
 * @author Dante
 *
 * Clase utilizada como filtro para la busqueda de rol
 * @author Dante
 * @version 1.0 - 01/12/2011
 */
public class FiltroBusquedaRol {

    private String codigoRol;
    private String nombre;
    private String descripcion;
    private String estado;
    private String tipo;
    private String alcance;
    private String sistema;
    private String codigoUsuarioSession;
    private String estadoDescripcion;
    private String alcanceDescripcion;
    private String tipoDescripcion;
    private String nombreSistema;
    private String error;

    /**
     * @return Retorna el nombreSistema.
     */
    public String getNombreSistema() {
            return nombreSistema;
    }
    /**
     * @param nombreSistema El nombreSistema a ingresar.
     */
    public void setNombreSistema(String nombreSistema) {
            this.nombreSistema = nombreSistema;
    }
    /**
     * @return Returns the alcance.
     */
    public String getAlcance() {
            return alcance;
    }
    /**
     * @param alcance The alcance to set.
     */
    public void setAlcance(String alcance) {
            this.alcance = alcance;
    }
    /**
     * @return Returns the codigoRol.
     */
    public String getCodigoRol() {
            return codigoRol;
    }
    /**
     * @param codigoRol The codigoRol to set.
     */
    public void setCodigoRol(String codigoRol) {
            this.codigoRol = codigoRol;
    }
    /**
     * @return Returns the codigoUsuarioSession.
     */
    public String getCodigoUsuarioSession() {
            return codigoUsuarioSession;
    }
    /**
     * @param codigoUsuarioSession The codigoUsuarioSession to set.
     */
    public void setCodigoUsuarioSession(String codigoUsuarioSession) {
            this.codigoUsuarioSession = codigoUsuarioSession;
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
     * @return Returns the sistema.
     */
    public String getSistema() {
            return sistema;
    }
    /**
     * @param sistema The sistema to set.
     */
    public void setSistema(String sistema) {
            this.sistema = sistema;
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
     * @return Returns the alcanceDescripcion.
     */
    public String getAlcanceDescripcion() {
            return alcanceDescripcion;
    }
    /**
     * @param alcanceDescripcion The alcanceDescripcion to set.
     */
    public void setAlcanceDescripcion(String alcanceDescripcion) {
            this.alcanceDescripcion = alcanceDescripcion;
    }
    /**
     * @return Returns the estadoDescripcion.
     */
    public String getEstadoDescripcion() {
            return estadoDescripcion;
    }
    /**
     * @param estadoDescripcion The estadoDescripcion to set.
     */
    public void setEstadoDescripcion(String estadoDescripcion) {
            this.estadoDescripcion = estadoDescripcion;
    }
    /**
     * @return Returns the tipoDescripcion.
     */
    public String getTipoDescripcion() {
            return tipoDescripcion;
    }
    /**
     * @param tipoDescripcion The tipoDescripcion to set.
     */
    public void setTipoDescripcion(String tipoDescripcion) {
            this.tipoDescripcion = tipoDescripcion;
    }
    /**
     * @return Returns the error.
     */
    public String getError() {
            return error;
    }
    /**
     * @param error The error to set.
     */
    public void setError(String error) {
            this.error = error;
    }
}