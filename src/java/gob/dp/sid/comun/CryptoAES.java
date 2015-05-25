package gob.dp.sid.comun;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Utilitario que devuelve la clave encriptada
 */

public class CryptoAES {

    /**
     * Representa el key de encriptación
     */
    private static String llave="64BB53B70CEA791732B5A8E9738B49CB";

    /**
     * Representa la clave por defecto a encriptar
     */
    private static String claveDefecto="clave";

	/**
	 * Representa la clase SecretKeySpec
	 * @see javax.crypto.spec.SecretKeySpec
	 */
	private SecretKeySpec skeySpec = null;

	/**
	 * Representa el objeto cifrador
	 * @see javax.cripto.Cipher
	 */
	private Cipher cipher = null;

	/**
	 * atributo singleton
	 */
	private static CryptoAES unico = null;

    /**
     * Metodo que devuelve la instancia de CryptoAES y utiliza el patrón singleton.
     *
     */
    public static CryptoAES getInstance() throws Exception {
		if (unico == null) {
			unico = new CryptoAES();

			SecretKeySpec skeySpec =
				new SecretKeySpec(CryptoAES.hex2byte(llave), "AES");

			//System.out.println("valor llave byte2hex:"+CryptoAES.hex2byte(llave));

			//generamos el key partiendo del campo llave
			Cipher cipher = Cipher.getInstance("AES"); // genera un objeto de encriptación
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec); // prepara el objeto para que encripte
			unico.setCipher(cipher);
			unico.setSkeySpec(skeySpec);

		}
		return unico;
	}


    /**
     * Metodo encargado de encriptar una cadena
     *
     * @param args
     * @return
     * @throws Exception
     */
    public String encriptar(String args) throws Exception{
        String encriptado="";

        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

        byte[] encrypted =cipher.doFinal(
                (args.toCharArray().length == 0 ? claveDefecto : args).getBytes()
                );

        encriptado = byte2hex(encrypted);


        return encriptado;
    }


    public String desencriptar(String args) throws Exception {
        String desencriptado="";

        // Instantiate the cipher
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);

        byte[] original = cipher.doFinal(hex2byte(args));
        desencriptado = new String(original);

        return desencriptado;
    }


    /**
     * Metodo encargado de convertir un arreglo de bytes en su equivalente hexadecimal y lo devuelve como cadena
     *
     * @param entrada
     * @return
     */
    private static String byte2hex(byte[] entrada) {
		String salida = "";
		int high;
		int low;
		int i;
		char[] hexChars =
			{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		for (i = 0; i < entrada.length; i++) {
			high = ((entrada[i] & 0xf0) >> 4);
			low = (entrada[i] & 0x0f);
			salida = salida + hexChars[high] + hexChars[low];
		}
		return salida;
	}

	/**
	 * Metodo encargado de convertir un hexadecimal expresado como cadena en su equivalente arreglo de bytes.
	 *
	 * @param cadena
	 * @return
	 */
	private static byte[] hex2byte(String cadena) {
		int j = 0;
		int size = 0;
		String sHex = null;
		size = cadena.length() / 2;
		byte[] respuesta = new byte[size];
		for (int i = 0; i <= (cadena.length() - 2); i = i + 2) {
			sHex = cadena.substring(i, i + 2);
			respuesta[j] = (byte) (Integer.parseInt(sHex, 16));
			//System.out.println("respuesta["+j+"]"+respuesta[j]);
			j++;
		}
		return respuesta;
	}


    /**
     * @return Retorna el cipher.
     */
    public Cipher getCipher() {
        return cipher;
    }
    /**
     * @param cipher El cipher a ingresar.
     */
    public void setCipher(Cipher cipher) {
        this.cipher = cipher;
    }
    /**
     * @return Retorna el skeySpec.
     */
    public SecretKeySpec getSkeySpec() {
        return skeySpec;
    }
    /**
     * @param skeySpec El skeySpec a ingresar.
     */
    public void setSkeySpec(SecretKeySpec skeySpec) {
        this.skeySpec = skeySpec;
    }
}

