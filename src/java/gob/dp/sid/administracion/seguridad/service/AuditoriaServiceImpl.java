/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.sid.administracion.seguridad.service;
import gob.dp.sid.administracion.seguridad.constantes.ConstantesAuditoria;
import gob.dp.sid.administracion.seguridad.dao.AuditoriaDao;
import gob.dp.sid.administracion.seguridad.entity.Usuario;
import gob.dp.sid.comun.Auditoria;
import gob.dp.sid.comun.SessionUtil;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrador
 */

@Service("auditoriaService")
public class AuditoriaServiceImpl implements AuditoriaService
{
    @Autowired
    private AuditoriaDao auditoriaDao;

    public void setAuditoriaDao(AuditoriaDao auditoriaDao) {
        this.auditoriaDao = auditoriaDao;
    }
    
    @Override
    public void auditar(String codigoAccion, String detalle) throws Exception{

           Auditoria filter=new Auditoria();
           Usuario usuario=SessionUtil.getUsuario();
           filter.setUsuario(usuario);
           filter.setIp(usuario.getIp());
           filter.setCodigoAccion(codigoAccion);

           //Validando k no sobrepase el máximo permitido		            
           if(detalle!=null){
               if(detalle.trim().length()>ConstantesAuditoria.TAMANIO_MAXIMO_DETALLE){
                    filter.setDetalle(detalle.trim().substring(0, ConstantesAuditoria.TAMANIO_MAXIMO_DETALLE));
                 }else{
                     filter.setDetalle(detalle.trim());  
                }
            }

           this.auditoriaDao.insertarAuditoria(filter);

    }

        /**
         * Cuando el usuario No se encuentra en sesión
         * @param codigoAccion
         * @param detalle
         * @param usuario
         * @throws Exception
         */
     @Override
     public void auditar(String codigoAccion, String detalle, Usuario usuario) throws Exception{

           Auditoria filter=new Auditoria();
           filter.setUsuario(usuario);

           FacesContext context = FacesContext.getCurrentInstance();
           HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
           usuario.setIp(request.getRemoteAddr());

           filter.setIp(usuario.getIp());
           filter.setCodigoAccion(codigoAccion);

           //Validando k no sobrepase el máximo permitido
           if(detalle!=null){
               if(detalle.trim().length()>ConstantesAuditoria.TAMANIO_MAXIMO_DETALLE){
                    filter.setDetalle(detalle.trim().substring(0, ConstantesAuditoria.TAMANIO_MAXIMO_DETALLE));
                 }else{
                     filter.setDetalle(detalle.trim());
                }
            }

           this.auditoriaDao.insertarAuditoria(filter);

    }
}
