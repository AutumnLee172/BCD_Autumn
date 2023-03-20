package bcd.blockchain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bcd.crypto.AsymmetricEncryption;
import bcd.signature.DigitalSignature;
import bcd.signature.KeyAccess;
import bcd.signature.KeyGenerator;

public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;
	public final int maxSize = 15;
	public String merkleRoot = "0";
	public List<String> dataLst = new ArrayList<>();

	public void add(String tranx) throws Exception {
		if (dataLst.size() < maxSize)
			dataLst.add(tranx);
		this.generateMerkleRoot();
	}

	@Override
	public String toString() {
		try {
			return "Transaction [merkleRoot=" + merkleRoot + ", dataList=" + dataLst + "]";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void generateMerkleRoot() {
		MerkleTree mt = MerkleTree.getInstance(dataLst);
		mt.build();
		this.merkleRoot = mt.getRoot();
	}

	public void SignandEncrypt() throws Exception {
		DigitalSignature signature = new DigitalSignature();
		AsymmetricEncryption asymm = new AsymmetricEncryption();
		String encrypted = "";

		String signed = signature.sign(String.join(", ", dataLst), KeyAccess.getPrivateKey(KeyGenerator.PRIVATE_FILE));

		Boolean verified = signature.verify(String.join(", ", dataLst), signed,
				KeyAccess.getPublicKey(KeyGenerator.PUBLIC_FILE));

		if (verified) {
			String asymmEncrypt = asymm.encrypt(String.join(", ", dataLst),
					KeyAccess.getPublicKey(KeyGenerator.PUBLIC_FILE));
			dataLst = new ArrayList<String>(Arrays.asList(String.join("|", asymmEncrypt, signed).split(","))); 
		}
	}
}
