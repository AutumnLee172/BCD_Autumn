package bcd.crypto;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

public class AsymmetricEncryption {
	 private Cipher cipher;

	    public AsymmetricEncryption(){
	        this("RSA");
	    }

	    public AsymmetricEncryption(String algorithm) {
	        try{
	            cipher = Cipher.getInstance(algorithm);
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	    }

	    public String encrypt( String data, PublicKey key) throws Exception
	    {
	        var cipherText = "";

	        cipher.init(Cipher.ENCRYPT_MODE, key);

	        byte[] cipherBytes = cipher.doFinal(data.getBytes());

	        cipherText = Base64.getEncoder().encodeToString(cipherBytes);

	        return cipherText;
	    }

	    public String decrypt( String data, PrivateKey key) throws Exception
	    {
	        cipher.init(Cipher.DECRYPT_MODE, key);

	        byte[] cipherText = Base64.getDecoder().decode(data);

	        byte[] dataBytes = cipher.doFinal(cipherText);

	        return new String(dataBytes);
	    }
}
