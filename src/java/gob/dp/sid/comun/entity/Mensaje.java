/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.comun.entity;

/**
 *
 * @author carlos
 */
public class Mensaje {
    
    private String titulo;
    
    private String detalle;
    
    private boolean ver;

    public Mensaje() {
    }

    public Mensaje(boolean ver) {
        this.ver = ver;
    }

    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public boolean isVer() {
        return ver;
    }

    public void setVer(boolean ver) {
        this.ver = ver;
    }
    
    
    
}
