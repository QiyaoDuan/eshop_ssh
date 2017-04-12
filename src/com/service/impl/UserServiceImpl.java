package com.service.impl;

import org.springframework.stereotype.Service;

import com.model.User;
import com.service.IUserService;

@Service("userService")  
public class UserServiceImpl extends BaseServiceImpl<User> implements  
        IUserService {  
  
    @Override  
    public User login(User user) {  
        String hql = "from User u where u.login=:login and u.pass=:pass";  
        return (User) getSession().createQuery(hql) 
            .setString("login", user.getLogin())  
            .setString("pass", user.getPass()) 
            .uniqueResult();  
    }  
  
} 