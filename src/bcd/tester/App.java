package bcd.tester;

import java.io.IOException;
import java.util.Base64;

import bcd.hashing.Hasher;
import bcd.blockchain.Block;
import bcd.blockchain.Blockchain;
import bcd.blockchain.Transaction;

public class App {
	public static void main(String[] args) throws IOException {

		Blockchain bc = new Blockchain();
		bc.genesis();
		String line1 = "bob|alice|debit|100";
		String line2 = "mick|alice|debit|200";

		Transaction tranxLst = new Transaction();
		tranxLst.add(line1);
		tranxLst.add(line2);

		// tranxLst.generateMerkleRoot();
		String previousHash = bc.get().getLast().getBlockHeader().getHash();
		Block b1 = new Block(previousHash);

	}
}
