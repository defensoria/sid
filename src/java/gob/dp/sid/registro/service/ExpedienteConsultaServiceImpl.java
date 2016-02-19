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
    public ExpedienteConsulta expedienteConsultaSelectOne(long idExpedienteConsulta) {
        return expedienteConsultaDAO.expedienteConsultaSelectOne(idExpedienteConsulta);
    }

    @Override
    public List<ExpedienteConsulta> expedienteConsultaPorExpediente(String numeroExpediente) {
        return expedienteConsultaDAO.expedienteConsultaPorExpediente(numeroExpediente);
    }

    @Override
    public List<ExpedienteConsulta> expedienteConsultaPorExpedientePadre(String numeroExpediente) {
        return expedienteConsultaDAO.expedienteConsultaPorExpedientePadre(numeroExpediente);
    }

    @Override
    public void expedienteConsultaUpdate(ExpedienteConsulta expedienteConsulta) {
        expedienteConsultaDAO.expedienteConsultaUpdate(expedienteConsulta);
    }

    @Override
    public ExpedienteConsulta expedienteConsultaPorExpedienteHijo(ExpedienteConsulta expedienteConsulta) {
        return expedienteConsultaDAO.expedienteConsultaPorExpedienteHijo(expedienteConsulta);
    }

    @Override
    public ExpedienteConsulta expedienteConsultaPorEtapa(ExpedienteConsulta expedienteConsulta) {
        return expedienteConsultaDAO.expedienteConsultaPorEtapa(expedienteConsulta);
    }

    @Override
    public int expedienteConsultaPorEtapaCount(ExpedienteConsulta expedienteConsulta) {
        return expedienteConsultaDAO.expedienteConsultaPorEtapaCount(expedienteConsulta);
    }

}
