package com.ypy.struts2.action;

import org.apache.struts2.interceptor.validation.SkipValidation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ypy.struts2.model.UserModel;

public class User1Action extends ActionSupport implements ModelDriven<UserModel>{
	private UserModel user = new UserModel();
	
	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}
	
	//@SkipValidation     //��ʾ�˷�����������֤
	public String query() {
		System.out.println("--------query--------");
		return SUCCESS;
	}

	public String login() {
		System.out.println("--------login--------");
		return SUCCESS;
	}

	@Override
	public UserModel getModel() {
		return user;
	}
	
	@Override
	public void validate() {  //����validate�����Ҫ��֤�ķ������Ϳ���֤�ض��ķ���
		super.validate();
		if ("".equals(user.getUsername()) || user.getUsername()==null) {
			System.out.println("-------------validate-------------");
			this.addActionError("�û�������Ϊ�գ�");
		}
	}
	
}
