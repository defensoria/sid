/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.reporte.service;

import gob.dp.sid.reporte.entity.ReporteSidConteo;
import gob.dp.sid.reporte.entity.ReporteSidExpediente;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ReporteSidExpedienteService {
    
    public List<ReporteSidExpediente> listaExpedienteReporte(ReporteSidExpediente reporteSidExpediente);
    
    public List<ReporteSidExpediente> listaGestionReporte(ReporteSidExpediente reporteSidExpediente);
    
    public List<ReporteSidExpediente> listaExpedienteReporteClasificacion(ReporteSidExpediente reporteSidExpediente);
    
    public List<ReporteSidExpediente> listaExpedienteReporteRecurrente(ReporteSidExpediente reporteSidExpediente);
    
    public List<ReporteSidExpediente> listaExpedienteReporteAfectado(ReporteSidExpediente reporteSidExpediente);
    
    public List<ReporteSidExpediente> listaExpedienteReporteExport(ReporteSidExpediente reporteSidExpediente);
    
    public List<ReporteSidConteo> listaEntidadReporte(ReporteSidExpediente reporteSidExpediente);
    
}
