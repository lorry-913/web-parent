package com.midea.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderUtil {

	public static String generateOrder(String prefix) {

		Integer suffix = (int) ((Math.random() * 9 + 1) * 1000);

		String time = getDateTime();

		return prefix + time + suffix;
	}

	private static String getDateTime() {
		DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}

	public static void main(String[] args) {
		System.out.println(generateOrder("CHRW"));
	}

}
