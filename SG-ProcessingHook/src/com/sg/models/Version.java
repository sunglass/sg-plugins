package com.sg.models;

import java.util.ArrayList;

public class Version {

	private String id;
	private String message;
	private ArrayList<String> parents = new ArrayList<String>();
	private String timestamp;
	private String createdAt;
	private ArrayList<Author> authors = new ArrayList<Author>();
	private String origChecksum;
	private String coverImage;


	public String getId() {
	return id;
	}

	public void setId(String id) {
	this.id = id;
	}

	public String getMessage() {
	return message;
	}

	public void setMessage(String message) {
	this.message = message;
	}

	public ArrayList<String> getParents() {
	return parents;
	}

	public void setParents(ArrayList<String> parents) {
	this.parents = parents;
	}

	public String getTimestamp() {
	return timestamp;
	}

	public void setTimestamp(String timestamp) {
	this.timestamp = timestamp;
	}

	public String getCreatedAt() {
	return createdAt;
	}

	public void setCreatedAt(String createdAt) {
	this.createdAt = createdAt;
	}

	public ArrayList<Author> getAuthors() {
	return authors;
	}

	public void setAuthors(ArrayList<Author> authors) {
	this.authors = authors;
	}

	public String getOrigChecksum() {
	return origChecksum;
	}

	public void setOrigChecksum(String origChecksum) {
	this.origChecksum = origChecksum;
	}

	public String getCoverImage() {
	return coverImage;
	}

	public void setCoverImage(String coverImage) {
	this.coverImage = coverImage;
	}

	
}
