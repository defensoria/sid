/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.administracion.seguridad.service;

import gob.dp.sid.administracion.seguridad.entity.UsuarioLogin;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface UsuarioLoginService {
    
    public Integer loginUsuario(UsuarioLogin login);
    
    public List<UsuarioLogin> buscarUsuarios(UsuarioLogin login);
    
}
