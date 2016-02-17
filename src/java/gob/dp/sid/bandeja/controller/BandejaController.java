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
import gob.dp.sid.registro.entity.ExpedienteConsulta;
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
    
    private List<Bandeja> listaMensajesPendientes;
    
    private List<Bandeja> listaMensajesPendientesInternos;
    
    private List<Bandeja> listaMensajesPendientesAutomaticos;
    
    private List<Bandeja> listaMensajesPendientesProgramados;
    
    private List<Bandeja> listaMensajesInternos;
    
    private List<Bandeja> listaMensajesAutomaticos;
    
    private List<Bandeja> listaMensajesProgramados;

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
    
    public void eliminarMensajes(){
        List<Bandeja> list = new ArrayList<>();
        for(Bandeja b : listaMensajes){
            if(b.getIndicador()){
                b.setActivo("I");
                bandejaService.mensajeInactivar(b.getId());
            }else{
                list.add(b);
            }
        }
        listaMensajes = list;
    }
    
    public void cargarMensajesPendientes(){
        usuarioSession();
        cargarMensajesInternos();
        cargarMensajesAutomaticos();
        cargarMensajesProgramados();
    }
    
    public String cargarMensajesInternos(){
        listaMensajesPendientesInternos = bandejaService.bandejaBuscarUsuarioPendientesInternos(usuarioSession.getCodigo());
        listaMensajesInternos = bandejaService.bandejaBuscarUsuarioInternos(usuarioSession.getCodigo());
        listaMensajes = listaMensajesPendientesInternos;
        return "bandeja";
    }

    public String cargarMensajesAutomaticos(){
        listaMensajesPendientesAutomaticos = bandejaService.bandejaBuscarUsuarioPendientesAutomaticos(usuarioSession.getCodigo());
        listaMensajesAutomaticos = bandejaService.bandejaBuscarUsuarioAutomaticos(usuarioSession.getCodigo());
        listaMensajes = listaMensajesPendientesAutomaticos;
        return "bandeja";
    }
    
    public String cargarMensajesProgramados(){
        listaMensajesPendientesProgramados = bandejaService.bandejaBuscarUsuarioPendientesProgramados(usuarioSession.getCodigo());
        listaMensajesProgramados = bandejaService.bandejaBuscarUsuarioProgramados(usuarioSession.getCodigo());
        listaMensajes = listaMensajesPendientesProgramados;
        return "bandeja";
    }
    
    public void cargarTotalInternos(){
        listaMensajesInternos = bandejaService.bandejaBuscarUsuarioInternos(usuarioSession.getCodigo());
        listaMensajes = listaMensajesInternos;
    }

    public void cargarTotalAutomaticos(){
        listaMensajesAutomaticos = bandejaService.bandejaBuscarUsuarioAutomaticos(usuarioSession.getCodigo());
        listaMensajes = listaMensajesAutomaticos;
    }
    
    public void cargarTotalProgramados(){
        listaMensajesProgramados = bandejaService.bandejaBuscarUsuarioProgramados(usuarioSession.getCodigo());
        listaMensajes = listaMensajesProgramados;
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
        usuarioSession.setRol(RolType.APROBADOR_OD.getKey());
        List<Usuario> listaDestinatarios = buscarDestinatarios(usuarioSession);
        guardarMensajeDerivacion(ed, listaDestinatarios, 0L);
    }
    
    public void mensajeEnviaConsulta(ExpedienteConsulta ec){
        usuarioSession();
        mensajeBandeja = new Bandeja();
        OficinaDefensorial of = oficinaDefensorialService.obtenerOficinaDefensorial(ec.getIdAdjuntiaDefensorial().longValue());
        mensajeBandeja.setTituloMensaje(MensajeType.MENSAJE_CONSULTA.getDetalle()+" exp: "+ec.getNumeroExpediente()+" a: "+of.getNombre());
        usuarioSession.setRol(RolType.APROBADOR_OD.getKey());
        List<Usuario> listaDestinatarios = buscarDestinatarios(usuarioSession);
        guardarMensajeConsulta(ec, listaDestinatarios, 0L);
    }
    
    public void mensajeEnviaConsultaAprobacion(ExpedienteConsulta ec){
        usuarioSession();
        mensajeBandeja = new Bandeja();
        OficinaDefensorial of = oficinaDefensorialService.obtenerOficinaDefensorial(ec.getIdAdjuntiaDefensorial().longValue());
        mensajeBandeja.setTituloMensaje(MensajeType.MENSAJE_CONSULTA.getDetalle()+" exp: "+ec.getNumeroExpediente()+" a: "+of.getNombre());
        usuarioSession.setRol(RolType.APROBADOR_AD.getKey());
        usuarioSession.setCodigoOD(ec.getIdAdjuntiaDefensorial());
        List<Usuario> listaDestinatarios = buscarDestinatarios(usuarioSession);
        guardarMensajeConsulta(ec, listaDestinatarios, 0L);
    }
    
    public void mensajeEnviaConsultaReasignacion(ExpedienteConsulta ec){
        usuarioSession();
        mensajeBandeja = new Bandeja();
        mensajeBandeja.setTituloMensaje(MensajeType.MENSAJE_CONSULTA.getDetalle()+" exp: "+ec.getNumeroExpediente());
        Usuario usuario = new Usuario();
        usuario.setCodigo(ec.getCodigoUsuarioReasignado());
        List<Usuario> listaDestinatarios = new ArrayList<>();
        listaDestinatarios.add(usuario);
        guardarMensajeConsulta(ec, listaDestinatarios, 0L);
    }
    
    public void mensajeEnviaConsultaResponde(ExpedienteConsulta ec){
        usuarioSession();
        mensajeBandeja = new Bandeja();
        mensajeBandeja.setTituloMensaje(MensajeType.MENSAJE_CONSULTA.getDetalle()+" exp: "+ec.getNumeroExpediente());
        Usuario usuario = new Usuario();
        usuario.setCodigo(ec.getCodigoUsuarioRetorno());
        List<Usuario> listaDestinatarios = new ArrayList<>();
        listaDestinatarios.add(usuario);
        guardarMensajeConsulta(ec, listaDestinatarios, 0L);
    }
    
    public void mensajeEnviaAprobarRespuesta(ExpedienteConsulta ec){
        usuarioSession();
        mensajeBandeja = new Bandeja();
        mensajeBandeja.setTituloMensaje(MensajeType.MENSAJE_CONSULTA.getDetalle()+" exp: "+ec.getNumeroExpediente());
        Usuario usuario = new Usuario();
        usuario.setCodigo(ec.getCodigoUsuarioRetorno());
        List<Usuario> listaDestinatarios = new ArrayList<>();
        listaDestinatarios.add(usuario);
        guardarMensajeConsulta(ec, listaDestinatarios, 0L);
    }
    
    public void mensajeEnviaAprobacion(ExpedienteDerivacion ed){
        mensajeBandeja = new Bandeja();
        OficinaDefensorial of = oficinaDefensorialService.obtenerOficinaDefensorial(ed.getIdOficinaDefensorial().longValue());
        mensajeBandeja.setTituloMensaje("Solicita: reasignación por "+MensajeType.MENSAJE_DERIVACION.getDetalle()+" exp: "+ed.getNumeroExpediente()+" a: "+of.getNombre());
        usuarioSession.setRol(RolType.APROBADOR_OD.getKey());
        usuarioSession.setCodigoOD(ed.getIdOficinaDefensorial());
        List<Usuario> listaDestinatarios = buscarDestinatarios(usuarioSession);
        guardarMensajeDerivacion(ed, listaDestinatarios, 0L);
    }
    
    public void mensajeEnviaDesaprobacion(ExpedienteDerivacion ed, Expediente e){
        mensajeBandeja = new Bandeja();
        mensajeBandeja.setTituloMensaje("No se aprueba la "+MensajeType.MENSAJE_DERIVACION.getDetalle()+" exp: "+ed.getNumeroExpediente());
        usuarioSession.setRol(RolType.APROBADOR_OD.getKey());
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
    
    private void guardarMensajeConsulta(ExpedienteConsulta ec, List<Usuario> usuarios, Long idExp) {
        for(Usuario u : usuarios){
            mensajeBandeja.setDestinatario(u.getCodigo());
            mensajeBandeja.setEstado("PEN");
            mensajeBandeja.setFechaEnvio(new Date());
            mensajeBandeja.setRemitente(usuarioSession.getCodigo());
            mensajeBandeja.setTipo(MensajeType.MENSAJE_CONSULTA.getKey());
            mensajeBandeja.setTitulo(MensajeType.MENSAJE_CONSULTA.getValue());
            mensajeBandeja.setCodigoTipo(ec.getId());
            mensajeBandeja.setNombreRemitente(usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno());
            mensajeBandeja.setDetalleTipo(MensajeType.MENSAJE_CONSULTA.getDetalle());
            mensajeBandeja.setColorTipo(MensajeType.MENSAJE_CONSULTA.getColor());
            mensajeBandeja.setMotivo(ec.getDetalle());
            mensajeBandeja.setNumeroExpediente(ec.getNumeroExpediente());
            mensajeBandeja.setCodigoConsulta(ec.getCodigo());
            mensajeBandeja.setTipoMensaje("INT");
            mensajeBandeja.setActivo("A");
            if(idExp > 0)
                mensajeBandeja.setIdExpediente(idExp);
            else
                mensajeBandeja.setIdExpediente(ec.getIdExpediente());
            mensajeBandeja.setIdAccion(ec.getId());
            bandejaService.bandejaInsertar(mensajeBandeja);
        }
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
            mensajeBandeja.setTipoMensaje("INT");
            mensajeBandeja.setActivo("A");
            if(idExp > 0)
                mensajeBandeja.setIdExpediente(idExp);
            else
                mensajeBandeja.setIdExpediente(ed.getIdExpediente());
            mensajeBandeja.setIdAccion(ed.getId());
            bandejaService.bandejaInsertar(mensajeBandeja);
        }
    }

    public void guardarMensajeBandejaPorReasignacion(ExpedienteDerivacion ed) {
        usuarioSession();
        usuarioSession.setRol(RolType.APROBADOR_OD.getKey());
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
        mensajeBandeja.setTipoMensaje("INT");
        mensajeBandeja.setActivo("A");
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
    
    public String cargarExpedientePorDeriva(){
        return cargarDerivacionPorId();
    }
    
    public String cargarExpedientePorMensa(){
        return cargarMensajePorNumero();
    }
    
    private String cargarDerivacionPorId() {
        FacesContext context = FacesContext.getCurrentInstance();
        RegistroController registroController = (RegistroController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "registroController");
        registroController.cargarExpedientePorId(mensajeBandeja.getIdExpediente());
        return registroController.inicioAccionesDerivacion();
    }
    
    private String cargarMensajePorNumero() {
        FacesContext context = FacesContext.getCurrentInstance();
        RegistroController registroController = (RegistroController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "registroController");
        registroController.cargarExpedientePorNumero(mensajeBandeja.getNumeroExpediente());
        return registroController.inicioAccionesConsultaPublic();
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

    public List<Bandeja> getListaMensajesPendientesInternos() {
        return listaMensajesPendientesInternos;
    }

    public void setListaMensajesPendientesInternos(List<Bandeja> listaMensajesPendientesInternos) {
        this.listaMensajesPendientesInternos = listaMensajesPendientesInternos;
    }

    public List<Bandeja> getListaMensajesPendientesAutomaticos() {
        return listaMensajesPendientesAutomaticos;
    }

    public void setListaMensajesPendientesAutomaticos(List<Bandeja> listaMensajesPendientesAutomaticos) {
        this.listaMensajesPendientesAutomaticos = listaMensajesPendientesAutomaticos;
    }

    public List<Bandeja> getListaMensajesPendientes() {
        return listaMensajesPendientes;
    }

    public void setListaMensajesPendientes(List<Bandeja> listaMensajesPendientes) {
        this.listaMensajesPendientes = listaMensajesPendientes;
    }

    public List<Bandeja> getListaMensajesInternos() {
        return listaMensajesInternos;
    }

    public void setListaMensajesInternos(List<Bandeja> listaMensajesInternos) {
        this.listaMensajesInternos = listaMensajesInternos;
    }

    public List<Bandeja> getListaMensajesAutomaticos() {
        return listaMensajesAutomaticos;
    }

    public void setListaMensajesAutomaticos(List<Bandeja> listaMensajesAutomaticos) {
        this.listaMensajesAutomaticos = listaMensajesAutomaticos;
    }

    public List<Bandeja> getListaMensajesPendientesProgramados() {
        return listaMensajesPendientesProgramados;
    }

    public void setListaMensajesPendientesProgramados(List<Bandeja> listaMensajesPendientesProgramados) {
        this.listaMensajesPendientesProgramados = listaMensajesPendientesProgramados;
    }

    public List<Bandeja> getListaMensajesProgramados() {
        return listaMensajesProgramados;
    }

    public void setListaMensajesProgramados(List<Bandeja> listaMensajesProgramados) {
        this.listaMensajesProgramados = listaMensajesProgramados;
    }

    

}
