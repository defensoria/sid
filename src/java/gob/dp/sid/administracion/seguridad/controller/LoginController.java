/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.administracion.seguridad.controller;

import gob.dp.sid.administracion.seguridad.bean.FiltroUsuario;
import gob.dp.sid.administracion.seguridad.constantes.ConstantesAuditoria;
import gob.dp.sid.administracion.seguridad.entity.Usuario;
import gob.dp.sid.administracion.seguridad.entity.UsuarioLogin;
import gob.dp.sid.administracion.seguridad.service.AuditoriaService;
import gob.dp.sid.administracion.seguridad.service.UsuarioLoginService;
import gob.dp.sid.administracion.seguridad.service.UsuarioService;
import gob.dp.sid.comun.MEncript;
import gob.dp.sid.comun.SessionUtil;
import gob.dp.sid.comun.controller.AbstractManagedBean;
import java.io.Serializable;
import javax.faces.context.FacesContext;

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
public class LoginController extends AbstractManagedBean implements Serializable {

    private static final Logger log = Logger.getLogger(LoginController.class);

    private Usuario usuario;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuditoriaService auditoriaService;

    @Autowired
    private UsuarioLoginService usuarioLoginService;

    public String ingresarSistema() {
        try {
            if (usuario.getCodigo() != null) {
                usuario.setCodigo(usuario.getCodigo().toUpperCase());
            }
            FiltroUsuario filtro = new FiltroUsuario();
            filtro.setCodigo(usuario.getCodigo());
            filtro.setIncluirLstRol(true);
            filtro.setIncluirMapRol(true);
            filtro.setIncluirMapRecurso(true);
            UsuarioLogin login = new UsuarioLogin();
            login.setCodigo(usuario.getCodigo());
            String encPass = MEncript.getStringMessageDigest(usuario.getClave().trim());
            login.setClave(encPass);
            Integer val = usuarioLoginService.loginUsuario(login);
            if (val > 0) {
                Usuario objUsuario = usuarioService.consultarUsuario(filtro);
                if(objUsuario != null){
                    SessionUtil.setUsuario(objUsuario);
                    auditoriaService.auditar(ConstantesAuditoria.SEGURIDAD_LOGIN_CORRECTO, "Login correcto");
                    cargarMenu();
                    return "ingresarSistema";
                }
            } else {
                msg.messageAlert("El usuario o la contraseña no coinciden", null);
                auditoriaService.auditar(ConstantesAuditoria.SEGURIDAD_LOGIN_INCORRECTO, "Login incorrecto", usuario);
                return "login";
            }
        } catch (Exception ex) {
            log.error("Ocurrió un error", ex);
        }
        return null;
    }

    private void cargarMenu() {
        FacesContext context = FacesContext.getCurrentInstance();
        MenuController menuController = (MenuController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "menuController");
        menuController.cargarMenu();
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Usuario getUsuario() {
        if (this.usuario == null) {
            this.usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioSesion() {
        return SessionUtil.getUsuario();
    }
    
    public void cambiarImagen(String ruta) {
        getUsuarioSesion().setRuta(ruta);
    }

    public void setAuditoriaService(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }
}