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

    @Override
    public OficinaDefensorial obtenerOficinaDefensorial(Long id) {
        return oficinaDefensorialDAO.obtenerOficinaDefensorial(id);
    }

    @Override
    public List<OficinaDefensorial> listaAdjuntiasDefensoriales() {
        return oficinaDefensorialDAO.listaAdjuntiasDefensoriales();
    }

    @Override
    public List<OficinaDefensorial> listaAdjuntiasDefensorialesConsulta() {
        return oficinaDefensorialDAO.listaAdjuntiasDefensorialesConsulta();
    }

    @Override
    public List<OficinaDefensorial> listaAdjuntiasDefensorialesTodos() {
        return oficinaDefensorialDAO.listaAdjuntiasDefensorialesTodos();
    }
    
}
