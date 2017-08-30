/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.reporte.service;

import gob.dp.sid.reporte.dao.ReporteSidExpedienteDAO;
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
    public List<ReporteSidExpediente> listaExpedienteReporteClasificacion(ReporteSidExpediente reporteSidExpediente) {
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
    
    
    
}
