/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.ExpedienteClasiTipo;

/**
 *
 * @author carlos
 */
public interface ExpedienteClasiTipoDAO {
    
    public void expedienteClasiTipoInsertar(ExpedienteClasiTipo expedienteClasiTipo);
    
    public void expedienteClasiTipoEliminar(ExpedienteClasiTipo expedienteClasiTipo);
}
