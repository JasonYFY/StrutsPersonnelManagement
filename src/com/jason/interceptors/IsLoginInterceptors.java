package com.jason.interceptors;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import com.jason.domain.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.opensymphony.xwork2.util.ValueStack;

public class IsLoginInterceptors extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation arg) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("activeUser");
		if(user==null) {
			session.put("notLogin", "请先登录！");
			return "input";
		}
		return arg.invoke();
	}

}
