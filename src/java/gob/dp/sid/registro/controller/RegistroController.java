/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.controller;

import gob.dp.sid.administracion.seguridad.controller.LoginController;
import gob.dp.sid.administracion.seguridad.entity.Usuario;
import gob.dp.sid.comun.controller.AbstractManagedBean;
import gob.dp.sid.comun.entity.FiltroParametro;
import gob.dp.sid.comun.entity.Parametro;
import gob.dp.sid.comun.service.CacheService;
import gob.dp.sid.comun.service.ParametroService;
import gob.dp.sid.registro.entity.Entidad;
import gob.dp.sid.registro.entity.Expediente;
import gob.dp.sid.registro.entity.Persona;
import gob.dp.sid.registro.service.EntidadService;
import gob.dp.sid.registro.service.ExpedienteService;
import gob.dp.sid.registro.service.PersonaService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author carlos
 */
@SessionScoped
@Named("registroController")
public class RegistroController extends AbstractManagedBean implements Serializable {

    private static final Logger log = Logger.getLogger(RegistroController.class);
    
    private Expediente expediente;
    
    private Persona persona;
    
    private Entidad entidad;
    
    private List<Persona> personasPopover;
    
    private List<Persona> personasSeleccionadas;
    
    private List<Entidad> entidadPopover;
    
    private List<Entidad> entidadSeleccionadas;
    
    private String cadenaPersonaPopover;
    
    private String cadenaEntidadPopover;
    
    private String cadenaEtiquetaAutocomplete;
    
    private List<Parametro> listaEtiquetasSeleccionadas;
    
    private List<Parametro> listaSubTemas;
    
    private Usuario usuarioSession;
    
    private List<Expediente> listaExpedientes;

    @Autowired
    private ExpedienteService expedienteService;
    
    @Autowired
    private PersonaService personaService;
    
    @Autowired
    private EntidadService entidadService;
    
    @Autowired
    private CacheService cacheService;
    
    @Autowired
    private ParametroService parametroService;
    
    public String cargarNuevoExpediente(){
        expediente = new Expediente();
        cadenaPersonaPopover = "";
        personasSeleccionadas = new ArrayList<>();
        cadenaEntidadPopover = "";
        entidadSeleccionadas = new ArrayList<>();
        cadenaEtiquetaAutocomplete = expedienteService.etiquetaBuscarAutocomplete();
        listaEtiquetasSeleccionadas = new ArrayList<>();
        usuarioSession();
        return "expedienteNuevo";
    }
    
    public String cargarNuevaPersona(){
        persona = new Persona();
        return "personaNuevo";
    }
    
    public String cargarNuevaEntidad(){
        entidad = new Entidad();
        return "entidadNuevo";
    }
    
    public String cargarExpedienteEdit(Expediente e){
        setExpediente(e);
        return "expedienteEdit";
    }
    
    public String cargarBusquedaExpediente(){
        listaExpedientes = expedienteService.expedienteBuscar(expediente);
        return "expedienteBusqueda";
    }
    
    private void usuarioSession(){
        usuarioSession = new Usuario();
        FacesContext context = FacesContext.getCurrentInstance();
        LoginController loginController = (LoginController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "loginController");
        usuarioSession = loginController.getUsuarioSesion();
    }
    
    public void cargarPopoverPersona(){
        personasPopover = personaService.personaBuscarCadena(cadenaPersonaPopover);
    }
    
    public void cargarPopoverEntidad(){
        entidadPopover = entidadService.entidadBuscarCadena(cadenaEntidadPopover);
    }
    
    public boolean addPersona(Persona p){
        for(Persona p1 : personasSeleccionadas){
            if(Objects.equals(p1.getId(), p.getId())){
                msg.messageAlert("Esta persona ya se encuentra seleccionada", null);
                cadenaPersonaPopover = null;
                return false;
            }
        }
        personasSeleccionadas.add(p);
        cadenaPersonaPopover = null;
        return true;
    }
    
    public void removePersona(Persona p){
        personasSeleccionadas.remove(p);
    }
    
    public boolean addEntidad(Entidad e){
        for(Entidad e1 : entidadSeleccionadas){
            if(Objects.equals(e1.getId(), e.getId())){
                msg.messageAlert("Esta Entidad ya se encuentra seleccionada", null);
                cadenaEntidadPopover = null;
                return false;
            }
        }
        entidadSeleccionadas.add(e);
        cadenaEntidadPopover = null;
        return false;
    }
    
    public void removeEntidad(Entidad e){
        entidadSeleccionadas.remove(e);
    }
   
    
    public void guardarBorrador(){
        expediente.setVersion(0);
        expediente.setEstado("A");
        if(expediente.getId() == null){
            expedienteService.expedienteInsertar(expediente);
            msg.messageInfo("Se registro el borrador del Expediente", null);
        }else{
            expedienteService.expedienteUpdate(expediente);
            msg.messageInfo("Se actualizo el borrador del Expediente", null);
        }
        
    }
    
