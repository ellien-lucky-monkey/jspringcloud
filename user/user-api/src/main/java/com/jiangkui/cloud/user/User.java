package com.jiangkui.cloud.user;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * package:    com.jiangkui.cloud
 * className:  User
 * date:       2017/09/28 04:12
 * author      jiangkui  😛😛😛😛😛  (o>=<o)
 * description
 */
@Entity
public class User {

	@Id
	private Long id;

	private String username;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}