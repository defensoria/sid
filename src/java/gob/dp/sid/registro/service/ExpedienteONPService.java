/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.entity.ExpedienteONP;

/**
 *
 * @author carlos
 */
public interface ExpedienteONPService {
    
    public void expedienteONPInsertar(ExpedienteONP onp);
            
    public void expedienteONPUpdate(ExpedienteONP onp);
            
    public ExpedienteONP expedienteONPBuscarId(long id);
                    
    public ExpedienteONP expedienteONPBuscarExpediente(String numeroExpediente);
}
