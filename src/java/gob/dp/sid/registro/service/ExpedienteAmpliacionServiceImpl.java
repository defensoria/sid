/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.dao.ExpedienteAmpliacionDAO;
import gob.dp.sid.registro.entity.ExpedienteAmpliacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ExpedienteAmpliacionServiceImpl implements ExpedienteAmpliacionService{
    
    @Autowired
    private ExpedienteAmpliacionDAO expedienteAmpliacionDAO;

    @Override
    public void expedienteAmpliacionInsertar(ExpedienteAmpliacion expedienteAmpliacion) {
        expedienteAmpliacionDAO.expedienteAmpliacionInsertar(expedienteAmpliacion);
    }

    @Override
    public List<ExpedienteAmpliacion> expedienteAmpliacionSelectList(long idExpediente) {
        return expedienteAmpliacionDAO.expedienteAmpliacionSelectList(idExpediente);
    }
    
}
