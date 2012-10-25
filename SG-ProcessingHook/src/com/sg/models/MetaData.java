package com.sg.models;

import java.util.HashMap;
import java.util.Map;

public class MetaData {

	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Map<String, Object> getAdditionalProperties() {
	return this.additionalProperties;
	}

	public void setAdditionalProperties(String name, Object value) {
	this.additionalProperties.put(name, value);
	}

}