package com.ritvik.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class MyCustomBasicDataSource extends BasicDataSource {

	private Logger logger = Logger.getLogger("org.ritvik.processLog");

	@Autowired
	private StringWriter stackTraceWriter;

	public MyCustomBasicDataSource() {
		super();
	}

	public synchronized void setPassword(String password) {
		try {
			super.setPassword(decode(password));
		} catch (Exception e) {
			e.printStackTrace(new PrintWriter(stackTraceWriter));
			logger.fatal(stackTraceWriter.toString());
			stackTraceWriter.flush();
		}
	}

	private String decode(String secret) throws NoSuchPaddingException, NoSuchAlgorithmException,
			InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

		String CIFER_TEXT = System.getenv("DECODE_KEY");
		String ALGORITHM = "Blowfish";
		
		if(secret != null && secret.length() > 0){

			byte[] kbytes = CIFER_TEXT.getBytes();

			SecretKeySpec key = new SecretKeySpec(kbytes, ALGORITHM);

			BigInteger n = new BigInteger(secret, 16);
			byte[] encoding = n.toByteArray();

			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);

			byte[] decode = cipher.doFinal(encoding);

			return new String(decode);
		}

		return secret;
	}

}