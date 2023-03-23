package bcd.hashing;

import java.security.MessageDigest;
import java.security.SecureRandom;
import org.apache.commons.codec.binary.Hex;

public class Hasher {
	/**
	 * 
	 * @param blockBytes
	 * @return
	 */
	static public String sha256(byte[] blockBytes) {
		try {
			byte[] hashBytes = MessageDigest.getInstance("SHA-256").digest(blockBytes);
			return Hex.encodeHexString(hashBytes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	static public String sha256(String input) {
		return hash(input, "SHA-256");
	}

	/**
	 * 
	 * @param input
	 * @param salt
	 * @return
	 */
	static public String sha256(String input, byte[] salt) {
		return hash(input, salt, "SHA-256");
	}

	/**
	 * hash(String, String) : String
	 */
	private static String hash(String input, String algorithm) {
		MessageDigest md;
		try {
			// instantiate the MD object
			md = MessageDigest.getInstance(algorithm);
			// fetch input to MD
			md.update(input.getBytes());

			// digest it
			byte[] hashBytes = md.digest();
			// convert to Hex format with Hex API from Apache common
			return String.valueOf(Hex.encodeHex(hashBytes));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static String hash(String input, byte[] salt, String algorithm) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance(algorithm);
			md.update(input.getBytes());
			md.update(salt);

			byte[] hashBytes = md.digest();
			return String.valueOf(Hex.encodeHex(hashBytes));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// salt generator
	public static byte[] generateSalt(int length) {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[length];
		random.nextBytes(salt);
		return salt;
	}
}
