package gob.dp.sid.administracion.seguridad.dao;

import gob.dp.sid.administracion.seguridad.entity.RolRecurso;
import java.util.List;
import org.springframework.transaction.TransactionException;

public interface RolRecursoDao {
    /**
     * Retorna la lista en forma de Arbol de RolRecurso de un determinado sistema y rol.
     *
     * @param codigoSistema que contiene el código del sistema.
     * @param codigoRol	contiene el código del Rol.
     * @return	Lista de Arbol de RolRecurso.
     * @see     pe.gob.contraloria.seguridad.core.domain.Arbol
     */
    public List buscarRolRecursoPorSistema(String codigoSistema,String codigoRol) throws TransactionException;
    /**
     * Realiza la asignación de Recursos a un Rol.
     *
     * @param mapRecursos	un <code>Map</code> que contiene los Recursos que van a ser asignados.
     * @param codigoSistema	un <code>String</code> que contiene el código del sistema al que pertenecen los recursos.
     * @param codigoRol		un <code>String</code> que contiene el código del Rol al que se le va a asignar los recursos.
     * @param codigoUsuarioSession	un <code>String</code> que contiene el codigo del usuario de sesión que realiza la asignación.
     */
    public void asignarRolRecurso() throws TransactionException;
    /**
     * Realiza la aprobación de los recursos asignados al rol.
     *
     * @param mapRecursos	un <code>Map</code> que contiene los Recursos que va a ser aprobados.
     * @param codigoSistema	un <code>String</code> que contiene el código del sistema al que pertenecen los recursos.
     * @param codigoRol		un <code>String</code> que contiene el código del rol.
     * @param codigoUsuarioSession	un <code>String</code> que contiene el código del usuario de sesión que realiza la aprobación.
     */
    public void aprobarRolRecurso() throws TransactionException;
    /**
     * Crea Rol Recurso según el bean RolRecurso rolRecurso.
     *
     * @param RolRecurso rolRecurso.
     * @exception  TransactionException
     */
    public void crearRolRecurso(RolRecurso rolRecurso) throws TransactionException;
    /**
     * Modifica el Estado del Rol del recurso según los datos RolRecurso ingresados
     *
     * @param RolRecurso rolRecurso.
     * @exception  DataAccessException
     */
    public void modificarEstadoRolRecurso(RolRecurso rolRecurso) throws TransactionException;
}
