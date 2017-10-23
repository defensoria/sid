/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.reporte.entity;

import java.io.Serializable;

/**
 *
 * @author carlos
 */

public class ReporteSidConteo implements Serializable{
    
    private Integer totalExpediente;
    
    private Integer totalExpedienteQueja;
    
    private Integer totalExpedienteQuejaTramite;
    
    private Integer totalExpedienteQuejaConcluido;
    
    private Integer totalExpedienteQuejaConcluidoFundado;
    
    private Integer totalExpedienteQuejaConcluidoFundadoAcogido;
    
    private Integer totalExpedienteQuejaConcluidoFundadoAcogidoParcial;
    
    private Integer totalExpedienteQuejaConcluidoFundadoAcogidoParcialAcogido;
    
    private Integer totalExpedienteQuejaConcluidoFundadoAcogidoParcialAcogidoParcial;
    
    private Integer totalExpedienteQuejaConcluidoFundadoAcogidoParcialNoAcogido;
    
    private Integer totalExpedienteQuejaConcluidoFundadoNoAcogidoAcogido;
    
    private Integer totalExpedienteQuejaConcluidoFundadoNoAcogidoAcogidoParcial;
    
    private Integer totalExpedienteQuejaConcluidoFundadoNoAcogidoNoAcogido;
    
    private Integer totalExpedientePetitorio;
    
    private Integer totalExpedientePetitorioTramite;
    
    private Integer totalExpedientePetitorioConcluido;
    
    private Integer totalExpedientePetitorioConcluidoAcogido;
    
    private Integer totalExpedientePetitorioConcluidoNoAcogido;
    
    private Integer totalExpedienteConsulta;
    
    private Integer totalExpedienteConsultaTramite;
    
    private Integer totalExpedienteConsultaConcluido;
    
    private Integer totalGestion;
    
    private Integer totalGestionSi;
    
    private Integer totalGestionNo;
    
    private String entidad;
    
    private String departamento;
    
    private String nombrePrimerNivel;
    
    private String nombreSegundoNivel;
    
    private String nombreTercerNivel;
    
    private String nombreCuartoNivel;
    
    private String nombreQuintoNivel;
    
    private String nombreSextoNivel;
    
    private String nombreComsionado;
    
    private String apellidoPaternoComisionado;
    
    private String apellidoMaternoComisionado;
    
    private String codigoComisionado;
    
    private String nombreAdjuntia;

    public Integer getTotalExpediente() {
        return totalExpediente;
    }

    public void setTotalExpediente(Integer totalExpediente) {
        this.totalExpediente = totalExpediente;
    }

    public Integer getTotalExpedienteQueja() {
        return totalExpedienteQueja;
    }

    public void setTotalExpedienteQueja(Integer totalExpedienteQueja) {
        this.totalExpedienteQueja = totalExpedienteQueja;
    }

    public Integer getTotalExpedienteQuejaTramite() {
        return totalExpedienteQuejaTramite;
    }

    public void setTotalExpedienteQuejaTramite(Integer totalExpedienteQuejaTramite) {
        this.totalExpedienteQuejaTramite = totalExpedienteQuejaTramite;
    }

    public Integer getTotalExpedienteQuejaConcluido() {
        return totalExpedienteQuejaConcluido;
    }

    public void setTotalExpedienteQuejaConcluido(Integer totalExpedienteQuejaConcluido) {
        this.totalExpedienteQuejaConcluido = totalExpedienteQuejaConcluido;
    }

    public Integer getTotalExpedienteQuejaConcluidoFundado() {
        return totalExpedienteQuejaConcluidoFundado;
    }

    public void setTotalExpedienteQuejaConcluidoFundado(Integer totalExpedienteQuejaConcluidoFundado) {
        this.totalExpedienteQuejaConcluidoFundado = totalExpedienteQuejaConcluidoFundado;
    }

    public Integer getTotalExpedienteQuejaConcluidoFundadoAcogido() {
        return totalExpedienteQuejaConcluidoFundadoAcogido;
    }

    public void setTotalExpedienteQuejaConcluidoFundadoAcogido(Integer totalExpedienteQuejaConcluidoFundadoAcogido) {
        this.totalExpedienteQuejaConcluidoFundadoAcogido = totalExpedienteQuejaConcluidoFundadoAcogido;
    }

    public Integer getTotalExpedienteQuejaConcluidoFundadoAcogidoParcial() {
        return totalExpedienteQuejaConcluidoFundadoAcogidoParcial;
    }

    public void setTotalExpedienteQuejaConcluidoFundadoAcogidoParcial(Integer totalExpedienteQuejaConcluidoFundadoAcogidoParcial) {
        this.totalExpedienteQuejaConcluidoFundadoAcogidoParcial = totalExpedienteQuejaConcluidoFundadoAcogidoParcial;
    }

