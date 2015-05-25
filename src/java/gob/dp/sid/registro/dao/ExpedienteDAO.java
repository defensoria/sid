/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.Expediente;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ExpedienteDAO {
    
    public void expedienteInsertar(Expediente expediente);
            
    public void expedienteUpdate(Expediente expediente);  
    
    public List<Expediente> expedienteBuscar(Expediente expediente); 
    
}
