package com.ypy.struts2.action;

import com.opensymphony.xwork2.Action;

public class UserAction implements Action{
	public String query() {
		System.out.println("--------query--------");
		return "success";
	}
	
	public String save() {
		System.out.println("--------save--------");
		return "success";
	}

	//�Ҳ�����Ӧ��resultĬ�Ϸ��ص�
	@Override
	public String execute() throws Exception {
		System.out.println("---------------execute");
		return SUCCESS;  //�ڽӿ��ж�����һЩ��������ֹд��
	}
}
