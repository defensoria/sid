/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.entity.ExpedienteGestion;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ExpedienteGestionService {
    
    public void expedienteGestionInsertar(ExpedienteGestion expedienteGestion);
            
    public void expedienteGestionUpdate(ExpedienteGestion expedienteGestion);
    
    public List<ExpedienteGestion> expedienteGestionLista(Long idExpediente);
            
    public ExpedienteGestion expedienteGestionBuscarOne(Long id);   
    
    public List<ExpedienteGestion> expedienteGestionListaXexpediente(String numeroExpediente);
    
    public Integer expedienteGestionCountONP(String codigoONP);
    
}
