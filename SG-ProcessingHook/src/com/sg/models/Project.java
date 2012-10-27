package com.sg.models;

public class Project {

private String id;
private String name;
private String description;
private String visibility;
private MetaData metaData;
private String href;
private ProjectLinks links;
private String createdAt;
private String modifiedAt;
private AssetCounts assetCounts;

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

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public String getVisibility() {
return visibility;
}

public void setVisibility(String visibility) {
this.visibility = visibility;
}

public MetaData getMetaData() {
return metaData;
}

public void setMetaData(MetaData metaData) {
this.metaData = metaData;
}

public String getHref() {
return href;
}

public void setHref(String href) {
this.href = href;
}

public ProjectLinks getLinks() {
return links;
}

public void setLinks(ProjectLinks links) {
this.links = links;
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

public AssetCounts getAssetCounts() {
return assetCounts;
}

public void setAssetCounts(AssetCounts assetCounts) {
this.assetCounts = assetCounts;
}

}