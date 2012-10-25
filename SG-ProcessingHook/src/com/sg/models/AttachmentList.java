package com.sg.models;

import java.util.ArrayList;

public class AttachmentList {
	private ArrayList<Attachment> attachments =	 new ArrayList<Attachment>();
	
	public ArrayList<Attachment> getAttachments() {
		return attachments;
	}
	
	public void setAttachments(ArrayList<Attachment> attachments) {
		this.attachments = attachments;
	}
}
