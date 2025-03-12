package br.portaldotenis.portal.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.portaldotenis.portal.exception.EncryptationException;

public class Encrypt {
	/**
	 * Método que criptografa uma String para MD5
	 * 
	 * @param string
	 *            - String pura, sem criptografia
	 * @return String criptografada em MD5
	 * @throws EncryptationException
	 *             - caso haja erro ao criptografar a String
	 */
	public static String toMD5(String string) throws EncryptationException {
		if (string == null || string.equals("")) {
			return null;
		}
		MessageDigest md;
		BigInteger hash;
		try {
			md = MessageDigest.getInstance("MD5");
			hash = new BigInteger(1, md.digest(string.getBytes("UTF-8")));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException   e) {
			throw new EncryptationException();
		} 

		return String.format("%32x", hash);
	}
	public static void main(String args[]) throws EncryptationException{
		System.out.println(toMD5("1111111"));
	}
}
