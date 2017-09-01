/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.reporte.dao;

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
    public List<ReporteSidExpediente> listaExpedienteReporteClasificacion(ReporteSidExpediente reporteSidExpediente) {
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
    
    
    
}
