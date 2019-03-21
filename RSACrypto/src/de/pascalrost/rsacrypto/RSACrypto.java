package de.pascalrost.rsacrypto;

import java.io.FileWriter;
import java.io.Writer;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

public class RSACrypto {

	Key publicKey = null; // Öffentlicher Schlüssel / Passwort
	Key privateKey = null; // Privater Schlüssel / Passwort
	
	/*
	 * Konstruktor mit Schlüsselübergabe
	 */
	public RSACrypto(Key publicKey, Key privateKey) {
		this.privateKey = privateKey;
		this.publicKey = publicKey;
	}
	
	/*
	 * Konstruktor mit automatischer Schlüsselerzeugung
	 */
	public RSACrypto() {
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(2048);
			KeyPair kp = kpg.genKeyPair();
			
			publicKey = kp.getPublic();
			privateKey = kp.getPrivate();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * Konstruktor mit automatischer Schlüsselerzeugung und variabler Schlüssellänge
	 */
	public RSACrypto(int keyLength) {
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(keyLength);
			KeyPair kp = kpg.genKeyPair();
			
			publicKey = kp.getPublic();
			privateKey = kp.getPrivate();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 * Encrypt / Verschlüsseln
	 */
	public byte[] encrypt(byte[] data) {
		byte[] encData = null;
		try {
	        Cipher cipher = Cipher.getInstance("RSA");  
	        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
	        encData = cipher.doFinal(data);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}

		return Base64.getEncoder().encode(encData);
    }

	/*
	 * Decrypt / Entschlüsseln
	 */
    public byte[] decrypt(byte [] encrypted) {
    	encrypted = Base64.getDecoder().decode(encrypted);
    	
    	byte[] decData = null;
    	try {
    		Cipher cipher = Cipher.getInstance("RSA");  
	        cipher.init(Cipher.DECRYPT_MODE, publicKey);
	        decData = cipher.doFinal(encrypted);
    	} catch(Exception e) {
			System.out.println(e.getMessage());
		}
        
        return decData;
	}
	

	/*
	 * Encrypt / Verschlüsseln
	 * mit übergebenen privateKey
	 */
	public byte[] encrypt(PrivateKey privateKey, byte[] data) {
		byte[] encData = null;
		try {
			Cipher cipher = Cipher.getInstance("RSA");
	        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
	        encData = cipher.doFinal(data);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}

		return Base64.getEncoder().encode(encData);
    }
    

	/*
	 * Decrypt / Entschlüsseln
	 * mit einem übergebenen publicKey
	 */
    public byte[] decrypt(PublicKey publicKey, byte [] encrypted) {
    	encrypted = Base64.getDecoder().decode(encrypted);
    	
    	byte[] decData = null;
    	try {
	    	Cipher cipher = Cipher.getInstance("RSA");  
	        cipher.init(Cipher.DECRYPT_MODE, publicKey);
	        decData = cipher.doFinal(encrypted);
    	} catch(Exception e) {
			System.out.println(e.getMessage());
		}
        
        return decData;
	}
    
    /**
     * Speichert die Schlüssel in Dateien
     */
    public void saveKeysToFiles() {
    	Base64.Encoder encoder = Base64.getEncoder();

    	try {
	    	Writer out = new FileWriter("private.key");
	    	out.write("-----BEGIN RSA PRIVATE KEY-----\n");
	    	out.write(encoder.encodeToString(privateKey.getEncoded()));
	    	out.write("\n-----END RSA PRIVATE KEY-----\n");
	    	out.close();
	    	

	    	out = new FileWriter("public.key");
	    	out.write("-----BEGIN RSA PUBLIC KEY-----\n");
	    	out.write(encoder.encodeToString(publicKey.getEncoded()));
	    	out.write("\n-----END RSA PUBLIC KEY-----\n");
	    	out.close();
    	} catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    	
    }
    
    /*
     * Gibt den publicKey zurück
     */
    public String getPublicKey() {
    	Base64.Encoder encoder = Base64.getEncoder();
    	
    	return encoder.encodeToString(publicKey.getEncoded());
    }
    
    /*
     * Gibt den privateKey zurück
     */
    public String getPrivateKey() {
    	Base64.Encoder encoder = Base64.getEncoder();
    	
    	return encoder.encodeToString(privateKey.getEncoded());
    }
	
}
