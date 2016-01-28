/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.administracion.seguridad.service;

import gob.dp.sid.administracion.seguridad.bean.FiltroUsuario;
import gob.dp.sid.administracion.seguridad.constantes.ConstantesAuditoria;
import gob.dp.sid.administracion.seguridad.dao.UsuarioDao;
import gob.dp.sid.administracion.seguridad.entity.Rol;
import gob.dp.sid.administracion.seguridad.entity.Usuario;
import gob.dp.sid.comun.MEncript;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrador
 */
@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

    private static final Logger log = Logger.getLogger(UsuarioServiceImpl.class);
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private RolService rolService;

    @Autowired
    private RecursoService recursoService;

    @Autowired
    private AuditoriaService auditoriaService;

    public void setRolService(RolService rolService) {
        this.rolService = rolService;
    }

    public void setRecursoService(RecursoService recursoService) {
        this.recursoService = recursoService;
    }

    @Override
    public List<Usuario> buscarUsuario(FiltroUsuario filtro) {
        return usuarioDao.buscarUsuario(filtro);
    }

    @Override
    public void insertarUsuario(Usuario usuario, List<Rol> listaRol) throws Exception {
        // 2013-08-19- Comentado para cambiar el algoritmo de encriptacion
        //String encPass = CryptoAES.getInstance().encriptar(usuario.getClave().trim());
        String encPass = MEncript.getStringMessageDigest(usuario.getClave().trim());
        // 2013-08-19- Comentado para permitir generar el codigo de usuario
        //usuario.setCodigo(usuarioDao.generarCodigoUsuario());
        usuario.setClave(encPass);
        usuarioDao.insertarUsuario(usuario);
        /**
         * Auditoria
         */
        auditoriaService.auditar(ConstantesAuditoria.SEGURIDAD_REGISTRAR_USUARIO, "Registrar Usu:" + usuario.getCodigo());
        rolService.asignarRolUsuario(usuario, listaRol);
    }

    @Override
    public void modificarUsuario(Usuario usuario, List<Rol> listaRol) throws Exception {
        usuarioDao.modificarUsuario(usuario);

        /**
         * Auditoria
         */
        auditoriaService.auditar(ConstantesAuditoria.SEGURIDAD_MODIFICAR_USUARIO, "Mofificar Usu:" + usuario.getCodigo());
        rolService.asignarRolUsuario(usuario, listaRol);
    }

    @Override
    public void cambiarClave(Usuario usuario) throws Exception {
        //String encPass = CryptoAES.getInstance().encriptar(usuario.getClave().trim());
        String encPass = MEncript.getStringMessageDigest(usuario.getClave().trim());
        usuario.setClave(encPass);
        usuarioDao.cambiarClaveUsuario(usuario);
        /**
         * Auditoria
         */
        auditoriaService.auditar(ConstantesAuditoria.SEGURIDAD_MODIFICAR_USUARIO, "Cambiar Clave:" + usuario.getCodigo());
    }

    @Override
    public Integer loginUsuario(Usuario usuario) throws Exception {
          //String encPass = CryptoAES.getInstance().encriptar(usuario.getClave().trim());
        String encPass = MEncript.getStringMessageDigest(usuario.getClave().trim());
        usuario.setClave(encPass);
        return usuarioDao.loginUsuario(usuario);
    }

    @Override
    public Usuario consultarUsuario(FiltroUsuario filtro) throws Exception {
        Usuario u = usuarioDao.consultarUsuario(filtro);
        if (u != null) {
            if (filtro.isIncluirLstRol()) {
                u.setListaRol(rolService.buscarRolSegunUsuario(u));
            }
            if (filtro.isIncluirMapRol()) {
                u.setMapRol(rolService.buscarMapRolSegunUsuario(u));
            }
            if (filtro.isIncluirMapRecurso()) {
                u.setMapRecurso(recursoService.buscarMapRecursoSegunUsuario(u));
            }

        }

        return u;
    }

    public void setAuditoriaService(AuditoriaService auditoriaService) {
        log.debug("METODO XXXXXXXX : UsuarioServiceImpl.setAuditoriaService");
        this.auditoriaService = auditoriaService;
    }

    @Override
    public Integer getTotalBuscarUsuario(FiltroUsuario filtro) {
        return usuarioDao.getTotalBuscarUsuario(filtro);
    }

    @Override
    public List<Usuario> buscarUsuarioTotal() {
        return usuarioDao.buscarUsuarioTotal();
    }

    @Override
    public String autocompletarUsuario() {
        List<Usuario> lst = usuarioDao.buscarUsuarioTotal();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("var projects = [");
        int i = 0;
        for(Usuario a:lst){
            if(i > 0)
            stringBuilder.append(",");    
            stringBuilder.append("{value: '").append(a.getCodigo()).append("' ,");
            stringBuilder.append("label: '").append(a.getNombre()+" "+a.getApellidoPaterno()+" "+a.getApellidoMaterno()+" - "+a.getCargo()).append("' ,");
            stringBuilder.append("desc: ").append("''").append(",");
            stringBuilder.append("icon: ").append("'' },");
            
        }
        stringBuilder.append("];");
        return stringBuilder.toString();
    }

    @Override
    public void modificarUsuarioSimple(Usuario usuario) {
        try {
            usuarioDao.modificarUsuario(usuario);
        } catch (Exception ex) {
            log.error(ex.getCause());
        }
    }

    @Override
    public List<Usuario> listaUsuariosPorOD(Usuario usuario) {
        return usuarioDao.listaUsuariosPorOD(usuario);
    }

}
