/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.OficinaDefensorial;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class OficinaDefensorialDAOImpl extends SqlSessionDaoSupport implements OficinaDefensorialDAO{

    @Override
    public List<OficinaDefensorial> listaOficinasDefensoriales() {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.OficinaDefensorialDAO.listaOficinasDefensoriales");
    }

    @Override
    public OficinaDefensorial obtenerOficinaDefensorial(Long id) {
        return getSqlSession().selectOne("gob.dp.sid.registro.dao.OficinaDefensorialDAO.obtenerOficinaDefensorial", id);
    }

    @Override
    public List<OficinaDefensorial> listaAdjuntiasDefensoriales() {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.OficinaDefensorialDAO.listaAdjuntiasDefensoriales");
    }

    @Override
    public List<OficinaDefensorial> listaAdjuntiasDefensorialesConsulta() {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.OficinaDefensorialDAO.listaAdjuntiasDefensorialesConsulta");
    }

    @Override
    public List<OficinaDefensorial> listaAdjuntiasDefensorialesTodos() {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.OficinaDefensorialDAO.listaAdjuntiasDefensorialesTodos");
    }
    
}
