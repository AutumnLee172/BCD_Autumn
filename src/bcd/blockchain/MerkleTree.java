package bcd.blockchain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bcd.hashing.Hasher;

public class MerkleTree implements Serializable {
	private static final long serialVersionUID = 1L;
	public List<String> trxLst;
	public String root = "0";

	public String getRoot() {
		return root;
	}

	/**
	 * * @implNote * Set the transaction list to the MerkleTree object. * * @param
	 * tranxLst
	 */
	public MerkleTree(List<String> trxLst) {
		super();
		this.trxLst = trxLst;
	}

	public static MerkleTree instance;

	public static MerkleTree getInstance(List<String> trxLst) {
		if (instance == null) {
			return new MerkleTree(trxLst);
		}
		return instance;
	}

	/** * @implNote * Build merkle tree * * @implSpec * + build() : void */
	public void build() {
		List<String> temp = new ArrayList<>();
		for (String tranx : this.trxLst) {
			temp.add(tranx);
		}
		List<String> hashes = TrxHash(temp);
		while (hashes.size() != 1) {
			hashes = TrxHash(hashes);
		}
		this.root = hashes.get(0);
	}

	/**
	 * * @implNote * Generate hashes of transactions * * @implSpec * -
	 * genTranxHashLst(List<String>) : List<String>
	 */
	public List<String> TrxHash(List<String> tranxLst) {
		List<String> hashLst = new ArrayList<>();
		int i = 0;
		while (i < tranxLst.size()) {
			String left = tranxLst.get(i);
			i++;
			String right = "";
			if (i != tranxLst.size())
				right = tranxLst.get(i);
			String hash = Hasher.sha256(left.concat(right), Hasher.generateSalt(256));
			hashLst.add(hash);
			i++;
		}
		return hashLst;
	}
}
