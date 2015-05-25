/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.comun.dao;

import gob.dp.sid.comun.entity.Menu;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface MenuDao {
    
    public List<Menu> menuPadre();
    
    public List<Menu> menuHijo(int padre);
    
}


