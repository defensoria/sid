/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.sid.administracion.seguridad.controller;
import gob.dp.sid.administracion.seguridad.bean.FiltroUsuario;
import gob.dp.sid.administracion.seguridad.entity.Usuario;
import gob.dp.sid.administracion.seguridad.service.UsuarioService;
import gob.dp.sid.comun.MessagesUtil;
import java.io.Serializable;

import javax.inject.Named;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;


/**
 *
 * @author Administrador
 */
@Named
@Scope("session")
public class UsuarioClaveController implements Serializable {

     private static Logger log = Logger.getLogger(UsuarioClaveController.class);

    private Usuario usuario;
    private String mensaje;
    private boolean verGuardar=true;
    MessagesUtil msg;
    
    @Autowired
    private UsuarioService usuarioService;

    public UsuarioClaveController() {
        msg = new MessagesUtil();
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

     public String verCambioClave(String codigo){
        this.verGuardar=true;
        log.debug("Entrando a verCambioClave");
        log.debug("codigo:"+codigo);
        FiltroUsuario filter=new FiltroUsuario();
        filter.setCodigo(codigo);
        this.mensaje="";
        usuario=usuarioService.buscarUsuario(filter).get(0);
        usuario.setConfirmacionClave(usuario.getClave());

        return "usuarioClave";
     }

     public void cambiarClave(){
          if(!this.usuario.getClave().trim().equals(this.usuario.getConfirmacionClave().trim())){
              System.out.println("error.usuario.claves");
              return;
          }
          try{
            usuarioService.cambiarClave(usuario);
            usuario.setClave("");
            usuario.setConfirmacionClave("");
            msg.messageInfo("Se realizaron todos los cambios correctamente", null);
         }catch(Exception ex){
             log.error("Ocurrió un error:",ex);
         }

     }

     public String regresar(){
         return "usuarioLista";
     }

    public Usuario getUsuario() {
        if(this.usuario==null){
            this.usuario=new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isVerGuardar() {
        return verGuardar;
    }

    public void setVerGuardar(boolean verGuardar) {
        this.verGuardar = verGuardar;
    }


}
