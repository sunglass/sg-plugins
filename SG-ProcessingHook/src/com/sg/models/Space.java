package com.sg.models;

import java.util.ArrayList;

public class Space {

	private String id;
	private String name;
	private String href;
	private String parentSpaceId;
	private Integer projectId;
	private ArrayList<Integer> transformMatrix = new ArrayList<Integer>();
	private String groupsList;
	private String role;
	private MetaData metaData;
	private String createdAt;
	private String modifiedAt;
	private SpaceLinks links;
	
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

	public String getHref() {
	return href;
	}

	public void setHref(String href) {
	this.href = href;
	}

	public String getParentSpaceId() {
	return parentSpaceId;
	}

	public void setParentSpaceId(String parentSpaceId) {
	this.parentSpaceId = parentSpaceId;
	}

	public Integer getProjectId() {
	return projectId;
	}

	public void setProjectId(Integer projectId) {
	this.projectId = projectId;
	}

	public ArrayList<Integer> getTransformMatrix() {
	return transformMatrix;
	}

	public void setTransformMatrix(ArrayList<Integer> transformMatrix) {
	this.transformMatrix = transformMatrix;
	}

	public String getGroupsList() {
	return groupsList;
	}

	public void setGroupsList(String groupsList) {
	this.groupsList = groupsList;
	}

	public String getRole() {
	return role;
	}

	public void setRole(String role) {
	this.role = role;
	}

	public MetaData getMetaData() {
	return metaData;
	}

	public void setMetaData(MetaData metaData) {
	this.metaData = metaData;
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

	public SpaceLinks getLinks() {
	return links;
	}

	public void setLinks(SpaceLinks links) {
	this.links = links;
	}

	
	
}
