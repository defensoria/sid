/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.controller;

import gob.dp.sid.administracion.seguridad.controller.LoginController;
import gob.dp.sid.administracion.seguridad.controller.MenuController;
import gob.dp.sid.administracion.seguridad.controller.SeguridadUtilController;
import gob.dp.sid.administracion.seguridad.entity.Usuario;
import gob.dp.sid.administracion.seguridad.service.UsuarioService;
import gob.dp.sid.bandeja.controller.BandejaController;
import gob.dp.sid.bandeja.entity.Bandeja;
import gob.dp.sid.bandeja.service.BandejaService;
import gob.dp.sid.comun.ConstantesUtil;
import gob.dp.sid.comun.ListadoClasificacion;
import gob.dp.sid.comun.controller.AbstractManagedBean;
import gob.dp.sid.comun.entity.Distrito;
import gob.dp.sid.comun.entity.FiltroParametro;
import gob.dp.sid.comun.entity.Parametro;
import gob.dp.sid.comun.entity.Provincia;
import gob.dp.sid.comun.service.ParametroService;
import gob.dp.sid.comun.service.UbigeoService;
import gob.dp.sid.comun.type.EstadoExpedienteType;
import gob.dp.sid.comun.type.EtapaAmpliacionType;
import gob.dp.sid.comun.type.EtapaConsultaType;
import gob.dp.sid.comun.type.EtapaDerivacionType;
import gob.dp.sid.comun.type.EtapaSuspencionType;
import gob.dp.sid.comun.type.EtapaType;
import gob.dp.sid.comun.type.ExpedienteType;
import gob.dp.sid.comun.type.HistorialType;
import gob.dp.sid.comun.type.MensajeType;
import gob.dp.sid.comun.type.RolType;
import gob.dp.sid.registro.entity.Entidad;
import gob.dp.sid.registro.entity.EtapaEstado;
import gob.dp.sid.registro.entity.Expediente;
import gob.dp.sid.registro.entity.ExpedienteAmpliacion;
import gob.dp.sid.registro.entity.ExpedienteClasiTipo;
import gob.dp.sid.registro.entity.ExpedienteClasificacion;
import gob.dp.sid.registro.entity.ExpedienteClasificacionTipo;
import gob.dp.sid.registro.entity.ExpedienteConsulta;
import gob.dp.sid.registro.entity.ExpedienteDerivacion;
import gob.dp.sid.registro.entity.ExpedienteEntidad;
import gob.dp.sid.registro.entity.ExpedienteEtapa;
import gob.dp.sid.registro.entity.ExpedienteFormularioVirtual;
import gob.dp.sid.registro.entity.ExpedienteGestion;
import gob.dp.sid.registro.entity.ExpedienteHistorial;
import gob.dp.sid.registro.entity.ExpedienteNivel;
import gob.dp.sid.registro.entity.ExpedienteONP;
import gob.dp.sid.registro.entity.ExpedientePersona;
import gob.dp.sid.registro.entity.ExpedienteSuspencion;
import gob.dp.sid.registro.entity.ExpedienteTiempo;
import gob.dp.sid.registro.entity.GestionEtapa;
import gob.dp.sid.registro.entity.OficinaDefensorial;
import gob.dp.sid.registro.entity.Persona;
import gob.dp.sid.registro.service.EntidadService;
import gob.dp.sid.registro.service.EtapaEstadoService;
import gob.dp.sid.registro.service.ExpedienteAmpliacionService;
import gob.dp.sid.registro.service.ExpedienteClasiTipoService;
import gob.dp.sid.registro.service.ExpedienteClasificacionService;
import gob.dp.sid.registro.service.ExpedienteClasificacionTipoService;
import gob.dp.sid.registro.service.ExpedienteConsultaService;
import gob.dp.sid.registro.service.ExpedienteDerivacionService;
import gob.dp.sid.registro.service.ExpedienteEntidadService;
import gob.dp.sid.registro.service.ExpedienteEtapaService;
import gob.dp.sid.registro.service.ExpedienteFormularioVirtualService;
import gob.dp.sid.registro.service.ExpedienteGestionService;
import gob.dp.sid.registro.service.ExpedienteHistorialService;
import gob.dp.sid.registro.service.ExpedienteNivelService;
import gob.dp.sid.registro.service.ExpedienteONPService;
import gob.dp.sid.registro.service.ExpedientePersonaService;
import gob.dp.sid.registro.service.ExpedienteService;
import gob.dp.sid.registro.service.ExpedienteSuspencionService;
import gob.dp.sid.registro.service.ExpedienteTiempoService;
import gob.dp.sid.registro.service.GestionEtapaService;
import gob.dp.sid.registro.service.OficinaDefensorialService;
import gob.dp.sid.registro.service.PersonaService;
import gob.dp.sid.reporte.entity.ExpedienteFicha;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import pe.gob.defensoria.wsdl.service.ServiceReniec;

/**
 *
 * @author carlos
 */
@Named
@Scope("session")
public class RegistroController extends AbstractManagedBean implements Serializable {

    private static final Logger log = Logger.getLogger(RegistroController.class);

    private Expediente expediente;

    private Expediente expedienteBusquedaReplica;

    private ExpedienteGestion expedienteGestion;

    private Persona persona;

    private Persona personaBusqueda;

    private Persona personaSeleccionada;

    private ExpedientePersona expedientepersonaModalEdit;

    private Entidad entidad;

    private List<Persona> personasPopover;

    private List<ExpedientePersona> personasSeleccionadas;

    private List<Entidad> entidadPopover;

    private List<ExpedienteEntidad> entidadSeleccionadas;

    private List<SelectItem> listaProvincia;

    private List<SelectItem> listaDistrito;

    private String cadenaPersonaPopover;

    private String cadenaEntidadPopover;

    private String cadenaEtiquetaAutocomplete;

    private List<Parametro> listaEtiquetasSeleccionadas;

    private List<Parametro> listaSubTemas;

    private Usuario usuarioSession;

    private List<Expediente> listaExpedientes;

    private List<Expediente> listaExpedienteXUsuario;

    private List<Expediente> listaExpedienteXUsuarioPaginado;

    private List<Expediente> listaExpedienteXUsuarioPaginadoReplica;

    private List<Expediente> listaExpedienteXPersona;

    private List<Persona> listaPersonaGeneral;

    private List<ExpedienteGestion> listaExpedientesCalificacionQueja;

    private List<ExpedienteGestion> listaExpedientesInvestigacionQueja;

    private List<ExpedienteGestion> listaExpedientesPersuacionQueja;

    private List<ExpedienteGestion> listaExpedientesSeguimientoQueja;

    private List<ExpedienteGestion> listaExpedientesCalificacionPetitorio;

    private List<ExpedienteGestion> listaExpedientesGestionPetitorio;

    private List<ExpedienteGestion> listaExpedientesPersuacionPetitorio;

    private boolean indSeleccion;

    private String grafico001;

    private String grafico002;

    private String grafico003;

    private Integer nroPagina = 1;

    private Integer nroPaginaModal = 1;

    private List<SelectItem> listaEstadoCalificacionQueja;

    private List<SelectItem> listaEstadoInvestigacionQueja;

    private List<SelectItem> listaEstadoPersuacionQueja;

    private List<SelectItem> listaEstadoSeguimientoQueja;

    private List<SelectItem> listaEstadoCalificacionPetitorio;

    private List<SelectItem> listaEstadoGestionPetitorio;

    private List<SelectItem> listaEstadoPersuacionPetitorio;

    private EtapaEstado etapaEstado;

    private List<ExpedienteGestion> listaExpedienteGestion;

    private Long nroPaginaPersona = 1L;

    private List<SelectItem> listaAdjuntiaDefensoriales;

    private List<SelectItem> listaUsuariosComisionadosPorOD;

    private List<SelectItem> listaUsuariosComisionadosPorAD;

    private ExpedienteDerivacion expedienteDerivacionEnvia;

    private ExpedienteDerivacion expedienteDerivacionAprueba;

    private ExpedienteDerivacion expedienteDerivacionReasigna;

    private List<ExpedienteConsulta> listaExpedienteConsultaEnvia;

    private List<ExpedienteConsulta> listaExpedienteTotalesEnvia;

    private List<ExpedienteConsulta> listaExpedienteTotalesAprueba;

    private List<ExpedienteConsulta> listaExpedienteTotalesReasigna;

    private List<ExpedienteConsulta> listaExpedienteTotalesResponde;

    private ExpedienteConsulta expedienteConsultaPadre;

    private ExpedienteConsulta expedienteConsultaEnvia;

    private ExpedienteConsulta expedienteConsultaAprueba;

    private ExpedienteConsulta expedienteConsultaReasigna;

    private ExpedienteConsulta expedienteConsultaResponde;

    private ExpedienteConsulta expedienteRespuestaAprueba;

    private ExpedienteConsulta expedienteRespuestaAcepta;

    private ExpedienteConsulta expedienteRespuestaRecibe;

    private ExpedienteSuspencion expedienteSuspencionEnvia;

    private ExpedienteAmpliacion expedienteAmpliacionEnvia;

    private ExpedienteSuspencion expedienteSuspencionAprueba;

    private ExpedienteAmpliacion expedienteAmpliacionAprueba;

    private ExpedienteSuspencion expedienteSuspencionAcepta;

    private ExpedienteAmpliacion expedienteAmpliacionAcepta;

    private ExpedienteClasificacion expedienteClasificacionBusqueda;

    private Part file1;

    private Part file2;

    private Part file3;

    private Part file4;

    private Part file5;

    private Part file6;
    
    private Part file7;

    private boolean verBotonRegistrarExpediente = true;

    private List<SelectItem> listaClasificacionPrimerLevel;
    
    private List<SelectItem> listaClasificacionSegundoLevel;

    private List<SelectItem> listaClasificacionTercerLevel;

    private List<SelectItem> listaClasificacionCuartoLevel;

    private List<SelectItem> listaClasificacionQuintoLevel;

    private List<SelectItem> listaClasificacionSextoLevel;

    private ExpedienteNivel expedienteNivel;

    private List<ExpedienteNivel> listaExpedienteNivel;

    private List<ExpedienteNivel> listaExpedienteNivelModal;

    private List<Usuario> listaUsuarioOD;

    private List<ExpedienteDerivacion> listaExpedienteDerivacion;

    private List<ExpedienteSuspencion> listaExpedienteSuspencion;

    private List<ExpedienteAmpliacion> listaExpedienteAmpliacion;

    JasperPrint jasperPrint;

    private Integer inicioPaginado;

    private Integer finPaginado;

    private List<ExpedienteGestion> listaGestionesParaReplica;

    private Long idGestionReplica;

    private Integer tipoBusqueda;

    private List<ListadoClasificacion> listadoClasificacionTipo;

    private Integer idSegundaClasificacion;

    private List<ExpedienteClasificacionTipo> listaExpedienteClasificacion;

    private Long idClasificacion;

    private ExpedienteONP expedienteONP;

    private List<ExpedienteGestion> listaGestionesONP;

    private ExpedienteTiempo expedienteTiempo;

    private Boolean verSeccionONP;

    private Boolean verModalConclusion;
    
    private ExpedienteHistorial historial;
    
    private List<ExpedienteHistorial> listaHistorialExpediente;
    
    private String usuarioCompartir;
    
    private Boolean esSupervisor;
    
    private ExpedienteFormularioVirtual expedienteFormularioVirtual;
    
    private ExpedienteFormularioVirtual filtroFormularioVirtual;
    
    private List<ExpedienteFormularioVirtual> listaExpedienteFormularioVirtual;

    @Autowired
    private ExpedienteService expedienteService;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private EntidadService entidadService;

    @Autowired
    private ParametroService parametroService;

    @Autowired
    private ExpedientePersonaService expedientePersonaService;

    @Autowired
    private ExpedienteEntidadService expedienteEntidadService;

    @Autowired
    private UbigeoService ubigeoService;

    @Autowired
    private ExpedienteGestionService expedienteGestionService;

    @Autowired
    private EtapaEstadoService etapaEstadoService;

    @Autowired
    private GestionEtapaService gestionEtapaService;

    @Autowired
    private OficinaDefensorialService oficinaDefensorialService;

    @Autowired
    private ExpedienteDerivacionService expedienteDerivacionService;

    @Autowired
    private ExpedienteConsultaService expedienteConsultaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ExpedienteClasificacionService expedienteClasificacionService;

    @Autowired
    private ExpedienteNivelService expedienteNivelService;

    @Autowired
    private ExpedienteClasificacionTipoService expedienteClasificacionTipoService;

    @Autowired
    private ExpedienteClasiTipoService expedienteClasiTipoService;

    @Autowired
    private ExpedienteONPService expedienteONPService;

    @Autowired
    private ExpedienteTiempoService expedienteTiempoService;

    @Autowired
    private ExpedienteEtapaService expedienteEtapaService;

    @Autowired
    private ExpedienteSuspencionService expedienteSuspencionService;

    @Autowired
    private ExpedienteAmpliacionService expedienteAmpliacionService;
    
    @Autowired
    private ExpedienteHistorialService expedienteHistorialService;
    
    @Autowired
    private BandejaService bandejaService;
    
    @Autowired
    private ExpedienteFormularioVirtualService  expedienteFormularioVirtualService;

    public String cargarNuevoExpediente() {
        try {
            cargarObjetoExpediente();
            expedienteNivel = new ExpedienteNivel();
            etapaEstado = new EtapaEstado();
            cadenaPersonaPopover = "";
            personasSeleccionadas = new ArrayList<>();
            cadenaEntidadPopover = "";
            entidadSeleccionadas = new ArrayList<>();
            cadenaEtiquetaAutocomplete = expedienteService.etiquetaBuscarAutocomplete();
            listaEtiquetasSeleccionadas = new ArrayList<>();
            setVerBotonRegistrarExpediente(true);
            expedienteClasificacionBusqueda = new ExpedienteClasificacion();
            expedientepersonaModalEdit = new ExpedientePersona();
            setearSumilla();
            return "expedienteNuevo";
        } catch (Exception e) {
            log.error("ERROR - cargarNuevoExpediente()" + e);
        }
        return null;
    }
    
    private void setearSumilla(){
        if(!StringUtils.equals(expediente.getTipoClasificion(), "02")){
            if(stringUtil.isBlank(expediente.getSumilla())){
                String cadena = "hechos son:\n\nEl pedido concreto es:";
                expediente.setSumilla(cadena);
            }
        }
    }
    
    public String cargarFormularioVirtual(){
        expedienteFormularioVirtual = new ExpedienteFormularioVirtual();
        filtroFormularioVirtual = new ExpedienteFormularioVirtual();
        listarRegistrosCAV();
        return "expedienteFormularioVirtual";
    }
    
    private void cargarObjetoExpediente() {
        try {
            expediente = new Expediente();
            listaExpedienteNivel = new ArrayList<>();
            expediente.setListaExpedienteNivel(listaExpedienteNivel);
        } catch (Exception e) {
            log.error("ERROR - cargarObjetoExpediente()" + e);
        }
    }
    
    public String cargarHistorial(){
        if(StringUtils.isNotBlank(expediente.getNumero())){
            listaHistorialExpediente = expedienteHistorialService.expedienteHistorialBuscar(expediente.getNumero());
        }else
            listaHistorialExpediente = null;
        return "expedienteHistorial";
    }
    
    public String cargarCompartir(){
        usuarioCompartir = "";
        return "expedienteCompartir";
    }
    
    public void enviarCompartir(){
        Bandeja b = new Bandeja();
        b.setNumeroExpediente(expediente.getNumero());
        b.setActivo("A");
        b.setTitulo("Compartir expediente: "+expediente.getNumero());
        b.setEstado("PEN");
        b.setFechaEnvio(new Date());
        b.setNombreRemitente(usuarioSession.getNombre()+" "+usuarioSession.getApellidoPaterno()+" "+usuarioSession.getApellidoMaterno());
        b.setRemitente(usuarioSession.getCodigo());
        b.setDestinatario(usuarioCompartir);
        b.setDetalleTipo(MensajeType.MENSAJE_COMPARTIR.getDetalle());
        b.setColorTipo(MensajeType.MENSAJE_COMPARTIR.getColor());
        b.setMotivo("El expediente : "+expediente.getNumero()+" ha sido compartido");
        b.setTituloMensaje(MensajeType.MENSAJE_COMPARTIR.getValue());
        b.setTipo(MensajeType.MENSAJE_COMPARTIR.getKey());
        b.setTipoMensaje("INT");
        bandejaService.bandejaInsertar(b);
        msg.messageInfo("Se compartio el expediente", null);
        historial = new ExpedienteHistorial(HistorialType.HISTORIAL_COMPARTIR_EXPEDIENTE.getKey(), HistorialType.HISTORIAL_COMPARTIR_EXPEDIENTE.getValue());
        guardarHistorial(historial);
    }
    
    public void eliminarArchivo(){
        expediente.setRuta(null);
        expedienteService.expedienteEliminarArchivo(expediente.getId());
        msg.messageInfo("Se elimino el archivo correctamente", null);
    }
    
    public void eliminarArchivoGestion1(){
        expedienteGestion.setRuta1(null);
        expedienteGestionService.expedienteGestionEliminarArchivo1(expedienteGestion.getId());
        msg.messageInfo("Se elimino el archivo correctamente", null);
    }
    
    public void eliminarArchivoGestion2(){
        expedienteGestion.setRuta2(null);
        expedienteGestionService.expedienteGestionEliminarArchivo2(expedienteGestion.getId());
        msg.messageInfo("Se elimino el archivo correctamente", null);
    }

    public String iniciarExpedienteNuevo() {
        try {
            expediente = new Expediente();
            if (StringUtils.equals(personaSeleccionada.getTipoExpediente(), "0")) {
                msg.messageAlert("Debe selecionar un tipo de expediente", null);
                return null;
            }
            persona = new Persona();
            expedientepersonaModalEdit = new ExpedientePersona();
            entidad = new Entidad();
            cargarNuevoExpediente();
            ExpedientePersona ep = new ExpedientePersona();
            expediente.setTipoClasificion(personaSeleccionada.getTipoExpediente());
            ep.setPersona(personaSeleccionada);
            personasSeleccionadas.add(ep);
            inicializarEtapaEstado(0);
            setVerBotonRegistrarExpediente(true);
            expedienteClasificacionBusqueda = new ExpedienteClasificacion();
            setearSumilla();
            return "expedienteNuevo";
        } catch (Exception e) {
            log.error("ERROR - cargarObjetoExpediente()" + e);
        }
        return null;
    }
    
    public void inicializaEntidad(){
        entidad = new Entidad();
        entidad.setIdDepartamento(0);
        entidad.setTipo("0");
    }
    
    public void limpiaListasReporte(){
        entidadPopover = null;
        inicializaEntidad();
    }

    public void inactivarExpediente(Expediente e) {
        try {
            expedienteService.expedienteInactivar(e);
            listarExpedienteUsuarioPaginadoOrder(1, 1);
            msg.messageInfo("Se ha inactivado el expediente, ya no podra verlo en el sistema", null);
        } catch (Exception ex) {
            log.error("ERROR - inactivarExpediente()" + ex);
        }
    }

    private void cargarFichaONP() {
        try {
            int i = 0;
            for(ExpedienteEntidad e : entidadSeleccionadas){
                if(e.getEntidad().getId() == 4455){
                    i++;
                }
            }
            if(i > 0){
                expedienteONP = expedienteONPService.expedienteONPBuscarExpediente(expediente.getNumero());
                if(expedienteONP == null)
                    expedienteONP = new ExpedienteONP();
                    verSeccionONP = true;
            }else{
                verSeccionONP = false;
            }
           // 4455
            /*listaGestionesONP = new ArrayList<>();
            List<ExpedienteGestion> list = expedienteGestionService.expedienteGestionListaXexpediente(expediente.getNumero());
            for (ExpedienteGestion eg : list) {
                if (StringUtils.isNotBlank(eg.getCodigoONP())) {
                    listaGestionesONP.add(eg);
                }
            }
            if (listaGestionesONP.size() > 0) {
                expedienteONP = expedienteONPService.expedienteONPBuscarExpediente(expediente.getNumero());
                if (expedienteONP == null) {
                    expedienteONP = new ExpedienteONP();
                }
            } else {
                expedienteONP = null;
            }*/
        } catch (Exception e) {
            log.error("ERROR - cargarFichaONP()" + e);
        }
    }
    
    public void buscarCAV(){
        listaExpedienteFormularioVirtual = expedienteFormularioVirtualService.expedienteFormularioVirtualBuscar(filtroFormularioVirtual);
        limpiarCAV();
    }
    
    public void guardarRegistroCAV(){
        if(StringUtils.equals(expedienteFormularioVirtual.getAccion(), "01") || StringUtils.equals(expedienteFormularioVirtual.getAccion(), "02") || StringUtils.equals(expedienteFormularioVirtual.getAccion(), "03")){
            expedienteFormularioVirtual.setEstado("E");
            if(expedienteFormularioVirtual.getId() == null){
                expedienteFormularioVirtual.setUsuarioRegistro(usuarioSession.getCodigo());
                expedienteFormularioVirtual.setFechaRegistro(new Date());
                expedienteFormularioVirtualService.expedienteFormularioVirtualInsertar(expedienteFormularioVirtual);
            }else{
                expedienteFormularioVirtualService.expedienteFormularioVirtualUpdate(expedienteFormularioVirtual);
            }
            crearValidarExpediente();
        }else{
            expedienteFormularioVirtual.setEstado("A");
            if(expedienteFormularioVirtual.getId() == null){
                expedienteFormularioVirtual.setFechaRegistro(new Date());
                expedienteFormularioVirtual.setUsuarioRegistro(usuarioSession.getCodigo());
                expedienteFormularioVirtualService.expedienteFormularioVirtualInsertar(expedienteFormularioVirtual);
            }else{
                expedienteFormularioVirtualService.expedienteFormularioVirtualUpdate(expedienteFormularioVirtual);
            }
        }
        listarRegistrosCAV();
        limpiarCAV();
        msg.messageInfo("Se realizo el registro", null);
    }
    
    private void crearValidarExpediente(){
        Persona perso = personaService.personaXDNI(expedienteFormularioVirtual.getNumeroDocumento());
        Persona filtro = new Persona();
        if (perso == null){
            filtro.setNumeroDocumento(expedienteFormularioVirtual.getNumeroDocumento());
            filtro.setNombre(expedienteFormularioVirtual.getNombre());
            filtro.setApellidoPat(expedienteFormularioVirtual.getApellidoPaterno());
            filtro.setApellidoMat(expedienteFormularioVirtual.getApellidoMaterno());
            filtro.setIdDepartamento(expedienteFormularioVirtual.getDepartamento());
            filtro.setIdProvincia(expedienteFormularioVirtual.getProvincia());
            filtro.setTipoDocumento(expedienteFormularioVirtual.getTipoDocumento());
            filtro.setIdDistrito(expedienteFormularioVirtual.getDistrito());
            filtro.setSexo(expedienteFormularioVirtual.getSexo());
            filtro.setDireccion(expedienteFormularioVirtual.getDireccion());
            filtro.setFechaRegistro(expedienteFormularioVirtual.getFechaRegistro());
            filtro.setUsuRegistro(expedienteFormularioVirtual.getUsuarioRegistro());
            personaService.personaInsertar(filtro);
        }else{
            filtro = perso;
        }
        personaSeleccionada = filtro;
        personaSeleccionada.setTipoExpediente(expedienteFormularioVirtual.getAccion());
        generarExpedienteCAV();
        expedienteFormularioVirtual.setNumeroExpediente(expediente.getNumero());
        expedienteFormularioVirtualService.expedienteFormularioVirtualUpdate(expedienteFormularioVirtual);
    }
    
    public void seteaFormularioVirtual(ExpedienteFormularioVirtual efv){
        if(efv.getDepartamento() != null){
           comboProvinciaId(efv.getDepartamento());
        }
        if(efv.getProvincia() != null){
            comboDistritoId(efv.getProvincia(), efv.getDepartamento());
        }
        setExpedienteFormularioVirtual(efv);
    }
    
    public void inactivaRegistroCAV(long id){
        expedienteFormularioVirtualService.expedienteFormularioVirtualInactiva(id);
        listarRegistrosCAV();
        limpiarCAV();
    }
    
    public void limpiarCAV(){
        expedienteFormularioVirtual = new ExpedienteFormularioVirtual();
        expedienteFormularioVirtual.setFechaRegistro(new Date());
    }
    
    private void generarExpedienteCAV(){
        iniciarExpedienteNuevo();
        expediente.setSumilla(expedienteFormularioVirtual.getDescripcion());
        expediente.setTipoIngreso(expedienteFormularioVirtual.getForma());
        guardarVersion();
    }
    
    private void listarRegistrosCAV(){
        listaExpedienteFormularioVirtual = expedienteFormularioVirtualService.expedienteFormularioVirtualSelect(new ExpedienteFormularioVirtual());
    }

    private void insertarActualizarTiempos() {
        try {
            if(!StringUtils.equals(expediente.getTipoClasificion(), "02")){
                ExpedienteEtapa etapa = expedienteEtapaService.expedienteEtapaBuscar(etapaEstado.getVerEtapa());
                if (expediente.getVersion() == 1) {
                    setearExpedienteTiempo(etapa, 1);
                } else {
                    ExpedienteTiempo et = expedienteTiempoService.expedienteTiempoOne(expediente.getNumero());
                    if (!Objects.equals(et.getEtapa(), etapa.getIdEtapa())) {
                        setearExpedienteTiempo(etapa, 2);
                    }
                }
            }
        } catch (Exception ex) {
            log.error("ERROR - insertarActualizarTiempos()" + ex);
        }
    }

    private void setearExpedienteTiempo(ExpedienteEtapa etapa, int tip) {
        try {
            expedienteTiempo = new ExpedienteTiempo();
            expedienteTiempo.setNumeroExpediente(expediente.getNumero());
            expedienteTiempo.setEtapa(etapaEstado.getVerEtapa());
            expedienteTiempo.setDiasRestante(etapa.getDiasTotal());
            expedienteTiempo.setDiasAlerta(etapa.getDiasAlerta());
            expedienteTiempo.setEstado("ACT");
            expedienteTiempo.setTipoExpediente(expediente.getTipoClasificion());
            if (tip == 1) {
                expedienteTiempoService.expedienteTiempoInsertar(expedienteTiempo);
            } else {
                expedienteTiempoService.expedienteTiempoUpdate(expedienteTiempo);
            }
        } catch (Exception e) {
            log.error("ERROR - setearExpedienteTiempo()" + e);
        }
    }

    private void setearExpedienteTiempo() {
        try {
            expedienteTiempo = expedienteTiempoService.expedienteTiempoOne(expediente.getNumero());
        } catch (Exception e) {
            log.error("ERROR - setearExpedienteTiempo()" + e);
        }
    }

    public void guardarExpedienteONP() {
        try {
            if (expedienteONP.getId() == null) {
                expedienteONP.setNumeroExpediente(expediente.getNumero());
                expedienteONPService.expedienteONPInsertar(expedienteONP);
            } else {
                expedienteONPService.expedienteONPUpdate(expedienteONP);
            }
            msg.messageInfo("Se realizaron los cambios", null);
        } catch (Exception e) {
            log.error("ERROR - guardarExpedienteONP()" + e);
        }
    }

    public void aumentarDisminuirTiempoEtapa(int tipo, int nroDias) {
        try {
            if (tipo == 1) {
                expedienteTiempo.setDiasRestante(expedienteTiempo.getDiasRestante() + nroDias);
            } else {
                expedienteTiempo.setDiasRestante(expedienteTiempo.getDiasRestante() - nroDias);
            }
            expedienteTiempoService.expedienteTiempoUpdate(expedienteTiempo);
        } catch (Exception e) {
            log.error("ERROR - aumentarDisminuirTiempoEtapa()" + e);
        }
    }

    public void setearTiempoEtapa(int nroDias) {
        try {
            expedienteTiempo.setDiasRestante(nroDias);
            expedienteTiempoService.expedienteTiempoUpdate(expedienteTiempo);
        } catch (Exception e) {
            log.error("ERROR - setearTiempoEtapa()" + e);
        }
    }
    
    public void extenderTiempoEtapa(int nroDias) {
        try {
            expedienteTiempo.setDiasRestante(expedienteTiempo.getDiasRestante()+nroDias);
            expedienteTiempoService.expedienteTiempoUpdate(expedienteTiempo);
        } catch (Exception e) {
            log.error("ERROR - setearTiempoEtapa()" + e);
        }
    }

    public void consultarReniec() throws ParseException {
        try {
             String proxyHost = "172.30.1.250";
             String proxyPort = "8080";
             System.out.println("Setting up with proxy: " + proxyHost + ":" + proxyPort);
             System.setProperty("http.proxyHost", proxyHost);
             System.setProperty("http.proxyPort", proxyPort);
             System.setProperty("http.nonProxyHosts", "localhost|127.0.0.1");
            ServiceReniec reniec = new ServiceReniec();
            DateFormat format = new SimpleDateFormat("yyMMddHHmmss");
                String cadenaReniec = usuarioSession.getCodigo()+format.format(new Date());
            List<String> list = reniec.getConsultarServicio("DEPUWS", "MzY5MjQ5MjM=", cadenaReniec, usuarioSession.getDni(), persona.getNumeroDocumento());
            if (list != null) {
                persona.setApellidoPat(list.get(1));
                persona.setApellidoMat(list.get(2));
                persona.setNombre(list.get(4));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                Date date = formatter.parse(list.get(20));
                persona.setFechaNacimiento(date);
                if (StringUtils.equals(list.get(13), "1")) {
                    persona.setSexo("M");
                } else {
                    persona.setSexo("F");
                }
                persona.setDireccion(list.get(11));
                persona.setIdDepartamento(list.get(5));
                persona.setIdProvincia(list.get(6));
                persona.setIdDistrito(list.get(7));
                if (StringUtils.isNotBlank(persona.getIdDepartamento())) {
                    comboProvincia();
                }
                if (StringUtils.isNotBlank(persona.getIdProvincia()) && StringUtils.isNotBlank(persona.getIdDepartamento())) {
                    comboDistrito();
                }
            } else {
                msg.messageAlert("No se encontraron datos en la RENIEC con el DNI ingresado", null);
                persona = new Persona();
                persona.setTipo("PER");
            }
        } catch (Exception e) {
            log.error("ERROR - consultarReniec()" + e);
            msg.messageError("El servicio de RENIEC no esta disponible", null);
        }
    }

