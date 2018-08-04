package com.pccwglobal.assessment.marssi.service;

import java.util.List;

import com.pccwglobal.assessment.marssi.pojo.CreateUserRequest;
import com.pccwglobal.assessment.marssi.pojo.UpdateUserRequest;
import com.pccwglobal.assessment.marssi.pojo.User;

public interface UserService {
	
	public List<User> getUsers(Integer page, Integer size);
	public User createUser(CreateUserRequest createUserRequest);
	public User getUserById(String id);
	public User updateUser(String id, UpdateUserRequest updateUserRequest);
	public User deleteUserById(String id);

	
}
