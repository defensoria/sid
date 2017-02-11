package gob.dp.sid.seguridad.dao;
import gob.dp.sid.seguridad.entity.Recurso;
import gob.dp.sid.seguridad.entity.Rol;
import gob.dp.sid.seguridad.entity.RolRecurso;
import gob.dp.sid.seguridad.entity.Usuario;
import java.util.List;
import java.util.Map;


public interface RecursoDAO {

    public List<Recurso> buscarRecurso(Recurso filtro);
    public List<Recurso> buscarRecursoSegunUsuario(Usuario filtro);
    public Map<String, Recurso> buscarMapRecursoSegunUsuario(Usuario filtro);
    public List<Recurso> buscarRecursoSegunRol(Rol filtro);
    
    public void insertarRolRecurso(RolRecurso rolRecurso)throws Exception;
    public void deleteRolRecurso(RolRecurso rolRecurso)throws Exception;
 
}