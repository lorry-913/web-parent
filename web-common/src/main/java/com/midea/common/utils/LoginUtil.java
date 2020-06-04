package com.midea.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginUtil {
	public static boolean checkIsLogin(HttpServletRequest request, HttpServletResponse response) {
		if(null != request.getSession().getAttribute("USER_INFO")) {
		    return true;
		} else {
			return false;
		}
	}
}
