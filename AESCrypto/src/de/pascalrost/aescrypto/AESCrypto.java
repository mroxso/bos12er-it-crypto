package de.pascalrost.aescrypto;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESCrypto {

	byte[] key;
	
	public AESCrypto(byte[] key) {
		this.key = key;
	}
	
	public AESCrypto() {
		try {
			 KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		     keyGen.init(128);
	
		     SecretKey secKey = keyGen.generateKey();
		
		     key = secKey.getEncoded();
		     
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * Encrypt / Verschlüsseln
	 */
	public byte[] encrypt(byte[] data) {
		byte[] encryptedData = null;
		
		try {
			Cipher c = Cipher.getInstance("AES");
			SecretKeySpec k = new SecretKeySpec(key, "AES");
			
			c.init(Cipher.ENCRYPT_MODE, k);
			
			encryptedData = c.doFinal(data);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
//		return encryptedData;
		return Base64.getEncoder().encode(encryptedData);
	}
	
	/*
	 * Decrypt / Entschlüsseln
	 */
	public byte[] decrypt(byte[] encryptedData) {
		encryptedData = Base64.getDecoder().decode(encryptedData);
		
		byte[] data = null;
		
		try {
			Cipher c = Cipher.getInstance("AES");
			SecretKeySpec k = new SecretKeySpec(key, "AES");
			
			c.init(Cipher.DECRYPT_MODE, k);
			
			data = c.doFinal(encryptedData);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return data;
	}
	
}
