package com.pccwglobal.assessment.marssi.controller;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import com.pccwglobal.assessment.marssi.pojo.CreateUserRequest;
import com.pccwglobal.assessment.marssi.pojo.UpdateUserRequest;
import com.pccwglobal.assessment.marssi.pojo.User;
import com.pccwglobal.assessment.marssi.service.UserService;
import com.pccwglobal.assessment.marssi.utils.JsonUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private UserService userService;
	private RequestBuilder request;

	//Test listing all users
	@Test
	public void testListUsers() throws Exception {
		// list all users without paging
		String expectedResult = JsonUtils.objectToJson(userService.getUsers(0, 0));
		request = get("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		mvc.perform(request).andExpect(status().isOk()).andExpect(content().string(equalTo(expectedResult)));
		// list all users with paging
		expectedResult = JsonUtils.objectToJson(userService.getUsers(1, 2));
		request = get("/users?page=1&size=2");
		mvc.perform(request).andExpect(status().isOk()).andExpect(content().string(equalTo(expectedResult)));
	}

	//Test creating a new user
	@Test
	public void testCreateUser() throws Exception {
		CreateUserRequest userRequest = new CreateUserRequest();
		userRequest.setEmail("newuser@newuser.com");
		userRequest.setName("newuser");
		userRequest.setPassword("67890");
		userRequest.setUsername("newuser");
		request = put("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(JsonUtils.objectToJson(userRequest));
		mvc.perform(request) .andExpect(status().isOk())
		 .andExpect(jsonPath("$.email").value(userRequest.getEmail()))
		 .andExpect(jsonPath("$.name").value(userRequest.getName()))
		 .andExpect(jsonPath("$.id", not("")))
		 .andExpect(jsonPath("$.password").value(userRequest.getPassword()))
		 .andExpect(jsonPath("$.username").value(userRequest.getUsername()));
	}
	
	//Test finding a user via id
	@Test
	public void testGetUserById() throws Exception {
		String id = "1";
		request = get("/users/{id}",id)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		String expectedResult = JsonUtils.objectToJson(userService.getUserById(id));
		mvc.perform(request).andExpect(status().isOk())
		.andDo(print())		
		.andExpect(content().string(equalTo(expectedResult)));
	}
	
	//Test updating a user
		@Test
		public void testUpdateUser() throws Exception {
			String id = "2";
			UpdateUserRequest userRequest = new UpdateUserRequest();
			userRequest.setEmail("updateduser@updateduser.com");
			userRequest.setName("updateduser");
			userRequest.setPassword("78901");
			userRequest.setUsername("updateduser");
			request = post("/users/{id}",id)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.content(JsonUtils.objectToJson(userRequest));
			mvc.perform(request).andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.email").value(userRequest.getEmail()))
			.andExpect(jsonPath("$.id").value(id))
			.andExpect(jsonPath("$.name").value(userRequest.getName()))
			.andExpect(jsonPath("$.password").value(userRequest.getPassword()))
			.andExpect(jsonPath("$.username").value(userRequest.getUsername()));
		}
	
		//Test deleting a user
		@Test
		public void testDeleteUserById() throws Exception {
			String id = "3";
			User user = userService.getUserById(id);
			request = delete("/users/{id}",id)					
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON);
			mvc.perform(request).andExpect(status().isOk())
			.andExpect(content().string(equalTo(JsonUtils.objectToJson(user))));
			assertNull(userService.getUserById(id));
		}
	}
