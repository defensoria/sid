package gob.dp.sid.administracion.seguridad.controller;

import java.util.ArrayList;
import java.util.List;

import gob.dp.sid.administracion.seguridad.entity.Rol;
import gob.dp.sid.administracion.seguridad.service.RolService;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;


@Named
@Scope("session")
public class RolController {
	 private static Logger log = Logger.getLogger(RolController.class);
	 
	 @Autowired
	 private RolService rolService;
	 private List<Rol> listaRol;

	 
    public String cargarPagina()
    {
        listaRol=rolService.buscarRol(new Rol());
        log.debug("METODO : RolController.cargarPagina");
       
        return "rolLista";
    }
	 
	public void setRolService(RolService rolService) {
		this.rolService = rolService;
	}

	public List<Rol> getListaRol() {
		if(this.listaRol==null){
		   this.listaRol=new ArrayList<Rol>();	
		}		
		return listaRol;
	}

	public void setListaRol(List<Rol> listaRol) {
		this.listaRol = listaRol;
	}
	
	 
}
