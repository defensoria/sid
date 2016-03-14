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

    public static String getStringMessageDigest(String message)
    {
        byte[] digest = null;
        byte[] buffer = message.getBytes();
        try {
            // MessageDigest messageDigest = MessageDigest.getInstance("SHA512");
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.reset();
            messageDigest.update(buffer);
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException ex) {
            log.error("Error creando Digest : "+ex);
        } catch (Exception e) {
            log.error("Error creando Digest : "+e);
        }
        return toHexadecimal(digest);
    }

}
