/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.dao.ExpedientePersonaDAO;
import gob.dp.sid.registro.entity.ExpedientePersona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ExpedientePersonaServiceImpl implements ExpedientePersonaService{
    
    @Autowired
    private ExpedientePersonaDAO expedientePersonaDAO;

    @Override
    public void expedientePersonaInsertar(ExpedientePersona expedientePersona) {
        expedientePersonaDAO.expedientePersonaInsertar(expedientePersona);
    }

    @Override
    public void expedientePersonaUpdate(ExpedientePersona expedientePersona) {
        expedientePersonaDAO.expedientePersonaUpdate(expedientePersona);
    }

    @Override
    public void expedientePersonaDelete(ExpedientePersona expedientePersona) {
        expedientePersonaDAO.expedientePersonaDelete(expedientePersona);
    }

    @Override
    public int expedientePersonaContar(ExpedientePersona expedientePersona) {
        return expedientePersonaDAO.expedientePersonaContar(expedientePersona);
    }

    @Override
    public List<ExpedientePersona> expedientePersonaBuscarXExpediente(long idExpediente) {
        return expedientePersonaDAO.expedientePersonaBuscarXExpediente(idExpediente);
    }

    @Override
    public void expedienteDatosPersonaUpdate(ExpedientePersona expedientePersona) {
        expedientePersonaDAO.expedienteDatosPersonaUpdate(expedientePersona);
    }
    
}
