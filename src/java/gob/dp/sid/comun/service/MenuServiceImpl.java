/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.comun.service;

import gob.dp.sid.comun.dao.MenuDao;
import gob.dp.sid.comun.entity.Menu;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class MenuServiceImpl implements MenuService {
    
    private static final Logger log = Logger.getLogger(MenuServiceImpl.class);
    
    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> menuPadre() {
        log.debug("METODO : MenuServiceImpl.menuPadre");
        return menuDao.menuPadre();
    }

    @Override
    public List<Menu> menuHijo(int padre) {
        log.debug("METODO : MenuServiceImpl.menuPadre");
        return menuDao.menuHijo(padre);
    }
    
}
