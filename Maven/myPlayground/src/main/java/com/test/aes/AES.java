/*
http://aesencryption.net/#Java-aes-encryption-example
*/

package com.test.aes;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AES {
	private static SecretKeySpec secretKey;
	private static byte[] key;
	private static String decryptedString;
	private static String encryptedString;	

	public static String getDecryptedString() {
		return decryptedString;
	}

	public static void setDecryptedString(String decryptedString) {
		AES.decryptedString = decryptedString;
	}

	public static String getEncryptedString() {
		return encryptedString;
	}

	public static void setEncryptedString(String encryptedString) {
		AES.encryptedString = encryptedString;
	}
	
	public static void setKey(String myKey) {
		//MessageDigest sha = null;
		try {
			/*key = myKey.getBytes("UTF-8");
			
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16); // use only first 128 bit
			System.out.println(new String(key, "UTF-8"));
			
			secretKey = new SecretKeySpec(key, "AES");*/
			secretKey = new SecretKeySpec(myKey.getBytes("UTF-8"), "AES");
		} /*catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}*/ catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static String encrypt(String strToEncrypt) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			setEncryptedString(Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes("UTF-8"))));
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}

	public static String decrypt(String strToDecrypt) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			setDecryptedString(new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt))));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}

	public static void main(String args[]) {
		final String strToEncrypt = "My Name is Ritvik";
		final String encryptorKey = "mynameritvikjain";
		
		AES.setKey(encryptorKey);

		System.out.println("String to Encrypt: " + strToEncrypt);
		AES.encrypt(strToEncrypt.trim());
		System.out.println("Encrypted: " + AES.getEncryptedString());

		System.out.println("String To Decrypt : " + AES.getEncryptedString());
		AES.decrypt(AES.getEncryptedString().trim());
		System.out.println("Decrypted : " + AES.getDecryptedString());
	}

}