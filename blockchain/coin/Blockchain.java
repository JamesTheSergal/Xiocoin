package coin;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
	
	public KeyPair generateKeys() throws NoSuchAlgorithmException, IOException {
		
		// TODO: Load in keys from file
		
		/*
		 * Here, we will make a RSA key pair which is used for signing of transactions.
		 * We will instantiate the key generator with RSA and then write out the keys
		 * to a file locally so that we can get to them.
		 */
		KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA"); // Make the generator
		
		// Add parameters and generate.
		gen.initialize(2048, new SecureRandom());
		KeyPair pair = gen.generateKeyPair();
		
		// Basic saving things.
		File privateKeyFile = new File("privateKey.pem");
		if(privateKeyFile.createNewFile()) {
			FileWriter privateKeyWriter = new FileWriter("privateKey.pem");
			privateKeyWriter.write(pair.getPrivate().toString());
			System.out.println("Generated new private key...");
		} else {
			System.out.println("Private Key file was found...");
		}
		
		
		File publicKeyFile = new File("privateKey.pem");
		if(publicKeyFile.createNewFile()) {
			FileWriter publicKeyWriter = new FileWriter("privateKey.pem");
			publicKeyWriter.write(pair.getPrivate().toString());
			System.out.println("Generated new private key...");
		} else {
			System.out.println("Private Key file was found...");
		}
		
		return pair;
		
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


