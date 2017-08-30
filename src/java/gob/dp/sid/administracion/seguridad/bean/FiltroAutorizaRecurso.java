package gob.dp.sid.administracion.seguridad.bean;

/**
 * @author Dante
 *
 * Clase utilizada como filtro para la autorización de recurso
 * @author Dante
 * @version 1.0 - 01/12/2011
 */
public class FiltroAutorizaRecurso {
    private String codigoUsuario;
    private String codRecurso;
    private boolean autorizado;
    private String url;

    /**
     * @param codigoUsuario
     * @param codRecurso
     * @param autorizado
     * @param url
     */
    public FiltroAutorizaRecurso(String codigoUsuario, String codRecurso,
                    String url) {
            super();
            this.codigoUsuario = codigoUsuario;
            this.codRecurso = codRecurso;
            this.autorizado = false;
            this.url = url;
    }
    /**
     * @param codigoUsuario
     * @param codRecurso
     */
    public FiltroAutorizaRecurso(String codigoUsuario, String codRecurso) {
        super();
        this.codigoUsuario = codigoUsuario;
        this.codRecurso = codRecurso;
        this.autorizado=false;
    }
    /**
     * @return Retorna el autorizado.
     */
    public boolean isAutorizado() {
        return autorizado;
    }
    /**
     * @param autorizado El autorizado a ingresar.
     */
    public void setAutorizado(boolean autorizado) {
        this.autorizado = autorizado;
    }
    /**
     * @return Retorna el codigoUsuario.
     */
    public String getCodigoUsuario() {
        return codigoUsuario;
    }
    /**
     * @param codigoUsuario El codigoUsuario a ingresar.
     */
    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }
    /**
     * @return Retorna el codRecurso.
     */
    public String getCodRecurso() {
        return codRecurso;
    }
    /**
     * @param codRecurso El codRecurso a ingresar.
     */
    public void setCodRecurso(String codRecurso) {
        this.codRecurso = codRecurso;
    }
    /**
     * @return Retorna el url.
     */
    public String getUrl() {
            return url;
    }
    /**
     * @param url El url a ingresar.
     */
    public void setUrl(String url) {
            this.url = url;
    }
}