/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.entity.Expediente;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ExpedienteService {
    
    public void expedienteInsertar(Expediente expediente);
            
    public void expedienteUpdate(Expediente expediente); 
    
    public String etiquetaBuscarAutocomplete();
    
    public List<Expediente> expedienteBuscar(Expediente expediente); 
    
    public List<Expediente> expedienteBuscarUsuario(String codigo); 
    
    public void expedienteConcluir(long idExpediente);
    
    public List<Expediente> expedienteBuscarUsuarioPaginado(Expediente expediente); 
    
    public List<Expediente> expedienteReporteMesUsuario(Expediente expediente);
    
    public List<Expediente> expedientexPersona(long idPersona); 
    
    public List<Expediente> expedienteBuscarUsuarios(Expediente expediente);
    
    public Expediente expedienteBuscarActivo(Expediente expediente);
            
    public Expediente expedienteBuscarActivoEtapa(Expediente expediente);
    
    public Expediente expedienteBuscarPorId(Long idExpediente);
    
    public Long expedienteCodigoPorOD(Long codigoOD);
    
    public Expediente expedienteBuscarPorNumero(String numeroExpediente);
    
    public void expedienteAsignar(Expediente expediente);
    
    public void expedienteDesistir(Expediente expediente);
    
    public List<Expediente> expedienteBuscarUsuarioCompleto(Expediente expediente);
    
    public List<Expediente> expedienteBuscarUsuarioODPaginado(Expediente expediente);
    
    public void expedienteUpdateNoClasificacion(Expediente expediente);
    
    public Integer expedienteMayorInformacionCount(String numeroExpediente);

    public void expedienteInactivar(Expediente expediente);
    
    public void expedienteReconsideracion(Expediente expediente);
    
    public void expedienteUpdateArchivo(Expediente expediente);
    
    public void expedienteEliminarArchivo(long idExpediente);
}
