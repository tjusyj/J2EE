package com.syj.service.inter;

import java.util.List;

import com.syj.domain.User;

public interface UserServiceInterface extends BaseInterface{

	public User checkUser(User user);
	public List<User> getUsers();
}
