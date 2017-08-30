/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.Persona;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public class PersonaDAOImpl extends SqlSessionDaoSupport implements PersonaDAO{

    @Override
    public void personaInsertar(Persona persona) {
        getSqlSession().insert("gob.dp.sid.registro.dao.PersonaDAO.personaInsertar", persona);
    }

    @Override
    public void personaUpdate(Persona persona) {
        getSqlSession().update("gob.dp.sid.registro.dao.PersonaDAO.personaUpdate", persona);
    }

    @Override
    public List<Persona> personaBuscarCadena(Persona persona) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.PersonaDAO.personaBuscarCadena", persona);
    }

    @Override
    public List<Persona> personaBusarGeneral(Persona persona) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.PersonaDAO.personaBusarGeneral", persona);
    }

    @Override
    public Long personaValidaDNI(String dni) {
        return getSqlSession().selectOne("gob.dp.sid.registro.dao.PersonaDAO.personaValidaDNI", dni);
    }

    @Override
    public Persona personaBusquedaOne(long idPersona) {
        return getSqlSession().selectOne("gob.dp.sid.registro.dao.PersonaDAO.personaBusquedaOne", idPersona);
    }

    @Override
    public Persona personaXDNI(String dni) {
        return getSqlSession().selectOne("gob.dp.sid.registro.dao.PersonaDAO.personaXDNI", dni);
    }

    @Override
    public List<Persona> personaPorExpediente(Long idExpediente) {
        return getSqlSession().selectList("gob.dp.sid.registro.dao.PersonaDAO.personaPorExpediente", idExpediente);
    }
    
}
