/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.ExpedienteHistorial;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ExpedienteHistorialDAOImpl extends SqlSessionDaoSupport implements ExpedienteHistorialDAO{

    @Override
    public void expedienteHistorialInsertar(ExpedienteHistorial expedienteHistorial) {
        getSqlSession().insert("gob.dp.sid.registro.dao.ExpedienteHistorialDAO.expedienteHistorialInsertar", expedienteHistorial);
    }

    @Override
    public List<ExpedienteHistorial> expedienteHistorialBuscar(String numeroExpediente) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.ExpedienteHistorialDAO.expedienteHistorialBuscar", numeroExpediente);
    }
    
}
