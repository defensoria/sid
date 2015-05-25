/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.dao.PersonaDAO;
import gob.dp.sid.registro.entity.Persona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class PersonaServiceImpl implements PersonaService{
    
    @Autowired
    private PersonaDAO personaDAO;

    @Override
    public void personaInsertar(Persona persona) {
        personaDAO.personaInsertar(persona);
    }

    @Override
    public void personaUpdate(Persona persona) {
        personaDAO.personaUpdate(persona);
    }

    @Override
    public List<Persona> personaBuscarCadena(String cadena) {
        return personaDAO.personaBuscarCadena(cadena);
    }
    
}
