package com.gx.service;

import com.gx.po.UserPo;
//登入业务接口
public interface UserService {

	public UserPo selectLogin(UserPo user);
	
}
