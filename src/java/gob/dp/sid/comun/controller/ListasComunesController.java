/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.comun.controller;

import gob.dp.sid.comun.ConstantesUtil;
import gob.dp.sid.comun.entity.Parametro;
import gob.dp.sid.comun.service.CacheService;
import java.util.Collections;
import java.util.Comparator;
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
        List<Parametro> lista1 = insertarValoresDefectoParametro(cacheService.buscarExpedienteTipoIngreso(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
        Collections.sort(lista1, new Comparator<Parametro>() {

			@Override
			public int compare(Parametro o1, Parametro o2) {
				return o1.getCodigoParametro().compareTo(o2.getCodigoParametro());
			}

		});
        return lista1;
    }
    
    public List<Parametro> buscarExpedienteTema(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarExpedienteTema(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarExpedienteSubTema(Integer codigoTema, boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarExpedienteSubTema(codigoTema), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarExpedienteTipoActor(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        List<Parametro> lista = insertarValoresDefectoParametro(cacheService.buscarExpedientetTipoActor(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
        
        Collections.sort(lista, new Comparator<Parametro>() {

			@Override
			public int compare(Parametro o1, Parametro o2) {
				return o1.getCodigoParametro().compareTo(o2.getCodigoParametro());
			}

		});
        return lista;
    }
    
    public List<Parametro> buscarExpedienteEtiquetas(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarExpedienteEtiquetas(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarGestionTipoAccion(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarGestionTipoAccion(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarGestionTipoCalidadRespuesta(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarGestionTipoCalidadRespuesta(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarAcogimientoRecomendaciones(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarAcogimientoRecomendaciones(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarDocumentoGestion(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarDocumentoGestion(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarTipoDocumento(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        List<Parametro> lista = insertarValoresDefectoParametro(cacheService.buscarTipoDocumento(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
        
        Collections.sort(lista, new Comparator<Parametro>() {

			@Override
			public int compare(Parametro o1, Parametro o2) {
				return o1.getCodigoParametro().compareTo(o2.getCodigoParametro());
			}

		});
        return lista;
    }
    
    public List<Parametro> buscarTipoEntidad(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        List<Parametro> lista = insertarValoresDefectoParametro(cacheService.buscarTipoEntidad(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
        
        Collections.sort(lista, new Comparator<Parametro>() {

			@Override
			public int compare(Parametro o1, Parametro o2) {
				return o1.getCodigoParametro().compareTo(o2.getCodigoParametro());
			}

		});
        return lista;
    } 
    
    
    public List<Parametro> buscarActuacionResponsabilidad(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarActuacionResponsabilidad(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarGrupoVulnerable(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarGrupoVulnerable(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarGrupoEspecial(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarGrupoEspecial(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarListaADOD(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarListaADOD(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> listaDepartamentos(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.listaDepartamentos(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> listaPrimerNivel(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.listaPrimerNivel(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> listaONPRegimen(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.listaONPRegimen(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> listaONPSubOrigen(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.listaONPSubOrigen(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> listaONPTipoSolicitud(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.listaONPTipoSolicitud(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> listaLenguaMaterna(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.listaLenguaMaterna(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> listaPuebloIndigena(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.listaPuebloIndigena(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> listaTipoArchivo(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.listaTipoArchivo(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> listaTipoSoporteArchivo(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.listaTipoSoporteArchivo(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> listaTipoAtr(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.listaTipoAtr(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarListaAdjuntia(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarListaAdjuntia(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> listaListaNacionalidades(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarListaNacionalidades(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarListaFormaCAV(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarListaFormaCAV(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarListaAccionCAV(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        List<Parametro> lista = insertarValoresDefectoParametro(cacheService.buscarListaAccionCAV(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
        
        Collections.sort(lista, new Comparator<Parametro>() {

			@Override
			public int compare(Parametro o1, Parametro o2) {
				return o1.getCodigoParametro().compareTo(o2.getCodigoParametro());
			}

		});
        return lista;
    }
    
    public List<Parametro> buscarListaTipoCAV(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarListaTipoCAV(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
    }
    
    public List<Parametro> buscarListaDocumentoCAV(boolean insertarTODOS, boolean insertarNINGUNO, boolean insertarSELECCIONE) {
        return insertarValoresDefectoParametro(cacheService.buscarListaDocumentoCAV(), insertarTODOS, insertarNINGUNO, insertarSELECCIONE);
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
