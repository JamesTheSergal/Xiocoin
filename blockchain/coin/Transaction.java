package coin;

import java.security.NoSuchAlgorithmException;
import java.time.Instant;

import org.json.simple.JSONObject;

public class Transaction {
		
	public String hash;
	public String sender;
	public String receiver;
	public String time;
	public float amt = (float) 0.0;
	
	public Transaction(String sender, String receiver, float amt) throws NoSuchAlgorithmException {
		this.sender = sender;
		this.receiver = receiver;
		this.amt = amt;
		this.time = Instant.now().toString();
		this.hash = calculateHash();
	}
	
	public String calculateHash() throws NoSuchAlgorithmException {
		String hashString = sender + receiver + String.valueOf(amt) + time;
		JSONObject obj = new JSONObject();
		obj.put(time, hashString);
		String hashEncoded = obj.toJSONString();
		return shaHelper.dataToSha(hashEncoded);
	}

}
