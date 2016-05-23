package com.ssh.shop.service;



import com.ssh.shop.model.User;

public interface UserService extends BaseService<User>{
	
	public User login(User user);
}
