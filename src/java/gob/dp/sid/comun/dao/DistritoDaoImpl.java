/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.comun.dao;

import gob.dp.sid.comun.entity.Distrito;
import java.util.List;
import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class DistritoDaoImpl extends SqlSessionDaoSupport implements DistritoDao{
    
    private static final Logger log = Logger.getLogger(DistritoDaoImpl.class);

    @Override
    public List<Distrito> distritoLista(Distrito distrito) {
        log.debug("METODO : DistritoDaoImpl.distritoLista");
        return getSqlSession().selectList("gob.dp.sid.comun.dao.DistritoDao.distritoLista",distrito);
    }

    @Override
    public Distrito distritoOne(Distrito distrito) {
        return getSqlSession().selectOne("gob.dp.sid.comun.dao.DistritoDao.distritoOne",distrito);
    }
    
}
