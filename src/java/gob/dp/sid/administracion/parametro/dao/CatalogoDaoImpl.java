/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.sid.administracion.parametro.dao;
import gob.dp.sid.administracion.parametro.entity.Catalogo;
import gob.dp.sid.administracion.parametro.bean.FiltroCatalogo;

import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrador
 */
@Repository
public class CatalogoDaoImpl  extends SqlSessionDaoSupport implements CatalogoDao
{
    @Override
    public List<Catalogo> buscarCatalogoPadre(FiltroCatalogo filtroCatalogo)
    {
       return getSqlSession().selectList("gob.dp.sid.administracion.parametro.dao.CatalogoDao.buscarCatalogoPadre",filtroCatalogo);
    }

    @Override
    public Integer getTotalBuscarCatalogoPadre(FiltroCatalogo filtroCatalogo)
    {
       return (Integer) getSqlSession().selectOne("gob.dp.sid.administracion.parametro.dao.CatalogoDao.getTotalBuscarCatalogoPadre",filtroCatalogo);
    }
    @Override
    public List<Catalogo> buscarCatalogoHijo(FiltroCatalogo filtroCatalogo)
    {
       return getSqlSession().selectList("gob.dp.sid.administracion.parametro.dao.CatalogoDao.buscarCatalogoHijo",filtroCatalogo);
    }

    @Override
    public Integer getTotalBuscarCatalogoHijo(FiltroCatalogo filtroCatalogo)
    {
       return (Integer) getSqlSession().selectOne("gob.dp.sid.administracion.parametro.dao.CatalogoDao.getTotalBuscarCatalogoHijo",filtroCatalogo);
    }

    @Override
    public Catalogo viewCatalogoPadre(Catalogo catalogo){
       return getSqlSession().selectOne("gob.dp.sid.administracion.parametro.dao.CatalogoDao.viewCatalogoPadre",catalogo);
    }

    @Override
    public Catalogo viewCatalogoHijo(Catalogo catalogo){
        return getSqlSession().selectOne("gob.dp.sid.administracion.parametro.dao.CatalogoDao.viewCatalogoHijo",catalogo);
    }

    @Override
    public void nuevoCatalogoPadre(Catalogo catalogo){
       getSqlSession().insert("gob.dp.sid.administracion.parametro.dao.CatalogoDao.nuevoCatalogoPadre",catalogo);
    }

    @Override
    public void nuevoCatalogoHijo(Catalogo catalogo){
       getSqlSession().insert("gob.dp.sid.administracion.parametro.dao.CatalogoDao.nuevoCatalogoHijo",catalogo);
    }

    @Override
    public void updateCatalogoPadre(Catalogo catalogo){
       getSqlSession().update("gob.dp.sid.administracion.parametro.dao.CatalogoDao.updateCatalogoPadre",catalogo);
    }

    @Override
    public void updateCatalogoHijo(Catalogo catalogo){
       getSqlSession().update("gob.dp.sid.administracion.parametro.dao.CatalogoDao.updateCatalogoHijo",catalogo);
    }

    @Override
    public void updateEstadoCatalogoHijo(Catalogo catalogo) {
        getSqlSession().update("gob.dp.sid.administracion.parametro.dao.CatalogoDao.updateEstadoCatalogoHijo",catalogo);
    }

    @Override
    public List<Catalogo> listarCatalogoPadre() {
        return getSqlSession().selectList("gob.dp.sid.administracion.parametro.dao.CatalogoDao.listarCatalogoPadre",null);
    }

    @Override
    public Integer generarCodigoCatalogoPadre() {
        return getSqlSession().selectOne("gob.dp.sid.administracion.parametro.dao.CatalogoDao.generarCodigoCatalogoPadre");
    }

    @Override
    public Integer generarCodigoCatalogoHijo() {
        return getSqlSession().selectOne("gob.dp.sid.administracion.parametro.dao.CatalogoDao.generarCodigoCatalogoHijo");
    }
}