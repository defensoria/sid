/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.dao.EntidadDAO;
import gob.dp.sid.registro.entity.Entidad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class EntidadServiceImpl implements EntidadService{
    
    @Autowired
    private EntidadDAO entidadDAO;

    @Override
    public void entidadInsertar(Entidad entidad) {
        entidadDAO.entidadInsertar(entidad);
    }

    @Override
    public void entidadUpdate(Entidad entidad) {
        entidadDAO.entidadUpdate(entidad);
    }

    @Override
    public List<Entidad> entidadBuscarCadena(String cadena) {
        return entidadDAO.entidadBuscarCadena(cadena);
    }
    
    
    
}
