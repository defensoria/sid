/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.bandeja.dao;

import gob.dp.sid.bandeja.entity.Bandeja;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface BandejaDAO {
    
    public void bandejaInsertar(Bandeja bandeja);
            
    public List<Bandeja> bandejaBuscarUsuario(String destinatario);
    
}
