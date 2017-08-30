package gob.dp.sid.administracion.seguridad.controller;

import java.util.ArrayList;
import java.util.List;

import gob.dp.sid.administracion.seguridad.entity.Recurso;
import gob.dp.sid.administracion.seguridad.entity.Rol;
import gob.dp.sid.administracion.seguridad.service.RecursoService;
import gob.dp.sid.administracion.seguridad.service.RolService;
import gob.dp.sid.comun.MessagesUtil;
import java.io.Serializable;

import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Named
@Scope("session")
public class AsignarRecursoRolController implements Serializable {

    private static Logger log = Logger.getLogger(AsignarRecursoRolController.class);
    
    private Rol rol;
    
    private String mensaje = "";

    private List<Recurso> listaRecurso;
    
    MessagesUtil msg;

    @Autowired
    private RecursoService recursoService;
    
    @Autowired
    private RolService rolService;

    public AsignarRecursoRolController() {
        msg = new MessagesUtil();
    }

    public void setRolService(RolService rolService) {
        this.rolService = rolService;
    }

    public void setRecursoService(RecursoService recursoService) {
        this.recursoService = recursoService;
    }

    public List<Recurso> getListaRecurso() {
        if (this.listaRecurso == null) {
            this.listaRecurso = new ArrayList<>();
        }
        return listaRecurso;
    }

    public void setListaRecurso(List<Recurso> listaRecurso) {
        this.listaRecurso = listaRecurso;
    }

    public String verAsignarRecursoRol(String codigo) {
        log.debug("Entrando a verAsignarRecurso:" + codigo);
        this.mensaje = "";
        Rol filter = new Rol();
        filter.setCodigo(codigo);
        this.rol = rolService.buscarRol(filter).get(0);
        this.listaRecurso = new ArrayList<>();
        this.listaRecurso = recursoService.buscarRecursosAsignados(this.rol);
        return "verAsignarRecursoRol";
    }

    public void asignarRecursosRol() {
        List<Recurso> listaSeleccionados = new ArrayList<>();
        for (Recurso rec : this.listaRecurso) {
            if (rec.isSeleccionado()) {
                listaSeleccionados.add(rec);
            }
        }
        try {
            recursoService.asignarRecursosRol(rol, listaSeleccionados);
            msg.messageInfo("Se realizaron los cambios correctamente", null);
        } catch (Exception ex) {
            log.error("Ocurrio un error:" + ex.getMessage(), ex);
        }
    }

    public String regresar() {
        return "rolLista";
    }

    public Rol getRol() {
        if (this.rol == null) {
            this.rol = new Rol();
        }
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
