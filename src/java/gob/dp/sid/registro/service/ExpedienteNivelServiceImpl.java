/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.dao.ExpedienteNivelDAO;
import gob.dp.sid.registro.entity.ExpedienteNivel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ExpedienteNivelServiceImpl implements ExpedienteNivelService{
    
    @Autowired
    private ExpedienteNivelDAO expedienteNivelDAO;

    @Override
    public void expedienteNivelInsertar(ExpedienteNivel expedienteNivel) {
        expedienteNivelDAO.expedienteNivelInsertar(expedienteNivel);
    }

    @Override
    public void expedienteNivelUpdate(Long idExpedienteNivel) {
        expedienteNivelDAO.expedienteNivelUpdate(idExpedienteNivel);
    }

    @Override
    public List<ExpedienteNivel> expedienteNivelPorExpediente(String numeroExpediente) {
        return expedienteNivelDAO.expedienteNivelPorExpediente(numeroExpediente);
    }

    @Override
    public void expedienteNivelActualizar(ExpedienteNivel expedienteNivel) {
        expedienteNivelDAO.expedienteNivelActualizar(expedienteNivel);
    }
    
}
