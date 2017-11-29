package com.jason.action;

import java.util.ArrayList;


import com.jason.service.JobService;
import com.jason.service.domain.EmployeeS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class EmployeeValidateAction extends EmployeeAction{
	
	//添加员工
	public String employeeAdd() throws Exception {
		ValueStack vs = ActionContext.getContext().getValueStack();
		boolean pos = getEmployeeService().addEmployee(getModel());
		if(pos) {
			vs.set("errorA", "no");
		}else {
			vs.set("errorA", "yes");
		}
		return SUCCESS;
	}
	
	//修改员工
	public String employeeUpdate() throws Exception {
		ValueStack vs = ActionContext.getContext().getValueStack();
		boolean pos = getEmployeeService().udateEmployee(getModel());
		getListData();
		if(pos) {
			vs.set("errorU", "no");
		}else {
			vs.set("errorU", "yes");
		}
		return SUCCESS;
	}
	
	@Override
	public void validate() {
		getListData();
		super.validate();
	}

}
