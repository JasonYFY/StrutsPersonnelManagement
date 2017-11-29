package com.jason.action;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

public class UserValidateAction extends UserAction{
	
	
	//添加用户
	public String userRegister() throws Exception {
		getModel().setCreatedate(new Date());
		boolean isRegister = getUserS().register(getModel());
		HttpServletRequest request = ServletActionContext.getRequest();

		if (isRegister) {
			request.setAttribute("errorA", "no");
		} else {
			request.setAttribute("errorA", "yes");
		}
		return SUCCESS; //返回addUser.jsp
	}
	
	
	
	//修改用户处理
	public String updateUser() throws Exception {
		ValueStack vs = ActionContext.getContext().getValueStack();
		boolean update = getUserS().update(getModel());
		
		if (update) {
			vs.set("errorU", "no");
		} else {
			vs.set("errorU", "yes");
		}
		return SUCCESS;
	}
	

}
