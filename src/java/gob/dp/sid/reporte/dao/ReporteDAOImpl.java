/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.reporte.dao;

import gob.dp.sid.registro.entity.Expediente;
import gob.dp.sid.reporte.dao.ReporteDAO;
import gob.dp.sid.reporte.entity.Reporte;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ReporteDAOImpl  extends SqlSessionDaoSupport implements ReporteDAO{

    @Override
    public List<Expediente> reporte001(Reporte reporte) {
        return getSqlSession().selectList("gob.dp.sid.reporte.dao.ReporteDAO.reporte001", reporte);
    }
    
}
