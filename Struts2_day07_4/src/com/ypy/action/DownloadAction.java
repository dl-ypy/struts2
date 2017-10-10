package com.ypy.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport{
	private String fileName;
	public String getFileName() {
		try {
			String str = new String(fileName.getBytes(), "ISO8859-1");
			return str;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String download() {
		return "download";
	}
	
	/**
	 * �ļ�����·��  ��������get+�����ļ���inputNameֵ����ĸ��д
	 * @return
	 */
	public InputStream getInputStream() {
		File f = new File("F:/upload/"+fileName);
		try {
			return new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
