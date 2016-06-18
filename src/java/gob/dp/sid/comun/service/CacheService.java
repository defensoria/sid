/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.sid.comun.service;

import gob.dp.sid.comun.entity.Parametro;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface CacheService {
    
    public List<Parametro> buscarExpedienteTipoClasificacion();
    
    public List<Parametro> buscarExpedienteTipoIngreso();
    
    public List<Parametro> buscarExpedienteTema();
    
    public List<Parametro> buscarExpedienteSubTema(int padreParametro);
    
    public List<Parametro> buscarExpedientetTipoActor();
    
    public List<Parametro> buscarExpedienteEtiquetas();
    
    public List<Parametro> buscarGestionTipoAccion();
    
    public List<Parametro> buscarGestionTipoCalidadRespuesta();
    
    public List<Parametro> buscarAcogimientoRecomendaciones();
    
    public List<Parametro> buscarDocumentoGestion();
    
    public List<Parametro> buscarTipoDocumento();
    
    public List<Parametro> buscarActuacionResponsabilidad();
    
    public List<Parametro> buscarGrupoVulnerable();
    
    public List<Parametro> buscarGrupoEspecial();
    
    public List<Parametro> buscarTipoEntidad();
    
    public List<Parametro> buscarListaADOD();
    
    public List<Parametro> listaDepartamentos();
    
    public List<Parametro> listaPrimerNivel();
    
    public List<Parametro> listaONPRegimen();
    
    public List<Parametro> listaONPSubOrigen();
    
    public List<Parametro> listaONPTipoSolicitud();
    
    public List<Parametro> listaLenguaMaterna();
    
    public List<Parametro> listaPuebloIndigena();
    
    public List<Parametro> listaTipoArchivo();
    
    public List<Parametro> listaTipoSoporteArchivo();
    
    public List<Parametro> listaTipoAtr();
    
    public List<Parametro> buscarListaAdjuntia();
    
    public List<Parametro> buscarListaNacionalidades();
    
    public List<Parametro> buscarListaFormaCAV();
    
    public List<Parametro> buscarListaAccionCAV();
    
    public List<Parametro> buscarListaTipoCAV();
    
    public List<Parametro> buscarListaDocumentoCAV();
    
}