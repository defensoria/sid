/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.reporte.entity;

import gob.dp.sid.registro.entity.ExpedienteNivel;
import java.util.Date;
import java.util.List;

/**
 *
 * @author carlos
 */
public class ReporteSidExpediente {
    
    private Long idExpediente;
    
    private Date fechaIngresoDesde;
    
    private Date fechaIngresoHasta;
    
    private Date fechaConclusionDesde;
    
    private Date fechaConclusionHasta;
    
    private Date fechaIngreso;
    
    private String tipoExpediente;
    
    private Long codigoEntidad;
    
    private String nombreEntidad;
    
    private Integer idRegion;
    
    private String canalIngreso;
    
    private String canalIngresoDetalle;
    
    private String ODBusqueda;
    
    private String idDepartamentoEntidad;
    
    private String idProvinciaEntidad;
    
    private String idDistritoEntidad;
    
    private String idDepartamentoRecurrente;
    
    private String idProvinciaRecurrente;
    
    private String idDistritoRecurrente;
    
    private String idDepartamentoAfectado;
    
    private String idProvinciaAfectado;
    
    private String idDistritoAfectado;
    
    private String usuarioCodigo;
    
    private String usuarioNombre;
    
    private String grupoVulnerable;
    
    private String idPrimerNivel;
    
    private String idSegundoNivel;
    
    private String idTercerNivel;
    
    private String idCuartoNivel;
    
    private String idQuintoNivel;
    
    private String idSextoNivel;
    
    private String tipoLenguaRecurrente;
    
    private String tipoPuebloRecurrente;
    
    private String esDiscapacitadoRecurrente;
    
    private String tipoLenguaAfectado;
    
    private String tipoPuebloAfectado;
    
    private String esDiscapacitadoAfectado;
    
    private String numeroExpediente;
    
    private Integer ini;
    
    private Integer fin;
    
    private String etapaExpediente;
    
    private Date fechaRegistro;
    
    private String estadoGeneral;
    
    private String estadoRegistro;
    
    private String sumilla;
            
    private String nombreOD;        
    
    private List<StringReport> personasRecurrentes;
    
    private List<StringReport> personasAfectados;

    private List<String> entidades;
    
    private List<String> gruposVulnerables;
    
    private List<ExpedienteNivel> niveles;
    
    private String comisionado;
    
    private String fechaIngresoDesdeString;
    
    private String fechaIngresoHastaString;
    
    private String fechaConclusionDesdeString;
    
    private String fechaConclusionHastaString;
    
    private String estadoCalificacion;
    
    private String estadoInvestigacion;
    
    private String estadoPersuacion;
    
    private String estadoSeguimiento;
    
    private String gruposVulnerablesString;
    
    private String etiquetasString;    
    
    private String tipoGrupoVulnerable;
    
    private String etiqueta;
    
    private String estaEnConsulta;
    
    private String personasRecurrentesString;
    
    private String personasAfectadosString;

    private String entidadesString;
    
    private String codigoGestion;
    
    private Date fechaRecepcionGestion;
    
    private String entidadGestion;
    
    private String descripcionGestion;
    
    private Date fechaRegistroGestion;
    
    private Date fechaModificacionGestion;
    
    private String respuestaGestion;
    
    private Date fechaRespuestaGestion;
    
    private String codigoGestionOrigen;
    
    private String tipoAccion;
    
    private String dependenciaOrigen;
    
    private String dependenciaDestino;
    
    public Date getFechaIngresoDesde() {
        return fechaIngresoDesde;
    }

    public void setFechaIngresoDesde(Date fechaIngresoDesde) {
        this.fechaIngresoDesde = fechaIngresoDesde;
    }

    public Date getFechaIngresoHasta() {
        return fechaIngresoHasta;
    }

    public void setFechaIngresoHasta(Date fechaIngresoHasta) {
        this.fechaIngresoHasta = fechaIngresoHasta;
    }

    public Date getFechaConclusionDesde() {
        return fechaConclusionDesde;
    }

    public void setFechaConclusionDesde(Date fechaConclusionDesde) {
        this.fechaConclusionDesde = fechaConclusionDesde;
    }

    public Date getFechaConclusionHasta() {
        return fechaConclusionHasta;
    }

    public void setFechaConclusionHasta(Date fechaConclusionHasta) {
        this.fechaConclusionHasta = fechaConclusionHasta;
    }
    
    

    public String getTipoExpediente() {
        return tipoExpediente;
    }

    public void setTipoExpediente(String tipoExpediente) {
        this.tipoExpediente = tipoExpediente;
    }

    public Long getCodigoEntidad() {
        return codigoEntidad;
    }

