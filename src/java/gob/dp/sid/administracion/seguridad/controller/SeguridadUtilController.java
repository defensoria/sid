/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.administracion.seguridad.controller;

import gob.dp.sid.administracion.seguridad.entity.Recurso;
import gob.dp.sid.administracion.seguridad.entity.Usuario;
import gob.dp.sid.administracion.seguridad.service.RolService;
import gob.dp.sid.comun.SessionUtil;
import java.util.Map;

import javax.inject.Named;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Named
@Scope("session")
public class SeguridadUtilController {

    private static final Logger log = Logger.getLogger(SeguridadUtilController.class);
    
    @Autowired
    private RolService rolService;

    public RolService getRolService() {
        return rolService;
    }

    public void setRolService(RolService rolService) {
        this.rolService = rolService;
    }

    public boolean tieneRecurso(String codigoRecurso) {
        Usuario usuario = SessionUtil.getUsuario();
        Map<String, Recurso> map = usuario.getMapRecurso();
        return map.get(codigoRecurso) != null;
    }

    public boolean tieneRol(String codigoRol) {
        Usuario usuario = SessionUtil.getUsuario();
        Map map = rolService.buscarMapRolSegunUsuario(usuario);
        return map.get(codigoRol) != null;
    }
    
    public boolean tieneRolUsuario(String codigoRol, Usuario usuario) {
        Map map = rolService.buscarMapRolSegunUsuario(usuario);
        return map.get(codigoRol) != null;

    }
}
