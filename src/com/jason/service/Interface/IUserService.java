package com.jason.service.Interface;

import com.jason.domain.User;

public interface IUserService {
	// 注册
	public boolean register(User user);
	// 登录验证
	public boolean login(User user);

}
