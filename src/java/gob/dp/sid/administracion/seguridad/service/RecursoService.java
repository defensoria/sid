/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.administracion.seguridad.service;
import gob.dp.sid.administracion.seguridad.entity.Recurso;
import gob.dp.sid.administracion.seguridad.entity.Rol;
import gob.dp.sid.administracion.seguridad.entity.Usuario;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrador
 */
public interface RecursoService {
    public List<Recurso> buscarRecurso(Recurso filtro);
    public List<Recurso> buscarRecursosAsignados(Rol filtro);
    
    public List<Recurso> buscarRecursoSegunUsuario(Usuario filtro);
    public Map<String,Recurso> buscarMapRecursoSegunUsuario(Usuario filtro);
    public void asignarRecursosRol(Rol rol,List<Recurso> listaRecursoSeleccionado) throws Exception;
}
