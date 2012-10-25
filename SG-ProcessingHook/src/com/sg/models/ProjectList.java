package com.sg.models;

import java.util.ArrayList;

public class ProjectList {
	
	private ArrayList<Project> owner =	 new ArrayList<Project>();
	private ArrayList<Project> admin = 	 new ArrayList<Project>();
	private ArrayList<Project> editor =	 new ArrayList<Project>();
	private ArrayList<Project> guest = 	 new ArrayList<Project>();
	
	public ArrayList<Project> getOwner() {
	return owner;
	}

	public void setOwner(ArrayList<Project> owner) {
	this.owner = owner;
	}

	public ArrayList<Project> getAdmin() {
	return admin;
	}

	public void setAdmin(ArrayList<Project> admin) {
	this.admin = admin;
	}

	public ArrayList<Project> getEditor() {
	return editor;
	}

	public void setEditor(ArrayList<Project> editor) {
	this.editor = editor;
	}

	public ArrayList<Project> getGuest() {
	return guest;
	}

	public void setGuest(ArrayList<Project> guest) {
	this.guest = guest;
	}
	
}
