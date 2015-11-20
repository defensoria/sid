/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.dao.EtapaEstadoDAO;
import gob.dp.sid.registro.entity.EtapaEstado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class EtapaEstadoServiceImpl implements EtapaEstadoService{
    
    @Autowired
    private EtapaEstadoDAO etapaEstadoDAO;

    @Override
    public void etapaEstadoInsertar(EtapaEstado etapaEstado) {
        etapaEstadoDAO.etapaEstadoInsertar(etapaEstado);
    }
    
}
