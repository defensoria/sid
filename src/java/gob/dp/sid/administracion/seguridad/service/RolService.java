/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.sid.administracion.seguridad.service;
import gob.dp.sid.administracion.seguridad.entity.Rol;
import gob.dp.sid.administracion.seguridad.entity.Usuario;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrador
 */
public interface RolService {
    public List<Rol> buscarRol(Rol filtro);
    public List<Rol> buscarRolSegunUsuario(Usuario filtro);
    public void asignarRolUsuario(Usuario usuario, List<Rol> listaRolSeleccionado)throws Exception;
    public Map<String,Rol> buscarMapRolSegunUsuario(Usuario filtro);
}
