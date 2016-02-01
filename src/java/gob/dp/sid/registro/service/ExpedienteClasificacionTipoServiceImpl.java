/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.dao.ExpedienteClasificacionTipoDAO;
import gob.dp.sid.registro.entity.ExpedienteClasificacionTipo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ExpedienteClasificacionTipoServiceImpl implements ExpedienteClasificacionTipoService{
    
    @Autowired
    private ExpedienteClasificacionTipoDAO clasificacionTipoDAO;

    @Override
    public List<ExpedienteClasificacionTipo> clasificacionCabecera(int idClasificacion) {
        return clasificacionTipoDAO.clasificacionCabecera(idClasificacion);
    }

    @Override
    public List<ExpedienteClasificacionTipo> clasificacionPorIdPadre(int idPadre) {
        return clasificacionTipoDAO.clasificacionPorIdPadre(idPadre);
    }

    @Override
    public ExpedienteClasificacionTipo clasificacionOne(int id) {
        return clasificacionTipoDAO.clasificacionOne(id);
    }

    @Override
    public List<ExpedienteClasificacionTipo> clasificacioneExpedienteClasiTipo(long idExpedienteClasificacion) {
        return clasificacionTipoDAO.clasificacioneExpedienteClasiTipo(idExpedienteClasificacion);
    }
    
}
