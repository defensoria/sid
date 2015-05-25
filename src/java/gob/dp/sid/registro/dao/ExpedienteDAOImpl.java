/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.Expediente;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ExpedienteDAOImpl extends SqlSessionDaoSupport implements ExpedienteDAO{

    @Override
    public void expedienteInsertar(Expediente expediente) {
        getSqlSession().insert("gob.dp.sid.registro.dao.ExpedienteDAO.expedienteInsertar", expediente);
    }

    @Override
    public void expedienteUpdate(Expediente expediente) {
        getSqlSession().update("gob.dp.sid.registro.dao.ExpedienteDAO.expedienteUpdate", expediente);
    }

    @Override
    public List<Expediente> expedienteBuscar(Expediente expediente) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.ExpedienteDAO.expedienteBuscar", expediente);
    }
    
}
