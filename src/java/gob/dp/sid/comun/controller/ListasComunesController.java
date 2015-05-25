/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.comun.controller;

import gob.dp.sid.comun.ConstantesUtil;
import gob.dp.sid.comun.entity.Parametro;
import gob.dp.sid.comun.service.CacheService;
import java.util.List;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author carlos
 */
@Named
@Scope("session")
public class ListasComunesController {

    private static final Logger log = Logger.getLogger(ListasComunesController.class);

    @Autowired
    private CacheService cacheService;

    public List<Parametro> buscarExpedienteTipoClasificacion(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarExpedienteTipoClasificacion(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarExpedienteTipoIngreso(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarExpedienteTipoIngreso(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarExpedienteTema(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarExpedienteTema(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarExpedienteSubTema(Integer codigoTema, boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarExpedienteSubTema(codigoTema), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarExpedienteTipoActor(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarExpedientetTipoActor(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarExpedienteEtiquetas(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarExpedienteEtiquetas(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }

    private List insertarValoresDefectoParametro(List lst, boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        if (insertarTODOS) {
            Parametro par = new Parametro();
            par.setValorParametro(ConstantesUtil.LISTA_VALOR_TODOS_CODIGO);
            par.setNombreParametro(ConstantesUtil.LISTA_VALOR_TODOS_NOMBRE);
            lst.add(0, par);
        }

        if (insertarNINGUNO) {
            Parametro par = new Parametro();
            par.setValorParametro(ConstantesUtil.LISTA_VALOR_NINGUNO_CODIGO);
            par.setNombreParametro(ConstantesUtil.LISTA_VALOR_NINGUNO_NOMBRE);
            lst.add(0, par);
        }

        if (insertarSELECCIONE) {
            Parametro par = new Parametro();
            par.setValorParametro(ConstantesUtil.LISTA_VALOR_SELECCIONE_CODIGO);
            par.setNombreParametro(ConstantesUtil.LISTA_VALOR_SELECCIONE_NOMBRE);
            lst.add(0, par);
        }

        return lst;
    }
}
