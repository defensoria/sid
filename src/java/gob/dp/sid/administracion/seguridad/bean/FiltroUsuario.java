package gob.dp.sid.administracion.seguridad.bean;

public class FiltroUsuario 
{
	private String codigo;
    private String dni;
    private String nombre; 
    private String apellidoPaterno;  
    private String apellidoMaterno;
    private boolean incluirLstRol;
    private boolean incluirMapRol;
    private boolean incluirMapRecurso;
    private boolean incluirLstRegInforme;
    private boolean tieneInforme=false;
    private Long ini;
    private Long fin;

	public boolean isIncluirLstRegInforme() {
		return incluirLstRegInforme;
	}
	public void setIncluirLstRegInforme(boolean incluirLstRegInforme) {
		this.incluirLstRegInforme = incluirLstRegInforme;
	}
	public boolean isTieneInforme() {
		return tieneInforme;
	}
	public void setTieneInforme(boolean tieneInforme) {
		this.tieneInforme = tieneInforme;
	}
	public boolean isIncluirMapRecurso() {
		return incluirMapRecurso;
	}
	public void setIncluirMapRecurso(boolean incluirMapRecurso) {
		this.incluirMapRecurso = incluirMapRecurso;
	}
	public boolean isIncluirLstRol() {
		return incluirLstRol;
	}
	public void setIncluirLstRol(boolean incluirLstRol) {
		this.incluirLstRol = incluirLstRol;
	}
	public boolean isIncluirMapRol() {
		return incluirMapRol;
	}
	public void setIncluirMapRol(boolean incluirMapRol) {
		this.incluirMapRol = incluirMapRol;
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}  

    public Long getIni() {
        return ini;
    }

    public void setIni(Long ini) {
        this.ini = ini;
    }

    public Long getFin() {
        return fin;
    }

    public void setFin(Long fin) {
        this.fin = fin;
    }
    
    
    
}
