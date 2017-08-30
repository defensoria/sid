/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.sid.administracion.parametro.service;

import gob.dp.sid.administracion.parametro.entity.Catalogo;
import gob.dp.sid.administracion.parametro.bean.FiltroCatalogo;

import java.util.List;

/**
 *
 * @author Administrador
 */
public interface CatalogoService {
    public List<Catalogo> buscarCatalogoPadre(FiltroCatalogo filtroCatalogo) throws Exception;
    public Integer getTotalBuscarCatalogoPadre(FiltroCatalogo filtroCatalogo) throws Exception;
    public List<Catalogo> buscarCatalogoHijo(FiltroCatalogo filtroCatalogo) throws Exception;
    public Integer getTotalBuscarCatalogoHijo(FiltroCatalogo filtroCatalogo) throws Exception;
    public List<Catalogo> listarCatalogoPadre() throws Exception;

    public Catalogo viewCatalogoPadre(Catalogo catalogo) throws Exception;
    public Catalogo viewCatalogoHijo(Catalogo catalogo) throws Exception;
    public void nuevoCatalogoPadre(Catalogo catalogo) throws Exception;
    public void nuevoCatalogoHijo(Catalogo catalogo) throws Exception;
    
    public void updateCatalogoPadre(Catalogo catalogo) throws Exception;
    public void updateCatalogoHijo(Catalogo catalogo) throws Exception;
    public void updateEstadoCatalogoHijo(Catalogo catalogo) throws Exception;    
    
}
