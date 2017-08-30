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
import gob.dp.sid.bandeja.entity.FiltroBusquedaMensaje;
import gob.dp.sid.bandeja.service.BandejaService;
import gob.dp.sid.comun.ConstantesUtil;
import gob.dp.sid.comun.controller.AbstractManagedBean;
import gob.dp.sid.comun.type.EtapaConsultaType;
import gob.dp.sid.comun.type.MensajeType;
import gob.dp.sid.comun.type.RolType;
import gob.dp.sid.registro.controller.RegistroController;
import gob.dp.sid.registro.entity.Expediente;
import gob.dp.sid.registro.entity.ExpedienteAmpliacion;
import gob.dp.sid.registro.entity.ExpedienteConsulta;
import gob.dp.sid.registro.entity.ExpedienteDerivacion;
import gob.dp.sid.registro.entity.ExpedienteSuspencion;
import gob.dp.sid.registro.entity.OficinaDefensorial;
import gob.dp.sid.registro.service.ExpedienteConsultaService;
import gob.dp.sid.registro.service.ExpedienteService;
import gob.dp.sid.registro.service.OficinaDefensorialService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.inject.Named;
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
public class BandejaController extends AbstractManagedBean implements Serializable {

    private static final Logger log = Logger.getLogger(BandejaController.class);

    private Usuario usuarioSession;

    private List<Bandeja> listaMensajes;
    
    private List<Bandeja> listaMensajesPendientes;
    
    private List<Bandeja> listaMensajesInternos;
    
    private List<Bandeja> listaMensajesAutomaticos;
    
    private List<Bandeja> listaMensajesProgramados;
    
    private Long nroMensajesInternos = 0L;
    
    private Long nroMensajesAutomaticos = 0L;
    
    private Long nroMensajesProgramados = 0L;

    private Bandeja mensajeBandeja;
    
    private Integer nroPaginaAutomatica = 1;
    
    private Integer indicadorBandeja = 0;
    
    @Autowired
    private BandejaService bandejaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private OficinaDefensorialService oficinaDefensorialService;
    
    @Autowired
    private ExpedienteConsultaService expedienteConsultaService;
    
    @Autowired
    private ExpedienteService expedienteService;

    public String cargarBandeja() {
        usuarioSession();
        listaMensajes = null;
        nroPaginaAutomatica = null;
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
        if(indicadorBandeja == 1)
            cargarTotalAutomaticos(nroPaginaAutomatica);
        if(indicadorBandeja == 2)
            cargarTotalProgramados(nroPaginaAutomatica);
        if(indicadorBandeja == 3)
            cargarTotalInternos(nroPaginaAutomatica);
            
    }
    
    public void cargarMensajesPendientes(){
        usuarioSession();
        cargarMensajesCabecera();
    }
    
    private void cargarMensajesCabecera(){
        nroMensajesInternos = bandejaService.bandejaContarUsuarioInternos(usuarioSession.getCodigo());
        nroMensajesProgramados = bandejaService.bandejaContarUsuarioProgramados(usuarioSession.getCodigo());
        nroMensajesAutomaticos = bandejaService.bandejaContarUsuarioAutomaticos(usuarioSession.getCodigo());
    }
    
    public String cargarTotalInternos(Integer pagina){
        indicadorBandeja = 3;
        FiltroBusquedaMensaje fbm = new FiltroBusquedaMensaje();
        int paginado = ConstantesUtil.PAGINADO_20;
        Integer ini;
        Integer fin;
        if(pagina > 0){
            ini = paginado * (pagina - 1) + 1;
            fin = paginado * pagina;
            fbm.setIni(ini);
            fbm.setFin(fin);
        }else{
            return null;
        }
        fbm.setCodigoUsuario(usuarioSession.getCodigo());
        try {
                        listaMensajesInternos = bandejaService.bandejaBuscarUsuarioInternos(fbm);
                        if (listaMensajesInternos.size() > 0) {
                            nroPaginaAutomatica = pagina;
                        } else {
                            if (fbm.getIni() == 1) {
                                listaMensajesInternos = null;
                                msg.messageAlert("No se han mensajes", null);
                            }else{
                                listaMensajesInternos = listaMensajes;
                            }
                        }
                    } catch (Exception e) {
                        log.error("ERROR : BandejaController.cargarTotalAutomaticos: " + e.getMessage());
                    }
        
        listaMensajes = listaMensajesInternos;
        return "bandeja";
    }

