/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.controller;

import gob.dp.sid.administracion.seguridad.controller.LoginController;
import gob.dp.sid.administracion.seguridad.controller.MenuController;
import gob.dp.sid.administracion.seguridad.entity.Usuario;
import gob.dp.sid.bandeja.controller.BandejaController;
import gob.dp.sid.comun.ConstantesUtil;
import gob.dp.sid.comun.SelectVO;
import gob.dp.sid.comun.controller.AbstractManagedBean;
import gob.dp.sid.comun.entity.Departamento;
import gob.dp.sid.comun.entity.Distrito;
import gob.dp.sid.comun.entity.FiltroParametro;
import gob.dp.sid.comun.entity.Parametro;
import gob.dp.sid.comun.entity.Provincia;
import gob.dp.sid.comun.service.CacheService;
import gob.dp.sid.comun.service.ParametroService;
import gob.dp.sid.comun.service.UbigeoService;
import gob.dp.sid.comun.type.AntesDespuesType;
import gob.dp.sid.comun.type.EstadoExpedienteType;
import gob.dp.sid.comun.type.EtapaType;
import gob.dp.sid.comun.type.ExpedienteType;
import gob.dp.sid.comun.type.RepeticionType;
import gob.dp.sid.comun.type.TiempoType;
import gob.dp.sid.registro.entity.Entidad;
import gob.dp.sid.registro.entity.EtapaEstado;
import gob.dp.sid.registro.entity.Expediente;
import gob.dp.sid.registro.entity.ExpedienteDerivacion;
import gob.dp.sid.registro.entity.ExpedienteEntidad;
import gob.dp.sid.registro.entity.ExpedienteGestion;
import gob.dp.sid.registro.entity.ExpedientePersona;
import gob.dp.sid.registro.entity.GestionEtapa;
import gob.dp.sid.registro.entity.OficinaDefensorial;
import gob.dp.sid.registro.entity.Persona;
import gob.dp.sid.registro.service.EntidadService;
import gob.dp.sid.registro.service.EtapaEstadoService;
import gob.dp.sid.registro.service.ExpedienteDerivacionService;
import gob.dp.sid.registro.service.ExpedienteEntidadService;
import gob.dp.sid.registro.service.ExpedienteGestionService;
import gob.dp.sid.registro.service.ExpedientePersonaService;
import gob.dp.sid.registro.service.ExpedienteService;
import gob.dp.sid.registro.service.GestionEtapaService;
import gob.dp.sid.registro.service.OficinaDefensorialService;
import gob.dp.sid.registro.service.PersonaService;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author carlos
 */
@Named
@Scope("session")
public class RegistroController extends AbstractManagedBean implements Serializable {

    private static final Logger log = Logger.getLogger(RegistroController.class);

    private Expediente expediente;

    private ExpedienteGestion expedienteGestion;

    private Persona persona;

    private Persona personaBusqueda;

    private Persona personaSeleccionada;

    private Entidad entidad;

    private List<Persona> personasPopover;

    private List<ExpedientePersona> personasSeleccionadas;

    private List<Entidad> entidadPopover;

    private List<ExpedienteEntidad> entidadSeleccionadas;

    private List<SelectItem> listaDepartamento;

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

    private List<SelectItem> listaTiempo;

    private List<SelectItem> listaAntesDespues;

    private List<SelectItem> listaRepeticion;

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
    
    private List<SelectItem> listaOficinaDefensoriales;
    
    private ExpedienteDerivacion expedienteDerivacion;

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
    
    public String cargarNuevoExpediente() {
        expediente = new Expediente();
        etapaEstado = new EtapaEstado();
        cadenaPersonaPopover = "";
        personasSeleccionadas = new ArrayList<>();
        cadenaEntidadPopover = "";
        entidadSeleccionadas = new ArrayList<>();
        cadenaEtiquetaAutocomplete = expedienteService.etiquetaBuscarAutocomplete();
        listaEtiquetasSeleccionadas = new ArrayList<>();
        return "expedienteNuevo";
    }

