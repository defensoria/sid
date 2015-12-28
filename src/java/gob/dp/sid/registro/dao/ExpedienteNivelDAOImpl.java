/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.ExpedienteNivel;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ExpedienteNivelDAOImpl extends SqlSessionDaoSupport implements ExpedienteNivelDAO{

    @Override
    public void expedienteNivelInsertar(ExpedienteNivel expedienteNivel) {
        getSqlSession().insert("gob.dp.sid.registro.dao.ExpedienteNivelDAO.expedienteNivelInsertar", expedienteNivel);
    }

    @Override
    public void expedienteNivelUpdate(Long idExpedienteNivel) {
        getSqlSession().update("gob.dp.sid.registro.dao.ExpedienteNivelDAO.expedienteNivelUpdate", idExpedienteNivel);
    }

    @Override
    public List<ExpedienteNivel> expedienteNivelPorExpediente(String numeroExpediente) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.ExpedienteNivelDAO.expedienteNivelPorExpediente", numeroExpediente);
    }

    @Override
    public void expedienteNivelActualizar(ExpedienteNivel expedienteNivel) {
        getSqlSession().update("gob.dp.sid.registro.dao.ExpedienteNivelDAO.expedienteNivelActualizar", expedienteNivel);
    }
    
}
