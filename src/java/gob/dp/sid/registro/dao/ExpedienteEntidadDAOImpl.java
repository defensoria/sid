/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.ExpedienteEntidad;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ExpedienteEntidadDAOImpl extends SqlSessionDaoSupport implements ExpedienteEntidadDAO {
    
    @Override
    public void expedienteEntidadInsertar(ExpedienteEntidad expedienteEntidad) {
        getSqlSession().insert("gob.dp.sid.registro.dao.ExpedienteEntidadDAO.expedienteEntidadInsertar", expedienteEntidad);
    }

    @Override
    public void expedienteEntidadDelete(ExpedienteEntidad expedienteEntidad) {
        getSqlSession().delete("gob.dp.sid.registro.dao.ExpedienteEntidadDAO.expedienteEntidadDelete", expedienteEntidad);
    }

    @Override
    public int expedienteEntidadContar(ExpedienteEntidad expedienteEntidad) {
        return getSqlSession().selectOne("gob.dp.sid.registro.dao.ExpedienteEntidadDAO.expedienteEntidadContar", expedienteEntidad);
    }

    @Override
    public List<ExpedienteEntidad> expedienteEntidadBuscarXExpediente(Long idExpediente) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.ExpedienteEntidadDAO.expedienteEntidadBuscarXExpediente", idExpediente);
    }
}
