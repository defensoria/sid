/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.dao.ExpedienteSuspencionDAO;
import gob.dp.sid.registro.entity.ExpedienteSuspencion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ExpedienteSuspencionServiceImpl implements ExpedienteSuspencionService{
    
    @Autowired
    private ExpedienteSuspencionDAO expedienteSuspencionDAO;

    @Override
    public void expedienteSuspencionInsertar(ExpedienteSuspencion expedienteSuspencion) {
        expedienteSuspencionDAO.expedienteSuspencionInsertar(expedienteSuspencion);
    }

    @Override
    public List<ExpedienteSuspencion> expedienteSuspencionSelectList(long idExpediente) {
        return expedienteSuspencionDAO.expedienteSuspencionSelectList(idExpediente);
    }
    
    
    
}
