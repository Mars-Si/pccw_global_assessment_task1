package com.pccwglobal.assessment.marssi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.pccwglobal.assessment.marssi.pojo.CreateUserRequest;
import com.pccwglobal.assessment.marssi.pojo.UpdateUserRequest;
import com.pccwglobal.assessment.marssi.pojo.User;
import com.pccwglobal.assessment.marssi.repo.UserJpaRepository;
import com.pccwglobal.assessment.marssi.repo.UserPagingAndSortingRepository;
import com.pccwglobal.assessment.marssi.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserPagingAndSortingRepository psRepo;

	@Override
	public List<User> getUsers(Integer page, Integer size) {
		if (page > 0 && size > 0) {
			Page<User> pageUser = psRepo.findAll(PageRequest.of(page - 1, size));
			return pageUser.getContent();
		}
		List<User> users = new ArrayList<>();
		psRepo.findAll().forEach(u -> users.add(u));
		return users;
	}

	@Override
	public User createUser(CreateUserRequest createUserRequest) {
		User user = new User();
		BeanUtils.copyProperties(createUserRequest, user);
		user.setId(UUID.randomUUID().toString().replace("-", ""));
		user = psRepo.save(user);
		return user;
	}

	@Override
	public User getUserById(String id) {
		Optional<User> optional = psRepo.findById(id);
		if (optional.isPresent())
			return optional.get();
		return null;
	}

	@Override
	public User updateUser(String id, UpdateUserRequest updateUserRequest) {
		User user = getUserById(id);
		if (user != null) {
			String email = updateUserRequest.getEmail();
			if (!StringUtils.isEmpty(email) && !user.getEmail().equals(email))
				user.setEmail(email);
			String name = updateUserRequest.getName();
			if (!StringUtils.isEmpty(name) && !user.getEmail().equals(name))
				user.setName(name);
			String password = updateUserRequest.getPassword();
			if (!StringUtils.isEmpty(password) && !user.getEmail().equals(password))
				user.setPassword(password);
			String username = updateUserRequest.getUsername();
			if (!StringUtils.isEmpty(username) && !user.getEmail().equals(username))
				user.setUsername(username);
			user = psRepo.save(user);
			return user;
		}
		return null;
	}

	@Override
	public User deleteUserById(String id) {
		User user = getUserById(id);
		if (user != null) {
			psRepo.delete(user);
			return user;
		}
		return null;
	}

}