    public void guardarVersion(){
        if(expediente.getId() == null || expediente.getVersion() == 0){
            expediente.setVersion(1);    
        }else{
            expediente.setVersion(expediente.getVersion()+1);
            expediente.setEstado("I");
            expedienteService.expedienteUpdate(expediente);
        }
        expediente.setEstado("A");
        expedienteService.expedienteInsertar(expediente);
        msg.messageInfo("Se genero la version "+expediente.getVersion()+" del Expediente", null);
    }
    
    public void guardarPersona(){
        personaService.personaInsertar(persona);
        msg.messageInfo("Se registro la Persona", null);
    }
    
    public void guardarEntidad(){
        entidadService.entidadInsertar(entidad);
        msg.messageInfo("Se registro la Entidad", null);
    }

    
    public boolean agregarEtiqueta(){
        if(stringUtil.isBlank(expediente.getIdEtiqueta())){
            msg.messageAlert("Debe de seleccionar una Etiqueta", null);
            return false;
        }
        for(Parametro p1 : listaEtiquetasSeleccionadas){
            if(p1.getValorParametro().equals(expediente.getIdEtiqueta())){
                expediente.setNombreEtiqueta(null);
                expediente.setIdEtiqueta(null);
                msg.messageAlert("La etiqueta seleccionada ya ha sido agregada", null);
                return false;
            }
        }
        Parametro p = new Parametro(expediente.getNombreEtiqueta(), expediente.getIdEtiqueta());
        listaEtiquetasSeleccionadas.add(p);
        expediente.setNombreEtiqueta(null);
        expediente.setIdEtiqueta(null);
        msg.messageInfo("Se agrego correctamente la etiqueta", null);
        return true;
    }
    
    public void removeEtiqueta(Parametro param){
        listaEtiquetasSeleccionadas.remove(param);
        msg.messageInfo("Se elimino la Etiqueta", null);
    }
    
    public void cargarSubTemas(){
        listaSubTemas = cacheService.buscarExpedienteSubTema(codigoParamentro());
    }
    
    private int codigoParamentro(){
        FiltroParametro filtro = new FiltroParametro();
        filtro.setCodigoPadreParametro(30);
        filtro.setValorParametro(expediente.getTipoTema());
        Parametro p = parametroService.consultarParametroValor(filtro);
        return p.getCodigoParametro();
    }
            
    
    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    public List<Persona> getPersonasPopover() {
        return personasPopover;
    }

    public void setPersonasPopover(List<Persona> personasPopover) {
        this.personasPopover = personasPopover;
    }

    public String getCadenaPersonaPopover() {
        return cadenaPersonaPopover;
    }

    public void setCadenaPersonaPopover(String cadenaPersonaPopover) {
        this.cadenaPersonaPopover = cadenaPersonaPopover;
    }

    public List<Persona> getPersonasSeleccionadas() {
        return personasSeleccionadas;
    }

    public void setPersonasSeleccionadas(List<Persona> personasSeleccionadas) {
        this.personasSeleccionadas = personasSeleccionadas;
    }

    public List<Entidad> getEntidadPopover() {
        return entidadPopover;
    }

    public void setEntidadPopover(List<Entidad> entidadPopover) {
        this.entidadPopover = entidadPopover;
    }

    public List<Entidad> getEntidadSeleccionadas() {
        return entidadSeleccionadas;
    }

    public void setEntidadSeleccionadas(List<Entidad> entidadSeleccionadas) {
        this.entidadSeleccionadas = entidadSeleccionadas;
    }

    public String getCadenaEntidadPopover() {
        return cadenaEntidadPopover;
    }

    public void setCadenaEntidadPopover(String cadenaEntidadPopover) {
        this.cadenaEntidadPopover = cadenaEntidadPopover;
    }

    public String getCadenaEtiquetaAutocomplete() {
        return cadenaEtiquetaAutocomplete;
    }

    public void setCadenaEtiquetaAutocomplete(String cadenaEtiquetaAutocomplete) {
        this.cadenaEtiquetaAutocomplete = cadenaEtiquetaAutocomplete;
    }

    public List<Parametro> getListaEtiquetasSeleccionadas() {
        return listaEtiquetasSeleccionadas;
    }

    public void setListaEtiquetasSeleccionadas(List<Parametro> listaEtiquetasSeleccionadas) {
        this.listaEtiquetasSeleccionadas = listaEtiquetasSeleccionadas;
    }

    public List<Parametro> getListaSubTemas() {
        return listaSubTemas;
    }

    public void setListaSubTemas(List<Parametro> listaSubTemas) {
        this.listaSubTemas = listaSubTemas;
    }

    public Usuario getUsuarioSession() {
        return usuarioSession;
    }

    public void setUsuarioSession(Usuario usuarioSession) {
        this.usuarioSession = usuarioSession;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Entidad getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }

    public List<Expediente> getListaExpedientes() {
        return listaExpedientes;
    }

    public void setListaExpedientes(List<Expediente> listaExpedientes) {
        this.listaExpedientes = listaExpedientes;
    }
    
    
}
