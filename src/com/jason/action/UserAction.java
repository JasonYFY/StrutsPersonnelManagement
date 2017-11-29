package com.jason.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.jason.domain.PageModel;
import com.jason.domain.User;
import com.jason.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	private User user = new User();
	private UserService userS = new UserService();
	private PageModel pageModel = new PageModel();
	private String[] userIds;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public UserService getUserS() {
		return userS;
	}

	public void setUserS(UserService userS) {
		this.userS = userS;
	}

	public PageModel getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}

	public String[] getUserIds() {
		return userIds;
	}

	public void setUserIds(String[] userIds) {
		this.userIds = userIds;
	}


	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return this.user;
	}
	//登录界面
	public String userLoginForm() throws Exception {
		return SUCCESS;
	}
	//用户查询界面
	public String queryUserForm() throws Exception {
		userS = new UserService();
		ArrayList<User> allList = userS.QueryUserAll(getPageModel());
		ValueStack vs = ActionContext.getContext().getValueStack();
		
		vs.set("userList", allList);
		return SUCCESS;
	}
	//用户查询处理
	public String queryUser() throws Exception {
		ValueStack vs = ActionContext.getContext().getValueStack();
		ArrayList<User> list = userS.QueryUserByPage(getModel(), getPageModel());
		if(list!=null) {
			//查询结果
			vs.set("userList", list);
		}
		return SUCCESS;
	}
	//用户登录处理
	public String userLogin() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		ValueStack vs = ActionContext.getContext().getValueStack();
		boolean isLogin = userS.login(getModel());
		
		if (isLogin) {
			session.put("activeUser", getModel());
			return SUCCESS;  //登录成功，返回主页
		} else {
			vs.set("error", "yes");
			return INPUT;
		}
	}
	//用户退出登录
	public String userLoginout() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove("activeUser");
		return SUCCESS;
	}
	
	/*//添加用户
	public String userRegister() throws Exception {
		getModel().setCreatedate(new Date());
		boolean isRegister = userS.register(getModel());
		HttpServletRequest request = ServletActionContext.getRequest();

		if (isRegister) {
			request.setAttribute("errorA", "no");
		} else {
			request.setAttribute("errorA", "yes");
		}
		return SUCCESS; //返回addUser.jsp
	}*/
	
	//修改用户的界面
	public String updateUserForm() throws Exception {
		ArrayList<User> list = userS.QueryUser(getModel());
		if(list!=null&&list.size()>0) {
			User u = list.get(0);
			setUser(u);
		}
		return SUCCESS;
	}
	
	/*//修改用户处理
	public String updateUser() throws Exception {
		ValueStack vs = ActionContext.getContext().getValueStack();
		boolean update = userS.update(getModel());
		
		if (update) {
			vs.set("errorU", "no");
		} else {
			vs.set("errorU", "yes");
		}
		return SUCCESS;
	}*/
	
	//删除用户处理
	public String userDelete() throws Exception {
		ValueStack vs = ActionContext.getContext().getValueStack();
		if(getUserIds()!=null&&getUserIds().length>0) {
			vs.set("errorDelete", "ok");
			for(String s:getUserIds()) {
				User u = new User();
				u.setId(Integer.parseInt(s));
				boolean isDelete = userS.delete(u);
				if(!isDelete) {
					vs.set("errorDelete", "yes");
					break;
				}
			}
		}else {
			vs.set("errorDelete", "no");
		}
		return SUCCESS;
	}
	
	//检查登录名是否重复
	public String checkLoginname() throws Exception {
        boolean pos = userS.isSameLoginname(getModel());
//        System.out.println("验证登录名："+getModel().getLoginname());
        if(pos) {
        	getModel().setLoginname("登录名已被使用");
        }else {
        	getModel().setLoginname("pass");
        }
		return SUCCESS;
	}
	
	
	/*public void doValidate() {
		if(user.getPassword()==null
				||user.getPassword().trim().equals("")) {
			addFieldError("password", "密码不能为空");
		}
	}*/

}
