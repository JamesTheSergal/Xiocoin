package coin;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class shaHelper {
	
	public static String dataToSha(String hashEncoded) throws NoSuchAlgorithmException {
		// Using the Java Security library, we can turn the data given to us and turn that into
		// Bytes that we will digest in the SHA-256 Algorithm. 
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] hash = md.digest(hashEncoded.getBytes(StandardCharsets.UTF_8));
		
		/*
		 * Then, using the Byte array, we will turn it into the sign-magnitude representation using
		 * BigInteger. 
		 * 
		 *  The explanation can be found here:
		 *  https://www.geeksforgeeks.org/sha-256-hash-in-java/
		 */
		
		BigInteger number = new BigInteger(1, hash);
		
		/*
		 * Then we turn it into a hex value. Like what you would normally see.
		 */
		StringBuilder hexString = new StringBuilder(number.toString(16)); 
		while (hexString.length() < 32) 
        { 
            hexString.insert(0, '0'); 
        } 
		
		return hexString.toString();
	}
}
