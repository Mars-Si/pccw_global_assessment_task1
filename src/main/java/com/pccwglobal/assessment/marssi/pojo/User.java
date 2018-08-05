package com.pccwglobal.assessment.marssi.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class User {

	@Column(nullable = false)
	@NotBlank(message = "Email cannot be empty.")
	@Email(message = "Please provide a valid email.")
	private String email;

	@Id
	@Column(nullable = false, unique = true)
	private String id;

	@Column(nullable = false)
	@NotBlank(message = "Name cannot be empty.")
	private String name;

	@Column(nullable = false)
	@NotBlank(message = "Password cannot be empty.")
	private String password;

	@Column(nullable = false)
	@NotBlank(message = "Username cannot be empty.")
	private String username;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
