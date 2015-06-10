/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.ExpedientePersona;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ExpedientePersonaDAO {
    
    void expedientePersonaInsertar(ExpedientePersona expedientePersona);
    
    void expedientePersonaUpdate(ExpedientePersona expedientePersona);
    
    void expedientePersonaDelete(ExpedientePersona expedientePersona);
    
    int expedientePersonaContar(ExpedientePersona expedientePersona);
    
    List<ExpedientePersona> expedientePersonaBuscarXExpediente(long idExpediente);
    
}
