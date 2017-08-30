/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.bandeja.dao;

import gob.dp.sid.bandeja.entity.Bandeja;
import gob.dp.sid.bandeja.entity.FiltroBusquedaMensaje;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface BandejaDAO {
    
    public void bandejaInsertar(Bandeja bandeja);
            
    public List<Bandeja> bandejaBuscarUsuario(String destinatario);
    
    public List<Bandeja> bandejaBuscarUsuarioPendientesInternos(String destinatario);
    
    public List<Bandeja> bandejaBuscarUsuarioPendientesAutomaticos(String destinatario);
    
    public List<Bandeja> bandejaBuscarUsuarioPendientesProgramados(String destinatario);
    
    public List<Bandeja> bandejaBuscarUsuarioInternos(FiltroBusquedaMensaje fbm);
            
    public List<Bandeja> bandejaBuscarUsuarioAutomaticos(FiltroBusquedaMensaje fbm);
    
    public List<Bandeja> bandejaBuscarUsuarioProgramados(FiltroBusquedaMensaje fbm);
    
    public void mensajeEstadoVisto(Long id);
    
    public void mensajeInactivar(Long id);
    
    public Long bandejaContarPendientesAutomaticos(String destinatario);
    
    public Long bandejaContarUsuarioInternos(String destinatario);
    
    public Long bandejaContarUsuarioAutomaticos(String destinatario);
    
    public Long bandejaContarUsuarioProgramados(String destinatario);
    
}
