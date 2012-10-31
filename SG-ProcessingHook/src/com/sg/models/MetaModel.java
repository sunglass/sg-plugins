package com.sg.models;

import java.util.ArrayList;

public class MetaModel {

private String id;
private String spaceId;
private Integer projectId;
private String modelId;
private ArrayList<Integer> transformMatrix = new ArrayList<Integer>();
private MetaData metaData;
private String createdAt;
private String modifiedAt;
private ModelDetails modelDetails;
private String href;

public String getId() {
return id;
}

public void setId(String id) {
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

public String getModelId() {
return modelId;
}

public void setModelId(String modelId) {
this.modelId = modelId;
}

public ArrayList<Integer> getTransformMatrix() {
return transformMatrix;
}

public void setTransformMatrix(ArrayList<Integer> transformMatrix) {
this.transformMatrix = transformMatrix;
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

public ModelDetails getModelDetails() {
return modelDetails;
}

public void setModelDetails(ModelDetails modelDetails) {
this.modelDetails = modelDetails;
}

public String getHref() {
return href;
}

public void setHref(String href) {
this.href = href;
}


}
