/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.sid.administracion.seguridad.service;
import gob.dp.sid.administracion.seguridad.entity.Usuario;

/**
 *
 * @author Administrador
 */
public interface AuditoriaService {

   /**
    * Cuando el usuario se encuentra en sesión
    * @param codigoAccion
    * @param detalle
    * @throws Exception
    */
    public void auditar(String codigoAccion, String detalle) throws Exception;
   /**
     * Cuando el usuario No se encuentra en sesión, le mando el usuario
     * @param codigoAccion
     * @param detalle
     * @param usuario
     * @throws Exception
     */
    public void auditar(String codigoAccion, String detalle, Usuario usuario) throws Exception;

}
