/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.sid.administracion.parametro.bean;

/**
 *
 * @author Sony
 */
public class FiltroCatalogo{

  private Integer numParametro;
  private Integer padreParametro;
  private String nombreParametro;
  private String valorParametro;
  private String codEstado;
  private String nombreEstado;
      private Long ini;
    private Long fin;

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    /**
     * @return the numParametro
     */
    public Integer getNumParametro() {
        return numParametro;
    }

    /**
     * @param numParametro the numParametro to set
     */
    public void setNumParametro(Integer numParametro) {
        this.numParametro = numParametro;
    }

    /**
     * @return the padreParametro
     */
    public Integer getPadreParametro() {
        return padreParametro;
    }

    /**
     * @param padreParametro the padreParametro to set
     */
    public void setPadreParametro(Integer padreParametro) {
        this.padreParametro = padreParametro;
    }

    /**
     * @return the nombreParametro
     */
    public String getNombreParametro() {
        return nombreParametro;
    }

    /**
     * @param nombreParametro the nombreParametro to set
     */
    public void setNombreParametro(String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }

    /**
     * @return the valorParametro
     */
    public String getValorParametro() {
        return valorParametro;
    }

    /**
     * @param valorParametro the valorParametro to set
     */
    public void setValorParametro(String valorParametro) {
        this.valorParametro = valorParametro;
    }

    /**
     * @return the codEstado
     */
    public String getCodEstado() {
        return codEstado;
    }

    /**
     * @param codEstado the codEstado to set
     */
    public void setCodEstado(String codEstado) {
        this.codEstado = codEstado;
    }

    public Long getIni() {
        return ini;
    }

    public void setIni(Long ini) {
        this.ini = ini;
    }

    public Long getFin() {
        return fin;
    }

    public void setFin(Long fin) {
        this.fin = fin;
    }
    
    

}
