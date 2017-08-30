/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.bandeja.dao;

import gob.dp.sid.bandeja.entity.Bandeja;
import gob.dp.sid.bandeja.entity.FiltroBusquedaMensaje;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class BandejaDAOImpl extends SqlSessionDaoSupport implements BandejaDAO{

    @Override
    public void bandejaInsertar(Bandeja bandeja) {
        getSqlSession().insert("gob.dp.sid.bandeja.dao.BandejaDAO.bandejaInsertar", bandeja);
    }

    @Override
    public List<Bandeja> bandejaBuscarUsuario(String destinatario) {
        return getSqlSession().selectList("gob.dp.sid.bandeja.dao.BandejaDAO.bandejaBuscarUsuario", destinatario);
    }

    @Override
    public void mensajeEstadoVisto(Long id) {
        getSqlSession().update("gob.dp.sid.bandeja.dao.BandejaDAO.mensajeEstadoVisto", id);
    }

    @Override
    public List<Bandeja> bandejaBuscarUsuarioPendientesInternos(String destinatario) {
        return getSqlSession().selectList("gob.dp.sid.bandeja.dao.BandejaDAO.bandejaBuscarUsuarioPendientesInternos", destinatario);
    }

    @Override
    public List<Bandeja> bandejaBuscarUsuarioPendientesAutomaticos(String destinatario) {
        return getSqlSession().selectList("gob.dp.sid.bandeja.dao.BandejaDAO.bandejaBuscarUsuarioPendientesAutomaticos", destinatario);
    }

    @Override
    public List<Bandeja> bandejaBuscarUsuarioInternos(FiltroBusquedaMensaje fbm) {
        return getSqlSession().selectList("gob.dp.sid.bandeja.dao.BandejaDAO.bandejaBuscarUsuarioInternos", fbm);
    }

    @Override
    public List<Bandeja> bandejaBuscarUsuarioAutomaticos(FiltroBusquedaMensaje fbm) {
        return getSqlSession().selectList("gob.dp.sid.bandeja.dao.BandejaDAO.bandejaBuscarUsuarioAutomaticos", fbm);
    }

    @Override
    public void mensajeInactivar(Long id) {
        getSqlSession().update("gob.dp.sid.bandeja.dao.BandejaDAO.mensajeInactivar", id);
    }

    @Override
    public List<Bandeja> bandejaBuscarUsuarioPendientesProgramados(String destinatario) {
        return getSqlSession().selectList("gob.dp.sid.bandeja.dao.BandejaDAO.bandejaBuscarUsuarioPendientesProgramados", destinatario);
    }

    @Override
    public List<Bandeja> bandejaBuscarUsuarioProgramados(FiltroBusquedaMensaje fbm) {
        return getSqlSession().selectList("gob.dp.sid.bandeja.dao.BandejaDAO.bandejaBuscarUsuarioProgramados", fbm);
    }

    @Override
    public Long bandejaContarPendientesAutomaticos(String destinatario) {
        return getSqlSession().selectOne("gob.dp.sid.bandeja.dao.BandejaDAO.bandejaContarPendientesAutomaticos", destinatario);
    }

    @Override
    public Long bandejaContarUsuarioInternos(String destinatario) {
        return getSqlSession().selectOne("gob.dp.sid.bandeja.dao.BandejaDAO.bandejaContarUsuarioInternos", destinatario);
    }

    @Override
    public Long bandejaContarUsuarioAutomaticos(String destinatario) {
        return getSqlSession().selectOne("gob.dp.sid.bandeja.dao.BandejaDAO.bandejaContarUsuarioAutomaticos", destinatario);
    }

    @Override
    public Long bandejaContarUsuarioProgramados(String destinatario) {
        return getSqlSession().selectOne("gob.dp.sid.bandeja.dao.BandejaDAO.bandejaContarUsuarioProgramados", destinatario);
    }
    
}
