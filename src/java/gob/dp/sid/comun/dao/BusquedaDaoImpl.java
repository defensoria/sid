/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.comun.dao;

import gob.dp.sid.comun.entity.Busqueda;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class BusquedaDaoImpl extends SqlSessionDaoSupport  implements BusquedaDao{

    @Override
    public List<Busqueda> busquedaLista() {
        return getSqlSession().selectList("gob.dp.sid.comun.dao.BusquedaDao.busquedaLista");
    }

    @Override
    public List<Busqueda> busquedaListaxPalabra(Busqueda busqueda) {
        return getSqlSession().selectList("gob.dp.sid.comun.dao.BusquedaDao.busquedaListaxPalabra", busqueda);
    }
    
}
