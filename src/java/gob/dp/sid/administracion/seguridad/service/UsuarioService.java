/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.administracion.seguridad.service;

import gob.dp.sid.administracion.seguridad.bean.FiltroUsuario;
import gob.dp.sid.administracion.seguridad.entity.Rol;
import gob.dp.sid.administracion.seguridad.entity.Usuario;

import java.util.List;

/**
 *
 * @author Administrador
 */
public interface UsuarioService {

    public List<Usuario> buscarUsuario(FiltroUsuario filtro);
    
    public List<Usuario> buscarUsuarioTotal();
    
    public String autocompletarUsuario();

    public Usuario consultarUsuario(FiltroUsuario filtro);

    public void insertarUsuario(Usuario usuario, List<Rol> listaRol);

    public void modificarUsuario(Usuario usuario, List<Rol> listaRol);

    public Integer loginUsuario(Usuario usuario) throws Exception;

    public Integer getTotalBuscarUsuario(FiltroUsuario filtro);
    
    public void modificarUsuarioSimple(Usuario usuario);
    
    public List<Usuario> listaUsuariosPorOD(Usuario usuario);
    
    public Integer listaUsuarioCount(String codigoUsuario);
    
    public Usuario buscarUsuarioOne(String codigoUsuario);
}
