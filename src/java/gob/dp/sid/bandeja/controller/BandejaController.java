/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.bandeja.controller;

import gob.dp.sid.administracion.seguridad.controller.LoginController;
import gob.dp.sid.administracion.seguridad.entity.Usuario;
import gob.dp.sid.administracion.seguridad.service.UsuarioService;
import gob.dp.sid.bandeja.entity.Bandeja;
import gob.dp.sid.bandeja.service.BandejaService;
import gob.dp.sid.comun.controller.AbstractManagedBean;
import gob.dp.sid.comun.type.MensajeType;
import gob.dp.sid.comun.type.RolType;
import gob.dp.sid.registro.entity.ExpedienteDerivacion;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author carlos
 */
@Named
@Scope("session")
public class BandejaController extends AbstractManagedBean implements Serializable {

    private static final Logger log = Logger.getLogger(BandejaController.class);
    
    private Usuario usuarioSession;
    
    private List<Bandeja> listaMensajes;
    
    @Autowired
    private BandejaService bandejaService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    
    public String cargarBandeja(){
        usuarioSession();
        listaMensajes = bandejaService.bandejaBuscarUsuario(usuarioSession.getCodigo());
        return "bandeja";
    } 
    
    public void guardarMensajeBandejaPorDerivacion(ExpedienteDerivacion ed){
        usuarioSession();
        usuarioSession.setRol(RolType.DERIVADOR_OD.getKey());
        List<Usuario> listaDestinatarios = buscarDestinatarios(usuarioSession);
        for(Usuario u : listaDestinatarios){
            Bandeja b = new Bandeja();
            b.setDestinatario(u.getCodigo());
            b.setEstado("ACT");
            b.setFechaEnvio(new Date());
            b.setRemitente(usuarioSession.getCodigo());
            b.setTipo(MensajeType.MENSAJE_DERIVACION.getKey());
            b.setTitulo(MensajeType.MENSAJE_DERIVACION.getValue());
            b.setCodigoTipo(ed.getId());
            b.setNombreRemitente(usuarioSession.getNombre()+" "+usuarioSession.getApellidoPaterno()+" "+usuarioSession.getApellidoMaterno());
            b.setDetalleTipo(MensajeType.MENSAJE_DERIVACION.getDetalle());
            b.setColorTipo(MensajeType.MENSAJE_DERIVACION.getColor());
            bandejaService.bandejaInsertar(b);
        }
    }
    
    private void usuarioSession() {
        usuarioSession = new Usuario();
        FacesContext context = FacesContext.getCurrentInstance();
        LoginController loginController = (LoginController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "loginController");
        usuarioSession = loginController.getUsuarioSesion();
    }
    
    private List<Usuario> buscarDestinatarios(Usuario u){
        List<Usuario> list = usuarioService.listaUsuariosPorOD(u);
        return list;
    }


    public Usuario getUsuarioSession() {
        return usuarioSession;
    }

    public void setUsuarioSession(Usuario usuarioSession) {
        this.usuarioSession = usuarioSession;
    }

    public List<Bandeja> getListaMensajes() {
        return listaMensajes;
    }

    public void setListaMensajes(List<Bandeja> listaMensajes) {
        this.listaMensajes = listaMensajes;
    }
    
    
    
}
