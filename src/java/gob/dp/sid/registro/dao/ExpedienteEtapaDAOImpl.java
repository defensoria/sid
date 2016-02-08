/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.ExpedienteEtapa;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ExpedienteEtapaDAOImpl extends SqlSessionDaoSupport implements ExpedienteEtapaDAO{

    @Override
    public ExpedienteEtapa expedienteEtapaBuscar(int idEtapa) {
        return getSqlSession().selectOne("gob.dp.sid.registro.dao.ExpedienteEtapaDAO.expedienteEtapaBuscar", idEtapa);
    }
    
}
