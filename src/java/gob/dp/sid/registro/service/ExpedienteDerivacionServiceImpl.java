/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.dao.ExpedienteDerivacionDAO;
import gob.dp.sid.registro.entity.ExpedienteDerivacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ExpedienteDerivacionServiceImpl implements ExpedienteDerivacionService{
    
    @Autowired
    private ExpedienteDerivacionDAO expedienteDerivacionDAO;

    @Override
    public void expedienteDerivacionInsertar(ExpedienteDerivacion expedienteDerivacion) {
        expedienteDerivacionDAO.expedienteDerivacionInsertar(expedienteDerivacion);
    }

    @Override
    public List<ExpedienteDerivacion> expedienteDerivacionSelectList(long idExpediente) {
        return expedienteDerivacionDAO.expedienteDerivacionSelectList(idExpediente);
    }
    
}
