/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.reporte.dao;

import gob.dp.sid.registro.entity.Expediente;
import gob.dp.sid.reporte.entity.Reporte;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface ReporteDAO {
    
    public List<Expediente> reporte001(Reporte reporte);
}
