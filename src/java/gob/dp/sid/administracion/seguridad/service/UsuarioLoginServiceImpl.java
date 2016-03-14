/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.administracion.seguridad.service;

import gob.dp.sid.administracion.seguridad.dao.UsuarioLoginDAO;
import gob.dp.sid.administracion.seguridad.entity.UsuarioLogin;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class UsuarioLoginServiceImpl implements UsuarioLoginService{
    
    @Autowired
    private UsuarioLoginDAO usuarioLoginDAO;

    @Override
    public Integer loginUsuario(UsuarioLogin login) {
        return usuarioLoginDAO.loginUsuario(login);
    }

    @Override
    public List<UsuarioLogin> buscarUsuarios(UsuarioLogin login) {
        return usuarioLoginDAO.buscarUsuarios(login);
    }
    
}
