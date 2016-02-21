/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.EtapaEstado;
import java.util.List;
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

    @Override
    public List<EtapaEstado> etapaEstadoxExpediente(String numeroExpediente) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.EtapaEstadoDAO.etapaEstadoxExpediente", numeroExpediente);
    }

    @Override
    public EtapaEstado etapaEstadoInicial(long idExpediente) {
        return getSqlSession().selectOne("gob.dp.sid.registro.dao.EtapaEstadoDAO.etapaEstadoInicial", idExpediente);
    }

    @Override
    public Long etapaEstadoUltimoEstado(String numeroExpediente) {
        return getSqlSession().selectOne("gob.dp.sid.registro.dao.EtapaEstadoDAO.etapaEstadoUltimoEstado", numeroExpediente);
    }

    @Override
    public EtapaEstado etapaEstadoPorID(long idEtapaEstado) {
        return getSqlSession().selectOne("gob.dp.sid.registro.dao.EtapaEstadoDAO.etapaEstadoPorID", idEtapaEstado);
    }

}
