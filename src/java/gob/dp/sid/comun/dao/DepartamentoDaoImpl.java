/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.comun.dao;

import gob.dp.sid.comun.entity.Departamento;
import java.util.List;
import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class DepartamentoDaoImpl extends SqlSessionDaoSupport implements DepartamentoDao{
    
    private static final Logger log = Logger.getLogger(DepartamentoDaoImpl.class);

    @Override
    public List<Departamento> departamentoLista() {
        log.debug("METODO : DepartamentoDaoImpl.departamentoLista");
        return getSqlSession().selectList("gob.dp.sid.comun.dao.DepartamentoDao.departamentoLista",1);
    }

    @Override
    public Departamento departamentoOne(String idDepartamento) {
        return getSqlSession().selectOne("gob.dp.sid.comun.dao.DepartamentoDao.departamentoOne",idDepartamento);
    }
    
}
