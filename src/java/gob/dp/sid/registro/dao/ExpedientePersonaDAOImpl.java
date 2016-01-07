/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.ExpedientePersona;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class ExpedientePersonaDAOImpl extends SqlSessionDaoSupport implements ExpedientePersonaDAO{

    @Override
    public void expedientePersonaInsertar(ExpedientePersona expedientePersona) {
        getSqlSession().insert("gob.dp.sid.registro.dao.ExpedientePersonaDAO.expedientePersonaInsertar", expedientePersona);
    }

    @Override
    public void expedientePersonaUpdate(ExpedientePersona expedientePersona) {
        getSqlSession().update("gob.dp.sid.registro.dao.ExpedientePersonaDAO.expedientePersonaUpdate", expedientePersona);
    }

    @Override
    public void expedientePersonaDelete(ExpedientePersona expedientePersona) {
        getSqlSession().delete("gob.dp.sid.registro.dao.ExpedientePersonaDAO.expedientePersonaDelete", expedientePersona);
    }

    @Override
    public int expedientePersonaContar(ExpedientePersona expedientePersona) {
        return getSqlSession().selectOne("gob.dp.sid.registro.dao.ExpedientePersonaDAO.expedientePersonaContar", expedientePersona);
    }

    @Override
    public List<ExpedientePersona> expedientePersonaBuscarXExpediente(long idExpediente) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.ExpedientePersonaDAO.expedientePersonaBuscarXExpediente", idExpediente);
    }

    @Override
    public void expedienteDatosPersonaUpdate(ExpedientePersona expedientePersona) {
        getSqlSession().update("gob.dp.sid.registro.dao.ExpedientePersonaDAO.expedienteDatosPersonaUpdate", expedientePersona);
    }
    
}
