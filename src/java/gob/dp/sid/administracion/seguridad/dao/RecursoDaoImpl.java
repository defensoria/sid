/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.sid.administracion.seguridad.dao;
import gob.dp.sid.administracion.seguridad.entity.Recurso;
import gob.dp.sid.administracion.seguridad.entity.Rol;
import gob.dp.sid.administracion.seguridad.entity.RolRecurso;
import gob.dp.sid.administracion.seguridad.entity.Usuario;
import gob.dp.sid.administracion.seguridad.entity.UsuarioRol;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrador
 */
@Repository
public class RecursoDaoImpl  extends SqlSessionDaoSupport implements RecursoDao
{
   @Override
    public List<Recurso> buscarRecurso(Recurso filtro)
    {
       return getSqlSession().selectList("recursoDao.buscarRecurso",filtro);
    }

   @Override
    public List<Recurso> buscarRecursoSegunUsuario(Usuario filtro){
        return getSqlSession().selectList("recursoDao.buscarRecursoSegunUsuario",filtro);
    }
   
   @Override
	public Map<String, Recurso> buscarMapRecursoSegunUsuario(Usuario filtro) {
		
		return getSqlSession().selectMap("recursoDao.buscarRecursoSegunUsuario",filtro,"codigo");
	}
   
   @Override
   public List<Recurso> buscarRecursoSegunRol(Rol filtro){
       return getSqlSession().selectList("recursoDao.buscarRecursoSegunRol",filtro);
   }
   
   @Override
   public void insertarRolRecurso(RolRecurso rolRecurso)throws Exception{
       getSqlSession().insert("recursoDao.insertarRolRecurso", rolRecurso);
   }
   @Override
   public void deleteRolRecurso(RolRecurso rolRecurso)throws Exception{
       getSqlSession().delete("recursoDao.deleteRolRecurso", rolRecurso);
   }
 
}
