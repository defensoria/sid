/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.dao.ExpedienteEntidadDAO;
import gob.dp.sid.registro.entity.ExpedienteEntidad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ExpedienteEntidadServiceImpl implements ExpedienteEntidadService{
    
    @Autowired
    private ExpedienteEntidadDAO expedienteEntidadDAO;

    @Override
    public void expedienteEntidadInsertar(ExpedienteEntidad expedienteEntidad) {
        expedienteEntidadDAO.expedienteEntidadInsertar(expedienteEntidad);
    }

    @Override
    public void expedienteEntidadDelete(ExpedienteEntidad expedienteEntidad) {
        expedienteEntidadDAO.expedienteEntidadDelete(expedienteEntidad);
    }

    @Override
    public int expedienteEntidadContar(ExpedienteEntidad expedienteEntidad) {
        return expedienteEntidadDAO.expedienteEntidadContar(expedienteEntidad);
    }

    @Override
    public List<ExpedienteEntidad> expedienteEntidadBuscarXExpediente(Long idExpediente) {
        return expedienteEntidadDAO.expedienteEntidadBuscarXExpediente(idExpediente);
    }

   
}
