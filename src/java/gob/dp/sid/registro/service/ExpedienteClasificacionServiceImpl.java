/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.dao.ExpedienteClasificacionDAO;
import gob.dp.sid.registro.entity.ExpedienteClasificacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ExpedienteClasificacionServiceImpl implements ExpedienteClasificacionService{
    
    @Autowired
    private ExpedienteClasificacionDAO expedienteClasificacionDAO;

    @Override
    public List<ExpedienteClasificacion> listaExpedienteClasificacion(ExpedienteClasificacion expedienteClasificacion) {
        return expedienteClasificacionDAO.listaExpedienteClasificacion(expedienteClasificacion);
    }

    @Override
    public List<ExpedienteClasificacion> expedienteClasificacionBusqueda(ExpedienteClasificacion expedienteClasificacion) {
        return expedienteClasificacionDAO.expedienteClasificacionBusqueda(expedienteClasificacion);
    }

    @Override
    public ExpedienteClasificacion expedienteClasificacionOne(Integer id) {
        return expedienteClasificacionDAO.expedienteClasificacionOne(id);
    }

    @Override
    public List<ExpedienteClasificacion> expedienteClasificacionBusquedaGrupo1(ExpedienteClasificacion expedienteClasificacion) {
        return expedienteClasificacionDAO.expedienteClasificacionBusquedaGrupo1(expedienteClasificacion);
    }
    
}
