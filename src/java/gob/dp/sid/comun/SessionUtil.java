/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.comun;

import gob.dp.sid.administracion.seguridad.entity.Usuario;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author WIN7
 */
public class SessionUtil {
    
    public static HttpSession getHttpSession() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        return httpSession;
    }

    public static Usuario getUsuario() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null) {
            Usuario usuario = (Usuario) httpSession.getAttribute("usuario");
            if (usuario != null) {
                return usuario;
            }
        }
        return null;
    }

    public static void setUsuario(Usuario usuario) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(true);
        usuario.setIp(request.getRemoteAddr());
        httpSession.setAttribute("usuario", usuario);
    }

}
