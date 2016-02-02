/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.ExpedienteONP;

/**
 *
 * @author carlos
 */
public interface ExpedienteONPDAO {
    
    public void expedienteONPInsertar(ExpedienteONP onp);
            
    public void expedienteONPUpdate(ExpedienteONP onp);
            
    public ExpedienteONP expedienteONPBuscarId(long id);
                    
    public ExpedienteONP expedienteONPBuscarExpediente(String numeroExpediente);
    
}
