/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.reporte.dao;

import gob.dp.sid.reporte.entity.ReporteSidConteo;
import gob.dp.sid.reporte.entity.ReporteSidExpediente;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ReporteSidExpedienteDAOImpl extends SqlSessionDaoSupport implements ReporteSidExpedienteDAO {

    @Override
    public List<ReporteSidExpediente> listaExpedienteReporte(ReporteSidExpediente reporteSidExpediente) {
        return getSqlSession().selectList("gob.dp.sid.reporte.dao.ReporteSidExpedienteDAO.listaExpedienteReporte", reporteSidExpediente);
    }

    @Override
    public List<ReporteSidConteo> listaExpedienteReporteClasificacion(ReporteSidExpediente reporteSidExpediente) {
        return getSqlSession().selectList("gob.dp.sid.reporte.dao.ReporteSidExpedienteDAO.listaExpedienteReporteClasificacion", reporteSidExpediente);
    }

    @Override
    public List<ReporteSidExpediente> listaExpedienteReporteRecurrente(ReporteSidExpediente reporteSidExpediente) {
        return getSqlSession().selectList("gob.dp.sid.reporte.dao.ReporteSidExpedienteDAO.listaExpedienteReporteRecurrente", reporteSidExpediente);
    }

    @Override
    public List<ReporteSidExpediente> listaExpedienteReporteAfectado(ReporteSidExpediente reporteSidExpediente) {
        return getSqlSession().selectList("gob.dp.sid.reporte.dao.ReporteSidExpedienteDAO.listaExpedienteReporteAfectado", reporteSidExpediente);
    }

    @Override
    public List<ReporteSidExpediente> listaExpedienteReporteExport(ReporteSidExpediente reporteSidExpediente) {
        return getSqlSession().selectList("gob.dp.sid.reporte.dao.ReporteSidExpedienteDAO.listaExpedienteReporteExport", reporteSidExpediente);
    }

    @Override
    public List<ReporteSidExpediente> listaGestionReporte(ReporteSidExpediente reporteSidExpediente) {
        return getSqlSession().selectList("gob.dp.sid.reporte.dao.ReporteSidExpedienteDAO.listaGestionReporte", reporteSidExpediente);
    }

    @Override
    public List<ReporteSidConteo> listaEntidadReporte(ReporteSidExpediente reporteSidExpediente) {
        return getSqlSession().selectList("gob.dp.sid.reporte.dao.ReporteSidExpedienteDAO.listaEntidadReporte", reporteSidExpediente);
    }

    @Override
    public List<ReporteSidConteo> listaExpedienteReporteComisionado(ReporteSidExpediente reporteSidExpediente) {
        return getSqlSession().selectList("gob.dp.sid.reporte.dao.ReporteSidExpedienteDAO.listaExpedienteReporteComisionado", reporteSidExpediente);
    }

    @Override
    public List<ReporteSidConteo> listaExpedienteReporteDependencia(ReporteSidExpediente reporteSidExpediente) {
        return getSqlSession().selectList("gob.dp.sid.reporte.dao.ReporteSidExpedienteDAO.listaExpedienteReporteDependencia", reporteSidExpediente);
    }

    @Override
    public List<ReporteSidExpediente> listaExpedienteReporteGestionInterna(ReporteSidExpediente reporteSidExpediente) {
        return getSqlSession().selectList("gob.dp.sid.reporte.dao.ReporteSidExpedienteDAO.listaExpedienteReporteGestionInterna", reporteSidExpediente);
    }

    @Override
    public List<ReporteSidExpediente> listaGestionReporteExport(ReporteSidExpediente reporteSidExpediente) {
        return getSqlSession().selectList("gob.dp.sid.reporte.dao.ReporteSidExpedienteDAO.listaGestionReporteExport", reporteSidExpediente);
    }

    @Override
    public List<ReporteSidConteo> listaEntidadReporteExport(ReporteSidExpediente reporteSidExpediente) {
        return getSqlSession().selectList("gob.dp.sid.reporte.dao.ReporteSidExpedienteDAO.listaEntidadReporteExport", reporteSidExpediente);
    }

    @Override
    public List<ReporteSidConteo> listaExpedienteReporteClasificacionExport(ReporteSidExpediente reporteSidExpediente) {
        return getSqlSession().selectList("gob.dp.sid.reporte.dao.ReporteSidExpedienteDAO.listaExpedienteReporteClasificacionExport", reporteSidExpediente);
    }

    @Override
    public List<ReporteSidConteo> listaExpedienteReporteComisionadoExport(ReporteSidExpediente reporteSidExpediente) {
        return getSqlSession().selectList("gob.dp.sid.reporte.dao.ReporteSidExpedienteDAO.listaExpedienteReporteComisionadoExport", reporteSidExpediente);
    }

    @Override
    public List<ReporteSidConteo> listaExpedienteReporteDependenciaExport(ReporteSidExpediente reporteSidExpediente) {
        return getSqlSession().selectList("gob.dp.sid.reporte.dao.ReporteSidExpedienteDAO.listaExpedienteReporteDependenciaExport", reporteSidExpediente);
    }

    @Override
    public List<ReporteSidExpediente> listaExpedienteReporteGestionInternaExport(ReporteSidExpediente reporteSidExpediente) {
        return getSqlSession().selectList("gob.dp.sid.reporte.dao.ReporteSidExpedienteDAO.listaExpedienteReporteGestionInternaExport", reporteSidExpediente);
    }
    
    
    
}
