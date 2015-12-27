/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.comun.service;

import gob.dp.sid.comun.entity.FiltroParametro;
import gob.dp.sid.comun.entity.Parametro;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class CacheServiceImpl implements CacheService{

    private static final Logger log = Logger.getLogger(CacheServiceImpl.class);

    private static final Integer CACHE_PARAMETRO_EXPEDIENTE_TIPO_CLASIFICACION = 10;
    
    private static final Integer CACHE_PARAMETRO_EXPEDIENTE_TIPO_INGRESO = 20;
    
    private static final Integer CACHE_PARAMETRO_EXPEDIENTE_TEMA = 30;
    
    //private static final Integer CACHE_PARAMETRO_EXPEDIENTE_SUBTEMA = 40;
    
    private static final Integer CACHE_PARAMETRO_EXPEDIENTE_TIPO_ACTOR = 50;
    
    private static final Integer CACHE_PARAMETRO_ETIQUETA = 60;
    
    private static final Integer CACHE_PARAMETRO_GESTION_TIPO_ACCION = 70;
    
    private static final Integer CACHE_PARAMETRO_GESTION_TIPO_CALIDAD_RESPUESTA = 80;
    
    private static final Integer CACHE_PARAMETRO_ACOGIMIENTO_RECOMENDACIONES = 90;
    
    private static final Integer CACHE_PARAMETRO_DOCUMENTO_GESTION = 100;
    
    private static final Integer CACHE_PARAMETRO_TIPO_DOCUMENTO = 110;
    
    private static final Integer CACHE_PARAMETRO_ACTUACION_RESPONSABILIDAD = 120;
    
    private static final Integer CACHE_PARAMETRO_GRUPO_VULNERABLE = 130;
    
    private static final Integer CACHE_PARAMETRO_GRUPO_ESPECIAL = 140;

    private volatile HashMap<Integer, Object> contenedor = null;

    @Autowired
    private ParametroService parametroService;

    private List<Parametro> buscarParametro(Integer codigoPadre, Integer key) {
        return buscarParametro(codigoPadre, key, false);
    }

    private List<Parametro> buscarParametro(Integer codigoPadre, Integer key, boolean ordenValor) {
        List<Parametro> lst = (List<Parametro>) getElemento(key);
        if (lst == null) {
            try {
                FiltroParametro filtro = new FiltroParametro();
                filtro.setOrdenValor(ordenValor);
                filtro.setCodigoPadreParametro(codigoPadre);
                lst = parametroService.buscarParametro(filtro);
                putElemento(key, lst);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        if (lst != null) {
            return new ArrayList(lst);
        }
        return lst;
    }

    @Override
    public List<Parametro> buscarExpedienteTipoClasificacion() {
        return buscarParametro(CACHE_PARAMETRO_EXPEDIENTE_TIPO_CLASIFICACION, CACHE_PARAMETRO_EXPEDIENTE_TIPO_CLASIFICACION);
    }
    
    @Override
    public List<Parametro> buscarExpedienteTipoIngreso() {
        return buscarParametro(CACHE_PARAMETRO_EXPEDIENTE_TIPO_INGRESO, CACHE_PARAMETRO_EXPEDIENTE_TIPO_INGRESO);
    }
    
    @Override
    public List<Parametro> buscarExpedienteTema() {
        return buscarParametro(CACHE_PARAMETRO_EXPEDIENTE_TEMA, CACHE_PARAMETRO_EXPEDIENTE_TEMA);
    }
    
    @Override
    public List<Parametro> buscarExpedienteSubTema(int padreParametro) {
        return buscarParametro(padreParametro, padreParametro);
    }
    
    @Override
    public List<Parametro> buscarExpedientetTipoActor() {
        return buscarParametro(CACHE_PARAMETRO_EXPEDIENTE_TIPO_ACTOR, CACHE_PARAMETRO_EXPEDIENTE_TIPO_ACTOR);
    }
    
    @Override
    public List<Parametro> buscarExpedienteEtiquetas() {
        return buscarParametro(CACHE_PARAMETRO_ETIQUETA, CACHE_PARAMETRO_ETIQUETA);
    }
    
    @Override
    public List<Parametro> buscarGestionTipoAccion() {
        return buscarParametro(CACHE_PARAMETRO_GESTION_TIPO_ACCION, CACHE_PARAMETRO_GESTION_TIPO_ACCION);
    }
    
    @Override
    public List<Parametro> buscarGestionTipoCalidadRespuesta() {
        return buscarParametro(CACHE_PARAMETRO_GESTION_TIPO_CALIDAD_RESPUESTA, CACHE_PARAMETRO_GESTION_TIPO_CALIDAD_RESPUESTA);
    }
    
    @Override
    public List<Parametro> buscarAcogimientoRecomendaciones() {
        return buscarParametro(CACHE_PARAMETRO_ACOGIMIENTO_RECOMENDACIONES, CACHE_PARAMETRO_ACOGIMIENTO_RECOMENDACIONES);
    }
    
    @Override
    public List<Parametro> buscarDocumentoGestion() {
        return buscarParametro(CACHE_PARAMETRO_DOCUMENTO_GESTION, CACHE_PARAMETRO_DOCUMENTO_GESTION);
    }
    
    @Override
    public List<Parametro> buscarTipoDocumento() {
        return buscarParametro(CACHE_PARAMETRO_TIPO_DOCUMENTO, CACHE_PARAMETRO_TIPO_DOCUMENTO);
    }
    
    @Override
    public List<Parametro> buscarActuacionResponsabilidad() {
        return buscarParametro(CACHE_PARAMETRO_ACTUACION_RESPONSABILIDAD, CACHE_PARAMETRO_ACTUACION_RESPONSABILIDAD);
    }

    @Override
    public List<Parametro> buscarGrupoVulnerable() {
        return buscarParametro(CACHE_PARAMETRO_GRUPO_VULNERABLE, CACHE_PARAMETRO_GRUPO_VULNERABLE);
    }
    
    @Override
    public List<Parametro> buscarGrupoEspecial() {
        return buscarParametro(CACHE_PARAMETRO_GRUPO_ESPECIAL, CACHE_PARAMETRO_GRUPO_ESPECIAL);
    }
    
    private Object getElemento(Integer key) {
        if (this.contenedor == null) {
            return null;
        }
        return this.contenedor.get(key);
    }

    private synchronized void putElemento(Integer key, Object objeto) {
        if (this.contenedor == null) {
            this.contenedor = new HashMap<>();
        }
        this.contenedor.put(key, objeto);
        notifyAll();
    }

    

}
