/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.ExpedienteAmpliacion;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ExpedienteAmpliacionDAOImpl extends SqlSessionDaoSupport implements ExpedienteAmpliacionDAO{

    @Override
    public void expedienteAmpliacionInsertar(ExpedienteAmpliacion expedienteAmpliacion) {
        getSqlSession().insert("gob.dp.sid.registro.dao.ExpedienteAmpliacionDAO.expedienteAmpliacionInsertar", expedienteAmpliacion);
    }

    @Override
    public List<ExpedienteAmpliacion> expedienteAmpliacionSelectList(long idExpediente) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.ExpedienteAmpliacionDAO.expedienteAmpliacionSelectList", idExpediente);
    }
    
}
