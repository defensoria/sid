/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.sid.comun.dao;

import gob.dp.sid.comun.entity.FiltroParametro;
import gob.dp.sid.comun.entity.Parametro;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrador
 */

@Repository
public class ParametroDaoImpl  extends SqlSessionDaoSupport  implements ParametroDao {

    @Override
    public Parametro consultarParametro(FiltroParametro filtro) {
        return getSqlSession().selectOne("gob.dp.sid.comun.dao.ParametroDao.consultarParametro",filtro);
    }

    @Override
    public List<Parametro> buscarParametro(FiltroParametro filtro) {
        return getSqlSession().selectList("gob.dp.sid.comun.dao.ParametroDao.buscarParametro",filtro);
    }

    @Override
    public void crearParametro(Parametro obj) {
        getSqlSession().insert("gob.dp.sid.comun.dao.ParametroDao.crearParametro", obj);
    }

    @Override
    public void modificarParametro(Parametro obj) {
        getSqlSession().update("gob.dp.sid.comun.dao.ParametroDao.modificarParametro", obj);
    }

    @Override
    public void eliminarParametro(FiltroParametro filtro) {
        getSqlSession().delete("gob.dp.sid.comun.registro.ParametroDao.eliminarParametro",filtro);
    }

    @Override
    public Parametro consultarParametroValor(FiltroParametro filtro) {
        return getSqlSession().selectOne("gob.dp.sid.comun.dao.ParametroDao.consultarParametroValor",filtro);
    }

}