package gob.dp.sid.administracion.seguridad.entity;

import gob.dp.sid.comun.StringUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Usuario implements Serializable 
{
    private String codigo;
    
    private String dni;
    
    private String nombre; 
    
    private String apellidoPaterno;  
    
    private String apellidoMaterno;  
    
    private String email;
    
    private String clave;
    
    private String confirmacionClave;
    
    private String estado;
    
    private String telefonoMovil;
    
    private String telefonoFijo;
    
    private String flagClaveInicial;
    
    private String cargo;
    
    private String ruta;
    
    private String sexo;
    
    private Integer nivel;
    
    private String ip; //auditoria
    
    private Integer codigoOD;
    
    private String nombreOD;
    
    private String idDepartamento;
    
    private String nombreDepartamento;
    
    private Integer oficinaDefensorial;
   
    private List<Rol> listaRol;
    private Map<String,Rol> mapRol;
    private Map<String,Recurso> mapRecurso;
    
    StringUtil stringUtil;
    
    //temporales
    private String tipo;
    
    private String codigoRemitente;
    
    private String rol;
    
    private String recurso;

    public Usuario() {
    }

    public Usuario(String codigo) {
        this.codigo = codigo;
    }
    
    
    
    public String getCodigoConNombreCompleto()
    {
    	String str="";
    	if (!stringUtil.isBlank(this.codigo))
    		str = str + this.codigo;
    	if (!stringUtil.isBlank(this.apellidoPaterno))
    		str = str + " - " + this.apellidoPaterno;
    	if (!stringUtil.isBlank(this.apellidoMaterno))
    		str = str + " " + this.apellidoMaterno;
    	if (!stringUtil.isBlank(this.apellidoPaterno))
    		str = str + ", " + this.nombre;
    	return str;
    }          

    public Map<String, Recurso> getMapRecurso() {
    if(mapRecurso==null){
        mapRecurso= new HashMap<>();
    }

        return mapRecurso;
    }

    public void setMapRecurso(Map<String, Recurso> mapRecurso) {
        this.mapRecurso = mapRecurso;
    }

    public Map<String, Rol> getMapRol() {
    if (mapRol==null)
        mapRol=new HashMap<>();
        return mapRol;
    }

    public void setMapRol(Map<String, Rol> mapRol) {
        this.mapRol = mapRol;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFlagClaveInicial() {
        return flagClaveInicial;
    }

    public void setFlagClaveInicial(String flagClaveInicial) {
        this.flagClaveInicial = flagClaveInicial;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getConfirmacionClave() {
        return confirmacionClave;
    }

    public void setConfirmacionClave(String confirmacionClave) {
        this.confirmacionClave = confirmacionClave;
    }

    public List<Rol> getListaRol() {
    	if (listaRol==null)
    		listaRol=new ArrayList<>();
        return listaRol;
    }

    public void setListaRol(List<Rol> listaRol) {
        this.listaRol = listaRol;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public String getCodigoRemitente() {
        return codigoRemitente;
    }

    public void setCodigoRemitente(String codigoRemitente) {
        this.codigoRemitente = codigoRemitente;
    }

    public Integer getCodigoOD() {
        return codigoOD;
    }

    public void setCodigoOD(Integer codigoOD) {
        this.codigoOD = codigoOD;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getNombreOD() {
        return nombreOD;
    }

    public void setNombreOD(String nombreOD) {
        this.nombreOD = nombreOD;
    }

    public Integer getOficinaDefensorial() {
        return oficinaDefensorial;
    }

    public void setOficinaDefensorial(Integer oficinaDefensorial) {
        this.oficinaDefensorial = oficinaDefensorial;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

}