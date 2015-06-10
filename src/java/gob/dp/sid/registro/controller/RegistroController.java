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
import gob.dp.sid.registro.entity.ExpedienteEntidad;
import gob.dp.sid.registro.entity.ExpedientePersona;
import gob.dp.sid.registro.entity.Persona;
import gob.dp.sid.registro.service.EntidadService;
import gob.dp.sid.registro.service.ExpedienteEntidadService;
import gob.dp.sid.registro.service.ExpedientePersonaService;
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
    
    private List<ExpedientePersona> personasSeleccionadas;
    
    private List<Entidad> entidadPopover;
    
    private List<ExpedienteEntidad> entidadSeleccionadas;
    
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
    
    @Autowired
    private ExpedientePersonaService expedientePersonaService;
    
    @Autowired
    private ExpedienteEntidadService expedienteEntidadService;

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
        cargarSubTemas();
        cargarEtiquetas();
        cargarPersonasEntidades();
        return "expedienteEdit";
    }
    
    private void cargarPersonasEntidades(){
        personasSeleccionadas = expedientePersonaService.expedientePersonaBuscarXExpediente(expediente.getId());
        entidadSeleccionadas = expedienteEntidadService.expedienteEntidadBuscarXExpediente(expediente.getId());
    }
    
    private void cargarEtiquetas(){
        if(!stringUtil.isBlank(expediente.getEtiqueta())){
            String[] adArray = expediente.getEtiqueta().split(",");
            for (String adArray1 : adArray) {
                Parametro p = codigoParamentro(60, adArray1);
                listaEtiquetasSeleccionadas.add(p);
            }
        }
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
        for(ExpedientePersona p1 : personasSeleccionadas){
            if(Objects.equals(p1.getPersona().getId(), p.getId())){
                msg.messageAlert("Esta persona ya se encuentra seleccionada", null);
                cadenaPersonaPopover = null;
                return false;
            }
        }
        ExpedientePersona ep = new ExpedientePersona(expediente, p);
        personasSeleccionadas.add(ep);
        cadenaPersonaPopover = null;
        return true;
    }
    
    public void removePersona(ExpedientePersona ep){
        personasSeleccionadas.remove(ep);
        int count = expedientePersonaService.expedientePersonaContar(ep);
        if(count > 0)
            expedientePersonaService.expedientePersonaDelete(ep);
        msg.messageInfo("Se elimino el registo de la persona", null);
    }
    
    public boolean addEntidad(Entidad e){
        for(ExpedienteEntidad e1 : entidadSeleccionadas){
            if(Objects.equals(e1.getEntidad().getId(), e.getId())){
                msg.messageAlert("Esta Entidad ya se encuentra seleccionada", null);
                cadenaEntidadPopover = null;
                return false;
            }
        }
        ExpedienteEntidad ee = new ExpedienteEntidad(expediente, e);
        entidadSeleccionadas.add(ee);
        cadenaEntidadPopover = null;
        return false;
    }
    
    public void removeEntidad(ExpedienteEntidad e){
        entidadSeleccionadas.remove(e);
        int count = expedienteEntidadService.expedienteEntidadContar(e);
        if(count > 0)
            expedienteEntidadService.expedienteEntidadDelete(e);
        msg.messageInfo("Se elimino el registo de la Entidad", null);
    }
   
    
    public void guardarBorrador(){
        expediente.setVersion(0);
        expediente.setEstado("A");
        expediente.setEtiqueta(encadenarEtiquetas());
        if(expediente.getId() == null){
            expedienteService.expedienteInsertar(expediente);
            insertUpdateListasPersonaEntidad();
            msg.messageInfo("Se registro el borrador del Expediente", null);
        }else{
            expedienteService.expedienteUpdate(expediente);
            insertUpdateListasPersonaEntidad();
            msg.messageInfo("Se actualizo el borrador del Expediente", null);
        }
    }
    
    private void insertUpdateListasPersonaEntidad(){
        for(ExpedientePersona p : personasSeleccionadas){
            p.setExpediente(expediente);
            insertUpdateExpedientePersona(p);
        }
        
        for(ExpedienteEntidad e : entidadSeleccionadas){
            e.setExpediente(expediente);
            insertUpdateExpedienteEntidad(e);
        }
    }
    
    private void insertListasPersonaEntidad(){
        for(ExpedientePersona p : personasSeleccionadas){
            p.setExpediente(expediente);
            expedientePersonaService.expedientePersonaInsertar(p);
        }
        
        for(ExpedienteEntidad e : entidadSeleccionadas){
            e.setExpediente(expediente);
            expedienteEntidadService.expedienteEntidadInsertar(e);
        }
    }
    
    public void guardarVersion(){
        expediente.setEtiqueta(encadenarEtiquetas());
        if(expediente.getId() == null || expediente.getVersion() == 0){
            expediente.setVersion(1);    
        }else{
            expediente.setVersion(expediente.getVersion()+1);
            expediente.setEstado("I");
            expedienteService.expedienteUpdate(expediente);
        }
        expediente.setEstado("A");
        expedienteService.expedienteInsertar(expediente);
        insertListasPersonaEntidad();
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
    
    public void insertUpdateExpedientePersona(ExpedientePersona ep){
        try {
            if(ep.getExpediente().getId() != null && ep.getPersona().getId() != null){
            int contador = expedientePersonaService.expedientePersonaContar(ep);
            if(contador == 0)
                expedientePersonaService.expedientePersonaInsertar(ep);
            else
                expedientePersonaService.expedientePersonaUpdate(ep);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
    
    public void insertUpdateExpedienteEntidad(ExpedienteEntidad ee){
        try {
            if (ee.getExpediente().getId() != null && ee.getEntidad().getId() != null) {
                int contador = expedienteEntidadService.expedienteEntidadContar(ee);
                if (contador == 0) {
                    expedienteEntidadService.expedienteEntidadInsertar(ee);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        
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
        listaSubTemas = cacheService.buscarExpedienteSubTema(codigoParamentro(30));
    }
    
    private int codigoParamentro(int codigo){
        FiltroParametro filtro = new FiltroParametro();
        filtro.setCodigoPadreParametro(codigo);
        filtro.setValorParametro(expediente.getTipoTema());
        Parametro p = parametroService.consultarParametroValor(filtro);
        return p.getCodigoParametro();
    }
    
    private Parametro codigoParamentro(int codigo, String valor){
        FiltroParametro filtro = new FiltroParametro();
        filtro.setCodigoPadreParametro(codigo);
        filtro.setValorParametro(valor);
        Parametro p = parametroService.consultarParametroValor(filtro);
        return p;
    }
    
    private String encadenarEtiquetas(){
        StringBuilder sb = new StringBuilder();
        sb.append("");
        for(Parametro p : listaEtiquetasSeleccionadas){
            sb.append(p.getValorParametro()).append(",");
        }
        return sb.toString();
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

    public List<ExpedientePersona> getPersonasSeleccionadas() {
        return personasSeleccionadas;
    }

    public void setPersonasSeleccionadas(List<ExpedientePersona> personasSeleccionadas) {
        this.personasSeleccionadas = personasSeleccionadas;
    }

    public List<Entidad> getEntidadPopover() {
        return entidadPopover;
    }

    public void setEntidadPopover(List<Entidad> entidadPopover) {
        this.entidadPopover = entidadPopover;
    }

    public List<ExpedienteEntidad> getEntidadSeleccionadas() {
        return entidadSeleccionadas;
    }

    public void setEntidadSeleccionadas(List<ExpedienteEntidad> entidadSeleccionadas) {
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
