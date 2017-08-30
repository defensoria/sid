/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.Entidad;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class EntidadDAOImpl extends SqlSessionDaoSupport implements EntidadDAO {

    @Override
    public void entidadInsertar(Entidad entidad) {
        getSqlSession().insert("gob.dp.sid.registro.dao.EntidadDAO.entidadInsertar", entidad);
    }

    @Override
    public void entidadUpdate(Entidad entidad) {
        getSqlSession().insert("gob.dp.sid.registro.dao.EntidadDAO.entidadUpdate", entidad);
    }

    @Override
    public List<Entidad> entidadBuscarCadena(Entidad entidad) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.EntidadDAO.entidadBuscarCadena", entidad);
    }

    @Override
    public List<Entidad> entidadPorExpediente(Long idExpediente) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.EntidadDAO.entidadPorExpediente", idExpediente);
    }
    
}