    public Integer getTotalExpedienteQuejaConcluidoFundadoAcogidoParcialAcogido() {
        return totalExpedienteQuejaConcluidoFundadoAcogidoParcialAcogido;
    }

    public void setTotalExpedienteQuejaConcluidoFundadoAcogidoParcialAcogido(Integer totalExpedienteQuejaConcluidoFundadoAcogidoParcialAcogido) {
        this.totalExpedienteQuejaConcluidoFundadoAcogidoParcialAcogido = totalExpedienteQuejaConcluidoFundadoAcogidoParcialAcogido;
    }

    public Integer getTotalExpedienteQuejaConcluidoFundadoAcogidoParcialAcogidoParcial() {
        return totalExpedienteQuejaConcluidoFundadoAcogidoParcialAcogidoParcial;
    }

    public void setTotalExpedienteQuejaConcluidoFundadoAcogidoParcialAcogidoParcial(Integer totalExpedienteQuejaConcluidoFundadoAcogidoParcialAcogidoParcial) {
        this.totalExpedienteQuejaConcluidoFundadoAcogidoParcialAcogidoParcial = totalExpedienteQuejaConcluidoFundadoAcogidoParcialAcogidoParcial;
    }

    public Integer getTotalExpedienteQuejaConcluidoFundadoAcogidoParcialNoAcogido() {
        return totalExpedienteQuejaConcluidoFundadoAcogidoParcialNoAcogido;
    }

    public void setTotalExpedienteQuejaConcluidoFundadoAcogidoParcialNoAcogido(Integer totalExpedienteQuejaConcluidoFundadoAcogidoParcialNoAcogido) {
        this.totalExpedienteQuejaConcluidoFundadoAcogidoParcialNoAcogido = totalExpedienteQuejaConcluidoFundadoAcogidoParcialNoAcogido;
    }

    public Integer getTotalExpedienteQuejaConcluidoFundadoNoAcogidoAcogido() {
        return totalExpedienteQuejaConcluidoFundadoNoAcogidoAcogido;
    }

    public void setTotalExpedienteQuejaConcluidoFundadoNoAcogidoAcogido(Integer totalExpedienteQuejaConcluidoFundadoNoAcogidoAcogido) {
        this.totalExpedienteQuejaConcluidoFundadoNoAcogidoAcogido = totalExpedienteQuejaConcluidoFundadoNoAcogidoAcogido;
    }

    public Integer getTotalExpedienteQuejaConcluidoFundadoNoAcogidoAcogidoParcial() {
        return totalExpedienteQuejaConcluidoFundadoNoAcogidoAcogidoParcial;
    }

    public void setTotalExpedienteQuejaConcluidoFundadoNoAcogidoAcogidoParcial(Integer totalExpedienteQuejaConcluidoFundadoNoAcogidoAcogidoParcial) {
        this.totalExpedienteQuejaConcluidoFundadoNoAcogidoAcogidoParcial = totalExpedienteQuejaConcluidoFundadoNoAcogidoAcogidoParcial;
    }

    public Integer getTotalExpedienteQuejaConcluidoFundadoNoAcogidoNoAcogido() {
        return totalExpedienteQuejaConcluidoFundadoNoAcogidoNoAcogido;
    }

    public void setTotalExpedienteQuejaConcluidoFundadoNoAcogidoNoAcogido(Integer totalExpedienteQuejaConcluidoFundadoNoAcogidoNoAcogido) {
        this.totalExpedienteQuejaConcluidoFundadoNoAcogidoNoAcogido = totalExpedienteQuejaConcluidoFundadoNoAcogidoNoAcogido;
    }

    public Integer getTotalExpedientePetitorio() {
        return totalExpedientePetitorio;
    }

    public void setTotalExpedientePetitorio(Integer totalExpedientePetitorio) {
        this.totalExpedientePetitorio = totalExpedientePetitorio;
    }

    public Integer getTotalExpedientePetitorioTramite() {
        return totalExpedientePetitorioTramite;
    }

    public void setTotalExpedientePetitorioTramite(Integer totalExpedientePetitorioTramite) {
        this.totalExpedientePetitorioTramite = totalExpedientePetitorioTramite;
    }

    public Integer getTotalExpedientePetitorioConcluido() {
        return totalExpedientePetitorioConcluido;
    }

    public void setTotalExpedientePetitorioConcluido(Integer totalExpedientePetitorioConcluido) {
        this.totalExpedientePetitorioConcluido = totalExpedientePetitorioConcluido;
    }

