package coin;
import java.io.IOException;

/**
 * This is the main file for running the blockchain at the moment.
 * All transaction tests can be performed here.
 */
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;

public class test {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		Blockchain blockchain = new Blockchain();
		Transaction transaction = null;
		
		try {
			KeyPair keys = blockchain.generateKeys();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		transaction = new Transaction("Stephen Hawking", "Ruben Rod", 50);
		blockchain.pendingTransactions.add(transaction);
		
		transaction = new Transaction("Stephen Hawking", "Ruben Rod", (float) 49.3278);
		blockchain.pendingTransactions.add(transaction);
		
		transaction = new Transaction("Stephen Hawking", "Ruben Rod", (float) 1.524);
		blockchain.pendingTransactions.add(transaction);
		
		transaction = new Transaction("Stephen Hawking", "Ruben Rod", (float) 2.367);
		blockchain.pendingTransactions.add(transaction);
		
		transaction = new Transaction("Stephen Hawking", "Ruben Rod", (float) 0.234548454);
		blockchain.pendingTransactions.add(transaction);
		
		transaction = new Transaction("Stephen Hawking", "Ruben Rod", (float) 0.00000789);
		blockchain.pendingTransactions.add(transaction);
		
		transaction = new Transaction("Stephen Hawking", "Ruben Rod", (float) 7.8914);
		blockchain.pendingTransactions.add(transaction);
		
		transaction = new Transaction("Stephen Hawking", "Ruben Rod", 80);
		blockchain.pendingTransactions.add(transaction);
		
		transaction = new Transaction("Stephen Hawking", "Ruben Rod", 25);
		blockchain.pendingTransactions.add(transaction);
		
		transaction = new Transaction("Stephen Hawking", "Ruben Rod", 25);
		blockchain.pendingTransactions.add(transaction);
		
		transaction = new Transaction("Stephen Hawking", "Ruben Rod", 16);
		blockchain.pendingTransactions.add(transaction);
		
		transaction = new Transaction("Stephen Hawking", "Ruben Rod", 17);
		blockchain.pendingTransactions.add(transaction);
		
		transaction = new Transaction("Stephen Hawking", "Ruben Rod", 18);
		blockchain.pendingTransactions.add(transaction);
		
		transaction = new Transaction("Stephen Hawking", "Ruben Rod", 69);
		blockchain.pendingTransactions.add(transaction);
		
		transaction = new Transaction("Stephen Hawking", "Ruben Rod", 22);
		blockchain.pendingTransactions.add(transaction);
		
		blockchain.minePendingTransactions("Ruben Rod");
		*/
		
		// Block test
		/*
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
		Block testblock = new Block(transactions, Instant.now().toString(), 0);
		blockchain.addBlock(testblock);
		
		testblock = new Block(transactions, Instant.now().toString(), 1);
		blockchain.addBlock(testblock);
		
		testblock = new Block(transactions, Instant.now().toString(), 2);
		blockchain.addBlock(testblock);
		
		System.out.println(blockchain);
		System.out.println("Length: "+String.valueOf(blockchain.chain.size()));
		
		*/

		System.out.println("Blockchain length: " + String.valueOf(blockchain.chain.size()));
		
		
		
	}

}
