package bcd.blockchain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;
	public final int maxSize = 15;
    
    
    public List<String> dataLst = new ArrayList<>();
    
    public void add(String tranx) {
        if( dataLst.size() < maxSize )
            dataLst.add(tranx);
    }
    @Override
    public String toString() {
        return "Transaction [merkleRoot=" + generateMerkleRoot() + ", dataList=" + dataLst + "]";
    }
    
    public String generateMerkleRoot() {
    	MerkleTree mt = MerkleTree.getInstance(dataLst);
    	mt.build();
    	return mt.getRoot();
    }
}