    public void setCodigoEntidad(Long codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public Integer getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

    public String getCanalIngreso() {
        return canalIngreso;
    }

    public void setCanalIngreso(String canalIngreso) {
        this.canalIngreso = canalIngreso;
    }

    public String getODBusqueda() {
        return ODBusqueda;
    }

    public void setODBusqueda(String ODBusqueda) {
        this.ODBusqueda = ODBusqueda;
    }

    public String getIdDepartamentoEntidad() {
        return idDepartamentoEntidad;
    }

    public void setIdDepartamentoEntidad(String idDepartamentoEntidad) {
        this.idDepartamentoEntidad = idDepartamentoEntidad;
    }

    public String getIdProvinciaEntidad() {
        return idProvinciaEntidad;
    }

    public void setIdProvinciaEntidad(String idProvinciaEntidad) {
        this.idProvinciaEntidad = idProvinciaEntidad;
    }

    public String getIdDistritoEntidad() {
        return idDistritoEntidad;
    }

    public void setIdDistritoEntidad(String idDistritoEntidad) {
        this.idDistritoEntidad = idDistritoEntidad;
    }

    public String getIdDepartamentoRecurrente() {
        return idDepartamentoRecurrente;
    }

    public void setIdDepartamentoRecurrente(String idDepartamentoRecurrente) {
        this.idDepartamentoRecurrente = idDepartamentoRecurrente;
    }

    public String getIdProvinciaRecurrente() {
        return idProvinciaRecurrente;
    }

    public void setIdProvinciaRecurrente(String idProvinciaRecurrente) {
        this.idProvinciaRecurrente = idProvinciaRecurrente;
    }

    public String getIdDistritoRecurrente() {
        return idDistritoRecurrente;
    }

    public void setIdDistritoRecurrente(String idDistritoRecurrente) {
        this.idDistritoRecurrente = idDistritoRecurrente;
    }

    public String getIdDepartamentoAfectado() {
        return idDepartamentoAfectado;
    }

    public void setIdDepartamentoAfectado(String idDepartamentoAfectado) {
        this.idDepartamentoAfectado = idDepartamentoAfectado;
    }

    public String getIdProvinciaAfectado() {
        return idProvinciaAfectado;
    }

    public void setIdProvinciaAfectado(String idProvinciaAfectado) {
        this.idProvinciaAfectado = idProvinciaAfectado;
    }

    public String getIdDistritoAfectado() {
        return idDistritoAfectado;
    }

    public void setIdDistritoAfectado(String idDistritoAfectado) {
        this.idDistritoAfectado = idDistritoAfectado;
    }

    

    public String getUsuarioCodigo() {
        return usuarioCodigo;
    }

    public void setUsuarioCodigo(String usuarioCodigo) {
        this.usuarioCodigo = usuarioCodigo;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getGrupoVulnerable() {
        return grupoVulnerable;
    }

    public void setGrupoVulnerable(String grupoVulnerable) {
        this.grupoVulnerable = grupoVulnerable;
    }

    public String getIdPrimerNivel() {
        return idPrimerNivel;
    }

    public void setIdPrimerNivel(String idPrimerNivel) {
        this.idPrimerNivel = idPrimerNivel;
    }

    public String getIdSegundoNivel() {
        return idSegundoNivel;
    }

    public void setIdSegundoNivel(String idSegundoNivel) {
        this.idSegundoNivel = idSegundoNivel;
    }

    public String getIdTercerNivel() {
        return idTercerNivel;
    }

    public void setIdTercerNivel(String idTercerNivel) {
        this.idTercerNivel = idTercerNivel;
    }

    public String getIdCuartoNivel() {
        return idCuartoNivel;
    }

    public void setIdCuartoNivel(String idCuartoNivel) {
        this.idCuartoNivel = idCuartoNivel;
    }

    public String getIdQuintoNivel() {
        return idQuintoNivel;
    }

    public void setIdQuintoNivel(String idQuintoNivel) {
        this.idQuintoNivel = idQuintoNivel;
    }

    public String getIdSextoNivel() {
        return idSextoNivel;
    }

    public void setIdSextoNivel(String idSextoNivel) {
        this.idSextoNivel = idSextoNivel;
    }

    public String getTipoLenguaRecurrente() {
        return tipoLenguaRecurrente;
    }

    public void setTipoLenguaRecurrente(String tipoLenguaRecurrente) {
        this.tipoLenguaRecurrente = tipoLenguaRecurrente;
    }

    public String getTipoPuebloRecurrente() {
        return tipoPuebloRecurrente;
    }

    public void setTipoPuebloRecurrente(String tipoPuebloRecurrente) {
        this.tipoPuebloRecurrente = tipoPuebloRecurrente;
    }

    public String getEsDiscapacitadoRecurrente() {
        return esDiscapacitadoRecurrente;
    }

    public void setEsDiscapacitadoRecurrente(String esDiscapacitadoRecurrente) {
        this.esDiscapacitadoRecurrente = esDiscapacitadoRecurrente;
    }

    public String getTipoLenguaAfectado() {
        return tipoLenguaAfectado;
    }

    public void setTipoLenguaAfectado(String tipoLenguaAfectado) {
        this.tipoLenguaAfectado = tipoLenguaAfectado;
    }

    public String getTipoPuebloAfectado() {
        return tipoPuebloAfectado;
    }

    public void setTipoPuebloAfectado(String tipoPuebloAfectado) {
        this.tipoPuebloAfectado = tipoPuebloAfectado;
    }

    public String getEsDiscapacitadoAfectado() {
        return esDiscapacitadoAfectado;
    }

    public void setEsDiscapacitadoAfectado(String esDiscapacitadoAfectado) {
        this.esDiscapacitadoAfectado = esDiscapacitadoAfectado;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public Integer getIni() {
        return ini;
    }

    public void setIni(Integer ini) {
        this.ini = ini;
    }

    public Integer getFin() {
        return fin;
    }

    public void setFin(Integer fin) {
        this.fin = fin;
    }

    public String getEtapaExpediente() {
        return etapaExpediente;
    }

    public void setEtapaExpediente(String etapaExpediente) {
        this.etapaExpediente = etapaExpediente;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstadoGeneral() {
        return estadoGeneral;
    }

    public void setEstadoGeneral(String estadoGeneral) {
        this.estadoGeneral = estadoGeneral;
    }

    public String getSumilla() {
        return sumilla;
    }

    public void setSumilla(String sumilla) {
        this.sumilla = sumilla;
    }

    public String getNombreOD() {
        return nombreOD;
    }

    public void setNombreOD(String nombreOD) {
        this.nombreOD = nombreOD;
    }

    public Long getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(Long idExpediente) {
        this.idExpediente = idExpediente;
    }

    public List<StringReport> getPersonasRecurrentes() {
        return personasRecurrentes;
    }

    public void setPersonasRecurrentes(List<StringReport> personasRecurrentes) {
        this.personasRecurrentes = personasRecurrentes;
    }

    public List<StringReport> getPersonasAfectados() {
        return personasAfectados;
    }

    public void setPersonasAfectados(List<StringReport> personasAfectados) {
        this.personasAfectados = personasAfectados;
    }    

    public List<String> getEntidades() {
        return entidades;
    }

    public void setEntidades(List<String> entidades) {
        this.entidades = entidades;
    }

    public String getComisionado() {
        return comisionado;
    }

    public void setComisionado(String comisionado) {
        this.comisionado = comisionado;
    }

    public List<ExpedienteNivel> getNiveles() {
        return niveles;
    }

    public void setNiveles(List<ExpedienteNivel> niveles) {
        this.niveles = niveles;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaIngresoDesdeString() {
        return fechaIngresoDesdeString;
    }

    public void setFechaIngresoDesdeString(String fechaIngresoDesdeString) {
        this.fechaIngresoDesdeString = fechaIngresoDesdeString;
    }

    public String getFechaIngresoHastaString() {
        return fechaIngresoHastaString;
    }

    public void setFechaIngresoHastaString(String fechaIngresoHastaString) {
        this.fechaIngresoHastaString = fechaIngresoHastaString;
    }

    public String getFechaConclusionDesdeString() {
        return fechaConclusionDesdeString;
    }

    public void setFechaConclusionDesdeString(String fechaConclusionDesdeString) {
        this.fechaConclusionDesdeString = fechaConclusionDesdeString;
    }

    public String getFechaConclusionHastaString() {
        return fechaConclusionHastaString;
    }

    public void setFechaConclusionHastaString(String fechaConclusionHastaString) {
        this.fechaConclusionHastaString = fechaConclusionHastaString;
    }

    public List<String> getGruposVulnerables() {
        return gruposVulnerables;
    }

    public void setGruposVulnerables(List<String> gruposVulnerables) {
        this.gruposVulnerables = gruposVulnerables;
    }

    public String getCanalIngresoDetalle() {
        return canalIngresoDetalle;
    }

    public void setCanalIngresoDetalle(String canalIngresoDetalle) {
        this.canalIngresoDetalle = canalIngresoDetalle;
    }

    public String getEstadoCalificacion() {
        return estadoCalificacion;
    }

    public void setEstadoCalificacion(String estadoCalificacion) {
        this.estadoCalificacion = estadoCalificacion;
    }

    public String getEstadoInvestigacion() {
        return estadoInvestigacion;
    }

    public void setEstadoInvestigacion(String estadoInvestigacion) {
        this.estadoInvestigacion = estadoInvestigacion;
    }

    public String getEstadoPersuacion() {
        return estadoPersuacion;
    }

    public void setEstadoPersuacion(String estadoPersuacion) {
        this.estadoPersuacion = estadoPersuacion;
    }

    public String getEstadoSeguimiento() {
        return estadoSeguimiento;
    }

    public void setEstadoSeguimiento(String estadoSeguimiento) {
        this.estadoSeguimiento = estadoSeguimiento;
    }

    public String getGruposVulnerablesString() {
        return gruposVulnerablesString;
    }

    public void setGruposVulnerablesString(String gruposVulnerablesString) {
        this.gruposVulnerablesString = gruposVulnerablesString;
    }

    public String getEtiquetasString() {
        return etiquetasString;
    }

    public void setEtiquetasString(String etiquetasString) {
        this.etiquetasString = etiquetasString;
    }

    public String getTipoGrupoVulnerable() {
        return tipoGrupoVulnerable;
    }

    public void setTipoGrupoVulnerable(String tipoGrupoVulnerable) {
        this.tipoGrupoVulnerable = tipoGrupoVulnerable;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEstaEnConsulta() {
        return estaEnConsulta;
    }

    public void setEstaEnConsulta(String estaEnConsulta) {
        this.estaEnConsulta = estaEnConsulta;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public String getPersonasRecurrentesString() {
        return personasRecurrentesString;
    }

    public void setPersonasRecurrentesString(String personasRecurrentesString) {
        this.personasRecurrentesString = personasRecurrentesString;
    }

    public String getPersonasAfectadosString() {
        return personasAfectadosString;
    }

    public void setPersonasAfectadosString(String personasAfectadosString) {
        this.personasAfectadosString = personasAfectadosString;
    }

    public String getEntidadesString() {
        return entidadesString;
    }

    public void setEntidadesString(String entidadesString) {
        this.entidadesString = entidadesString;
    }

    public String getCodigoGestion() {
        return codigoGestion;
    }

    public void setCodigoGestion(String codigoGestion) {
        this.codigoGestion = codigoGestion;
    }

    public Date getFechaRecepcionGestion() {
        return fechaRecepcionGestion;
    }

    public void setFechaRecepcionGestion(Date fechaRecepcionGestion) {
        this.fechaRecepcionGestion = fechaRecepcionGestion;
    }

    public String getEntidadGestion() {
        return entidadGestion;
    }

    public void setEntidadGestion(String entidadGestion) {
        this.entidadGestion = entidadGestion;
    }

    public String getDescripcionGestion() {
        return descripcionGestion;
    }

    public void setDescripcionGestion(String descripcionGestion) {
        this.descripcionGestion = descripcionGestion;
    }

    public Date getFechaRegistroGestion() {
        return fechaRegistroGestion;
    }

    public void setFechaRegistroGestion(Date fechaRegistroGestion) {
        this.fechaRegistroGestion = fechaRegistroGestion;
    }

    public Date getFechaModificacionGestion() {
        return fechaModificacionGestion;
    }

    public void setFechaModificacionGestion(Date fechaModificacionGestion) {
        this.fechaModificacionGestion = fechaModificacionGestion;
    }

    public String getRespuestaGestion() {
        return respuestaGestion;
    }

    public void setRespuestaGestion(String respuestaGestion) {
        this.respuestaGestion = respuestaGestion;
    }

    public Date getFechaRespuestaGestion() {
        return fechaRespuestaGestion;
    }

    public void setFechaRespuestaGestion(Date fechaRespuestaGestion) {
        this.fechaRespuestaGestion = fechaRespuestaGestion;
    }

    public String getCodigoGestionOrigen() {
        return codigoGestionOrigen;
    }

    public void setCodigoGestionOrigen(String codigoGestionOrigen) {
        this.codigoGestionOrigen = codigoGestionOrigen;
    }

    public String getDependenciaOrigen() {
        return dependenciaOrigen;
    }

    public void setDependenciaOrigen(String dependenciaOrigen) {
        this.dependenciaOrigen = dependenciaOrigen;
    }

    public String getDependenciaDestino() {
        return dependenciaDestino;
    }

    public void setDependenciaDestino(String dependenciaDestino) {
        this.dependenciaDestino = dependenciaDestino;
    }

    public String getTipoAccion() {
        return tipoAccion;
    }

    public void setTipoAccion(String tipoAccion) {
        this.tipoAccion = tipoAccion;
    }

}
