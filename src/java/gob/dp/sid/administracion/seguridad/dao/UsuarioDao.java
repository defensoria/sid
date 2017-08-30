package gob.dp.sid.administracion.seguridad.dao;

import gob.dp.sid.administracion.seguridad.bean.FiltroUsuario;
import gob.dp.sid.administracion.seguridad.entity.Usuario;
import java.util.List;

public interface UsuarioDao {

    public List<Usuario> buscarUsuario(FiltroUsuario filtro);
    
    public List<Usuario> buscarUsuarioTotal();
    
    public Usuario consultarUsuario(FiltroUsuario filtro);

    public void insertarUsuario(Usuario usuario);

    public void modificarUsuario(Usuario usuario);

    public String generarCodigoUsuario();

    public Integer loginUsuario(Usuario usuario);

    public Integer getTotalBuscarUsuario(FiltroUsuario filtro);
    
    public List<Usuario> listaUsuariosPorOD(Usuario usuario);
    
    public Integer listaUsuarioCount(String codigoUsuario);

}
