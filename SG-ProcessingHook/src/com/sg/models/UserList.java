package com.sg.models;

import java.util.ArrayList;

public class UserList {
	
private ArrayList<User> users =	 new ArrayList<User>();
private Integer projectId;

	public ArrayList<User> getUsers() {
		return users;
	}
	
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public Integer getProjectId() {
	return projectId;
	}

	public void setProjectId(Integer projectId) {
	this.projectId = projectId;
	}
	
}
