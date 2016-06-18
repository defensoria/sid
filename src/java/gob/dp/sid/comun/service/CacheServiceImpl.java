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
    
    private static final Integer CACHE_PARAMETRO_TIPO_ENTIDAD = 150;
    
    private static final Integer CACHE_PARAMETRO_LISTA_ADOD = 160;
    
    private static final Integer CACHE_PARAMETRO_LISTA_DEPARTAMENTOS = 170;
    
    private static final Integer CACHE_PARAMETRO_LISTA_PRIMER_NIVEL = 180;
    
    private static final Integer CACHE_PARAMETRO_LISTA_ONP_REGIMEN = 190;
    
    private static final Integer CACHE_PARAMETRO_LISTA_ONP_SUB_ORIGEN = 200;
    
    private static final Integer CACHE_PARAMETRO_LISTA_ONP_TIPO_SOLICITUD = 210;
    
    private static final Integer CACHE_PARAMETRO_LISTA_LENGUA_MATERNA = 220;
    
    private static final Integer CACHE_PARAMETRO_LISTA_PUEBLO_INDIGENA = 230;
    
    private static final Integer CACHE_PARAMETRO_TIPO_ARCHIVO = 240;
    
    private static final Integer CACHE_PARAMETRO_TIPO_SOPORTE_ARCHIVO = 250;
    
    private static final Integer CACHE_PARAMETRO_TIPO_ATR = 260;
    
    private static final Integer CACHE_PARAMETRO_LISTA_ADJ = 270;
    
    private static final Integer CACHE_PARAMETRO_LISTA_NACIONALIDADES = 280;
    
    private static final Integer CACHE_PARAMETRO_LISTA_FORMA_CAV = 320;
    
    private static final Integer CACHE_PARAMETRO_LISTA_ACCION_CAV = 330;
    
    private static final Integer CACHE_PARAMETRO_LISTA_TIPO_CAV = 340;
    
    private static final Integer CACHE_PARAMETRO_LISTA_DOCUMENTO_CAV = 310;
    

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
    
    @Override
    public List<Parametro> buscarTipoEntidad() {
        return buscarParametro(CACHE_PARAMETRO_TIPO_ENTIDAD, CACHE_PARAMETRO_TIPO_ENTIDAD);
    }
    
    @Override
    public List<Parametro> buscarListaADOD() {
        return buscarParametro(CACHE_PARAMETRO_LISTA_ADOD, CACHE_PARAMETRO_LISTA_ADOD);
    }
    
    @Override
    public List<Parametro> listaDepartamentos() {
        return buscarParametro(CACHE_PARAMETRO_LISTA_DEPARTAMENTOS, CACHE_PARAMETRO_LISTA_DEPARTAMENTOS);
    }
    
    @Override
    public List<Parametro> listaPrimerNivel() {
        return buscarParametro(CACHE_PARAMETRO_LISTA_PRIMER_NIVEL, CACHE_PARAMETRO_LISTA_PRIMER_NIVEL);
    }
    
    @Override
    public List<Parametro> listaONPRegimen() {
        return buscarParametro(CACHE_PARAMETRO_LISTA_ONP_REGIMEN, CACHE_PARAMETRO_LISTA_ONP_REGIMEN);
    }

    @Override
    public List<Parametro> listaONPSubOrigen() {
        return buscarParametro(CACHE_PARAMETRO_LISTA_ONP_SUB_ORIGEN, CACHE_PARAMETRO_LISTA_ONP_SUB_ORIGEN);
    }

    @Override
    public List<Parametro> listaONPTipoSolicitud() {
        return buscarParametro(CACHE_PARAMETRO_LISTA_ONP_TIPO_SOLICITUD, CACHE_PARAMETRO_LISTA_ONP_TIPO_SOLICITUD);
    }
    
    @Override
    public List<Parametro> listaLenguaMaterna() {
        return buscarParametro(CACHE_PARAMETRO_LISTA_LENGUA_MATERNA, CACHE_PARAMETRO_LISTA_LENGUA_MATERNA);
    }
    
    @Override
    public List<Parametro> listaPuebloIndigena() {
        return buscarParametro(CACHE_PARAMETRO_LISTA_PUEBLO_INDIGENA, CACHE_PARAMETRO_LISTA_PUEBLO_INDIGENA);
    }
    
    @Override
    public List<Parametro> listaTipoArchivo() {
        return buscarParametro(CACHE_PARAMETRO_TIPO_ARCHIVO, CACHE_PARAMETRO_TIPO_ARCHIVO);
    }

    @Override
    public List<Parametro> listaTipoSoporteArchivo() {
        return buscarParametro(CACHE_PARAMETRO_TIPO_SOPORTE_ARCHIVO, CACHE_PARAMETRO_TIPO_SOPORTE_ARCHIVO);
    }
    
    @Override
    public List<Parametro> listaTipoAtr() {
        return buscarParametro(CACHE_PARAMETRO_TIPO_ATR, CACHE_PARAMETRO_TIPO_ATR);
    }
    
    @Override
    public List<Parametro> buscarListaAdjuntia() {
        return buscarParametro(CACHE_PARAMETRO_LISTA_ADJ, CACHE_PARAMETRO_LISTA_ADJ);
    }
    
    @Override
    public List<Parametro> buscarListaNacionalidades() {
        return buscarParametro(CACHE_PARAMETRO_LISTA_NACIONALIDADES, CACHE_PARAMETRO_LISTA_NACIONALIDADES);
    }
    
    @Override
    public List<Parametro> buscarListaFormaCAV() {
        return buscarParametro(CACHE_PARAMETRO_LISTA_FORMA_CAV, CACHE_PARAMETRO_LISTA_FORMA_CAV);
    }

    @Override
    public List<Parametro> buscarListaAccionCAV() {
        return buscarParametro(CACHE_PARAMETRO_LISTA_ACCION_CAV, CACHE_PARAMETRO_LISTA_ACCION_CAV);
    }

    @Override
    public List<Parametro> buscarListaTipoCAV() {
        return buscarParametro(CACHE_PARAMETRO_LISTA_TIPO_CAV, CACHE_PARAMETRO_LISTA_TIPO_CAV);
    }
    
    @Override
    public List<Parametro> buscarListaDocumentoCAV() {
        return buscarParametro(CACHE_PARAMETRO_LISTA_DOCUMENTO_CAV, CACHE_PARAMETRO_LISTA_DOCUMENTO_CAV);
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


