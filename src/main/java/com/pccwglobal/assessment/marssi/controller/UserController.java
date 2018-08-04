package com.pccwglobal.assessment.marssi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pccwglobal.assessment.marssi.pojo.CreateUserRequest;
import com.pccwglobal.assessment.marssi.pojo.UpdateUserRequest;
import com.pccwglobal.assessment.marssi.pojo.User;
import com.pccwglobal.assessment.marssi.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "User Management" })
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@ApiOperation(value = "list all users", consumes = "application/json", produces = "application/json;charset=UTF-8")
	public List<User> listUsers(@RequestParam(defaultValue = "0", required = false) Integer page,
			@RequestParam(defaultValue = "0", required = false) Integer size) {
		try {
			return userService.getUsers(page, size);
		} catch (Exception e) {
			return null;
		}
	}

	@PutMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@ApiOperation(value = "Register a new user", consumes = "application/json", produces = "application/json;charset=UTF-8")
	public User createUser(@RequestBody(required = true) CreateUserRequest request) {
 		try {
			return userService.createUser(request);
		} catch (Exception e) {
			return null;
		}
	}

	@GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@ApiOperation(value = "Find a user via id", consumes = "application/json", produces = "application/json;charset=UTF-8")
	public User getUserById(@PathVariable(required = true) String id) {
		try {
			return userService.getUserById(id);
		} catch (Exception e) {
			return null;
		}
	}

	@PostMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@ApiOperation(value = "Update a user", consumes = "application/json", produces = "application/json;charset=UTF-8")
	public User updateUserById(@PathVariable(required = true) String id,
			@RequestBody(required = true) UpdateUserRequest request) {
		try {
			return userService.updateUser(id, request);
		} catch (Exception e) {
			return null;
		}
	}

	@DeleteMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@ApiOperation(value = "Delete a user", consumes = "application/json", produces = "application/json;charset=UTF-8")
	public User updateUserById(@PathVariable(required = true) String id) {
		try {
			return userService.deleteUserById(id);
		} catch (Exception e) {
			return null;
		}
	}
}
