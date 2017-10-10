package com.ypy.utils;
/**
 * �õ�cookie�Ĺ�����
 */
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

//����cookie
public class CookieUtil {
	public static String getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie.getValue();
				}
			}
		}
		return "";
	}
}

