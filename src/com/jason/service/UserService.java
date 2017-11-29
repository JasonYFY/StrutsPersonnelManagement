package com.jason.service;

import java.util.ArrayList;

import com.jason.dao.DaoFactory;
import com.jason.dao.UserDao;
import com.jason.dao.Interface.IUserDao;
import com.jason.domain.PageModel;
import com.jason.domain.User;
import com.jason.service.Interface.IUserService;

public class UserService implements IUserService{
	private IUserDao userDao;
	public UserService() {
		userDao = DaoFactory.getUserDao();
	}

	@Override
	public boolean register(User user) {
		// TODO Auto-generated method stub
		if(!isSameLoginname(user)) {
			int pos = userDao.insertUser(user);
			if(pos>0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean login(User user) {
		// TODO Auto-generated method stub
		UserDao dao = (UserDao) userDao;
		if(dao.doLogin(user)!=null) {
			return true;
		}
		return false;
	}
	public boolean update(User user) {
		int pos = userDao.updateUser(user);
		if(pos>0) {
			return true;
		}
		return false;
	}
	public ArrayList<User> QueryUser(User user) {
		ArrayList<User> list = userDao.QueryUser(user);
		if(list!=null&&list.size()>0) {
			return list;
		}
		return null;
	}
	
	public ArrayList<User> QueryUserAll(PageModel page) {
		UserDao dao = (UserDao) userDao;
		User user = new User();
		page.setTotalRecordSum(QueryUserCount(user));
		page.setPageIndex(1);
		return dao.QueryUserByPage(user, page);
	}
	
	public boolean delete(User user) {
		// TODO Auto-generated method stub
		int pos = userDao.deleteUser(user);
		if(pos>0) {
			return true;
		}
		return false;
	}
	public boolean isSameLoginname(User user) {
		UserDao dao = (UserDao) userDao;
		return dao.querySame(user);
	}
	
	public Integer QueryUserCount(User user){
		UserDao dao = (UserDao) userDao;
		return dao.QueryUserCount(user);
	}
	
	public ArrayList<User> QueryUserByPage(User user,PageModel page){
		UserDao dao = (UserDao) userDao;
		page.setTotalRecordSum(QueryUserCount(user));
		return dao.QueryUserByPage(user, page);
	}

}
