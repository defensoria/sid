/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.administracion.seguridad.dao;

import gob.dp.sid.administracion.seguridad.entity.UsuarioLogin;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface UsuarioLoginDAO {
    
    public Integer loginUsuario(UsuarioLogin login);
    
    public List<UsuarioLogin> buscarUsuarios(UsuarioLogin login);
    
}
