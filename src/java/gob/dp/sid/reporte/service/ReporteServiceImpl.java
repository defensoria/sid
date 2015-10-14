/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.reporte.service;

import gob.dp.sid.registro.entity.Expediente;
import gob.dp.sid.reporte.dao.ReporteDAO;
import gob.dp.sid.reporte.entity.Reporte;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class ReporteServiceImpl implements ReporteService{
    
    @Autowired
    private ReporteDAO reporteDAO;

    @Override
    public List<Expediente> reporte001(Reporte reporte) {
        return reporteDAO.reporte001(reporte);
    }
    
}
