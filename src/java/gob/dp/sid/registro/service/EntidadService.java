/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.entity.Entidad;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface EntidadService {
    
    public void entidadInsertar(Entidad entidad);
    
    public void entidadUpdate(Entidad entidad);
    
    public List<Entidad> entidadBuscarCadena(Entidad entidad);    
    
    public List<String> entidadPorExpediente(Long idExpediente);
}
