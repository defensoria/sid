/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.ExpedienteFormularioVirtual;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ExpedienteFormularioVirtualDAOImpl extends SqlSessionDaoSupport implements ExpedienteFormularioVirtualDAO {

    @Override
    public void expedienteFormularioVirtualInsertar(ExpedienteFormularioVirtual ExpedienteFormularioVirtual) {
        getSqlSession().insert("gob.dp.sid.registro.dao.ExpedienteFormularioVirtualDAO.expedienteFormularioVirtualInsertar", ExpedienteFormularioVirtual);
    }

    @Override
    public void expedienteFormularioVirtualUpdate(ExpedienteFormularioVirtual ExpedienteFormularioVirtual) {
        getSqlSession().update("gob.dp.sid.registro.dao.ExpedienteFormularioVirtualDAO.expedienteFormularioVirtualUpdate", ExpedienteFormularioVirtual);
    }

    @Override
    public List<ExpedienteFormularioVirtual> expedienteFormularioVirtualSelect(ExpedienteFormularioVirtual ExpedienteFormularioVirtual) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.ExpedienteFormularioVirtualDAO.expedienteFormularioVirtualSelect", ExpedienteFormularioVirtual);
    }

    @Override
    public void expedienteFormularioVirtualInactiva(Long idExpedienteFormularioVirtual) {
        getSqlSession().update("gob.dp.sid.registro.dao.ExpedienteFormularioVirtualDAO.expedienteFormularioVirtualInactiva", idExpedienteFormularioVirtual);
    }

    @Override
    public List<ExpedienteFormularioVirtual> expedienteFormularioVirtualBuscar(ExpedienteFormularioVirtual ExpedienteFormularioVirtual) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.ExpedienteFormularioVirtualDAO.expedienteFormularioVirtualBuscar", ExpedienteFormularioVirtual);
    }
    
}
