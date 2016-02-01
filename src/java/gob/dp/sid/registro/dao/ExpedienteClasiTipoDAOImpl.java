/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.ExpedienteClasiTipo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ExpedienteClasiTipoDAOImpl extends SqlSessionDaoSupport implements ExpedienteClasiTipoDAO{

    @Override
    public void expedienteClasiTipoInsertar(ExpedienteClasiTipo expedienteClasiTipo) {
        getSqlSession().insert("gob.dp.sid.registro.dao.ExpedienteClasiTipoDAO.expedienteClasiTipoInsertar", expedienteClasiTipo);
    }

    @Override
    public void expedienteClasiTipoEliminar(ExpedienteClasiTipo expedienteClasiTipo) {
        getSqlSession().delete("gob.dp.sid.registro.dao.ExpedienteClasiTipoDAO.expedienteClasiTipoEliminar", expedienteClasiTipo);
    }
    
}
