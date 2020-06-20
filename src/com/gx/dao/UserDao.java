package com.gx.dao;

import com.gx.po.UserPo;;
//登入接口
public interface UserDao {
	
	public UserPo selectLogin(UserPo user);
	
}
