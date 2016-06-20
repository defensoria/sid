/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.dao.ExpedienteFormularioVirtualDAO;
import gob.dp.sid.registro.entity.ExpedienteFormularioVirtual;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ExpedienteFormularioVirtualServiceImpl implements ExpedienteFormularioVirtualService{
    
    @Autowired
    private ExpedienteFormularioVirtualDAO expedienteFormularioVirtualDAO;

    @Override
    public void expedienteFormularioVirtualInsertar(ExpedienteFormularioVirtual ExpedienteFormularioVirtual) {
        expedienteFormularioVirtualDAO.expedienteFormularioVirtualInsertar(ExpedienteFormularioVirtual);
    }

    @Override
    public void expedienteFormularioVirtualUpdate(ExpedienteFormularioVirtual ExpedienteFormularioVirtual) {
        expedienteFormularioVirtualDAO.expedienteFormularioVirtualUpdate(ExpedienteFormularioVirtual);
    }

    @Override
    public List<ExpedienteFormularioVirtual> expedienteFormularioVirtualSelect(ExpedienteFormularioVirtual ExpedienteFormularioVirtual) {
        return expedienteFormularioVirtualDAO.expedienteFormularioVirtualSelect(ExpedienteFormularioVirtual);
    }

    @Override
    public void expedienteFormularioVirtualInactiva(Long idExpedienteFormularioVirtual) {
        expedienteFormularioVirtualDAO.expedienteFormularioVirtualInactiva(idExpedienteFormularioVirtual);
    }

    @Override
    public List<ExpedienteFormularioVirtual> expedienteFormularioVirtualBuscar(ExpedienteFormularioVirtual ExpedienteFormularioVirtual) {
        return expedienteFormularioVirtualDAO.expedienteFormularioVirtualBuscar(ExpedienteFormularioVirtual);
    }
    
}
