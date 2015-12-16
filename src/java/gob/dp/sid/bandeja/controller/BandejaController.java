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
import gob.dp.sid.registro.entity.Expediente;
import gob.dp.sid.registro.entity.ExpedienteDerivacion;
import gob.dp.sid.registro.entity.OficinaDefensorial;
import gob.dp.sid.registro.service.OficinaDefensorialService;
import java.io.Serializable;
import java.util.ArrayList;
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

    public String cargarBandeja() {
        usuarioSession();
        listaMensajes = bandejaService.bandejaBuscarUsuario(usuarioSession.getCodigo());
        return "bandeja";
    }

    public String verMensajeBandeja(Bandeja b) {
        setMensajeBandeja(b);
        b.setEstado("VIS");
        bandejaService.mensajeEstadoVisto(b.getId());
        return "verMensaje";
    }
    
    public void mensajeEnviaDerivacion(ExpedienteDerivacion ed){
        usuarioSession();
        mensajeBandeja = new Bandeja();
        OficinaDefensorial of = oficinaDefensorialService.obtenerOficinaDefensorial(ed.getIdOficinaDefensorial().longValue());
        mensajeBandeja.setTituloMensaje("Solicita: aprobación de "+MensajeType.MENSAJE_DERIVACION.getDetalle()+" exp: "+ed.getNumeroExpediente()+" a: "+of.getNombre());
        usuarioSession.setRol(RolType.DERIVADOR_OD.getKey());
        List<Usuario> listaDestinatarios = buscarDestinatarios(usuarioSession);
        guardarMensajeDerivacion(ed, listaDestinatarios, 0L);
    }
    
    public void mensajeEnviaAprobacion(ExpedienteDerivacion ed){
        mensajeBandeja = new Bandeja();
        OficinaDefensorial of = oficinaDefensorialService.obtenerOficinaDefensorial(ed.getIdOficinaDefensorial().longValue());
        mensajeBandeja.setTituloMensaje("Solicita: reasignación por "+MensajeType.MENSAJE_DERIVACION.getDetalle()+" exp: "+ed.getNumeroExpediente()+" a: "+of.getNombre());
        usuarioSession.setRol(RolType.DERIVADOR_OD.getKey());
        usuarioSession.setCodigoOD(ed.getIdOficinaDefensorial());
        List<Usuario> listaDestinatarios = buscarDestinatarios(usuarioSession);
        guardarMensajeDerivacion(ed, listaDestinatarios, 0L);
    }
    
    public void mensajeEnviaDesaprobacion(ExpedienteDerivacion ed, Expediente e){
        mensajeBandeja = new Bandeja();
        OficinaDefensorial of = oficinaDefensorialService.obtenerOficinaDefensorial(ed.getIdOficinaDefensorial().longValue());
        mensajeBandeja.setTituloMensaje("No se aprueba la "+MensajeType.MENSAJE_DERIVACION.getDetalle()+" exp: "+ed.getNumeroExpediente()+" a: "+of.getNombre());
        usuarioSession.setRol(RolType.DERIVADOR_OD.getKey());
        List<Usuario> listaDestinatarios = new ArrayList<>();
        Usuario usu = new Usuario();
        usu.setCodigo(e.getUsuarioRegistro());
        listaDestinatarios.add(usu);
        guardarMensajeDerivacion(ed, listaDestinatarios, e.getId());
    }
    
    public void mensajeEnviaReasignacion(ExpedienteDerivacion ed, Expediente e){
        mensajeBandeja = new Bandeja();
        mensajeBandeja.setTituloMensaje("Se reasigna el exp: "+ed.getNumeroExpediente());
        List<Usuario> listaDestinatarios = new ArrayList<>();
        Usuario u = new Usuario();
        u.setCodigo(ed.getCodigoUsuarioDerivado());
        listaDestinatarios.add(u);
        guardarMensajeDerivacion(ed, listaDestinatarios, e.getId());
    }
    
    public void mensajeEnviaReasignacionDesaprobada(ExpedienteDerivacion ed, String codigoUsuarioRetorno, Expediente e){
        mensajeBandeja = new Bandeja();
        mensajeBandeja.setTituloMensaje("Se rechaza la reasignación por derivación del exp: "+ed.getNumeroExpediente());
        List<Usuario> listaDestinatarios = new ArrayList<>();
        Usuario u = new Usuario();
        u.setCodigo(codigoUsuarioRetorno);
        listaDestinatarios.add(u);
        guardarMensajeDerivacion(ed, listaDestinatarios, e.getId());
    }
    

    private void guardarMensajeDerivacion(ExpedienteDerivacion ed, List<Usuario> usuarios, Long idExp) {
        for(Usuario u : usuarios){
            mensajeBandeja.setDestinatario(u.getCodigo());
            mensajeBandeja.setEstado("PEN");
            mensajeBandeja.setFechaEnvio(new Date());
            mensajeBandeja.setRemitente(usuarioSession.getCodigo());
            mensajeBandeja.setTipo(MensajeType.MENSAJE_DERIVACION.getKey());
            mensajeBandeja.setTitulo(MensajeType.MENSAJE_DERIVACION.getValue());
            mensajeBandeja.setCodigoTipo(ed.getId());
            mensajeBandeja.setNombreRemitente(usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno());
            mensajeBandeja.setDetalleTipo(MensajeType.MENSAJE_DERIVACION.getDetalle());
            mensajeBandeja.setColorTipo(MensajeType.MENSAJE_DERIVACION.getColor());
            mensajeBandeja.setMotivo(ed.getDetalle());
            mensajeBandeja.setNumeroExpediente(ed.getNumeroExpediente());
            if(idExp > 0)
                mensajeBandeja.setIdExpediente(idExp);
            else
                mensajeBandeja.setIdExpediente(ed.getIdExpediente());
            mensajeBandeja.setIdAccion(ed.getId());
            bandejaService.bandejaInsertar(mensajeBandeja);
        }
    }

    /*private void guardarMensajeBandejaPorDerivacion(ExpedienteDerivacion ed, int tip) {
        usuarioSession();
        if (tip == 2) {
            usuarioSession.setCodigoOD(ed.getIdOficinaDefensorial());
        }
        usuarioSession.setRol(RolType.DERIVADOR_OD.getKey());
        List<Usuario> listaDestinatarios = buscarDestinatarios(usuarioSession);
        for (Usuario u : listaDestinatarios) {
            mensajeBandeja = new Bandeja();
            mensajeBandeja.setDestinatario(u.getCodigo());
            mensajeBandeja.setEstado("PEN");
            mensajeBandeja.setFechaEnvio(new Date());
            mensajeBandeja.setRemitente(usuarioSession.getCodigo());
            mensajeBandeja.setTipo(MensajeType.MENSAJE_DERIVACION.getKey());
            mensajeBandeja.setTitulo(MensajeType.MENSAJE_DERIVACION.getValue());
            mensajeBandeja.setCodigoTipo(ed.getId());
            mensajeBandeja.setNombreRemitente(usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno());
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
    }*/

    public void guardarMensajeBandejaPorReasignacion(ExpedienteDerivacion ed) {
        usuarioSession();
        usuarioSession.setRol(RolType.DERIVADOR_OD.getKey());
        mensajeBandeja = new Bandeja();
        mensajeBandeja.setDestinatario(ed.getCodigoUsuarioDerivado());
        mensajeBandeja.setEstado("PEN");
        mensajeBandeja.setFechaEnvio(new Date());
        mensajeBandeja.setRemitente(usuarioSession.getCodigo());
        mensajeBandeja.setTipo(MensajeType.MENSAJE_DERIVACION.getKey());
        mensajeBandeja.setTitulo(MensajeType.MENSAJE_DERIVACION.getValue());
        mensajeBandeja.setCodigoTipo(ed.getId());
        mensajeBandeja.setNombreRemitente(usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno());
        mensajeBandeja.setDetalleTipo(MensajeType.MENSAJE_DERIVACION.getDetalle());
        mensajeBandeja.setColorTipo(MensajeType.MENSAJE_DERIVACION.getColor());
        mensajeBandeja.setMotivo(ed.getDetalle());
        mensajeBandeja.setNumeroExpediente(ed.getNumeroExpediente());
        mensajeBandeja.setIdExpediente(ed.getIdExpediente());
        mensajeBandeja.setIdAccion(ed.getId());
        bandejaService.bandejaInsertar(mensajeBandeja);
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
    
    public String cargarDerivacionPorId() {
        FacesContext context = FacesContext.getCurrentInstance();
        RegistroController registroController = (RegistroController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "registroController");
        registroController.cargarExpedientePorId(mensajeBandeja.getIdExpediente());
        return registroController.inicioAccionesDerivacion();
    }

    private List<Usuario> buscarDestinatarios(Usuario u) {
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
