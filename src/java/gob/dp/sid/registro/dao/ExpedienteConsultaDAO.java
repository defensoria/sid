/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.ExpedienteConsulta;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ExpedienteConsultaDAO {
    
    public void expedienteConsultaInsertar(ExpedienteConsulta expedienteConsulta);
    
    public List<ExpedienteConsulta> expedienteConsultaSelectList(long idExpediente);
    
    public List<ExpedienteConsulta> expedienteConsultaPorExpediente(String numeroExpediente);
    
}
