/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.sid.comun;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.log4j.Logger;

/**
 *
 * @author vmorales
 */
public class MEncript {
     private static final Logger log = Logger.getLogger(MEncript.class);

        /* Encriptación de la clave */
    private static String toHexadecimal(byte[] digest){
        String hash = "";
        for(byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) hash += "0";
            hash += Integer.toHexString(b);
        }
        return hash;
    }

    //public static String getStringMessageDigest(String message, String algorithm)
    public static String getStringMessageDigest(String message)
    {
        byte[] digest = null;
        byte[] buffer = message.getBytes();
        log.debug("Entrando a getStringMessageDigest 1 ");
        try {
            log.debug("Entrando a getStringMessageDigest 2 ");
            // MessageDigest messageDigest = MessageDigest.getInstance("SHA512");
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            log.debug("Entrando a getStringMessageDigest 3 ");
            messageDigest.reset();
            log.debug("Entrando a getStringMessageDigest 4 ");
            messageDigest.update(buffer);
            log.debug("Entrando a getStringMessageDigest 5 ");
            digest = messageDigest.digest();
            log.debug("Entrando a getStringMessageDigest 6 ");
        } catch (NoSuchAlgorithmException ex) {
            log.debug("Error creando Digest : "+ex);
        } catch (Exception e) {
            log.debug("Error creando Digest : "+e);
        }
        return toHexadecimal(digest);
    }

}