    public String cargarTotalAutomaticos(Integer pagina){
        indicadorBandeja = 1;
        FiltroBusquedaMensaje fbm = new FiltroBusquedaMensaje();
        int paginado = ConstantesUtil.PAGINADO_20;
        Integer ini;
        Integer fin;
        if(pagina > 0){
            ini = paginado * (pagina - 1) + 1;
            fin = paginado * pagina;
            fbm.setIni(ini);
            fbm.setFin(fin);
        }else{
            return null;
        }
        fbm.setCodigoUsuario(usuarioSession.getCodigo());
        try {
                        listaMensajesAutomaticos = bandejaService.bandejaBuscarUsuarioAutomaticos(fbm);
                        if (listaMensajesAutomaticos.size() > 0) {
                            nroPaginaAutomatica = pagina;
                        } else {
                            if (fbm.getIni() == 1) {
                                listaMensajesAutomaticos = null;
                                msg.messageAlert("No se han mensajes", null);
                            }else{
                                listaMensajesAutomaticos = listaMensajes;
                            }
                        }
                    } catch (Exception e) {
                        log.error("ERROR : BandejaController.cargarTotalAutomaticos: " + e.getMessage());
                    }
        
        listaMensajes = listaMensajesAutomaticos;
        return "bandeja";
    }
    
    public String cargarTotalProgramados(Integer pagina){
        indicadorBandeja = 2;
        FiltroBusquedaMensaje fbm = new FiltroBusquedaMensaje();
        int paginado = ConstantesUtil.PAGINADO_20;
        Integer ini;
        Integer fin;
        if(pagina > 0){
            ini = paginado * (pagina - 1) + 1;
            fin = paginado * pagina;
            fbm.setIni(ini);
            fbm.setFin(fin);
        }else{
            return null;
        }
        fbm.setCodigoUsuario(usuarioSession.getCodigo());
        try {
                        listaMensajesProgramados = bandejaService.bandejaBuscarUsuarioProgramados(fbm);
                        if (listaMensajesProgramados.size() > 0) {
                            nroPaginaAutomatica = pagina;
                        } else {
                            if (fbm.getIni() == 1) {
                                listaMensajesProgramados = null;
                                msg.messageAlert("No se han mensajes", null);
                            }else{
                                listaMensajesProgramados = listaMensajes;
                            }
                        }
                    } catch (Exception e) {
                        log.error("ERROR : BandejaController.cargarTotalAutomaticos: " + e.getMessage());
                    }
        
        listaMensajes = listaMensajesProgramados;
        return "bandeja";
    }
    
    public String verMensajeBandeja(Bandeja b) {
        setMensajeBandeja(b);
        b.setEstado("VIS");
        bandejaService.mensajeEstadoVisto(b.getId());
        //contarMensajesAutomaticosPendientes();
        return "verMensaje";
    }
    
    public void mensajeEnviaDerivacion(ExpedienteDerivacion ed){
        usuarioSession();
        mensajeBandeja = new Bandeja();
        OficinaDefensorial of = oficinaDefensorialService.obtenerOficinaDefensorial(ed.getIdOficinaDefensorial().longValue());
        mensajeBandeja.setTituloMensaje("Solicita: aprobación de "+MensajeType.MENSAJE_DERIVACION.getDetalle()+" exp: "+ed.getNumeroExpediente()+" a: "+of.getNombre());
        Usuario usu = new Usuario();
        usu.setRol(RolType.APROBADOR_OD.getKey());
        usu.setCodigoOD(usuarioSession.getCodigoOD());
        List<Usuario> listaDestinatarios = buscarDestinatarios(usu);
        guardarMensajeDerivacion(ed, listaDestinatarios, 0L);
    }
    
    public void mensajeEnviaAsignacion(Expediente ex){
        usuarioSession();
        mensajeBandeja = new Bandeja();
        mensajeBandeja.setTituloMensaje(MensajeType.MENSAJE_ASIGNACION.getDetalle()+" exp: "+ex.getNumero());
        guardarMensajeAsignacion(ex);
    }
    
    public void mensajeEnviaConsulta(ExpedienteConsulta ec){
        usuarioSession();
        mensajeBandeja = new Bandeja();
        OficinaDefensorial of = oficinaDefensorialService.obtenerOficinaDefensorial(ec.getIdAdjuntiaDefensorial().longValue());
        mensajeBandeja.setTituloMensaje(MensajeType.MENSAJE_CONSULTA.getDetalle()+" exp: "+ec.getNumeroExpediente()+" a: "+of.getNombre());
        Usuario usu = new Usuario();
        usu.setRol(RolType.APROBADOR_OD.getKey());
        usu.setCodigoOD(usuarioSession.getCodigoOD());
        List<Usuario> listaDestinatarios = buscarDestinatarios(usu);
        guardarMensajeConsulta(ec, listaDestinatarios, 0L);
    }
    
