/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.dao.ExpedienteTiempoDAO;
import gob.dp.sid.registro.entity.ExpedienteTiempo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ExpedienteTiempoServiceImpl implements ExpedienteTiempoService{
    
    @Autowired
    private ExpedienteTiempoDAO expedienteTiempoDAO;

    @Override
    public void expedienteTiempoInsertar(ExpedienteTiempo expedienteTiempo) {
        expedienteTiempoDAO.expedienteTiempoInsertar(expedienteTiempo);
    }

    @Override
    public void expedienteTiempoUpdate(ExpedienteTiempo expedienteTiempo) {
        expedienteTiempoDAO.expedienteTiempoUpdate(expedienteTiempo);
    }

    @Override
    public ExpedienteTiempo expedienteTiempoOne(String numeroExpediente) {
        return expedienteTiempoDAO.expedienteTiempoOne(numeroExpediente);
    }
    
    
    
}
