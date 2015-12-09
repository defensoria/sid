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
    
}
