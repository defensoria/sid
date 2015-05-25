/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.comun.service;

import gob.dp.sid.comun.entity.Busqueda;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface BusquedaService {
    
    public String autocompletarBusquedaGeneral();
    
    public List<Busqueda> busquedaListaxPalabra(Busqueda busqueda);
    
}
