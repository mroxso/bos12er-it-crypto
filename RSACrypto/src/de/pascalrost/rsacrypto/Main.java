package de.pascalrost.rsacrypto;

public class Main {

	public static void main(String[] args) {
		
		RSACrypto crypto = new RSACrypto(4096);
		
		String text = "Hello World!";
		byte[] data = text.getBytes();
		
		byte[] encData = crypto.encrypt(data);
		byte[] decData = crypto.decrypt(encData);
		
		System.out.println("Public Key: " + crypto.getPublicKey());
		System.out.println("Private Key: " + crypto.getPrivateKey());
		System.out.println("-----------------");
		System.out.println("Original: " + new String(data));
		System.out.println("-----------------");
		System.out.println("Encoded: " + new String(encData));
		System.out.println("Decoded: " + new String(decData));
		
		crypto.saveKeysToFiles();
	}

}
