/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.administracion.seguridad.controller;

import gob.dp.sid.administracion.parametro.constantes.Constantes;
import gob.dp.sid.administracion.seguridad.entity.Rol;
import gob.dp.sid.administracion.seguridad.entity.Usuario;
import gob.dp.sid.administracion.seguridad.service.RolService;
import gob.dp.sid.administracion.seguridad.service.UsuarioService;
import gob.dp.sid.comun.controller.AbstractManagedBean;
import gob.dp.sid.registro.entity.OficinaDefensorial;
import gob.dp.sid.registro.service.OficinaDefensorialService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;

import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author WIN7
 */
@Named
@Scope("session")
public class UsuarioNuevoController extends AbstractManagedBean implements Serializable {

    private static final Logger log = Logger.getLogger(UsuarioNuevoController.class);

    private Usuario usuario;

    private String mensaje;

    private List<SelectItem> lstRoles;

    private List<String> lstRolesSeleccionados;

    private List<OficinaDefensorial> listaDefensorials;

    private boolean verGuardar = true;

    private boolean habilitado = true;

    private String rolSeleccionado;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @Autowired
    private OficinaDefensorialService oficinaDefensorialService;

    public String verUsuarioNuevo() {
        this.verGuardar = true;
        this.habilitado = true;
        this.usuario = new Usuario();
        listaDefensorials = oficinaDefensorialService.listaAdjuntiasDefensorialesTodos();
        this.mensaje = "";
        List<Rol> lstRolesTodos = rolService.buscarRol(new Rol());
        SelectItem item;
        this.lstRolesSeleccionados = new ArrayList<>();//Inicializo
        this.lstRoles = new ArrayList<>();
        for (Rol obj : lstRolesTodos) {
            item = new SelectItem(obj.getCodigo(), obj.getNombre());
            this.getLstRoles().add(item);
        }
        return "usuarioNuevo";
    }

    private void llenarFiltro(Usuario filter) {

        if (usuario.getCodigo() != null && !usuario.getCodigo().trim().equals("")) {
            filter.setCodigo(usuario.getCodigo().trim());
        }
        if (usuario.getClave() != null && !usuario.getClave().trim().equals("")) {
            filter.setClave(usuario.getClave().trim());
        }
        if (usuario.getConfirmacionClave() != null && !usuario.getConfirmacionClave().trim().equals("")) {
            filter.setConfirmacionClave(usuario.getConfirmacionClave().trim());
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
        if (usuario.getEmail() != null && !usuario.getEmail().trim().equals("")) {
            filter.setEmail(usuario.getEmail().trim());
        }
        if (usuario.getTelefonoMovil() != null && !usuario.getTelefonoMovil().trim().equals("")) {
            filter.setTelefonoMovil(usuario.getTelefonoMovil().trim());
        }
        if (usuario.getTelefonoFijo() != null && !usuario.getTelefonoFijo().trim().equals("")) {
            filter.setTelefonoFijo(usuario.getTelefonoFijo().trim());
        }
        if (StringUtils.isNotBlank(usuario.getSexo())) {
            filter.setSexo(usuario.getSexo().trim());
        }
        if (StringUtils.isNotBlank(rolSeleccionado)) {
            if(StringUtils.equals(rolSeleccionado, "ROL0000001")){
                usuario.setCargo("Comisionado");
            }
            if(StringUtils.equals(rolSeleccionado, "ROL0000002")){
                usuario.setCargo("Jefe");
            }
            if(StringUtils.equals(rolSeleccionado, "ROL0000003")){
                usuario.setCargo("Comisionado");
            }
            if(StringUtils.equals(rolSeleccionado, "ROL0000004")){
                usuario.setCargo("Adjunto");
            }
            if(StringUtils.equals(rolSeleccionado, "ROL0000005")){
                usuario.setCargo("Practicante/Secigra");
            }
            if(StringUtils.equals(rolSeleccionado, "ROL0000006")){
                usuario.setCargo("Practicante/Secigra");
            }
        }
        
        if (usuario.getCodigoOD() != null) {
            filter.setCodigoOD(usuario.getCodigoOD());
            OficinaDefensorial of = oficinaDefensorialService.obtenerOficinaDefensorial(Long.parseLong(usuario.getCodigoOD().toString()));
            String numeroDepartamento = String.format("%2s",of.getIdDepartamento().toString()).replace(' ', '0');
            filter.setIdDepartamento(numeroDepartamento);
        }
        if (this.isHabilitado()) {
            filter.setEstado(Constantes.ESTADO_ACTIVO);
        } else {
            filter.setEstado(Constantes.ESTADO_INACTIVO);
        }
    }

    public String insertarUsuario() {
        Integer contador = usuarioService.listaUsuarioCount(usuario.getCodigo().toUpperCase());
        if(contador > 0){
            msg.messageAlert("El código de usuario ya existe en el sistema", null);
            return null;
        }
        Usuario filter = new Usuario();
        this.llenarFiltro(filter);
        try {
            List<Rol> lstRolSel = new ArrayList<>();
            Rol rol;
            //for (String codigo : this.getLstRolesSeleccionados()) {
                rol = new Rol();
                rol.setCodigo(rolSeleccionado);
                lstRolSel.add(rol);
            //}
            usuarioService.insertarUsuario(filter, lstRolSel);
            usuario.setCodigo(filter.getCodigo());
            Usuario usu = usuario;
            rolSeleccionado= null;
            usuario = new Usuario();
            FacesContext context = FacesContext.getCurrentInstance();
            BusquedaUsuarioController busquedaUsuarioController = (BusquedaUsuarioController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "busquedaUsuarioController");
            msg.messageInfo("Se registro el usuario", null);
            return busquedaUsuarioController.addUsuarioLista(usu);
        } catch (Exception e) {
            log.error(e);
        }
        return "usuarioLista";
    }

    public String regresar() {
        return "usuarioLista";
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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isVerGuardar() {
        return verGuardar;
    }

    public void setVerGuardar(boolean verGuardar) {
        this.verGuardar = verGuardar;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public List<SelectItem> getLstRoles() {
        if (this.lstRoles == null) {
            this.lstRoles = new ArrayList<>();
        }
        return lstRoles;
    }

    public void setLstRoles(List<SelectItem> lstRoles) {
        this.lstRoles = lstRoles;
    }

    public List<String> getLstRolesSeleccionados() {
        if (this.lstRolesSeleccionados == null) {
            this.lstRolesSeleccionados = new ArrayList<>();
        }
        return lstRolesSeleccionados;
    }

    public void setLstRolesSeleccionados(List<String> lstRolesSeleccionados) {
        this.lstRolesSeleccionados = lstRolesSeleccionados;
    }

    public RolService getRolService() {
        return rolService;
    }

    public void setRolService(RolService rolService) {
        this.rolService = rolService;
    }

    public String getRolSeleccionado() {
        return rolSeleccionado;
    }

    public void setRolSeleccionado(String rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }

    public List<OficinaDefensorial> getListaDefensorials() {
        return listaDefensorials;
    }

    public void setListaDefensorials(List<OficinaDefensorial> listaDefensorials) {
        this.listaDefensorials = listaDefensorials;
    }

}
