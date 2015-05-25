/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.sid.administracion.seguridad.service;
import gob.dp.sid.administracion.seguridad.constantes.ConstantesAuditoria;
import gob.dp.sid.administracion.seguridad.dao.RolDao;
import gob.dp.sid.administracion.seguridad.entity.Rol;
import gob.dp.sid.administracion.seguridad.entity.Usuario;
import gob.dp.sid.administracion.seguridad.entity.UsuarioRol;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrador
 */

@Service("rolService")
public class RolServiceImpl implements RolService
{
    private static Logger log = Logger.getLogger(RolServiceImpl.class);

    @Autowired
    private RolDao rolDao;

    @Autowired
    private AuditoriaService auditoriaService;

    @Override
    public List<Rol> buscarRol(Rol filtro){
        return rolDao.buscarRol(filtro);
    }
    @Override
    public List<Rol> buscarRolSegunUsuario(Usuario filtro){
        return rolDao.buscarRolSegunUsuario(filtro);
    }

    @Override
    public void asignarRolUsuario(Usuario usuario, List<Rol> listaRolSeleccionado)throws Exception{
	List<Rol> lstRolesNuevos=new ArrayList<Rol>();
	//Buscamos los roles asignados actualmente
	List<Rol> lstRolesBD=rolDao.buscarRolSegunUsuario(usuario);

	boolean encontrado=false;
	 Rol val;
	 Rol rol;
         //recorro los roles finales que deseo tener
         for(Iterator it=listaRolSeleccionado.iterator();it.hasNext();){
             val=(Rol)it.next();
             encontrado=false;
             for(Iterator it2=lstRolesBD.iterator();it2.hasNext();){
                rol=(Rol)it2.next();
                if(val.getCodigo().equals(rol.getCodigo())){
                      it2.remove();
                      encontrado=true;
                      break;
                }
             }
             if(!encontrado){
                 lstRolesNuevos.add(val);
             }
         }

        //Agregamos los nuevos Roles
        UsuarioRol usuarioRol;

        StringBuffer sbDetalle=new StringBuffer("");
        for(int i=0;i<lstRolesNuevos.size();i++){
             sbDetalle.delete(0,sbDetalle.length());
             rol=lstRolesNuevos.get(i);
             usuarioRol=new UsuarioRol();
             usuarioRol.setRol(rol);
             usuarioRol.setUsuario(usuario);
             this.rolDao.insertarUsuarioRol(usuarioRol);
             /*
     		 * Auditoria
     		 */
             sbDetalle.append("Usu:").append(usuario.getCodigo()).append(",Asignar Rol:").append(rol.getCodigo());
             auditoriaService.auditar(ConstantesAuditoria.SEGURIDAD_ASIGNAR_ROL, sbDetalle.toString());
             log.debug("Rol Nuevo:"+rol.getCodigo());
         }
        //Eliminamos los que sobran        
        for(int i=0;i<lstRolesBD.size();i++){
             sbDetalle.delete(0,sbDetalle.length());
             rol= lstRolesBD.get(i);
             usuarioRol=new UsuarioRol();
             usuarioRol.setRol(rol);
             usuarioRol.setUsuario(usuario);
             this.rolDao.deleteUsuarioRol(usuarioRol);
            /*
    		 * Auditoria
    		 */
            sbDetalle.append("Usu:").append(usuario.getCodigo()).append(", Quitar Rol:").append(rol.getCodigo());
            auditoriaService.auditar(ConstantesAuditoria.SEGURIDAD_QUITAR_ROL, sbDetalle.toString());
            log.debug("Rol Eliminado:"+rol.getCodigo());
         }
    }
	@Override
	public Map<String,Rol> buscarMapRolSegunUsuario(Usuario filtro) {
		return rolDao.buscarMapRolSegunUsuario(filtro);
	}

    public void setAuditoriaService(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }


}


