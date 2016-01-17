/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.entity.ExpedienteClasificacion;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ExpedienteClasificacionService {
    
    public List<ExpedienteClasificacion> listaExpedienteClasificacion(ExpedienteClasificacion expedienteClasificacion);
    
    public List<ExpedienteClasificacion> expedienteClasificacionBusqueda(ExpedienteClasificacion expedienteClasificacion);
    
    public ExpedienteClasificacion expedienteClasificacionOne(Integer id);
    
    public List<ExpedienteClasificacion> expedienteClasificacionBusquedaGrupo1(ExpedienteClasificacion expedienteClasificacion);
    
}
