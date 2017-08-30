package gob.dp.sid.administracion.seguridad.dao;
import gob.dp.sid.administracion.seguridad.entity.Recurso;
import gob.dp.sid.administracion.seguridad.entity.Rol;
import gob.dp.sid.administracion.seguridad.entity.RolRecurso;
import gob.dp.sid.administracion.seguridad.entity.Usuario;
import java.util.List;
import java.util.Map;


/**
 * Interface RecursoDao que realiza la relación entre el service y el sqlMap
 * @author Dante
 * @version 1.0 - 14/12/2011
 */

public interface RecursoDao {

    public List<Recurso> buscarRecurso(Recurso filtro);
    public List<Recurso> buscarRecursoSegunUsuario(Usuario filtro);
    public Map<String, Recurso> buscarMapRecursoSegunUsuario(Usuario filtro);
    public List<Recurso> buscarRecursoSegunRol(Rol filtro);
    
    public void insertarRolRecurso(RolRecurso rolRecurso)throws Exception;
    public void deleteRolRecurso(RolRecurso rolRecurso)throws Exception;
 
}