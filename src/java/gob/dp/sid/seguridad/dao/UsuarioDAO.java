package gob.dp.sid.seguridad.dao;

import gob.dp.sid.seguridad.bean.FiltroUsuario;
import gob.dp.sid.seguridad.entity.Usuario;
import java.util.List;

public interface UsuarioDAO {

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
