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
    public List<ExpedienteConsulta> expedienteConsultaSelectList(long idExpediente) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.ExpedienteConsultaDAO.expedienteConsultaSelectList", idExpediente);
    }

    @Override
    public List<ExpedienteConsulta> expedienteConsultaPorExpediente(String numeroExpediente) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.ExpedienteConsultaDAO.expedienteConsultaPorExpediente", numeroExpediente);
    }
    
}
