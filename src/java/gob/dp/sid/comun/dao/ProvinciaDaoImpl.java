/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.comun.dao;

import gob.dp.sid.comun.entity.Provincia;
import java.util.List;
import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ProvinciaDaoImpl extends SqlSessionDaoSupport implements ProvinciaDao{
    
    private static final Logger log = Logger.getLogger(ProvinciaDaoImpl.class);

    @Override
    public List<Provincia> provinciaLista(String idDepartamento) {
        log.debug("METODO : ProvinciaDaoImpl.provinciaLista");
        return getSqlSession().selectList("gob.dp.sid.comun.dao.ProvinciaDao.provinciaLista",idDepartamento);
    }

    @Override
    public Provincia provinciaOne(Provincia provincia) {
        return getSqlSession().selectOne("gob.dp.sid.comun.dao.ProvinciaDao.provinciaOne",provincia);
    }
}
