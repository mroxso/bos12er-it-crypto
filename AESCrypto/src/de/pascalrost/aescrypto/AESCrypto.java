package de.pascalrost.aescrypto;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESCrypto {

	// Schlüssel / Passwort
	byte[] key;
	
	/*
	 * Konstruktor mit Schlüsselübergabe
	 */
	public AESCrypto(byte[] key) {
		this.key = key;
	}
	
	/*
	 * Konstruktor mit automatischer Schlüsselerzeugung
	 */
	public AESCrypto() {
		try {
			// Schlüssel wird generiert (128 Bit Schlüssellänge)
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		    keyGen.init(128);
		    SecretKey secKey = keyGen.generateKey();
		
		    key = secKey.getEncoded();
		     
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * Konstruktor mit automatischer Schlüsselerzeugung und variabler Schlüssellänge
	 */
	public AESCrypto(int keyLength) {
		try {
			// Schlüssel wird generiert (mit benutzerdefinierte Schlüssellänge)
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		    keyGen.init(keyLength);
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
	
	/*
	 * Schlüssel as byte-Array
	 */
	public byte[] getKeyAsBytes() {
		return key;
	}
	
	/*
	 * Schlüssel als String 
	 */
	public String getKeyAsString() {
		return Base64.getEncoder().encodeToString(key);
	}
	
}
