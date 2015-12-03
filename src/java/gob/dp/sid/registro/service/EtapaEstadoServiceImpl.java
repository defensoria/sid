/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.comun.type.EstadoExpedienteType;
import gob.dp.sid.registro.dao.EtapaEstadoDAO;
import gob.dp.sid.registro.entity.EtapaEstado;
import java.util.List;
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

    @Override
    public void etapaEstadoUpdate(EtapaEstado etapaEstado) {
        etapaEstadoDAO.etapaEstadoUpdate(etapaEstado);
    }

    @Override
    public EtapaEstado etapaEstadoVigente(long idExpediente) {
        return etapaEstadoDAO.etapaEstadoVigente(idExpediente);
    }

    @Override
    public List<EtapaEstado> etapaEstadoxExpediente(String numeroExpediente) {
        return etapaEstadoDAO.etapaEstadoxExpediente(numeroExpediente);
    }

    @Override
    public EtapaEstado etapaEstadoInicial(long idExpediente) {
        return etapaEstadoDAO.etapaEstadoInicial(idExpediente);
    }

    @Override
    public String etapaEstadoUltimoEstado(String numeroExpediente) {
        Long codigoEstado = etapaEstadoDAO.etapaEstadoUltimoEstado(numeroExpediente);
        if(codigoEstado != null){
            return EstadoExpedienteType.getValues(codigoEstado.intValue());
        }
        return null;
    }
    
}
