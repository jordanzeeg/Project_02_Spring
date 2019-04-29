/*
 * Source: http://appsdeveloperblog.com/encrypt-user-password-example-java/ 
 */

package com.java.salt;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordAndSalt {
	private static final Random RANDOM = new SecureRandom();
	private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final int ITERATIONS = 10000;
	private static final int KEY_LENGTH = 256;

	public String getSalt(int length) {
		StringBuilder returnSalt = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			returnSalt.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length()))); // creating the salt
		}

		return new String(returnSalt); // returning the salt as a string
	}

	public byte[] hash(char[] password, byte[] salt) {
		PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
		Arrays.fill(password, Character.MIN_VALUE);

		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			/*
			 * PBKDF2: Password-based-Key-Derivative-Function, used to implement a
			 * pseudorandom function, such as a cryptograhic hash, cipher, or HMAC to the
			 * input password or passphrase along with a salt value and repeats the process
			 * many times to produce a derived key
			 * 
			 * 
			 * HMAC: Keyed-Hash Message Authentication Code A specific construction for
			 * calculating a message authentication code (MAC) involving a cryptographic
			 * hash function with a secret cryptographic key
			 * 
			 * 
			 * SHA1 will produce a hash length of 160 bits
			 */

			return skf.generateSecret(spec).getEncoded();

		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
			// e.printStackTrace();
		} finally {
			spec.clearPassword();
		}

	}

	public String generateSecurePassword(String password, String salt) {// creating the password to be stored in the db
		String returnValue = null;

		byte[] securePassword = hash(password.toCharArray(), salt.getBytes());

		returnValue = Base64.getEncoder().encodeToString(securePassword);

		return returnValue;
	}

	public boolean verifyUserPassword(String providedPassword, String securePassword, String salt) {
		// providedPassword is user input password
		// securePassword is password from the db

		boolean returnValue = false;

		// Generate New secure password with the same salt
	String newSecurePassword = generateSecurePassword(providedPassword, salt);// encrypting the user password with
																					// the same salt create
		// check if two passwords are equal
		returnValue = newSecurePassword.equalsIgnoreCase(securePassword); // check if new encrypted password is the same
		
		return returnValue;
	}
}
