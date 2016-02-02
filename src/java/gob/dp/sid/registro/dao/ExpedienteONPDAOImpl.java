/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.ExpedienteONP;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ExpedienteONPDAOImpl extends SqlSessionDaoSupport implements ExpedienteONPDAO{

    @Override
    public void expedienteONPInsertar(ExpedienteONP onp) {
        getSqlSession().insert("gob.dp.sid.registro.dao.ExpedienteONPDAO.expedienteONPInsertar", onp);
    }

    @Override
    public void expedienteONPUpdate(ExpedienteONP onp) {
        getSqlSession().insert("gob.dp.sid.registro.dao.ExpedienteONPDAO.expedienteONPUpdate", onp);
    }

    @Override
    public ExpedienteONP expedienteONPBuscarId(long id) {
        return getSqlSession().selectOne("gob.dp.sid.registro.dao.ExpedienteONPDAO.expedienteONPBuscarId", id);
    }

    @Override
    public ExpedienteONP expedienteONPBuscarExpediente(String numeroExpediente) {
        return getSqlSession().selectOne("gob.dp.sid.registro.dao.ExpedienteONPDAO.expedienteONPBuscarExpediente", numeroExpediente);
    }
    
}
