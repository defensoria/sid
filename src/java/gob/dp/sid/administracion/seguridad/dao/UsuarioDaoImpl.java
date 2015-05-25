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
    public void insertarUsuario(Usuario usuario) throws Exception {
        getSqlSession().insert("usuarioDao.insertarUsuario", usuario);
    }

    @Override
    public void modificarUsuario(Usuario usuario) throws Exception {

        log.debug("Nombre:" + usuario.getNombre());
        log.debug("Ap paterno:" + usuario.getApellidoPaterno());
        log.debug("Ap materno:" + usuario.getApellidoMaterno());
        log.debug("email:" + usuario.getEmail());
        log.debug("telefono fijo:" + usuario.getTelefonoFijo());
        log.debug("telefono movil:" + usuario.getTelefonoMovil());
        log.debug("estado:" + usuario.getEstado());
        log.debug("clave:" + usuario.getClave());
        log.debug("dni:" + usuario.getDni());
        log.debug("codigo:" + usuario.getCodigo());

        getSqlSession().update("usuarioDao.modificarUsuario", usuario);
    }

    @Override
    public void cambiarClaveUsuario(Usuario usuario) throws Exception {
        getSqlSession().update("usuarioDao.cambiarClaveUsuario", usuario);
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
}
