package de.pascalrost.aescrypto;

public class Main {

	public static void main(String[] args) {
		// With manual key
		String key = "12345678901234567890123456789012";
		AESCrypto crypto = new AESCrypto(key.getBytes());
		
		// With generated (128 bit) key
//		AESCrypto crypto = new AESCrypto();

		String data = "Hello World! This is a testmessage :)";
		String encData = new String(crypto.encrypt(data.getBytes()));
		String decData = new String(crypto.decrypt(encData.getBytes()));
		
		System.out.println("Original: " + data);
		System.out.println("Encrypted: " + encData);
		System.out.println("Decrypted: " + decData);
		
	}

}
