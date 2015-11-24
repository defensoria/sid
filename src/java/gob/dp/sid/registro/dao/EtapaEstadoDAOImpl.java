/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.EtapaEstado;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class EtapaEstadoDAOImpl extends SqlSessionDaoSupport implements EtapaEstadoDAO{

    @Override
    public void etapaEstadoInsertar(EtapaEstado etapaEstado) {
        getSqlSession().insert("gob.dp.sid.registro.dao.EtapaEstadoDAO.etapaEstadoInsertar", etapaEstado);    
    }

    @Override
    public void etapaEstadoUpdate(EtapaEstado etapaEstado) {
        getSqlSession().update("gob.dp.sid.registro.dao.EtapaEstadoDAO.etapaEstadoUpdate", etapaEstado);
    }

    @Override
    public EtapaEstado etapaEstadoVigente(long idExpediente) {
        return getSqlSession().selectOne("gob.dp.sid.registro.dao.EtapaEstadoDAO.etapaEstadoVigente", idExpediente);
    }

}
