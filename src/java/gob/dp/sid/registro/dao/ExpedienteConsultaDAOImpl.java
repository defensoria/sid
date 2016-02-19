/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.ExpedienteConsulta;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ExpedienteConsultaDAOImpl extends SqlSessionDaoSupport implements ExpedienteConsultaDAO {

    @Override
    public void expedienteConsultaInsertar(ExpedienteConsulta expedienteConsulta) {
        getSqlSession().insert("gob.dp.sid.registro.dao.ExpedienteConsultaDAO.expedienteConsultaInsertar", expedienteConsulta);
    }

    @Override
    public ExpedienteConsulta expedienteConsultaSelectOne(long idExpedienteConsulta) {
        return getSqlSession().selectOne("gob.dp.sid.registro.dao.ExpedienteConsultaDAO.expedienteConsultaSelectOne", idExpedienteConsulta);
    }

    @Override
    public List<ExpedienteConsulta> expedienteConsultaPorExpediente(String numeroExpediente) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.ExpedienteConsultaDAO.expedienteConsultaPorExpediente", numeroExpediente);
    }

    @Override
    public List<ExpedienteConsulta> expedienteConsultaPorExpedientePadre(String numeroExpediente) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.ExpedienteConsultaDAO.expedienteConsultaPorExpedientePadre", numeroExpediente);
    }

    @Override
    public void expedienteConsultaUpdate(ExpedienteConsulta expedienteConsulta) {
        getSqlSession().update("gob.dp.sid.registro.dao.ExpedienteConsultaDAO.expedienteConsultaUpdate", expedienteConsulta);
    }

    @Override
    public ExpedienteConsulta expedienteConsultaPorExpedienteHijo(ExpedienteConsulta expedienteConsulta) {
        return getSqlSession().selectOne("gob.dp.sid.registro.dao.ExpedienteConsultaDAO.expedienteConsultaPorExpedienteHijo", expedienteConsulta);
    }

    @Override
    public ExpedienteConsulta expedienteConsultaPorEtapa(ExpedienteConsulta expedienteConsulta) {
        return getSqlSession().selectOne("gob.dp.sid.registro.dao.ExpedienteConsultaDAO.expedienteConsultaPorEtapa", expedienteConsulta);
    }

    @Override
    public int expedienteConsultaPorEtapaCount(ExpedienteConsulta expedienteConsulta) {
        return getSqlSession().selectOne("gob.dp.sid.registro.dao.ExpedienteConsultaDAO.expedienteConsultaPorEtapaCount", expedienteConsulta);
    }
    
}
