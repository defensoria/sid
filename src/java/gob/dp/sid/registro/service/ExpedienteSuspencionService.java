/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.entity.ExpedienteSuspencion;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ExpedienteSuspencionService {
    
    public void expedienteSuspencionInsertar(ExpedienteSuspencion expedienteSuspencion);
    
    public List<ExpedienteSuspencion> expedienteSuspencionSelectList(long idExpediente);
}
