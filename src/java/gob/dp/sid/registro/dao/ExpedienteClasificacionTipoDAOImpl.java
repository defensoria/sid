/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.ExpedienteClasificacionTipo;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ExpedienteClasificacionTipoDAOImpl extends SqlSessionDaoSupport implements ExpedienteClasificacionTipoDAO{

    @Override
    public List<ExpedienteClasificacionTipo> clasificacionCabecera(int idClasificacion) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.ExpedienteClasificacionTipoDAO.clasificacionCabecera", idClasificacion);
    }

    @Override
    public List<ExpedienteClasificacionTipo> clasificacionPorIdPadre(int idPadre) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.ExpedienteClasificacionTipoDAO.clasificacionPorIdPadre", idPadre);
    }

    @Override
    public ExpedienteClasificacionTipo clasificacionOne(int id) {
        return getSqlSession().selectOne("gob.dp.sid.registro.dao.ExpedienteClasificacionTipoDAO.clasificacionOne", id);
    }

    @Override
    public List<ExpedienteClasificacionTipo> clasificacioneExpedienteClasiTipo(long idExpedienteClasificacion) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.ExpedienteClasificacionTipoDAO.clasificacioneExpedienteClasiTipo", idExpedienteClasificacion);
    }
    
}
