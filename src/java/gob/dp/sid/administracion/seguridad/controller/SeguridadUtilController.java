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
import javax.faces.bean.SessionScoped;

import javax.inject.Named;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;


/**
 *
 * @author WIN7
 */
@Named
@Scope("session")
public class SeguridadUtilController {

    private static Logger log = Logger.getLogger(SeguridadUtilController.class);
    @Autowired
    private RolService rolService;

    public RolService getRolService() {
        return rolService;
    }

    public void setRolService(RolService rolService) {
        this.rolService = rolService;
    }

    
    public boolean tieneRecurso(String codigoRecurso){
    	   System.out.println(codigoRecurso);
    	 Usuario usuario=SessionUtil.getUsuario();
    	 Map<String, Recurso> map=usuario.getMapRecurso();
    	 if(map.get(codigoRecurso)!=null ){           
             return true;
         }else{            
             return false;
         }
    }
    
    

    public boolean tieneRol(String codigoRol){
      
        Usuario usuario=SessionUtil.getUsuario();
        Map map=rolService.buscarMapRolSegunUsuario(usuario);
        if(map.get(codigoRol)!=null ){           
            return true;
        }else{            
            return false;
        }

    }
}
