package bcd.blockchain;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import com.google.gson.GsonBuilder;


public class Blockchain {
	
		public static LinkedList<Block> DB = new LinkedList<>();
		
		public static final String MasterFolder = "master/";
		public static final String MasterFile = MasterFolder + "blockchain.bin";
		public static final String LedgerFile = MasterFolder + "ledger.txt";
		
		public static Blockchain _instance;

		public static Blockchain getInstance() {
			if (_instance == null)
				_instance = new Blockchain();
			return _instance;
		}


		public Blockchain() {
			super();
			System.out.println("> Created a blockchain object!");
		}
		
		/** * genesis() 
		 * @throws IOException */
		public void genesis() throws IOException {
			Block genesis = new Block("0");
			DB.add(genesis);
			this.persist();
			this.distribute();
		}

		/** * nextBlock() 
		 * @throws IOException */
		public void nextBlock(Block nextBlock) throws IOException {
			DB = get();
			DB.add(nextBlock);
			this.persist();
			this.distribute();
		}

		/** * get() 
		 * @throws IOException */
		public LinkedList<Block> get() throws IOException {
			createFile(MasterFile);
			try (FileInputStream fi = new FileInputStream(MasterFile);
					ObjectInputStream in = new ObjectInputStream(fi);) {
				return (LinkedList<Block>) in.readObject();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		/** * persist() 
		 * @throws IOException */
		private void persist() throws IOException {
			createFile(MasterFile);
			try (FileOutputStream fo = new FileOutputStream(MasterFile);
					ObjectOutputStream out = new ObjectOutputStream(fo);) {
				out.writeObject(DB);
				System.out.println("> Updated the Master File.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/** * distribute() 
		 * @throws IOException */
		public void distribute() throws IOException
		{
			createFile(LedgerFile);
			String chain = new GsonBuilder().setPrettyPrinting().create().toJson(DB);
			System.out.println(chain);
			try (FileOutputStream fo = new FileOutputStream(LedgerFile);
				     BufferedOutputStream bout = new BufferedOutputStream(fo)) {
				     bout.write(chain.getBytes());
				} catch (Exception e) {
				     e.printStackTrace();
			}
		}
		
		public void createFile(String filePath) throws IOException {
			 File file = new File(filePath);
			    if(!file.exists()) {
			        new File(MasterFolder).mkdir();
			        file.createNewFile(); 
			    }
		}
}
