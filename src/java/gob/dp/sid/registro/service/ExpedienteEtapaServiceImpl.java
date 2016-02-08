/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.dao.ExpedienteEtapaDAO;
import gob.dp.sid.registro.entity.ExpedienteEtapa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ExpedienteEtapaServiceImpl implements ExpedienteEtapaService{
    
    @Autowired
    private ExpedienteEtapaDAO expedienteEtapaDAO;

    @Override
    public ExpedienteEtapa expedienteEtapaBuscar(int idEtapa) {
        return expedienteEtapaDAO.expedienteEtapaBuscar(idEtapa);
    }
    
}
