package coin;

import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;
import org.json.simple.JSONObject;

public class Block {
		public int index; 							// Basically the block height
		public String time;							// full date
		public ArrayList<Transaction> transactions; // All of the transactions in the block
		public String previous = "";				// I forgot what this was for
		public String hash = "";		// Will be filled with the hash of this block later
		public int nonse = 0;			// The number of "iterations" this block was calculated with.
		public float volume;			// Calculated monetary value of this block.
		
		public Block(ArrayList<Transaction> transactions, String time, int index) throws NoSuchAlgorithmException {
			this.index = index;
			this.transactions = transactions;
			this.time = time;
			
			// TODO: Get previous block hash and place it here
			this.previous = ""; // Hash of previous block 
			//
			this.hash = calculateHash(); // Hash of this block
			this.volume = calculateVolume();
			
			// TODO: Analyze nonse states
			this.nonse = 0;
			// Validate
		}
		
		public String calculateHash() throws NoSuchAlgorithmException {
			
			/*
			 * Here, we load up the block with transactions and information.
			 * The sum of the information of the block calculated into a hash
			 * is what validates the block and changes the hash.
			 */
			String hashTransactions = "";
			try {
				for(Transaction record : transactions) {
					hashTransactions += record.hash;
				}
			} catch(java.lang.NullPointerException e) {
				//System.out.println("No transactions in block.");
			}
			
			/*
			 * Here, we just make a jumbled mess of datat all appended onto each other in a hashString.
			 * It dosn't really matter too much since once the data goes into the SHA256 Algorithm, you can't decode it. 
			 */
			String hashString = Instant.now().toString() + hashTransactions + String.valueOf(nonse) + String.valueOf(volume) + previous + String.valueOf(index);
			
			// TODO: Format JSON object so that we can store this in the object in a readable form.
			JSONObject obj = new JSONObject();
			obj.put(String.valueOf(index), hashString);
			
			// Then turn it into a string and encode it.
			String hashEncoded = obj.toJSONString();
	        return shaHelper.dataToSha(hashEncoded);
		}
		
		public float calculateVolume() {
			/*
			 * Calculates total value of block.
			 */
			
			float total = 0.0f;
			for(Transaction record : transactions) {
				total = total + record.amt;
			}
			return total;
		}

		public void mineBlock(int difficulty) throws NoSuchAlgorithmException {
			ArrayList<String> arr = new ArrayList<String>();
			for(int i = 1; i < difficulty+1; i++) {
				arr.add(String.valueOf(i));
			}

			
			String hashPuzzel = String.join("", arr);
			while(!hash.substring(0, difficulty).equals(hashPuzzel)) {
				//System.out.println("Compare: (HASH) "+hash.substring(0, difficulty)+" Goal: "+hashPuzzel);
				nonse = nonse + 1;
				hash = calculateHash();
				//System.out.println("Nonse: "+String.valueOf(nonse));
				//System.out.println("Hash: "+hash);
			}
			System.out.println("Block mined!");
				
		}
		
}

