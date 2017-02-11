/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.sid.seguridad.dao;
import gob.dp.sid.seguridad.entity.Rol;
import gob.dp.sid.seguridad.entity.Usuario;
import gob.dp.sid.seguridad.entity.UsuarioRol;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrador
 */
@Repository
public class RolDAOImpl  extends SqlSessionDaoSupport implements RolDAO
{
    @Override
    public List<Rol> buscarRol(Rol filtro)
    {
       return getSqlSession().selectList("rolDAO.buscarRol",filtro);
    }

     @Override
    public List<Rol> buscarRolSegunUsuario(Usuario filtro){
        return getSqlSession().selectList("rolDAO.buscarRolSegunUsuario",filtro);
    }

    @Override
    public void insertarUsuarioRol(UsuarioRol usuarioRol)throws Exception{
        getSqlSession().insert("rolDAO.insertarUsuarioRol", usuarioRol);
    }
    @Override
    public void deleteUsuarioRol(UsuarioRol usuarioRol)throws Exception{
        getSqlSession().delete("rolDAO.deleteUsuarioRol", usuarioRol);
    }

	@Override
	public Map<String, Rol> buscarMapRolSegunUsuario(Usuario filtro) {
		
		return getSqlSession().selectMap("rolDAO.buscarRolSegunUsuario",filtro,"codigo");
	}
 
}
