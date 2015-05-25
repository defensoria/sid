/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.sid.comun;



/**
 *
 * @author Administrador
 */
public class ComunUtil {

    private ComunUtil()
    {

    }

    public static boolean isCodigoListaValorPorDefecto(String codigo)
    {
        if (ConstantesUtil.LISTA_VALOR_SELECCIONE_CODIGO.equals(codigo))
            return true;
        if (ConstantesUtil.LISTA_VALOR_TODOS_CODIGO.equals(codigo))
            return true;
        if (ConstantesUtil.LISTA_VALOR_NINGUNO_CODIGO.equals(codigo))
            return true;
        return false;

    }



}
