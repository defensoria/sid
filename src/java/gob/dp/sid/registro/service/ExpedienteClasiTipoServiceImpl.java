/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.dao.ExpedienteClasiTipoDAO;
import gob.dp.sid.registro.entity.ExpedienteClasiTipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ExpedienteClasiTipoServiceImpl implements ExpedienteClasiTipoService{
    
    @Autowired
    private ExpedienteClasiTipoDAO expedienteClasiTipoDAO;

    @Override
    public void expedienteClasiTipoInsertar(ExpedienteClasiTipo expedienteClasiTipo) {
        expedienteClasiTipoDAO.expedienteClasiTipoInsertar(expedienteClasiTipo);
    }

    @Override
    public void expedienteClasiTipoEliminar(ExpedienteClasiTipo expedienteClasiTipo) {
        expedienteClasiTipoDAO.expedienteClasiTipoEliminar(expedienteClasiTipo);
    }
    
}
