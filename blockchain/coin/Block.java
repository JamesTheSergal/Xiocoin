package coin;

import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;

public class Block {
		public int index;
		public String time;
		public ArrayList<Transaction> transactions;
		public String previous = "";
		public String hash = calculateHash();
		public int nonse = 0;
		public float volume;
		
		public Block(ArrayList<Transaction> transactions, String time, int index) throws NoSuchAlgorithmException {
			this.index = index; //block number
			this.transactions = transactions;
			this.time = time;
			this.previous = ""; // Hash of previous block 
			this.hash = calculateHash(); // Hash of this block
			this.volume = calculateVolume();
			this.nonse = 0;
		}
		
		public String calculateHash() throws NoSuchAlgorithmException {
			String hashTransactions = "";
			try {
				for(Transaction record : transactions) {
					hashTransactions += record.hash;
				}
			} catch(java.lang.NullPointerException e) {
				//System.out.println("No transactions in block.");
			}
			
			String hashString = Instant.now().toString() + hashTransactions + String.valueOf(nonse) + String.valueOf(volume) + previous + String.valueOf(index);
			JSONObject obj = new JSONObject();
			obj.put(String.valueOf(index), hashString);
			String hashEncoded = obj.toJSONString();
	        return shaHelper.dataToSha(hashEncoded);
		}
		
		public float calculateVolume() {
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

