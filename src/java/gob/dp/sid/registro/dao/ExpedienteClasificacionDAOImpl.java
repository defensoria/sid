/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.ExpedienteClasificacion;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ExpedienteClasificacionDAOImpl extends SqlSessionDaoSupport implements ExpedienteClasificacionDAO{

    @Override
    public List<ExpedienteClasificacion> listaExpedienteClasificacion(ExpedienteClasificacion expedienteClasificacion) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.ExpedienteClasificacionDAO.expedienteClasificacionLista", expedienteClasificacion);
    }

    @Override
    public List<ExpedienteClasificacion> expedienteClasificacionBusqueda(ExpedienteClasificacion expedienteClasificacion) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.ExpedienteClasificacionDAO.expedienteClasificacionBusqueda", expedienteClasificacion);
    }

    @Override
    public ExpedienteClasificacion expedienteClasificacionOne(Integer id) {
        return getSqlSession().selectOne("gob.dp.sid.registro.dao.ExpedienteClasificacionDAO.expedienteClasificacionOne", id);
    }

    @Override
    public List<ExpedienteClasificacion> expedienteClasificacionBusquedaGrupo1(ExpedienteClasificacion expedienteClasificacion) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.ExpedienteClasificacionDAO.expedienteClasificacionBusquedaGrupo1", expedienteClasificacion);
    }
    
}
