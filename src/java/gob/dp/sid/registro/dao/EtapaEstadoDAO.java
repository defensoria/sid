/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.registro.dao;

import gob.dp.sid.registro.entity.EtapaEstado;
import java.util.List;

/**
 *
 * @author carlos
 */
public interface EtapaEstadoDAO {
    
    public void etapaEstadoInsertar(EtapaEstado etapaEstado);
    
    public void etapaEstadoUpdate(EtapaEstado etapaEstado);
    
    public EtapaEstado etapaEstadoVigente(long idExpediente);
    
    public EtapaEstado etapaEstadoInicial(long idExpediente);
    
    public List<EtapaEstado> etapaEstadoxExpediente(String numeroExpediente);
    
    public Long etapaEstadoUltimoEstado(String numeroExpediente);
    
    public EtapaEstado etapaEstadoPorID(long idEtapaEstado);
}
