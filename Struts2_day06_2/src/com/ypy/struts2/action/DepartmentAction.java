package com.ypy.struts2.action;

import java.util.ArrayList;
import java.util.List;

import com.ypy.struts2.model.DepartmentModel;

public class DepartmentAction {
	private List<DepartmentModel> list;
	
	public List<DepartmentModel> getList() {
		return list;
	}

	public void setList(List<DepartmentModel> list) {
		this.list = list;
	}

	public String query() {
		list = new ArrayList<>();
		list.add(new DepartmentModel(1,"���²�"));
		list.add(new DepartmentModel(2,"����"));
		list.add(new DepartmentModel(3,"���"));
		return "success";
	}
}
