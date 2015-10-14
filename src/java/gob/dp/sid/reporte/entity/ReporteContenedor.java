/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.reporte.entity;

import java.util.List;

/**
 *
 * @author carlos
 */
public class ReporteContenedor {
    
    private String titulo;
    
    private String fecha;
    
    private List<Reporte> reportes;
    
    private List<Reporte001> reporte001s;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(List<Reporte> reportes) {
        this.reportes = reportes;
    }

    public List<Reporte001> getReporte001s() {
        return reporte001s;
    }

    public void setReporte001s(List<Reporte001> reporte001s) {
        this.reporte001s = reporte001s;
    }
    
    
}
