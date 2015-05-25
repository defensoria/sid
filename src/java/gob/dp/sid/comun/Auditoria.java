/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.sid.comun;

import gob.dp.sid.administracion.seguridad.entity.Usuario;
import gob.dp.sid.comun.entity.Parametro;
import java.io.Serializable;

/**
 *
 * @author Administrador
 */
public class Auditoria implements Serializable 
{   private String codigo;
    private Usuario usuario=new Usuario();
    private String  codigoAccion;
    private String  detalle;
    private String  ip;
    
    private Parametro estado;
    
    public Parametro getEstado() {
        if (estado==null)
            estado=new Parametro();
        return estado;
    }

    public void setEstado(Parametro estado) {
        this.estado = estado;
    }

    public String getCodigoAccion() {
        return codigoAccion;
    }

    public void setCodigoAccion(String codigoAccion) {
        this.codigoAccion = codigoAccion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    
}
