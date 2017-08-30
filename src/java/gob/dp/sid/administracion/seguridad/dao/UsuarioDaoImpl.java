/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.administracion.seguridad.dao;

import gob.dp.sid.administracion.seguridad.bean.FiltroUsuario;
import gob.dp.sid.administracion.seguridad.entity.Usuario;
import java.util.List;
import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrador
 */
@Repository
public class UsuarioDaoImpl extends SqlSessionDaoSupport implements UsuarioDao {

    private static final Logger log = Logger.getLogger(UsuarioDaoImpl.class);

    @Override
    public void insertarUsuario(Usuario usuario){
        getSqlSession().insert("usuarioDao.insertarUsuario", usuario);
    }

    @Override
    public void modificarUsuario(Usuario usuario){
        getSqlSession().update("usuarioDao.modificarUsuario", usuario);
    }

    @Override
    public String generarCodigoUsuario() {
        String codigo = getSqlSession().selectOne("usuarioDao.generarCodigoUsuario");
        log.debug("codigo usuario generado:" + codigo);
        return codigo;
    }

    @Override
    public Integer loginUsuario(Usuario usuario) {
        return (Integer) getSqlSession().selectOne("usuarioDao.loginUsuario", usuario);
    }

    @Override
    public Usuario consultarUsuario(FiltroUsuario filtro) {
        return getSqlSession().selectOne("usuarioDao.consultarUsuario", filtro);
    }

    @Override
    public Integer getTotalBuscarUsuario(FiltroUsuario filtro) {
        return getSqlSession().selectOne("usuarioDao.getTotalBuscarUsuario", filtro);
    }

    @Override
    public List<Usuario> buscarUsuario(FiltroUsuario filtro) {
        return getSqlSession().selectList("usuarioDao.buscarUsuario", filtro);
    }

    @Override
    public List<Usuario> buscarUsuarioTotal() {
        return getSqlSession().selectList("usuarioDao.buscarUsuarioTotal");
    }

    @Override
    public List<Usuario> listaUsuariosPorOD(Usuario usuario) {
        return getSqlSession().selectList("usuarioDao.listaUsuariosPorOD",usuario);
    }

    @Override
    public Integer listaUsuarioCount(String codigoUsuario) {
        return getSqlSession().selectOne("usuarioDao.listaUsuarioCount",codigoUsuario);
    }
}
