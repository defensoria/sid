/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.ExpedienteFormularioVirtual;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ExpedienteFormularioVirtualDAO {
    
    public void expedienteFormularioVirtualInsertar(ExpedienteFormularioVirtual ExpedienteFormularioVirtual);
            
    public void expedienteFormularioVirtualUpdate(ExpedienteFormularioVirtual ExpedienteFormularioVirtual);
            
    public List<ExpedienteFormularioVirtual> expedienteFormularioVirtualSelect(ExpedienteFormularioVirtual ExpedienteFormularioVirtual);
    
    public List<ExpedienteFormularioVirtual> expedienteFormularioVirtualBuscar(ExpedienteFormularioVirtual ExpedienteFormularioVirtual);
    
    public void expedienteFormularioVirtualInactiva(Long idExpedienteFormularioVirtual);
    
}
