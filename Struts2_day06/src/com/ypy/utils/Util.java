package com.ypy.utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class Util {
	/**
	 * ��ֹStringתint����
	 * @param str Ҫת��ֵ
	 * @param defval ����ʱ���ص�Ĭ��ֵ
	 * @return
	 */
	public static int strToInt(String str, int defval) {
		int result = 0;
		try {
			result = Integer.parseInt(str);
		} catch (Exception e) {
			return defval;
		}
		return result;
	}
	
	/**
	 * ��ֹStringתdouble����
	 * @param str Ҫת��ֵ
	 * @param defval ����ʱ���ص�Ĭ��ֵ
	 * @return
	 */
	public static double strToDoub(String str, double defval) {
		double result = 0;
		try {
			result = Double.parseDouble(str);
		} catch (Exception e) {
			return defval;
		}
		return result;
	}

	/**
	 * �ж��ַ����Ƿ�Ϊ��
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str!=null && !"".equals(str)) {
			return false;
		}
		return true;
	}
	
	/**
	 * �õ���ǰҳ��
	 * @param request
	 * @return
	 */
	public static int getCurrentPage(HttpServletRequest request) {
		String currentPage = request.getParameter("currentPage");
		if (!isEmpty(currentPage)) {
			return strToInt(currentPage, 1);
		}
		return 1;
	}
	
	/**
	 * �õ�ÿҳ��ʾ�ļ�¼��
	 * @param request
	 * @return
	 */
	public static int getPageSize(HttpServletRequest request) {
		String pageSize = request.getParameter("pageSize");
		if (!isEmpty(pageSize)) {
			return strToInt(pageSize, 5);
		}
		return 5;
	}
	
	/**
	 * ��ȡʱ���
	 * @return
	 */
	public static String getTime() {
		Date date = new Date();
		return date.getTime()+"";
	}
	
	/**
	 * �õ���׺��
	 * @param fileName
	 * @return
	 */
	public static String getName(String fileName) {
		if (!isEmpty(fileName)) {
			int index = fileName.indexOf(".");//�õ�"."��λ��
			return fileName.substring(index);
		}
		return null;
	}
}
