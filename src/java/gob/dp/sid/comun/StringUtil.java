/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.comun;

/**
 *
 * @author carlos
 */
public class StringUtil {
    
    public boolean isBlank(String cadena){
        return cadena == null || cadena.isEmpty();
    }
    
    public boolean isNotNullOrCero(Integer numero){
        return numero != null && numero != 0;
    }
    
}
