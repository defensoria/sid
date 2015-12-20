/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.dao.ExpedienteConsultaDAO;
import gob.dp.sid.registro.entity.ExpedienteConsulta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ExpedienteConsultaServiceImpl implements ExpedienteConsultaService{
    
    @Autowired
    private ExpedienteConsultaDAO expedienteConsultaDAO;

    @Override
    public void expedienteConsultaInsertar(ExpedienteConsulta expedienteConsulta) {
        expedienteConsultaDAO.expedienteConsultaInsertar(expedienteConsulta);
    }

    @Override
    public List<ExpedienteConsulta> expedienteConsultaSelectList(long idExpediente) {
        return expedienteConsultaDAO.expedienteConsultaSelectList(idExpediente);
    }

    @Override
    public List<ExpedienteConsulta> expedienteConsultaPorExpediente(String numeroExpediente) {
        return expedienteConsultaDAO.expedienteConsultaPorExpediente(numeroExpediente);
    }

}
