/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.comun;

import gob.dp.sid.registro.entity.ExpedienteClasificacionTipo;
import java.util.List;

/**
 *
 * @author carlos
 */
public class ListadoClasificacion {
    
    private String titulo;
    
    private List<ExpedienteClasificacionTipo> items;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<ExpedienteClasificacionTipo> getItems() {
        return items;
    }

    public void setItems(List<ExpedienteClasificacionTipo> items) {
        this.items = items;
    }
    
    
    
}
