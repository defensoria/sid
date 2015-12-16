/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.bandeja.dao;

import gob.dp.sid.bandeja.entity.Bandeja;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class BandejaDAOImpl extends SqlSessionDaoSupport implements BandejaDAO{

    @Override
    public void bandejaInsertar(Bandeja bandeja) {
        getSqlSession().insert("gob.dp.sid.bandeja.dao.BandejaDAO.bandejaInsertar", bandeja);
    }

    @Override
    public List<Bandeja> bandejaBuscarUsuario(String destinatario) {
        return getSqlSession().selectList("gob.dp.sid.bandeja.dao.BandejaDAO.bandejaBuscarUsuario", destinatario);
    }

    @Override
    public void mensajeEstadoVisto(Long id) {
        getSqlSession().update("gob.dp.sid.bandeja.dao.BandejaDAO.mensajeEstadoVisto", id);
    }
    
}
