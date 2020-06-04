package com.midea.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES128 {
	static final String KEY_ALGORITHM = "AES";
	static final String CIPHER_ECB_PKCS5 = "AES/ECB/PKCS5Padding";

	/**
	 * 加密
	 * @param src
	 * @param seed
	 * @return
	 */
	public static String encrypt(String src, String seed) {
		try {
			byte[] result = encode(src.getBytes("utf-8"), seed.getBytes());
			return bytesToHexString(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * @param decryptSrc
	 * @param seed
	 * @return
	 */
	public static String decrypt(String decryptSrc, String seed) {
		try {
			byte[] enc = hexStringToBytes(decryptSrc);
			byte[] result = decode(enc, seed.getBytes());
			if (result != null) {
				return new String(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static byte[] encode(byte[] clear, byte[] raw) {
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
			Cipher cipher = Cipher.getInstance(CIPHER_ECB_PKCS5);
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(clear);
			return encrypted;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static byte[] decode(byte[] encrypted, byte[] raw) {
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
			Cipher cipher = Cipher.getInstance(CIPHER_ECB_PKCS5);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] decrypted = cipher.doFinal(encrypted);
			return decrypted;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	private static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	public static void main(String args[]) {
		String password = "123456";
		String seed = "sec8104a45db1459";

		//加密
		System.out.println("加密前：" + password);
		String encryptResultStr = encrypt(password, seed);
		System.out.println("加密后：" + encryptResultStr);
		//解密
		String decryptResult = decrypt("b6f96baac2c8d6576572957bf49e9fd9c97d1a49108c5dc564babc08cf030d751ac7994eb289fbd6cdcca7f22b352f10e519e9f1072398338d165d966a9d914a","20160509230aBcDW");
		System.out.println("解密后：" + decryptResult);
	}
}
