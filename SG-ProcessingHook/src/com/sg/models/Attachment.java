package com.sg.models;

public class Attachment {
	private String noteId;
	private String attachmentId;
	private String aType;
	private AttachmentData attachmentData;
	private String path;
	private String createdAt;
	private String modifiedAt;
	private String content;
	
	public String getNoteId() {
	return noteId;
	}

	public void setNoteId(String noteId) {
	this.noteId = noteId;
	}

	public String getAttachmentId() {
	return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
	this.attachmentId = attachmentId;
	}

	public String getAType() {
	return aType;
	}

	public void setAType(String aType) {
	this.aType = aType;
	}

	public AttachmentData getAttachmentData() {
	return attachmentData;
	}

	public void setAttachmentData(AttachmentData attachmentData) {
	this.attachmentData = attachmentData;
	}

	public String getPath() {
	return path;
	}

	public void setPath(String path) {
	this.path = path;
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

	public String getContent() {
	return content;
	}

	public void setContent(String content) {
	this.content = content;
	}
}