    public void cargarSegundaClasificacion(long idClasifica, int idPrimerNivel) {
        try {
            idClasificacion = idClasifica;
            idSegundaClasificacion = null;
            listaExpedienteClasificacion = expedienteClasificacionTipoService.clasificacioneExpedienteClasiTipo(idClasificacion);
            if (listaExpedienteClasificacion.isEmpty()) {
                listaExpedienteClasificacion = new ArrayList<>();
            }

            listadoClasificacionTipo = new ArrayList<>();
            List<ExpedienteClasificacionTipo> list = expedienteClasificacionTipoService.clasificacionCabecera(idPrimerNivel);
            if (list.size() > 0) {
                for (ExpedienteClasificacionTipo tipo : list) {
                    ListadoClasificacion lc = new ListadoClasificacion();
                    lc.setTitulo(tipo.getDetalle());
                    lc.setItems(expedienteClasificacionTipoService.clasificacionPorIdPadre(tipo.getId()));
                    listadoClasificacionTipo.add(lc);
                }
            }
        } catch (Exception e) {
            log.error("ERROR - cargarSegundaClasificacion()" + e);
        }
    }

    public boolean addSegundaClasificacion() {
        try {
            if (idSegundaClasificacion != null && idSegundaClasificacion != 0) {
                ExpedienteClasificacionTipo ect = expedienteClasificacionTipoService.clasificacionOne(idSegundaClasificacion);
                listaExpedienteClasificacion = expedienteClasificacionTipoService.clasificacioneExpedienteClasiTipo(idClasificacion);
                if (listaExpedienteClasificacion.size() > 0) {
                    for (ExpedienteClasificacionTipo ect1 : listaExpedienteClasificacion) {
                        if (Objects.equals(ect1.getId(), ect.getId())) {
                            return true;
                        }
                    }
                    expedienteClasiTipoService.expedienteClasiTipoInsertar(new ExpedienteClasiTipo(idClasificacion, ect.getId()));
                } else {
                    expedienteClasiTipoService.expedienteClasiTipoInsertar(new ExpedienteClasiTipo(idClasificacion, ect.getId()));
                }
                listaExpedienteClasificacion = expedienteClasificacionTipoService.clasificacioneExpedienteClasiTipo(idClasificacion);

            } else {
                msg.messageAlert("Debe seleccionar una clasificación", null);
            }
            idSegundaClasificacion = null;
            listarNiveles();
        } catch (Exception e) {
            log.error("ERROR - addSegundaClasificacion()" + e);
        }
        return false;
    }

    public void removeSegundaClasificacion(ExpedienteClasificacionTipo ect) {
        try {
            expedienteClasiTipoService.expedienteClasiTipoEliminar(new ExpedienteClasiTipo(idClasificacion, ect.getId()));
            listaExpedienteClasificacion = expedienteClasificacionTipoService.clasificacioneExpedienteClasiTipo(idClasificacion);
            listarNiveles();
        } catch (Exception e) {
            log.error("ERROR - removeSegundaClasificacion()" + e);
        }
    }

    public String cargarNuevaBusqueda() {
        try {
            persona = new Persona();
            personaBusqueda = new Persona();
            personaSeleccionada = new Persona();
            listaPersonaGeneral = null;
            listaExpedienteXPersona = null;
            indSeleccion = true;
            return "expedienteUsuario";
        } catch (Exception e) {
            log.error("ERROR - cargarNuevaBusqueda()" + e);
        }
        return null;
    }

    public String irOficio() {
        try {
            iniciarExpedienteNuevo();
            expediente.setIndicadorOficio(true);
            personasSeleccionadas = new ArrayList<>();
            setearSumilla();
            return "expedienteNuevo";
        } catch (Exception e) {
            log.error("ERROR - irOficio()" + e);
        }
        return null;
    }

    public void initConsulta() throws JRException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<ExpedienteFicha> list = new ArrayList<>();
        ExpedienteFicha ficha = new ExpedienteFicha();
        String oficina = usuarioSession.getNombreOD().replace("OD", "OFICINA DEFENSORIAL");
        ficha.setNumeroExpediente("EXPEDIENTE " + expediente.getNumero());
        ficha.setOficinaDefensorial(oficina + " - " + usuarioSession.getNombreOD());
        /**
         * LISTA DE PERSONAS
         */
        List<ExpedientePersona> eps = new ArrayList<>();
        Integer nro = 0;
        FiltroParametro fp = new FiltroParametro();
        Parametro parametro;
        for (ExpedientePersona ep : personasSeleccionadas) {
            if (!ep.getIndicadorReserva()) {
                nro++;
                ep.setNro(nro.toString() + ".-");
                ep.setNombreCompleto(ep.getPersona().getNombre().toUpperCase() + " " + ep.getPersona().getApellidoPat().toUpperCase() + " " + ep.getPersona().getApellidoMat().toUpperCase());
                fp.setCodigoPadreParametro(50);
                fp.setValorParametro(ep.getTipo());
                parametro = parametroService.consultarParametroValor(fp);
                ep.setDetalleCargo(parametro.getNombreParametro().toUpperCase() + ": ");
                eps.add(ep);
            }
        }
        ficha.setFechaIngreso(simpleDateFormat.format(expediente.getFechaIngreso()));
        ficha.setFechaRegistro(simpleDateFormat.format(expediente.getFechaRegistro()));
        ficha.setClaseExpediente(expediente.getClasificacionTipoNombre());
        fp.setCodigoPadreParametro(20);
        fp.setValorParametro(expediente.getTipoIngreso());
        parametro = parametroService.consultarParametroValor(fp);
        ficha.setClaseExpediente(expediente.getClasificacionTipoNombre());
        ficha.setFormaIngreso(parametro.getNombreParametro().toUpperCase());
        ficha.setDireccion("Oficina");
        ficha.setLugarRecepcion("Oficina");
        ficha.setDescripcion(expediente.getSumilla().toUpperCase());
        ficha.setConclusion(expediente.getConclusion().toUpperCase());
        ficha.setCodigoUsuario(usuarioSession.getCodigo().toUpperCase());
        ficha.setExpedientePersonas(eps);
        if (StringUtils.equals(expediente.getGeneral(), "C")) {
            ficha.setFechaConclusion(simpleDateFormat.format(expediente.getFechaModificacion()));
        } else {
            ficha.setFechaConclusion("");
        }
        /**
         * LISTA DE PERSONAS
         */
        list.add(ficha);
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(list);
        
