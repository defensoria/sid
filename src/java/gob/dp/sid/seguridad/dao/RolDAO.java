package gob.dp.sid.seguridad.dao;
import gob.dp.sid.seguridad.entity.Rol;
import gob.dp.sid.seguridad.entity.Usuario;
import gob.dp.sid.seguridad.entity.UsuarioRol;
import java.util.List;
import java.util.Map;

/**
 * Interface RolDAO que realiza la relación entre el service y el sqlMap
 *
 * @author  Dante Antiporta
 * @version 1.0 - 10/12/2011
 * @since   1.0
 */
public interface RolDAO {

   public List<Rol> buscarRol(Rol filtro);
   public List<Rol> buscarRolSegunUsuario(Usuario filtro);
   public void insertarUsuarioRol(UsuarioRol usuarioRol)throws Exception;
   public void deleteUsuarioRol(UsuarioRol usuarioRol)throws Exception;
   public Map<String,Rol> buscarMapRolSegunUsuario(Usuario filtro);

}