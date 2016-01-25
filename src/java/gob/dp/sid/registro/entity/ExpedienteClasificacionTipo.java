/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.entity;


/**
 *
 * @author carlos
 */
public class ExpedienteClasificacionTipo{
    
    private Long id;
    
    private Long idExpedienteClasificacion;
            
    private Long idCodigoA;
            
    private Long idCodigoB;
                    
    private Long idCodigoC;
                            
    private Long idPadre;
                            
    private String estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdExpedienteClasificacion() {
        return idExpedienteClasificacion;
    }

    public void setIdExpedienteClasificacion(Long idExpedienteClasificacion) {
        this.idExpedienteClasificacion = idExpedienteClasificacion;
    }

    public Long getIdCodigoA() {
        return idCodigoA;
    }

    public void setIdCodigoA(Long idCodigoA) {
        this.idCodigoA = idCodigoA;
    }

    public Long getIdCodigoB() {
        return idCodigoB;
    }

    public void setIdCodigoB(Long idCodigoB) {
        this.idCodigoB = idCodigoB;
    }

    public Long getIdCodigoC() {
        return idCodigoC;
    }

    public void setIdCodigoC(Long idCodigoC) {
        this.idCodigoC = idCodigoC;
    }

    public Long getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Long idPadre) {
        this.idPadre = idPadre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
            
    
    
}
