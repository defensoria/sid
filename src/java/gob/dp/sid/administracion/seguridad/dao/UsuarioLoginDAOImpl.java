/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.administracion.seguridad.dao;

import gob.dp.sid.administracion.seguridad.entity.UsuarioLogin;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class UsuarioLoginDAOImpl extends SqlSessionDaoSupport implements UsuarioLoginDAO {

    @Override
    public Integer loginUsuario(UsuarioLogin login) {
        return getSqlSession().selectOne("gob.dp.sid.administracion.seguridad.dao.UsuarioLoginDAO.loginUsuario", login);
    }

    @Override
    public List<UsuarioLogin> buscarUsuarios(UsuarioLogin login) {
        return getSqlSession().selectList("gob.dp.sid.administracion.seguridad.dao.UsuarioLoginDAO.buscarUsuarios", login);
    }
    
}
