/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.reporte.service;

import gob.dp.sid.reporte.dao.ReporteSidExpedienteDAO;
import gob.dp.sid.reporte.entity.ReporteSidConteo;
import gob.dp.sid.reporte.entity.ReporteSidExpediente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ReporteSidExpedienteServiceImpl implements ReporteSidExpedienteService{

    @Autowired
    private ReporteSidExpedienteDAO reporteSidExpedienteDAO;
    
    @Override
    public List<ReporteSidExpediente> listaExpedienteReporte(ReporteSidExpediente reporteSidExpediente) {
        return reporteSidExpedienteDAO.listaExpedienteReporte(reporteSidExpediente);
    }
    
    @Override
    public List<ReporteSidExpediente> listaGestionReporte(ReporteSidExpediente reporteSidExpediente) {
        return reporteSidExpedienteDAO.listaGestionReporte(reporteSidExpediente);
    }

    @Override
    public List<ReporteSidConteo> listaExpedienteReporteClasificacion(ReporteSidExpediente reporteSidExpediente) {
        return reporteSidExpedienteDAO.listaExpedienteReporteClasificacion(reporteSidExpediente);
    }

    @Override
    public List<ReporteSidExpediente> listaExpedienteReporteRecurrente(ReporteSidExpediente reporteSidExpediente) {
        return reporteSidExpedienteDAO.listaExpedienteReporteRecurrente(reporteSidExpediente);
    }

    @Override
    public List<ReporteSidExpediente> listaExpedienteReporteAfectado(ReporteSidExpediente reporteSidExpediente) {
        return reporteSidExpedienteDAO.listaExpedienteReporteAfectado(reporteSidExpediente);
    }

    @Override
    public List<ReporteSidExpediente> listaExpedienteReporteExport(ReporteSidExpediente reporteSidExpediente) {
        return reporteSidExpedienteDAO.listaExpedienteReporteExport(reporteSidExpediente);
    }

    @Override
    public List<ReporteSidConteo> listaEntidadReporte(ReporteSidExpediente reporteSidExpediente) {
        return reporteSidExpedienteDAO.listaEntidadReporte(reporteSidExpediente);
    }

    @Override
    public List<ReporteSidConteo> listaExpedienteReporteComisionado(ReporteSidExpediente reporteSidExpediente) {
        return reporteSidExpedienteDAO.listaExpedienteReporteComisionado(reporteSidExpediente);
    }

    @Override
    public List<ReporteSidConteo> listaExpedienteReporteDependencia(ReporteSidExpediente reporteSidExpediente) {
        return reporteSidExpedienteDAO.listaExpedienteReporteDependencia(reporteSidExpediente);
    }

    @Override
    public List<ReporteSidExpediente> listaExpedienteReporteGestionInterna(ReporteSidExpediente reporteSidExpediente) {
        return reporteSidExpedienteDAO.listaExpedienteReporteGestionInterna(reporteSidExpediente);
    }

    @Override
    public List<ReporteSidExpediente> listaGestionReporteExport(ReporteSidExpediente reporteSidExpediente) {
        return reporteSidExpedienteDAO.listaGestionReporteExport(reporteSidExpediente);
    }

    @Override
    public List<ReporteSidConteo> listaEntidadReporteExport(ReporteSidExpediente reporteSidExpediente) {
        return reporteSidExpedienteDAO.listaEntidadReporteExport(reporteSidExpediente);
    }

    @Override
    public List<ReporteSidConteo> listaExpedienteReporteClasificacionExport(ReporteSidExpediente reporteSidExpediente) {
        return reporteSidExpedienteDAO.listaExpedienteReporteClasificacionExport(reporteSidExpediente);
    }

    @Override
    public List<ReporteSidConteo> listaExpedienteReporteComisionadoExport(ReporteSidExpediente reporteSidExpediente) {
        return reporteSidExpedienteDAO.listaExpedienteReporteComisionadoExport(reporteSidExpediente);
    }

    @Override
    public List<ReporteSidConteo> listaExpedienteReporteDependenciaExport(ReporteSidExpediente reporteSidExpediente) {
        return reporteSidExpedienteDAO.listaExpedienteReporteDependenciaExport(reporteSidExpediente);
    }

    @Override
    public List<ReporteSidExpediente> listaExpedienteReporteGestionInternaExport(ReporteSidExpediente reporteSidExpediente) {
        return reporteSidExpedienteDAO.listaExpedienteReporteGestionInternaExport(reporteSidExpediente);
    }

    
}
