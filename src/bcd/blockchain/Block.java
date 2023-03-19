package bcd.blockchain;

import java.io.Serializable;

import bcd.hashing.Hasher;

public class Block implements Serializable {

	private static final long serialVersionUID = 1L;
	public Header blockHeader;

	public Header getBlockHeader() {
        return blockHeader;
    }
	
	public Block(String previousHash) {
        super();
       
        this.blockHeader = new Header();
        this.blockHeader.setPreviousHash(previousHash);
        this.blockHeader.setTimestamp(System.currentTimeMillis());
        String newHash = Hasher.sha256( 
                String.join("+", Integer.toString(this.blockHeader.getID()), this.blockHeader.getPreviousHash(), Long.toString(this.blockHeader.getTimestamp())), 
                Hasher.generateSalt(256) );
        this.blockHeader.setHash(newHash);
    }

	public class Header implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		// data member
		public int id;
		public String hash, previousHash;
		public long timestamp;

		@Override
		public String toString() {
			return "Header [id=" + id + ", currentHash=" + hash + ", previousHash=" + previousHash + ", timestamp="
					+ timestamp + "]";
		}

		// getset methods
		public void setID(int id) {
			this.id = id;
		}
		
		public int getID() {
			return id;
		}
		
		public String getHash() {
			return hash;
		}

		public void setHash(String Hash) {
			this.hash = Hash;
		}

		public String getPreviousHash() {
			return previousHash;
		}

		public void setPreviousHash(String previousHash) {
			this.previousHash = previousHash;
		}

		public long getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(long timestamp) {
			this.timestamp = timestamp;
		}

	}
	
	 /* aggregation relationship */
    public Transaction trxLst;
    public void setTranxLst(Transaction trxLst) {
        this.trxLst = trxLst;
    }
    
    @Override
    public String toString() {
        return "Block [Header=" + blockHeader + ", Transactions=" + trxLst + "]";
    }
	
	
}
