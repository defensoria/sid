/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.dao.GestionEtapaDAO;
import gob.dp.sid.registro.entity.GestionEtapa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class GestionEtapaServiceImpl implements GestionEtapaService{
    
    @Autowired
    private GestionEtapaDAO gestionEtapaDAO;

    @Override
    public void gestionEtapaInsertar(GestionEtapa gestionEtapa) {
        gestionEtapaDAO.gestionEtapaInsertar(gestionEtapa);
    }
    
}
