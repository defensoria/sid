/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.comun.dao;

import gob.dp.sid.comun.entity.Menu;
import java.util.List;
import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class MenuDaoImpl extends SqlSessionDaoSupport implements MenuDao{
    
    private static final Logger log = Logger.getLogger(MenuDaoImpl.class);

    @Override
    public List<Menu> menuPadre() {
        log.debug("METODO : MenuDaoImpl.menuPadre");
        return getSqlSession().selectList("gob.dp.sid.comun.dao.MenuDao.menuPadre");
    }

    @Override
    public List<Menu> menuHijo(int padre) {
        log.debug("METODO : MenuDaoImpl.menuHijo");
        return getSqlSession().selectList("gob.dp.sid.comun.dao.MenuDao.menuHijo", padre);
    }
    
}
