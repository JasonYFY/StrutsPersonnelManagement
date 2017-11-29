package com.jason.dao.Interface;

import java.util.ArrayList;

import com.jason.domain.User;

public interface IUserDao {
	public int insertUser(User user);
	public int deleteUser(User user);
	public int updateUser(User user);
	public ArrayList<User> QueryUser(User user);
}
