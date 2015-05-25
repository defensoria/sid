/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.entity.Expediente;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ExpedienteService {
    
    public void expedienteInsertar(Expediente expediente);
            
    public void expedienteUpdate(Expediente expediente); 
    
    public String etiquetaBuscarAutocomplete();
    
    public List<Expediente> expedienteBuscar(Expediente expediente); 
}
