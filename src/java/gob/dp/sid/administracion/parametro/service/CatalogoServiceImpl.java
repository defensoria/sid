/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.sid.administracion.parametro.service;
import gob.dp.sid.administracion.parametro.entity.Catalogo;
import gob.dp.sid.administracion.parametro.bean.FiltroCatalogo;

import gob.dp.sid.administracion.parametro.dao.CatalogoDao;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrador
 */

@Service("catalogoService")
public class CatalogoServiceImpl implements CatalogoService
{
    private static Logger log = Logger.getLogger(CatalogoServiceImpl.class);

    @Autowired
    private CatalogoDao catalogoDao;

    @Override
    public List<Catalogo> buscarCatalogoPadre(FiltroCatalogo filtroCatalogo){
        log.debug("METODO : CatalogoServiceImpl.buscarCatalogoPadre");
        return catalogoDao.buscarCatalogoPadre(filtroCatalogo);
    }

    @Override
    public Integer getTotalBuscarCatalogoPadre(FiltroCatalogo filtroCatalogo){
        log.debug("METODO : CatalogoServiceImpl.getTotalBuscarCatalogoPadre");
        return catalogoDao.getTotalBuscarCatalogoPadre(filtroCatalogo);
    }

    @Override
    public List<Catalogo> buscarCatalogoHijo(FiltroCatalogo filtroCatalogo){
        log.debug("METODO : CatalogoServiceImpl.buscarCatalogoHijo");
        return catalogoDao.buscarCatalogoHijo(filtroCatalogo);
    }

    @Override
    public Integer getTotalBuscarCatalogoHijo(FiltroCatalogo filtroCatalogo){
        log.debug("METODO : CatalogoServiceImpl.getTotalBuscarCatalogoHijo");
        return catalogoDao.getTotalBuscarCatalogoHijo(filtroCatalogo);
    }

    @Override
    public Catalogo viewCatalogoPadre(Catalogo catalogo){
        return catalogoDao.viewCatalogoPadre(catalogo);
    }

    @Override
    public Catalogo viewCatalogoHijo(Catalogo catalogo){
        return catalogoDao.viewCatalogoHijo(catalogo);
    }

    @Override
    public void nuevoCatalogoPadre(Catalogo catalogo){
        log.debug("METODO : CatalogoServiceImpl.nuevoCatalogoPadre");
        catalogo.setNumParametro(catalogoDao.generarCodigoCatalogoPadre());
        catalogoDao.nuevoCatalogoPadre(catalogo);
    }

    @Override
    public void nuevoCatalogoHijo(Catalogo catalogo){
        log.debug("METODO : CatalogoServiceImpl.nuevoCatalogoHijo");
        catalogo.setNumParametro(catalogoDao.generarCodigoCatalogoHijo());
        catalogoDao.nuevoCatalogoHijo(catalogo);
    }

    @Override
    public void updateCatalogoPadre(Catalogo catalogo){
        catalogoDao.updateCatalogoPadre(catalogo);
    }

    @Override
    public void updateCatalogoHijo(Catalogo catalogo){
        catalogoDao.updateCatalogoHijo(catalogo);
    }

    @Override
    public void updateEstadoCatalogoHijo(Catalogo catalogo){
        catalogoDao.updateEstadoCatalogoHijo(catalogo);
    }

    /**
     * @return the parametroDao
     */
    public CatalogoDao getCatalogoDao() {
        return catalogoDao;
    }

    /**
     * @param parametroDao the parametroDao to set
     */
    public void setCatalogoDao(CatalogoDao catalogoDao) {
        this.catalogoDao = catalogoDao;
    }

    @Override
    public List<Catalogo> listarCatalogoPadre() throws Exception {
        log.debug("METODO : CatalogoServiceImpl.listarCatalogoPadre");
        return catalogoDao.listarCatalogoPadre();
    }
    
}