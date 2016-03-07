/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.OficinaDefensorial;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface OficinaDefensorialDAO {
    
    public List<OficinaDefensorial> listaOficinasDefensoriales();
    
    public List<OficinaDefensorial> listaAdjuntiasDefensoriales();
    
    public OficinaDefensorial obtenerOficinaDefensorial(Long id);
    
    public List<OficinaDefensorial> listaAdjuntiasDefensorialesConsulta();
    
    public List<OficinaDefensorial> listaAdjuntiasDefensorialesTodos();
    
}