    public void mensajeEnviaConsultaAprobacion(ExpedienteConsulta ec){
        usuarioSession();
        mensajeBandeja = new Bandeja();
        OficinaDefensorial of = oficinaDefensorialService.obtenerOficinaDefensorial(ec.getIdAdjuntiaDefensorial().longValue());
        mensajeBandeja.setTituloMensaje(MensajeType.MENSAJE_CONSULTA.getDetalle()+" exp: "+ec.getNumeroExpediente()+" a: "+of.getNombre());
        Usuario usu = new Usuario();
        usu.setRol(RolType.APROBADOR_AD.getKey());
        usu.setCodigoOD(ec.getIdAdjuntiaDefensorial());
        List<Usuario> listaDestinatarios = buscarDestinatarios(usu);
        guardarMensajeConsulta(ec, listaDestinatarios, 0L);
    }
    
    public void mensajeEnviaConsultaDesaprobacion(ExpedienteConsulta ec){
        mensajeBandeja = new Bandeja();
        OficinaDefensorial of = oficinaDefensorialService.obtenerOficinaDefensorial(ec.getIdAdjuntiaDefensorial().longValue());
        mensajeBandeja.setTituloMensaje(MensajeType.MENSAJE_CONSULTA.getDetalle()+" exp: "+ec.getNumeroExpediente()+" a: "+of.getNombre());
        ExpedienteConsulta ec1 = expedienteConsultaService.expedienteConsultaPorEtapa(new ExpedienteConsulta(EtapaConsultaType.CONSULTA_ETAPA_ENVIA.getKey(), ec.getIdPadre()));
        Usuario u = new Usuario();
        u.setCodigo(ec1.getCodigoUsuario());
        List<Usuario> listaDestinatarios = new ArrayList<>();
        listaDestinatarios.add(u);
        guardarMensajeConsulta(ec, listaDestinatarios, 0L);
    }
    
    public void mensajeEnviaDesapruebaConsultaReasignacion(ExpedienteConsulta ec){
        
        mensajeBandeja = new Bandeja();
        mensajeBandeja.setTituloMensaje("Se desaprueba "+MensajeType.MENSAJE_CONSULTA.getDetalle()+" exp: "+ec.getNumeroExpediente());
        ExpedienteConsulta ec1 = expedienteConsultaService.expedienteConsultaPorEtapa(new ExpedienteConsulta(EtapaConsultaType.CONSULTA_ETAPA_ENVIA.getKey(), ec.getIdPadre()));
        List<Usuario> listaDestinatarios = new ArrayList<>();
        listaDestinatarios.add(new Usuario(ec1.getCodigoUsuario()));
        ExpedienteConsulta ec2 = expedienteConsultaService.expedienteConsultaPorEtapa(new ExpedienteConsulta(EtapaConsultaType.CONSULTA_ETAPA_APRUEBA.getKey(), ec.getIdPadre()));
        listaDestinatarios.add(new Usuario(ec2.getCodigoUsuario()));
        guardarMensajeConsulta(ec, listaDestinatarios, 0L);
    }
    
    public void mensajeEnviaConsultaReasignacion(ExpedienteConsulta ec){
        mensajeBandeja = new Bandeja();
        mensajeBandeja.setTituloMensaje(MensajeType.MENSAJE_CONSULTA.getDetalle()+" exp: "+ec.getNumeroExpediente());
        Usuario usuario = new Usuario();
        usuario.setCodigo(ec.getCodigoUsuarioReasignado());
        List<Usuario> listaDestinatarios = new ArrayList<>();
        listaDestinatarios.add(usuario);
        guardarMensajeConsulta(ec, listaDestinatarios, 0L);
    }
    
    public void mensajeEnviaConsultaResponde(ExpedienteConsulta ec){
        mensajeBandeja = new Bandeja();
        mensajeBandeja.setTituloMensaje(MensajeType.MENSAJE_CONSULTA.getDetalle()+" exp: "+ec.getNumeroExpediente());
        ExpedienteConsulta ec1 = expedienteConsultaService.expedienteConsultaPorEtapa(new ExpedienteConsulta(EtapaConsultaType.CONSULTA_ETAPA_REASIGNA.getKey(), ec.getIdPadre()));
        List<Usuario> listaDestinatarios = new ArrayList<>();
        listaDestinatarios.add(new Usuario(ec1.getCodigoUsuario()));
        guardarMensajeConsulta(ec, listaDestinatarios, 0L);
    }
    
