/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.entity.ExpedienteClasificacionTipo;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ExpedienteClasificacionTipoService {
    
    public List<ExpedienteClasificacionTipo> clasificacionCabecera(int idClasificacion);
    
    public List<ExpedienteClasificacionTipo> clasificacionPorIdPadre(int idPadre);
    
    public ExpedienteClasificacionTipo clasificacionOne(int id);
    
    public List<ExpedienteClasificacionTipo> clasificacioneExpedienteClasiTipo(long idExpedienteClasificacion);
    
}
