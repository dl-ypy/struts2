package com.ypy.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class UserInterceptor extends MethodFilterInterceptor{

	@Override
	public void destroy() {
		System.out.println("------destroy--------");
	}

	@Override
	public void init() {
		System.out.println("-----------init----------");
	}

	/*@Override
	public String intercept(ActionInvocation ar) throws Exception {
		System.out.println("---------intercept------------");
		Object obj = ServletActionContext.getRequest().getSession().getAttribute("user_login");
		HttpServletResponse response = ServletActionContext.getResponse();
		if (obj != null) {
			ar.invoke();
		} else {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write("<script type=\"text/javascript\">alert('���ȵ�½��')</script>");
			out.flush();
			out.close();
		}
		return null;
	}*/

	//�̳�MethodFilterInterceptor����ָ�����صķ���
	@Override
	protected String doIntercept(ActionInvocation ar) throws Exception {
		System.out.println("---------intercept------------");
		Object obj = ServletActionContext.getRequest().getSession().getAttribute("user_login");
		HttpServletResponse response = ServletActionContext.getResponse();
		if (obj != null) {
			ar.invoke();
		} else {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write("<script type=\"text/javascript\">alert('���ȵ�½��')</script>");
			out.flush();
			out.close();
		}
		return null;
	}

}