    public void mensajeEnviaAprobarRespuesta(ExpedienteConsulta ec){
        mensajeBandeja = new Bandeja();
        mensajeBandeja.setTituloMensaje(MensajeType.MENSAJE_CONSULTA.getDetalle()+" exp: "+ec.getNumeroExpediente());
        ExpedienteConsulta ec1 = expedienteConsultaService.expedienteConsultaPorEtapa(new ExpedienteConsulta(EtapaConsultaType.CONSULTA_ETAPA_APRUEBA.getKey(), ec.getIdPadre()));
        List<Usuario> listaDestinatarios = new ArrayList<>();
        listaDestinatarios.add(new Usuario(ec1.getCodigoUsuario()));
        guardarMensajeConsulta(ec, listaDestinatarios, 0L);
    }
    
    public void mensajeEnviaDesaprobarRespuesta(ExpedienteConsulta ec){
        mensajeBandeja = new Bandeja();
        mensajeBandeja.setTituloMensaje("Se desaprueba la respuesta "+MensajeType.MENSAJE_CONSULTA.getDetalle()+" exp: "+ec.getNumeroExpediente());
        ExpedienteConsulta ec1 = expedienteConsultaService.expedienteConsultaPorEtapa(new ExpedienteConsulta(EtapaConsultaType.CONSULTA_ETAPA_ENVIA.getKey(), ec.getIdPadre()));
        List<Usuario> listaDestinatarios = new ArrayList<>();
        listaDestinatarios.add(new Usuario(ec1.getCodigoUsuario()));
        ExpedienteConsulta ec2 = expedienteConsultaService.expedienteConsultaPorEtapa(new ExpedienteConsulta(EtapaConsultaType.CONSULTA_ETAPA_APRUEBA.getKey(), ec.getIdPadre()));
        listaDestinatarios.add(new Usuario(ec2.getCodigoUsuario()));
        ExpedienteConsulta ec3 = expedienteConsultaService.expedienteConsultaPorEtapa(new ExpedienteConsulta(EtapaConsultaType.CONSULTA_ETAPA_RESPONDE.getKey(), ec.getIdPadre()));
        listaDestinatarios.add(new Usuario(ec3.getCodigoUsuario()));
        guardarMensajeConsulta(ec, listaDestinatarios, 0L);
    }
    
    public void mensajeEnviaAceptarRespuesta(ExpedienteConsulta ec){
        mensajeBandeja = new Bandeja();
        mensajeBandeja.setTituloMensaje("Se acepta la consulta "+MensajeType.MENSAJE_CONSULTA.getDetalle()+" exp: "+ec.getNumeroExpediente());
        ExpedienteConsulta ec1 = expedienteConsultaService.expedienteConsultaPorEtapa(new ExpedienteConsulta(EtapaConsultaType.CONSULTA_ETAPA_ENVIA.getKey(), ec.getIdPadre()));
        List<Usuario> listaDestinatarios = new ArrayList<>();
        listaDestinatarios.add(new Usuario(ec1.getCodigoUsuario()));
        guardarMensajeConsulta(ec, listaDestinatarios, 0L);
    }
    
    public void mensajeEnviaRechazarRespuesta(ExpedienteConsulta ec){
        mensajeBandeja = new Bandeja();
        mensajeBandeja.setTituloMensaje("Se rechaza la respuesta "+MensajeType.MENSAJE_CONSULTA.getDetalle()+" exp: "+ec.getNumeroExpediente());
        ExpedienteConsulta ec1 = expedienteConsultaService.expedienteConsultaPorEtapa(new ExpedienteConsulta(EtapaConsultaType.CONSULTA_ETAPA_ENVIA.getKey(), ec.getIdPadre()));
        List<Usuario> listaDestinatarios = new ArrayList<>();
        listaDestinatarios.add(new Usuario(ec1.getCodigoUsuario()));
        ExpedienteConsulta ec2 = expedienteConsultaService.expedienteConsultaPorEtapa(new ExpedienteConsulta(EtapaConsultaType.CONSULTA_ETAPA_REASIGNA.getKey(), ec.getIdPadre()));
        listaDestinatarios.add(new Usuario(ec2.getCodigoUsuario()));
        ExpedienteConsulta ec3 = expedienteConsultaService.expedienteConsultaPorEtapa(new ExpedienteConsulta(EtapaConsultaType.CONSULTA_ETAPA_RESPONDE.getKey(), ec.getIdPadre()));
        listaDestinatarios.add(new Usuario(ec3.getCodigoUsuario()));
        guardarMensajeConsulta(ec, listaDestinatarios, 0L);
    }
    
