package coin;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.json.simple.JSONObject;
import java.time.*;


public class Blockchain{
	
	public ArrayList<Block> chain;
	public ArrayList<Transaction> pendingTransactions;
	public int difficulty, minerReward;
	public int blockSize = 10;
	
	public Blockchain() {
		this.chain = new ArrayList<Block>(); 
		this.pendingTransactions = new ArrayList<Transaction>();
		this.difficulty = 4;
		this.minerReward = 10;
	}
	
	public Block getLastBlock() {
		System.out.println("Returning block "+String.valueOf(chain.size()-1)+" out of size: "+String.valueOf(chain.size()));
		if(chain.size() > 0) {
			return chain.get(chain.size()-1);
		} else if(chain.size() == 1) {
			return chain.get(0);
		} else {
			return null;	
		}
	}
	
	public void addBlock(Block block) {
		if(chain.size() > 0) {
			block.previous = getLastBlock().hash;
		} else {
			block.previous = "none";
		}
		chain.add(block);
	}
	
	public Boolean minePendingTransactions(String minerAddr) throws NoSuchAlgorithmException {
		int lenPT = pendingTransactions.size();
		ArrayList<Transaction> transactionSlice = new ArrayList<Transaction>();
		
		if(lenPT < 2) {
			System.out.println("Not enough transactions to process. Mining cancled.");
		} else {
			while(pendingTransactions.size() > 2) {
				transactionSlice.clear();
				
				int end = 0;
				for(int j = 0; j < blockSize; j++) {
					Transaction gotTrans = pendingTransactions.get(0);
					transactionSlice.add(gotTrans);
					pendingTransactions.remove(0);
					if(pendingTransactions.size() == 0 || j > blockSize-1) {
						break;
					}
				}
						
			
				Block generatedBlock = new Block(transactionSlice, Instant.now().toString(), chain.size());
				
				String hashVal = "";
				if(getLastBlock() != null) {
					hashVal = getLastBlock().hash;
				} else {
					hashVal = "none";
				}
				generatedBlock.previous = hashVal;
				System.out.println("Mining block...");
				generatedBlock.mineBlock(difficulty);
				chain.add(generatedBlock);
				
				Transaction payMiner = new Transaction("Network", minerAddr, minerReward);
				pendingTransactions.add(payMiner);
			}
			
			System.out.println("Mining transactions processed.");
			
		}
		
		return true;
	}
}


