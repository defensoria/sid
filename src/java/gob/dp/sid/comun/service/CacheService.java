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
    
}