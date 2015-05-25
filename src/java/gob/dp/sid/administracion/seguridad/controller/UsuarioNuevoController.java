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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author WIN7
 */

@Named
@Scope("session")
public class UsuarioNuevoController implements Serializable{

    private static final Logger log = Logger.getLogger(UsuarioNuevoController.class);

    private Usuario usuario;
    private String mensaje;

    private List<SelectItem> lstRoles;
    private List<String> lstRolesSeleccionados;
    private boolean verGuardar=true;
    private boolean habilitado=true;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

      public String verUsuarioNuevo(){
        log.debug("Entrando a verUsuarioNuevo");
        this.verGuardar=true;
        this.habilitado=true;

        this.usuario=new Usuario();
        
        this.mensaje="";
        List<Rol> lstRolesTodos=rolService.buscarRol(new Rol());
        SelectItem item=null;
        this.lstRolesSeleccionados=new ArrayList<String>();//Inicializo
        this.lstRoles=new ArrayList<SelectItem>();
        for(Rol obj:lstRolesTodos){
            item=new SelectItem(obj.getCodigo(), obj.getNombre());
            this.getLstRoles().add(item);
        }

        return "usuarioNuevo";
     }


     private void llenarFiltro(Usuario filter){

        if(usuario.getCodigo()!=null && !usuario.getCodigo().trim().equals("")){
            filter.setCodigo(usuario.getCodigo().trim());
        }
        if(usuario.getClave()!=null && !usuario.getClave().trim().equals("")){
            filter.setClave(usuario.getClave().trim());
        }
        if(usuario.getConfirmacionClave()!=null && !usuario.getConfirmacionClave().trim().equals("")){
            filter.setConfirmacionClave(usuario.getConfirmacionClave().trim());
        }
        if(usuario.getDni()!=null&&!usuario.getDni().trim().equals("")){
            filter.setDni(usuario.getDni().trim());
        }
        if(usuario.getNombre()!=null && !usuario.getNombre().trim().equals("")){
            filter.setNombre(usuario.getNombre().trim());
        }
        if(usuario.getApellidoPaterno()!=null&&!usuario.getApellidoPaterno().trim().equals("")){
            filter.setApellidoPaterno(usuario.getApellidoPaterno().trim());
        }
        if(usuario.getApellidoMaterno()!=null&&!usuario.getApellidoMaterno().trim().equals("")){
            filter.setApellidoMaterno(usuario.getApellidoMaterno().trim());
        }
        if(usuario.getEmail()!=null && !usuario.getEmail().trim().equals("")){
            filter.setEmail(usuario.getEmail().trim());
        }
        if(usuario.getTelefonoMovil()!=null && !usuario.getTelefonoMovil().trim().equals("")){
            filter.setTelefonoMovil(usuario.getTelefonoMovil().trim());
        }
        if(usuario.getTelefonoFijo()!=null && !usuario.getTelefonoFijo().trim().equals("")){
            filter.setTelefonoFijo(usuario.getTelefonoFijo().trim());
        }
        
        if(this.isHabilitado()){
            filter.setEstado(Constantes.ESTADO_ACTIVO);
        }else{
            filter.setEstado(Constantes.ESTADO_INACTIVO);
        }
     }

      public void insertarUsuario(){
        log.debug("Entrando a insertarUsuario");
        Usuario filter=new Usuario();
        this.llenarFiltro(filter);
        
        try{
            List<Rol> lstRolSel=new ArrayList<>();
            Rol rol=null;
            for(String codigo:this.getLstRolesSeleccionados()){
                log.debug("Rol seleccionado:"+codigo);
                rol=new Rol();
                rol.setCodigo(codigo);
                lstRolSel.add(rol);
            }
            usuarioService.insertarUsuario(filter,lstRolSel);
            usuario.setCodigo(filter.getCodigo());
            System.out.println("administracion.usuario.crear.ok");
            usuario = new Usuario();
        }catch(Exception e){
            log.error(e);
        }

    }

     public String regresar(){
         return "usuarioLista";
     }

    public Usuario getUsuario() {
        if(this.usuario==null){
            this.usuario=new Usuario();
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
        if(this.lstRoles==null){
            this.lstRoles=new ArrayList<SelectItem>();
        }
        return lstRoles;
    }

    public void setLstRoles(List<SelectItem> lstRoles) {
        this.lstRoles = lstRoles;
    }

    public List<String> getLstRolesSeleccionados() {
         if(this.lstRolesSeleccionados==null){
            this.lstRolesSeleccionados=new ArrayList<String>();
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
}
