package bcd.signature;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import bcd.blockchain.Blockchain;


public class KeyGenerator {
private static final String ALGORITHM = "RSA";
	
	private KeyPairGenerator keygen;
	private KeyPair keyPair;
	private static PublicKey publicKey;
	private static PrivateKey privateKey;
	
	public static final String PUBLIC_FILE = Blockchain.MasterFolder + "PublicKey.bin";
	public static final String PRIVATE_FILE = Blockchain.MasterFolder + "PrivateKey.bin";
	
	public static PublicKey getPublicKey() {
		return publicKey;
	}
	
	public static PrivateKey getPrivateKey() {
		return privateKey;
	}

	public KeyGenerator() {
		try {
			keygen = KeyPairGenerator.getInstance(ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void generate() throws IOException {
		try {
			if(!Blockchain.checkFile(PUBLIC_FILE) && !Blockchain.checkFile(PRIVATE_FILE)) {
				KeyGenerator keyMaker = new KeyGenerator();
				//generate keypair
				keyMaker.keyPair = keyMaker.keygen.generateKeyPair();
				//get public key
				publicKey = keyMaker.keyPair.getPublic();
				save(publicKey.getEncoded(), PUBLIC_FILE);
				//get private key
				privateKey = keyMaker.keyPair.getPrivate();
				save(privateKey.getEncoded(), PRIVATE_FILE);
				
				System.out.println("Generated a key pair.");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// DIR/PublicKey; DIR/PrivateKey
	public static void save(byte[] keyBytes, String path) {
		File f = new File(path);
		f.getParentFile().mkdirs();
		
		try {
			Files.write(Paths.get(path), keyBytes, StandardOpenOption.CREATE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
