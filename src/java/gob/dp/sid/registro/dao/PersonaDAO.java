/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.Persona;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface PersonaDAO {
    
    public void personaInsertar(Persona persona);
    
    public void personaUpdate(Persona persona);
    
    public List<Persona> personaBuscarCadena(String cadena);
    
    public List<Persona> personaBusarGeneral(Persona persona);
}
