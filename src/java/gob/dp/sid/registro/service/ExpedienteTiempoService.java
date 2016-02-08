/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.entity.ExpedienteTiempo;

/**
 *
 * @author carlos
 */
public interface ExpedienteTiempoService {
    
    public void expedienteTiempoInsertar(ExpedienteTiempo expedienteTiempo);
    
    public void expedienteTiempoUpdate(ExpedienteTiempo expedienteTiempo);
    
    public ExpedienteTiempo expedienteTiempoOne(String numeroExpediente);
    
}
