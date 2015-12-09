/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.ExpedienteDerivacion;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ExpedienteDerivacionDAOImpl extends SqlSessionDaoSupport implements ExpedienteDerivacionDAO{

    @Override
    public void expedienteDerivacionInsertar(ExpedienteDerivacion expedienteDerivacion) {
        getSqlSession().insert("gob.dp.sid.registro.dao.ExpedienteDerivacionDAO.expedienteDerivacionInsertar", expedienteDerivacion);
    }
    
}
