package de.pascalrost.rsacrypto;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainRSA {

	public static void main(String[] args) {
		
		// Neue Schlüssel werden generiert (mit 4096 Bit Schlüssellänge)
		RSACrypto crypto = new RSACrypto(4096);
		
		String text = null; // Unser Text/Daten die Verschlüsselt werden sollen, werden hier gespeichert
		try {
			// Daten/Text wird eingelesen
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Eingabe: ");
			text = br.readLine();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		byte[] data = text.getBytes(); // Daten/Text wird in Bytes umgewandelt
		
		byte[] encData = crypto.encrypt(data); // Daten werden Verschlüsselt
		byte[] decData = crypto.decrypt(encData); // Daten werden Entschlüsselt
		
		System.out.println("Public Key: " + crypto.getPublicKey()); // Ausgabe des Public Keys
		System.out.println("Private Key: " + crypto.getPrivateKey()); // Ausgabe des Private Keys
		System.out.println("-----------------");
		System.out.println("Original: " + new String(data)); // Original Text/Daten
		System.out.println("-----------------");
		System.out.println("Encoded: " + new String(encData)); // Verschlüsselter Text/Daten
		System.out.println("Decoded: " + new String(decData)); // Entschlüsselter Text/Daten -> Bei Erfolg = Original
		
		crypto.saveKeysToFiles(); // Schlüssel werden in Dateien gespeichert
	}

}
