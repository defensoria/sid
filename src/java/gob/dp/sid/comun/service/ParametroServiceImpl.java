/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.sid.comun.service;

import gob.dp.sid.comun.dao.ParametroDao;
import gob.dp.sid.comun.entity.FiltroParametro;
import gob.dp.sid.comun.entity.Parametro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrador
 */

@Service
public class ParametroServiceImpl implements ParametroService {

    @Autowired
    private ParametroDao parametroDao;

    @Override
    public Parametro consultarParametro(FiltroParametro filtro) {
        return parametroDao.consultarParametro(filtro);
    }

    @Override
    public List<Parametro> buscarParametro(FiltroParametro filtro) {
        return parametroDao.buscarParametro(filtro);
    }

    @Override
    public void crearParametro(Parametro obj) {
        parametroDao.crearParametro(obj);
    }

    @Override
    public void modificarParametro(Parametro obj) {
        parametroDao.modificarParametro(obj);
    }

    @Override
    public void eliminarParametro(FiltroParametro filtro) {
        parametroDao.eliminarParametro(filtro);
    }

    @Override
    public Parametro consultarParametroValor(FiltroParametro filtro) {
        return parametroDao.consultarParametroValor(filtro);
    }

}
