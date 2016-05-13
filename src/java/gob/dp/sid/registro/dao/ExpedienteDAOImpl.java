/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.Expediente;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ExpedienteDAOImpl extends SqlSessionDaoSupport implements ExpedienteDAO{

    @Override
    public void expedienteInsertar(Expediente expediente) {
        getSqlSession().insert("gob.dp.sid.registro.dao.ExpedienteDAO.expedienteInsertar", expediente);
    }

    @Override
    public void expedienteUpdate(Expediente expediente) {
        getSqlSession().update("gob.dp.sid.registro.dao.ExpedienteDAO.expedienteUpdate", expediente);
    }

    @Override
    public List<Expediente> expedienteBuscar(Expediente expediente) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.ExpedienteDAO.expedienteBuscar", expediente);
    }

    @Override
    public List<Expediente> expedienteBuscarUsuario(String codigo) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.ExpedienteDAO.expedienteBuscarUsuario", codigo);
    }

    @Override
    public void expedienteConcluir(long idExpediente) {
        getSqlSession().update("gob.dp.sid.registro.dao.ExpedienteDAO.expedienteConcluir", idExpediente);
    }

    @Override
    public List<Expediente> expedienteBuscarUsuarioPaginado(Expediente expediente) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.ExpedienteDAO.expedienteBuscarUsuarioPaginado", expediente);
    }

    @Override
    public List<Expediente> expedienteReporteMesUsuario(Expediente expediente) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.ExpedienteDAO.expedienteReporteMesUsuario", expediente);
    }

    @Override
    public List<Expediente> expedientexPersona(long idPersona) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.ExpedienteDAO.expedientexPersona", idPersona);
    }

    @Override
    public List<Expediente> expedienteBuscarUsuarios(Expediente expediente) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.ExpedienteDAO.expedienteBuscarUsuarios", expediente);
    }

    @Override
    public Expediente expedienteBuscarActivo(Expediente expediente) {
        return getSqlSession().selectOne("gob.dp.sid.registro.dao.ExpedienteDAO.expedienteBuscarActivo", expediente);
    }

    @Override
    public Expediente expedienteBuscarActivoEtapa(Expediente expediente) {
        return getSqlSession().selectOne("gob.dp.sid.registro.dao.ExpedienteDAO.expedienteBuscarActivoEtapa", expediente);
    }

    @Override
    public Expediente expedienteBuscarPorId(Long idExpediente) {
        return getSqlSession().selectOne("gob.dp.sid.registro.dao.ExpedienteDAO.expedienteBuscarPorId", idExpediente);
    }

    @Override
    public Long expedienteCodigoPorOD(Long codigoOD) {
        return getSqlSession().selectOne("gob.dp.sid.registro.dao.ExpedienteDAO.expedienteCodigoPorOD", codigoOD);
    }

    @Override
    public Expediente expedienteBuscarPorNumero(String numeroExpediente) {
        return getSqlSession().selectOne("gob.dp.sid.registro.dao.ExpedienteDAO.expedienteBuscarPorNumero", numeroExpediente);
    }

    @Override
    public void expedienteAsignar(Expediente expediente) {
        getSqlSession().update("gob.dp.sid.registro.dao.ExpedienteDAO.expedienteAsignar", expediente);
    }

    @Override
    public void expedienteDesistir(Expediente expediente) {
        getSqlSession().update("gob.dp.sid.registro.dao.ExpedienteDAO.expedienteDesistir", expediente);
    }

    @Override
    public List<Expediente> expedienteBuscarUsuarioCompleto(Expediente expediente) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.ExpedienteDAO.expedienteBuscarUsuarioCompleto", expediente);
    }

    @Override
    public List<Expediente> expedienteBuscarUsuarioODPaginado(Expediente expediente) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.ExpedienteDAO.expedienteBuscarUsuarioODPaginado", expediente);
    }

    @Override
    public void expedienteUpdateNoClasificacion(Expediente expediente) {
        getSqlSession().update("gob.dp.sid.registro.dao.ExpedienteDAO.expedienteUpdateNoClasificacion", expediente);
    }

    @Override
    public Integer expedienteMayorInformacionCount(String numeroExpediente) {
        return getSqlSession().selectOne("gob.dp.sid.registro.dao.ExpedienteDAO.expedienteMayorInformacionCount", numeroExpediente);
    }

    @Override
    public void expedienteInactivar(Expediente expediente) {
        getSqlSession().update("gob.dp.sid.registro.dao.ExpedienteDAO.expedienteInactivar", expediente);
    }

    @Override
    public void expedienteReconsideracion(Expediente expediente) {
        getSqlSession().update("gob.dp.sid.registro.dao.ExpedienteDAO.expedienteReconsideracion", expediente);
    }

    @Override
    public void expedienteUpdateArchivo(Expediente expediente) {
        getSqlSession().update("gob.dp.sid.registro.dao.ExpedienteDAO.expedienteUpdateArchivo", expediente);
    }

    @Override
    public void expedienteEliminarArchivo(long idExpediente) {
        getSqlSession().update("gob.dp.sid.registro.dao.ExpedienteDAO.expedienteEliminarArchivo", idExpediente);
    }
    
}
