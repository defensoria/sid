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
    
    private Object getElemento(Integer key) {
        if (this.contenedor == null) {
            return null;
        }
        return this.contenedor.get(key);
    }

    private synchronized void putElemento(Integer key, Object objeto) {
        if (this.contenedor == null) {
            this.contenedor = new HashMap<Integer, Object>();
        }
        this.contenedor.put(key, objeto);
        notifyAll();
    }

}
