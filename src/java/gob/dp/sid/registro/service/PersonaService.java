/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.entity.Persona;
import gob.dp.sid.reporte.entity.StringReport;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface PersonaService {
    
    public boolean personaInsertar(Persona persona);
    
    public void personaUpdate(Persona persona);
    
    public List<Persona> personaBuscarCadena(Persona persona);
    
    public List<Persona> personaBusarGeneral(Persona persona);
    
    public Long personaValidaDNI(String dni);
    
    public Persona personaBusquedaOne(long idPersona);
    
    public Persona personaXDNI(String dni);
    
    public List<StringReport> personaPorExpedienteRecurrente(Long idExpediente);
    
    public List<StringReport> personaPorExpedienteAfectado(Long idExpediente);
}
