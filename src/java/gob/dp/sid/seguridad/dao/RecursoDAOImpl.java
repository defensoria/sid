/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.sid.seguridad.dao;
import gob.dp.sid.seguridad.entity.Recurso;
import gob.dp.sid.seguridad.entity.Rol;
import gob.dp.sid.seguridad.entity.RolRecurso;
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
public class RecursoDAOImpl  extends SqlSessionDaoSupport implements RecursoDAO
{
   @Override
    public List<Recurso> buscarRecurso(Recurso filtro)
    {
       return getSqlSession().selectList("RecursoDAO.buscarRecurso",filtro);
    }

   @Override
    public List<Recurso> buscarRecursoSegunUsuario(Usuario filtro){
        return getSqlSession().selectList("RecursoDAO.buscarRecursoSegunUsuario",filtro);
    }
   
   @Override
	public Map<String, Recurso> buscarMapRecursoSegunUsuario(Usuario filtro) {
		
		return getSqlSession().selectMap("RecursoDAO.buscarRecursoSegunUsuario",filtro,"codigo");
	}
   
   @Override
   public List<Recurso> buscarRecursoSegunRol(Rol filtro){
       return getSqlSession().selectList("RecursoDAO.buscarRecursoSegunRol",filtro);
   }
   
   @Override
   public void insertarRolRecurso(RolRecurso rolRecurso)throws Exception{
       getSqlSession().insert("RecursoDAO.insertarRolRecurso", rolRecurso);
   }
   @Override
   public void deleteRolRecurso(RolRecurso rolRecurso)throws Exception{
       getSqlSession().delete("RecursoDAO.deleteRolRecurso", rolRecurso);
   }
 
}
