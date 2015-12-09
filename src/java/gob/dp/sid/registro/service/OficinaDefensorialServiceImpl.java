/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.dao.OficinaDefensorialDAO;
import gob.dp.sid.registro.entity.OficinaDefensorial;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class OficinaDefensorialServiceImpl implements OficinaDefensorialService{
    
    @Autowired
    private OficinaDefensorialDAO oficinaDefensorialDAO;

    @Override
    public List<OficinaDefensorial> listaOficinasDefensoriales() {
        return oficinaDefensorialDAO.listaOficinasDefensoriales();
    }
    
}
