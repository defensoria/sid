/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.comun.service;

import gob.dp.sid.comun.dao.DepartamentoDao;
import gob.dp.sid.comun.dao.DistritoDao;
import gob.dp.sid.comun.dao.ProvinciaDao;
import gob.dp.sid.comun.entity.Departamento;
import gob.dp.sid.comun.entity.Distrito;
import gob.dp.sid.comun.entity.Provincia;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class UbigeoServiceImpl implements UbigeoService{
    
    private static final Logger log = Logger.getLogger(UbigeoServiceImpl.class);
    
    @Autowired
    private DepartamentoDao departamentoDao;
    
    @Autowired
    private ProvinciaDao provinciaDao;
    
    @Autowired
    private DistritoDao distritoDao;
    
    
    @Override
    public List<Departamento> departamentoLista() {
        log.debug("METODO : UbigeoServiceImpl.departamentoLista");
        return departamentoDao.departamentoLista();
    }

    @Override
    public List<Provincia> provinciaLista(String idDepartamento) {
        log.debug("METODO : UbigeoServiceImpl.provinciaLista");
        return provinciaDao.provinciaLista(idDepartamento);
    }

    @Override
    public List<Distrito> distritoLista(Distrito distrito) {
        log.debug("METODO : UbigeoServiceImpl.distritoLista");
        return distritoDao.distritoLista(distrito);
    }

    @Override
    public Departamento departamentoOne(String idDepartamento) {
        return departamentoDao.departamentoOne(idDepartamento);
    }

    @Override
    public Provincia provinciaOne(Provincia provincia) {
        return provinciaDao.provinciaOne(provincia);
    }

    @Override
    public Distrito distritoOne(Distrito distrito) {
        return distritoDao.distritoOne(distrito);
    }
    
    @Override
    public String arrayDepartamento(){
        StringBuilder sb = new StringBuilder();
        List<Departamento> departamentos= departamentoDao.departamentoLista();
        if(departamentos.size() > 0){
            sb.append("var departamento = new Array()");
            int i = 0;
            for(Departamento departamento : departamentos){
                i++;
                sb.append("departamento["+i+"] = new slctr('"+departamento.getIdDepartamento()+"','"+departamento.getDescripcion()+"') ");
            }
        }
        return sb.toString();
    }
    
}
