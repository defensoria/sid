/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.ExpedienteEntidad;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ExpedienteEntidadDAO {
    
    void expedienteEntidadInsertar(ExpedienteEntidad expedienteEntidad);
    
    void expedienteEntidadDelete(ExpedienteEntidad expedienteEntidad);
    
    int expedienteEntidadContar(ExpedienteEntidad expedienteEntidad);
    
    List<ExpedienteEntidad> expedienteEntidadBuscarXExpediente(Long idExpediente);
}
