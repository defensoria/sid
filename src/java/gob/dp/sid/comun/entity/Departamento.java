/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.comun.entity;

import java.io.Serializable;

/**
 *
 * @author carlos
 */
public class Departamento implements Serializable{
    
    private int id;
    
    private String descripcion;
    
    private String coordenadax;
    
    private String coordenaday;
    
    private int zoom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCoordenadax() {
        return coordenadax;
    }

    public void setCoordenadax(String coordenadax) {
        this.coordenadax = coordenadax;
    }

    public String getCoordenaday() {
        return coordenaday;
    }

    public void setCoordenaday(String coordenaday) {
        this.coordenaday = coordenaday;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }
    
    
}
