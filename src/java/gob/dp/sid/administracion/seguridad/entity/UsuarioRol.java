package gob.dp.sid.administracion.seguridad.entity;


public class UsuarioRol {
    private Usuario usuario=new Usuario();
    private Rol rol=new Rol();

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
  
}