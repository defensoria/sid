/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.ExpedienteTiempo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ExpedienteTiempoDAOImpl extends SqlSessionDaoSupport implements ExpedienteTiempoDAO{

    @Override
    public void expedienteTiempoInsertar(ExpedienteTiempo expedienteTiempo) {
        getSqlSession().insert("gob.dp.sid.registro.dao.ExpedienteTiempoDAO.expedienteTiempoInsertar", expedienteTiempo);
    }

    @Override
    public void expedienteTiempoUpdate(ExpedienteTiempo expedienteTiempo) {
        getSqlSession().insert("gob.dp.sid.registro.dao.ExpedienteTiempoDAO.expedienteTiempoUpdate", expedienteTiempo);
    }

    @Override
    public ExpedienteTiempo expedienteTiempoOne(String numeroExpediente) {
        return getSqlSession().selectOne("gob.dp.sid.registro.dao.ExpedienteTiempoDAO.expedienteTiempoOne", numeroExpediente);
    }   
}