    public String iniciarExpedienteNuevo() {
        persona = new Persona();
        entidad = new Entidad();
        cargarNuevoExpediente();
        ExpedientePersona ep = new ExpedientePersona();
        expediente.setTipoClasificion(personaSeleccionada.getTipoExpediente());
        ep.setPersona(personaSeleccionada);
        personasSeleccionadas.add(ep);
        inicializarEtapaEstado(0);
        return "expedienteNuevo";
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
    
    public String cargarExpedientePorId(Long idExpediente){
        expediente = expedienteService.expedienteBuscarPorId(idExpediente);
        return cargarExpedienteEdit(expediente);
    }

    public String cargarExpedienteGestion() {
        expedienteGestion = new ExpedienteGestion();
        //listaExpedienteGestion = expedienteGestionService.expedienteGestionLista(expediente.getId());
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
        listarExpedienteUsuarioPaginado(1);
        cargarGraficos001();
        cargarGraficos002();
        cargarGraficos003();
    }
    
    public String inicioAcciones(){
        return "expedienteAcciones";
    }
    
    public String inicioAccionesDerivacion(){
        expedienteDerivacion = new ExpedienteDerivacion();
        return "expedienteAccionesDerivacion";
    }
    
    public void enviarDerivacion(){
        expedienteDerivacion.setIdExpediente(expediente.getId());
        expedienteDerivacion.setNumeroExpediente(expediente.getNumero());
        expedienteDerivacion.setEstado("ACT");
        expedienteDerivacionService.expedienteDerivacionInsertar(expedienteDerivacion);
        enviarMensajeDerivacion();
        msg.messageInfo("Se envio la Derivación", null);
    }
    
    private void enviarMensajeDerivacion(){
        FacesContext context = FacesContext.getCurrentInstance();
        BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
        bandejaController.guardarMensajeBandejaPorDerivacion(expedienteDerivacion);
    }

    public String registarExpedienteGestion() {
        if (StringUtils.isBlank(expedienteGestion.getCodigoGestion())) {
            DateFormat format = new SimpleDateFormat("yyMMddHHmmss");
            String formato = format.format(new Date());
            expedienteGestion.setUsuarioRegistro(usuarioSession.getCodigo());
            expedienteGestion.setFechaRegistro(new Date());
            expedienteGestion.setCodigoGestion("GES" + formato);
            expedienteGestionService.expedienteGestionInsertar(expedienteGestion);
            guardarGestionEtapa();
            msg.messageInfo("Se registro una nueva gestión", null);
        } else {
            expedienteGestion.setUsuarioModificacion(usuarioSession.getCodigo());
            expedienteGestion.setFechaModificacion(new Date());
            expedienteGestionService.expedienteGestionUpdate(expedienteGestion);
            msg.messageInfo("Se actualizo la gestión", null);
        }
        return cargarExpedienteGestionLista();
    }
    
    private String detalleUltimoEstado(String numeroExpediente){
        String retornaDetalle = etapaEstadoService.etapaEstadoUltimoEstado(numeroExpediente);
        return retornaDetalle;
    }

    public String setearExpedienteGestion(ExpedienteGestion eg) {
        setExpedienteGestion(eg);
        return "expedienteGestion";
    }

    public String verExpedienteGestion(ExpedienteGestion eg) {
        setExpedienteGestion(eg);
        return "expedienteGestionVer";
    }

    private void guardarGestionEtapa() {
        GestionEtapa ge = new GestionEtapa(expedienteGestion.getId(), expediente.getId(), etapaEstado.getIdEtapa(), expediente.getNumero());
        gestionEtapaService.gestionEtapaInsertar(ge);
    }

    public void listarExpedienteUsuarioPaginado(Integer pagina) {
        Expediente e = new Expediente();
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
                    if(StringUtils.isNoneBlank(e1.getNumero()))
                       e1.setEstadoDetalle(detalleUltimoEstado(e1.getNumero()));
                }
                listaExpedienteXUsuarioPaginado = list;
                nroPagina = pagina;
            }
        }
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
        sb.append("{x: 'Queja', y: " + queja + "},");
        sb.append("{x: 'Petitorio', y: " + petitorio + "},");
        sb.append("{x: 'Consulta', y: " + consulta + "}");
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
            sb.append("{'period': '" + e1.getMes() + "', 'queja': " + e1.getCount() + "},");
        }
        for (Expediente e1 : list002) {
            sb.append("{'period': '" + e1.getMes() + "', 'consulta': " + e1.getCount() + "},");
        }
        for (Expediente e1 : list003) {
            sb.append("{'period': '" + e1.getMes() + "', 'petitorio': " + e1.getCount() + "},");
        }
        grafico003 = sb.toString();
    }

    public void setearPersonaSeleccionada(Persona perso) {
        if (perso.getIdDepartamento() != null && perso.getIdDepartamento() != 0) {
            perso.setNombreDepartamento(ubigeoService.departamentoOne(perso.getIdDepartamento()).getDescripcion());
        }
        if (perso.getIdProvincia() != null && perso.getIdProvincia() != 0) {
            perso.setNombreProvincia(ubigeoService.provinciaOne(perso.getIdProvincia()).getDescripcion());
        }
        if (perso.getIdDistrito() != null && perso.getIdDistrito() != 0) {
            perso.setNombreDistrito(ubigeoService.distritoOne(perso.getIdDistrito()).getDescripcion());
        }
        listarExpedientexPersona(perso.getId());
        setPersonaSeleccionada(perso);
        indSeleccion = false;
    }

    public void pintar() {
        System.out.println("pinta");
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
        //cargarSubTemas();
        cargarEtiquetas();
        cargarPersonasEntidades();
        if (expediente.getVersion() == 0) {
            inicializarEtapaEstado(0);
        } else {
            inicializarEtapaEstado(1);
        }
        return "expedienteEdit";
    }

    public void setearExpediente(Expediente e) {
        FacesContext context = FacesContext.getCurrentInstance();
        MenuController menuController = (MenuController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "menuController");
        menuController.cargarPagina(1);
        listaEtiquetasSeleccionadas = new ArrayList<>();
        setExpediente(e);
        //cargarSubTemas();
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
        System.out.println("care");
    }

    public boolean buscarPersonaGeneral(Long pagina) {
        int i = 0;
        if (stringUtil.isBlank(personaBusqueda.getDni())) {
            personaBusqueda.setDni(null);
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
                    }
                } catch (Exception e) {
                    log.error("ERROR : BusquedaUsuarioController.listarPaginado: " + e.getMessage());
                }
            }

            //listaPersonaGeneral = personaService.personaBusarGeneral(personaBusqueda);
        }
        if (listaPersonaGeneral.isEmpty()) {
            msg.messageAlert("No se han encontrado Personas", null);
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
                }
            } catch (Exception e) {
                log.error("ERROR : BusquedaUsuarioController.listarPaginado: " + e.getMessage());
            }
        }

            //listaPersonaGeneral = personaService.personaBusarGeneral(personaBusqueda);
        if (personasPopover.isEmpty()) {
            msg.messageAlert("No se han encontrado Personas", null);
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
                }
            } catch (Exception e) {
                log.error("ERROR : BusquedaUsuarioController.listarPaginado: " + e.getMessage());
            }
        }

            //listaPersonaGeneral = personaService.personaBusarGeneral(personaBusqueda);
        if (entidadPopover.isEmpty()) {
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
        personasSeleccionadas.add(ep);
        cadenaPersonaPopover = null;
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
        if (tip == 0) {
            etapaEstado = new EtapaEstado();
            etapaEstado.setVerEtapa(0);
        } else {
            etapaEstado = etapaEstadoService.etapaEstadoVigente(expediente.getId());
            if (etapaEstado == null) {
                etapaEstado = etapaEstadoService.etapaEstadoInicial(expediente.getId());
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
    }

    public void guardarBorrador() {
        expediente.setVersion(0);
        expediente.setEstado("A");
        expediente.setEtiqueta(encadenarEtiquetas());
        expediente.setUsuarioRegistro(usuarioSession.getCodigo());
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

    public void guardarVersion() {
        Long idExpedienteOld = null;
        if (expediente.getId() != null) {
            idExpedienteOld = expediente.getId();
        }
        guardar();
        guardarEtapaEstado(idExpedienteOld);
        inicializarEtapaEstado(1);
        msg.messageInfo("Se genero una nueva version del Expediente", null);
    }
    
    public void guardarVersion2() {
        Long idExpedienteOld = null;
        if (expediente.getId() != null) {
            idExpedienteOld = expediente.getId();
        }
        guardar();
        guardarEtapaEstado(idExpedienteOld);
        inicializarEtapaEstado(1);
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
                DateFormat format = new SimpleDateFormat("yyMMddHHmmss");
                String formato = format.format(new Date());
                expediente.setNumero("CP" + formato);
                expediente.setFechaRegistro(new Date());
            } else {
                expediente.setVersion(expediente.getVersion() + 1);
                expediente.setFechaModificacion(new Date());
                expediente.setEstado("I");
                expediente.setUsuarioModificacion(usuarioSession.getCodigo());
                expedienteService.expedienteUpdate(expediente);
            }
            expediente.setEstado("A");
            expedienteService.expedienteInsertar(expediente);
            insertListasPersonaEntidad();
        } catch (Exception e) {
            log.error(e);
        }
    }

    private void generarFlujo() {

    }

    public boolean concluir() {
        Long idExpedienteOld = expediente.getId();
        if (idExpedienteOld == null) {
            return false;
        }
        /**
         * QUEJA
         */
        if (StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.QUEJA.getKey())) {
            if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.CALIFICACION_QUEJA.getKey())) {
                if (expediente.getEstadoCalificacion() == null) {
                    msg.messageAlert("El expediente no cuenta con ningun estado, por favor seleccione si esta admitido o no admitido", null);
                    return false;
                }
            }
            if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.INVESTIGACION_QUEJA.getKey())) {
                if (expediente.getEstadoInvestigacion() == null) {
                    msg.messageAlert("El expediente no cuenta con ningun estado, por favor seleccione si esta fundado o infundado", null);
                    return false;
                }
            }
            if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.PERSUACION_QUEJA.getKey())) {
                if (expediente.getEstadoPersuacion() == null) {
                    msg.messageAlert("El expediente no cuenta con ningun estado, por favor seleccione si esta acogido o no acogido", null);
                    return false;
                }
            }
            if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.SEGUIMIENTO_QUEJA.getKey())) {
                if (expediente.getEstadoSeguimiento() == null) {
                    msg.messageAlert("El expediente no cuenta con ningun estado, por favor seleccione si esta acogido o no acogido", null);
                    return false;
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
                    return false;
                }
            }
            if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.GESTION_PETITORIO.getKey())) {
                if (expediente.getEstadoGestion() == null) {
                    msg.messageAlert("El expediente no cuenta con ningun estado, por favor seleccione si esta solucionado o no solucionado", null);
                    return false;
                }
            }
            if (Objects.equals(etapaEstado.getVerEtapa(), EtapaType.PERSUASION_PETITORIO.getKey())) {
                if (expediente.getEstadoPersuacion() == null) {
                    msg.messageAlert("El expediente no cuenta con ningun estado, por favor seleccione si devienen en solucionados, por negativa expresa o falta de respuesta", null);
                    return false;
                }
            }
        }

        guardar();
        guardarEtapaEstadoConcluir(idExpedienteOld);
        inicializarEtapaEstado(1);
        guardarVersion2();
        /**GENERAR NUEVO ESTADO*/
        
        msg.messageInfo("Se concluyó la etapa", null);
        return true;
    }

    private void guardarEtapaEstado(Long idExpedienteOld) {
        EtapaEstado etapaEstado1 = new EtapaEstado();
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
    }

    private void guardarEtapaEstadoConcluir(Long idExpedienteOld) {
        EtapaEstado etapaEstado1 = new EtapaEstado();
        /**QUEJA*/
        if(StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.QUEJA.getKey())){
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
        
        /**PETITORIO*/
        if(StringUtils.equals(expediente.getTipoClasificion(), ExpedienteType.PETITORIO.getKey())){
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
                    /*if (expediente.getEstadoGestion() == EstadoExpedienteType.GESTION_CONCLUIDO_PETITORIO.getKey()) {
                        expediente.setGeneral("C");
                        expedienteService.expedienteConcluir(expediente.getId());
                    }*/
                    
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
        
        etapaEstado1.setIdExpediente(expediente.getId());
        etapaEstado1.setNumeroExpediente(expediente.getNumero());
        etapaEstado1.setIndicador("ACT");
        etapaEstadoService.etapaEstadoInsertar(etapaEstado1);
    }

    private void insertUpdateListasPersonaEntidad() {
        for (ExpedientePersona p : personasSeleccionadas) {
            p.setExpediente(expediente);
            insertUpdateExpedientePersona(p);
        }

        for (ExpedienteEntidad e : entidadSeleccionadas) {
            e.setExpediente(expediente);
            insertUpdateExpedienteEntidad(e);
        }
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

    public boolean guardarVincularPersona() {
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
        msg.messageInfo("Se registro la Persona", null);
        return true;
    }

    public boolean guardarVincularListaPersona() {
        if (StringUtils.isBlank(persona.getDni())) {
            msg.messageAlert("Debe ingresar un DNI", null);
            return false;
        } else {
            if (persona.getDni().length() != 8) {
                msg.messageAlert("El número de DNI debe contar con 8 caracteres", null);
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
        msg.messageInfo("Se registro la Persona", null);
        return true;
    }

    public void guardarEntidad() {
        entidadService.entidadInsertar(entidad);
        msg.messageInfo("Se registro la Entidad", null);
    }

    public void insertUpdateExpedientePersona(ExpedientePersona ep) {
        try {
            if (ep.getExpediente().getId() != null && ep.getPersona().getId() != null) {
                int contador = expedientePersonaService.expedientePersonaContar(ep);
                if (contador == 0) {
                    expedientePersonaService.expedientePersonaInsertar(ep);
                } else {
                    expedientePersonaService.expedientePersonaUpdate(ep);
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

    public void removeEtiqueta(Parametro param) {
        listaEtiquetasSeleccionadas.remove(param);
        msg.messageInfo("Se elimino la Etiqueta", null);
    }

    public void cargarSubTemas() {
        if (!expediente.getTipoTema().trim().equals("0")) {
            listaSubTemas = cacheService.buscarExpedienteSubTema(codigoParamentro(30));
        }
    }

    private int codigoParamentro(int codigo) {
        FiltroParametro filtro = new FiltroParametro();
        filtro.setCodigoPadreParametro(codigo);
        filtro.setValorParametro(expediente.getTipoTema());
        Parametro p = parametroService.consultarParametroValor(filtro);
        return p.getCodigoParametro();
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
        int id = persona.getIdDepartamento();
        if (id == 0) {
            listaProvincia.clear();
        } else {
            List<Provincia> list = ubigeoService.provinciaLista(id);
            if (list.size() > 0) {
                for (Provincia provincia : list) {
                    listaProvincia.add(new SelectItem(provincia.getId(), provincia.getDescripcion()));
                }
            }
            //Departamento dep = ubigeoService.departamentoOne(id);
        }
    }

    public void comboDistrito() {
        listaDistrito = new ArrayList<>();
        int id = persona.getIdProvincia();
        if (id == 0) {
            listaDistrito.clear();
        } else {
            List<Distrito> list = ubigeoService.distritoLista(id);
            if (list.size() > 0) {
                for (Distrito distrito : list) {
                    listaDistrito.add(new SelectItem(distrito.getId(), distrito.getDescripcion()));
                }
            }
            //Provincia prov = ubigeoService.provinciaOne(id);
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

    public List<SelectItem> getListaDepartamento() {
        listaDepartamento = new ArrayList<>();
        List<Departamento> list = ubigeoService.departamentoLista();
        if (list.size() > 0) {
            for (Departamento departamento : list) {
                listaDepartamento.add(new SelectItem(departamento.getId(), departamento.getDescripcion()));
            }
        }
        return listaDepartamento;
    }

    public void setListaDepartamento(List<SelectItem> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
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

    public List<SelectItem> getListaTiempo() {
        try {
            listaTiempo = new ArrayList<>();
            List<SelectVO> tiposTiempo = TiempoType.getList();
            if (tiposTiempo != null) {
                listaTiempo.add(new SelectItem("0", "[Seleccione]"));
                for (SelectVO tipoTiempo : tiposTiempo) {
                    listaTiempo.add(new SelectItem(tipoTiempo.getId(),
                            tipoTiempo.getValue()));
                }
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return listaTiempo;
    }

    public void setListaTiempo(List<SelectItem> listaTiempo) {
        this.listaTiempo = listaTiempo;
    }

    public List<SelectItem> getListaAntesDespues() {
        try {
            listaAntesDespues = new ArrayList<>();
            List<SelectVO> tiposAntesDespues = AntesDespuesType.getList();
            if (tiposAntesDespues != null) {
                listaAntesDespues.add(new SelectItem("0", "[Seleccione]"));
                for (SelectVO tipoAntesDespues : tiposAntesDespues) {
                    listaAntesDespues.add(new SelectItem(tipoAntesDespues.getId(),
                            tipoAntesDespues.getValue()));
                }
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return listaAntesDespues;
    }

    public void setListaAntesDespues(List<SelectItem> listaAntesDespues) {
        this.listaAntesDespues = listaAntesDespues;
    }

    public List<SelectItem> getListaRepeticion() {
        try {
            listaRepeticion = new ArrayList<>();
            List<SelectVO> tiposRepeticion = RepeticionType.getList();
            if (tiposRepeticion != null) {
                listaRepeticion.add(new SelectItem("0", "[Seleccione]"));
                for (SelectVO tipoRepeticion : tiposRepeticion) {
                    listaRepeticion.add(new SelectItem(tipoRepeticion.getId(),
                            tipoRepeticion.getValue()));
                }
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return listaRepeticion;
    }

    public void setListaRepeticion(List<SelectItem> listaRepeticion) {
        this.listaRepeticion = listaRepeticion;
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

    public List<SelectItem> getListaOficinaDefensoriales() {
        List<SelectItem> listaOficinaDef = new ArrayList<>();
        try {
            List<OficinaDefensorial> list = oficinaDefensorialService.listaOficinasDefensoriales();
                for (OficinaDefensorial od : list) {
                    listaOficinaDef.add(new SelectItem(od.getId(), od.getNombre()));
                }
            listaOficinaDefensoriales = listaOficinaDef;
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return listaOficinaDefensoriales;
    }

    public void setListaOficinaDefensoriales(List<SelectItem> listaOficinaDefensoriales) {
        this.listaOficinaDefensoriales = listaOficinaDefensoriales;
    }

    public ExpedienteDerivacion getExpedienteDerivacion() {
        return expedienteDerivacion;
    }

    public void setExpedienteDerivacion(ExpedienteDerivacion expedienteDerivacion) {
        this.expedienteDerivacion = expedienteDerivacion;
    }

}
