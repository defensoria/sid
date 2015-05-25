/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.sid.administracion.seguridad.controller;


import gob.dp.sid.administracion.seguridad.bean.FiltroUsuario;
import gob.dp.sid.administracion.seguridad.entity.Rol;
import gob.dp.sid.administracion.seguridad.entity.Usuario;
import gob.dp.sid.administracion.seguridad.service.RolService;
import gob.dp.sid.administracion.seguridad.service.UsuarioService;
import gob.dp.sid.comun.ConstantesUtil;
import gob.dp.sid.comun.MessagesUtil;
import gob.dp.sid.comun.controller.AbstractManagedBean;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
/**
 *
 * @author Administrador
 */

@Named
@Scope("session")
public class UsuarioDetalleController extends AbstractManagedBean implements Serializable {

    private static final Logger log = Logger.getLogger(UsuarioDetalleController.class);

    private Usuario usuario;
    
    private String mensaje;

    private List<SelectItem> lstRoles;
    
    private List<String> lstRolesSeleccionados;
    
    private boolean verGuardar=true;
    
    private boolean habilitado=true;
    
    private Part file1;
    
    MessagesUtil msg;

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private RolService rolService;

    public UsuarioDetalleController() {
        msg = new MessagesUtil();
    }
    
    public String verDetalleUsuario(String codigo){
        log.debug("verDetalleUsuario");        

        this.verGuardar=true;
        this.mensaje="";      
        FiltroUsuario filter=new FiltroUsuario();
        filter.setCodigo(codigo);
    
        usuario=usuarioService.buscarUsuario(filter).get(0);      

        List<Rol> lstRolesTodos=rolService.buscarRol(new Rol());
        SelectItem item;
        this.lstRoles=new ArrayList<>();
        for(Rol obj:lstRolesTodos){
            item=new SelectItem(obj.getCodigo(), obj.getNombre());
            this.getLstRoles().add(item);
        }

        Usuario filter2=new Usuario();
        filter2.setCodigo(codigo);
        List<Rol> lstRolUsuario=rolService.buscarRolSegunUsuario(filter2);
        this.lstRolesSeleccionados=new ArrayList<>();
         for(Rol obj:lstRolUsuario){
            this.getLstRolesSeleccionados().add(obj.getCodigo());
        }         
        return "usuarioDetalle";
    }
    
    public String verDetallePerfil(){
        FacesContext context = FacesContext.getCurrentInstance();
        LoginController loginController = (LoginController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "loginController");
        Usuario usuarioSession = loginController.getUsuarioSesion();
        verDetalleUsuario(usuarioSession.getCodigo());
        return "perfil";
    }

   public void guardar(){      
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
            usuarioService.modificarUsuario(filter,lstRolSel);
            msg.messageInfo("Se realizaron los cambios correctamente", null);
        }catch(Exception ex){
            mensaje="Ocurrió un error:"+ex.getMessage();
            log.error("Ocurrió un error", ex);
        }

    }

    public String regresar(){
         System.out.println("Entrando a UsuarioDetalleController.regresar");
         return "usuarioLista";
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
    }
   
    public void agregarImagen(){
        String nameArchive = getFilename(file1);
        String extencion = "";
        if(!stringUtil.isBlank(nameArchive)){
            switch (file1.getContentType()) {
            case "image/png":  extencion = ".png";
                     break;
            case "image/jpeg":  extencion = ".jpg";
                     break;
            case "image/gif":  extencion = ".gif";
                     break;                     
            }
            
            DateFormat fechaHora = new SimpleDateFormat("yyyyMMddHHmmss");
            String formato = fechaHora.format(new Date());
            String ruta = formato + extencion;
            File file = new File(ConstantesUtil.FILE_SYSTEM+ruta);
            try (InputStream input = file1.getInputStream()) {
                Files.copy(input, file.toPath());
            } catch (IOException ex) {
                log.error(ex.getCause());
            }
            usuario.setRuta("/filesystem/"+ruta);
            usuarioService.modificarUsuarioSimple(usuario);
            verDetallePerfil();
        }
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

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void setRolService(RolService rolService) {
        this.rolService = rolService;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public List<SelectItem> getLstRoles() {
        if(this.lstRoles==null){
            this.lstRoles=new ArrayList<>();
        }
        return lstRoles;
    }

    public void setLstRoles(List<SelectItem> lstRoles) {
        this.lstRoles = lstRoles;
    }

    public List<String> getLstRolesSeleccionados() {
       if(this.lstRolesSeleccionados==null){
            this.lstRolesSeleccionados=new ArrayList<>();
        }
        return lstRolesSeleccionados;
    }

    public void setLstRolesSeleccionados(List<String> lstRolesSeleccionados) {
        this.lstRolesSeleccionados = lstRolesSeleccionados;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
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

    public boolean isVerGuardar() {
        return verGuardar;
    }

    public void setVerGuardar(boolean verGuardar) {
        this.verGuardar = verGuardar;
    }

    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }

}