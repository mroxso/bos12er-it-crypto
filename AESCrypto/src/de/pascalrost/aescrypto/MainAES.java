package de.pascalrost.aescrypto;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainAES {

	public static void main(String[] args) {
		// ======================================= //
		// ========= WITH MANUAL KEY ============= //
		// ======================================= //
//		String key = "12345678901234567890123456789012"; // 32 Bit Key
//		String key = "1234567890123456"; // 16 Bit Key
//		String key = "abcdefghijklmnop";
//		String key = "YWJjZGVmZ2hpamtsbW5vcA==";
//		AESCrypto crypto = new AESCrypto(key.getBytes());
		
		// ======================================= //
		// ========= WITH GENERATED KEY ========== //
		// ======================================= //
		// With generated (128 bit) key
//		AESCrypto crypto = new AESCrypto();

		// ======================================= //
		// ========= WITH LONGER KEY ============= //
		// ======================================= //
		// With longer generated key (possible: 128, 192, 256)
		AESCrypto crypto = new AESCrypto(256);
		
		// Daten die verschlüsselt werden sollen
//		String text = "Hello World!";
		
		String text = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Eingabe: ");
			text = br.readLine();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		byte[] data = text.getBytes();

		// Daten verschlüsseln und in encData speichern
		byte[] encData = (crypto.encrypt(data));
		
		// Daten entschlüsseln und in decData speichern
		byte[] decData = (crypto.decrypt(encData));
		
		// Alle Informationen ausgeben
		System.out.println("Key: " + crypto.getKeyAsString());
		System.out.println("Original: " + new String(data));
		System.out.println("Encrypted: " + new String(encData));
		System.out.println("Decrypted: " + new String (decData));
		
	}

}
