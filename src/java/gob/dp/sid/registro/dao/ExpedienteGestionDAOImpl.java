/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.ExpedienteGestion;
import java.util.List;
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
    public List<ExpedienteGestion> expedienteGestionLista(Long idExpediente) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.ExpedienteGestionDAO.expedienteGestionLista", idExpediente);
    }

    @Override
    public ExpedienteGestion expedienteGestionBuscarOne(Long id) {
        return getSqlSession().selectOne("gob.dp.sid.registro.dao.ExpedienteGestionDAO.expedienteGestionBuscarOne", id);
    }
    
}
