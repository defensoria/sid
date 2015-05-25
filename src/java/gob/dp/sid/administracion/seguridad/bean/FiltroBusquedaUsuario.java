package gob.dp.sid.administracion.seguridad.bean;

import java.util.Date;

public class FiltroBusquedaUsuario {

    private String codigoUsuario;
    private String nombres;
    private String apellidos;
    private String documentos;
    private String clave;
    private String entidad;
    private String ambito;
    private String estado;
    private String tipoUsuario;

    private String codigoEntidad;
    private String codigoAmbito;
    private String codigoEstado;
    private String codigoTipo;

    private String flagExpiClave;
    private String flagExpiUsuario;
    private String flagClaveInicial;
    private Date fechaExpiUsuario;
    private Date fechaUltimoCambioClave;
    private Date fechaMaxCambioClave;

    private boolean seleccionado=false;
    private String tipoDocumento;
    private String usuarioRegistra;
    /**
     * @return Returns the ambito.
     */
    public String getAmbito() {
            return ambito;
    }
    /**
     * @param ambito The ambito to set.
     */
    public void setAmbito(String ambito) {
            this.ambito = ambito;
    }
    /**
     * @return Returns the entidad.
     */
    public String getEntidad() {
            return entidad;
    }
    /**
     * @param entidad The entidad to set.
     */
    public void setEntidad(String entidad) {
            this.entidad = entidad;
    }
    /**
     * @return Returns the apellidos.
     */
    public String getApellidos() {
            return apellidos;
    }
    /**
     * @param apellidos The apellidos to set.
     */
    public void setApellidos(String apellidos) {
            this.apellidos = apellidos;
    }
    /**
     * @return Returns the clave.
     */
    public String getClave() {
            return clave;
    }
    /**
     * @param clave The clave to set.
     */
    public void setClave(String clave) {
            this.clave = clave;
    }

    /**
     * @return Returns the documentos.
     */
    public String getDocumentos() {
            return documentos;
    }
    /**
     * @param documentos The documentos to set.
     */
    public void setDocumentos(String documentos) {
            this.documentos = documentos;
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


    public String getFlagClaveInicial() {
            return flagClaveInicial;
    }
    /**
     * @param flagClaveInicial The flagClaveInicial to set.
     */
    public void setFlagClaveInicial(String flagClaveInicial) {
            this.flagClaveInicial = flagClaveInicial;
    }
    /**
     * @return Returns the flagExpiClave.
     */
    public String getFlagExpiClave() {
            return flagExpiClave;
    }
    /**
     * @param flagExpiClave The flagExpiClave to set.
     */
    public void setFlagExpiClave(String flagExpiClave) {
            this.flagExpiClave = flagExpiClave;
    }
    /**
     * @return Returns the flagExpiUsuario.
     */
    public String getFlagExpiUsuario() {
            return flagExpiUsuario;
    }
    /**
     * @param flagExpiUsuario The flagExpiUsuario to set.
     */
    public void setFlagExpiUsuario(String flagExpiUsuario) {
            this.flagExpiUsuario = flagExpiUsuario;
    }
    /**
     * @return Returns the nombres.
     */
    public String getNombres() {
            return nombres;
    }
    /**
     * @param nombres The nombres to set.
     */
    public void setNombres(String nombres) {
            this.nombres = nombres;
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
     * @return Returns the codigoAmbito.
     */
    public String getCodigoAmbito() {
            return codigoAmbito;
    }
    /**
     * @param codigoAmbito The codigoAmbito to set.
     */
    public void setCodigoAmbito(String codigoAmbito) {
            this.codigoAmbito = codigoAmbito;
    }
    /**
     * @return Returns the codigoEntidad.
     */
    public String getCodigoEntidad() {
            return codigoEntidad;
    }
    /**
     * @param codigoEntidad The codigoEntidad to set.
     */
    public void setCodigoEntidad(String codigoEntidad) {
            this.codigoEntidad = codigoEntidad;
    }
    /**
     * @return Returns the codigoEstado.
     */
    public String getCodigoEstado() {
            return codigoEstado;
    }
    /**
     * @param codigoEstado The codigoEstado to set.
     */
    public void setCodigoEstado(String codigoEstado) {
            this.codigoEstado = codigoEstado;
    }
    /**
     * @return Returns the codigoTipo.
     */
    public String getCodigoTipo() {
            return codigoTipo;
    }
    /**
     * @param codigoTipo The codigoTipo to set.
     */
    public void setCodigoTipo(String codigoTipo) {
            this.codigoTipo = codigoTipo;
    }

    public String getTipoUsuario() {
            return tipoUsuario;
    }
    /**
     * @param tipoUsuario The tipoUsuario to set.
     */
    public void setTipoUsuario(String tipoUsuario) {
            this.tipoUsuario = tipoUsuario;
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
     * @return Returns the fechaExpiUsuario.
     */
    public Date getFechaExpiUsuario() {
            return fechaExpiUsuario;
    }
    /**
     * @param fechaExpiUsuario The fechaExpiUsuario to set.
     */
    public void setFechaExpiUsuario(Date fechaExpiUsuario) {
            this.fechaExpiUsuario = fechaExpiUsuario;
    }
    /**
     * @return Returns the fechaMaxCambioClave.
     */
    public Date getFechaMaxCambioClave() {
            return fechaMaxCambioClave;
    }
    /**
     * @param fechaMaxCambioClave The fechaMaxCambioClave to set.
     */
    public void setFechaMaxCambioClave(Date fechaMaxCambioClave) {
            this.fechaMaxCambioClave = fechaMaxCambioClave;
    }
    /**
     * @return Returns the fechaUltimoCambioClave.
     */
    public Date getFechaUltimoCambioClave() {
            return fechaUltimoCambioClave;
    }
    /**
     * @param fechaUltimoCambioClave The fechaUltimoCambioClave to set.
     */
    public void setFechaUltimoCambioClave(Date fechaUltimoCambioClave) {
            this.fechaUltimoCambioClave = fechaUltimoCambioClave;
    }
    /**
     * @return Returns the tipoDocumento.
     */
    public String getTipoDocumento() {
            return tipoDocumento;
    }
    /**
     * @param tipoDocumento The tipoDocumento to set.
     */
    public void setTipoDocumento(String tipoDocumento) {
            this.tipoDocumento = tipoDocumento;
    }
    /**
     * @return Returns the usuarioRegistra.
     */
    public String getUsuarioRegistra() {
            return usuarioRegistra;
    }
    /**
     * @param usuarioRegistra The usuarioRegistra to set.
     */
    public void setUsuarioRegistra(String usuarioRegistra) {
            this.usuarioRegistra = usuarioRegistra;
    }
}
