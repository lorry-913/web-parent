package com.midea.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class PasswordUtil {
	private static final String SPECIAL_CHARACTER = "~!@#$%^&*";

	/**
	 * 判断密码是否是个合法密码
	 *
	 * @param min
	 *            最小长度
	 * @param max
	 *            最大长度
	 * @param safe_level
	 *            密码强度级别
	 * @param password
	 *            密码
	 * @return
	 */
	public static boolean isLegalPassword(int min, int max, int safe_level, String password) {
		Map<String, String> map = new HashMap<String, String>();
		int pwdLength = password.length();// 密码长度
		if (pwdLength < min || pwdLength > max) {
			return false;
		}
		for (int i = 0; i < pwdLength; i++) {
			String s = password.substring(i, i + 1);
			int A = password.charAt(i);
			if (A >= 48 && A <= 57) {// 数字
				map.put("数字", "数字");
			} else if (A >= 65 && A <= 90) {// 大写
				map.put("大写", "大写");
			} else if (A >= 97 && A <= 122) {// 小写
				map.put("小写", "小写");
			} else if (SPECIAL_CHARACTER.contains(s)) {
				map.put("特殊", "特殊");
			} else {
				map.put("特殊", "特殊");
				return false;
			}
		}
		Set<String> sets = map.keySet();
		int pwdSize = sets.size();// 密码字符种类数
		if (pwdSize >= safe_level) {
			return true;// 强密码
		} else {
			return false;// 弱密码
		}
	}

	/**
	 * 根据密码长度范围及安全级别获取随机密码
	 *
	 * @param min
	 *            最短长度
	 * @param max
	 *            最长长度
	 * @param safe_level
	 *            安全级别
	 * @return
	 */
	public static String getRandomPassword(int min, int max, int safe_level) {
		int length = getRadomInt(min, max);
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; i++)
			buffer.append((char) getChar(safe_level));
		return buffer.toString();
	}

	/**
	 * 根据密码安全级别随机获取字符
	 *
	 * @param safe_level
	 * @return
	 */
	private static char getChar(int safe_level) {
		char x = 1;
		int index = 1;
		// 按照字符等级强度取ascii值
		final int levelIndex = getRadomInt(0, 100000) % safe_level;
		switch (safe_level) {
		case 1:
			x = getNum();
			break;
		case 2:
			if (levelIndex == index)
				x = getNum();
			else
				x = getLetter();
			break;
		case 3:
			if (levelIndex == 0)
				x = getNum();
			else if (levelIndex == index)
				x = getLetter();
			else
				x = getUpper();
			break;
		case 4:
			if (levelIndex == 0)
				x = getNum();
			else if (levelIndex == index)
				x = getLetter();
			else if (levelIndex == index * 2)
				x = getUpper();
			else
				x = getSpecialCharacter();
			break;
		default:
		}
		return x;
	}

	/**
	 * 随机获取一个小写字母
	 *
	 * @param args
	 */
	private static char getLetter() {
		char c = (char) getRadomInt(97, 122);
		return c;
	}

	/**
	 * 随机获取一个大写字母
	 *
	 * @param args
	 */
	private static char getUpper() {
		char c = (char) getRadomInt(65, 90);
		return c;
	}

	/**
	 * 随机获取一个0-9的数字
	 *
	 * @return
	 */
	private static char getNum() {
		char c = (char) getRadomInt(48, 57);
		return c;
	}

	/**
	 * 随机获取一个特殊字符
	 *
	 * @return
	 */
	private static char getSpecialCharacter() {
		char c = SPECIAL_CHARACTER.charAt(getRadomInt(0, SPECIAL_CHARACTER.length() - 1));
		return c;
	}

	/**
	 * 获取一个范围内的随机数字
	 *
	 * @return
	 */
	private static int getRadomInt(int min, int max) {
		return new Random().nextInt(max - min + 1) + min;
	}

	public static void main(String[] args) {
		System.out.println(getRandomPassword(6, 10, 4));
	}
}
