/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.bandeja.service;

import gob.dp.sid.bandeja.dao.BandejaDAO;
import gob.dp.sid.bandeja.entity.Bandeja;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlos
 */
@Service
public class BandejaServiceImpl implements BandejaService{
    
    @Autowired
    private BandejaDAO bandejaDAO;

    @Override
    public void bandejaInsertar(Bandeja bandeja) {
        bandejaDAO.bandejaInsertar(bandeja);
    }

    @Override
    public List<Bandeja> bandejaBuscarUsuario(String destinatario) {
        return bandejaDAO.bandejaBuscarUsuario(destinatario);
    }

    @Override
    public void mensajeEstadoVisto(Long id) {
        bandejaDAO.mensajeEstadoVisto(id);
    }

    @Override
    public List<Bandeja> bandejaBuscarUsuarioPendientesInternos(String destinatario) {
        return bandejaDAO.bandejaBuscarUsuarioPendientesInternos(destinatario);
    }

    @Override
    public List<Bandeja> bandejaBuscarUsuarioPendientesAutomaticos(String destinatario) {
        return bandejaDAO.bandejaBuscarUsuarioPendientesAutomaticos(destinatario);
    }

    @Override
    public List<Bandeja> bandejaBuscarUsuarioInternos(String destinatario) {
        return bandejaDAO.bandejaBuscarUsuarioInternos(destinatario);
    }

    @Override
    public List<Bandeja> bandejaBuscarUsuarioAutomaticos(String destinatario) {
        return bandejaDAO.bandejaBuscarUsuarioAutomaticos(destinatario);
    }

    @Override
    public void mensajeInactivar(Long id) {
        bandejaDAO.mensajeInactivar(id);
    }

    @Override
    public List<Bandeja> bandejaBuscarUsuarioPendientesProgramados(String destinatario) {
        return bandejaDAO.bandejaBuscarUsuarioPendientesProgramados(destinatario);
    }

    @Override
    public List<Bandeja> bandejaBuscarUsuarioProgramados(String destinatario) {
        return bandejaDAO.bandejaBuscarUsuarioProgramados(destinatario);
    }
    
}
