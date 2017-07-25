package com.hxs.demo;

import io.swagger.annotations.ApiModelProperty;

public class SimpleUserBean {
	public SimpleUserBean() {
	}

	public SimpleUserBean(Integer id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	@ApiModelProperty(notes = "The user's id")
	private Integer id;
	@ApiModelProperty(notes = "The user's name")
	private String username;
	@ApiModelProperty(notes = "The user's password")
	private String password;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

}
