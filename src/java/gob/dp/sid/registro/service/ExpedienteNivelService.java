/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.entity.ExpedienteNivel;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ExpedienteNivelService {
    
    public void expedienteNivelInsertar(ExpedienteNivel expedienteNivel);
    
    public void expedienteNivelUpdate(Long idExpedienteNivel);
    
    public List<ExpedienteNivel> expedienteNivelPorExpediente(String numeroExpediente);
    
    public void expedienteNivelActualizar(ExpedienteNivel expedienteNivel);
    
}
