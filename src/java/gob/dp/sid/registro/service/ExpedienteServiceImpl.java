/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.comun.entity.Parametro;
import gob.dp.sid.comun.service.CacheService;
import gob.dp.sid.registro.dao.ExpedienteDAO;
import gob.dp.sid.registro.entity.Expediente;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ExpedienteServiceImpl implements ExpedienteService{
    
    private static final Logger log = Logger.getLogger(ExpedienteServiceImpl.class);
    
    @Autowired
    private ExpedienteDAO expedienteDAO;
    
    @Autowired
    private CacheService cacheService;

    @Override
    public void expedienteInsertar(Expediente expediente) {
        expedienteDAO.expedienteInsertar(expediente);
    }

    @Override
    public void expedienteUpdate(Expediente expediente) {
        expedienteDAO.expedienteUpdate(expediente);
    }

    @Override
    public String etiquetaBuscarAutocomplete() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("var projects = [");
        int i = 0;
        try {
            for(Parametro a: cacheService.buscarExpedienteEtiquetas()){
                if(i > 0)
                    stringBuilder.append(",");
                    stringBuilder.append("{value: '").append(a.getValorParametro()).append("' ,");
                    stringBuilder.append("label: '").append(a.getNombreParametro()).append("' ,");
                    stringBuilder.append("desc: ").append("''").append(",");
                    stringBuilder.append("icon: ").append("'' },");
            }
            stringBuilder.append("];");
        } catch (Exception ex) {
            log.error(ex);
        } 
        return stringBuilder.toString();
    }

    @Override
    public List<Expediente> expedienteBuscar(Expediente expediente) {
        return expedienteDAO.expedienteBuscar(expediente);
    }

    @Override
    public List<Expediente> expedienteBuscarUsuario(String codigo) {
        return expedienteDAO.expedienteBuscarUsuario(codigo);
    }

    @Override
    public void expedienteConcluir(long idExpediente) {
        expedienteDAO.expedienteConcluir(idExpediente);
    }

    @Override
    public List<Expediente> expedienteBuscarUsuarioPaginado(Expediente expediente) {
        return expedienteDAO.expedienteBuscarUsuarioPaginado(expediente);
    }

    @Override
    public List<Expediente> expedienteReporteMesUsuario(Expediente expediente) {
        return expedienteDAO.expedienteReporteMesUsuario(expediente);    }

    @Override
    public List<Expediente> expedientexPersona(long idPersona) {
        return expedienteDAO.expedientexPersona(idPersona);
    }

    @Override
    public List<Expediente> expedienteBuscarUsuarios(Expediente expediente) {
        return expedienteDAO.expedienteBuscarUsuarios(expediente);
    }
    
}
