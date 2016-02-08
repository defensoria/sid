package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.ExpedienteTiempo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author carlos
 */
public interface ExpedienteTiempoDAO {
    
    public void expedienteTiempoInsertar(ExpedienteTiempo expedienteTiempo);
    
    public void expedienteTiempoUpdate(ExpedienteTiempo expedienteTiempo);
            
    public ExpedienteTiempo expedienteTiempoOne(String numeroExpediente);
    
}
