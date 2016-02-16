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
import gob.dp.sid.comun.type.EtapaConsultaType;
import gob.dp.sid.comun.type.EtapaDerivacionType;
import gob.dp.sid.comun.type.EtapaType;
import gob.dp.sid.comun.type.ExpedienteType;
import gob.dp.sid.comun.type.RolType;
import gob.dp.sid.registro.entity.Entidad;
import gob.dp.sid.registro.entity.EtapaEstado;
import gob.dp.sid.registro.entity.Expediente;
import gob.dp.sid.registro.entity.ExpedienteClasiTipo;
import gob.dp.sid.registro.entity.ExpedienteClasificacion;
import gob.dp.sid.registro.entity.ExpedienteClasificacionTipo;
import gob.dp.sid.registro.entity.ExpedienteConsulta;
import gob.dp.sid.registro.entity.ExpedienteDerivacion;
import gob.dp.sid.registro.entity.ExpedienteEntidad;
import gob.dp.sid.registro.entity.ExpedienteEtapa;
import gob.dp.sid.registro.entity.ExpedienteGestion;
import gob.dp.sid.registro.entity.ExpedienteNivel;
import gob.dp.sid.registro.entity.ExpedienteONP;
import gob.dp.sid.registro.entity.ExpedientePersona;
import gob.dp.sid.registro.entity.ExpedienteTiempo;
import gob.dp.sid.registro.entity.GestionEtapa;
import gob.dp.sid.registro.entity.OficinaDefensorial;
import gob.dp.sid.registro.entity.Persona;
import gob.dp.sid.registro.service.EntidadService;
import gob.dp.sid.registro.service.EtapaEstadoService;
import gob.dp.sid.registro.service.ExpedienteClasiTipoService;
import gob.dp.sid.registro.service.ExpedienteClasificacionService;
import gob.dp.sid.registro.service.ExpedienteClasificacionTipoService;
import gob.dp.sid.registro.service.ExpedienteConsultaService;
import gob.dp.sid.registro.service.ExpedienteDerivacionService;
import gob.dp.sid.registro.service.ExpedienteEntidadService;
import gob.dp.sid.registro.service.ExpedienteEtapaService;
import gob.dp.sid.registro.service.ExpedienteGestionService;
import gob.dp.sid.registro.service.ExpedienteNivelService;
import gob.dp.sid.registro.service.ExpedienteONPService;
import gob.dp.sid.registro.service.ExpedientePersonaService;
import gob.dp.sid.registro.service.ExpedienteService;
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

    private ExpedienteClasificacion expedienteClasificacionBusqueda;

    private Part file1;

    private Part file2;

    private Part file3;

    private Part file4;

    private Part file5;

    private boolean verBotonRegistrarExpediente = true;

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

    public String cargarNuevoExpediente() {
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
        return "expedienteNuevo";
    }

    private void cargarObjetoExpediente() {
        expediente = new Expediente();
        listaExpedienteNivel = new ArrayList<>();
        expediente.setListaExpedienteNivel(listaExpedienteNivel);
    }

    public String iniciarExpedienteNuevo() {
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
        return "expedienteNuevo";
    }

    private void cargarFichaONP() {
        listaGestionesONP = new ArrayList<>();
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
        }
    }

    private void insertarActualizarTiempos() {
        try {
            ExpedienteEtapa etapa = expedienteEtapaService.expedienteEtapaBuscar(etapaEstado.getVerEtapa());
            if (expediente.getVersion() == 1) {
                setearExpedienteTiempo(etapa, 1);
            } else {
                ExpedienteTiempo et = expedienteTiempoService.expedienteTiempoOne(expediente.getNumero());
                if (!Objects.equals(et.getEtapa(), etapa.getIdEtapa())) {
                    setearExpedienteTiempo(etapa, 2);
                }
            }
        } catch (Exception ex) {
            log.error(ex);
        }
    }

    private void setearExpedienteTiempo(ExpedienteEtapa etapa, int tip) {
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
    }

    private void setearExpedienteTiempo() {
        expedienteTiempo = expedienteTiempoService.expedienteTiempoOne(expediente.getNumero());
    }

    public void guardarExpedienteONP() {
        if (expedienteONP.getId() == null) {
            expedienteONP.setNumeroExpediente(expediente.getNumero());
            expedienteONPService.expedienteONPInsertar(expedienteONP);
        } else {
            expedienteONPService.expedienteONPUpdate(expedienteONP);
        }
        msg.messageInfo("Se realizaron los cambios", null);
    }

    public void consultarReniec() throws ParseException {
        /* String proxyHost = "172.30.1.250";
         String proxyPort = "8080";
         System.out.println("Setting up with proxy: " + proxyHost + ":" + proxyPort);
         System.setProperty("http.proxyHost", proxyHost);
         System.setProperty("http.proxyPort", proxyPort);
         System.setProperty("http.nonProxyHosts", "localhost|127.0.0.1");*/
        ServiceReniec reniec = new ServiceReniec();
        List<String> list = reniec.getConsultarServicio("DEPUWS", "DEPUWS!=", null, "08715701", persona.getNumeroDocumento());
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
    }

    public void cargarSegundaClasificacion(long idClasifica, int idPrimerNivel) {
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
    }

    public boolean addSegundaClasificacion() {
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
        return false;
    }

    public void removeSegundaClasificacion(ExpedienteClasificacionTipo ect) {
        expedienteClasiTipoService.expedienteClasiTipoEliminar(new ExpedienteClasiTipo(idClasificacion, ect.getId()));
        listaExpedienteClasificacion = expedienteClasificacionTipoService.clasificacioneExpedienteClasiTipo(idClasificacion);
        listarNiveles();
    }

    public String cargarNuevaBusqueda() {
        persona = new Persona();
        personaBusqueda = new Persona();
        personaSeleccionada = new Persona();
        listaPersonaGeneral = null;
        listaExpedienteXPersona = null;
        indSeleccion = true;
        return "expedienteUsuario";
    }

    public String irOficio() {
        iniciarExpedienteNuevo();
        expediente.setIndicadorOficio(true);
        personasSeleccionadas = new ArrayList<>();
        return "expedienteNuevo";
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
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
                list);
        jasperPrint = JasperFillManager.fillReport("C:\\recursos\\reportesSID\\expedienteConsulta.jasper",
                new HashMap(), beanCollectionDataSource);

    }

    public void initPetitorio() throws JRException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
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
        ficha.setExpedienteGestions(listaGestiones);
        if (expediente.getFechaIngreso() != null) {
            ficha.setFechaIngreso(simpleDateFormat.format(expediente.getFechaIngreso()));
        } else {
            ficha.setFechaIngreso("");
        }

        ficha.setFechaRegistro(simpleDateFormat.format(expediente.getFechaRegistro()));
        if (expediente.getClasificacionTipoNombre() == null) {
            fp.setCodigoPadreParametro(10);
            fp.setValorParametro(expediente.getTipoClasificion());
            parametro = parametroService.consultarParametroValor(fp);
            ficha.setClaseExpediente(parametro.getNombreParametro());
        } else {
            ficha.setClaseExpediente(expediente.getClasificacionTipoNombre());
        }
        ficha.setClaseExpediente(expediente.getClasificacionTipoNombre());
        fp.setCodigoPadreParametro(20);
        fp.setValorParametro(expediente.getTipoIngreso());
        parametro = parametroService.consultarParametroValor(fp);
        if (parametro != null) {
            ficha.setFormaIngreso(parametro.getNombreParametro().toUpperCase());
        } else {
            ficha.setFormaIngreso("");
        }
        ficha.setDireccion("Oficina");
        ficha.setLugarRecepcion("Oficina");
        ficha.setDescripcion(expediente.getSumilla().toUpperCase());
        ficha.setConclusion(expediente.getConclusion().toUpperCase());
        ficha.setCodigoUsuario(usuarioSession.getCodigo().toUpperCase());
        ficha.setExpedientePersonas(eps);
        /**
         * LISTA DE PERSONAS
         */
        list.add(ficha);
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
                list);
        jasperPrint = JasperFillManager.fillReport("C:\\recursos\\reportesSID\\expedientePetitorio.jasper",
                new HashMap(), beanCollectionDataSource);
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
            initConsulta();
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
        expediente = expedienteService.expedienteBuscarPorId(idExpediente);
        return cargarExpedienteEdit(expediente);
    }

    public String cargarExpedientePorNumero(String numeroExpediente) {
        expediente = expedienteService.expedienteBuscarPorNumero(numeroExpediente);
        return cargarExpedienteEdit(expediente);
    }

    public String cargarExpedienteGestion() {
        expedienteGestion = new ExpedienteGestion();
        expedienteBusquedaReplica = new Expediente();
        return "expedienteGestion";
    }

    public String cargarExpedienteGestionLista() {
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
        return "expedienteGestionLista";
    }

    public void cargarExpedienteEtapa(int etapa) {
        if (etapaEstado.getVerEtapa() == etapa) {
            expediente = expedienteService.expedienteBuscarActivo(expediente);
        } else {
            expediente.setIdEtapa(etapa);
            expediente = expedienteService.expedienteBuscarActivoEtapa(expediente);
        }
        setearExpediente(expediente);
    }

    public void inicio() {
        usuarioSession();
        listaExpedienteXUsuario = expedienteService.expedienteBuscarUsuario(usuarioSession.getCodigo());
        listarExpedienteUsuarioPaginadoOrder(1, 1);
        cargarGraficos001();
        cargarGraficos002();
        cargarGraficos003();
    }

    public String inicioAcciones() {
        return "expedienteAcciones";
    }

    public String datosGeneralesExpediente() {
        defineBotonRegistro();
        return "expedienteNuevo";
    }

    public void limpiarModalConsulta() {
        expedienteConsultaEnvia.setIdExpediente(expediente.getId());
        expedienteConsultaEnvia.setNumeroExpediente(expediente.getNumero());
    }
    
    public void limpiarModalConsultaAprueba(ExpedienteConsulta ec){
        //expedienteConsultaAprueba = expedienteConsultaService.expedienteConsultaSelectOne(id);
        expedienteConsultaAprueba = ec;
    }
    
    public void limpiarModalConsultaReasigna(ExpedienteConsulta ec){
        //expedienteConsultaAprueba = expedienteConsultaService.expedienteConsultaSelectOne(id);
        expedienteConsultaReasigna = ec;
        expedienteConsultaReasigna.setAprueba(null);
        cargarListaComisionadoAD();
    }
    
    public void limpiarModalConsultaResponde(ExpedienteConsulta ec){
        //expedienteConsultaAprueba = expedienteConsultaService.expedienteConsultaSelectOne(id);
        expedienteConsultaResponde = ec;
        expedienteConsultaResponde.setAprueba(null);
    }
    
    public void limpiarModalRespuestaAprueba(ExpedienteConsulta ec){
        //expedienteConsultaAprueba = expedienteConsultaService.expedienteConsultaSelectOne(id);
        expedienteRespuestaAprueba = ec;
        expedienteRespuestaAprueba.setAprueba(null);
    }
    
    public void limpiarModalRespuestaAcepta(ExpedienteConsulta ec){
        //expedienteConsultaAprueba = expedienteConsultaService.expedienteConsultaSelectOne(id);
        expedienteRespuestaAcepta = ec;
    }
    
    private void cargarListaComisionadoAD(){
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
            log.debug(e.getMessage());
        }
    }

    public void limpiarModalBusquedaClasificacion() {
        expedienteClasificacionBusqueda = new ExpedienteClasificacion();
        listaExpedienteNivelModal = new ArrayList<>();
        nroPaginaModal = 1;
    }

    public void limpiarModalAsignar() {
        listaUsuarioOD = usuarioService.listaUsuariosPorOD(usuarioSession);
    }

    public void guardarAsignado() {
        if (StringUtils.isBlank(expediente.getUsuarioAsignado())) {
            msg.messageAlert("Debe ingresar el usuario asignado", null);
        } else {
            expedienteService.expedienteAsignar(expediente);
            expediente.setUsuarioRegistro(expediente.getUsuarioAsignado());
            msg.messageInfo("Se asigno el expediente correctamente", null);
        }
    }

    public boolean buscarClasificacion(Integer pagina) {
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
    }

    private void clasificarNivel(ExpedienteClasificacion ec2, ExpedienteNivel en) {
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
    }

    public void cargarNivelesClasificacion(Integer idPadre, Integer grupo) {
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
    }

    public void guardarNivel() {
        if (expedienteNivel.getIdPrimerNivel() != null && expedienteNivel.getIdPrimerNivel() != 0) {
            if (expedienteNivel.getId() == null) {
                expedienteNivel.setNumeroExpediente(expediente.getNumero());
                expedienteNivel.setEstado("ACT");
                expedienteNivelService.expedienteNivelInsertar(expedienteNivel);
                msg.messageInfo("Se agrego una nueva clasificacion temática", null);
            } else {
                expedienteNivelService.expedienteNivelActualizar(expedienteNivel);
                msg.messageInfo("Se actualizo la clasificacion temática", null);
            }
            List<ExpedienteNivel> nivels = expedienteNivelService.expedienteNivelPorExpediente(expediente.getNumero());
            expediente.setListaExpedienteNivel(nivels);
            expedienteNivel = new ExpedienteNivel();
        }
        listarNiveles();
    }

    public void limpiarNivelesAll() {
        listaClasificacionSegundoLevel = new ArrayList<>();
        listaClasificacionTercerLevel = new ArrayList<>();
        listaClasificacionCuartoLevel = new ArrayList<>();
        listaClasificacionQuintoLevel = new ArrayList<>();
        listaClasificacionSextoLevel = new ArrayList<>();
    }

    public void guardarNivel(ExpedienteNivel en) {
        if (en.getIdPrimerNivel() != null && en.getIdPrimerNivel() != 0) {
            en.setNumeroExpediente(expediente.getNumero());
            en.setEstado("ACT");
            expedienteNivelService.expedienteNivelInsertar(en);
            List<ExpedienteNivel> nivels = expedienteNivelService.expedienteNivelPorExpediente(expediente.getNumero());
            expediente.setListaExpedienteNivel(nivels);
            msg.messageInfo("Se agrego una nueva clasificacion temática", null);
        }
    }

    public void inactivarNivel(ExpedienteNivel en) {
        expedienteNivelService.expedienteNivelUpdate(en.getId());
        List<ExpedienteNivel> nivels = expedienteNivelService.expedienteNivelPorExpediente(expediente.getNumero());
        expediente.setListaExpedienteNivel(nivels);
        listarNiveles();
    }

    public void editarNivel(ExpedienteNivel en) {
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
    }

    public void desistirExpediente() {
        expediente.setIndicadorDesestimiento(1);
        expediente.setGeneral("C");
        expedienteService.expedienteDesistir(expediente);
        msg.messageInfo("Se ha concluido el expediente, pasa al estado desistido", null);
    }

    private void defineBotonRegistro() {
        /*´para derivaciones*/
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
    }

    public String inicioAccionesConsulta() {
        expedienteConsultaAprueba = new ExpedienteConsulta();
        expedienteConsultaPadre = new ExpedienteConsulta();
        expedienteConsultaEnvia = new ExpedienteConsulta();
        expedienteConsultaReasigna = new ExpedienteConsulta();
        expedienteRespuestaAcepta = new ExpedienteConsulta();
        expedienteConsultaReasigna = new ExpedienteConsulta();
        expedienteRespuestaAprueba = new ExpedienteConsulta();
                
        listaExpedienteTotalesEnvia = new ArrayList<>();
        listaExpedienteTotalesAprueba = new ArrayList<>();
        listaExpedienteTotalesReasigna = new ArrayList<>();
        listaExpedienteTotalesResponde = new ArrayList<>();
        List<ExpedienteConsulta> lista = expedienteConsultaService.expedienteConsultaPorExpedientePadre(expediente.getNumero());
        for(ExpedienteConsulta ec : lista){
            if(ec.getEtapa() == 1){
                listaExpedienteTotalesEnvia.add(ec);
                listaExpedienteTotalesAprueba.add(ec);
            }
            if(ec.getEtapa() == 2){
                listaExpedienteTotalesEnvia.add(ec);
                listaExpedienteTotalesAprueba.add(ec);
                if(Objects.equals(ec.getIdAdjuntiaDefensorial(), usuarioSession.getCodigoOD())){
                    listaExpedienteTotalesReasigna.add(ec);
                }
                
            }
            if(ec.getEtapa() >= 3){
                listaExpedienteTotalesEnvia.add(ec);
                listaExpedienteTotalesAprueba.add(ec);
                listaExpedienteTotalesReasigna.add(ec);
                listaExpedienteTotalesResponde.add(ec);
            }
            
        }
        return "expedienteAccionesConsulta";
    }

    public String inicioAccionesDerivacion() {
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
    }

    public boolean enviarDerivacion() {
        if (StringUtils.isBlank(expedienteDerivacionEnvia.getDetalle())) {
            msg.messageAlert("Debe ingresar el detalle", null);
            return false;
        }
        if (expedienteDerivacionEnvia.getIdOficinaDefensorial() == 0) {
            msg.messageAlert("Debe seleccionar una oficina defensorial", null);
            return false;
        }
        if(expedienteDerivacionEnvia.getId() != null){
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
        return true;
    }

    public boolean aprobarDerivacion() {
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
        } else {
            guardarVersion2();
            enviarMensajeDesaprobacion();
            msg.messageInfo("No se aprobo la derivación", null);
        }
        return true;
    }

    public boolean reasignarDerivacion() {
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
        } else {
            guardarVersion2();
            enviarMensajeReasignacionDesaprobada();
            msg.messageInfo("Se rechaza la derivación", null);
        }
        return true;
    }

    public boolean enviarConsulta(int tipo) {
        if(expedienteConsultaEnvia.getId() != null){
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
            expedienteConsultaEnvia.setCodigo("C" + formato);
        }
        expedienteConsultaEnvia.setFecha(new Date());
        /**GRABAR EL PADRE*/
        expedienteConsultaService.expedienteConsultaInsertar(expedienteConsultaEnvia);
        /**GRABAR EL HIJO*/
        expedienteConsultaEnvia.setIdPadre(expedienteConsultaEnvia.getId());
        expedienteConsultaEnvia.setEtapa(EtapaConsultaType.CONSULTA_ETAPA_ENVIA.getKey());
        expedienteConsultaEnvia.setId(null);
        expedienteConsultaService.expedienteConsultaInsertar(expedienteConsultaEnvia);
        /***/
        enviarMensajeConsulta();
        inicioAccionesConsulta();
        msg.messageInfo("Se envio la Consulta", null);
        return true;
    }
    
    public boolean enviarAprobacion() {
        /*if(expedienteConsultaAprueba.getId() != null){
            return false;
        }*/
        String ruta1 = uploadArchive(file3);
        if(ruta1 != null){
            expedienteConsultaPadre.setRuta(ruta1);
        }
        setExpedienteConsultaPadre(expedienteConsultaAprueba);
        expedienteConsultaPadre.setCodigoUsuario(usuarioSession.getCodigo());
        expedienteConsultaPadre.setNombreUsuario(usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno());
        expedienteConsultaPadre.setEtapa(EtapaConsultaType.CONSULTA_ETAPA_APRUEBA.getKey());
        expedienteConsultaPadre.setFecha(new Date());
        /**ACTUALIZAR PADRE*/
        expedienteConsultaService.expedienteConsultaUpdate(expedienteConsultaPadre);
        /**GRABAR EL HIJO*/
        setExpedienteConsultaAprueba(expedienteConsultaPadre);
        expedienteConsultaAprueba.setIdPadre(expedienteConsultaPadre.getId());
        expedienteConsultaAprueba.setEtapa(EtapaConsultaType.CONSULTA_ETAPA_APRUEBA.getKey());
        expedienteConsultaAprueba.setId(null);
        expedienteConsultaService.expedienteConsultaInsertar(expedienteConsultaAprueba);
        /***/
        enviarMensajeAprobacionConsulta();
        inicioAccionesConsulta();
        msg.messageInfo("Se envio la Consulta", null);
        return true;
    }
    
    public boolean reasignarConsulta() {
        if (StringUtils.isBlank(expedienteConsultaReasigna.getAprueba())) {
            msg.messageAlert("Debe aceptar o rechazar la solicitud de derivación", null);
            return false;
        }

        if (StringUtils.isBlank(expedienteConsultaReasigna.getDetalle())) {
            msg.messageAlert("Debe ingresar el detalle", null);
            return false;
        }

        if (StringUtils.equals(expedienteConsultaReasigna.getAprueba(), "SI")) {
            if (StringUtils.equals(expedienteConsultaReasigna.getCodigoUsuarioReasignado(), "0")) {
                msg.messageAlert("Debe seleccionar el comisionado al cual derivará el expediente", null);
                return false;
            }
        }
        String ruta1 = uploadArchive(file3);
        if(ruta1 != null){
            expedienteConsultaPadre.setRuta(ruta1);
        }
        setExpedienteConsultaPadre(expedienteConsultaReasigna);
        expedienteConsultaPadre.setCodigoUsuario(usuarioSession.getCodigo());
        expedienteConsultaPadre.setNombreUsuario(usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno());
        expedienteConsultaPadre.setEtapa(EtapaConsultaType.CONSULTA_ETAPA_REASIGNA.getKey());
        expedienteConsultaPadre.setFecha(new Date());
        /**ACTUALIZAR PADRE*/
        expedienteConsultaService.expedienteConsultaUpdate(expedienteConsultaPadre);
        /**GRABAR EL HIJO*/
        setExpedienteConsultaReasigna(expedienteConsultaPadre);
        expedienteConsultaReasigna.setIdPadre(expedienteConsultaPadre.getId());
        expedienteConsultaReasigna.setEtapa(EtapaConsultaType.CONSULTA_ETAPA_REASIGNA.getKey());
        expedienteConsultaReasigna.setId(null);
        expedienteConsultaService.expedienteConsultaInsertar(expedienteConsultaReasigna);
        /***/
        
        if (StringUtils.equals(expedienteConsultaReasigna.getAprueba(), "SI")) {
            enviarMensajeReasignacionConsulta();
            msg.messageInfo("Se reasigno la consulta", null);
        } else {
            enviarMensajeReasignacionDesaprobada();
            msg.messageInfo("Se rechaza la consulta", null);
        }
        inicioAccionesConsulta();
        return true;
    }
    
    public boolean responderConsulta() {
        if (StringUtils.isBlank(expedienteConsultaResponde.getDetalle())) {
            msg.messageAlert("Debe responder la consulta", null);
            return false;
        }
        
        setExpedienteConsultaPadre(expedienteConsultaResponde);
        expedienteConsultaPadre.setCodigoUsuario(usuarioSession.getCodigo());
        expedienteConsultaPadre.setNombreUsuario(usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno());
        expedienteConsultaPadre.setEtapa(EtapaConsultaType.CONSULTA_ETAPA_RESPONDE.getKey());
        expedienteConsultaPadre.setFecha(new Date());
        /**ACTUALIZAR PADRE*/
        expedienteConsultaService.expedienteConsultaUpdate(expedienteConsultaPadre);
        /**GRABAR EL HIJO*/
        setExpedienteConsultaResponde(expedienteConsultaPadre);
        expedienteConsultaResponde.setIdPadre(expedienteConsultaPadre.getId());
        expedienteConsultaResponde.setEtapa(EtapaConsultaType.CONSULTA_ETAPA_RESPONDE.getKey());
        expedienteConsultaResponde.setId(null);
        expedienteConsultaService.expedienteConsultaInsertar(expedienteConsultaResponde);
        /***/
        enviarMensajeRespondeConsulta();
        inicioAccionesConsulta();
        return true;
    }
    
    public boolean respuestaAprobar() {
        if (StringUtils.isBlank(expedienteRespuestaAprueba.getDetalle())) {
            msg.messageAlert("Debe responder la consulta", null);
            return false;
        }
        
        setExpedienteConsultaPadre(expedienteRespuestaAprueba);
        expedienteConsultaPadre.setCodigoUsuario(usuarioSession.getCodigo());
        expedienteConsultaPadre.setNombreUsuario(usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno());
        expedienteConsultaPadre.setEtapa(EtapaConsultaType.RESPUESTA_ETAPA_APRUEBA.getKey());
        expedienteConsultaPadre.setFecha(new Date());
        /**ACTUALIZAR PADRE*/
        expedienteConsultaService.expedienteConsultaUpdate(expedienteConsultaPadre);
        /**GRABAR EL HIJO*/
        expedienteRespuestaAprueba = expedienteConsultaPadre;
        expedienteRespuestaAprueba.setIdPadre(expedienteConsultaPadre.getId());
        expedienteRespuestaAprueba.setEtapa(EtapaConsultaType.RESPUESTA_ETAPA_APRUEBA.getKey());
        expedienteRespuestaAprueba.setId(null);
        expedienteConsultaService.expedienteConsultaInsertar(expedienteRespuestaAprueba);
        /***/
        enviarMensajeAprobarRespuesta();
        inicioAccionesConsulta();
        return true;
    }
    
    private void enviarMensajeConsulta() {
        FacesContext context = FacesContext.getCurrentInstance();
        BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
        bandejaController.mensajeEnviaConsulta(expedienteConsultaEnvia);
    }

    private void enviarMensajeAprobacionConsulta() {
        FacesContext context = FacesContext.getCurrentInstance();
        BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
        bandejaController.mensajeEnviaConsultaAprobacion(expedienteConsultaAprueba);
    }

    private void enviarMensajeReasignacionConsulta() {
        FacesContext context = FacesContext.getCurrentInstance();
        BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
        bandejaController.mensajeEnviaConsultaReasignacion(expedienteConsultaReasigna);
    }

    private void enviarMensajeRespondeConsulta() {
        FacesContext context = FacesContext.getCurrentInstance();
        BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
        ExpedienteConsulta consultaReasigna = new ExpedienteConsulta();
        consultaReasigna.setIdPadre(expedienteConsultaResponde.getIdPadre());
        consultaReasigna.setEtapa(EtapaConsultaType.CONSULTA_ETAPA_REASIGNA.getKey());
        consultaReasigna = expedienteConsultaService.expedienteConsultaPorEtapa(consultaReasigna);
        expedienteConsultaResponde.setCodigoUsuarioRetorno(consultaReasigna.getCodigoUsuario());
        bandejaController.mensajeEnviaConsultaResponde(expedienteConsultaResponde);
    }

    private void enviarMensajeAprobarRespuesta() {
        FacesContext context = FacesContext.getCurrentInstance();
        BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
        ExpedienteConsulta consultaAprueba = new ExpedienteConsulta();
        consultaAprueba.setIdPadre(expedienteRespuestaAprueba.getIdPadre());
        consultaAprueba.setEtapa(EtapaConsultaType.CONSULTA_ETAPA_APRUEBA.getKey());
        consultaAprueba = expedienteConsultaService.expedienteConsultaPorEtapa(consultaAprueba);
        expedienteRespuestaAprueba.setCodigoUsuarioRetorno(consultaAprueba.getCodigoUsuario());
        bandejaController.mensajeEnviaAprobarRespuesta(expedienteRespuestaAprueba);
    }

    private void enviarMensajeDerivacion() {
        FacesContext context = FacesContext.getCurrentInstance();
        BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
        bandejaController.mensajeEnviaDerivacion(expedienteDerivacionEnvia);
    }

    private void enviarMensajeAprobacion() {
        FacesContext context = FacesContext.getCurrentInstance();
        BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
        bandejaController.mensajeEnviaAprobacion(expedienteDerivacionAprueba);
    }

    private void enviarMensajeDesaprobacion() {
        FacesContext context = FacesContext.getCurrentInstance();
        BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
        bandejaController.mensajeEnviaDesaprobacion(expedienteDerivacionAprueba, expediente);
    }

    private void enviarMensajeReasignacion() {
        FacesContext context = FacesContext.getCurrentInstance();
        BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
        bandejaController.mensajeEnviaReasignacion(expedienteDerivacionReasigna, expediente);
    }

    private void enviarMensajeReasignacionDesaprobada() {
        FacesContext context = FacesContext.getCurrentInstance();
        BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
        bandejaController.mensajeEnviaReasignacionDesaprobada(expedienteDerivacionReasigna, expedienteDerivacionAprueba.getCodigoUsuario(), expediente);
    }

    public String registarExpedienteGestion() {
        String ruta1 = uploadArchive(file1);
        String ruta2 = uploadArchive(file2);
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
        } else {
            expedienteGestion.setUsuarioModificacion(usuarioSession.getCodigo());
            expedienteGestion.setFechaModificacion(new Date());
            if (StringUtils.isNotBlank(expedienteGestion.getCodigoONP())) {
                expedienteGestion.setCodigoONP(null);
            }
            expedienteGestionService.expedienteGestionUpdate(expedienteGestion);
            msg.messageInfo("Se actualizo la gestión", null);
        }
        return cargarExpedienteGestionLista();
    }

    private String detalleUltimoEstado(String numeroExpediente) {
        String retornaDetalle = etapaEstadoService.etapaEstadoUltimoEstado(numeroExpediente);
        return retornaDetalle;
    }

    public String setearExpedienteGestion(ExpedienteGestion eg) {
        setExpedienteGestion(eg);
        expedienteBusquedaReplica = new Expediente();
        return "expedienteGestion";
    }

    public String verExpedienteGestion(ExpedienteGestion eg) {
        setExpedienteGestion(eg);
        return "expedienteGestionVer";
    }

    private void guardarGestionEtapa() {
        GestionEtapa ge = new GestionEtapa(expedienteGestion.getId(), expediente.getId(), etapaEstado.getVerEtapa(), expediente.getNumero());
        gestionEtapaService.gestionEtapaInsertar(ge);
    }

    public void listarExpedienteUsuarioPaginado(Integer pagina) {
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
            if(seguridadUtilController.tieneRecurso("REC_EXP008")){
                e.setIdOficinaDefensorial(usuarioSession.getCodigoOD());
                list = expedienteService.expedienteBuscarUsuarioODPaginado(e);
            }else{
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
    }

    public void listarExpedienteUsuarioPaginadoOrder(Integer pagina, int tipo) {
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
            if (pagina == 0) {
                ini = 1;
                fin = 10;
            }
            e.setIni(ini);
            e.setFin(fin);
            List<Expediente> list;
            FacesContext context = FacesContext.getCurrentInstance();
            SeguridadUtilController seguridadUtilController = (SeguridadUtilController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "seguridadUtilController");
            if(seguridadUtilController.tieneRecurso("REC_EXP008")){
                e.setIdOficinaDefensorial(usuarioSession.getCodigoOD());
                list = expedienteService.expedienteBuscarUsuarioODPaginado(e);
            }else{
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
    }

    public void listarExpedienteUsuarioPaginado(Integer pagina, Expediente e) {
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
    }

    public void listarExpedienteUsuarioPaginadoCompleto(Integer pagina, Expediente e) {
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
    }

    public void gestionesPorExpediente(String numeroExpediente) {
        listaGestionesParaReplica = null;
        listaGestionesParaReplica = expedienteGestionService.expedienteGestionListaXexpediente(numeroExpediente);
    }

    public void insertarReplicaGestion1() {
        int i = 0;
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
                log.error(ex);
            }
        }
        listarExpedienteUsuarioPaginadoCompleto(1, expedienteBusquedaReplica);
        msg.messageInfo("Se realizaron las replicas", null);
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
            log.error(e);
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
    }

    private void cargarGraficos001() {
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
    }

    private void cargarGraficos002() {
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
    }

    private void cargarGraficos003() {
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
    }

    public void setearPersonaSeleccionada(Persona perso) {
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
    }

    private void listarExpedientexPersona(long idPersona) {
        listaExpedienteXPersona = expedienteService.expedientexPersona(idPersona);
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
        persona = new Persona();
        entidad = new Entidad();
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
        listarNiveles();
        defineBotonRegistro();
        expedienteClasificacionBusqueda = new ExpedienteClasificacion();
        cargarFichaONP();
        verONP();
        if(StringUtils.isBlank(expediente.getNumero())){
            return "expedienteEdit";
        }
        if(StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.CONSULTA.getKey())){
            setearExpedienteTiempo();
            return "expedienteEdit";
        }else{
            if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.CALIFICACION_PETITORIO.getKey()) || Objects.equals(etapaEstado.getVerEtapa(), EtapaType.CALIFICACION_QUEJA.getKey())) {
                setearExpedienteTiempo();
                return "expedienteEdit";
            }
            cargarExpedienteGestionLista();
            setearExpedienteTiempo();
            verONP();
            return "expedienteGestionLista";
        }
    }
    
    private void verONP(){
        int i = 0;
        for(ExpedienteEntidad ee : entidadSeleccionadas){
            if(ee.getEntidad().getId() == 4455){
                i++;
            }
        }
        verSeccionONP = i > 0;
    }

    private void listarNiveles() {
        if (StringUtils.isNotBlank(expediente.getNumero())) {
            List<ExpedienteNivel> list = expedienteNivelService.expedienteNivelPorExpediente(expediente.getNumero());
            for (ExpedienteNivel en : list) {
                listaExpedienteClasificacion = expedienteClasificacionTipoService.clasificacioneExpedienteClasiTipo(en.getId());
                en.setListaClasificacionTipo(listaExpedienteClasificacion);
            }
            expediente.setListaExpedienteNivel(list);
        }
        limpiarNivelesAll();
    }

    public void setearExpediente(Expediente e) {
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
    }

    private void cargarPersonasEntidades() {
        personasSeleccionadas = expedientePersonaService.expedientePersonaBuscarXExpediente(expediente.getId());
        entidadSeleccionadas = expedienteEntidadService.expedienteEntidadBuscarXExpediente(expediente.getId());
    }

    public void cargarModalActor() {
        persona = new Persona();
        persona.setTipo("PER");
    }

    public boolean buscarPersonaGeneral(Long pagina) {
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
    }

    private void cargarEtiquetas() {
        if (!stringUtil.isBlank(expediente.getEtiqueta())) {
            String[] adArray = expediente.getEtiqueta().split(",");
            for (String adArray1 : adArray) {
                Parametro p = codigoParamentro(60, adArray1);
                listaEtiquetasSeleccionadas.add(p);
            }
        }
    }

    public String cargarBusquedaExpediente() {
        listaExpedientes = expedienteService.expedienteBuscar(expediente);
        return "expedienteBusqueda";
    }

    private void usuarioSession() {
        usuarioSession = new Usuario();
        FacesContext context = FacesContext.getCurrentInstance();
        LoginController loginController = (LoginController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "loginController");
        usuarioSession = loginController.getUsuarioSesion();
        usuarioSession.setNombreDepartamento(ubigeoService.departamentoOne(usuarioSession.getIdDepartamento()).getDescripcion());
    }

    public void entidadQuejada() {
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
    }

    public void addEntidadQuejada(Entidad entidad) {
        expedienteGestion.setIdEntidad(Integer.parseInt(entidad.getId().toString()));
        expedienteGestion.setNombreEntidad(entidad.getNombre());
    }

    public boolean cargarPopoverPersona(Long pagina) {
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
    }

    public boolean cargarPopoverEntidad(Long pagina) {
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
    }

    public boolean addPersona(Persona p) {
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
    }

    public boolean seteaPersonaExpediente(ExpedientePersona ep) {
        Persona p = personaService.personaBusquedaOne(ep.getPersona().getId());
        ep.setPersona(p);
        setExpedientepersonaModalEdit(ep);
        expedientepersonaModalEdit.setDireccion(expedientepersonaModalEdit.getPersona().getDireccion());
        expedientepersonaModalEdit.setIdDepartamento(expedientepersonaModalEdit.getPersona().getIdDepartamento());
        expedientepersonaModalEdit.setIdProvincia(expedientepersonaModalEdit.getPersona().getIdProvincia());
        expedientepersonaModalEdit.setIdDistrito(expedientepersonaModalEdit.getPersona().getIdDistrito());
        expedientepersonaModalEdit.setTelefono1(expedientepersonaModalEdit.getPersona().getTelefono1());
        expedientepersonaModalEdit.setTelefono2(expedientepersonaModalEdit.getPersona().getTelefono2());
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
    }

    public boolean editaPersonaExpediente() {
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
    }

    public void removePersona(ExpedientePersona ep) {
        personasSeleccionadas.remove(ep);
        int count = expedientePersonaService.expedientePersonaContar(ep);
        if (count > 0) {
            expedientePersonaService.expedientePersonaDelete(ep);
        }
        msg.messageInfo("Se elimino el registo de la persona", null);
    }

    public boolean addEntidad(Entidad e) {
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
    }

    public void removeEntidad(ExpedienteEntidad e) {
        entidadSeleccionadas.remove(e);
        int count = expedienteEntidadService.expedienteEntidadContar(e);
        if (count > 0) {
            expedienteEntidadService.expedienteEntidadDelete(e);
        }
        msg.messageInfo("Se elimino el registo de la Entidad", null);
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
            log.error(e);
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
            log.error(e);
        }
    }

    public void guardarBorrador() {
        expediente.setVersion(0);
        expediente.setEstado("A");
        expediente.setEtiqueta(encadenarEtiquetas());
        expediente.setUsuarioRegistro(usuarioSession.getCodigo());
        expediente.setNumero(" ");
        if (expediente.getId() == null) {
            expedienteService.expedienteInsertar(expediente);
            insertUpdateListasPersonaEntidad();
            msg.messageInfo("Se registro el borrador del Expediente", null);
        } else {
            expedienteService.expedienteUpdate(expediente);
            insertUpdateListasPersonaEntidad();
            msg.messageInfo("Se actualizo el borrador del Expediente", null);
        }
        inicializarEtapaEstado(0);
    }

    public boolean guardarVersion() {
        if(StringUtils.isBlank(expediente.getTipoClasificion())){
            msg.messageAlert("Debe seleccionar un tipo de expediente", null);
            return false;
        }
        if(!StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.CONSULTA.getKey())){
            if(expediente.getIndicadorOficio()){
            if(StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.CONSULTA.getKey())){
                msg.messageAlert("Un expediente de oficio no puede ser del tipo consulta", null);
                return false;
            }
        }
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
            verONP();
            msg.messageInfo("Se genero una nueva version del Expediente", null);
        } catch (Exception e) {
            log.error(e);
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
            verONP();
        } catch (Exception e) {
            log.error(e);
        }
    }

    public void guardarVersion3(String codigoUsuario) {
        try {
            Long idExpedienteOld = null;
            if (expediente.getId() != null) {
                idExpedienteOld = expediente.getId();
            }
            guardar1(codigoUsuario);
            guardarEtapaEstado(idExpedienteOld);
            inicializarEtapaEstado(1);
        } catch (Exception e) {
            log.error(e);
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
            String ruta = uploadArchive(file5);
            if (ruta != null) {
                expediente.setRuta(ruta);
            }
            cargarGruposVulnerables();
            expedienteService.expedienteInsertar(expediente);
            insertListasPersonaEntidad();
        } catch (Exception e) {
            log.error(e);
        }
    }

    private void generarCodigoExpediente() {
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
    }

    private void guardar1(String codigoUsuario) {
        try {
            expediente.setEtiqueta(encadenarEtiquetas());
            if (expediente.getId() == null || expediente.getVersion() == 0) {
                if (expediente.getId() != null) {
                    expediente.setEstado("I");
                    expedienteService.expedienteUpdate(expediente);
                }
                expediente.setUsuarioRegistro(codigoUsuario);
                expediente.setVersion(1);
                generarCodigoExpediente();
                expediente.setFechaRegistro(new Date());
            } else {
                expediente.setVersion(expediente.getVersion() + 1);
                expediente.setEstado("I");
                expedienteService.expedienteUpdate(expediente);
            }
            expediente.setUsuarioRegistro(codigoUsuario);
            expediente.setIndDerivado(1);
            expediente.setEstado("A");
            expedienteService.expedienteInsertar(expediente);
            insertListasPersonaEntidad();
        } catch (Exception e) {
            log.error(e);
        }
    }

    public String concluir() {
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

        
        if(!validaConcluision()){
            return null;
        }
        guardar();
        guardarEtapaEstadoConcluir(idExpedienteOld);
        inicializarEtapaEstado(1);
        insertarActualizarTiempos();
        verONP();
        /**
         * GENERAR NUEVO ESTADO
         */

        msg.messageInfo("Se concluyó la etapa", null);
        if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.CALIFICACION_PETITORIO.getKey()) || Objects.equals(etapaEstado.getVerEtapa(), EtapaType.CALIFICACION_QUEJA.getKey())) {
                setearExpedienteTiempo();
                return "expedienteEdit";
            }
        return "expedienteGestionLista";
    }
    
    private boolean validaConcluision(){
        if (StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.QUEJA.getKey()) || StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.PETITORIO.getKey())) {
            if(StringUtils.equals(expediente.getTipoIngreso(), "0")){
                    msg.messageAlert("Debe ingresar el canal de ingreso", null);
                    return false;
            
            }
            if(StringUtils.isBlank(expediente.getSumilla())){
                    msg.messageAlert("Debe ingresar la sumilla", null);
                    return false;
            
            }
            if(!expediente.getIndicadorOficio()){
                if(personasSeleccionadas.size() == 0){
                    msg.messageAlert("Debe ingresar el recurrente y el afectado", null);
                    return false;
            }else{
                int i = 0;
                int j = 0;
                for(ExpedientePersona ep : personasSeleccionadas ){
                    if(StringUtils.equals(ep.getTipo(), "01")){
                        i++;
                    }
                    if(StringUtils.equals(ep.getTipo(), "03")){
                        j++;
                    }
                }
                if(i == 0){
                    msg.messageAlert("Debe ingresar el recurrente", null);
                    return false;
                }
                if(j == 0){
                    msg.messageAlert("Debe ingresar el afectado", null);
                    return false;
                }
            }
            }else{
                if(personasSeleccionadas.size() == 0){
                    msg.messageAlert("Debe ingresar el afectado", null);
                    return false;
            }else{
                int j = 0;
                for(ExpedientePersona ep : personasSeleccionadas ){
                    if(StringUtils.equals(ep.getTipo(), "03")){
                        j++;
                    }
                }
                
                if(j == 0){
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
        }
        return true;
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
            log.error(e);
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
            log.error(e);
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
            log.error(e);
        }
    }

    public void cargarGruposVulnerables() {
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
                    if (year > 60) {
                        if (adultosMayores == 0) {
                            if (!sb.toString().contains("03")) {
                                sb.append(",03");
                            }
                            adultosMayores++;
                        }
                    }
                    if (year <= 12) {
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
        for (ExpedientePersona p : personasSeleccionadas) {
            p.setExpediente(expediente);
            expedientePersonaService.expedientePersonaInsertar(p);
        }
        for (ExpedienteEntidad e : entidadSeleccionadas) {
            e.setExpediente(expediente);
            expedienteEntidadService.expedienteEntidadInsertar(e);
        }
    }

    private void actualizarEtapaEstado(long idExpediente) {
        EtapaEstado ee = new EtapaEstado();
        ee.setIndicador("INA");
        ee.setIdExpediente(idExpediente);
        etapaEstadoService.etapaEstadoUpdate(ee);
    }

    private void actualizarEtapaEstadosSeguimiento(long idExpediente) {
        EtapaEstado ee = new EtapaEstado();
        ee.setIndicadorEtapa("");
        ee.setIdExpediente(idExpediente);
        etapaEstadoService.etapaEstadoUpdate(ee);
    }

    private void actualizarEtapaEstadosPersuacion(long idExpediente) {
        EtapaEstado ee = new EtapaEstado();
        ee.setIndicadorEtapa("");
        ee.setIdExpediente(idExpediente);
        etapaEstadoService.etapaEstadoUpdate(ee);
    }

    public void guardarPersona() {
        personaService.personaInsertar(persona);
        msg.messageInfo("Se registro la Persona", null);
    }

    public boolean guardarVincularPersona2() {
        persona.setUsuRegistro(usuarioSession.getCodigo());
        persona.setFechaRegistro(new Date());
        persona.setFechaModificacion(new Date());
        persona.setUsuModificacion(usuarioSession.getCodigo());
        setearPersonaSeleccionada(persona);
        msg.messageInfo("Se registro la Persona", null);
        return true;
    }

    public boolean guardarVincularPersona() {
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
    }

    public boolean guardarVincularListaPersona() {
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
    }

    public void guardarEntidad() {
        entidadService.entidadInsertar(entidad);
        msg.messageInfo("Se registro la Entidad", null);
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
            log.error(e.getMessage());
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
            log.error(e.getMessage());
        }
    }

    public boolean agregarEtiqueta() {
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
    }

    public void cargarModalReplica() {
        expedienteBusquedaReplica = new Expediente();
        listaExpedienteXUsuarioPaginadoReplica = null;
        listaGestionesParaReplica = null;
        idGestionReplica = null;
    }

    public void buscarModalReplica() {
        listarExpedienteUsuarioPaginadoCompleto(1, expedienteBusquedaReplica);
    }

    public void removeEtiqueta(Parametro param) {
        listaEtiquetasSeleccionadas.remove(param);
        msg.messageInfo("Se elimino la Etiqueta", null);
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
    }

    public void comboDistrito() {
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
    }

    public void comboProvinciaId(String idDepartamento) {
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
    }

    public void comboDistritoId(String idProvincia, String idDepartamento) {
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
    }

    private String uploadArchive(Part fil) {
        String nameArchive = getFilename(fil);
        String extencion = getFileExtension(getFilename(fil));
        if (StringUtils.isNoneBlank(nameArchive)) {
            String formato = RandomStringUtils.random(32, 0, 20, true, true, "qw32rfHIJk9iQ8Ud7h0X".toCharArray());
            String ruta = formato + extencion;
            File file = new File(ConstantesUtil.FILE_SYSTEM + ruta);
            try (InputStream input = fil.getInputStream()) {
                Files.copy(input, file.toPath());
            } catch (IOException ex) {
                log.error(ex.getCause());
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
            u.setRol(RolType.COMISIONADO_OD.getKey());
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
        System.out.println("getListaAdjuntiaDefensoriales");
        List<SelectItem> listaAdjuntiaDef = new ArrayList<>();
        try {
            List<OficinaDefensorial> list = oficinaDefensorialService.listaAdjuntiasDefensoriales();
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

    
}
