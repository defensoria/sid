package gob.dp.sid.administracion.seguridad.bean;

import java.sql.Timestamp;

public class FiltroBusquedaUsuarioRol {
    private String codigoUsuario;
    private String codigoRol;
    private String descripcion;
    private String alcance;
    private String codigoSistema;
    private Timestamp fechaInicio;
    private Timestamp fechaFin;
    private String estado;
    private String comentario;
    private String tipo;
    private String descripcionAlcance;
    private String descripcionSistema;
    private String chequeado;
    private boolean seleccionado;
    private boolean visible;
    private String codigoUsuarioSession;
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
     * @return Returns the codigoSistema.
     */
    public String getCodigoSistema() {
            return codigoSistema;
    }
    /**
     * @param codigoSistema The codigoSistema to set.
     */
    public void setCodigoSistema(String codigoSistema) {
            this.codigoSistema = codigoSistema;
    }
    /**
     * @return Returns the codigoUsuario.
     */
    public String getCodigoUsuario() {
            return codigoUsuario;
    }
    /**
     * @param codigoUsuario The codigoUsuario to set.
     */
    public void setCodigoUsuario(String codigoUsuario) {
            this.codigoUsuario = codigoUsuario;
    }
    /**
     * @return Returns the comentario.
     */
    public String getComentario() {
            return comentario;
    }
    /**
     * @param comentario The comentario to set.
     */
    public void setComentario(String comentario) {
            this.comentario = comentario;
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
     * @return Returns the fechaFin.
     */
    public Timestamp getFechaFin() {
            return fechaFin;
    }
    /**
     * @param fechaFin The fechaFin to set.
     */
    public void setFechaFin(Timestamp fechaFin) {
            this.fechaFin = fechaFin;
    }
    /**
     * @return Returns the fechaInicio.
     */
    public Timestamp getFechaInicio() {
            return fechaInicio;
    }
    /**
     * @param fechaInicio The fechaInicio to set.
     */
    public void setFechaInicio(Timestamp fechaInicio) {
            this.fechaInicio = fechaInicio;
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
     * @return Returns the chequeado.
     */
    public String getChequeado() {
            return chequeado;
    }
    /**
     * @param chequeado The chequeado to set.
     */
    public void setChequeado(String chequeado) {
            this.chequeado = chequeado;
    }
    /**
     * @return Returns the descripcionAlcance.
     */
    public String getDescripcionAlcance() {
            return descripcionAlcance;
    }
    /**
     * @param descripcionAlcance The descripcionAlcance to set.
     */
    public void setDescripcionAlcance(String descripcionAlcance) {
            this.descripcionAlcance = descripcionAlcance;
    }
    /**
     * @return Returns the descripcionSistema.
     */
    public String getDescripcionSistema() {
            return descripcionSistema;
    }
    /**
     * @param descripcionSistema The descripcionSistema to set.
     */
    public void setDescripcionSistema(String descripcionSistema) {
            this.descripcionSistema = descripcionSistema;
    }
    /**
     * @return Returns the seleccionado.
     */
    public boolean isSeleccionado() {
            return seleccionado;
    }
    /**
     * @param seleccionado The seleccionado to set.
     */
    public void setSeleccionado(boolean seleccionado) {
            this.seleccionado = seleccionado;
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
     * @return Returns the visible.
     */
    public boolean isVisible() {
            return visible;
    }
    /**
     * @param visible The visible to set.
     */
    public void setVisible(boolean visible) {
            this.visible = visible;
    }
}