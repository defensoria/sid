/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.service;

import gob.dp.sid.registro.dao.PersonaDAO;
import gob.dp.sid.registro.entity.Persona;
import gob.dp.sid.reporte.entity.StringReport;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaDAO personaDAO;

    @Override
    public boolean personaInsertar(Persona persona) {
        if (persona.getNumeroDocumento() != null) {
            Long valid = personaValidaDNI(persona.getNumeroDocumento());
            if (valid > 0) {
                return false;
            }
        }
        personaDAO.personaInsertar(persona);
        return true;
    }

    @Override
    public void personaUpdate(Persona persona) {
        personaDAO.personaUpdate(persona);
    }

    @Override
    public List<Persona> personaBuscarCadena(Persona persona) {
        return personaDAO.personaBuscarCadena(persona);
    }

    @Override
    public List<Persona> personaBusarGeneral(Persona persona) {
        return personaDAO.personaBusarGeneral(persona);
    }

    @Override
    public Long personaValidaDNI(String dni) {
        return personaDAO.personaValidaDNI(dni);
    }

    @Override
    public Persona personaBusquedaOne(long idPersona) {
        return personaDAO.personaBusquedaOne(idPersona);
    }

    @Override
    public Persona personaXDNI(String dni) {
        return personaDAO.personaXDNI(dni);
    }

    @Override
    public List<StringReport> personaPorExpedienteRecurrente(Long idExpediente) {
        List<Persona> lista = personaDAO.personaPorExpediente(idExpediente);
        List<StringReport> list = new ArrayList<>();
        for(Persona p : lista){
            StringBuilder sb = new StringBuilder();
            if(StringUtils.equals(p.getTipo(), "01") || StringUtils.equals(p.getTipo(), "03")){
                if(StringUtils.isNotBlank(p.getNombre()))
                    sb.append(p.getNombre()+" ");
                if(StringUtils.isNotBlank(p.getApellidoPat()))
                    sb.append(p.getApellidoPat()+" ");
                if(StringUtils.isNotBlank(p.getApellidoMat()))
                    sb.append(p.getApellidoMat()+" ");
                list.add(new StringReport(sb.toString()));
            }
        }
        return list;
    }

    @Override
    public List<StringReport> personaPorExpedienteAfectado(Long idExpediente) {
        List<Persona> lista = personaDAO.personaPorExpediente(idExpediente);
        List<StringReport> list = new ArrayList<>();
        for(Persona p : lista){
            StringBuilder sb = new StringBuilder();
            if(StringUtils.equals(p.getTipo(), "02") || StringUtils.equals(p.getTipo(), "03")){
                if(StringUtils.isNotBlank(p.getNombre()))
                    sb.append(p.getNombre()+" ");
                if(StringUtils.isNotBlank(p.getApellidoPat()))
                    sb.append(p.getApellidoPat()+" ");
                if(StringUtils.isNotBlank(p.getApellidoMat()))
                    sb.append(p.getApellidoMat()+" ");
                list.add(new StringReport(sb.toString()));
            }
        }
        return list;
    }

}
