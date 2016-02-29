/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.dao.ExpedienteHistorialDAO;
import gob.dp.sid.registro.entity.ExpedienteHistorial;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ExpedienteHistorialServiceImpl implements ExpedienteHistorialService{
    
    @Autowired
    private ExpedienteHistorialDAO expedienteHistorialDAO;

    @Override
    public void expedienteHistorialInsertar(ExpedienteHistorial expedienteHistorial) {
        expedienteHistorialDAO.expedienteHistorialInsertar(expedienteHistorial);
    }

    @Override
    public List<ExpedienteHistorial> expedienteHistorialBuscar(String numeroExpediente) {
        return expedienteHistorialDAO.expedienteHistorialBuscar(numeroExpediente);
    }
    
}
