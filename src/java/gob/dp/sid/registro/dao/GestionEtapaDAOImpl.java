/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.GestionEtapa;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class GestionEtapaDAOImpl extends SqlSessionDaoSupport implements GestionEtapaDAO{

    @Override
    public void gestionEtapaInsertar(GestionEtapa gestionEtapa) {
        getSqlSession().insert("gob.dp.sid.registro.dao.GestionEtapaDAO.gestionEtapaInsertar", gestionEtapa);
    }
    
    
    
}