        //jasperPrint = JasperFillManager.fillReport(RutaType.URL_FILE_SYSTEM.getValue()+"expedienteConsulta.jasper", new HashMap(), beanCollectionDataSource);
    }

    public void initPetitorio() throws JRException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        List<ExpedienteFicha> list = new ArrayList<>();
        ExpedienteFicha ficha = new ExpedienteFicha();
        String oficina = usuarioSession.getNombreOD().replace("OD", "Oficina Defensorial");
        ficha.setNumeroExpediente("Expediente: " + expediente.getNumero());
        ficha.setOficinaDefensorial(oficina);
        /**
         * LISTA DE PERSONAS
         */
        List<ExpedientePersona> eps = new ArrayList<>();
        Integer nro = 0;
        FiltroParametro fp = new FiltroParametro();
        Parametro parametro;
        for (ExpedientePersona ep : personasSeleccionadas) {
            if (!ep.getIndicadorReserva()) {
                nro++;
                Persona p = personaService.personaBusquedaOne(ep.getPersona().getId());
                ep.setNro(nro.toString() + ".-");
                String departam = "";
                String provin = "";
                String distrit = "";
                if(!StringUtils.equals(p.getIdDepartamento(),"0")){
                    departam = ubigeoService.departamentoOne(p.getIdDepartamento()).getDescripcion();
                }
                if(!StringUtils.equals(p.getIdDepartamento(),"0") && !StringUtils.equals(p.getIdProvincia(),"0")){
                    Provincia p1 = new Provincia();
                    p1.setIdDepartamento(p.getIdDepartamento());
                    p1.setIdProvincia(p.getIdProvincia());
                    provin = ", "+ubigeoService.provinciaOne(p1).getDescripcion();
                }
                if(!StringUtils.equals(p.getIdDepartamento(),"0") && !StringUtils.equals(p.getIdProvincia(),"0") && !StringUtils.equals(p.getIdDistrito(),"0")){
                    Distrito d1 = new Distrito();
                    d1.setIdDepartamento(p.getIdDepartamento());
                    d1.setIdProvincia(p.getIdProvincia());
                    d1.setIdDistrito(p.getIdDistrito());
                    distrit = ", "+ubigeoService.distritoOne(d1).getDescripcion();
                }
                ep.setDireccionNotifica(departam+provin+distrit);
                ep.setDireccion(p.getDireccion() == null? "" : p.getDireccion());
                ep.setEmail(p.getEmail() == null? "" : p.getEmail());
                ep.setTelefono1(p.getTelefono1() == null? "" : p.getTelefono1());
                String nombre = p.getNombre() == null? "" : p.getNombre().toUpperCase();
                String apePat = p.getApellidoPat() == null? "" :p.getApellidoPat().toUpperCase();
                String apeMat = p.getApellidoMat() == null? "" : p.getApellidoMat().toUpperCase();
                
                ep.setNombreCompleto(nombre + " " + apePat + " " + apeMat);
                fp.setCodigoPadreParametro(50);
                fp.setValorParametro(ep.getTipo());
                parametro = parametroService.consultarParametroValor(fp);
                ep.setDetalleCargo(parametro.getNombreParametro() + ": ");
                eps.add(ep);
            }
        }
        List<ExpedienteEntidad> ees = new ArrayList<>();
        for(ExpedienteEntidad enti : entidadSeleccionadas){
            ExpedienteEntidad en = new ExpedienteEntidad();
            en.setNombreCompleto(enti.getEntidad().getNombre());
            ees.add(en);
        }
        Integer nro2 = 0;
        List<ExpedienteGestion> listaGestiones = expedienteGestionService.expedienteGestionListaXexpediente(expediente.getNumero());
        for (ExpedienteGestion eg : listaGestiones) {
            nro2++;
            if (eg.getDescripcion() == null) {
                eg.setDescripcion("");
            }
            if (eg.getDetalleRespuesta() == null) {
                eg.setDetalleRespuesta("");
            }

            eg.setNro(nro2.toString() + ".-");
            if (eg.getFecha() != null) {
                eg.setFechaString(simpleDateFormat.format(eg.getFecha()));
            } else {
                eg.setFechaString("");
            }
            fp.setCodigoPadreParametro(70);
            fp.setValorParametro(eg.getTipo());
            parametro = parametroService.consultarParametroValor(fp);
            if (parametro != null) {
                eg.setTipoAccionString(parametro.getNombreParametro());
            } else {
                eg.setTipoAccionString("");
            }
        }
        if (expediente.getFechaIngreso() != null) {
            ficha.setFechaIngreso(simpleDateFormat.format(expediente.getFechaIngreso()));
        } else {
            ficha.setFechaIngreso("");
        }

        ficha.setFechaRegistro(simpleDateFormat.format(expediente.getFechaRegistro()));
        ficha.setClaseExpediente(ExpedienteType.tipoClasificacionNombre(expediente.getTipoClasificion()));
        fp.setCodigoPadreParametro(20);
        fp.setValorParametro(expediente.getTipoIngreso());
        parametro = parametroService.consultarParametroValor(fp);
        if (parametro != null) {
            ficha.setFormaIngreso(parametro.getNombreParametro());
        } else {
            ficha.setFormaIngreso("");
        }
        ficha.setDireccion("Oficina");
        ficha.setLugarRecepcion("Oficina");
        ficha.setDescripcion(expediente.getSumilla());
        ficha.setConclusion(expediente.getConclusion()== null? "" : expediente.getConclusion());
        ficha.setCodigoUsuario(usuarioSession.getCodigo().toUpperCase());
        ficha.setExpedientePersonas(eps);
        ficha.setExpedienteEntidades(ees);
        ficha.setEstadoGeneral(expediente.getGeneral().equals("C")? "Concluído" : "Trámite");
        ficha.setExpedienteNiveles(expediente.getListaExpedienteNivel());
        Usuario u = usuarioService.buscarUsuarioOne(expediente.getUsuarioRegistro());
        String nombreUsua = u.getNombre() == null? "" : u.getNombre();
        String apePatUsua = u.getApellidoPaterno() == null? "" : u.getApellidoPaterno();
        String apeMatUsua = u.getApellidoMaterno() == null? "" : u.getApellidoMaterno();
        ficha.setComisionado(nombreUsua+" "+apePatUsua+" "+apeMatUsua);
        ficha.setTieneOrientacion(null);
        ficha.setOrientacion(null);
        if(StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.CONSULTA.getKey())){
            ficha.setTieneOrientacion("Orientación:");
            ficha.setOrientacion(expediente.getObservacion());
        }
        /**add gestiones*/
        ficha.setExpedienteGestions(listaGestiones);
        list.add(ficha);
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(list);
        //jasperPrint = JasperFillManager.fillReport(RutaType.URL_FILE_SYSTEM.getValue()+"fichaExpediente.jasper",new HashMap(), beanCollectionDataSource);
        jasperPrint = JasperFillManager.fillReport(retornaRutaPath().concat("/jasper/fichaExpediente.jasper"), new HashMap(), beanCollectionDataSource);
    }

    public void ordenar(int tipo) {
        Collections.sort(listaExpedienteXUsuarioPaginado, new Comparator<Expediente>() {
            @Override
            public int compare(Expediente o1, Expediente o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });
    }

    public void pdf() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String fecha = simpleDateFormat.format(date);
        if (StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.CONSULTA.getKey())) {
            initPetitorio();
        }
        if (StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.PETITORIO.getKey())) {
            initPetitorio();
        }
        if (StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.QUEJA.getKey())) {
            initPetitorio();
        }
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse;
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_caso.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        facesContext.responseComplete();
        facesContext.renderResponse();
    }

    public String cargarExpedientePorId(Long idExpediente) {
        try {
            expediente = expedienteService.expedienteBuscarPorId(idExpediente);
            return cargarExpedienteEdit(expediente);
        } catch (Exception e) {
            log.error("ERROR - cargarExpedientePorId()" + e);
        }
        return null;
    }

    public String cargarExpedientePorNumero(String numeroExpediente) {
        try {
            expediente = expedienteService.expedienteBuscarPorNumero(numeroExpediente);
            return cargarExpedienteEdit(expediente);
        } catch (Exception e) {
            log.error("ERROR - cargarExpedientePorNumero()" + e);
        }
        return null;
    }

    public String cargarExpedienteGestion() {
        try {
            expedienteGestion = new ExpedienteGestion();
            expedienteBusquedaReplica = new Expediente();
            listaExpedienteXUsuarioPaginadoReplica = null;
            return "expedienteGestion";
        } catch (Exception e) {
            log.error("ERROR - cargarExpedienteGestion()" + e);
        }
        return null;
    }

    public String cargarExpedienteGestionLista() {
        try {
            listaExpedientesCalificacionQueja = new ArrayList<>();
            listaExpedientesInvestigacionQueja = new ArrayList<>();
            listaExpedientesPersuacionQueja = new ArrayList<>();
            listaExpedientesSeguimientoQueja = new ArrayList<>();
            listaExpedientesCalificacionPetitorio = new ArrayList<>();
            listaExpedientesGestionPetitorio = new ArrayList<>();
            listaExpedientesPersuacionPetitorio = new ArrayList<>();
            List<ExpedienteGestion> list = expedienteGestionService.expedienteGestionListaXexpediente(expediente.getNumero());
            for (ExpedienteGestion ee : list) {
                if (Objects.equals(ee.getIdEtapa(), EtapaType.CALIFICACION_QUEJA.getKey())) {
                    listaExpedientesCalificacionQueja.add(ee);
                }
                if (Objects.equals(ee.getIdEtapa(), EtapaType.INVESTIGACION_QUEJA.getKey())) {
                    listaExpedientesInvestigacionQueja.add(ee);
                }
                if (Objects.equals(ee.getIdEtapa(), EtapaType.PERSUACION_QUEJA.getKey())) {
                    listaExpedientesPersuacionQueja.add(ee);
                }
                if (Objects.equals(ee.getIdEtapa(), EtapaType.SEGUIMIENTO_QUEJA.getKey())) {
                    listaExpedientesSeguimientoQueja.add(ee);
                }
                if (Objects.equals(ee.getIdEtapa(), EtapaType.CALIFICACION_PETITORIO.getKey())) {
                    listaExpedientesCalificacionPetitorio.add(ee);
                }
                if (Objects.equals(ee.getIdEtapa(), EtapaType.GESTION_PETITORIO.getKey())) {
                    listaExpedientesGestionPetitorio.add(ee);
                }
                if (Objects.equals(ee.getIdEtapa(), EtapaType.PERSUASION_PETITORIO.getKey())) {
                    listaExpedientesPersuacionPetitorio.add(ee);
                }
            }
            verModalConclusion = false;
            return "expedienteGestionLista";
        } catch (Exception e) {
            log.error("ERROR - cargarExpedienteGestionLista()" + e);
        }
        return null;
    }

    public void cargarExpedienteEtapa(int etapa) {
        try {
            if (etapaEstado.getVerEtapa() == etapa) {
                expediente = expedienteService.expedienteBuscarActivo(expediente);
            } else {
                expediente.setIdEtapa(etapa);
                expediente = expedienteService.expedienteBuscarActivoEtapa(expediente);
            }
            setearExpediente(expediente);
        } catch (Exception e) {
            log.error("ERROR - cargarExpedienteEtapa()" + e);
        }
    }

    public void inicio() {
        try {
            usuarioSession();
            listaExpedienteXUsuario = expedienteService.expedienteBuscarUsuario(usuarioSession.getCodigo());
            listarExpedienteUsuarioPaginadoOrder(1, 1);
            cargarGraficos001();
            cargarGraficos002();
            cargarGraficos003();
        } catch (Exception e) {
            log.error("ERROR - inicio()" + e);
        }
    }

    public String inicioAcciones() {
        return "expedienteAcciones";
    }

    public String datosGeneralesExpediente() {
        defineBotonRegistro();
        setearSumilla();
        return "expedienteNuevo";
    }

    public void limpiarModalConsulta() {
        try {
            expedienteConsultaEnvia = new ExpedienteConsulta();
            expedienteConsultaEnvia.setIdExpediente(expediente.getId());
            expedienteConsultaEnvia.setNumeroExpediente(expediente.getNumero());
        } catch (Exception e) {
            log.error("ERROR - inicio()" + e);
        }
    }

    public void limpiarModalConsultaAprueba(ExpedienteConsulta ec) {
        expedienteConsultaAprueba = ec;
    }

    public void limpiarModalConsultaReasigna(ExpedienteConsulta ec) {
        expedienteConsultaReasigna = ec;
        expedienteConsultaReasigna.setAprueba(null);
        cargarListaComisionadoAD();
    }

    public void limpiarModalConsultaResponde(ExpedienteConsulta ec) {
        expedienteConsultaResponde = ec;
        expedienteConsultaResponde.setAprueba(null);
    }

    public void limpiarModalRespuestaAprueba(ExpedienteConsulta ec) {
        expedienteRespuestaAprueba = ec;
        expedienteRespuestaAprueba.setAprueba(null);
    }

    public void limpiarModalRespuestaAcepta(ExpedienteConsulta ec) {
        expedienteRespuestaAcepta = ec;
        expedienteRespuestaAcepta.setAprueba(null);
    }

    public void limpiarModalRespuestaRecibe(ExpedienteConsulta ec) {
        expedienteRespuestaRecibe = ec;
        expedienteRespuestaRecibe.setAprueba(null);
    }

    private void cargarListaComisionadoAD() {
        List<SelectItem> listaUsuario = new ArrayList<>();
        try {
            Usuario u = new Usuario();
            u.setCodigoOD(expedienteConsultaReasigna.getIdAdjuntiaDefensorial());
            u.setRol(RolType.COMISIONADO_AD.getKey());
            List<Usuario> list = usuarioService.listaUsuariosPorOD(u);
            for (Usuario u1 : list) {
                listaUsuario.add(new SelectItem(u1.getCodigo(), u1.getNombre() + " " + u1.getApellidoPaterno() + " " + u1.getApellidoMaterno()));
            }
            listaUsuariosComisionadosPorAD = listaUsuario;
        } catch (Exception e) {
            log.error("ERROR - cargarListaComisionadoAD()" + e);
        }
    }

    public void limpiarModalBusquedaClasificacion() {
        try {
            expedienteClasificacionBusqueda = new ExpedienteClasificacion();
            listaExpedienteNivelModal = new ArrayList<>();
            nroPaginaModal = 1;
        } catch (Exception e) {
            log.error("ERROR - limpiarModalBusquedaClasificacion()" + e);
        }

    }

    public void limpiarModalAsignar() {
        try {
            Usuario u = new Usuario();
            u.setCodigoOD(usuarioSession.getCodigoOD());
            listaUsuarioOD = null;
            listaUsuarioOD = usuarioService.listaUsuariosPorOD(u);
        } catch (Exception e) {
            log.error("ERROR - limpiarModalAsignar()" + e);
        }
    }

    public boolean guardarReconsideracion() {
        try {
            if (!expediente.getIndiceReconsideracion()) {
                msg.messageAlert("Debe marcar el check de reconsideración", null);
                expediente.setIndiceReconsideracion(false);
                expediente.setDetalleReconsideracion(null);
                return false;
            }
            if (StringUtils.isBlank(expediente.getDetalleReconsideracion())) {
                expediente.setIndiceReconsideracion(false);
                expediente.setDetalleReconsideracion(null);
                msg.messageAlert("Debe ingresar un detalle reconsideración", null);
                return false;
            }
            expedienteService.expedienteReconsideracion(expediente);
            msg.messageInfo("Se registro la reconsideración", null);
            return true;
        } catch (Exception e) {
            log.error("ERROR - guardarReconsideracion()" + e);
        }
        return false;
    }

    public void guardarAsignado() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            SeguridadUtilController seguridadUtilController = (SeguridadUtilController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "seguridadUtilController");

            if (StringUtils.isBlank(expediente.getUsuarioAsignado())) {
                msg.messageAlert("Debe ingresar el usuario asignado", null);
            } else {
                //si es practicante o sesigrista
                
                if (seguridadUtilController.tieneRolUsuario("ROL0000005", new Usuario(expediente.getUsuarioAsignado())) || seguridadUtilController.tieneRolUsuario("ROL0000006",new Usuario(expediente.getUsuarioAsignado()))) {
                    expediente.setUsuarioResponsable(usuarioSession.getCodigo());
                } else {
                    expediente.setUsuarioResponsable(expediente.getUsuarioAsignado());
                }
                expediente.setUsuarioRegistro(expediente.getUsuarioAsignado());
                expedienteService.expedienteAsignar(expediente);
                msg.messageInfo("Se asigno el expediente correctamente", null);
            }
            enviarAsignacion();
            historial = new ExpedienteHistorial(HistorialType.HISTORIAL_ASIGNAR_EXPEDIENTE.getKey(), HistorialType.HISTORIAL_ASIGNAR_EXPEDIENTE.getValue()+expediente.getUsuarioAsignado());
            guardarHistorial(historial);
        } catch (Exception e) {
            log.error("ERROR - guardarAsignado()" + e);
        }

    }

    public boolean buscarClasificacion(Integer pagina) {
        try {
            List<ExpedienteNivel> ens = new ArrayList<>();
            if (pagina > 0) {
                int paginado = ConstantesUtil.PAGINADO_10;
                Integer ini = paginado * (pagina - 1) + 1;
                Integer fin = paginado * pagina;
                if (pagina == 0) {
                    ini = 1;
                    fin = 10;
                }
                expedienteClasificacionBusqueda.setIni(ini);
                expedienteClasificacionBusqueda.setFin(fin);

                if (StringUtils.equals(expediente.getTipoClasificion(), "01")) {
                    List<ExpedienteClasificacion> list = expedienteClasificacionService.expedienteClasificacionBusqueda(expedienteClasificacionBusqueda);
                    for (ExpedienteClasificacion ec : list) {
                        ExpedienteNivel en = new ExpedienteNivel();
                        clasificarNivel(ec, en);
                        Integer idPadre = ec.getPadre();

                        while (idPadre != 0) {
                            ExpedienteClasificacion ec1 = expedienteClasificacionService.expedienteClasificacionOne(idPadre);
                            idPadre = ec1.getPadre();
                            clasificarNivel(ec1, en);
                        }
                        ens.add(en);
                    }
                    if (list.size() > 0) {
                        listaExpedienteNivelModal = ens;
                        nroPaginaModal = pagina;
                    } else {
                        if (expedienteClasificacionBusqueda.getIni() == 1) {
                            listaExpedienteNivelModal = null;
                        }
                    }
                } else {
                    List<ExpedienteClasificacion> list = expedienteClasificacionService.expedienteClasificacionBusquedaGrupo1(expedienteClasificacionBusqueda);
                    for (ExpedienteClasificacion ec : list) {
                        ExpedienteNivel en = new ExpedienteNivel();
                        clasificarNivel(ec, en);
                        Integer idPadre = ec.getPadre();

                        while (idPadre != 0) {
                            ExpedienteClasificacion ec1 = expedienteClasificacionService.expedienteClasificacionOne(idPadre);
                            idPadre = ec1.getPadre();
                            clasificarNivel(ec1, en);
                        }
                        ens.add(en);
                    }
                    if (list.size() > 0) {
                        listaExpedienteNivelModal = ens;
                        nroPaginaModal = pagina;
                    } else {
                        if (expedienteClasificacionBusqueda.getIni() == 1) {
                            listaExpedienteNivelModal = null;
                        }
                    }
                }
            }
            return true;
        } catch (Exception e) {
            log.error("ERROR - buscarClasificacion()" + e);
        }
        return false;
    }

    private void clasificarNivel(ExpedienteClasificacion ec2, ExpedienteNivel en) {
        try {
            if (ec2.getGrupo() == 1) {
                en.setIdPrimerNivel(ec2.getId());
                en.setPrimerNivel(ec2.getNombre());
            }
            if (ec2.getGrupo() == 2) {
                en.setIdSegundoNivel(ec2.getId());
                en.setSegundoNivel(ec2.getNombre());
            }
            if (ec2.getGrupo() == 3) {
                en.setIdTercerNivel(ec2.getId());
                en.setTercerNivel(ec2.getNombre());
            }
            if (ec2.getGrupo() == 4) {
                en.setIdCuartoNivel(ec2.getId());
                en.setCuartoNivel(ec2.getNombre());
            }
            if (ec2.getGrupo() == 5) {
                en.setIdQuintoNivel(ec2.getId());
                en.setQuintoNivel(ec2.getNombre());
            }
            if (ec2.getGrupo() == 6) {
                en.setIdSextoNivel(ec2.getId());
                en.setSextoNivel(ec2.getNombre());
            }
        } catch (Exception e) {
            log.error("ERROR - clasificarNivel()" + e);
        }
    }

    public void cargarNivelesClasificacion(Integer idPadre, Integer grupo) {
        if(idPadre != null)
        try {
            List<ExpedienteClasificacion> listaClasi = expedienteClasificacionService.listaExpedienteClasificacion(new ExpedienteClasificacion(idPadre, grupo, "ACT"));
            if (grupo == 2) {
                listaClasificacionSegundoLevel = new ArrayList<>();
                listaClasificacionTercerLevel = new ArrayList<>();
                listaClasificacionCuartoLevel = new ArrayList<>();
                listaClasificacionQuintoLevel = new ArrayList<>();
                listaClasificacionSextoLevel = new ArrayList<>();
                for (ExpedienteClasificacion ec : listaClasi) {
                    listaClasificacionSegundoLevel.add(new SelectItem(ec.getId(), ec.getNombre()));
                }
            }
            if (grupo == 3) {
                listaClasificacionTercerLevel = new ArrayList<>();
                listaClasificacionCuartoLevel = new ArrayList<>();
                listaClasificacionQuintoLevel = new ArrayList<>();
                listaClasificacionSextoLevel = new ArrayList<>();
                for (ExpedienteClasificacion ec : listaClasi) {
                    listaClasificacionTercerLevel.add(new SelectItem(ec.getId(), ec.getNombre()));
                }
            }
            if (grupo == 4) {
                listaClasificacionCuartoLevel = new ArrayList<>();
                listaClasificacionQuintoLevel = new ArrayList<>();
                listaClasificacionSextoLevel = new ArrayList<>();
                for (ExpedienteClasificacion ec : listaClasi) {
                    listaClasificacionCuartoLevel.add(new SelectItem(ec.getId(), ec.getNombre()));
                }
            }
            if (grupo == 5) {
                listaClasificacionQuintoLevel = new ArrayList<>();
                listaClasificacionSextoLevel = new ArrayList<>();
                for (ExpedienteClasificacion ec : listaClasi) {
                    listaClasificacionQuintoLevel.add(new SelectItem(ec.getId(), ec.getNombre()));
                }
            }
            if (grupo == 6) {
                listaClasificacionSextoLevel = new ArrayList<>();
                for (ExpedienteClasificacion ec : listaClasi) {
                    listaClasificacionSextoLevel.add(new SelectItem(ec.getId(), ec.getNombre()));
                }
            }
        } catch (Exception e) {
            log.error("ERROR - cargarNivelesClasificacion()" + e);
        }
    }

    public void guardarNivel() {
        try {
            if (expedienteNivel.getIdPrimerNivel() != null && expedienteNivel.getIdPrimerNivel() != 0) {
                if (expedienteNivel.getId() == null) {
                    expedienteNivel.setNumeroExpediente(expediente.getNumero());
                    expedienteNivel.setEstado("ACT");
                    expedienteNivelService.expedienteNivelInsertar(expedienteNivel);
                    msg.messageInfo("Se agrego una nueva clasificación temática", null);
                } else {
                    expedienteNivelService.expedienteNivelActualizar(expedienteNivel);
                    msg.messageInfo("Se actualizo la clasificación temática", null);
                }
                List<ExpedienteNivel> nivels = expedienteNivelService.expedienteNivelPorExpediente(expediente.getNumero());
                expediente.setListaExpedienteNivel(nivels);
                expedienteNivel = new ExpedienteNivel();
            }
            listarNiveles();
        } catch (Exception e) {
            log.error("ERROR - guardarNivel()" + e);
        }
    }

    public void limpiarNivelesAll() {
        listaClasificacionSegundoLevel = new ArrayList<>();
        listaClasificacionTercerLevel = new ArrayList<>();
        listaClasificacionCuartoLevel = new ArrayList<>();
        listaClasificacionQuintoLevel = new ArrayList<>();
        listaClasificacionSextoLevel = new ArrayList<>();
    }

    public void guardarNivel(ExpedienteNivel en) {
        try {
            if (en.getIdPrimerNivel() != null && en.getIdPrimerNivel() != 0) {
                en.setNumeroExpediente(expediente.getNumero());
                en.setEstado("ACT");
                expedienteNivelService.expedienteNivelInsertar(en);
                List<ExpedienteNivel> nivels = expedienteNivelService.expedienteNivelPorExpediente(expediente.getNumero());
                expediente.setListaExpedienteNivel(nivels);
                msg.messageInfo("Se agrego una nueva clasificacion temática", null);
            }
        } catch (Exception e) {
            log.error("ERROR - guardarNivel()" + e);
        }
    }

    public void inactivarNivel(ExpedienteNivel en) {
        try {
            expedienteNivelService.expedienteNivelUpdate(en.getId());
            List<ExpedienteNivel> nivels = expedienteNivelService.expedienteNivelPorExpediente(expediente.getNumero());
            expediente.setListaExpedienteNivel(nivels);
            listarNiveles();
        } catch (Exception e) {
            log.error("ERROR - inactivarNivel()" + e);
        }
    }

    public void editarNivel(ExpedienteNivel en) {
        try {
            expedienteNivel.setId(en.getId());
            if (en.getIdPrimerNivel() != null && en.getIdPrimerNivel() != 0) {
                cargarNivelesClasificacion(en.getIdPrimerNivel(), 2);
                expedienteNivel.setIdPrimerNivel(en.getIdPrimerNivel());
            }
            if (en.getIdSegundoNivel() != null && en.getIdSegundoNivel() != 0) {
                cargarNivelesClasificacion(en.getIdSegundoNivel(), 3);
                expedienteNivel.setIdSegundoNivel(en.getIdSegundoNivel());
            }
            if (en.getIdTercerNivel() != null && en.getIdTercerNivel() != 0) {
                cargarNivelesClasificacion(en.getIdTercerNivel(), 4);
                expedienteNivel.setIdTercerNivel(en.getIdTercerNivel());
            }
            if (en.getIdCuartoNivel() != null && en.getIdCuartoNivel() != 0) {
                cargarNivelesClasificacion(en.getIdCuartoNivel(), 5);
                expedienteNivel.setIdCuartoNivel(en.getIdCuartoNivel());
            }
            if (en.getIdQuintoNivel() != null && en.getIdQuintoNivel() != 0) {
                cargarNivelesClasificacion(en.getIdQuintoNivel(), 6);
                expedienteNivel.setIdQuintoNivel(en.getIdQuintoNivel());
            }
            if (en.getIdSextoNivel() != null && en.getIdSextoNivel() != 0) {
                expedienteNivel.setIdSextoNivel(en.getIdSextoNivel());
            }
        } catch (Exception e) {
            log.error("ERROR - editarNivel()" + e);
        }
    }

    public void desistirExpediente() {
        try {
            expediente.setIndicadorDesestimiento(1);
            expediente.setGeneral("C");
            expedienteService.expedienteDesistir(expediente);
            msg.messageInfo("Se ha concluido el expediente, pasa al estado desistido", null);
            historial = new ExpedienteHistorial(HistorialType.HISTORIAL_DESISTIR.getKey(), HistorialType.HISTORIAL_DESISTIR.getValue());
            guardarHistorial(historial);
        } catch (Exception e) {
            log.error("ERROR - desistirExpediente()" + e);
        }
    }
    
    public void conclusionManualExpediente() {
        try {
            expediente.setGeneral("C");
            expedienteService.expedienteConcluir(expediente.getId());
            msg.messageInfo("Se ha concluido manualmente el expediente", null);
            historial = new ExpedienteHistorial(HistorialType.HISTORIAL_CONCLUSION_MANUAL.getKey(), HistorialType.HISTORIAL_CONCLUSION_MANUAL.getValue());
            guardarHistorial(historial);
        } catch (Exception e) {
            log.error("ERROR - desistirExpediente()" + e);
        }
    }

    private void defineBotonRegistro() {
        try {
            listaExpedienteDerivacion = null;
            listaExpedienteDerivacion = expedienteDerivacionService.expedienteDerivacionSelectList(expediente.getId());
            if (listaExpedienteDerivacion.size() > 0) {
                setVerBotonRegistrarExpediente(false);
            } else {
                setVerBotonRegistrarExpediente(true);
            }

            /*en general*/
            if (StringUtils.equals(expediente.getUsuarioRegistro(), usuarioSession.getCodigo())) {
                setVerBotonRegistrarExpediente(true);
            } else {
                setVerBotonRegistrarExpediente(false);
            }
        } catch (Exception e) {
            log.error("ERROR - defineBotonRegistro()" + e);
        }

    }

    private void limpiarElementosConsulta() {
        expedienteConsultaAprueba = new ExpedienteConsulta();
        expedienteConsultaPadre = new ExpedienteConsulta();
        expedienteConsultaEnvia = new ExpedienteConsulta();
        expedienteConsultaReasigna = new ExpedienteConsulta();
        expedienteRespuestaAcepta = new ExpedienteConsulta();
        expedienteConsultaReasigna = new ExpedienteConsulta();
        expedienteRespuestaAprueba = new ExpedienteConsulta();
        expedienteRespuestaRecibe = new ExpedienteConsulta();
        expedienteConsultaResponde = new ExpedienteConsulta();
    }

    private void inicioAccionesConsulta() {
        try {
            listaExpedienteTotalesEnvia = new ArrayList<>();
            listaExpedienteTotalesAprueba = new ArrayList<>();
            listaExpedienteTotalesReasigna = new ArrayList<>();
            listaExpedienteTotalesResponde = new ArrayList<>();
            List<ExpedienteConsulta> lista = expedienteConsultaService.expedienteConsultaPorExpedientePadre(expediente.getNumero());
            for (ExpedienteConsulta ec : lista) {
                if (ec.getEtapa() == 1) {
                    listaExpedienteTotalesEnvia.add(ec);
                    if(esSupervisor){
                        listaExpedienteTotalesEnvia = new ArrayList<>();
                    }
                    listaExpedienteTotalesAprueba.add(ec);
                }
                if (ec.getEtapa() == 2) {
                    listaExpedienteTotalesEnvia.add(ec);
                    if(esSupervisor){
                        listaExpedienteTotalesEnvia = new ArrayList<>();
                    }
                    listaExpedienteTotalesAprueba.add(ec);
                    if (Objects.equals(ec.getIdAdjuntiaDefensorial(), usuarioSession.getCodigoOD())) {
                        listaExpedienteTotalesReasigna.add(ec);
                    }
                }
                if (ec.getEtapa() >= 3) {
                    listaExpedienteTotalesEnvia.add(ec);
                    if(esSupervisor){
                        listaExpedienteTotalesEnvia = new ArrayList<>();
                    }
                    listaExpedienteTotalesAprueba.add(ec);
                    if (Objects.equals(ec.getIdAdjuntiaDefensorial(), usuarioSession.getCodigoOD())) {
                        listaExpedienteTotalesReasigna.add(ec);
                    }
                    if (Objects.equals(ec.getIdAdjuntiaDefensorial(), usuarioSession.getCodigoOD())) {
                        listaExpedienteTotalesResponde.add(ec);
                    }
                }
            }
        } catch (Exception e) {
            log.error("ERROR - inicioAccionesConsulta()" + e);
        }
    }

    public String inicioAccionesSuspenderPublic() {
        try {
            limpiarElementosSuspencion();
            inicioAccionesSuspencion();
            return "expedienteAccionesSuspeder";
        } catch (Exception e) {
            log.error("ERROR - inicioAccionesSuspenderPublic()" + e);
        }
        return null;
    }

    private void limpiarElementosSuspencion() {
        expedienteSuspencionEnvia = new ExpedienteSuspencion();
        expedienteSuspencionAprueba = new ExpedienteSuspencion();
        expedienteSuspencionAcepta = new ExpedienteSuspencion();
    }

    private String inicioAccionesSuspencion() {
        try {
            listaExpedienteSuspencion = null;
            listaExpedienteSuspencion = expedienteSuspencionService.expedienteSuspencionSelectList(expediente.getId());

            for (ExpedienteSuspencion es : listaExpedienteSuspencion) {
                if (es.getEtapa() == EtapaSuspencionType.SUSPENCION_ETAPA_ENVIA.getKey()) {
                    setExpedienteSuspencionEnvia(es);
                }
                if (es.getEtapa() == EtapaSuspencionType.SUSPENCION_ETAPA_APRUEBA.getKey()) {
                    setExpedienteSuspencionAprueba(es);
                }
                if (es.getEtapa() == EtapaSuspencionType.SUSPENCION_ETAPA_ACEPTA.getKey()) {
                    setExpedienteSuspencionAcepta(es);
                }
            }
            if (expedienteSuspencionEnvia.getId() == null) {
                expedienteSuspencionEnvia = new ExpedienteSuspencion();
            }

            if (expedienteSuspencionAprueba.getId() == null) {
                expedienteSuspencionAprueba = new ExpedienteSuspencion();
                expedienteSuspencionAprueba.setCodigoUsuario(usuarioSession.getCodigo());
                return "expedienteAccionesSuspeder";
            } else {
                if (StringUtils.equals(expedienteSuspencionAprueba.getCodigoUsuario(), usuarioSession.getCodigo())) {
                    return "expedienteAccionesSuspeder";
                }
            }

            if (expedienteSuspencionAcepta.getId() == null) {
                expedienteSuspencionAcepta = new ExpedienteSuspencion();
                expedienteSuspencionAcepta.setCodigoUsuario(usuarioSession.getCodigo());
            }
            return "expedienteAccionesSuspeder";
        } catch (Exception e) {
            log.error("ERROR - inicioAccionesSuspencion()" + e);
        }
        return null;
    }

    public String inicioAccionesAmpliarPublic() {
        try {
            limpiarElementosAmpliacion();
            inicioAccionesAmpliacion();
            return "expedienteAccionesAmpliar";
        } catch (Exception e) {
            log.error("ERROR - inicioAccionesAmpliarPublic()" + e);
        }
        return null;
    }

    private void limpiarElementosAmpliacion() {
        expedienteAmpliacionEnvia = new ExpedienteAmpliacion();
        expedienteAmpliacionAprueba = new ExpedienteAmpliacion();
        expedienteAmpliacionAcepta = new ExpedienteAmpliacion();
    }

    private String inicioAccionesAmpliacion() {
        try {
            listaExpedienteAmpliacion = null;
            listaExpedienteAmpliacion = expedienteAmpliacionService.expedienteAmpliacionSelectList(expediente.getId());

            for (ExpedienteAmpliacion ea : listaExpedienteAmpliacion) {
                if (ea.getEtapa() == EtapaAmpliacionType.AMPLIACION_ETAPA_ENVIA.getKey()) {
                    setExpedienteAmpliacionEnvia(ea);
                }
                if (ea.getEtapa() == EtapaAmpliacionType.AMPLIACION_ETAPA_APRUEBA.getKey()) {
                    setExpedienteAmpliacionAprueba(ea);
                }
                if (ea.getEtapa() == EtapaAmpliacionType.AMPLIACION_ETAPA_ACEPTA.getKey()) {
                    setExpedienteAmpliacionAcepta(ea);
                }
            }
            if (expedienteAmpliacionEnvia.getId() == null) {
                expedienteAmpliacionEnvia = new ExpedienteAmpliacion();
            }

            if (expedienteAmpliacionAprueba.getId() == null) {
                expedienteAmpliacionAprueba = new ExpedienteAmpliacion();
                expedienteAmpliacionAprueba.setCodigoUsuario(usuarioSession.getCodigo());
                return "expedienteAccionesAmpliar";
            } else {
                if (StringUtils.equals(expedienteAmpliacionAprueba.getCodigoUsuario(), usuarioSession.getCodigo())) {
                    return "expedienteAccionesAmpliar";
                }
            }

            if (expedienteAmpliacionAcepta.getId() == null) {
                expedienteAmpliacionAcepta = new ExpedienteAmpliacion();
                expedienteAmpliacionAcepta.setCodigoUsuario(usuarioSession.getCodigo());
            }
            return "expedienteAccionesAmpliar";
        } catch (Exception e) {
            log.error("ERROR - inicioAccionesAmpliacion()" + e);
        }
        return null;
    }

    public String inicioAccionesConsultaPublic() {
        try {
            limpiarElementosConsulta();
            inicioAccionesConsulta();
            return "expedienteAccionesConsulta";
        } catch (Exception e) {
            log.error("ERROR - inicioAccionesConsultaPublic()" + e);
        }
        return null;
    }

    public String inicioAccionesDerivacion() {
        try {
            listaExpedienteDerivacion = null;
            listaExpedienteDerivacion = expedienteDerivacionService.expedienteDerivacionSelectList(expediente.getId());
            expedienteDerivacionEnvia = null;
            expedienteDerivacionAprueba = null;
            expedienteDerivacionReasigna = null;
            for (ExpedienteDerivacion ed : listaExpedienteDerivacion) {
                if (ed.getEtapa() == EtapaDerivacionType.DERIVAR_ETAPA_ENVIA.getKey()) {
                    setExpedienteDerivacionEnvia(ed);
                }
                if (ed.getEtapa() == EtapaDerivacionType.DERIVAR_ETAPA_APRUEBA.getKey()) {
                    setExpedienteDerivacionAprueba(ed);
                }
                if (ed.getEtapa() == EtapaDerivacionType.DERIVAR_ETAPA_REASIGNA.getKey()) {
                    setExpedienteDerivacionReasigna(ed);
                }
            }
            if (expedienteDerivacionEnvia == null) {
                expedienteDerivacionEnvia = new ExpedienteDerivacion();
            }

            if (expedienteDerivacionAprueba == null) {
                expedienteDerivacionAprueba = new ExpedienteDerivacion();
                expedienteDerivacionAprueba.setCodigoUsuario(usuarioSession.getCodigo());
                expedienteDerivacionAprueba.setIdOficinaDefensorial(expedienteDerivacionEnvia.getIdOficinaDefensorial());
                return "expedienteAccionesDerivacion";
            } else {
                if (StringUtils.equals(expedienteDerivacionAprueba.getCodigoUsuario(), usuarioSession.getCodigo())) {
                    return "expedienteAccionesDerivacion";
                }
            }

            if (expedienteDerivacionReasigna == null) {
                expedienteDerivacionReasigna = new ExpedienteDerivacion();
                expedienteDerivacionReasigna.setCodigoUsuario(usuarioSession.getCodigo());
            }
            return "expedienteAccionesDerivacion";
        } catch (Exception e) {
            log.error("ERROR - inicioAccionesDerivacion()" + e);
        }
        return null;
    }

    public boolean enviarDerivacion() {
        try {
            if (StringUtils.isBlank(expedienteDerivacionEnvia.getDetalle())) {
                msg.messageAlert("Debe ingresar el detalle", null);
                return false;
            }
            if (expedienteDerivacionEnvia.getIdOficinaDefensorial() == 0) {
                msg.messageAlert("Debe seleccionar una oficina defensorial", null);
                return false;
            }
            if (expedienteDerivacionEnvia.getId() != null) {
                return false;
            }
            expedienteDerivacionEnvia.setIdExpediente(expediente.getId());
            expedienteDerivacionEnvia.setNumeroExpediente(expediente.getNumero());
            expedienteDerivacionEnvia.setEstado("ACT");
            expedienteDerivacionEnvia.setEtapa(EtapaDerivacionType.DERIVAR_ETAPA_ENVIA.getKey());
            expedienteDerivacionEnvia.setCodigoUsuario(usuarioSession.getCodigo());
            expedienteDerivacionEnvia.setNombreUsuario(usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno());
            expedienteDerivacionEnvia.setFecha(new Date());
            String ruta = uploadArchive(file4);
            expedienteDerivacionEnvia.setRuta(ruta);
            expedienteDerivacionService.expedienteDerivacionInsertar(expedienteDerivacionEnvia);
            enviarMensajeDerivacion();
            msg.messageInfo("Se envio la Derivación", null);
            historial = new ExpedienteHistorial(HistorialType.HISTORIAL_DERIVAR_ENVIAR.getKey(), HistorialType.HISTORIAL_DERIVAR_ENVIAR.getValue());
            guardarHistorial(historial);
            return true;
        } catch (Exception e) {
            log.error("ERROR - enviarDerivacion()" + e);
        }
        return false;
    }
    
    public boolean enviarAsignacion() {
        try {
            enviarMensajeAsignacion();
        } catch (Exception e) {
            log.error("ERROR - enviarDerivacion()" + e);
        }
        return false;
    }

    public boolean aprobarDerivacion() {
        try {
            if (StringUtils.isBlank(expedienteDerivacionAprueba.getAprueba())) {
                msg.messageAlert("Debe aprobar o desaprobar la solicitud de derivación", null);
                return false;
            }

            if (StringUtils.isBlank(expedienteDerivacionAprueba.getDetalle())) {
                msg.messageAlert("Debe ingresar el detalle", null);
                return false;
            }

            if (StringUtils.equals(expedienteDerivacionAprueba.getAprueba(), "SI")) {
                if (expedienteDerivacionAprueba.getIdOficinaDefensorial() == 0) {
                    msg.messageAlert("Debe seleccionar una oficina defensorial", null);
                    return false;
                }
            }
            expedienteDerivacionAprueba.setIdExpediente(expediente.getId());
            expedienteDerivacionAprueba.setNumeroExpediente(expediente.getNumero());
            expedienteDerivacionAprueba.setEstado("ACT");
            expedienteDerivacionAprueba.setEtapa(EtapaDerivacionType.DERIVAR_ETAPA_APRUEBA.getKey());
            expedienteDerivacionAprueba.setCodigoUsuario(usuarioSession.getCodigo());
            expedienteDerivacionAprueba.setNombreUsuario(usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno());
            expedienteDerivacionAprueba.setFecha(new Date());
            expedienteDerivacionService.expedienteDerivacionInsertar(expedienteDerivacionAprueba);
            if (StringUtils.equals(expedienteDerivacionAprueba.getAprueba(), "SI")) {
                enviarMensajeAprobacion();
                msg.messageInfo("Se aprobó la Derivación", null);
                historial = new ExpedienteHistorial(HistorialType.HISTORIAL_DERIVAR_APROBAR.getKey(), HistorialType.HISTORIAL_DERIVAR_APROBAR.getValue());
                guardarHistorial(historial);
            } else {
                guardarVersion2();
                enviarMensajeDesaprobacion();
                msg.messageInfo("No se aprobo la derivación", null);
                historial = new ExpedienteHistorial(HistorialType.HISTORIAL_DERIVAR_DESAPROBAR.getKey(), HistorialType.HISTORIAL_DERIVAR_DESAPROBAR.getValue());
                guardarHistorial(historial);
            }
            return true;
        } catch (Exception e) {
            log.error("ERROR - aprobarDerivacion()" + e);
        }
        return false;
    }

    public boolean reasignarDerivacion() {
        try {
            if (StringUtils.isBlank(expedienteDerivacionReasigna.getAprueba())) {
                msg.messageAlert("Debe aceptar o rechazar la solicitud de derivación", null);
                return false;
            }

            if (StringUtils.isBlank(expedienteDerivacionReasigna.getDetalle())) {
                msg.messageAlert("Debe ingresar el detalle", null);
                return false;
            }

            if (StringUtils.equals(expedienteDerivacionReasigna.getAprueba(), "SI")) {
                if (StringUtils.equals(expedienteDerivacionReasigna.getCodigoUsuarioDerivado(), "0")) {
                    msg.messageAlert("Debe seleccionar el comisionado al cual derivará el expediente", null);
                    return false;
                }
            }
            expedienteDerivacionReasigna.setIdExpediente(expediente.getId());
            expedienteDerivacionReasigna.setNumeroExpediente(expediente.getNumero());
            expedienteDerivacionReasigna.setEstado("ACT");
            expedienteDerivacionReasigna.setEtapa(EtapaDerivacionType.DERIVAR_ETAPA_REASIGNA.getKey());
            expedienteDerivacionReasigna.setCodigoUsuario(usuarioSession.getCodigo());
            expedienteDerivacionReasigna.setNombreUsuario(usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno());
            expedienteDerivacionReasigna.setFecha(new Date());
            expedienteDerivacionService.expedienteDerivacionInsertar(expedienteDerivacionReasigna);
            if (StringUtils.equals(expedienteDerivacionReasigna.getAprueba(), "SI")) {
                guardarVersion3(expedienteDerivacionReasigna.getCodigoUsuarioDerivado());
                enviarMensajeReasignacion();
                msg.messageInfo("Se reasigno por derivación el expediente", null);
                historial = new ExpedienteHistorial(HistorialType.HISTORIAL_DERIVAR_ACEPTAR.getKey(), HistorialType.HISTORIAL_DERIVAR_ACEPTAR.getValue()+expedienteDerivacionReasigna.getCodigoUsuarioDerivado());
                guardarHistorial(historial);
            } else {
                guardarVersion2();
                enviarMensajeReasignacionDesaprobada();
                msg.messageInfo("Se rechaza la derivación", null);
                msg.messageInfo("Se reasigno por derivación el expediente", null);
                historial = new ExpedienteHistorial(HistorialType.HISTORIAL_DERIVAR_RECHAZAR.getKey(), HistorialType.HISTORIAL_DERIVAR_RECHAZAR.getValue());
            }
            return true;
        } catch (Exception e) {
            log.error("ERROR - reasignarDerivacion()" + e);
        }
        return false;
    }

    public boolean enviarConsulta(int tipo) {
        try {
            if (expedienteConsultaEnvia.getId() != null) {
                return false;
            }
            if (StringUtils.isBlank(expedienteConsultaEnvia.getDetalle())) {
                msg.messageAlert("Debe ingresar la consulta", null);
                return false;
            }
            if (expedienteConsultaEnvia.getIdAdjuntiaDefensorial() == null || expedienteConsultaEnvia.getIdAdjuntiaDefensorial() == 0) {
                msg.messageAlert("Debe seleccionar el destino de la consulta", null);
                return false;
            }
            String ruta1 = uploadArchive(file3);
            expedienteConsultaEnvia.setRuta(ruta1);
            expedienteConsultaEnvia.setIdExpediente(expediente.getId());
            expedienteConsultaEnvia.setNumeroExpediente(expediente.getNumero());
            expedienteConsultaEnvia.setEstado("ACT");
            expedienteConsultaEnvia.setEtapa(EtapaConsultaType.CONSULTA_ETAPA_ENVIA.getKey());
            expedienteConsultaEnvia.setCodigoUsuario(usuarioSession.getCodigo());
            expedienteConsultaEnvia.setNombreUsuario(usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno());
            expedienteConsultaEnvia.setTipo(tipo);
            if (tipo == 1) {
                String formato = RandomStringUtils.random(11, 0, 21, true, true, "WERTYUIO123456789KPBV".toCharArray());
                expedienteConsultaEnvia.setCodigo("C"+usuarioSession.getCodigo() + formato);
            }
            expedienteConsultaEnvia.setFecha(new Date());
            /**
             * GRABAR EL PADRE
             */
            expedienteConsultaService.expedienteConsultaInsertar(expedienteConsultaEnvia);
            /**
             * GRABAR EL HIJO
             */
            expedienteConsultaEnvia.setIdPadre(expedienteConsultaEnvia.getId());
            expedienteConsultaEnvia.setEtapa(EtapaConsultaType.CONSULTA_ETAPA_ENVIA.getKey());
            expedienteConsultaEnvia.setId(null);
            expedienteConsultaService.expedienteConsultaInsertar(expedienteConsultaEnvia);
            /**
             *
             */
            enviarMensajeConsulta();
            inicioAccionesConsulta();
            historial = new ExpedienteHistorial(HistorialType.HISTORIAL_CONSULTA_ENVIA.getKey(), HistorialType.HISTORIAL_CONSULTA_ENVIA.getValue());
            guardarHistorial(historial);
            msg.messageInfo("Se envio la Consulta", null);
            return true;
        } catch (Exception e) {
            log.error("ERROR - enviarConsulta()" + e);
        }
        return false;
    }

    public boolean enviarAprobacion() {
        try {
            int contador = expedienteConsultaService.expedienteConsultaPorEtapaCount(new ExpedienteConsulta(EtapaConsultaType.CONSULTA_ETAPA_APRUEBA.getKey(), expedienteConsultaAprueba.getId()));
            if (contador > 0) {
                return false;
            }

            if (StringUtils.isBlank(expedienteConsultaAprueba.getDetalle())) {
                msg.messageAlert("El campo de la consulta no puede estar vacio", null);
                return false;
            }
            if (StringUtils.isBlank(expedienteConsultaAprueba.getAprueba())) {
                msg.messageAlert("Debe aprobar o desaprobar la consulta", null);
                return false;
            } else {
                if (StringUtils.equals(expedienteConsultaAprueba.getAprueba(), "NO")) {
                    expedienteConsultaAprueba.setEstado("INA");
                } else {
                    expedienteConsultaAprueba.setEstado("ACT");
                }
            }
            if (expedienteConsultaAprueba.getIdAdjuntiaDefensorial() == null || expedienteConsultaAprueba.getIdAdjuntiaDefensorial() == 0) {
                msg.messageAlert("Debe seleccionar el destino de la consulta", null);
                return false;
            }
            setExpedienteConsultaPadre(expedienteConsultaAprueba);
            String ruta1 = uploadArchive(file7);
            if (ruta1 != null) {
                expedienteConsultaPadre.setRuta(ruta1);
            }
            expedienteConsultaPadre.setCodigoUsuario(usuarioSession.getCodigo());
            expedienteConsultaPadre.setNombreUsuario(usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno());
            if (StringUtils.equals(expedienteConsultaPadre.getAprueba(), "NO")) {
                expedienteConsultaPadre.setEtapa(EtapaConsultaType.CONSULTA_ETAPA_ENVIA.getKey());
            } else {
                expedienteConsultaPadre.setEtapa(EtapaConsultaType.CONSULTA_ETAPA_APRUEBA.getKey());
                aumentarDisminuirTiempoEtapa(1, 20);
            }
            expedienteConsultaPadre.setFecha(new Date());
            /**
             * ACTUALIZAR PADRE
             */
            expedienteConsultaService.expedienteConsultaUpdate(expedienteConsultaPadre);
            /**
             * GRABAR EL HIJO
             */
            setExpedienteConsultaAprueba(expedienteConsultaPadre);
            expedienteConsultaAprueba.setIdPadre(expedienteConsultaPadre.getId());
            expedienteConsultaAprueba.setEtapa(EtapaConsultaType.CONSULTA_ETAPA_APRUEBA.getKey());
            expedienteConsultaAprueba.setId(null);
            expedienteConsultaAprueba.setEstado("ACT");
            expedienteConsultaService.expedienteConsultaInsertar(expedienteConsultaAprueba);
            /**
             *
             */
            if (StringUtils.equals(expedienteConsultaAprueba.getAprueba(), "NO")) {
                enviarMensajeDesaprobacionConsulta();
                inicioAccionesConsulta();
                historial = new ExpedienteHistorial(HistorialType.HISTORIAL_CONSULTA_DESAPRUEBA.getKey(), HistorialType.HISTORIAL_CONSULTA_DESAPRUEBA.getValue());
                guardarHistorial(historial);
                msg.messageInfo("No se aprobo la Consulta", null);
            } else {
                enviarMensajeAprobacionConsulta();
                inicioAccionesConsulta();
                historial = new ExpedienteHistorial(HistorialType.HISTORIAL_CONSULTA_APRUEBA.getKey(), HistorialType.HISTORIAL_CONSULTA_APRUEBA.getValue());
                guardarHistorial(historial);
                msg.messageInfo("Se aprobo la Consulta", null);
            }

            return true;
        } catch (Exception e) {
            log.error("ERROR - enviarAprobacion()" + e);
        }
        return false;
    }

    public boolean reasignarConsulta() {
        try {
            int contador = expedienteConsultaService.expedienteConsultaPorEtapaCount(new ExpedienteConsulta(EtapaConsultaType.CONSULTA_ETAPA_REASIGNA.getKey(), expedienteConsultaReasigna.getId()));
            if (contador > 0) {
                return false;
            }

            if (StringUtils.isBlank(expedienteConsultaReasigna.getDetalle())) {
                msg.messageAlert("El campo de la consulta no puede estar vacio", null);
                return false;
            }
            if (StringUtils.isBlank(expedienteConsultaReasigna.getAprueba())) {
                msg.messageAlert("Debe aprobar o desaprobar la consulta", null);
                return false;
            } else {
                if (StringUtils.equals(expedienteConsultaReasigna.getAprueba(), "SI")) {
                    if (StringUtils.equals(expedienteConsultaReasigna.getCodigoUsuarioReasignado(), "0")) {
                        msg.messageAlert("Debe seleccionar el comisionado al cual reasignara la consulta", null);
                        return false;
                    }
                }
            }
            setExpedienteConsultaPadre(expedienteConsultaReasigna);
            String ruta1 = uploadArchive(file3);
            if (ruta1 != null) {
                expedienteConsultaPadre.setRuta(ruta1);
            }
            expedienteConsultaPadre.setCodigoUsuario(usuarioSession.getCodigo());
            expedienteConsultaPadre.setNombreUsuario(usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno());
            if (StringUtils.equals(expedienteConsultaPadre.getAprueba(), "NO")) {
                expedienteConsultaPadre.setEtapa(EtapaConsultaType.CONSULTA_ETAPA_APRUEBA.getKey());
                expedienteConsultaPadre.setEstado("INA");
            } else {
                expedienteConsultaPadre.setEtapa(EtapaConsultaType.CONSULTA_ETAPA_REASIGNA.getKey());
                expedienteConsultaPadre.setEstado("ACT");
            }
            expedienteConsultaPadre.setFecha(new Date());
            /**
             * ACTUALIZAR PADRE
             */
            expedienteConsultaService.expedienteConsultaUpdate(expedienteConsultaPadre);
            /**
             * GRABAR EL HIJO
             */
            setExpedienteConsultaReasigna(expedienteConsultaPadre);
            expedienteConsultaReasigna.setIdPadre(expedienteConsultaPadre.getId());
            expedienteConsultaReasigna.setEtapa(EtapaConsultaType.CONSULTA_ETAPA_REASIGNA.getKey());
            expedienteConsultaReasigna.setId(null);
            expedienteConsultaReasigna.setEstado("ACT");
            expedienteConsultaService.expedienteConsultaInsertar(expedienteConsultaReasigna);
            /**
             *
             */
            if (StringUtils.equals(expedienteConsultaReasigna.getAprueba(), "NO")) {
                enviarMensajeDesaprobacionReasignacionConsulta();
                historial = new ExpedienteHistorial(HistorialType.HISTORIAL_CONSULTA_RECHAZA.getKey(), HistorialType.HISTORIAL_CONSULTA_ENVIA.getValue());
                guardarHistorial(historial);
                msg.messageInfo("Se rechaza la consulta", null);
            } else {
                enviarMensajeReasignacionConsulta();
                historial = new ExpedienteHistorial(HistorialType.HISTORIAL_CONSULTA_ACEPTA.getKey(), HistorialType.HISTORIAL_CONSULTA_ENVIA.getValue());
                guardarHistorial(historial);
                msg.messageInfo("Se reasigno la consulta", null);
            }
            inicioAccionesConsulta();
            return true;
        } catch (Exception e) {
            log.error("ERROR - reasignarConsulta()" + e);
        }
        return false;
    }

    public boolean responderConsulta() {
        try {
            if (StringUtils.isBlank(expedienteConsultaResponde.getDetalle())) {
                msg.messageAlert("Debe responder la consulta", null);
                return false;
            }

            setExpedienteConsultaPadre(expedienteConsultaResponde);
            String rutaRespuesta = uploadArchive(file6);
            if (rutaRespuesta != null) {
                expedienteConsultaPadre.setRutaRespuesta(rutaRespuesta);
            }
            expedienteConsultaPadre.setCodigoUsuario(usuarioSession.getCodigo());
            expedienteConsultaPadre.setNombreUsuario(usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno());
            expedienteConsultaPadre.setEtapa(EtapaConsultaType.CONSULTA_ETAPA_RESPONDE.getKey());
            expedienteConsultaPadre.setFecha(new Date());
            /**
             * ACTUALIZAR PADRE
             */
            expedienteConsultaService.expedienteConsultaUpdate(expedienteConsultaPadre);
            /**
             * GRABAR EL HIJO
             */
            setExpedienteConsultaResponde(expedienteConsultaPadre);
            expedienteConsultaResponde.setIdPadre(expedienteConsultaPadre.getId());
            expedienteConsultaResponde.setEtapa(EtapaConsultaType.CONSULTA_ETAPA_RESPONDE.getKey());
            expedienteConsultaResponde.setId(null);
            expedienteConsultaService.expedienteConsultaInsertar(expedienteConsultaResponde);
            enviarMensajeRespondeConsulta();
            inicioAccionesConsulta();
            historial = new ExpedienteHistorial(HistorialType.HISTORIAL_CONSULTA_RESPONDE.getKey(), HistorialType.HISTORIAL_CONSULTA_RESPONDE.getValue());
            guardarHistorial(historial);
            msg.messageInfo("Se respondío la consulta", null);
            return true;
        } catch (Exception e) {
            log.error("ERROR - responderConsulta()" + e);
        }
        return false;
    }
    
    public boolean esSupervisor(){
        try {
            Usuario u = usuarioService.buscarUsuarioOne(expediente.getUsuarioRegistro());
            if(Objects.equals(u.getCodigoOD(), usuarioSession.getCodigoOD())){
                FacesContext context = FacesContext.getCurrentInstance();
                SeguridadUtilController seguridadUtilController = (SeguridadUtilController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "seguridadUtilController");
                if(seguridadUtilController.tieneRol("ROL0000002") || seguridadUtilController.tieneRol("ROL0000004")){
                    return esSupervisor = true;
                }
            }   
        } catch (Exception e) {
            log.error("ERROR - esSupervisor()" + e);
        }
        return esSupervisor = false;
    }

    public boolean respuestaAprobar() {
        try {
            if (StringUtils.isBlank(expedienteRespuestaAprueba.getDetalle())) {
                msg.messageAlert("Debe responder la consulta", null);
                return false;
            }
            if (StringUtils.isBlank(expedienteRespuestaAprueba.getAprueba())) {
                msg.messageAlert("Debe aprobar o desaprobar la respuesta", null);
                return false;
            }

            setExpedienteConsultaPadre(expedienteRespuestaAprueba);
            String rutaRespuesta = uploadArchive(file6);
            if (rutaRespuesta != null) {
                expedienteConsultaPadre.setRutaRespuesta(rutaRespuesta);
            }
            expedienteConsultaPadre.setCodigoUsuario(usuarioSession.getCodigo());
            expedienteConsultaPadre.setNombreUsuario(usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno());
            if (StringUtils.equals(expedienteConsultaPadre.getAprueba(), "NO")) {
                expedienteConsultaPadre.setEtapa(EtapaConsultaType.CONSULTA_ETAPA_RESPONDE.getKey());
                expedienteConsultaPadre.setEstado("INA");
            } else {
                expedienteConsultaPadre.setEtapa(EtapaConsultaType.RESPUESTA_ETAPA_APRUEBA.getKey());
                expedienteConsultaPadre.setEstado("ACT");
            }
            expedienteConsultaPadre.setEtapa(EtapaConsultaType.RESPUESTA_ETAPA_APRUEBA.getKey());
            expedienteConsultaPadre.setFecha(new Date());
            /**
             * ACTUALIZAR PADRE
             */
            expedienteConsultaService.expedienteConsultaUpdate(expedienteConsultaPadre);
            /**
             * GRABAR EL HIJO
             */
            expedienteRespuestaAprueba = expedienteConsultaPadre;
            expedienteRespuestaAprueba.setIdPadre(expedienteConsultaPadre.getId());
            expedienteRespuestaAprueba.setEstado("ACT");
            expedienteRespuestaAprueba.setEtapa(EtapaConsultaType.RESPUESTA_ETAPA_APRUEBA.getKey());
            expedienteRespuestaAprueba.setId(null);
            expedienteConsultaService.expedienteConsultaInsertar(expedienteRespuestaAprueba);
            /**
             *
             */
            if (StringUtils.equals(expedienteRespuestaAprueba.getAprueba(), "NO")) {
                enviarMensajeDesaprobarRespuesta();
                historial = new ExpedienteHistorial(HistorialType.HISTORIAL_CONSULTA_DESAPRUEBA_RESPUESTA.getKey(), HistorialType.HISTORIAL_CONSULTA_DESAPRUEBA_RESPUESTA.getValue());
                guardarHistorial(historial);
                msg.messageInfo("Se rechaza la consulta", null);
            } else {
                enviarMensajeAprobarRespuesta();
                historial = new ExpedienteHistorial(HistorialType.HISTORIAL_CONSULTA_APRUEBA_RESPUESTA.getKey(), HistorialType.HISTORIAL_CONSULTA_APRUEBA_RESPUESTA.getValue());
                guardarHistorial(historial);
                msg.messageInfo("Se aprobo la respuesta", null);
            }
            inicioAccionesConsulta();
            return true;
        } catch (Exception e) {
            log.error("ERROR - respuestaAprobar()" + e);
        }
        return false;
    }

    public boolean respuestaAceptar() {
        try {
            if (StringUtils.isBlank(expedienteRespuestaAcepta.getDetalle())) {
                msg.messageAlert("Debe responder la consulta", null);
                return false;
            }
            if (StringUtils.isBlank(expedienteRespuestaAcepta.getAprueba())) {
                msg.messageAlert("Debe aceptar o rechazar la respuesta", null);
                return false;
            }

            setExpedienteConsultaPadre(expedienteRespuestaAcepta);
            String rutaRespuesta = uploadArchive(file6);
            if (rutaRespuesta != null) {
                expedienteConsultaPadre.setRutaRespuesta(rutaRespuesta);
            }
            expedienteConsultaPadre.setCodigoUsuario(usuarioSession.getCodigo());
            expedienteConsultaPadre.setNombreUsuario(usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno());
            if (StringUtils.equals(expedienteConsultaPadre.getAprueba(), "NO")) {
                expedienteConsultaPadre.setEtapa(EtapaConsultaType.RESPUESTA_ETAPA_APRUEBA.getKey());
                expedienteConsultaPadre.setEstado("INA");
            } else {
                expedienteConsultaPadre.setEtapa(EtapaConsultaType.RESPUESTA_ETAPA_ACEPTA.getKey());
                expedienteConsultaPadre.setEstado("ACT");
            }
            expedienteConsultaPadre.setEtapa(EtapaConsultaType.RESPUESTA_ETAPA_ACEPTA.getKey());
            expedienteConsultaPadre.setFecha(new Date());
            /**
             * ACTUALIZAR PADRE
             */
            expedienteConsultaService.expedienteConsultaUpdate(expedienteConsultaPadre);
            /**
             * GRABAR EL HIJO
             */
            expedienteRespuestaAcepta = expedienteConsultaPadre;
            expedienteRespuestaAcepta.setIdPadre(expedienteConsultaPadre.getId());
            expedienteRespuestaAcepta.setEtapa(EtapaConsultaType.RESPUESTA_ETAPA_ACEPTA.getKey());
            expedienteRespuestaAcepta.setEstado("ACT");
            expedienteRespuestaAcepta.setId(null);
            expedienteConsultaService.expedienteConsultaInsertar(expedienteRespuestaAcepta);
            /**
             *
             */
            if (StringUtils.equals(expedienteRespuestaAcepta.getAprueba(), "NO")) {
                enviarMensajeRechazaRespuesta();
                historial = new ExpedienteHistorial(HistorialType.HISTORIAL_CONSULTA_RECHAZA_RESPUESTA.getKey(), HistorialType.HISTORIAL_CONSULTA_RECHAZA_RESPUESTA.getValue());
                guardarHistorial(historial);
                msg.messageInfo("Se rechaza la respuesta", null);
            } else {
                enviarMensajeAceptarRespuesta();
                historial = new ExpedienteHistorial(HistorialType.HISTORIAL_CONSULTA_ACEPTA_RESPUESTA.getKey(), HistorialType.HISTORIAL_CONSULTA_ACEPTA_RESPUESTA.getValue());
                guardarHistorial(historial);
                msg.messageInfo("Se acepta la respuesta", null);
            }

            inicioAccionesConsulta();
            return true;
        } catch (Exception e) {
            log.error("ERROR - respuestaAceptar()" + e);
        }
        return false;
    }

    public boolean enviarSuspencion() {
        try {
            if (StringUtils.isBlank(expedienteSuspencionEnvia.getDetalle())) {
                msg.messageAlert("Debe ingresar el detalle", null);
                return false;
            }

            if (expedienteSuspencionEnvia.getId() != null) {
                return false;
            }
            expedienteSuspencionEnvia.setIdExpediente(expediente.getId());
            expedienteSuspencionEnvia.setNumeroExpediente(expediente.getNumero());
            expedienteSuspencionEnvia.setEstado("ACT");
            expedienteSuspencionEnvia.setEtapa(EtapaSuspencionType.SUSPENCION_ETAPA_ENVIA.getKey());
            expedienteSuspencionEnvia.setCodigoUsuario(usuarioSession.getCodigo());
            expedienteSuspencionEnvia.setNombreUsuario(usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno());
            expedienteSuspencionEnvia.setFecha(new Date());
            String ruta = uploadArchive(file4);
            expedienteSuspencionEnvia.setRuta(ruta);
            expedienteSuspencionService.expedienteSuspencionInsertar(expedienteSuspencionEnvia);
            enviarMensajeSupensionEnvio();
            historial = new ExpedienteHistorial(HistorialType.HISTORIAL_SUSPENCION_ENVIA.getKey(), HistorialType.HISTORIAL_SUSPENCION_ENVIA.getValue());
            guardarHistorial(historial);
            msg.messageInfo("Se envio la Suspensión", null);
            return true;
        } catch (Exception e) {
            log.error("ERROR - enviarSuspencion()" + e);
        }
        return false;
    }

    public boolean aprobarSuspencion() {
        try {
            if (StringUtils.isBlank(expedienteSuspencionAprueba.getAprueba())) {
                msg.messageAlert("Debe aprobar o desaprobar la solicitud de Suspensión", null);
                return false;
            }
            if (StringUtils.isBlank(expedienteSuspencionAprueba.getDetalle())) {
                msg.messageAlert("Debe ingresar el detalle", null);
                return false;
            }
            expedienteSuspencionAprueba.setIdExpediente(expediente.getId());
            expedienteSuspencionAprueba.setNumeroExpediente(expediente.getNumero());
            expedienteSuspencionAprueba.setEstado("ACT");
            expedienteSuspencionAprueba.setEtapa(EtapaSuspencionType.SUSPENCION_ETAPA_APRUEBA.getKey());
            expedienteSuspencionAprueba.setCodigoUsuario(usuarioSession.getCodigo());
            expedienteSuspencionAprueba.setNombreUsuario(usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno());
            expedienteSuspencionAprueba.setFecha(new Date());
            String ruta = uploadArchive(file4);
            expedienteSuspencionAprueba.setRuta(ruta);
            expedienteSuspencionService.expedienteSuspencionInsertar(expedienteSuspencionAprueba);
            if (StringUtils.equals(expedienteSuspencionAprueba.getAprueba(), "SI")) {
                enviarMensajeSuspencionAprobacion();
                historial = new ExpedienteHistorial(HistorialType.HISTORIAL_SUSPENCION_APRUEBA.getKey(), HistorialType.HISTORIAL_SUSPENCION_APRUEBA.getValue());
                guardarHistorial(historial);
                msg.messageInfo("Se aprobó la solicitud de Suspensión", null);
            } else {
                guardarVersion2();
                enviarMensajeSuspencionDesaprobacion();
                historial = new ExpedienteHistorial(HistorialType.HISTORIAL_SUSPENCION_DESAPRUEBA.getKey(), HistorialType.HISTORIAL_SUSPENCION_DESAPRUEBA.getValue());
                guardarHistorial(historial);
                msg.messageInfo("No se aprobo la solicitud de Suspensión", null);
            }
            return true;
        } catch (Exception e) {
            log.error("ERROR - aprobarSuspencion()" + e);
        }
        return false;
    }

    public boolean aceptarSuspencion() {
        try {
            if (StringUtils.isBlank(expedienteSuspencionAcepta.getAprueba())) {
                msg.messageAlert("Debe aceptar o rechazar la solicitud de Suspensión", null);
                return false;
            }

            if (StringUtils.isBlank(expedienteSuspencionAcepta.getDetalle())) {
                msg.messageAlert("Debe ingresar el detalle", null);
                return false;
            }

            expedienteSuspencionAcepta.setIdExpediente(expediente.getId());
            expedienteSuspencionAcepta.setNumeroExpediente(expediente.getNumero());
            expedienteSuspencionAcepta.setEstado("ACT");
            expedienteSuspencionAcepta.setEtapa(EtapaSuspencionType.SUSPENCION_ETAPA_ACEPTA.getKey());
            expedienteSuspencionAcepta.setCodigoUsuario(usuarioSession.getCodigo());
            expedienteSuspencionAcepta.setNombreUsuario(usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno());
            expedienteSuspencionAcepta.setFecha(new Date());
            expedienteSuspencionService.expedienteSuspencionInsertar(expedienteSuspencionAcepta);
            if (StringUtils.equals(expedienteSuspencionAcepta.getAprueba(), "SI")) {
                guardarVersion2();
                enviarMensajeSuspencionAcepta();
                aumentarDisminuirTiempoEtapa(1, 20);
                historial = new ExpedienteHistorial(HistorialType.HISTORIAL_SUSPENCION_ACEPTA.getKey(), HistorialType.HISTORIAL_SUSPENCION_ACEPTA.getValue());
                guardarHistorial(historial);
                msg.messageInfo("Se acepta la solicitud de Suspensión se agregan 20 dias habiles en la etapa actual", null);
            } else {
                guardarVersion2();
                enviarMensajeSuspencionRechaza();
                historial = new ExpedienteHistorial(HistorialType.HISTORIAL_SUSPENCION_RECHAZA.getKey(), HistorialType.HISTORIAL_SUSPENCION_RECHAZA.getValue());
                guardarHistorial(historial);
                msg.messageInfo("Se rechaza la solicitud de Suspensión", null);
            }
            return true;
        } catch (Exception e) {
            log.error("ERROR - aceptarSuspencion()" + e);
        }
        return false;
    }

    public boolean enviarAmpliacion() {
        try {
            if (StringUtils.isBlank(expedienteAmpliacionEnvia.getDetalle())) {
                msg.messageAlert("Debe ingresar el detalle", null);
                return false;
            }
            if (expedienteAmpliacionEnvia.getId() != null) {
                return false;
            }
            expedienteAmpliacionEnvia.setIdExpediente(expediente.getId());
            expedienteAmpliacionEnvia.setNumeroExpediente(expediente.getNumero());
            expedienteAmpliacionEnvia.setEstado("ACT");
            expedienteAmpliacionEnvia.setEtapa(EtapaAmpliacionType.AMPLIACION_ETAPA_ENVIA.getKey());
            expedienteAmpliacionEnvia.setCodigoUsuario(usuarioSession.getCodigo());
            expedienteAmpliacionEnvia.setNombreUsuario(usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno());
            expedienteAmpliacionEnvia.setFecha(new Date());
            String ruta = uploadArchive(file4);
            expedienteAmpliacionEnvia.setRuta(ruta);
            expedienteAmpliacionService.expedienteAmpliacionInsertar(expedienteAmpliacionEnvia);
            enviarMensajeAmpliacionEnvio();
            historial = new ExpedienteHistorial(HistorialType.HISTORIAL_AMPLIACION_ENVIA.getKey(), HistorialType.HISTORIAL_AMPLIACION_ENVIA.getValue());
            guardarHistorial(historial);
            msg.messageInfo("Se envio la solicitud de ampliación", null);
            return true;
        } catch (Exception e) {
            log.error("ERROR - enviarAmpliacion()" + e);
        }
        return false;
    }

    public boolean aprobarAmpliacion() {
        try {
            if (StringUtils.isBlank(expedienteAmpliacionAprueba.getAprueba())) {
                msg.messageAlert("Debe aprobar o desaprobar la solicitud de ampliación", null);
                return false;
            }
            if (StringUtils.isBlank(expedienteAmpliacionAprueba.getDetalle())) {
                msg.messageAlert("Debe ingresar el detalle", null);
                return false;
            }
            expedienteAmpliacionAprueba.setIdExpediente(expediente.getId());
            expedienteAmpliacionAprueba.setNumeroExpediente(expediente.getNumero());
            expedienteAmpliacionAprueba.setEstado("ACT");
            expedienteAmpliacionAprueba.setEtapa(EtapaAmpliacionType.AMPLIACION_ETAPA_APRUEBA.getKey());
            expedienteAmpliacionAprueba.setCodigoUsuario(usuarioSession.getCodigo());
            expedienteAmpliacionAprueba.setNombreUsuario(usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno());
            expedienteAmpliacionAprueba.setFecha(new Date());
            String ruta = uploadArchive(file4);
            expedienteAmpliacionAprueba.setRuta(ruta);
            expedienteAmpliacionService.expedienteAmpliacionInsertar(expedienteAmpliacionAprueba);
            if (StringUtils.equals(expedienteAmpliacionAprueba.getAprueba(), "SI")) {
                enviarMensajeAmpliacionAprobacion();
                historial = new ExpedienteHistorial(HistorialType.HISTORIAL_AMPLIACION_APRUEBA.getKey(), HistorialType.HISTORIAL_AMPLIACION_APRUEBA.getValue());
                guardarHistorial(historial);
                msg.messageInfo("Se aprobó la ampliación", null);
            } else {
                guardarVersion2();
                enviarMensajeAmpliacionDesaprobacion();
                historial = new ExpedienteHistorial(HistorialType.HISTORIAL_AMPLIACION_DESAPRUEBA.getKey(), HistorialType.HISTORIAL_AMPLIACION_DESAPRUEBA.getValue());
                guardarHistorial(historial);
                msg.messageInfo("Se desaprobo la ampliación", null);
            }
            return true;
        } catch (Exception e) {
            log.error("ERROR - aprobarAmpliacion()" + e);
        }
        return false;
    }

    public boolean aceptarAmpliacion() {
        try {
            if (StringUtils.isBlank(expedienteAmpliacionAcepta.getAprueba())) {
                msg.messageAlert("Debe aceptar o rechazar la solicitud de ampliación", null);
                return false;
            }
            if (StringUtils.isBlank(expedienteAmpliacionAcepta.getDetalle())) {
                msg.messageAlert("Debe ingresar el detalle", null);
                return false;
            }
            expedienteAmpliacionAcepta.setIdExpediente(expediente.getId());
            expedienteAmpliacionAcepta.setNumeroExpediente(expediente.getNumero());
            expedienteAmpliacionAcepta.setEstado("ACT");
            expedienteAmpliacionAcepta.setEtapa(EtapaAmpliacionType.AMPLIACION_ETAPA_ACEPTA.getKey());
            expedienteAmpliacionAcepta.setCodigoUsuario(usuarioSession.getCodigo());
            expedienteAmpliacionAcepta.setNombreUsuario(usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno());
            expedienteAmpliacionAcepta.setFecha(new Date());
            expedienteAmpliacionService.expedienteAmpliacionInsertar(expedienteAmpliacionAcepta);
            if (StringUtils.equals(expedienteAmpliacionAcepta.getAprueba(), "SI")) {
                guardarVersion2();
                enviarMensajeAmpliacionAcepta();
                aumentarDisminuirTiempoEtapa(1, 30);
                historial = new ExpedienteHistorial(HistorialType.HISTORIAL_AMPLIACION_ACEPTA.getKey(), HistorialType.HISTORIAL_AMPLIACION_ACEPTA.getValue());
                guardarHistorial(historial);
                msg.messageInfo("Se acepta la solicitud de ampliación se agregan 30 dias habiles en la etapa actual", null);
            } else {
                guardarVersion2();
                enviarMensajeAmpliacionRechaza();
                historial = new ExpedienteHistorial(HistorialType.HISTORIAL_AMPLIACION_RECHAZA.getKey(), HistorialType.HISTORIAL_AMPLIACION_RECHAZA.getValue());
                guardarHistorial(historial);
                msg.messageInfo("Se rechaza la solicitud de ampliación", null);
            }
            return true;
        } catch (Exception e) {
            log.error("ERROR - aceptarAmpliacion()" + e);
        }
        return false;
    }

    private void enviarMensajeConsulta() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
            bandejaController.mensajeEnviaConsulta(expedienteConsultaEnvia);
        } catch (Exception e) {
            log.error("ERROR - enviarMensajeConsulta()" + e);
        }
    }

    private void enviarMensajeAprobacionConsulta() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
            bandejaController.mensajeEnviaConsultaAprobacion(expedienteConsultaAprueba);
        } catch (Exception e) {
            log.error("ERROR - enviarMensajeAprobacionConsulta()" + e);
        }
    }

    private void enviarMensajeDesaprobacionConsulta() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
            bandejaController.mensajeEnviaConsultaDesaprobacion(expedienteConsultaAprueba);
        } catch (Exception e) {
            log.error("ERROR - enviarMensajeDesaprobacionConsulta()" + e);
        }
    }

    private void enviarMensajeReasignacionConsulta() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
            bandejaController.mensajeEnviaConsultaReasignacion(expedienteConsultaReasigna);
        } catch (Exception e) {
            log.error("ERROR - enviarMensajeReasignacionConsulta()" + e);
        }
    }

    private void enviarMensajeDesaprobacionReasignacionConsulta() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
            bandejaController.mensajeEnviaDesapruebaConsultaReasignacion(expedienteConsultaReasigna);
        } catch (Exception e) {
            log.error("ERROR - enviarMensajeDesaprobacionReasignacionConsulta()" + e);
        }
    }

    private void enviarMensajeRespondeConsulta() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
            bandejaController.mensajeEnviaConsultaResponde(expedienteConsultaResponde);
        } catch (Exception e) {
            log.error("ERROR - enviarMensajeRespondeConsulta()" + e);
        }
    }

    private void enviarMensajeAprobarRespuesta() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
            bandejaController.mensajeEnviaAprobarRespuesta(expedienteRespuestaAprueba);
        } catch (Exception e) {
            log.error("ERROR - enviarMensajeAprobarRespuesta()" + e);
        }
    }

    private void enviarMensajeDesaprobarRespuesta() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
            bandejaController.mensajeEnviaDesaprobarRespuesta(expedienteRespuestaAprueba);
        } catch (Exception e) {
            log.error("ERROR - enviarMensajeDesaprobarRespuesta()" + e);
        }
    }

    private void enviarMensajeAceptarRespuesta() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
            bandejaController.mensajeEnviaAceptarRespuesta(expedienteRespuestaAcepta);
        } catch (Exception e) {
            log.error("ERROR - enviarMensajeAceptarRespuesta()" + e);
        }
    }

    private void enviarMensajeRechazaRespuesta() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
            bandejaController.mensajeEnviaRechazarRespuesta(expedienteRespuestaAcepta);
        } catch (Exception e) {
            log.error("ERROR - enviarMensajeRechazaRespuesta()" + e);
        }
    }

    private void enviarMensajeDerivacion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
            bandejaController.mensajeEnviaDerivacion(expedienteDerivacionEnvia);
        } catch (Exception e) {
            log.error("ERROR - enviarMensajeDerivacion()" + e);
        }
    }
    
    private void enviarMensajeAsignacion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
            bandejaController.mensajeEnviaAsignacion(expediente);
        } catch (Exception e) {
            log.error("ERROR - enviarMensajeDerivacion()" + e);
        }
    }

    private void enviarMensajeAprobacion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
            bandejaController.mensajeEnviaAprobacion(expedienteDerivacionAprueba);
        } catch (Exception e) {
            log.error("ERROR - enviarMensajeAprobacion()" + e);
        }
    }

    private void enviarMensajeDesaprobacion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
            bandejaController.mensajeEnviaDesaprobacion(expedienteDerivacionAprueba, expediente);
        } catch (Exception e) {
            log.error("ERROR - enviarMensajeDesaprobacion()" + e);
        }
    }

    private void enviarMensajeReasignacion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
            bandejaController.mensajeEnviaReasignacion(expedienteDerivacionReasigna, expediente);
        } catch (Exception e) {
            log.error("ERROR - enviarMensajeReasignacion()" + e);
        }
    }

    private void enviarMensajeReasignacionDesaprobada() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
            bandejaController.mensajeEnviaReasignacionDesaprobada(expedienteDerivacionReasigna, expedienteDerivacionAprueba.getCodigoUsuario(), expediente);
        } catch (Exception e) {
            log.error("ERROR - enviarMensajeReasignacionDesaprobada()" + e);
        }
    }

    private void enviarMensajeSupensionEnvio() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
            bandejaController.mensajeEnviaSuspencionEnvia(expedienteSuspencionEnvia);
        } catch (Exception e) {
            log.error("ERROR - enviarMensajeSupensionEnvio()" + e);
        }
    }

    private void enviarMensajeSuspencionAprobacion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
            bandejaController.mensajeEnviaSuspencionAprobacion(expedienteSuspencionAprueba);
        } catch (Exception e) {
            log.error("ERROR - enviarMensajeSuspencionAprobacion()" + e);
        }
    }

    private void enviarMensajeSuspencionDesaprobacion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
            bandejaController.mensajeEnviaSuspencionDesaprobacion(expedienteSuspencionEnvia, expedienteSuspencionAprueba, expediente);
        } catch (Exception e) {
            log.error("ERROR - enviarMensajeSuspencionDesaprobacion()" + e);
        }
    }

    private void enviarMensajeSuspencionAcepta() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
            bandejaController.mensajeEnviaSuspencionAcepta(expedienteSuspencionAcepta, expedienteSuspencionEnvia, expedienteSuspencionAprueba);
        } catch (Exception e) {
            log.error("ERROR - enviarMensajeSuspencionAcepta()" + e);
        }
    }

    private void enviarMensajeSuspencionRechaza() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
            bandejaController.mensajeEnviaSuspencionRechaza(expedienteSuspencionAcepta, expedienteSuspencionEnvia, expedienteSuspencionAprueba, expediente);
        } catch (Exception e) {
            log.error("ERROR - enviarMensajeSuspencionRechaza()" + e);
        }
    }

    private void enviarMensajeAmpliacionEnvio() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
            bandejaController.mensajeEnviaAmpliacionEnvia(expedienteAmpliacionEnvia);
        } catch (Exception e) {
            log.error("ERROR - enviarMensajeAmpliacionEnvio()" + e);
        }
    }

    private void enviarMensajeAmpliacionAprobacion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
            bandejaController.mensajeEnviaAmpliacionAprobacion(expedienteAmpliacionAprueba);
        } catch (Exception e) {
            log.error("ERROR - enviarMensajeAmpliacionAprobacion()" + e);
        }
    }

    private void enviarMensajeAmpliacionDesaprobacion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
            bandejaController.mensajeEnviaAmpliacionDesaprobacion(expedienteAmpliacionEnvia, expedienteAmpliacionAprueba, expediente);
        } catch (Exception e) {
            log.error("ERROR - enviarMensajeAmpliacionDesaprobacion()" + e);
        }
    }

    private void enviarMensajeAmpliacionAcepta() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
            bandejaController.mensajeEnviaAmpliacionAcepta(expedienteAmpliacionAcepta, expedienteAmpliacionEnvia, expedienteAmpliacionAprueba);
        } catch (Exception e) {
            log.error("ERROR - enviarMensajeAmpliacionAcepta()" + e);
        }
    }

    private void enviarMensajeAmpliacionRechaza() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
            bandejaController.mensajeEnviaAmpliacionRechaza(expedienteAmpliacionAcepta, expedienteAmpliacionEnvia, expedienteAmpliacionAprueba, expediente);
        } catch (Exception e) {
            log.error("ERROR - enviarMensajeAmpliacionRechaza()" + e);
        }
    }

    public String registarExpedienteGestion() {
        try {
            if (StringUtils.isBlank(expedienteGestion.getTipo())) {
                msg.messageAlert("Debe seleccionar el tipo de acción", null);
                return null;
            }
            if (StringUtils.isBlank(expedienteGestion.getDescripcion())) {
                msg.messageAlert("Debe ingresar la descripción", null);
                return null;
            }
            String ruta1 = null;
            String ruta2 = null;
            if(file1 != null)
                ruta1 = uploadArchive(file1);
            if(file2 != null)
                ruta2 = uploadArchive(file2);
            expedienteGestion.setRuta1(ruta1);
            expedienteGestion.setRuta2(ruta2);
            if (StringUtils.isBlank(expedienteGestion.getCodigoGestion())) {
                DateFormat format = new SimpleDateFormat("yyMMddHHmmss");
                String formato = format.format(new Date());
                expedienteGestion.setUsuarioRegistro(usuarioSession.getCodigo());
                expedienteGestion.setFechaRegistro(new Date());
                expedienteGestion.setCodigoGestion("GES" + formato);
                expedienteGestion.setFecha(new Date());
                expedienteGestionService.expedienteGestionInsertar(expedienteGestion);
                guardarGestionEtapa();
                msg.messageInfo("Se registro una nueva gestión", null);
                historial = new ExpedienteHistorial(HistorialType.HISTORIAL_GESTION_GUARDAR.getKey(), HistorialType.HISTORIAL_GESTION_GUARDAR.getValue());
                guardarHistorial(historial);
            } else {
                if (expedienteGestion.getFechaRecepcion() != null) {
                    if (sumarRestarDiasFecha(expedienteGestion.getFechaRecepcion(), 1).compareTo(expedienteGestion.getFecha()) < 0) {
                        expedienteGestion.setFechaRecepcion(null);
                        msg.messageAlert("La fecha de recepción no puede ser menos que la fecha de registro de la gestión", null);
                        return null;
                    }
                }
                expedienteGestion.setUsuarioModificacion(usuarioSession.getCodigo());
                expedienteGestion.setFechaModificacion(new Date());
                if (StringUtils.isNotBlank(expedienteGestion.getCodigoONP())) {
                    expedienteGestion.setCodigoONP(null);
                }
                expedienteGestionService.expedienteGestionUpdate(expedienteGestion);
                msg.messageInfo("Se actualizo la gestión", null);
                historial = new ExpedienteHistorial(HistorialType.HISTORIAL_GESTION_MODIFICAR.getKey(), HistorialType.HISTORIAL_GESTION_MODIFICAR.getValue());
                guardarHistorial(historial);
            }
            return cargarExpedienteGestionLista();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ERROR - registarExpedienteGestion()" + e);
        }
        return null;
    }

    private Date sumarRestarDiasFecha(Date fecha, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe	
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0	
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos	
    }

    private String detalleUltimoEstado(String numeroExpediente) {
        try {
            String retornaDetalle = etapaEstadoService.etapaEstadoUltimoEstado(numeroExpediente);
            return retornaDetalle;
        } catch (Exception e) {
            log.error("ERROR - detalleUltimoEstado()" + e);
        }
        return null;
    }

    public String setearExpedienteGestion(ExpedienteGestion eg) {
        listaExpedienteXUsuarioPaginadoReplica = null;
        setExpedienteGestion(eg);
        expedienteBusquedaReplica = new Expediente();
        return "expedienteGestion";
    }

    public String verExpedienteGestion(ExpedienteGestion eg) {
        listaExpedienteXUsuarioPaginadoReplica = null;
        setExpedienteGestion(eg);
        expedienteBusquedaReplica = new Expediente();
        return "expedienteGestionVer";
    }

    private void guardarGestionEtapa() {
        try {
            GestionEtapa ge = new GestionEtapa(expedienteGestion.getId(), expediente.getId(), etapaEstado.getVerEtapa(), expediente.getNumero());
            gestionEtapaService.gestionEtapaInsertar(ge);
        } catch (Exception e) {
            log.error("ERROR - guardarGestionEtapa()" + e);
        }
    }

    public void listarExpedienteUsuarioPaginado(Integer pagina) {
        try {
            Expediente e = new Expediente();
            e.setTipoBusqueda(tipoBusqueda);
            if (pagina > 0) {
                int paginado = ConstantesUtil.PAGINADO_10;
                Integer ini = paginado * (pagina - 1) + 1;
                Integer fin = paginado * pagina;
                if (pagina == 0) {
                    ini = 1;
                    fin = 10;
                }
                e.setUsuarioRegistro(usuarioSession.getCodigo());
                e.setIni(ini);
                e.setFin(fin);
                FacesContext context = FacesContext.getCurrentInstance();
                SeguridadUtilController seguridadUtilController = (SeguridadUtilController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "seguridadUtilController");
                List<Expediente> list;
                if (seguridadUtilController.tieneRecurso("REC_EXP008")) {
                    e.setIdOficinaDefensorial(usuarioSession.getCodigoOD());
                    list = expedienteService.expedienteBuscarUsuarioODPaginado(e);
                } else {
                    e.setUsuarioRegistro(usuarioSession.getCodigo());
                    list = expedienteService.expedienteBuscarUsuarioPaginado(e);
                }
                if (list.size() > 0) {
                    for (Expediente e1 : list) {
                        e1.setEtapaDetalle(devolverEtapa(e1));
                        if (StringUtils.isNoneBlank(e1.getNumero())) {
                            e1.setEstadoDetalle(detalleUltimoEstado(e1.getNumero()));
                        }
                    }
                    listaExpedienteXUsuarioPaginado = list;
                    nroPagina = pagina;
                }
            }
        } catch (Exception e) {
            log.error("ERROR - listarExpedienteUsuarioPaginado()" + e);
        }
    }

    public void listarExpedienteUsuarioPaginadoOrder(Integer pagina, int tipo) {
        try {
            if (tipoBusqueda != null) {
                if (tipo == tipoBusqueda) {
                    tipoBusqueda = tipo * (-1);
                } else {
                    tipoBusqueda = tipo;
                }
            } else {
                tipoBusqueda = tipo;
            }
            Expediente e = new Expediente();
            e.setTipoBusqueda(tipoBusqueda);
            if (pagina > 0) {
                int paginado = ConstantesUtil.PAGINADO_10;
                Integer ini = paginado * (pagina - 1) + 1;
                Integer fin = paginado * pagina;
                e.setIni(ini);
                e.setFin(fin);
                List<Expediente> list;
                FacesContext context = FacesContext.getCurrentInstance();
                SeguridadUtilController seguridadUtilController = (SeguridadUtilController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "seguridadUtilController");
                if (seguridadUtilController.tieneRecurso("REC_EXP008")) {
                    e.setIdOficinaDefensorial(usuarioSession.getCodigoOD());
                    list = expedienteService.expedienteBuscarUsuarioODPaginado(e);
                } else {
                    e.setUsuarioRegistro(usuarioSession.getCodigo());
                    list = expedienteService.expedienteBuscarUsuarioPaginado(e);
                }

                if (list.size() > 0) {
                    for(Expediente e1 : list) {
                        e1.setEtapaDetalle(devolverEtapa(e1));
                        if (StringUtils.isNoneBlank(e1.getNumero())) {
                            e1.setEstadoDetalle(detalleUltimoEstado(e1.getNumero()));
                        }
                    }
                    listaExpedienteXUsuarioPaginado = list;
                    nroPagina = pagina;
                }
            }
        } catch (Exception e) {
            log.error("ERROR - listarExpedienteUsuarioPaginadoOrder()" + e);
        }
    }

    public void listarExpedienteUsuarioPaginado(Integer pagina, Expediente e) {
        try {
            if (pagina > 0) {
                int paginado = ConstantesUtil.PAGINADO_10;
                Integer ini = paginado * (pagina - 1) + 1;
                Integer fin = paginado * pagina;
                if (pagina == 0) {
                    ini = 1;
                    fin = 10;
                }
                e.setUsuarioRegistro(usuarioSession.getCodigo());
                e.setIni(ini);
                e.setFin(fin);

                List<Expediente> list = expedienteService.expedienteBuscarUsuarioPaginado(e);
                if (list.size() > 0) {
                    for (Expediente e1 : list) {
                        e1.setEtapaDetalle(devolverEtapa(e1));
                        if (StringUtils.isNoneBlank(e1.getNumero())) {
                            e1.setEstadoDetalle(detalleUltimoEstado(e1.getNumero()));
                        }
                    }
                    listaExpedienteXUsuarioPaginado = list;
                    nroPagina = pagina;
                }
            }
        } catch (Exception ex) {
            log.error("ERROR - listarExpedienteUsuarioPaginado()" + ex);
        }
    }

    public void listarExpedienteUsuarioPaginadoCompleto(Integer pagina, Expediente e) {
        try {
            listaExpedienteXUsuarioPaginadoReplica = new ArrayList<>();
            if (pagina > 0) {
                e.setUsuarioRegistro(usuarioSession.getCodigo());
                listaExpedienteXUsuarioPaginadoReplica = expedienteService.expedienteBuscarUsuarioCompleto(e);
                if (listaExpedienteXUsuarioPaginadoReplica.size() > 0) {
                    for (Expediente e1 : listaExpedienteXUsuarioPaginadoReplica) {
                        e1.setEtapaDetalle(devolverEtapa(e1));
                        if (StringUtils.isNoneBlank(e1.getNumero())) {
                            e1.setEstadoDetalle(detalleUltimoEstado(e1.getNumero()));
                        }
                    }
                    listarExpedienteUsuarioPaginadoCompletoPagina(pagina);
                }
            }
        } catch (Exception ex) {
            log.error("ERROR - listarExpedienteUsuarioPaginadoCompleto()" + ex);
        }
    }

    public void gestionesPorExpediente(String numeroExpediente) {
        try {
            listaGestionesParaReplica = null;
            listaGestionesParaReplica = expedienteGestionService.expedienteGestionListaXexpediente(numeroExpediente);
        } catch (Exception e) {
            log.error("ERROR - gestionesPorExpediente()" + e);
        }

    }

    public boolean insertarReplicaGestion1() {
        int i = 0;
        int j = 0;
        for (Expediente e : listaExpedienteXUsuarioPaginadoReplica) {
            if (e.getIndReplica() != null){
                if (e.getIndReplica()) {
                    j++;
                }
            }
        }
        if(j == 0){
            msg.messageAlert("No ha seleccionado ninguna gestión", null);
            return false;
        }
        
        for (Expediente e : listaExpedienteXUsuarioPaginadoReplica) {
            try {
                if (e.getIndReplica() != null) {
                    if (e.getIndReplica()) {
                        ExpedienteGestion eg = new ExpedienteGestion();
                        eg.setTipo(expedienteGestion.getTipo());
                        eg.setFecha(expedienteGestion.getFecha());
                        eg.setDocumentoGestion(expedienteGestion.getDocumentoGestion());
                        eg.setFechaRecepcion(expedienteGestion.getFechaRecepcion());
                        eg.setDescripcion(expedienteGestion.getDescripcion());
                        eg.setIdEntidad(expedienteGestion.getIdEntidad());
                        eg.setIndEntidadQuejada(expedienteGestion.getIndEntidadQuejada());
                        eg.setNombre(expedienteGestion.getNombre());
                        eg.setApellidoPaterno(expedienteGestion.getApellidoPaterno());
                        eg.setApellidoMaterno(expedienteGestion.getApellidoMaterno());
                        eg.setCargo(expedienteGestion.getCargo());
                        eg.setDocumentoGestion(expedienteGestion.getDocumentoGestion());
                        eg.setRuta1(expedienteGestion.getRuta1());
                        eg.setCodigoGestionOrigen(expedienteGestion.getCodigoGestion());
                        DateFormat format = new SimpleDateFormat("yyMMddHHmmss");
                        String formato = format.format(new Date());
                        eg.setUsuarioRegistro(usuarioSession.getCodigo());
                        eg.setFechaRegistro(new Date());
                        eg.setCodigoGestion("GES" + formato + i);
                        i++;
                        expedienteGestionService.expedienteGestionInsertar(eg);
                        /*gestion etapa*/
                        inicializarEtapaEstado(1, e);
                        GestionEtapa ge = new GestionEtapa(eg.getId(), e.getId(), etapaEstado.getVerEtapa(), e.getNumero());
                        gestionEtapaService.gestionEtapaInsertar(ge);
                    }
                }
            } catch (Exception ex) {
                log.error("ERROR - insertarReplicaGestion1()" + ex);
            }
        }
        listarExpedienteUsuarioPaginadoCompleto(1, expedienteBusquedaReplica);
        msg.messageInfo("Se realizaron las replicas", null);
        return true;
    }

    public void actualizarReplicaGestion() {
        try {
            if (idGestionReplica != null) {
                ExpedienteGestion eg = expedienteGestionService.expedienteGestionBuscarOne(idGestionReplica);
                eg.setFechaModificacion(new Date());
                eg.setUsuarioModificacion(usuarioSession.getCodigo());
                eg.setRespuesta(expedienteGestion.getRespuesta());
                eg.setFechaRespuesta(expedienteGestion.getFechaRespuesta());
                eg.setDocumentoRespuesta(expedienteGestion.getDocumentoRespuesta());
                eg.setTipoCalidad(expedienteGestion.getTipoCalidad());
                eg.setDetalleRespuesta(expedienteGestion.getDetalleRespuesta());
                eg.setObservacionRespuesta(expedienteGestion.getObservacionRespuesta());
                eg.setRuta2(expedienteGestion.getRuta2());
                eg.setCodigoGestionRespuesta(expedienteGestion.getCodigoGestion());
                expedienteGestionService.expedienteGestionUpdate(eg);
            }
            msg.messageInfo("Se actualizo la respuesta", null);
        } catch (Exception e) {
            log.error("ERROR - actualizarReplicaGestion()" + e);
        }
    }

    public boolean listarExpedienteUsuarioPaginadoCompletoPagina(Integer pagina) {
        if (pagina > 0) {
            Integer inicio = ConstantesUtil.PAGINADO_10 * (pagina - 1);
            if (inicio > listaExpedienteXUsuarioPaginadoReplica.size()) {
                return false;
            } else {
                inicioPaginado = inicio;
                finPaginado = ConstantesUtil.PAGINADO_10 * pagina;
            }

            if (finPaginado > listaExpedienteXUsuarioPaginadoReplica.size()) {
                finPaginado = listaExpedienteXUsuarioPaginadoReplica.size();
            }
            nroPagina = pagina;
        }
        return true;
    }

    private String devolverEtapa(Expediente e) {
        try {
            String detalleEtapa = null;
            if (e.getIdEtapa() != null) {
                if (e.getIdEtapa() > 0) {
                    if (StringUtils.equals(e.getIndicadorEtapa(), "VIG")) {
                        if (StringUtils.equals(e.getEstado(), "C")) {
                            if (Objects.equals(e.getIdEtapa(), EtapaType.CALIFICACION_QUEJA.getKey())) {
                                detalleEtapa = EtapaType.CALIFICACION_QUEJA.getValue();
                            }
                            if (Objects.equals(e.getIdEtapa(), EtapaType.INVESTIGACION_QUEJA.getKey())) {
                                detalleEtapa = EtapaType.INVESTIGACION_QUEJA.getValue();
                            }
                            if (Objects.equals(e.getIdEtapa(), EtapaType.PERSUACION_QUEJA.getKey())) {
                                detalleEtapa = EtapaType.PERSUACION_QUEJA.getValue();
                            }
                            if (Objects.equals(e.getIdEtapa(), EtapaType.SEGUIMIENTO_QUEJA.getKey())) {
                                detalleEtapa = EtapaType.SEGUIMIENTO_QUEJA.getValue();
                            }
                            if (Objects.equals(e.getIdEtapa(), EtapaType.CALIFICACION_PETITORIO.getKey())) {
                                detalleEtapa = EtapaType.CALIFICACION_PETITORIO.getValue();
                            }
                            if (Objects.equals(e.getIdEtapa(), EtapaType.GESTION_PETITORIO.getKey())) {
                                detalleEtapa = EtapaType.GESTION_PETITORIO.getValue();
                            }
                            if (Objects.equals(e.getIdEtapa(), EtapaType.PERSUASION_PETITORIO.getKey())) {
                                detalleEtapa = EtapaType.PERSUASION_PETITORIO.getValue();
                            }
                        } else {
                            if (Objects.equals(e.getIdEtapa(), EtapaType.CALIFICACION_QUEJA.getKey())) {
                                detalleEtapa = EtapaType.INVESTIGACION_QUEJA.getValue();
                            }
                            if (Objects.equals(e.getIdEtapa(), EtapaType.INVESTIGACION_QUEJA.getKey())) {
                                detalleEtapa = EtapaType.PERSUACION_QUEJA.getValue();
                            }
                            if (Objects.equals(e.getIdEtapa(), EtapaType.PERSUACION_QUEJA.getKey())) {
                                detalleEtapa = EtapaType.SEGUIMIENTO_QUEJA.getValue();
                            }
                            if (Objects.equals(e.getIdEtapa(), EtapaType.SEGUIMIENTO_QUEJA.getKey())) {
                                detalleEtapa = EtapaType.SEGUIMIENTO_QUEJA.getValue();
                            }
                            if (Objects.equals(e.getIdEtapa(), EtapaType.CALIFICACION_PETITORIO.getKey())) {
                                detalleEtapa = EtapaType.GESTION_PETITORIO.getValue();
                            }
                            if (Objects.equals(e.getIdEtapa(), EtapaType.GESTION_PETITORIO.getKey())) {
                                detalleEtapa = EtapaType.PERSUASION_PETITORIO.getValue();
                            }
                            if (Objects.equals(e.getIdEtapa(), EtapaType.PERSUASION_PETITORIO.getKey())) {
                                detalleEtapa = EtapaType.PERSUASION_PETITORIO.getValue();
                            }
                        }
                    } else {
                        if (Objects.equals(e.getIdEtapa(), EtapaType.CALIFICACION_QUEJA.getKey())) {
                            detalleEtapa = EtapaType.CALIFICACION_QUEJA.getValue();
                        }
                        if (Objects.equals(e.getIdEtapa(), EtapaType.INVESTIGACION_QUEJA.getKey())) {
                            detalleEtapa = EtapaType.INVESTIGACION_QUEJA.getValue();
                        }
                        if (Objects.equals(e.getIdEtapa(), EtapaType.PERSUACION_QUEJA.getKey())) {
                            detalleEtapa = EtapaType.PERSUACION_QUEJA.getValue();
                        }
                        if (Objects.equals(e.getIdEtapa(), EtapaType.SEGUIMIENTO_QUEJA.getKey())) {
                            detalleEtapa = EtapaType.SEGUIMIENTO_QUEJA.getValue();
                        }
                        if (Objects.equals(e.getIdEtapa(), EtapaType.CALIFICACION_PETITORIO.getKey())) {
                            detalleEtapa = EtapaType.CALIFICACION_PETITORIO.getValue();
                        }
                        if (Objects.equals(e.getIdEtapa(), EtapaType.GESTION_PETITORIO.getKey())) {
                            detalleEtapa = EtapaType.GESTION_PETITORIO.getValue();
                        }
                        if (Objects.equals(e.getIdEtapa(), EtapaType.PERSUASION_PETITORIO.getKey())) {
                            detalleEtapa = EtapaType.PERSUASION_PETITORIO.getValue();
                        }
                    }
                }
            }
            return detalleEtapa;
        } catch (Exception ex) {
            log.error("ERROR - devolverEtapa()" + ex);
        }
        return null;
    }

    private void cargarGraficos001() {
        try {
            StringBuilder sb = new StringBuilder();
            int queja = 0;
            int petitorio = 0;
            int consulta = 0;
            for (Expediente e : listaExpedienteXUsuario) {
                if (e.getTipoClasificion().equals("01")) {
                    queja++;
                }
                if (e.getTipoClasificion().equals("02")) {
                    consulta++;
                }
                if (e.getTipoClasificion().equals("03")) {
                    petitorio++;
                }
            }
            sb.append("{x: 'Queja', y: ").append(queja).append("},");
            sb.append("{x: 'Petitorio', y: ").append(petitorio).append("},");
            sb.append("{x: 'Consulta', y: ").append(consulta).append("}");
            grafico001 = sb.toString();
        } catch (Exception e) {
            log.error("ERROR - cargarGraficos001()" + e);
        }

    }

    private void cargarGraficos002() {
        try {
            StringBuilder sb = new StringBuilder();
            int quejax = 0;
            int petitoriox = 0;
            int consultax = 0;
            int quejay = 0;
            int petitorioy = 0;
            int consultay = 0;
            for (Expediente e : listaExpedienteXUsuario) {
                if (e.getTipoClasificion().equals("01")) {
                    if (e.getGeneral().equals("C")) {
                        quejay++;
                    } else {
                        quejax++;
                    }
                }
                if (e.getTipoClasificion().equals("02")) {
                    if (e.getGeneral().equals("C")) {
                        consultay++;
                    } else {
                        consultax++;
                    }
                }
                if (e.getTipoClasificion().equals("03")) {
                    if (e.getGeneral().equals("C")) {
                        petitorioy++;
                    } else {
                        petitoriox++;
                    }
                }
            }
            sb.append("{x: 'Queja', y: " + quejax + ", z: " + quejay + "},");
            sb.append("{x: 'Petitorio', y: " + petitoriox + ", z: " + petitorioy + "},");
            sb.append("{x: 'Consulta', y: " + consultax + ", z: " + consultay + "}");
            grafico002 = sb.toString();
        } catch (Exception e) {
            log.error("ERROR - cargarGraficos002()" + e);
        }
    }

    private void cargarGraficos003() {
        try {
            Expediente e = new Expediente();
            StringBuilder sb = new StringBuilder();
            e.setTipoClasificion("01");
            e.setUsuarioRegistro(usuarioSession.getCodigo());
            List<Expediente> list001 = expedienteService.expedienteReporteMesUsuario(e);
            e.setTipoClasificion("02");
            List<Expediente> list002 = expedienteService.expedienteReporteMesUsuario(e);
            e.setTipoClasificion("03");
            List<Expediente> list003 = expedienteService.expedienteReporteMesUsuario(e);
            for (Expediente e1 : list001) {
                sb.append("{'period': '").append(e1.getMes()).append("', 'queja': ").append(e1.getCount()).append("},");
            }
            for (Expediente e1 : list002) {
                sb.append("{'period': '").append(e1.getMes()).append("', 'consulta': ").append(e1.getCount()).append("},");
            }
            for (Expediente e1 : list003) {
                sb.append("{'period': '").append(e1.getMes()).append("', 'petitorio': ").append(e1.getCount()).append("},");
            }
            grafico003 = sb.toString();
        } catch (Exception e) {
            log.error("ERROR - cargarGraficos003()" + e);
        }
    }

    public void setearPersonaSeleccionada(Persona perso) {
        try {
            if (StringUtils.isNotBlank(perso.getIdDepartamento()) && !StringUtils.equals(perso.getIdDepartamento(), "0")) {
                perso.setNombreDepartamento(ubigeoService.departamentoOne(perso.getIdDepartamento()).getDescripcion());
            }
            if (StringUtils.isNotBlank(perso.getIdProvincia()) && !StringUtils.equals(perso.getIdProvincia(), "0")) {
                Provincia p = new Provincia();
                p.setIdDepartamento(perso.getIdDepartamento());
                p.setIdProvincia(perso.getIdProvincia());
                perso.setNombreProvincia(ubigeoService.provinciaOne(p).getDescripcion());
            }
            if (StringUtils.isNotBlank(perso.getIdDistrito()) && !StringUtils.equals(perso.getIdDistrito(), "0")) {
                Distrito d = new Distrito();
                d.setIdDepartamento(perso.getIdDepartamento());
                d.setIdProvincia(perso.getIdProvincia());
                d.setIdDistrito(perso.getIdDistrito());
                perso.setNombreDistrito(ubigeoService.distritoOne(d).getDescripcion());
            }
            listarExpedientexPersona(perso.getId());
            setPersonaSeleccionada(perso);
            indSeleccion = false;
        } catch (Exception e) {
            log.error("ERROR - setearPersonaSeleccionada()" + e);
        }
    }

    private void listarExpedientexPersona(long idPersona) {
        try {
            listaExpedienteXPersona = expedienteService.expedientexPersona(idPersona);
        } catch (Exception e) {
            log.error("ERROR - listarExpedientexPersona()" + e);
        }
    }

    public void retornoListaPersonasSeleccionadas() {
        indSeleccion = true;
    }

    public String cargarNuevaPersona() {
        persona = new Persona();
        return "personaNuevo";
    }

    public String cargarNuevaEntidad() {
        entidad = new Entidad();
        return "entidadNuevo";
    }

    public String cargarExpedienteEdit(Expediente e) {
        try {
            persona = new Persona();
            entidad = new Entidad();
            FacesContext context = FacesContext.getCurrentInstance();
            MenuController menuController = (MenuController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "menuController");
            menuController.cargarPagina(1);
            listaEtiquetasSeleccionadas = new ArrayList<>();
            setExpediente(e);
            cargarEtiquetas();
            cargarPersonasEntidades();
            esSupervisor();
            if (expediente.getVersion() == 0) {
                inicializarEtapaEstado(0);
            } else {
                inicializarEtapaEstado(1);
            }
            listarNiveles();
            defineBotonRegistro();
            expedienteClasificacionBusqueda = new ExpedienteClasificacion();
            cargarFichaONP();
            if (StringUtils.isBlank(expediente.getNumero())) {
                return "expedienteEdit";
            }
            if (StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.CONSULTA.getKey())) {
                setearExpedienteTiempo();
                return "expedienteEdit";
            } else {
                if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.CALIFICACION_PETITORIO.getKey()) || Objects.equals(etapaEstado.getVerEtapa(), EtapaType.CALIFICACION_QUEJA.getKey())) {
                    setearExpedienteTiempo();
                    return "expedienteEdit";
                }
                cargarExpedienteGestionLista();
                setearExpedienteTiempo();
                cargarFichaONP();
                esSupervisor();
                return "expedienteGestionLista";
            }
        } catch (Exception ex) {
            log.error("ERROR - cargarExpedienteEdit()" + ex);
        }
        return null;
    }

    public void actualizarArchivo() {
        try {
            expedienteService.expedienteUpdateArchivo(expediente);
            msg.messageInfo("Se actualizaron los datos del archivo", null);
        } catch (Exception e) {
            log.error("ERROR - actualizarArchivo()" + e);
        }
    }

    private void listarNiveles() {
        try {
            if (StringUtils.isNotBlank(expediente.getNumero())) {
                List<ExpedienteNivel> list = expedienteNivelService.expedienteNivelPorExpediente(expediente.getNumero());
                for (ExpedienteNivel en : list) {
                    listaExpedienteClasificacion = expedienteClasificacionTipoService.clasificacioneExpedienteClasiTipo(en.getId());
                    en.setListaClasificacionTipo(listaExpedienteClasificacion);
                }
                expediente.setListaExpedienteNivel(list);
            }
            limpiarNivelesAll();
        } catch (Exception e) {
            log.error("ERROR - listarNiveles()" + e);
        }
    }

    public void setearExpediente(Expediente e) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            MenuController menuController = (MenuController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "menuController");
            menuController.cargarPagina(1);
            listaEtiquetasSeleccionadas = new ArrayList<>();
            setExpediente(e);
            cargarEtiquetas();
            cargarPersonasEntidades();
            if (expediente.getVersion() == 0) {
                inicializarEtapaEstado(0);
            } else {
                inicializarEtapaEstado(1);
            }
        } catch (Exception ex) {
            log.error("ERROR - setearExpediente()" + ex);
        }
    }

    private void cargarPersonasEntidades() {
        try {
            personasSeleccionadas = expedientePersonaService.expedientePersonaBuscarXExpediente(expediente.getId());
            entidadSeleccionadas = expedienteEntidadService.expedienteEntidadBuscarXExpediente(expediente.getId());
        } catch (Exception e) {
            log.error("ERROR - cargarPersonasEntidades()" + e);
        }
    }

    public void cargarModalActor() {
        persona = new Persona();
        persona.setTipo("PER");
    }

    public boolean buscarPersonaGeneral(Long pagina) {
        try {
            int i = 0;
            if (stringUtil.isBlank(personaBusqueda.getNumeroDocumento())) {
                personaBusqueda.setNumeroDocumento(null);
                i++;
            }
            if (stringUtil.isBlank(personaBusqueda.getApellidoPat())) {
                personaBusqueda.setApellidoPat(null);
                i++;
            }
            if (stringUtil.isBlank(personaBusqueda.getApellidoMat())) {
                personaBusqueda.setApellidoMat(null);
                i++;
            }
            if (stringUtil.isBlank(personaBusqueda.getNombre())) {
                personaBusqueda.setNombre(null);
                i++;
            }
            if (stringUtil.isBlank(personaBusqueda.getNumeroExpediente())) {
                personaBusqueda.setNumeroExpediente(null);
                i++;
            }
            if (i == 5) {
                msg.messageAlert("Debe de ingresar almenos un criterio de busqueda", null);
                return false;
            } else {
                if (pagina > 0) {
                    int paginado = ConstantesUtil.PAGINADO_5;
                    Long ini = paginado * (pagina - 1) + 1;
                    Long fin = paginado * pagina;
                    if (pagina == 0) {
                        ini = 1L;
                        fin = 5L;
                    }

                    personaBusqueda.setIni(ini);
                    personaBusqueda.setFin(fin);
                    try {
                        List<Persona> list = personaService.personaBusarGeneral(personaBusqueda);
                        if (list.size() > 0) {
                            listaPersonaGeneral = list;
                            nroPaginaPersona = pagina;
                        } else {
                            if (personaBusqueda.getIni() == 1) {
                                listaPersonaGeneral = null;
                                msg.messageAlert("No se han encontrado Personas", null);
                            }
                        }
                    } catch (Exception e) {
                        log.error("ERROR : BusquedaUsuarioController.listarPaginado: " + e.getMessage());
                    }
                }
            }
            indSeleccion = true;
            personaBusqueda = new Persona();
            listaExpedienteXPersona = null;
            return true;
        } catch (Exception e) {
            log.error("ERROR - buscarPersonaGeneral()" + e);
        }
        return false;
    }

    private void cargarEtiquetas() {
        try {
            if (!stringUtil.isBlank(expediente.getEtiqueta())) {
                String[] adArray = expediente.getEtiqueta().split(",");
                for (String adArray1 : adArray) {
                    Parametro p = codigoParamentro(60, adArray1);
                    listaEtiquetasSeleccionadas.add(p);
                }
            }
        } catch (Exception e) {
            log.error("ERROR - cargarEtiquetas()" + e);
        }
    }

    public String cargarBusquedaExpediente() {
        try {
            listaExpedientes = expedienteService.expedienteBuscar(expediente);
            return "expedienteBusqueda";
        } catch (Exception e) {
            log.error("ERROR - cargarBusquedaExpediente()" + e);
        }
        return null;
    }

    private void usuarioSession() {
        try {
            usuarioSession = new Usuario();
            FacesContext context = FacesContext.getCurrentInstance();
            LoginController loginController = (LoginController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "loginController");
            usuarioSession = loginController.getUsuarioSesion();
            usuarioSession.setNombreDepartamento(ubigeoService.departamentoOne(usuarioSession.getIdDepartamento()).getDescripcion());
        } catch (Exception e) {
            log.error("ERROR - usuarioSession()" + e);
        }
    }
    
    public void seleccionarUsuario(Usuario u){
        
    }

    public void entidadQuejada() {
        try {
            if (expedienteGestion.getIndEntidadQuejada()) {
                if (entidadSeleccionadas.size() == 1) {
                    for (ExpedienteEntidad ee : entidadSeleccionadas) {
                        expedienteGestion.setIdEntidad(Integer.parseInt(ee.getEntidad().getId().toString()));
                        expedienteGestion.setNombreEntidad(ee.getEntidad().getNombre());
                    }
                }
            } else {
                expedienteGestion.setIdEntidad(null);
                expedienteGestion.setNombreEntidad(null);
            }
        } catch (Exception e) {
            log.error("ERROR - entidadQuejada()" + e);
        }
    }

    public void addEntidadQuejada(Entidad entidad) {
        try {
            expedienteGestion.setIdEntidad(Integer.parseInt(entidad.getId().toString()));
            expedienteGestion.setNombreEntidad(entidad.getNombre());
        } catch (Exception e) {
            log.error("ERROR - entidadQuejada()" + e);
        }
    }

    public boolean cargarPopoverPersona(Long pagina) {
        try {
            if (pagina == 1) {
                nroPaginaPersona = 1L;
            }
            if (pagina > 0) {
                int paginado = ConstantesUtil.PAGINADO_5;
                Long ini = paginado * (pagina - 1) + 1;
                Long fin = paginado * pagina;
                if (pagina == 0) {
                    ini = 1L;
                    fin = 5L;
                }

                persona.setIni(ini);
                persona.setFin(fin);
                try {
                    List<Persona> list = personaService.personaBuscarCadena(persona);
                    if (list.size() > 0) {
                        personasPopover = list;
                        nroPaginaPersona = pagina;
                    } else {
                        if (persona.getIni() == 1) {
                            personasPopover = null;
                            msg.messageAlert("No se han encontrado Personas", null);
                        }
                    }
                } catch (Exception e) {
                    log.error("ERROR : BusquedaUsuarioController.listarPaginado: " + e.getMessage());
                }
            }
            return true;
        } catch (Exception e) {
            log.error("ERROR - cargarPopoverPersona()" + e);
        }
        return false;
    }

    public boolean cargarPopoverEntidad(Long pagina) {
        try {
            if (pagina == 1) {
                nroPaginaPersona = 1L;
            }
            if (pagina > 0) {
                int paginado = ConstantesUtil.PAGINADO_5;
                Long ini = paginado * (pagina - 1) + 1;
                Long fin = paginado * pagina;
                if (pagina == 0) {
                    ini = 1L;
                    fin = 5L;
                }
                entidad.setIni(ini);
                entidad.setFin(fin);
                try {
                    List<Entidad> list = entidadService.entidadBuscarCadena(entidad);
                    if (list.size() > 0) {
                        entidadPopover = list;
                        nroPaginaPersona = pagina;
                    } else {
                        if (entidad.getIni() == 1) {
                            entidadPopover = null;
                        }
                    }
                } catch (Exception e) {
                    log.error("ERROR : BusquedaUsuarioController.listarPaginado: " + e.getMessage());
                }
            }
            if (entidadPopover == null) {
                msg.messageAlert("No se han encontrado Personas", null);
            }
            return true;
        } catch (Exception e) {
            log.error("ERROR - cargarPopoverEntidad()" + e);
        }
        return false;
    }

    public boolean addPersona(Persona p) {
        try {
            for (ExpedientePersona p1 : personasSeleccionadas) {
                if (Objects.equals(p1.getPersona().getId(), p.getId())) {
                    msg.messageAlert("Esta persona ya se encuentra seleccionada", null);
                    cadenaPersonaPopover = null;
                    return false;
                }
            }
            ExpedientePersona ep = new ExpedientePersona(expediente, p);
            ep.setContacto(p.getContacto());
            personasSeleccionadas.add(ep);
            cadenaPersonaPopover = null;
            return true;
        } catch (Exception e) {
            log.error("ERROR - cargarPopoverEntidad()" + e);
        }
        return false;
    }

    public boolean seteaPersonaExpediente(ExpedientePersona ep) {
        try {
            Persona p = personaService.personaBusquedaOne(ep.getPersona().getId());
            ep.setPersona(p);
            setExpedientepersonaModalEdit(ep);
            expedientepersonaModalEdit.setDireccion(expedientepersonaModalEdit.getPersona().getDireccion());
            expedientepersonaModalEdit.setIdDepartamento(expedientepersonaModalEdit.getPersona().getIdDepartamento());
            expedientepersonaModalEdit.setIdProvincia(expedientepersonaModalEdit.getPersona().getIdProvincia());
            expedientepersonaModalEdit.setIdDistrito(expedientepersonaModalEdit.getPersona().getIdDistrito());
            expedientepersonaModalEdit.setTelefono1(expedientepersonaModalEdit.getPersona().getTelefono1());
            expedientepersonaModalEdit.setDireccionNotifica(expedientepersonaModalEdit.getPersona().getDireccionNotifica());
            expedientepersonaModalEdit.setEmail(expedientepersonaModalEdit.getPersona().getEmail());
            expedientepersonaModalEdit.setTipoLengua(expedientepersonaModalEdit.getPersona().getTipoLengua());
            expedientepersonaModalEdit.setIndicadorDiscapacitado(expedientepersonaModalEdit.getPersona().getIndicadorDiscapacitado());
            expedientepersonaModalEdit.setTipoPueblo(expedientepersonaModalEdit.getPersona().getTipoPueblo());
            if (StringUtils.isNotBlank(expedientepersonaModalEdit.getIdDepartamento()) && !StringUtils.equals(expedientepersonaModalEdit.getIdDepartamento(), "0")) {
                comboProvinciaId(expedientepersonaModalEdit.getIdDepartamento());
            }
            if (StringUtils.isNotBlank(ep.getIdProvincia()) && !StringUtils.equals(expedientepersonaModalEdit.getIdProvincia(), "0")) {
                comboDistritoId(expedientepersonaModalEdit.getIdProvincia(), expedientepersonaModalEdit.getIdDepartamento());
            }
            return true;
        } catch (Exception e) {
            log.error("ERROR - seteaPersonaExpediente()" + e);
        }
        return false;
    }

    public boolean editaPersonaExpediente() {
        try {
            expedientepersonaModalEdit.getPersona().setUsuModificacion(usuarioSession.getCodigo());
            expedientepersonaModalEdit.getPersona().setFechaModificacion(new Date());
            expedientepersonaModalEdit.getPersona().setDireccion(expedientepersonaModalEdit.getDireccion());
            expedientepersonaModalEdit.getPersona().setTelefono1(expedientepersonaModalEdit.getTelefono1());
            expedientepersonaModalEdit.getPersona().setEmail(expedientepersonaModalEdit.getEmail());
            expedientepersonaModalEdit.getPersona().setIdDepartamento(expedientepersonaModalEdit.getIdDepartamento());
            expedientepersonaModalEdit.getPersona().setIdProvincia(expedientepersonaModalEdit.getIdProvincia());
            expedientepersonaModalEdit.getPersona().setIdDistrito(expedientepersonaModalEdit.getIdDistrito());
            expedientepersonaModalEdit.getPersona().setContacto(expedientepersonaModalEdit.getContacto());
            expedientepersonaModalEdit.getPersona().setIndicadorDiscapacitado(expedientepersonaModalEdit.getIndicadorDiscapacitado());
            expedientepersonaModalEdit.getPersona().setTipoLengua(expedientepersonaModalEdit.getTipoLengua());
            expedientepersonaModalEdit.getPersona().setTipoPueblo(expedientepersonaModalEdit.getTipoPueblo());
            personaService.personaUpdate(expedientepersonaModalEdit.getPersona());
            expedientePersonaService.expedienteDatosPersonaUpdate(expedientepersonaModalEdit);
            if (StringUtils.equals(expedientepersonaModalEdit.getPersona().getTipo(), "PER")) {
                msg.messageInfo("Se actualizo los datos de la persona", null);
            } else {
                msg.messageInfo("Se actualizo los datos de la organización", null);
            }
            return true;
        } catch (Exception e) {
            log.error("ERROR - editaPersonaExpediente()" + e);
        }
        return false;
    }

    public void removePersona(ExpedientePersona ep) {
        try {
            personasSeleccionadas.remove(ep);
            int count = expedientePersonaService.expedientePersonaContar(ep);
            if (count > 0) {
                expedientePersonaService.expedientePersonaDelete(ep);
            }
            msg.messageInfo("Se elimino el registo de la persona", null);
        } catch (Exception e) {
            log.error("ERROR - removePersona()" + e);
        }
    }

    public boolean addEntidad(Entidad e) {
        try {
            for (ExpedienteEntidad e1 : entidadSeleccionadas) {
                if (Objects.equals(e1.getEntidad().getId(), e.getId())) {
                    msg.messageAlert("Esta Entidad ya se encuentra seleccionada", null);
                    cadenaEntidadPopover = null;
                    return false;
                }
            }
            ExpedienteEntidad ee = new ExpedienteEntidad(expediente, e);
            entidadSeleccionadas.add(ee);
            cadenaEntidadPopover = null;
            return false;
        } catch (Exception ex) {
            log.error("ERROR - addEntidad()" + ex);
        }
        return false;
    }

    public void removeEntidad(ExpedienteEntidad e) {
        try {
            entidadSeleccionadas.remove(e);
            int count = expedienteEntidadService.expedienteEntidadContar(e);
            if (count > 0) {
                expedienteEntidadService.expedienteEntidadDelete(e);
            }
            msg.messageInfo("Se elimino el registo de la Entidad", null);
        } catch (Exception ex) {
            log.error("ERROR - removeEntidad()" + ex);
        }
    }

    private void inicializarEtapaEstado(int tip) {
        try {
            if (tip == 0) {
                etapaEstado = new EtapaEstado();
                etapaEstado.setVerEtapa(0);
            } else {
                etapaEstado = etapaEstadoService.etapaEstadoVigente(expediente.getId());
                if (etapaEstado == null) {
                    etapaEstado = etapaEstadoService.etapaEstadoInicial(expediente.getId());
                }
                if (etapaEstado == null) {
                    etapaEstado = new EtapaEstado();
                }
                /**
                 * QUEJA
                 */
                if (StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.QUEJA.getKey())) {
                    if (etapaEstado.getIdEtapa() == null) {
                        etapaEstado.setVerEtapa(EtapaType.CALIFICACION_QUEJA.getKey());
                    }
                    if (Objects.equals(etapaEstado.getIdEtapa(), EtapaType.CALIFICACION_QUEJA.getKey())) {
                        if (StringUtils.equals(etapaEstado.getIndicadorEtapa(), "VIG")) {
                            etapaEstado.setVerEtapa(EtapaType.INVESTIGACION_QUEJA.getKey());
                            if (StringUtils.equals(expediente.getGeneral(), "C")) {
                                etapaEstado.setVerEtapa(EtapaType.CALIFICACION_QUEJA.getKey());
                            }
                        } else {
                            etapaEstado.setVerEtapa(EtapaType.CALIFICACION_QUEJA.getKey());
                        }
                    }
                    if (Objects.equals(etapaEstado.getIdEtapa(), EtapaType.INVESTIGACION_QUEJA.getKey())) {
                        if (StringUtils.equals(etapaEstado.getIndicadorEtapa(), "VIG")) {
                            etapaEstado.setVerEtapa(EtapaType.PERSUACION_QUEJA.getKey());
                            if (StringUtils.equals(expediente.getGeneral(), "C")) {
                                etapaEstado.setVerEtapa(EtapaType.INVESTIGACION_QUEJA.getKey());
                            }
                        } else {
                            etapaEstado.setVerEtapa(EtapaType.INVESTIGACION_QUEJA.getKey());
                        }
                    }
                    if (Objects.equals(etapaEstado.getIdEtapa(), EtapaType.PERSUACION_QUEJA.getKey())) {
                        if (StringUtils.equals(etapaEstado.getIndicadorEtapa(), "VIG")) {
                            etapaEstado.setVerEtapa(EtapaType.SEGUIMIENTO_QUEJA.getKey());
                            if (StringUtils.equals(expediente.getGeneral(), "C")) {
                                etapaEstado.setVerEtapa(EtapaType.PERSUACION_QUEJA.getKey());
                            }
                        } else {
                            etapaEstado.setVerEtapa(EtapaType.PERSUACION_QUEJA.getKey());
                        }
                    }
                    if (Objects.equals(etapaEstado.getIdEtapa(), EtapaType.SEGUIMIENTO_QUEJA.getKey())) {
                        etapaEstado.setVerEtapa(EtapaType.SEGUIMIENTO_QUEJA.getKey());
                    }
                }
                /**
                 * PETITORIO
                 */
                if (StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.PETITORIO.getKey())) {
                    if (etapaEstado.getIdEtapa() == null) {
                        etapaEstado.setVerEtapa(EtapaType.CALIFICACION_PETITORIO.getKey());
                    }
                    if (Objects.equals(etapaEstado.getIdEtapa(), EtapaType.CALIFICACION_PETITORIO.getKey())) {
                        if (StringUtils.equals(etapaEstado.getIndicadorEtapa(), "VIG")) {
                            etapaEstado.setVerEtapa(EtapaType.GESTION_PETITORIO.getKey());
                            if (StringUtils.equals(expediente.getGeneral(), "C")) {
                                etapaEstado.setVerEtapa(EtapaType.CALIFICACION_PETITORIO.getKey());
                            }
                        } else {
                            etapaEstado.setVerEtapa(EtapaType.CALIFICACION_PETITORIO.getKey());
                        }
                    }
                    if (Objects.equals(etapaEstado.getIdEtapa(), EtapaType.GESTION_PETITORIO.getKey())) {
                        if (StringUtils.equals(etapaEstado.getIndicadorEtapa(), "VIG")) {
                            etapaEstado.setVerEtapa(EtapaType.PERSUASION_PETITORIO.getKey());
                            if (StringUtils.equals(expediente.getGeneral(), "C")) {
                                etapaEstado.setVerEtapa(EtapaType.GESTION_PETITORIO.getKey());
                            }
                        } else {
                            etapaEstado.setVerEtapa(EtapaType.GESTION_PETITORIO.getKey());
                        }
                    }
                    if (Objects.equals(etapaEstado.getIdEtapa(), EtapaType.PERSUASION_PETITORIO.getKey())) {
                        etapaEstado.setVerEtapa(EtapaType.PERSUASION_PETITORIO.getKey());
                    }
                }
                etapaEstado.setUltimoEstado(detalleUltimoEstado(expediente.getNumero()));
            }
        } catch (Exception e) {
            log.error("ERROR - inicializarEtapaEstado()" + e);
        }
    }

    private void inicializarEtapaEstado(int tip, Expediente exp) {
        try {
            if (tip == 0) {
                etapaEstado = new EtapaEstado();
                etapaEstado.setVerEtapa(0);
            } else {
                etapaEstado = etapaEstadoService.etapaEstadoVigente(exp.getId());
                if (etapaEstado == null) {
                    etapaEstado = etapaEstadoService.etapaEstadoInicial(exp.getId());
                }
                if (etapaEstado == null) {
                    etapaEstado = new EtapaEstado();
                }
                /**
                 * QUEJA
                 */
                if (StringUtils.equals(exp.getTipoClasificion(), ExpedienteType.QUEJA.getKey())) {
                    if (etapaEstado.getIdEtapa() == null) {
                        etapaEstado.setVerEtapa(EtapaType.CALIFICACION_QUEJA.getKey());
                    }
                    if (Objects.equals(etapaEstado.getIdEtapa(), EtapaType.CALIFICACION_QUEJA.getKey())) {
                        if (StringUtils.equals(etapaEstado.getIndicadorEtapa(), "VIG")) {
                            etapaEstado.setVerEtapa(EtapaType.INVESTIGACION_QUEJA.getKey());
                            if (StringUtils.equals(exp.getGeneral(), "C")) {
                                etapaEstado.setVerEtapa(EtapaType.CALIFICACION_QUEJA.getKey());
                            }
                        } else {
                            etapaEstado.setVerEtapa(EtapaType.CALIFICACION_QUEJA.getKey());
                        }
                    }
                    if (Objects.equals(etapaEstado.getIdEtapa(), EtapaType.INVESTIGACION_QUEJA.getKey())) {
                        if (StringUtils.equals(etapaEstado.getIndicadorEtapa(), "VIG")) {
                            etapaEstado.setVerEtapa(EtapaType.PERSUACION_QUEJA.getKey());
                            if (StringUtils.equals(exp.getGeneral(), "C")) {
                                etapaEstado.setVerEtapa(EtapaType.INVESTIGACION_QUEJA.getKey());
                            }
                        } else {
                            etapaEstado.setVerEtapa(EtapaType.INVESTIGACION_QUEJA.getKey());
                        }
                    }
                    if (Objects.equals(etapaEstado.getIdEtapa(), EtapaType.PERSUACION_QUEJA.getKey())) {
                        if (StringUtils.equals(etapaEstado.getIndicadorEtapa(), "VIG")) {
                            etapaEstado.setVerEtapa(EtapaType.SEGUIMIENTO_QUEJA.getKey());
                            if (StringUtils.equals(exp.getGeneral(), "C")) {
                                etapaEstado.setVerEtapa(EtapaType.PERSUACION_QUEJA.getKey());
                            }
                        } else {
                            etapaEstado.setVerEtapa(EtapaType.PERSUACION_QUEJA.getKey());
                        }
                    }
                    if (Objects.equals(etapaEstado.getIdEtapa(), EtapaType.SEGUIMIENTO_QUEJA.getKey())) {
                        etapaEstado.setVerEtapa(EtapaType.SEGUIMIENTO_QUEJA.getKey());
                    }
                }
                /**
                 * PETITORIO
                 */
                if (StringUtils.equals(exp.getTipoClasificion(), ExpedienteType.PETITORIO.getKey())) {
                    if (etapaEstado.getIdEtapa() == null) {
                        etapaEstado.setVerEtapa(EtapaType.CALIFICACION_PETITORIO.getKey());
                    }
                    if (Objects.equals(etapaEstado.getIdEtapa(), EtapaType.CALIFICACION_PETITORIO.getKey())) {
                        if (StringUtils.equals(etapaEstado.getIndicadorEtapa(), "VIG")) {
                            etapaEstado.setVerEtapa(EtapaType.GESTION_PETITORIO.getKey());
                            if (StringUtils.equals(exp.getGeneral(), "C")) {
                                etapaEstado.setVerEtapa(EtapaType.CALIFICACION_PETITORIO.getKey());
                            }
                        } else {
                            etapaEstado.setVerEtapa(EtapaType.CALIFICACION_PETITORIO.getKey());
                        }
                    }
                    if (Objects.equals(etapaEstado.getIdEtapa(), EtapaType.GESTION_PETITORIO.getKey())) {
                        if (StringUtils.equals(etapaEstado.getIndicadorEtapa(), "VIG")) {
                            etapaEstado.setVerEtapa(EtapaType.PERSUASION_PETITORIO.getKey());
                            if (StringUtils.equals(exp.getGeneral(), "C")) {
                                etapaEstado.setVerEtapa(EtapaType.GESTION_PETITORIO.getKey());
                            }
                        } else {
                            etapaEstado.setVerEtapa(EtapaType.GESTION_PETITORIO.getKey());
                        }
                    }
                    if (Objects.equals(etapaEstado.getIdEtapa(), EtapaType.PERSUASION_PETITORIO.getKey())) {
                        etapaEstado.setVerEtapa(EtapaType.PERSUASION_PETITORIO.getKey());
                    }
                }
                etapaEstado.setUltimoEstado(detalleUltimoEstado(exp.getNumero()));
            }
        } catch (Exception e) {
            log.error("ERROR - inicializarEtapaEstado()" + e);
        }
    }

    public void guardarBorrador() {
        try {
            expediente.setVersion(0);
            expediente.setEstado("A");
            expediente.setEtiqueta(encadenarEtiquetas());
            expediente.setUsuarioRegistro(usuarioSession.getCodigo());
            expediente.setNumero("");
            if (expediente.getId() == null) {
                expediente.setGeneral("A");
                expedienteService.expedienteInsertar(expediente);
                insertUpdateListasPersonaEntidad();
                msg.messageInfo("Se registro el borrador del Expediente", null);
            } else {
                expedienteService.expedienteUpdate(expediente);
                insertUpdateListasPersonaEntidad();
                msg.messageInfo("Se actualizo el borrador del Expediente", null);
            }
            inicializarEtapaEstado(0);
        } catch (Exception e) {
            log.error("ERROR - guardarBorrador()" + e);
        }
    }
    
    private void guardarHistorial(ExpedienteHistorial historial){
        historial.setIdExpediente(expediente.getId());
        historial.setNumero(expediente.getNumero());
        historial.setUsuario(usuarioSession.getCodigo());
        historial.setFecha(new Date());
        expedienteHistorialService.expedienteHistorialInsertar(historial);
    }
    
    private boolean validaGestionesInternas(){
        if(expediente.getId() != null){
            listaExpedienteDerivacion = expedienteDerivacionService.expedienteDerivacionSelectList(expediente.getId());
        if(listaExpedienteDerivacion.size() > 0){
            msg.messageAlert("El expediente no puede ser modificado hasta que termine el flujo de derivación", null);
            return false;
        }
        
        listaExpedienteSuspencion = expedienteSuspencionService.expedienteSuspencionSelectList(expediente.getId());
        if(listaExpedienteSuspencion.size() > 0){
            msg.messageAlert("El expediente no puede ser modificado hasta que termine el flujo de suspención", null);
            return false;
        }
        
        listaExpedienteAmpliacion = expedienteAmpliacionService.expedienteAmpliacionSelectList(expediente.getId());
        if(listaExpedienteAmpliacion.size() > 0){
            msg.messageAlert("El expediente no puede ser modificado hasta que termine el flujo de ampliación", null);
            return false;
        }
        }
        return true;
    }

    public boolean guardarVersion() {
        if (StringUtils.isBlank(expediente.getTipoClasificion())) {
            msg.messageAlert("Debe seleccionar un tipo de expediente", null);
            return false;
        }
        if (StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.CONSULTA.getKey())) {
            if (expediente.getIndicadorOficio() != null) {
                if (expediente.getIndicadorOficio()) {
                    msg.messageAlert("Un expediente de oficio no puede ser del tipo consulta", null);
                    return false;
                }
            }
        }
        if(!validaGestionesInternas()){
            return false;
        }
        
            try {
            Long idExpedienteOld = null;
            if (expediente.getId() != null) {
                idExpedienteOld = expediente.getId();
            }
            guardar();
            guardarEtapaEstado(idExpedienteOld);
            inicializarEtapaEstado(1);
            insertarActualizarTiempos();
            cargarFichaONP();
            esSupervisor();
            if (expediente.getIndiceMayorInformacion() != null) {
                if (expediente.getIndiceMayorInformacion()) {
                    if (etapaEstado.getVerEtapa() == 1 || etapaEstado.getVerEtapa() == 5) {
                        Integer contador = expedienteService.expedienteMayorInformacionCount(expediente.getNumero());
                        if (contador == 1) {
                            setearTiempoEtapa(15);
                        }
                    }
                }
            }
            historial = new ExpedienteHistorial(HistorialType.HISTORIAL_GUARDAR_VERSION.getKey(), HistorialType.HISTORIAL_GUARDAR_VERSION.getValue());
            guardarHistorial(historial);
            msg.messageInfo("Se genero una nueva version del Expediente", null);
        } catch (Exception e) {
            log.error("ERROR - guardarVersion()" + e);
        }
        return true;
    }
    
    public void extenderPlazo(){
        extenderTiempoEtapa(20);
    }

    public boolean guardarVersionSupervisor(int tipo) {
        if (StringUtils.isBlank(expediente.getTipoClasificion())) {
            msg.messageAlert("Debe seleccionar un tipo de expediente", null);
            return false;
        }
        if (StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.CONSULTA.getKey())) {
            if (expediente.getIndicadorOficio()) {
                msg.messageAlert("Un expediente de oficio no puede ser del tipo consulta", null);
                return false;
            }
        }
        try {
            Long idExpedienteOld = null;
            if (expediente.getId() != null) {
                idExpedienteOld = expediente.getId();
            }
            guardarSinClasificacion();
            guardarEtapaEstado(idExpedienteOld);
            inicializarEtapaEstado(1);
            insertarActualizarTiempos();
            cargarFichaONP();
            if (tipo == 1) {
                expediente.setGeneral("A");
            }
            if (tipo == 1) {
                historial = new ExpedienteHistorial(HistorialType.HISTORIAL_LEVANTAR_CONCLUSION.getKey(), HistorialType.HISTORIAL_LEVANTAR_CONCLUSION.getValue());
                msg.messageInfo("Se levanto la conclusión", null);
            }else{
                historial = new ExpedienteHistorial(HistorialType.HISTORIAL_CAMBIAR_TIPO.getKey(), HistorialType.HISTORIAL_CAMBIAR_TIPO.getValue());
                msg.messageInfo("Se modifico el tipo de clasificación", null);
            }
            guardarHistorial(historial);
        } catch (Exception e) {
            log.error("ERROR - guardarVersionSupervisor()" + e);
        }
        return true;
    }

    public void guardarVersion2() {
        try {
            Long idExpedienteOld = null;
            if (expediente.getId() != null) {
                idExpedienteOld = expediente.getId();
            }
            guardar();
            guardarEtapaEstado(idExpedienteOld);
            inicializarEtapaEstado(1);
            insertarActualizarTiempos();
            cargarFichaONP();
        } catch (Exception e) {
            log.error("ERROR - guardarVersion2()" + e);
        }
    }

    public void guardarVersion3(String codigoUsuario) {
        try {
            Usuario u = usuarioService.buscarUsuarioOne(codigoUsuario);
            Long idExpedienteOld = null;
            if (expediente.getId() != null) {
                idExpedienteOld = expediente.getId();
            }
            guardarExpedienteDerivado(u);
            guardarEtapaEstado(idExpedienteOld);
            inicializarEtapaEstado(1);
        } catch (Exception e) {
            log.error("ERROR - guardarVersion3()" + e);
        }
    }

    private void guardar() {
        try {
            expediente.setEtiqueta(encadenarEtiquetas());
            if (expediente.getId() == null || expediente.getVersion() == 0) {
                if (expediente.getId() != null) {
                    expediente.setEstado("I");
                    expedienteService.expedienteUpdate(expediente);
                }
                expediente.setUsuarioRegistro(usuarioSession.getCodigo());
                expediente.setUsuarioResponsable(usuarioSession.getCodigo());
                expediente.setVersion(1);
                generarCodigoExpediente();
                expediente.setFechaIngreso(new Date());
                expediente.setFechaRegistro(new Date());
            } else {
                expediente.setVersion(expediente.getVersion() + 1);
                expediente.setEstado("I");
                expediente.setFechaModificacion(new Date());
                expedienteService.expedienteUpdate(expediente);
            }
            expediente.setEstado("A");
            if (file5 != null) {
                String ruta = uploadArchive(file5);
                if (ruta != null) {
                    expediente.setRuta(ruta);
                }
            }
            cargarGruposVulnerables();
            expediente.setGeneral("A");
            expedienteService.expedienteInsertar(expediente);
            insertListasPersonaEntidad();
        } catch (Exception e) {
            log.error("ERROR - guardar()" + e);
        }
    }

    private void guardarSinClasificacion() {
        try {
            expediente.setEtiqueta(encadenarEtiquetas());
            if (expediente.getId() == null || expediente.getVersion() == 0) {
                if (expediente.getId() != null) {
                    expediente.setEstado("I");
                    expedienteService.expedienteUpdate(expediente);
                }
                expediente.setUsuarioRegistro(usuarioSession.getCodigo());
                expediente.setVersion(1);
                generarCodigoExpediente();
                expediente.setFechaIngreso(new Date());
                expediente.setFechaRegistro(new Date());
            } else {
                expediente.setVersion(expediente.getVersion() + 1);
                expediente.setEstado("I");
                expediente.setFechaModificacion(new Date());
                expedienteService.expedienteUpdateNoClasificacion(expediente);
            }
            expediente.setEstado("A");
            if (file5 != null) {
                String ruta = uploadArchive(file5);
                if (ruta != null) {
                    expediente.setRuta(ruta);
                }
            }
            cargarGruposVulnerables();
            expediente.setGeneral("A");
            expedienteService.expedienteInsertar(expediente);
            insertListasPersonaEntidad();
        } catch (Exception e) {
            log.error("ERROR - guardarSinClasificacion()" + e);
        }
    }

    private void generarCodigoExpediente() {
        try {
            Long consecutivo = expedienteService.expedienteCodigoPorOD(usuarioSession.getCodigoOD().longValue());
            if (consecutivo == null) {
                consecutivo = 0L;
            }
            String stringCodigoOD = String.format("%4s", usuarioSession.getCodigoOD().toString()).replace(' ', '0');
            expediente.setCodigoOD(usuarioSession.getCodigoOD());
            expediente.setConsecutivo(consecutivo + 1);
            Calendar c1 = Calendar.getInstance();
            String numeroExpediente = stringCodigoOD + "-" + c1.get(Calendar.YEAR) + "-" + String.format("%6s", expediente.getConsecutivo().toString()).replace(' ', '0');
            expediente.setNumero(numeroExpediente);
        } catch (Exception e) {
            log.error("ERROR - generarCodigoExpediente()" + e);
        }

    }

    private void guardarExpedienteDerivado(Usuario usu) {
        try {
            expediente.setEtiqueta(encadenarEtiquetas());
            if (expediente.getId() == null || expediente.getVersion() == 0) {
                if (expediente.getId() != null) {
                    expediente.setEstado("I");
                    expedienteService.expedienteUpdate(expediente);
                }
                expediente.setUsuarioRegistro(usu.getCodigo());
                expediente.setVersion(1);
                generarCodigoExpediente();
                expediente.setFechaRegistro(new Date());
            } else {
                expediente.setVersion(expediente.getVersion() + 1);
                expediente.setEstado("I");
                expedienteService.expedienteUpdate(expediente);
            }
            expediente.setUsuarioRegistro(usu.getCodigo());
            expediente.setIndDerivado(1);
            expediente.setEstado("A");
            expediente.setCodigoOD(usu.getCodigoOD());
            expediente.setGeneral("A");
            expedienteService.expedienteInsertar(expediente);
            insertListasPersonaEntidad();
        } catch (Exception e) {
            log.error("ERROR - guardar1()" + e);
        }
    }

    public String concluir() {
        try {
            Long idExpedienteOld = expediente.getId();
            if (idExpedienteOld == null) {
                return null;
            }
            /**
             * QUEJA
             */
            if (StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.QUEJA.getKey())) {
                if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.CALIFICACION_QUEJA.getKey())) {
                    if (expediente.getEstadoCalificacion() == null) {
                        msg.messageAlert("El expediente no cuenta con ningun estado, por favor seleccione si esta admitido o no admitido", null);
                        return null;
                    }
                }
                if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.INVESTIGACION_QUEJA.getKey())) {
                    if (expediente.getEstadoInvestigacion() == null) {
                        msg.messageAlert("El expediente no cuenta con ningun estado, por favor seleccione si esta fundado o infundado", null);
                        return null;
                    }
                }
                if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.PERSUACION_QUEJA.getKey())) {
                    if (expediente.getEstadoPersuacion() == null) {
                        msg.messageAlert("El expediente no cuenta con ningun estado, por favor seleccione si esta acogido o no acogido", null);
                        return null;
                    }
                }
                if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.SEGUIMIENTO_QUEJA.getKey())) {
                    if (expediente.getEstadoSeguimiento() == null) {
                        msg.messageAlert("El expediente no cuenta con ningun estado, por favor seleccione si esta acogido o no acogido", null);
                        return null;
                    }
                }
            }

            /**
             * PETITORIO
             */
            if (StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.PETITORIO.getKey())) {
                if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.CALIFICACION_PETITORIO.getKey())) {
                    if (expediente.getEstadoCalificacion() == null) {
                        msg.messageAlert("El expediente no cuenta con ningun estado, por favor seleccione si esta admitido o no admitido", null);
                        return null;
                    }
                }
                if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.GESTION_PETITORIO.getKey())) {
                    if (expediente.getEstadoGestion() == null) {
                        msg.messageAlert("El expediente no cuenta con ningun estado, por favor seleccione si esta solucionado o no solucionado", null);
                        return null;
                    }
                }
                if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.PERSUASION_PETITORIO.getKey())) {
                    if (expediente.getEstadoPersuacion() == null) {
                        msg.messageAlert("El expediente no cuenta con ningun estado, por favor seleccione si devienen en solucionados, por negativa expresa o falta de respuesta", null);
                        return null;
                    }
                }
            }
            if (!validaConcluision()) {
                return null;
            }

            if (validaConclusionFinal()) {
                return null;
            }
            if(!validaGestionesInternas()){
                return null;
            }
            
            guardar();
            guardarEtapaEstadoConcluir(idExpedienteOld);
            inicializarEtapaEstado(1);
            insertarActualizarTiempos();
            cargarFichaONP();
            verModalConclusion = false;
            /**
             * GENERAR NUEVO ESTADO
             */
            historial = new ExpedienteHistorial(HistorialType.HISTORIAL_CONCLUIR_ETAPA.getKey(), HistorialType.HISTORIAL_CONCLUIR_ETAPA.getValue());
            guardarHistorial(historial);
            msg.messageInfo("Se concluyó la etapa", null);
            if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.CALIFICACION_PETITORIO.getKey()) || Objects.equals(etapaEstado.getVerEtapa(), EtapaType.CALIFICACION_QUEJA.getKey())) {
                setearExpedienteTiempo();
                return "expedienteEdit";
            }
            cargarExpedienteGestionLista();
            return "expedienteGestionLista";
        } catch (Exception e) {
            log.error("ERROR - concluir()" + e);
        }
        return null;
    }

    public void cancelarConclusion() {
        verModalConclusion = false;
    }

    public String guardarConclusionFinExpediente() {
        try {
            Long idExpedienteOld = expediente.getId();
            if (idExpedienteOld == null) {
                return null;
            }
            guardar();
            guardarEtapaEstadoConcluir(idExpedienteOld);
            inicializarEtapaEstado(1);
            insertarActualizarTiempos();
            cargarFichaONP();
            /**
             * GENERAR NUEVO ESTADO
             */
            msg.messageInfo("Se concluyó el expediente", null);
            if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.CALIFICACION_PETITORIO.getKey()) || Objects.equals(etapaEstado.getVerEtapa(), EtapaType.CALIFICACION_QUEJA.getKey())) {
                setearExpedienteTiempo();
                verModalConclusion = false;
                return "expedienteEdit";
            }
            verModalConclusion = false;
            historial = new ExpedienteHistorial(HistorialType.HISTORIAL_CONCLUIR_EXPEDIENTE.getKey(), HistorialType.HISTORIAL_CONCLUIR_EXPEDIENTE.getValue());
            guardarHistorial(historial);
            return "expedienteGestionLista";
        } catch (Exception e) {
            log.error("ERROR - guardarConclusionFinExpediente()" + e);
        }
        return null;
    }

    private boolean validaConclusionFinal() {
        try {
            verModalConclusion = false;
            /**
             * QUEJA
             */
            if (StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.QUEJA.getKey())) {

                //actualizarEtapaEstado(idExpedienteOld);
                if (etapaEstado != null) {
                    if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.CALIFICACION_QUEJA.getKey())) {
                        if (expediente.getEstadoCalificacion() == EstadoExpedienteType.CALIFICACION_NO_ADMITIDA_QUEJA.getKey()) {
                            verModalConclusion = true;
                            return verModalConclusion;
                        }
                    }
                    if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.INVESTIGACION_QUEJA.getKey())) {
                        if (expediente.getEstadoInvestigacion() == EstadoExpedienteType.INVESTIGACION_INFUNDADO_QUEJA.getKey()) {
                            verModalConclusion = true;
                            return verModalConclusion;
                        }
                    }
                    if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.PERSUACION_QUEJA.getKey())) {
                        if (expediente.getEstadoPersuacion() == EstadoExpedienteType.PERSUACION_ACOGIDO_QUEJA.getKey()) {
                            verModalConclusion = true;
                            return verModalConclusion;
                        }
                    }
                    if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.SEGUIMIENTO_QUEJA.getKey())) {
                        verModalConclusion = true;
                        return verModalConclusion;
                    }
                }
            }
            /**
             * PETITORIO
             */
            if (StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.PETITORIO.getKey())) {
                //actualizarEtapaEstado(idExpedienteOld);
                if (etapaEstado != null) {
                    if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.CALIFICACION_PETITORIO.getKey())) {
                        if (expediente.getEstadoCalificacion() == EstadoExpedienteType.CALIFICACION_NO_ADMITIDA_PETITORIO.getKey()) {
                            verModalConclusion = true;
                            return verModalConclusion;
                        }
                    }
                    if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.GESTION_PETITORIO.getKey())) {
                        if (expediente.getEstadoGestion() == EstadoExpedienteType.GESTION_SOLUCIONADO_PETITORIO.getKey()) {
                            verModalConclusion = true;
                            return verModalConclusion;
                        }
                    }
                    if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.PERSUASION_PETITORIO.getKey())) {
                        verModalConclusion = true;
                        return verModalConclusion;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            log.error("ERROR - validaConclusionFinal()" + e);
        }
        return false;
    }

    private boolean validaConcluision() {
        try {
            if (StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.QUEJA.getKey()) || StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.PETITORIO.getKey())) {
                if (StringUtils.equals(expediente.getTipoIngreso(), "0")) {
                    msg.messageAlert("Debe ingresar el canal de ingreso", null);
                    return false;
                }
                if (StringUtils.isBlank(expediente.getSumilla())) {
                    msg.messageAlert("Debe ingresar la sumilla", null);
                    return false;
                }
                if (!expediente.getIndicadorOficio()) {
                    if (personasSeleccionadas.size() == 0) {
                        msg.messageAlert("Debe ingresar el recurrente y el afectado", null);
                        return false;
                    } else {
                        int i = 0;
                        int j = 0;
                        int k = 0;
                        for (ExpedientePersona ep : personasSeleccionadas) {
                            if (StringUtils.equals(ep.getTipo(), "01")) {
                                i++;
                            }
                            if (StringUtils.equals(ep.getTipo(), "03")) {
                                j++;
                            }
                            if (StringUtils.equals(ep.getTipo(), "02")) {
                                k++;
                            }
                        }
                        if (k == 0) {
                            if (i == 0) {
                                msg.messageAlert("Debe ingresar el recurrente", null);
                                return false;
                            }
                            if (j == 0) {
                                msg.messageAlert("Debe ingresar el afectado", null);
                                return false;
                            }
                        }
                    }
                } else {
                    if (personasSeleccionadas.size() == 0) {
                        msg.messageAlert("Debe ingresar el afectado", null);
                        return false;
                    } else {
                        int j = 0;
                        for (ExpedientePersona ep : personasSeleccionadas) {
                            if (StringUtils.equals(ep.getTipo(), "03")) {
                                j++;
                            }
                        }
                        if (j == 0) {
                            msg.messageAlert("Debe ingresar el afectado", null);
                            return false;
                        }
                    }
                }
                if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.CALIFICACION_QUEJA.getKey())) {
                    if (expediente.getListaExpedienteNivel().size() == 0) {
                        msg.messageAlert("Debe ingresar al menos una clasificación", null);
                        return false;
                    }
                }
                if (StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.QUEJA.getKey())) {
                    if (entidadSeleccionadas.size() == 0) {
                        msg.messageAlert("Debe ingresar al menos una Entidad", null);
                        return false;
                    }
                }

                /*if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.INVESTIGACION_QUEJA.getKey())) {
                    if (listaExpedientesInvestigacionQueja != null) {
                        if (listaExpedientesInvestigacionQueja.size() == 0) {
                            msg.messageAlert("Debe ingresar por lo menos una gestion para culminar la etapa", null);
                            return false;
                        }
                    }
                }*/
                
                if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.PERSUACION_QUEJA.getKey())) {
                    if (listaExpedientesPersuacionQueja != null) {
                        if (listaExpedientesPersuacionQueja.size() == 0) {
                            msg.messageAlert("Debe ingresar por lo menos una gestion para culminar la etapa", null);
                            return false;
                        }
                    }
                }
                if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.SEGUIMIENTO_QUEJA.getKey())) {
                    if (listaExpedientesSeguimientoQueja != null) {
                        if (listaExpedientesSeguimientoQueja.size() == 0) {
                            msg.messageAlert("Debe ingresar por lo menos una gestion para culminar la etapa", null);
                            return false;
                        }
                    }
                }
                if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.CALIFICACION_PETITORIO.getKey())) {
                    if (expediente.getListaExpedienteNivel().size() == 0) {
                        msg.messageAlert("Debe ingresar al menos una clasificación", null);
                        return false;
                    }
                }
                if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.GESTION_PETITORIO.getKey())) {
                    if (listaExpedientesGestionPetitorio != null) {
                        if (listaExpedientesGestionPetitorio.size() == 0) {
                            msg.messageAlert("Debe ingresar por lo menos una gestion para culminar la etapa", null);
                            return false;
                        }
                    }
                }
                if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.PERSUASION_PETITORIO.getKey())) {
                    if (listaExpedientesPersuacionPetitorio != null) {
                        if (listaExpedientesPersuacionPetitorio.size() == 0) {
                            msg.messageAlert("Debe ingresar por lo menos una gestion para culminar la etapa", null);
                            return false;
                        }
                    }
                }
            }
            return true;
        } catch (Exception e) {
            log.error("ERROR - validaConcluision()" + e);
        }
        return false;
    }

    private void guardarEtapaEstado(Long idExpedienteOld) {
        EtapaEstado etapaEstado1 = new EtapaEstado();
        try {
            /**
             * QUEJA
             */
            if (StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.QUEJA.getKey())) {
                if (idExpedienteOld != null) {
                    actualizarEtapaEstado(idExpedienteOld);
                    if (etapaEstado != null) {
                        if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.CALIFICACION_QUEJA.getKey()) || etapaEstado.getVerEtapa() == 0) {
                            etapaEstado1.setIdEtapa(EtapaType.CALIFICACION_QUEJA.getKey());
                            etapaEstado1.setIdEstado(expediente.getEstadoCalificacion());
                        }
                        if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.INVESTIGACION_QUEJA.getKey())) {
                            etapaEstado1.setIdEtapa(EtapaType.INVESTIGACION_QUEJA.getKey());
                            etapaEstado1.setIdEstado(expediente.getEstadoInvestigacion());
                        }
                        if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.PERSUACION_QUEJA.getKey())) {
                            etapaEstado1.setIdEtapa(EtapaType.PERSUACION_QUEJA.getKey());
                            etapaEstado1.setIdEstado(expediente.getEstadoPersuacion());
                        }
                        if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.SEGUIMIENTO_QUEJA.getKey())) {
                            Integer etapaOld = etapaEstado.getIdEtapa();
                            etapaEstado1.setIdEtapa(EtapaType.SEGUIMIENTO_QUEJA.getKey());
                            etapaEstado1.setIdEstado(expediente.getEstadoSeguimiento());
                            etapaEstado1.setIndicadorEtapa("VIG");
                            if (Objects.equals(etapaOld, EtapaType.SEGUIMIENTO_QUEJA.getKey())) {
                                actualizarEtapaEstadosSeguimiento(idExpedienteOld);
                            }
                        }
                    } else {
                        etapaEstado1.setIdEtapa(EtapaType.CALIFICACION_QUEJA.getKey());
                    }
                } else {
                    etapaEstado1.setIdEtapa(EtapaType.CALIFICACION_QUEJA.getKey());
                }
            }
            /**
             * PETITORIO
             */
            if (StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.PETITORIO.getKey())) {
                if (idExpedienteOld != null) {
                    actualizarEtapaEstado(idExpedienteOld);
                    if (etapaEstado != null) {
                        if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.CALIFICACION_PETITORIO.getKey()) || etapaEstado.getVerEtapa() == 0) {
                            etapaEstado1.setIdEtapa(EtapaType.CALIFICACION_PETITORIO.getKey());
                            etapaEstado1.setIdEstado(expediente.getEstadoCalificacion());
                        }
                        if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.GESTION_PETITORIO.getKey())) {
                            etapaEstado1.setIdEtapa(EtapaType.GESTION_PETITORIO.getKey());
                            etapaEstado1.setIdEstado(expediente.getEstadoGestion());
                        }
                        if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.PERSUASION_PETITORIO.getKey())) {
                            Integer etapaOld = etapaEstado.getIdEtapa();
                            etapaEstado1.setIdEtapa(EtapaType.PERSUASION_PETITORIO.getKey());
                            etapaEstado1.setIdEstado(expediente.getEstadoPersuacion());
                            etapaEstado1.setIndicadorEtapa("VIG");
                            if (Objects.equals(etapaOld, EtapaType.PERSUASION_PETITORIO.getKey())) {
                                actualizarEtapaEstadosPersuacion(idExpedienteOld);
                            }
                        }
                    } else {
                        etapaEstado1.setIdEtapa(EtapaType.CALIFICACION_PETITORIO.getKey());
                    }
                } else {
                    etapaEstado1.setIdEtapa(EtapaType.CALIFICACION_PETITORIO.getKey());
                }
            }

            etapaEstado1.setIdExpediente(expediente.getId());
            etapaEstado1.setNumeroExpediente(expediente.getNumero());
            etapaEstado1.setIndicador("ACT");
            etapaEstadoService.etapaEstadoInsertar(etapaEstado1);
        } catch (Exception e) {
            log.error("ERROR - guardarEtapaEstado()" + e);
        }
    }

    private void guardarEtapaEstadoConcluir(Long idExpedienteOld) {
        try {
            EtapaEstado etapaEstado1 = new EtapaEstado();
            /**
             * QUEJA
             */
            if (StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.QUEJA.getKey())) {
                if (idExpedienteOld != null) {
                    actualizarEtapaEstado(idExpedienteOld);
                    if (etapaEstado != null) {
                        if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.CALIFICACION_QUEJA.getKey())) {
                            etapaEstado1.setIdEtapa(EtapaType.CALIFICACION_QUEJA.getKey());
                            etapaEstado1.setIdEstado(expediente.getEstadoCalificacion());
                            if (expediente.getEstadoCalificacion() == EstadoExpedienteType.CALIFICACION_NO_ADMITIDA_QUEJA.getKey()) {
                                expediente.setGeneral("C");
                                expedienteService.expedienteConcluir(expediente.getId());
                            }
                        }
                        if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.INVESTIGACION_QUEJA.getKey())) {
                            etapaEstado1.setIdEtapa(EtapaType.INVESTIGACION_QUEJA.getKey());
                            etapaEstado1.setIdEstado(expediente.getEstadoInvestigacion());
                            if (expediente.getEstadoInvestigacion() == EstadoExpedienteType.INVESTIGACION_INFUNDADO_QUEJA.getKey()) {
                                expediente.setGeneral("C");
                                expedienteService.expedienteConcluir(expediente.getId());
                            }
                        }
                        if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.PERSUACION_QUEJA.getKey())) {
                            etapaEstado1.setIdEtapa(EtapaType.PERSUACION_QUEJA.getKey());
                            etapaEstado1.setIdEstado(expediente.getEstadoPersuacion());
                            if (expediente.getEstadoPersuacion() == EstadoExpedienteType.PERSUACION_ACOGIDO_QUEJA.getKey()) {
                                expediente.setGeneral("C");
                                expedienteService.expedienteConcluir(expediente.getId());
                            }
                        }
                        if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.SEGUIMIENTO_QUEJA.getKey())) {
                            etapaEstado1.setIdEtapa(EtapaType.SEGUIMIENTO_QUEJA.getKey());
                            etapaEstado1.setIdEstado(expediente.getEstadoSeguimiento());
                            expediente.setGeneral("C");
                            expedienteService.expedienteConcluir(expediente.getId());
                        }
                        etapaEstado1.setIndicadorEtapa("VIG");
                    } else {
                        etapaEstado1.setIdEtapa(EtapaType.CALIFICACION_QUEJA.getKey());
                    }
                } else {
                    etapaEstado1.setIdEtapa(EtapaType.CALIFICACION_QUEJA.getKey());
                }
            }
            /**
             * PETITORIO
             */
            if (StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.PETITORIO.getKey())) {
                if (idExpedienteOld != null) {
                    actualizarEtapaEstado(idExpedienteOld);
                    if (etapaEstado != null) {
                        if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.CALIFICACION_PETITORIO.getKey())) {
                            etapaEstado1.setIdEtapa(EtapaType.CALIFICACION_PETITORIO.getKey());
                            etapaEstado1.setIdEstado(expediente.getEstadoCalificacion());
                            if (expediente.getEstadoCalificacion() == EstadoExpedienteType.CALIFICACION_NO_ADMITIDA_PETITORIO.getKey()) {
                                expediente.setGeneral("C");
                                expedienteService.expedienteConcluir(expediente.getId());
                            }
                        }
                        if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.GESTION_PETITORIO.getKey())) {
                            etapaEstado1.setIdEtapa(EtapaType.GESTION_PETITORIO.getKey());
                            etapaEstado1.setIdEstado(expediente.getEstadoGestion());
                            if (expediente.getEstadoGestion() == EstadoExpedienteType.GESTION_SOLUCIONADO_PETITORIO.getKey()) {
                                expediente.setGeneral("C");
                                expedienteService.expedienteConcluir(expediente.getId());
                            }
                        }
                        if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.PERSUASION_PETITORIO.getKey())) {
                            etapaEstado1.setIdEtapa(EtapaType.PERSUASION_PETITORIO.getKey());
                            etapaEstado1.setIdEstado(expediente.getEstadoPersuacion());
                            expediente.setGeneral("C");
                            expedienteService.expedienteConcluir(expediente.getId());
                        }
                        etapaEstado1.setIndicadorEtapa("VIG");
                    } else {
                        etapaEstado1.setIdEtapa(EtapaType.CALIFICACION_PETITORIO.getKey());
                    }
                } else {
                    etapaEstado1.setIdEtapa(EtapaType.CALIFICACION_PETITORIO.getKey());
                }
            }
            /**
             * CONSULTA
             */
            if (StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.CONSULTA.getKey())) {
                expediente.setGeneral("C");
                expedienteService.expedienteConcluir(expediente.getId());
            }

            etapaEstado1.setIdExpediente(expediente.getId());
            etapaEstado1.setNumeroExpediente(expediente.getNumero());
            etapaEstado1.setIndicador("ACT");
            etapaEstadoService.etapaEstadoInsertar(etapaEstado1);

        } catch (Exception e) {
            log.error("ERROR - guardarEtapaEstadoConcluir()" + e);
        }
    }

    private void insertUpdateListasPersonaEntidad() {
        try {
            for (ExpedientePersona p : personasSeleccionadas) {
                p.setExpediente(expediente);
                insertUpdateExpedientePersona(p);
            }

            for (ExpedienteEntidad e : entidadSeleccionadas) {
                e.setExpediente(expediente);
                insertUpdateExpedienteEntidad(e);
            }
        } catch (Exception e) {
            log.error("ERROR - insertUpdateListasPersonaEntidad()" + e);
        }
    }

    public void cargarGruposVulnerables() {
        try {
            int mujeres = 0;
            int adultosMayores = 0;
            int nin = 0;
            StringBuilder sb = new StringBuilder();
            sb.append(expediente.getTipoGrupoVulnerable());
            for (ExpedientePersona ep : personasSeleccionadas) {
                if (StringUtils.equals(ep.getTipo(), "01") || StringUtils.equals(ep.getTipo(), "03")) {
                    if (StringUtils.equals(ep.getPersona().getSexo(), "F")) {
                        if (mujeres == 0) {
                            if (!sb.toString().contains("01")) {
                                sb.append(",01");
                            }
                            mujeres++;
                        }
                    }
                    if (ep.getPersona().getFechaNacimiento() != null) {
                        int year = getMonths(ep.getPersona().getFechaNacimiento(), new Date());
                        if (year >= 60) {
                            if (adultosMayores == 0) {
                                if (!sb.toString().contains("03")) {
                                    sb.append(",03");
                                }
                                adultosMayores++;
                            }
                        }
                        if (year <= 18) {
                            if (nin == 0) {
                                if (!sb.toString().contains("02")) {
                                    sb.append(",02");
                                }
                                nin++;
                            }
                        }
                    }
                }
            }
            expediente.setTipoGrupoVulnerable(sb.toString());
        } catch (Exception e) {
            log.error("ERROR - cargarGruposVulnerables()" + e);
        }

    }

    public int getMonths(Date g1, Date g2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        // Establecer las fechas
        cal1.setTime(g1);
        cal2.setTime(g2);
        // conseguir la representacion de la fecha en milisegundos
        long milis1 = cal1.getTimeInMillis();
        long milis2 = cal2.getTimeInMillis();
        long diff = milis2 - milis1;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        Long diffyear = diffDays / 365;
        return Integer.parseInt(diffyear.toString());
    }

    private void insertListasPersonaEntidad() {
        try {
            for (ExpedientePersona p : personasSeleccionadas) {
                p.setExpediente(expediente);
                p.setContacto(p.getPersona().getContacto());
                p.setDireccion(p.getPersona().getDireccion());
                p.setEmail(p.getPersona().getEmail());
                p.setIdDepartamento(p.getPersona().getIdDepartamento());
                p.setIdProvincia(p.getPersona().getIdProvincia());
                p.setIdDistrito(p.getPersona().getIdDistrito());
                p.setIndicadorDiscapacitado(p.getPersona().getIndicadorDiscapacitado());
                p.setNombreCompleto(p.getPersona().getNombre());
                p.setTelefono1(p.getPersona().getTelefono1());
                p.setDireccionNotifica(p.getPersona().getDireccionNotifica());
                p.setTipoLengua(p.getPersona().getTipoLengua());
                p.setTipoPueblo(p.getPersona().getTipoPueblo());
                expedientePersonaService.expedientePersonaInsertar(p);
            }
            for (ExpedienteEntidad e : entidadSeleccionadas) {
                e.setExpediente(expediente);
                expedienteEntidadService.expedienteEntidadInsertar(e);
            }
        } catch (Exception e) {
            log.error("ERROR - insertListasPersonaEntidad()" + e);
        }

    }

    private void actualizarEtapaEstado(long idExpediente) {
        try {
            EtapaEstado ee = new EtapaEstado();
            ee.setIndicador("INA");
            ee.setIdExpediente(idExpediente);
            etapaEstadoService.etapaEstadoUpdate(ee);
        } catch (Exception e) {
            log.error("ERROR - actualizarEtapaEstado()" + e);
        }
    }

    private void actualizarEtapaEstadosSeguimiento(long idExpediente) {
        try {
            EtapaEstado ee = new EtapaEstado();
            ee.setIndicadorEtapa("");
            ee.setIdExpediente(idExpediente);
            etapaEstadoService.etapaEstadoUpdate(ee);
        } catch (Exception e) {
            log.error("ERROR - actualizarEtapaEstadosSeguimiento()" + e);
        }
    }

    private void actualizarEtapaEstadosPersuacion(long idExpediente) {
        try {
            EtapaEstado ee = new EtapaEstado();
            ee.setIndicadorEtapa("");
            ee.setIdExpediente(idExpediente);
            etapaEstadoService.etapaEstadoUpdate(ee);
        } catch (Exception e) {
            log.error("ERROR - actualizarEtapaEstadosPersuacion()" + e);
        }
    }

    public void guardarPersona() {
        try {
            personaService.personaInsertar(persona);
            msg.messageInfo("Se registro la Persona", null);
        } catch (Exception e) {
            log.error("ERROR - guardarPersona()" + e);
        }
    }

    public boolean guardarVincularPersona2() {
        try {
            persona.setUsuRegistro(usuarioSession.getCodigo());
            persona.setFechaRegistro(new Date());
            persona.setFechaModificacion(new Date());
            persona.setUsuModificacion(usuarioSession.getCodigo());
            setearPersonaSeleccionada(persona);
            msg.messageInfo("Se registro la Persona", null);
            return true;
        } catch (Exception e) {
            log.error("ERROR - guardarVincularPersona2()" + e);
        }
        return false;
    }

    public boolean guardarVincularPersona() {
        try {
            if (StringUtils.isBlank(persona.getTipo())) {
                msg.messageAlert("Debe ingresar si es persona u organización", null);
                return false;
            } else {
                if (StringUtils.isBlank(persona.getNombre().trim())) {
                    msg.messageAlert("Debe ingresar el nombre", null);
                    return false;
                }
            }
            persona.setUsuRegistro(usuarioSession.getCodigo());
            persona.setFechaRegistro(new Date());
            persona.setFechaModificacion(new Date());
            persona.setUsuModificacion(usuarioSession.getCodigo());
            boolean valid = personaService.personaInsertar(persona);
            if (!valid) {
                msg.messageAlert("El DNI ya se encuentra registrado", null);
                return false;
            }
            setearPersonaSeleccionada(persona);
            if (StringUtils.equals(persona.getTipo(), "PER")) {
                msg.messageInfo("Se registro la Persona", null);
            } else {
                msg.messageInfo("Se registro la Organización", null);
            }
            return true;
        } catch (Exception e) {
            log.error("ERROR - guardarVincularPersona()" + e);
        }
        return false;
    }

    public boolean guardarVincularListaPersona() {
        try {
            if (StringUtils.isBlank(persona.getTipo())) {
                msg.messageAlert("Debe ingresar si es persona u organización", null);
                return false;
            } else {
                if (StringUtils.isBlank(persona.getNombre().trim())) {
                    msg.messageAlert("Debe ingresar el nombre", null);
                    return false;
                }
            }
            persona.setUsuRegistro(usuarioSession.getCodigo());
            persona.setFechaRegistro(new Date());
            persona.setFechaModificacion(new Date());
            persona.setUsuModificacion(usuarioSession.getCodigo());
            boolean valid = personaService.personaInsertar(persona);
            if (!valid) {
                msg.messageAlert("El DNI ya se encuentra registrado", null);
                return false;
            }
            addPersona(persona);
            if (StringUtils.equals(persona.getTipo(), "PER")) {
                msg.messageInfo("Se registro la Persona", null);
            } else {
                msg.messageInfo("Se registro la Organización", null);
            }
            return true;
        } catch (Exception e) {
            log.error("ERROR - guardarVincularListaPersona()" + e);
        }
        return false;
    }

    public void guardarEntidad() {
        try {
            entidadService.entidadInsertar(entidad);
            msg.messageInfo("Se registro la Entidad", null);
        } catch (Exception e) {
            log.error("ERROR - guardarEntidad()" + e);
        }

    }

    public void insertUpdateExpedientePersona(ExpedientePersona ep) {
        try {
            if (ep.getExpediente().getId() != null && ep.getPersona().getId() != null) {
                if (ep.getId() == null) {
                    expedientePersonaService.expedientePersonaInsertar(ep);
                } else {
                    int contador = expedientePersonaService.expedientePersonaContar(ep);
                    if (contador == 0) {
                        expedientePersonaService.expedientePersonaInsertar(ep);
                    } else {
                        expedientePersonaService.expedientePersonaUpdate(ep);
                    }
                }
            }
        } catch (Exception e) {
            log.error("ERROR - insertUpdateExpedientePersona()" + e);
        }
    }

    public void insertUpdateExpedienteEntidad(ExpedienteEntidad ee) {
        try {
            if (ee.getExpediente().getId() != null && ee.getEntidad().getId() != null) {
                int contador = expedienteEntidadService.expedienteEntidadContar(ee);
                if (contador == 0) {
                    expedienteEntidadService.expedienteEntidadInsertar(ee);
                }
            }
        } catch (Exception e) {
            log.error("ERROR - insertUpdateExpedienteEntidad()" + e);
        }
    }

    public boolean agregarEtiqueta() {
        try {
            if (stringUtil.isBlank(expediente.getIdEtiqueta())) {
                msg.messageAlert("Debe de seleccionar una Etiqueta", null);
                return false;
            }
            for (Parametro p1 : listaEtiquetasSeleccionadas) {
                if (p1.getValorParametro().equals(expediente.getIdEtiqueta())) {
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
        } catch (Exception e) {
            log.error("ERROR - agregarEtiqueta()" + e);
        }
        return false;
    }

    public void cargarModalReplica() {
        expedienteBusquedaReplica = new Expediente();
        listaExpedienteXUsuarioPaginadoReplica = null;
        listaGestionesParaReplica = null;
        idGestionReplica = null;
    }

    public void buscarModalReplica() {
        try {
            listarExpedienteUsuarioPaginadoCompleto(1, expedienteBusquedaReplica);
        } catch (Exception e) {
            log.error("ERROR - buscarModalReplica()" + e);
        }
    }

    public void removeEtiqueta(Parametro param) {
        try {
            listaEtiquetasSeleccionadas.remove(param);
            msg.messageInfo("Se elimino la Etiqueta", null);
        } catch (Exception e) {
            log.error("ERROR - removeEtiqueta()" + e);
        }
    }

    private Parametro codigoParamentro(int codigo, String valor) {
        FiltroParametro filtro = new FiltroParametro();
        filtro.setCodigoPadreParametro(codigo);
        filtro.setValorParametro(valor);
        Parametro p = parametroService.consultarParametroValor(filtro);
        return p;
    }

    private String encadenarEtiquetas() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        for (Parametro p : listaEtiquetasSeleccionadas) {
            sb.append(p.getValorParametro()).append(",");
        }
        return sb.toString();
    }

    public void comboProvincia() {
        try {
            listaProvincia = new ArrayList<>();
            listaDistrito = new ArrayList<>();
            String idDepartamento = persona.getIdDepartamento();
            if (StringUtils.equals(idDepartamento, "0")) {
                listaProvincia.clear();
            } else {
                List<Provincia> list = ubigeoService.provinciaLista(idDepartamento);
                if (list.size() > 0) {
                    for (Provincia provincia : list) {
                        listaProvincia.add(new SelectItem(provincia.getIdProvincia(), provincia.getDescripcion()));
                    }
                }
            }
        } catch (Exception e) {
            log.error("ERROR - comboProvincia()" + e);
        }
    }

    public void comboDistrito() {
        try {
            listaDistrito = new ArrayList<>();
            String idProvincia = persona.getIdProvincia();
            String idDepartamento = persona.getIdDepartamento();
            if (StringUtils.equals(idProvincia, "0") || StringUtils.equals(idDepartamento, "0")) {
                listaDistrito.clear();
            } else {
                Distrito d = new Distrito();
                d.setIdDepartamento(idDepartamento);
                d.setIdProvincia(idProvincia);
                List<Distrito> list = ubigeoService.distritoLista(d);
                if (list.size() > 0) {
                    for (Distrito distrito : list) {
                        listaDistrito.add(new SelectItem(distrito.getIdDistrito(), distrito.getDescripcion()));
                    }
                }
            }
        } catch (Exception e) {
            log.error("ERROR - comboDistrito()" + e);
        }
    }

    public void comboProvinciaId(String idDepartamento) {
        try {
            listaProvincia = new ArrayList<>();
            listaDistrito = new ArrayList<>();
            if (StringUtils.equals(idDepartamento, "0")) {
                listaProvincia.clear();
            } else {
                List<Provincia> list = ubigeoService.provinciaLista(idDepartamento);
                if (list.size() > 0) {
                    for (Provincia provincia : list) {
                        listaProvincia.add(new SelectItem(provincia.getIdProvincia(), provincia.getDescripcion()));
                    }
                }
            }
        } catch (Exception e) {
            log.error("ERROR - comboProvinciaId()" + e);
        }
    }

    public void comboDistritoId(String idProvincia, String idDepartamento) {
        try {
            listaDistrito = new ArrayList<>();
            if (StringUtils.equals(idProvincia, "0")) {
                listaDistrito.clear();
            } else {
                Distrito d = new Distrito();
                d.setIdDepartamento(idDepartamento);
                d.setIdProvincia(idProvincia);
                List<Distrito> list = ubigeoService.distritoLista(d);
                if (list.size() > 0) {
                    for (Distrito distrito : list) {
                        listaDistrito.add(new SelectItem(distrito.getIdDistrito(), distrito.getDescripcion()));
                    }
                }
            }
        } catch (Exception e) {
            log.error("ERROR - comboDistritoId()" + e);
        }

    }

    private String uploadArchive(Part fil) {
            String nameArchive = getFilename(fil);
            String extencion = getFileExtension(getFilename(fil));
            if (StringUtils.isNoneBlank(nameArchive)) {
                String formato = RandomStringUtils.random(32, 0, 20, true, true, "qw32rfHIJk9iQ8Ud7h0X".toCharArray());
                String ruta = formato + extencion;
                File file = new File(FILE_SYSTEM + ruta);
                try (InputStream input = fil.getInputStream()) {
                    Files.copy(input, file.toPath());
                } catch (IOException ex) {
                    log.error(ex);
                }
                return ruta;
            }
        return null;
    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf("=") + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }

    private String getFileExtension(String name) {
        try {
            return name.substring(name.lastIndexOf("."));
        } catch (Exception e) {
            return "";
        }
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

    public List<Persona> getListaPersonaGeneral() {
        return listaPersonaGeneral;
    }

    public void setListaPersonaGeneral(List<Persona> listaPersonaGeneral) {
        this.listaPersonaGeneral = listaPersonaGeneral;
    }

    public Persona getPersonaBusqueda() {
        return personaBusqueda;
    }

    public void setPersonaBusqueda(Persona personaBusqueda) {
        this.personaBusqueda = personaBusqueda;
    }

    public Persona getPersonaSeleccionada() {
        return personaSeleccionada;
    }

    public void setPersonaSeleccionada(Persona personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
    }

    public boolean isIndSeleccion() {
        return indSeleccion;
    }

    public void setIndSeleccion(boolean indSeleccion) {
        this.indSeleccion = indSeleccion;
    }

    public List<SelectItem> getListaProvincia() {
        return listaProvincia;
    }

    public void setListaProvincia(List<SelectItem> listaProvincia) {
        this.listaProvincia = listaProvincia;
    }

    public List<SelectItem> getListaDistrito() {
        return listaDistrito;
    }

    public void setListaDistrito(List<SelectItem> listaDistrito) {
        this.listaDistrito = listaDistrito;
    }

    public List<Expediente> getListaExpedienteXUsuario() {
        return listaExpedienteXUsuario;
    }

    public void setListaExpedienteXUsuario(List<Expediente> listaExpedienteXUsuario) {
        this.listaExpedienteXUsuario = listaExpedienteXUsuario;
    }

    public String getGrafico001() {
        return grafico001;
    }

    public void setGrafico001(String grafico001) {
        this.grafico001 = grafico001;
    }

    public String getGrafico002() {
        return grafico002;
    }

    public void setGrafico002(String grafico002) {
        this.grafico002 = grafico002;
    }

    public List<Expediente> getListaExpedienteXUsuarioPaginado() {
        return listaExpedienteXUsuarioPaginado;
    }

    public void setListaExpedienteXUsuarioPaginado(List<Expediente> listaExpedienteXUsuarioPaginado) {
        this.listaExpedienteXUsuarioPaginado = listaExpedienteXUsuarioPaginado;
    }

    public Integer getNroPagina() {
        return nroPagina;
    }

    public void setNroPagina(Integer nroPagina) {
        this.nroPagina = nroPagina;
    }

    public String getGrafico003() {
        return grafico003;
    }

    public void setGrafico003(String grafico003) {
        this.grafico003 = grafico003;
    }

    public List<Expediente> getListaExpedienteXPersona() {
        return listaExpedienteXPersona;
    }

    public void setListaExpedienteXPersona(List<Expediente> listaExpedienteXPersona) {
        this.listaExpedienteXPersona = listaExpedienteXPersona;
    }

    public ExpedienteGestion getExpedienteGestion() {
        return expedienteGestion;
    }

    public void setExpedienteGestion(ExpedienteGestion expedienteGestion) {
        this.expedienteGestion = expedienteGestion;
    }

    public List<SelectItem> getListaEstadoCalificacionQueja() {
        listaEstadoCalificacionQueja = EstadoExpedienteType.getListEstado(1);
        return listaEstadoCalificacionQueja;
    }

    public List<SelectItem> getListaEstadoInvestigacionQueja() {
        listaEstadoInvestigacionQueja = EstadoExpedienteType.getListEstado(2);
        return listaEstadoInvestigacionQueja;
    }

    public EtapaEstado getEtapaEstado() {
        return etapaEstado;
    }

    public void setEtapaEstado(EtapaEstado etapaEstado) {
        this.etapaEstado = etapaEstado;
    }

    public List<SelectItem> getListaEstadoPersuacionQueja() {
        listaEstadoPersuacionQueja = EstadoExpedienteType.getListEstado(3);
        return listaEstadoPersuacionQueja;
    }

    public List<SelectItem> getListaEstadoSeguimientoQueja() {
        listaEstadoSeguimientoQueja = EstadoExpedienteType.getListEstado(4);
        return listaEstadoSeguimientoQueja;
    }

    public List<ExpedienteGestion> getListaExpedienteGestion() {
        return listaExpedienteGestion;
    }

    public void setListaExpedienteGestion(List<ExpedienteGestion> listaExpedienteGestion) {
        this.listaExpedienteGestion = listaExpedienteGestion;
    }

    public List<ExpedienteGestion> getListaExpedientesCalificacionQueja() {
        return listaExpedientesCalificacionQueja;
    }

    public void setListaExpedientesCalificacionQueja(List<ExpedienteGestion> listaExpedientesCalificacionQueja) {
        this.listaExpedientesCalificacionQueja = listaExpedientesCalificacionQueja;
    }

    public List<ExpedienteGestion> getListaExpedientesInvestigacionQueja() {
        return listaExpedientesInvestigacionQueja;
    }

    public void setListaExpedientesInvestigacionQueja(List<ExpedienteGestion> listaExpedientesInvestigacionQueja) {
        this.listaExpedientesInvestigacionQueja = listaExpedientesInvestigacionQueja;
    }

    public List<ExpedienteGestion> getListaExpedientesPersuacionQueja() {
        return listaExpedientesPersuacionQueja;
    }

    public void setListaExpedientesPersuacionQueja(List<ExpedienteGestion> listaExpedientesPersuacionQueja) {
        this.listaExpedientesPersuacionQueja = listaExpedientesPersuacionQueja;
    }

    public List<ExpedienteGestion> getListaExpedientesSeguimientoQueja() {
        return listaExpedientesSeguimientoQueja;
    }

    public void setListaExpedientesSeguimientoQueja(List<ExpedienteGestion> listaExpedientesSeguimientoQueja) {
        this.listaExpedientesSeguimientoQueja = listaExpedientesSeguimientoQueja;
    }

    public Long getNroPaginaPersona() {
        return nroPaginaPersona;
    }

    public void setNroPaginaPersona(Long nroPaginaPersona) {
        this.nroPaginaPersona = nroPaginaPersona;
    }

    public List<SelectItem> getListaEstadoCalificacionPetitorio() {
        listaEstadoCalificacionPetitorio = EstadoExpedienteType.getListEstado(5);
        return listaEstadoCalificacionPetitorio;
    }

    public List<SelectItem> getListaEstadoGestionPetitorio() {
        listaEstadoGestionPetitorio = EstadoExpedienteType.getListEstado(6);
        return listaEstadoGestionPetitorio;
    }

    public List<SelectItem> getListaEstadoPersuacionPetitorio() {
        listaEstadoPersuacionPetitorio = EstadoExpedienteType.getListEstado(7);
        return listaEstadoPersuacionPetitorio;
    }

    public List<ExpedienteGestion> getListaExpedientesCalificacionPetitorio() {
        return listaExpedientesCalificacionPetitorio;
    }

    public void setListaExpedientesCalificacionPetitorio(List<ExpedienteGestion> listaExpedientesCalificacionPetitorio) {
        this.listaExpedientesCalificacionPetitorio = listaExpedientesCalificacionPetitorio;
    }

    public List<ExpedienteGestion> getListaExpedientesGestionPetitorio() {
        return listaExpedientesGestionPetitorio;
    }

    public void setListaExpedientesGestionPetitorio(List<ExpedienteGestion> listaExpedientesGestionPetitorio) {
        this.listaExpedientesGestionPetitorio = listaExpedientesGestionPetitorio;
    }

    public List<ExpedienteGestion> getListaExpedientesPersuacionPetitorio() {
        return listaExpedientesPersuacionPetitorio;
    }

    public void setListaExpedientesPersuacionPetitorio(List<ExpedienteGestion> listaExpedientesPersuacionPetitorio) {
        this.listaExpedientesPersuacionPetitorio = listaExpedientesPersuacionPetitorio;
    }

    public ExpedienteDerivacion getExpedienteDerivacionEnvia() {
        return expedienteDerivacionEnvia;
    }

    public void setExpedienteDerivacionEnvia(ExpedienteDerivacion expedienteDerivacionEnvia) {
        this.expedienteDerivacionEnvia = expedienteDerivacionEnvia;
    }

    public ExpedienteDerivacion getExpedienteDerivacionAprueba() {
        return expedienteDerivacionAprueba;
    }

    public void setExpedienteDerivacionAprueba(ExpedienteDerivacion expedienteDerivacionAprueba) {
        this.expedienteDerivacionAprueba = expedienteDerivacionAprueba;
    }

    public List<SelectItem> getListaUsuariosComisionadosPorOD() {
        System.out.println("getListaUsuariosComisionadosPorOD");
        List<SelectItem> listaUsuario = new ArrayList<>();
        try {
            Usuario u = new Usuario();
            u.setCodigoOD(expedienteDerivacionAprueba.getIdOficinaDefensorial());
            if(expedienteDerivacionAprueba.getIdOficinaDefensorial() < 1000)
                u.setRol(RolType.COMISIONADO_OD.getKey());
            else
                u.setRol(RolType.COMISIONADO_AD.getKey());
            List<Usuario> list = usuarioService.listaUsuariosPorOD(u);
            for (Usuario u1 : list) {
                listaUsuario.add(new SelectItem(u1.getCodigo(), u1.getNombre() + " " + u1.getApellidoPaterno() + " " + u1.getApellidoMaterno()));
            }
            listaUsuariosComisionadosPorOD = listaUsuario;
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return listaUsuariosComisionadosPorOD;
    }

    public ExpedienteDerivacion getExpedienteDerivacionReasigna() {
        return expedienteDerivacionReasigna;
    }

    public void setExpedienteDerivacionReasigna(ExpedienteDerivacion expedienteDerivacionReasigna) {
        this.expedienteDerivacionReasigna = expedienteDerivacionReasigna;
    }

    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }

    public Part getFile2() {
        return file2;
    }

    public void setFile2(Part file2) {
        this.file2 = file2;
    }

    public ExpedienteConsulta getExpedienteConsultaEnvia() {
        return expedienteConsultaEnvia;
    }

    public void setExpedienteConsultaEnvia(ExpedienteConsulta expedienteConsultaEnvia) {
        this.expedienteConsultaEnvia = expedienteConsultaEnvia;
    }

    public boolean isVerBotonRegistrarExpediente() {
        return verBotonRegistrarExpediente;
    }

    public void setVerBotonRegistrarExpediente(boolean verBotonRegistrarExpediente) {
        this.verBotonRegistrarExpediente = verBotonRegistrarExpediente;
    }

    public List<SelectItem> getListaAdjuntiaDefensoriales() {
        List<SelectItem> listaAdjuntiaDef = new ArrayList<>();
        try {
            List<OficinaDefensorial> list = oficinaDefensorialService.listaAdjuntiasDefensorialesConsulta();
            for (OficinaDefensorial od : list) {
                listaAdjuntiaDef.add(new SelectItem(od.getId(), od.getNombre()));
            }
            listaAdjuntiaDefensoriales = listaAdjuntiaDef;
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return listaAdjuntiaDefensoriales;
    }

    public Part getFile3() {
        return file3;
    }

    public void setFile3(Part file3) {
        this.file3 = file3;
    }

    public List<ExpedienteConsulta> getListaExpedienteConsultaEnvia() {
        return listaExpedienteConsultaEnvia;
    }

    public void setListaExpedienteConsultaEnvia(List<ExpedienteConsulta> listaExpedienteConsultaEnvia) {
        this.listaExpedienteConsultaEnvia = listaExpedienteConsultaEnvia;
    }

    public List<SelectItem> getListaClasificacionSegundoLevel() {
        return listaClasificacionSegundoLevel;
    }

    public void setListaClasificacionSegundoLevel(List<SelectItem> listaClasificacionSegundoLevel) {
        this.listaClasificacionSegundoLevel = listaClasificacionSegundoLevel;
    }

    public List<SelectItem> getListaClasificacionTercerLevel() {
        return listaClasificacionTercerLevel;
    }

    public void setListaClasificacionTercerLevel(List<SelectItem> listaClasificacionTercerLevel) {
        this.listaClasificacionTercerLevel = listaClasificacionTercerLevel;
    }

    public List<SelectItem> getListaClasificacionCuartoLevel() {
        return listaClasificacionCuartoLevel;
    }

    public void setListaClasificacionCuartoLevel(List<SelectItem> listaClasificacionCuartoLevel) {
        this.listaClasificacionCuartoLevel = listaClasificacionCuartoLevel;
    }

    public List<SelectItem> getListaClasificacionQuintoLevel() {
        return listaClasificacionQuintoLevel;
    }

    public void setListaClasificacionQuintoLevel(List<SelectItem> listaClasificacionQuintoLevel) {
        this.listaClasificacionQuintoLevel = listaClasificacionQuintoLevel;
    }

    public List<SelectItem> getListaClasificacionSextoLevel() {
        return listaClasificacionSextoLevel;
    }

    public void setListaClasificacionSextoLevel(List<SelectItem> listaClasificacionSextoLevel) {
        this.listaClasificacionSextoLevel = listaClasificacionSextoLevel;
    }

    public ExpedienteConsulta getExpedienteConsultaAprueba() {
        return expedienteConsultaAprueba;
    }

    public void setExpedienteConsultaAprueba(ExpedienteConsulta expedienteConsultaAprueba) {
        this.expedienteConsultaAprueba = expedienteConsultaAprueba;
    }

    public ExpedienteConsulta getExpedienteConsultaReasigna() {
        return expedienteConsultaReasigna;
    }

    public void setExpedienteConsultaReasigna(ExpedienteConsulta expedienteConsultaReasigna) {
        this.expedienteConsultaReasigna = expedienteConsultaReasigna;
    }

    public List<SelectItem> getListaUsuariosComisionadosPorAD() {
        return listaUsuariosComisionadosPorAD;
    }

    public void setListaUsuariosComisionadosPorAD(List<SelectItem> listaUsuariosComisionadosPorAD) {
        this.listaUsuariosComisionadosPorAD = listaUsuariosComisionadosPorAD;
    }

    public List<ExpedienteNivel> getListaExpedienteNivel() {
        return listaExpedienteNivel;
    }

    public void setListaExpedienteNivel(List<ExpedienteNivel> listaExpedienteNivel) {
        this.listaExpedienteNivel = listaExpedienteNivel;
    }

    public ExpedienteNivel getExpedienteNivel() {
        return expedienteNivel;
    }

    public void setExpedienteNivel(ExpedienteNivel expedienteNivel) {
        this.expedienteNivel = expedienteNivel;
    }

    public List<ExpedienteNivel> getListaExpedienteNivelModal() {
        return listaExpedienteNivelModal;
    }

    public void setListaExpedienteNivelModal(List<ExpedienteNivel> listaExpedienteNivelModal) {
        this.listaExpedienteNivelModal = listaExpedienteNivelModal;
    }

    public ExpedienteClasificacion getExpedienteClasificacionBusqueda() {
        return expedienteClasificacionBusqueda;
    }

    public void setExpedienteClasificacionBusqueda(ExpedienteClasificacion expedienteClasificacionBusqueda) {
        this.expedienteClasificacionBusqueda = expedienteClasificacionBusqueda;
    }

    public Integer getNroPaginaModal() {
        return nroPaginaModal;
    }

    public void setNroPaginaModal(Integer nroPaginaModal) {
        this.nroPaginaModal = nroPaginaModal;
    }

    public Part getFile4() {
        return file4;
    }

    public void setFile4(Part file4) {
        this.file4 = file4;
    }

    public List<Usuario> getListaUsuarioOD() {
        return listaUsuarioOD;
    }

    public void setListaUsuarioOD(List<Usuario> listaUsuarioOD) {
        this.listaUsuarioOD = listaUsuarioOD;
    }

    public Part getFile5() {
        return file5;
    }

    public void setFile5(Part file5) {
        this.file5 = file5;
    }

    public ExpedientePersona getExpedientepersonaModalEdit() {
        return expedientepersonaModalEdit;
    }

    public void setExpedientepersonaModalEdit(ExpedientePersona expedientepersonaModalEdit) {
        this.expedientepersonaModalEdit = expedientepersonaModalEdit;
    }

    public List<ExpedienteDerivacion> getListaExpedienteDerivacion() {
        return listaExpedienteDerivacion;
    }

    public void setListaExpedienteDerivacion(List<ExpedienteDerivacion> listaExpedienteDerivacion) {
        this.listaExpedienteDerivacion = listaExpedienteDerivacion;
    }

    public Expediente getExpedienteBusquedaReplica() {
        return expedienteBusquedaReplica;
    }

    public void setExpedienteBusquedaReplica(Expediente expedienteBusquedaReplica) {
        this.expedienteBusquedaReplica = expedienteBusquedaReplica;
    }

    public Integer getInicioPaginado() {
        return inicioPaginado;
    }

    public void setInicioPaginado(Integer inicioPaginado) {
        this.inicioPaginado = inicioPaginado;
    }

    public Integer getFinPaginado() {
        return finPaginado;
    }

    public void setFinPaginado(Integer finPaginado) {
        this.finPaginado = finPaginado;
    }

    public List<Expediente> getListaExpedienteXUsuarioPaginadoReplica() {
        return listaExpedienteXUsuarioPaginadoReplica;
    }

    public void setListaExpedienteXUsuarioPaginadoReplica(List<Expediente> listaExpedienteXUsuarioPaginadoReplica) {
        this.listaExpedienteXUsuarioPaginadoReplica = listaExpedienteXUsuarioPaginadoReplica;
    }

    public List<ExpedienteGestion> getListaGestionesParaReplica() {
        return listaGestionesParaReplica;
    }

    public void setListaGestionesParaReplica(List<ExpedienteGestion> listaGestionesParaReplica) {
        this.listaGestionesParaReplica = listaGestionesParaReplica;
    }

    public Long getIdGestionReplica() {
        return idGestionReplica;
    }

    public void setIdGestionReplica(Long idGestionReplica) {
        this.idGestionReplica = idGestionReplica;
    }

    public ExpedienteConsulta getExpedienteConsultaResponde() {
        return expedienteConsultaResponde;
    }

    public void setExpedienteConsultaResponde(ExpedienteConsulta expedienteConsultaResponde) {
        this.expedienteConsultaResponde = expedienteConsultaResponde;
    }

    public Integer getTipoBusqueda() {
        return tipoBusqueda;
    }

    public void setTipoBusqueda(Integer tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }

    public List<ListadoClasificacion> getListadoClasificacionTipo() {
        return listadoClasificacionTipo;
    }

    public void setListadoClasificacionTipo(List<ListadoClasificacion> listadoClasificacionTipo) {
        this.listadoClasificacionTipo = listadoClasificacionTipo;
    }

    public Integer getIdSegundaClasificacion() {
        return idSegundaClasificacion;
    }

    public void setIdSegundaClasificacion(Integer idSegundaClasificacion) {
        this.idSegundaClasificacion = idSegundaClasificacion;
    }

    public List<ExpedienteClasificacionTipo> getListaExpedienteClasificacion() {
        return listaExpedienteClasificacion;
    }

    public void setListaExpedienteClasificacion(List<ExpedienteClasificacionTipo> listaExpedienteClasificacion) {
        this.listaExpedienteClasificacion = listaExpedienteClasificacion;
    }

    public Long getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(Long idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    public ExpedienteONP getExpedienteONP() {
        return expedienteONP;
    }

    public void setExpedienteONP(ExpedienteONP expedienteONP) {
        this.expedienteONP = expedienteONP;
    }

    public List<ExpedienteGestion> getListaGestionesONP() {
        return listaGestionesONP;
    }

    public void setListaGestionesONP(List<ExpedienteGestion> listaGestionesONP) {
        this.listaGestionesONP = listaGestionesONP;
    }

    public ExpedienteTiempo getExpedienteTiempo() {
        return expedienteTiempo;
    }

    public void setExpedienteTiempo(ExpedienteTiempo expedienteTiempo) {
        this.expedienteTiempo = expedienteTiempo;
    }

    public Boolean getVerSeccionONP() {
        return verSeccionONP;
    }

    public void setVerSeccionONP(Boolean verSeccionONP) {
        this.verSeccionONP = verSeccionONP;
    }

    public ExpedienteConsulta getExpedienteConsultaPadre() {
        return expedienteConsultaPadre;
    }

    public void setExpedienteConsultaPadre(ExpedienteConsulta expedienteConsultaPadre) {
        this.expedienteConsultaPadre = expedienteConsultaPadre;
    }

    public List<ExpedienteConsulta> getListaExpedienteTotalesEnvia() {
        return listaExpedienteTotalesEnvia;
    }

    public void setListaExpedienteTotalesEnvia(List<ExpedienteConsulta> listaExpedienteTotalesEnvia) {
        this.listaExpedienteTotalesEnvia = listaExpedienteTotalesEnvia;
    }

    public List<ExpedienteConsulta> getListaExpedienteTotalesAprueba() {
        return listaExpedienteTotalesAprueba;
    }

    public void setListaExpedienteTotalesAprueba(List<ExpedienteConsulta> listaExpedienteTotalesAprueba) {
        this.listaExpedienteTotalesAprueba = listaExpedienteTotalesAprueba;
    }

    public List<ExpedienteConsulta> getListaExpedienteTotalesReasigna() {
        return listaExpedienteTotalesReasigna;
    }

    public void setListaExpedienteTotalesReasigna(List<ExpedienteConsulta> listaExpedienteTotalesReasigna) {
        this.listaExpedienteTotalesReasigna = listaExpedienteTotalesReasigna;
    }

    public List<ExpedienteConsulta> getListaExpedienteTotalesResponde() {
        return listaExpedienteTotalesResponde;
    }

    public void setListaExpedienteTotalesResponde(List<ExpedienteConsulta> listaExpedienteTotalesResponde) {
        this.listaExpedienteTotalesResponde = listaExpedienteTotalesResponde;
    }

    public ExpedienteConsulta getExpedienteRespuestaAprueba() {
        return expedienteRespuestaAprueba;
    }

    public void setExpedienteRespuestaAprueba(ExpedienteConsulta expedienteRespuestaAprueba) {
        this.expedienteRespuestaAprueba = expedienteRespuestaAprueba;
    }

    public ExpedienteConsulta getExpedienteRespuestaAcepta() {
        return expedienteRespuestaAcepta;
    }

    public void setExpedienteRespuestaAcepta(ExpedienteConsulta expedienteRespuestaAcepta) {
        this.expedienteRespuestaAcepta = expedienteRespuestaAcepta;
    }

    public ExpedienteConsulta getExpedienteRespuestaRecibe() {
        return expedienteRespuestaRecibe;
    }

    public void setExpedienteRespuestaRecibe(ExpedienteConsulta expedienteRespuestaRecibe) {
        this.expedienteRespuestaRecibe = expedienteRespuestaRecibe;
    }

    public Part getFile6() {
        return file6;
    }

    public void setFile6(Part file6) {
        this.file6 = file6;
    }

    public ExpedienteSuspencion getExpedienteSuspencionEnvia() {
        return expedienteSuspencionEnvia;
    }

    public void setExpedienteSuspencionEnvia(ExpedienteSuspencion expedienteSuspencionEnvia) {
        this.expedienteSuspencionEnvia = expedienteSuspencionEnvia;
    }

    public ExpedienteAmpliacion getExpedienteAmpliacionEnvia() {
        return expedienteAmpliacionEnvia;
    }

    public void setExpedienteAmpliacionEnvia(ExpedienteAmpliacion expedienteAmpliacionEnvia) {
        this.expedienteAmpliacionEnvia = expedienteAmpliacionEnvia;
    }

    public ExpedienteSuspencion getExpedienteSuspencionAprueba() {
        return expedienteSuspencionAprueba;
    }

    public void setExpedienteSuspencionAprueba(ExpedienteSuspencion expedienteSuspencionAprueba) {
        this.expedienteSuspencionAprueba = expedienteSuspencionAprueba;
    }

    public ExpedienteAmpliacion getExpedienteAmpliacionAprueba() {
        return expedienteAmpliacionAprueba;
    }

    public void setExpedienteAmpliacionAprueba(ExpedienteAmpliacion expedienteAmpliacionAprueba) {
        this.expedienteAmpliacionAprueba = expedienteAmpliacionAprueba;
    }

    public ExpedienteSuspencion getExpedienteSuspencionAcepta() {
        return expedienteSuspencionAcepta;
    }

    public void setExpedienteSuspencionAcepta(ExpedienteSuspencion expedienteSuspencionAcepta) {
        this.expedienteSuspencionAcepta = expedienteSuspencionAcepta;
    }

    public ExpedienteAmpliacion getExpedienteAmpliacionAcepta() {
        return expedienteAmpliacionAcepta;
    }

    public void setExpedienteAmpliacionAcepta(ExpedienteAmpliacion expedienteAmpliacionAcepta) {
        this.expedienteAmpliacionAcepta = expedienteAmpliacionAcepta;
    }

    public List<ExpedienteSuspencion> getListaExpedienteSuspencion() {
        return listaExpedienteSuspencion;
    }

    public void setListaExpedienteSuspencion(List<ExpedienteSuspencion> listaExpedienteSuspencion) {
        this.listaExpedienteSuspencion = listaExpedienteSuspencion;
    }

    public List<ExpedienteAmpliacion> getListaExpedienteAmpliacion() {
        return listaExpedienteAmpliacion;
    }

    public void setListaExpedienteAmpliacion(List<ExpedienteAmpliacion> listaExpedienteAmpliacion) {
        this.listaExpedienteAmpliacion = listaExpedienteAmpliacion;
    }

    public Boolean getVerModalConclusion() {
        return verModalConclusion;
    }

    public void setVerModalConclusion(Boolean verModalConclusion) {
        this.verModalConclusion = verModalConclusion;
    }

    public ExpedienteHistorial getHistorial() {
        return historial;
    }

    public void setHistorial(ExpedienteHistorial historial) {
        this.historial = historial;
    }

    public List<ExpedienteHistorial> getListaHistorialExpediente() {
        return listaHistorialExpediente;
    }

    public void setListaHistorialExpediente(List<ExpedienteHistorial> listaHistorialExpediente) {
        this.listaHistorialExpediente = listaHistorialExpediente;
    }

    public String getUsuarioCompartir() {
        return usuarioCompartir;
    }

    public void setUsuarioCompartir(String usuarioCompartir) {
        this.usuarioCompartir = usuarioCompartir;
    }

    public Part getFile7() {
        return file7;
    }

    public void setFile7(Part file7) {
        this.file7 = file7;
    }

    public Boolean getEsSupervisor() {
        return esSupervisor;
    }

    public void setEsSupervisor(Boolean esSupervisor) {
        this.esSupervisor = esSupervisor;
    }

    public ExpedienteFormularioVirtual getExpedienteFormularioVirtual() {
        return expedienteFormularioVirtual;
    }

    public void setExpedienteFormularioVirtual(ExpedienteFormularioVirtual expedienteFormularioVirtual) {
        this.expedienteFormularioVirtual = expedienteFormularioVirtual;
    }

    public List<ExpedienteFormularioVirtual> getListaExpedienteFormularioVirtual() {
        return listaExpedienteFormularioVirtual;
    }

    public void setListaExpedienteFormularioVirtual(List<ExpedienteFormularioVirtual> listaExpedienteFormularioVirtual) {
        this.listaExpedienteFormularioVirtual = listaExpedienteFormularioVirtual;
    }

    public ExpedienteFormularioVirtual getFiltroFormularioVirtual() {
        return filtroFormularioVirtual;
    }

    public void setFiltroFormularioVirtual(ExpedienteFormularioVirtual filtroFormularioVirtual) {
        this.filtroFormularioVirtual = filtroFormularioVirtual;
    }

    public List<SelectItem> getListaClasificacionPrimerLevel() {
        List<ExpedienteClasificacion> listaClasi = expedienteClasificacionService.listaExpedienteClasificacion(new ExpedienteClasificacion(0, 1, "ACT"));
        List<SelectItem> listaClasificacion = new ArrayList<>();    
                for (ExpedienteClasificacion ec : listaClasi) {
                    listaClasificacion.add(new SelectItem(ec.getId(), ec.getNombre()));
                }
        listaClasificacionPrimerLevel = listaClasificacion;
        return listaClasificacionPrimerLevel;
    }

    public void setListaClasificacionPrimerLevel(List<SelectItem> listaClasificacionPrimerLevel) {
        this.listaClasificacionPrimerLevel = listaClasificacionPrimerLevel;
    }

}
