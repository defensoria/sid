/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.entity.ExpedienteConsulta;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ExpedienteConsultaService {
    
    public void expedienteConsultaInsertar(ExpedienteConsulta expedienteConsulta);
    
    public ExpedienteConsulta expedienteConsultaSelectOne(long idExpedienteConsulta); 
    
    public List<ExpedienteConsulta> expedienteConsultaPorExpediente(String numeroExpediente);
    
    public List<ExpedienteConsulta> expedienteConsultaPorExpedientePadre(String numeroExpediente);
    
    public void expedienteConsultaUpdate(ExpedienteConsulta expedienteConsulta);
    
    public ExpedienteConsulta expedienteConsultaPorExpedienteHijo(ExpedienteConsulta expedienteConsulta);
    
    public ExpedienteConsulta expedienteConsultaPorEtapa(ExpedienteConsulta expedienteConsulta);
    
    public int expedienteConsultaPorEtapaCount(ExpedienteConsulta expedienteConsulta);
    
}
