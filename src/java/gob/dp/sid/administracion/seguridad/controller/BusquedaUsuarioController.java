/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.administracion.seguridad.controller;

import gob.dp.sid.administracion.seguridad.bean.FiltroUsuario;
import gob.dp.sid.administracion.seguridad.entity.Usuario;
import gob.dp.sid.administracion.seguridad.service.UsuarioService;
import gob.dp.sid.comun.ConstantesUtil;
import gob.dp.sid.comun.MessagesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author Luis Medina
 */
@Named
@Scope("session")
public class BusquedaUsuarioController implements Serializable {

    private static final Logger log = Logger.getLogger(BusquedaUsuarioController.class);

    private Long nroPagina = 1L;

    private Usuario usuario;

    private List<Usuario> listaUsuario;
    
    MessagesUtil msg;

    @Autowired
    private UsuarioService usuarioService;

    public BusquedaUsuarioController() {
        msg = new MessagesUtil();
    }

    public String cargarPagina() {
        listaUsuario = new ArrayList<>();
        log.debug("METODO : BusquedaUsuarioController.cargarPagina");
        this.usuario = new Usuario();
        return "usuarioLista";
    }

    public void buscarUsuario(Long page) {

        log.debug("Entrando a buscarUsuario:" + "dni:" + this.usuario.getDni());
        FiltroUsuario filter = new FiltroUsuario();
        if (usuario.getCodigo() != null && !usuario.getCodigo().trim().equals("")) {
            filter.setCodigo(usuario.getCodigo().trim());
        }
        if (usuario.getDni() != null && !usuario.getDni().trim().equals("")) {
            filter.setDni(usuario.getDni().trim());
        }
        if (usuario.getNombre() != null && !usuario.getNombre().trim().equals("")) {
            filter.setNombre(usuario.getNombre().trim());
        }
        if (usuario.getApellidoPaterno() != null && !usuario.getApellidoPaterno().trim().equals("")) {
            filter.setApellidoPaterno(usuario.getApellidoPaterno().trim());
        }
        if (usuario.getApellidoMaterno() != null && !usuario.getApellidoMaterno().trim().equals("")) {
            filter.setApellidoMaterno(usuario.getApellidoMaterno().trim());
        }

        try {
            listarPaginado2(filter, page);
        } catch (Exception e) {
            log.error(e);
        }
    }

    public void listarPaginado(Long pagina) {
        if(pagina > 0){
            int paginado = ConstantesUtil.PAGINADO_ACTORES_5;
            Long ini = paginado * (pagina - 1) + 1;
            Long fin = paginado * pagina;
            if (pagina == 0) {
                ini = 1L;
                fin = 5L;
            }
            FiltroUsuario filtroUsuario = new FiltroUsuario();
            filtroUsuario.setIni(ini);
            filtroUsuario.setFin(fin);
            try {
                List<Usuario> list = usuarioService.buscarUsuario(filtroUsuario);
                if(list.size() > 0){
                    listaUsuario = list;
                    nroPagina = pagina;
                }
            } catch (Exception e) {
                log.error("ERROR : BusquedaUsuarioController.listarPaginado: " + e.getMessage());
            }
        }
    }
    
    public void listarPaginado2(FiltroUsuario filtroUsuario, Long pagina) {
        if(pagina > 0){
            int paginado = ConstantesUtil.PAGINADO_ACTORES_5;
            Long ini = paginado * (pagina - 1) + 1;
            Long fin = paginado * pagina;
            if (pagina == 0) {
                ini = 1L;
                fin = 5L;
            }
            filtroUsuario.setIni(ini);
            filtroUsuario.setFin(fin);
            try {
                List<Usuario> list = usuarioService.buscarUsuario(filtroUsuario);
                if(list.size() > 0){
                    listaUsuario = list;
                    nroPagina = pagina;
                }
                if(listaUsuario.isEmpty()){
                    usuario = new Usuario();
                    msg.messageAlert("No se han encontrado Resultados para la busqueda", null);
                }
            } catch (Exception e) {
                log.error("ERROR : BusquedaUsuarioController.listarPaginado: " + e.getMessage());
            }
        }
    }

    public Usuario getUsuario() {
        if (this.usuario == null) {
            this.usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuario() {
        if (this.listaUsuario == null) {
            this.listaUsuario = new ArrayList<>();
        }
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Long getNroPagina() {
        return nroPagina;
    }

    public void setNroPagina(Long nroPagina) {
        this.nroPagina = nroPagina;
    }

}