    public void mensajeEnviaAprobacion(ExpedienteDerivacion ed){
        mensajeBandeja = new Bandeja();
        OficinaDefensorial of = oficinaDefensorialService.obtenerOficinaDefensorial(ed.getIdOficinaDefensorial().longValue());
        mensajeBandeja.setTituloMensaje("Solicita: reasignación por "+MensajeType.MENSAJE_DERIVACION.getDetalle()+" exp: "+ed.getNumeroExpediente()+" a: "+of.getNombre());
        Usuario usu = new Usuario();
        usu.setCodigoOD(ed.getIdOficinaDefensorial());
        if(ed.getIdOficinaDefensorial() < 1000)
            usu.setRol(RolType.APROBADOR_OD.getKey());
        else
            usu.setRol(RolType.APROBADOR_AD.getKey());
        List<Usuario> listaDestinatarios = buscarDestinatarios(usu);
        guardarMensajeDerivacion(ed, listaDestinatarios, 0L);
    }
    
    public void mensajeEnviaDesaprobacion(ExpedienteDerivacion ed, Expediente e){
        mensajeBandeja = new Bandeja();
        mensajeBandeja.setTituloMensaje("No se aprueba la "+MensajeType.MENSAJE_DERIVACION.getDetalle()+" exp: "+ed.getNumeroExpediente());
        List<Usuario> listaDestinatarios = new ArrayList<>();
        Usuario usu = new Usuario();
        usu.setCodigo(e.getUsuarioRegistro());
        listaDestinatarios.add(usu);
        guardarMensajeDerivacion(ed, listaDestinatarios, e.getId());
    }
    
