/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.comun.controller;

import gob.dp.sid.administracion.seguridad.controller.LoginController;
import gob.dp.sid.administracion.seguridad.entity.Usuario;
import gob.dp.sid.comun.ConstantesUtil;
import gob.dp.sid.comun.type.EtapaType;
import gob.dp.sid.registro.entity.Expediente;
import gob.dp.sid.registro.service.EtapaEstadoService;
import gob.dp.sid.registro.service.ExpedienteService;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author carlos
 */
@Named
@Scope("session")
public class BusquedaController extends AbstractManagedBean implements Serializable{
    
    private List<Expediente> listadoGeneral;
    
    private Expediente expediente;
    
    private Usuario usuarioSession;
    
    private Integer nroPagina = 1;
    
    @Autowired
    private ExpedienteService expedienteService;
    
    @Autowired
    private EtapaEstadoService etapaEstadoService;
    

    public void inicioBusqueda(){
        expediente = new Expediente();
        usuarioSession();
    }
    
    public String buscar(){
        expediente.setCodigoOD(usuarioSession.getCodigoOD());
        expediente.setUsuarioRegistro(usuarioSession.getCodigo());
        //listadoGeneral = expedienteService.expedienteBuscarUsuarioPaginadoGeneral(expediente);
        return "busquedaGeneral";
    }
    
    private void usuarioSession() {
        usuarioSession = new Usuario();
        FacesContext context = FacesContext.getCurrentInstance();
        LoginController loginController = (LoginController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "loginController");
        usuarioSession = loginController.getUsuarioSesion();
    }
    
    public String listarExpedienteUsuarioPaginado(Integer pagina) {
        if (pagina > 0) {
            int paginado = ConstantesUtil.PAGINADO_20;
            Integer ini = paginado * (pagina - 1) + 1;
            Integer fin = paginado * pagina;
            if (pagina == 0) {
                ini = 1;
                fin = 20;
            }
            expediente.setUsuarioRegistro(usuarioSession.getCodigo());
            expediente.setIni(ini);
            expediente.setFin(fin);

            List<Expediente> list = expedienteService.expedienteBuscarUsuarioPaginado(expediente);
            
            if (list.size() > 0) {
                for (Expediente e1 : list) {
                    e1.setEtapaDetalle(devolverEtapa(e1));
                    if(StringUtils.isNoneBlank(e1.getNumero()))
                       e1.setEstadoDetalle(detalleUltimoEstado(e1.getNumero()));
                }
                listadoGeneral = list;
                nroPagina = pagina;
            }else{
                if(expediente.getIni() == 1){
                    listadoGeneral = null;
                }
            }
        }
        return "busquedaGeneral";
    }
    
    public String listarExpedientesPaginado(Integer pagina) {
        if(StringUtils.isNotBlank(expediente.getODBusqueda()) || StringUtils.isNotBlank(expediente.getAnhoBusqueda()) || StringUtils.isNotBlank(expediente.getObservacion())){
        if (pagina > 0) {
            int paginado = ConstantesUtil.PAGINADO_20;
            Integer ini = paginado * (pagina - 1) + 1;
            Integer fin = paginado * pagina;
            if (pagina == 0) {
                ini = 1;
                fin = 20;
            }
            expediente.setUsuarioRegistro(null);
            expediente.setIni(ini);
            expediente.setFin(fin);
            if(StringUtils.isBlank(expediente.getODBusqueda())){
                expediente.setODBusqueda(null);
            }
            if(StringUtils.isBlank(expediente.getAnhoBusqueda())){
                expediente.setAnhoBusqueda(null);
            }
            if(StringUtils.isNotBlank(expediente.getObservacion())){
                if(StringUtils.isNumeric(expediente.getObservacion())){
                    expediente.setFiltroNroExpediente(expediente.getObservacion().trim());
                    expediente.setFiltroSumilla(null);
                }else{
                    expediente.setFiltroSumilla(expediente.getObservacion());
                    expediente.setFiltroNroExpediente(null);
                }    
            }
            List<Expediente> list = expedienteService.expedienteBuscarUsuarioPaginado(expediente);
            
            if (list.size() > 0) {
                for (Expediente e1 : list) {
                    e1.setEtapaDetalle(devolverEtapa(e1));
                    if(StringUtils.isNoneBlank(e1.getNumero()))
                       e1.setEstadoDetalle(detalleUltimoEstado(e1.getNumero()));
                }
                listadoGeneral = list;
                nroPagina = pagina;
            }else{
                if(expediente.getIni() == 1){
                    listadoGeneral = null;
                }
            }
        }
        return "busquedaGeneral";
        }else{
            msg.messageAlert("Debe ingresar almenos un criterio de búsqueda",null);
        }
        return null;
    }
    
    private String detalleUltimoEstado(String numeroExpediente){
        String retornaDetalle = etapaEstadoService.etapaEstadoUltimoEstado(numeroExpediente);
        return retornaDetalle;
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

    public List<Expediente> getListadoGeneral() {
        return listadoGeneral;
    }

    public void setListadoGeneral(List<Expediente> listadoGeneral) {
        this.listadoGeneral = listadoGeneral;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    public Usuario getUsuarioSession() {
        return usuarioSession;
    }

    public void setUsuarioSession(Usuario usuarioSession) {
        this.usuarioSession = usuarioSession;
    }

    public Integer getNroPagina() {
        return nroPagina;
    }

    public void setNroPagina(Integer nroPagina) {
        this.nroPagina = nroPagina;
    }
    
    
}
