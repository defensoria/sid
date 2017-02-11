/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.seguridad.dao;

import gob.dp.sid.seguridad.bean.FiltroUsuario;
import gob.dp.sid.seguridad.entity.Usuario;
import java.util.List;
import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrador
 */
@Repository
public class UsuarioDAOImpl extends SqlSessionDaoSupport implements UsuarioDAO {

    private static final Logger log = Logger.getLogger(UsuarioDAOImpl.class);

    @Override
    public void insertarUsuario(Usuario usuario){
        getSqlSession().insert("usuarioDAO.insertarUsuario", usuario);
    }

    @Override
    public void modificarUsuario(Usuario usuario){
        getSqlSession().update("usuarioDAO.modificarUsuario", usuario);
    }

    @Override
    public String generarCodigoUsuario() {
        String codigo = getSqlSession().selectOne("usuarioDAO.generarCodigoUsuario");
        log.debug("codigo usuario generado:" + codigo);
        return codigo;
    }

    @Override
    public Integer loginUsuario(Usuario usuario) {
        return (Integer) getSqlSession().selectOne("usuarioDAO.loginUsuario", usuario);
    }

    @Override
    public Usuario consultarUsuario(FiltroUsuario filtro) {
        return getSqlSession().selectOne("usuarioDAO.consultarUsuario", filtro);
    }

    @Override
    public Integer getTotalBuscarUsuario(FiltroUsuario filtro) {
        return getSqlSession().selectOne("usuarioDAO.getTotalBuscarUsuario", filtro);
    }

    @Override
    public List<Usuario> buscarUsuario(FiltroUsuario filtro) {
        return getSqlSession().selectList("usuarioDAO.buscarUsuario", filtro);
    }

    @Override
    public List<Usuario> buscarUsuarioTotal() {
        return getSqlSession().selectList("usuarioDAO.buscarUsuarioTotal");
    }

    @Override
    public List<Usuario> listaUsuariosPorOD(Usuario usuario) {
        return getSqlSession().selectList("usuarioDAO.listaUsuariosPorOD",usuario);
    }

    @Override
    public Integer listaUsuarioCount(String codigoUsuario) {
        return getSqlSession().selectOne("usuarioDAO.listaUsuarioCount",codigoUsuario);
    }
}