    public Integer getTotalExpedientePetitorioConcluidoAcogido() {
        return totalExpedientePetitorioConcluidoAcogido;
    }

    public void setTotalExpedientePetitorioConcluidoAcogido(Integer totalExpedientePetitorioConcluidoAcogido) {
        this.totalExpedientePetitorioConcluidoAcogido = totalExpedientePetitorioConcluidoAcogido;
    }

    public Integer getTotalExpedientePetitorioConcluidoNoAcogido() {
        return totalExpedientePetitorioConcluidoNoAcogido;
    }

    public void setTotalExpedientePetitorioConcluidoNoAcogido(Integer totalExpedientePetitorioConcluidoNoAcogido) {
        this.totalExpedientePetitorioConcluidoNoAcogido = totalExpedientePetitorioConcluidoNoAcogido;
    }

    

    public Integer getTotalGestion() {
        return totalGestion;
    }

    public void setTotalGestion(Integer totalGestion) {
        this.totalGestion = totalGestion;
    }

    public Integer getTotalGestionSi() {
        return totalGestionSi;
    }

    public void setTotalGestionSi(Integer totalGestionSi) {
        this.totalGestionSi = totalGestionSi;
    }

    public Integer getTotalGestionNo() {
        return totalGestionNo;
    }

    public void setTotalGestionNo(Integer totalGestionNo) {
        this.totalGestionNo = totalGestionNo;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getNombrePrimerNivel() {
        return nombrePrimerNivel;
    }

    public void setNombrePrimerNivel(String nombrePrimerNivel) {
        this.nombrePrimerNivel = nombrePrimerNivel;
    }

    public String getNombreSegundoNivel() {
        return nombreSegundoNivel;
    }

    public void setNombreSegundoNivel(String nombreSegundoNivel) {
        this.nombreSegundoNivel = nombreSegundoNivel;
    }

    public String getNombreTercerNivel() {
        return nombreTercerNivel;
    }

    public void setNombreTercerNivel(String nombreTercerNivel) {
        this.nombreTercerNivel = nombreTercerNivel;
    }

    public String getNombreCuartoNivel() {
        return nombreCuartoNivel;
    }

    public void setNombreCuartoNivel(String nombreCuartoNivel) {
        this.nombreCuartoNivel = nombreCuartoNivel;
    }

    public String getNombreQuintoNivel() {
        return nombreQuintoNivel;
    }

    public void setNombreQuintoNivel(String nombreQuintoNivel) {
        this.nombreQuintoNivel = nombreQuintoNivel;
    }

    public String getNombreSextoNivel() {
        return nombreSextoNivel;
    }

    public void setNombreSextoNivel(String nombreSextoNivel) {
        this.nombreSextoNivel = nombreSextoNivel;
    }

    public Integer getTotalExpedienteConsulta() {
        return totalExpedienteConsulta;
    }

    public void setTotalExpedienteConsulta(Integer totalExpedienteConsulta) {
        this.totalExpedienteConsulta = totalExpedienteConsulta;
    }

    public Integer getTotalExpedienteConsultaTramite() {
        return totalExpedienteConsultaTramite;
    }

    public void setTotalExpedienteConsultaTramite(Integer totalExpedienteConsultaTramite) {
        this.totalExpedienteConsultaTramite = totalExpedienteConsultaTramite;
    }

    public Integer getTotalExpedienteConsultaConcluido() {
        return totalExpedienteConsultaConcluido;
    }

    public void setTotalExpedienteConsultaConcluido(Integer totalExpedienteConsultaConcluido) {
        this.totalExpedienteConsultaConcluido = totalExpedienteConsultaConcluido;
    }

    public String getNombreComsionado() {
        return nombreComsionado;
    }

    public void setNombreComsionado(String nombreComsionado) {
        this.nombreComsionado = nombreComsionado;
    }

    public String getApellidoPaternoComisionado() {
        return apellidoPaternoComisionado;
    }

    public void setApellidoPaternoComisionado(String apellidoPaternoComisionado) {
        this.apellidoPaternoComisionado = apellidoPaternoComisionado;
    }

    public String getApellidoMaternoComisionado() {
        return apellidoMaternoComisionado;
    }

    public void setApellidoMaternoComisionado(String apellidoMaternoComisionado) {
        this.apellidoMaternoComisionado = apellidoMaternoComisionado;
    }

    public String getCodigoComisionado() {
        return codigoComisionado;
    }

    public void setCodigoComisionado(String codigoComisionado) {
        this.codigoComisionado = codigoComisionado;
    }

    public String getNombreAdjuntia() {
        return nombreAdjuntia;
    }

    public void setNombreAdjuntia(String nombreAdjuntia) {
        this.nombreAdjuntia = nombreAdjuntia;
    }
    
}



