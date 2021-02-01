package com.care.mvc.common.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptUtil {

	public static String oneWays(String message, String algorithm) {

		String encMessage = "";

		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			byte[] bytes = message.getBytes(Charset.forName("UTF-8"));

			md.update(bytes);

			encMessage = Base64.getEncoder().encodeToString(md.digest());

			System.out.println("Original Message : " + message + " >>>>> " + algorithm +" Encoding Message : " + encMessage);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return encMessage;
	}
}
