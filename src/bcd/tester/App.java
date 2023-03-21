package bcd.tester;

import java.io.IOException;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import bcd.hashing.Hasher;
import bcd.signature.DigitalSignature;
import bcd.signature.KeyAccess;
import bcd.signature.KeyGenerator;
import bcd.blockchain.Block;
import bcd.blockchain.Blockchain;
import bcd.blockchain.Transaction;
import bcd.crypto.AsymmetricEncryption;

public class App {
	public static void main(String[] args) throws Exception {
//
//		Blockchain bc = new Blockchain();
//		bc.genesis();
//		String line1 = "bob|alice|debit|100";
//		String line2 = "mick|alice|debit|200";
//
//		Transaction tranxLst = new Transaction();
//		tranxLst.add(line1);
//		tranxLst.add(line2);
//
//		// tranxLst.generateMerkleRoot();
//		String previousHash = bc.get().getLast().getBlockHeader().getHash();
//		Block b1 = new Block(previousHash);
//		b1.setTranxLst(tranxLst);
//		bc.nextBlock(b1);
		
		   LinkedList<Block> list = new LinkedList<Block>();
	        list = Blockchain.get();

	        AsymmetricEncryption asymm = new AsymmetricEncryption();

	        List<String> TranxList = null;

	        System.out.println("Transaction Decryption is beginning..");

	        System.out.println("Below is decrypted Transaction Record:\n");

	        //for loop start at index 1 to exclude genesis block
	        for (int i = 1 ; i < list.size(); i ++)
	        {
	            TranxList = list.get(i).getTransaction().getTranxLst();
	            //split the transactin list with digital signature
//	            String[] tranx = TranxList.get(0).toString().split("|");
	            String[] tranx = TranxList.get(0).toString().split(":");

	            //decrypt cipher text
	            String oriText = asymm.decrypt(tranx[0], KeyAccess.getPrivateKey(KeyGenerator.PRIVATE_FILE));
	            System.out.println(oriText);
	}
	}
}
