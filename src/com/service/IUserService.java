package com.service;

import com.model.User;

/**
 * @author Qiyao
 *
 * @Date 2017-4-10
 * 
 * @Version 1.0.1
 */
public interface IUserService {
	//用户登陆，成功返回该User  
    public User login(User user); 
}