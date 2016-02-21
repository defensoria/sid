/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.ExpedienteSuspencion;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ExpedienteSuspencionDAOImpl extends SqlSessionDaoSupport implements ExpedienteSuspencionDAO{

    @Override
    public void expedienteSuspencionInsertar(ExpedienteSuspencion expedienteSuspencion) {
        getSqlSession().insert("gob.dp.sid.registro.dao.ExpedienteSuspencionDAO.expedienteSuspencionInsertar", expedienteSuspencion);
    }

    @Override
    public List<ExpedienteSuspencion> expedienteSuspencionSelectList(long idExpediente) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.ExpedienteSuspencionDAO.expedienteSuspencionSelectList", idExpediente);
    }
    
}
