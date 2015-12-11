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
import gob.dp.sid.registro.controller.RegistroController;
import gob.dp.sid.registro.entity.ExpedienteDerivacion;
import gob.dp.sid.registro.entity.OficinaDefensorial;
import gob.dp.sid.registro.service.OficinaDefensorialService;
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
    
    private Bandeja mensajeBandeja;
    
    @Autowired
    private BandejaService bandejaService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private OficinaDefensorialService oficinaDefensorialService;
    
    
    public String cargarBandeja(){
        usuarioSession();
        listaMensajes = bandejaService.bandejaBuscarUsuario(usuarioSession.getCodigo());
        return "bandeja";
    } 
    
    public String verMensajeBandeja(Bandeja b){
        setMensajeBandeja(b);
        return "verMensaje";
    }
    
    public void guardarMensajeBandejaPorDerivacion(ExpedienteDerivacion ed){
        usuarioSession();
        usuarioSession.setRol(RolType.DERIVADOR_OD.getKey());
        List<Usuario> listaDestinatarios = buscarDestinatarios(usuarioSession);
        for(Usuario u : listaDestinatarios){
            mensajeBandeja = new Bandeja();
            mensajeBandeja.setDestinatario(u.getCodigo());
            mensajeBandeja.setEstado("ACT");
            mensajeBandeja.setFechaEnvio(new Date());
            mensajeBandeja.setRemitente(usuarioSession.getCodigo());
            mensajeBandeja.setTipo(MensajeType.MENSAJE_DERIVACION.getKey());
            mensajeBandeja.setTitulo(MensajeType.MENSAJE_DERIVACION.getValue());
            mensajeBandeja.setCodigoTipo(ed.getId());
            mensajeBandeja.setNombreRemitente(usuarioSession.getNombre()+" "+usuarioSession.getApellidoPaterno()+" "+usuarioSession.getApellidoMaterno());
            mensajeBandeja.setDetalleTipo(MensajeType.MENSAJE_DERIVACION.getDetalle());
            mensajeBandeja.setColorTipo(MensajeType.MENSAJE_DERIVACION.getColor());
            mensajeBandeja.setMotivo(ed.getDetalle());
            mensajeBandeja.setNumeroExpediente(ed.getNumeroExpediente());
            mensajeBandeja.setIdExpediente(ed.getIdExpediente());
            mensajeBandeja.setIdAccion(ed.getId());
            OficinaDefensorial of = oficinaDefensorialService.obtenerOficinaDefensorial(ed.getIdOficinaDefensorial().longValue());
            mensajeBandeja.setOficinaDefensorial(of.getNombre());
            bandejaService.bandejaInsertar(mensajeBandeja);
        }
    }
    
    private void usuarioSession() {
        usuarioSession = new Usuario();
        FacesContext context = FacesContext.getCurrentInstance();
        LoginController loginController = (LoginController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "loginController");
        usuarioSession = loginController.getUsuarioSesion();
    }
    
    public String cargarExpedientePorId() {
        FacesContext context = FacesContext.getCurrentInstance();
        RegistroController registroController = (RegistroController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "registroController");
        return registroController.cargarExpedientePorId(mensajeBandeja.getIdExpediente());
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

    public Bandeja getMensajeBandeja() {
        return mensajeBandeja;
    }

    public void setMensajeBandeja(Bandeja mensajeBandeja) {
        this.mensajeBandeja = mensajeBandeja;
    }
    
}
