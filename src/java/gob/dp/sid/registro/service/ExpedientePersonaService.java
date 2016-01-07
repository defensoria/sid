/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.entity.ExpedientePersona;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ExpedientePersonaService {
    
    public void expedientePersonaInsertar(ExpedientePersona expedientePersona);
    
    public void expedientePersonaUpdate(ExpedientePersona expedientePersona);
    
    public void expedientePersonaDelete(ExpedientePersona expedientePersona);
    
    public int expedientePersonaContar(ExpedientePersona expedientePersona);
    
    public List<ExpedientePersona> expedientePersonaBuscarXExpediente(long idExpediente);
    
    public void expedienteDatosPersonaUpdate(ExpedientePersona expedientePersona);
}
