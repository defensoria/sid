/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.dao.ExpedienteGestionDAO;
import gob.dp.sid.registro.entity.ExpedienteGestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ExpedienteGestionServiceImpl implements ExpedienteGestionService{
    
    @Autowired
    private ExpedienteGestionDAO expedienteGestionDAO;

    @Override
    public void expedienteGestionInsertar(ExpedienteGestion expedienteGestion) {
        expedienteGestionDAO.expedienteGestionInsertar(expedienteGestion);
    }

    @Override
    public void expedienteGestionUpdate(ExpedienteGestion expedienteGestion) {
        expedienteGestionDAO.expedienteGestionUpdate(expedienteGestion);
    }

    @Override
    public ExpedienteGestion expedienteGestionBuscarOne() {
        return expedienteGestionDAO.expedienteGestionBuscarOne();
    }
    
}
