/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.bandeja.service;

import gob.dp.sid.bandeja.entity.Bandeja;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface BandejaService {
    
    public void bandejaInsertar(Bandeja bandeja);
            
    public List<Bandeja> bandejaBuscarUsuario(String destinatario);
    
    public List<Bandeja> bandejaBuscarUsuarioPendientes(String destinatario);
    
    public void mensajeEstadoVisto(Long id);
    
}
