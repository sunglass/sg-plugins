package com.sg.models;

public class ModelDetails {

	private String id;
	private String name;
	private String modelType;
	private Integer projectId;
	private String description;
	private MetaData metaData;
	private Integer creatorId;
	private String createdAt;
	private String modifiedAt;
	private String href;
	private String version;
	
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

	public String getModelType() {
	return modelType;
	}

	public void setModelType(String modelType) {
	this.modelType = modelType;
	}

	public Integer getProjectId() {
	return projectId;
	}

	public void setProjectId(Integer projectId) {
	this.projectId = projectId;
	}

	public String getDescription() {
	return description;
	}

	public void setDescription(String description) {
	this.description = description;
	}

	public MetaData getMetaData() {
	return metaData;
	}

	public void setMetaData(MetaData metaData) {
	this.metaData = metaData;
	}

	public Integer getCreatorId() {
	return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
	this.creatorId = creatorId;
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

	public String getHref() {
	return href;
	}

	public void setHref(String href) {
	this.href = href;
	}

	public String getVersion() {
	return version;
	}

	public void setVersion(String version) {
	this.version = version;
	}
		
}
