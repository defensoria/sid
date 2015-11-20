/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.ExpedienteGestion;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ExpedienteGestionDAOImpl extends SqlSessionDaoSupport implements  ExpedienteGestionDAO{

    @Override
    public void expedienteGestionInsertar(ExpedienteGestion expedienteGestion) {
        getSqlSession().insert("gob.dp.sid.registro.dao.ExpedienteGestionDAO.expedienteGestionInsertar", expedienteGestion);
    }

    @Override
    public void expedienteGestionUpdate(ExpedienteGestion expedienteGestion) {
        getSqlSession().update("gob.dp.sid.registro.dao.ExpedienteGestionDAO.expedienteGestionUpdate", expedienteGestion);
    }

    @Override
    public ExpedienteGestion expedienteGestionBuscarOne() {
        return getSqlSession().selectOne("gob.dp.sid.registro.dao.ExpedienteGestionDAO.expedienteGestionBuscarOne");
    }
    
}
