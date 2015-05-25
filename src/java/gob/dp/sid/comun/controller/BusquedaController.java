/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.comun.controller;

import gob.dp.sid.comun.entity.Busqueda;
import gob.dp.sid.comun.service.BusquedaService;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author carlos
 */
@SessionScoped
@Named("busquedaController")
public class BusquedaController implements Serializable{
    
    private String cadenaAutocomplete;
    
    private List<Busqueda> listadoGeneral;
    
    private Busqueda busqueda;
    
    @Autowired
    private BusquedaService busquedaService;

    public void inicioBusqueda(){
        busqueda = new Busqueda();
        //cadenaAutocomplete = busquedaService.autocompletarBusquedaGeneral();
    }
    
    public void pintar(){
        System.out.println("pintar");
    }
    
    public String buscar(){
        listadoGeneral = busquedaService.busquedaListaxPalabra(busqueda);
        return "busquedaGeneral";
    }
    
    public String getCadenaAutocomplete() {
        return cadenaAutocomplete;
    }

    public void setCadenaAutocomplete(String cadenaAutocomplete) {
        this.cadenaAutocomplete = cadenaAutocomplete;
    }

    public List<Busqueda> getListadoGeneral() {
        return listadoGeneral;
    }

    public void setListadoGeneral(List<Busqueda> listadoGeneral) {
        this.listadoGeneral = listadoGeneral;
    }

    public Busqueda getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(Busqueda busqueda) {
        this.busqueda = busqueda;
    }

}
