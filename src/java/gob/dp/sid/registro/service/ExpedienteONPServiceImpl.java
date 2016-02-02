/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.dao.ExpedienteONPDAO;
import gob.dp.sid.registro.entity.ExpedienteONP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ExpedienteONPServiceImpl implements ExpedienteONPService{
    
    @Autowired
    private ExpedienteONPDAO expedienteONPDAO;

    @Override
    public void expedienteONPInsertar(ExpedienteONP onp) {
        expedienteONPDAO.expedienteONPInsertar(onp);
    }

    @Override
    public void expedienteONPUpdate(ExpedienteONP onp) {
        expedienteONPDAO.expedienteONPUpdate(onp);
    }

    @Override
    public ExpedienteONP expedienteONPBuscarId(long id) {
        return expedienteONPDAO.expedienteONPBuscarId(id);
    }

    @Override
    public ExpedienteONP expedienteONPBuscarExpediente(String numeroExpediente) {
        return expedienteONPDAO.expedienteONPBuscarExpediente(numeroExpediente);
    }
    
}
