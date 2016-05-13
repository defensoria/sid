/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.dao.ExpedienteGestionDAO;
import gob.dp.sid.registro.entity.ExpedienteGestion;
import java.util.List;
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
    public List<ExpedienteGestion> expedienteGestionLista(Long idExpediente) {
        return expedienteGestionDAO.expedienteGestionLista(idExpediente);
    }

    @Override
    public ExpedienteGestion expedienteGestionBuscarOne(Long id) {
        return expedienteGestionDAO.expedienteGestionBuscarOne(id);
    }

    @Override
    public List<ExpedienteGestion> expedienteGestionListaXexpediente(String numeroExpediente) {
        return expedienteGestionDAO.expedienteGestionListaXexpediente(numeroExpediente);
    }

    @Override
    public Integer expedienteGestionCountONP(String codigoONP) {
        return expedienteGestionDAO.expedienteGestionCountONP(codigoONP);
    }

    @Override
    public ExpedienteGestion expedienteGestionPorONP(String codigoONP) {
        return expedienteGestionDAO.expedienteGestionPorONP(codigoONP);
    }

    @Override
    public ExpedienteGestion expedienteGestionPorONPUltimo(String numeroExpediente) {
        return expedienteGestionDAO.expedienteGestionPorONPUltimo(numeroExpediente);
    }

    @Override
    public void expedienteGestionEliminarArchivo1(long idGestion) {
        expedienteGestionDAO.expedienteGestionEliminarArchivo1(idGestion);
    }

    @Override
    public void expedienteGestionEliminarArchivo2(long idGestion) {
        expedienteGestionDAO.expedienteGestionEliminarArchivo2(idGestion);
    }
    
}
