/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.seguridad.dao;
import gob.dp.sid.comun.Auditoria;
import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrador
 */
@Repository
public class AuditoriaDAOImpl  extends SqlSessionDaoSupport implements AuditoriaDAO
{
    private static Logger log = Logger.getLogger(AuditoriaDAOImpl.class);

    @Override
    public void insertarAuditoria(Auditoria filter)throws Exception{  	
        getSqlSession().insert("auditoriaDAO.insertarAuditoria", filter);
    }

}
