package com.ypy.action;

import java.util.List;
import org.apache.struts2.interceptor.validation.SkipValidation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ypy.model.UserModel;
import com.ypy.service.IUserService;
import com.ypy.service.impl.UserServiceImpl;

public class UserAction extends ActionSupport implements ModelDriven<UserModel>{
	private UserModel user = new UserModel();
	private IUserService impl = new UserServiceImpl();
	private List<UserModel> list;
	private UserModel u;
	
	public List<UserModel> getList() {
		return list;
	}

	public void setList(List<UserModel> list) {
		this.list = list;
	}
	
	@SkipValidation
	public String query() {
		list = impl.query();
		return SUCCESS;
	}
	
	public String save() {
		impl.save(user);
		return "query";
	}
	
	@SkipValidation
	public String delete() {
		impl.delete(user.getUserid());
		return "query";
	}
	
	@SkipValidation   //����Ҫ��֤
	public String updatePage() {
		setU(impl.queryById(user.getUserid()));   //��ѯ��Ҫ�޸ĵļ�¼
		ActionContext.getContext().getValueStack().push(u);   //��u�����Էŵ�ֵջ����
		return "update";
	}
	
	public String update() {
		impl.update(user);
		return "query";
	}

	@Override
	public UserModel getModel() {
		return user;
	}

	public UserModel getU() {
		return u;
	}

	public void setU(UserModel u) {
		this.u = u;
	}

}
