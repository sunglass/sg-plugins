package com.sg.models;

import java.util.ArrayList;

public class Note {
	private Integer id;
	private String spaceId;
	private Integer projectId;
	private String noteData;
	private ArrayList<Attachment> attachments = new ArrayList<Attachment>();
	private String href;
	private String createdAt;
	private String modifiedAt;
	
	public Integer getId() {
	return id;
	}

	public void setId(Integer id) {
	this.id = id;
	}

	public String getSpaceId() {
	return spaceId;
	}

	public void setSpaceId(String spaceId) {
	this.spaceId = spaceId;
	}

	public Integer getProjectId() {
	return projectId;
	}

	public void setProjectId(Integer projectId) {
	this.projectId = projectId;
	}

	public String getNoteData() {
	return noteData;
	}

	public void setNoteData(String noteData) {
	this.noteData = noteData;
	}

	public ArrayList<Attachment> getAttachments() {
	return attachments;
	}

	public void setAttachments(ArrayList<Attachment> attachments) {
	this.attachments = attachments;
	}

	public String getHref() {
	return href;
	}

	public void setHref(String href) {
	this.href = href;
	}

	public String getCreatedAt() {
	return createdAt;
	}

	public void setCreatedAt(String createdAt) {
	this.createdAt = createdAt;
	}

	public String getModifiedAt() {
	return modifiedAt;
	}

	public void setModifiedAt(String modifiedAt) {
	this.modifiedAt = modifiedAt;
	}


}