    public void mensajeEnviaReasignacion(ExpedienteDerivacion ed, Expediente e){
        mensajeBandeja = new Bandeja();
        mensajeBandeja.setTituloMensaje("Se deriva el exp: "+ed.getNumeroExpediente());
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
    
    public void mensajeEnviaSuspencionEnvia(ExpedienteSuspencion es){
        usuarioSession();
        mensajeBandeja = new Bandeja();
        OficinaDefensorial of = oficinaDefensorialService.obtenerOficinaDefensorial(es.getIdAdjuntiaDefensorial().longValue());
        mensajeBandeja.setTituloMensaje("Solicita: aprobación de "+MensajeType.MENSAJE_SUSPENCION.getDetalle()+" exp: "+es.getNumeroExpediente()+" a: "+of.getNombre());
        Usuario usu = usuarioSession;
        usu.setRol(RolType.APROBADOR_OD.getKey());
        List<Usuario> listaDestinatarios = buscarDestinatarios(usu);
        guardarMensajeSuspencion(es, listaDestinatarios, 0L);
    }
    
    public void mensajeEnviaSuspencionAprobacion(ExpedienteSuspencion es){
        mensajeBandeja = new Bandeja();
        OficinaDefensorial of = oficinaDefensorialService.obtenerOficinaDefensorial(es.getIdAdjuntiaDefensorial().longValue());
        mensajeBandeja.setTituloMensaje("Solicita aceptar solicitud por "+MensajeType.MENSAJE_SUSPENCION.getDetalle()+" del exp: "+es.getNumeroExpediente()+" a: "+of.getNombre());
        Usuario usu = new Usuario();
        usu.setRol(RolType.APROBADOR_AD.getKey());
        usu.setCodigoOD(es.getIdAdjuntiaDefensorial());
        List<Usuario> listaDestinatarios = buscarDestinatarios(usu);
        guardarMensajeSuspencion(es, listaDestinatarios, 0L);
    }
    
    public void mensajeEnviaSuspencionDesaprobacion(ExpedienteSuspencion envia, ExpedienteSuspencion aprueba, Expediente e){
        mensajeBandeja = new Bandeja();
        mensajeBandeja.setTituloMensaje("Se Desaprueba la solicitud de "+MensajeType.MENSAJE_SUSPENCION.getDetalle()+" del exp: "+aprueba.getNumeroExpediente());
        List<Usuario> listaDestinatarios = new ArrayList<>();
        Usuario u;
        u = new Usuario(envia.getCodigoUsuario());
        listaDestinatarios.add(u);
        guardarMensajeSuspencion(aprueba, listaDestinatarios, e.getId());
    }
    
    public void mensajeEnviaSuspencionAcepta(ExpedienteSuspencion acepta,ExpedienteSuspencion envia,ExpedienteSuspencion aprueba){
        mensajeBandeja = new Bandeja();
        mensajeBandeja.setTituloMensaje("Se acepta la solicitud de "+MensajeType.MENSAJE_SUSPENCION.getDetalle()+" del exp: "+acepta.getNumeroExpediente());
        List<Usuario> listaDestinatarios = new ArrayList<>();
        Usuario u;
        u = new Usuario(envia.getCodigoUsuario());
        listaDestinatarios.add(u);
        u = new Usuario(aprueba.getCodigoUsuario());
        listaDestinatarios.add(u);
        guardarMensajeSuspencion(acepta, listaDestinatarios, 0L);
    }
    
    public void mensajeEnviaSuspencionRechaza(ExpedienteSuspencion acepta,ExpedienteSuspencion envia,ExpedienteSuspencion aprueba, Expediente e){
        mensajeBandeja = new Bandeja();
        mensajeBandeja.setTituloMensaje("Se rechaza la solicitud de "+MensajeType.MENSAJE_SUSPENCION.getDetalle()+" exp: "+acepta.getNumeroExpediente());
        List<Usuario> listaDestinatarios = new ArrayList<>();
        Usuario u;
        u = new Usuario(envia.getCodigoUsuario());
        listaDestinatarios.add(u);
        u = new Usuario(aprueba.getCodigoUsuario());
        listaDestinatarios.add(u);
        guardarMensajeSuspencion(acepta, listaDestinatarios, e.getId());
    }
    
    public void mensajeEnviaAmpliacionEnvia(ExpedienteAmpliacion es){
        usuarioSession();
        mensajeBandeja = new Bandeja();
        OficinaDefensorial of = oficinaDefensorialService.obtenerOficinaDefensorial(es.getIdAdjuntiaDefensorial().longValue());
        mensajeBandeja.setTituloMensaje("Solicita: aprobación de "+MensajeType.MENSAJE_AMPLIACION.getDetalle()+" exp: "+es.getNumeroExpediente()+" a: "+of.getNombre());
        Usuario usu = new Usuario();
        usu.setRol(RolType.APROBADOR_OD.getKey());
        usu.setCodigoOD(usuarioSession.getCodigoOD());
        List<Usuario> listaDestinatarios = buscarDestinatarios(usu);
        guardarMensajeAmpliacion(es, listaDestinatarios, 0L);
    }
    
    public void mensajeEnviaAmpliacionAprobacion(ExpedienteAmpliacion es){
        mensajeBandeja = new Bandeja();
        OficinaDefensorial of = oficinaDefensorialService.obtenerOficinaDefensorial(es.getIdAdjuntiaDefensorial().longValue());
        mensajeBandeja.setTituloMensaje("Solicita aceptar solicitud por "+MensajeType.MENSAJE_AMPLIACION.getDetalle()+" del exp: "+es.getNumeroExpediente()+" a: "+of.getNombre());
        Usuario usu = new Usuario();
        usu.setRol(RolType.APROBADOR_AD.getKey());
        usu.setCodigoOD(es.getIdAdjuntiaDefensorial());
        List<Usuario> listaDestinatarios = buscarDestinatarios(usu);
        guardarMensajeAmpliacion(es, listaDestinatarios, 0L);
    }
    
    public void mensajeEnviaAmpliacionDesaprobacion(ExpedienteAmpliacion envia, ExpedienteAmpliacion aprueba, Expediente e){
        mensajeBandeja = new Bandeja();
        mensajeBandeja.setTituloMensaje("Se Desaprueba la solicitud de "+MensajeType.MENSAJE_AMPLIACION.getDetalle()+" del exp: "+aprueba.getNumeroExpediente());
        List<Usuario> listaDestinatarios = new ArrayList<>();
        Usuario u;
        u = new Usuario(envia.getCodigoUsuario());
        listaDestinatarios.add(u);
        guardarMensajeAmpliacion(aprueba, listaDestinatarios, e.getId());
    }
    
    public void mensajeEnviaAmpliacionAcepta(ExpedienteAmpliacion acepta,ExpedienteAmpliacion envia,ExpedienteAmpliacion aprueba){
        mensajeBandeja = new Bandeja();
        mensajeBandeja.setTituloMensaje("Se acepta la solicitud de "+MensajeType.MENSAJE_AMPLIACION.getDetalle()+" del exp: "+acepta.getNumeroExpediente());
        List<Usuario> listaDestinatarios = new ArrayList<>();
        Usuario u;
        u = new Usuario(envia.getCodigoUsuario());
        listaDestinatarios.add(u);
        u = new Usuario(aprueba.getCodigoUsuario());
        listaDestinatarios.add(u);
        guardarMensajeAmpliacion(acepta, listaDestinatarios, 0L);
    }
    
    public void mensajeEnviaAmpliacionRechaza(ExpedienteAmpliacion acepta,ExpedienteAmpliacion envia,ExpedienteAmpliacion aprueba, Expediente e){
        mensajeBandeja = new Bandeja();
        mensajeBandeja.setTituloMensaje("Se rechaza la solicitud de "+MensajeType.MENSAJE_AMPLIACION.getDetalle()+" exp: "+acepta.getNumeroExpediente());
        List<Usuario> listaDestinatarios = new ArrayList<>();
        Usuario u;
        u = new Usuario(envia.getCodigoUsuario());
        listaDestinatarios.add(u);
        u = new Usuario(aprueba.getCodigoUsuario());
        listaDestinatarios.add(u);
        guardarMensajeAmpliacion(acepta, listaDestinatarios, e.getId());
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
    
    private void guardarMensajeAsignacion(Expediente ex) {
        mensajeBandeja.setDestinatario(ex.getUsuarioAsignado());
        mensajeBandeja.setEstado("PEN");
        mensajeBandeja.setFechaEnvio(new Date());
        mensajeBandeja.setRemitente(usuarioSession.getCodigo());
        mensajeBandeja.setTipo(MensajeType.MENSAJE_ASIGNACION.getKey());
        mensajeBandeja.setTitulo(MensajeType.MENSAJE_ASIGNACION.getValue());
        mensajeBandeja.setNombreRemitente(usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno());
        mensajeBandeja.setDetalleTipo(MensajeType.MENSAJE_ASIGNACION.getDetalle());
        mensajeBandeja.setColorTipo(MensajeType.MENSAJE_ASIGNACION.getColor());
        mensajeBandeja.setMotivo("Se asigna el expediente: "+ex.getNumero());
        mensajeBandeja.setNumeroExpediente(ex.getNumero());
        mensajeBandeja.setTipoMensaje("INT");
        mensajeBandeja.setActivo("A");
        mensajeBandeja.setIdExpediente(ex.getId());
        bandejaService.bandejaInsertar(mensajeBandeja);
    }
    
    private void guardarMensajeSuspencion(ExpedienteSuspencion es, List<Usuario> usuarios, Long idExp) {
        for(Usuario u : usuarios){
            mensajeBandeja.setDestinatario(u.getCodigo());
            mensajeBandeja.setEstado("PEN");
            mensajeBandeja.setFechaEnvio(new Date());
            mensajeBandeja.setRemitente(usuarioSession.getCodigo());
            mensajeBandeja.setTipo(MensajeType.MENSAJE_SUSPENCION.getKey());
            mensajeBandeja.setTitulo(MensajeType.MENSAJE_SUSPENCION.getValue());
            mensajeBandeja.setCodigoTipo(es.getId());
            mensajeBandeja.setNombreRemitente(usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno());
            mensajeBandeja.setDetalleTipo(MensajeType.MENSAJE_SUSPENCION.getDetalle());
            mensajeBandeja.setColorTipo(MensajeType.MENSAJE_SUSPENCION.getColor());
            mensajeBandeja.setMotivo(es.getDetalle());
            mensajeBandeja.setNumeroExpediente(es.getNumeroExpediente());
            mensajeBandeja.setTipoMensaje("INT");
            mensajeBandeja.setActivo("A");
            if(idExp > 0)
                mensajeBandeja.setIdExpediente(idExp);
            else
                mensajeBandeja.setIdExpediente(es.getIdExpediente());
            mensajeBandeja.setIdAccion(es.getId());
            bandejaService.bandejaInsertar(mensajeBandeja);
        }
    }
    
    private void guardarMensajeAmpliacion(ExpedienteAmpliacion es, List<Usuario> usuarios, Long idExp) {
        for(Usuario u : usuarios){
            mensajeBandeja.setDestinatario(u.getCodigo());
            mensajeBandeja.setEstado("PEN");
            mensajeBandeja.setFechaEnvio(new Date());
            mensajeBandeja.setRemitente(usuarioSession.getCodigo());
            mensajeBandeja.setTipo(MensajeType.MENSAJE_AMPLIACION.getKey());
            mensajeBandeja.setTitulo(MensajeType.MENSAJE_AMPLIACION.getValue());
            mensajeBandeja.setCodigoTipo(es.getId());
            mensajeBandeja.setNombreRemitente(usuarioSession.getNombre() + " " + usuarioSession.getApellidoPaterno() + " " + usuarioSession.getApellidoMaterno());
            mensajeBandeja.setDetalleTipo(MensajeType.MENSAJE_AMPLIACION.getDetalle());
            mensajeBandeja.setColorTipo(MensajeType.MENSAJE_AMPLIACION.getColor());
            mensajeBandeja.setMotivo(es.getDetalle());
            mensajeBandeja.setNumeroExpediente(es.getNumeroExpediente());
            mensajeBandeja.setTipoMensaje("INT");
            mensajeBandeja.setActivo("A");
            if(idExp > 0)
                mensajeBandeja.setIdExpediente(idExp);
            else
                mensajeBandeja.setIdExpediente(es.getIdExpediente());
            mensajeBandeja.setIdAccion(es.getId());
            bandejaService.bandejaInsertar(mensajeBandeja);
        }
    }

    public void guardarMensajeBandejaPorReasignacion(ExpedienteDerivacion ed) {
        usuarioSession();
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
    
    public String cargarExpedientePorSuspencion(){
        return cargarSuspencionPorNumero();
    }
    
    public String cargarExpedientePorAmpliacion(){
        return cargarAmpliacionPorNumero();
    }
    
    private String cargarDerivacionPorId() {
        FacesContext context = FacesContext.getCurrentInstance();
        RegistroController registroController = (RegistroController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "registroController");
        Expediente e = expedienteService.expedienteBuscarPorId(mensajeBandeja.getIdExpediente());
        if(e == null){
            msg.messageAlert("El flujo de derivación ha sido concluido", null);
            return null;
        }
        String retorno = registroController.cargarExpedientePorId(mensajeBandeja.getIdExpediente());
        if(StringUtils.equals(registroController.getExpediente().getUsuarioRegistro(), usuarioSession.getCodigo())){
            return retorno;
        }
        return registroController.inicioAccionesDerivacion();
    }
    
    private String cargarMensajePorNumero() {
        FacesContext context = FacesContext.getCurrentInstance();
        RegistroController registroController = (RegistroController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "registroController");
        registroController.cargarExpedientePorNumero(mensajeBandeja.getNumeroExpediente());
        return registroController.inicioAccionesConsultaPublic();
    }
    
    private String cargarSuspencionPorNumero() {
        FacesContext context = FacesContext.getCurrentInstance();
        RegistroController registroController = (RegistroController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "registroController");
        registroController.cargarExpedientePorNumero(mensajeBandeja.getNumeroExpediente());
        return registroController.inicioAccionesSuspenderPublic();
    }
    
    private String cargarAmpliacionPorNumero() {
        FacesContext context = FacesContext.getCurrentInstance();
        RegistroController registroController = (RegistroController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "registroController");
        registroController.cargarExpedientePorNumero(mensajeBandeja.getNumeroExpediente());
        return registroController.inicioAccionesAmpliarPublic();
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

    public List<Bandeja> getListaMensajesProgramados() {
        return listaMensajesProgramados;
    }

    public void setListaMensajesProgramados(List<Bandeja> listaMensajesProgramados) {
        this.listaMensajesProgramados = listaMensajesProgramados;
    }

    public Integer getNroPaginaAutomatica() {
        return nroPaginaAutomatica;
    }

    public void setNroPaginaAutomatica(Integer nroPaginaAutomatica) {
        this.nroPaginaAutomatica = nroPaginaAutomatica;
    }

    public Integer getIndicadorBandeja() {
        return indicadorBandeja;
    }

    public void setIndicadorBandeja(Integer indicadorBandeja) {
        this.indicadorBandeja = indicadorBandeja;
    }

    public Long getNroMensajesInternos() {
        return nroMensajesInternos;
    }

    public void setNroMensajesInternos(Long nroMensajesInternos) {
        this.nroMensajesInternos = nroMensajesInternos;
    }

    public Long getNroMensajesAutomaticos() {
        return nroMensajesAutomaticos;
    }

    public void setNroMensajesAutomaticos(Long nroMensajesAutomaticos) {
        this.nroMensajesAutomaticos = nroMensajesAutomaticos;
    }

    public Long getNroMensajesProgramados() {
        return nroMensajesProgramados;
    }

    public void setNroMensajesProgramados(Long nroMensajesProgramados) {
        this.nroMensajesProgramados = nroMensajesProgramados;
    }

}
