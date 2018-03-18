package jain.ritvik.utils.crypto;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import jain.ritvik.utils.common.Constants;
import jain.ritvik.utils.exceptions.BlankStringException;
import jain.ritvik.utils.validators.Evaluator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class Transformer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Evaluator eval = null;
	private BASE64Encoder encoder = null;
	private BASE64Decoder decoder = null;
	
	
	public Transformer() {
		eval = new Evaluator();
	}

	public void setEncryptionKey(String sEncryptionKey) {
		Constants.CIFER_TEXT = sEncryptionKey;
	}

	public void setAlgorithm(String sAlgorithm) {
		Constants.ALGORITHM = sAlgorithm;
	}

	public final String decode(String secret) throws NoSuchPaddingException,
			NoSuchAlgorithmException, InvalidKeyException, BadPaddingException,
			IllegalBlockSizeException, BlankStringException {

		if(Constants.CIFER_TEXT == null){
			Constants.CIFER_TEXT = System.getenv("DECODE_KEY");
		}
		
		if (eval.checkBlankString(secret)) {

			byte[] kbytes = Constants.CIFER_TEXT.getBytes();

			SecretKeySpec key = new SecretKeySpec(kbytes, Constants.ALGORITHM);

			BigInteger n = new BigInteger(secret, 16);
			byte[] encoding = n.toByteArray();

			Cipher cipher = Cipher.getInstance(Constants.ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);

			byte[] decode = cipher.doFinal(encoding);

			return new String(decode);
		}

		return secret;
	}

	public final String encode(String secret) throws NoSuchPaddingException,
			NoSuchAlgorithmException, InvalidKeyException, BadPaddingException,
			IllegalBlockSizeException, BlankStringException {

		if(Constants.CIFER_TEXT == null){
			Constants.CIFER_TEXT = System.getenv("DECODE_KEY");
		}
		
		if (eval.checkBlankString(secret)) {

			byte[] kbytes = Constants.CIFER_TEXT.getBytes();

			SecretKeySpec key = new SecretKeySpec(kbytes, Constants.ALGORITHM);

			Cipher cipher = Cipher.getInstance(Constants.ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);

			byte[] encoding = cipher.doFinal(secret.getBytes());
			BigInteger n = new BigInteger(encoding);
			return n.toString(16);
		}
		return secret;
	}

	public String base64Encode(String secret) throws Exception {
		String encodedBytes = null;
		try {
			encoder = new BASE64Encoder();
			encodedBytes = encoder.encodeBuffer(secret.getBytes());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			encoder = null;
		}
		return encodedBytes;
	}

	public String base64Decode(String encryptedData) throws Exception {
		byte[] decodedBytes = null;

		try {
			decoder = new BASE64Decoder();
			decodedBytes = decoder.decodeBuffer(encryptedData);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			decoder = null;
		}
		
		return new String(decodedBytes);
	}

}
