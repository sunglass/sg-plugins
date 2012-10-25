package com.sg.models;

import java.util.HashMap;
import java.util.Map;

public class NewModel {
	private Model model;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Model getModel() {
	return model;
	}

	public void setModel(Model model) {
	this.model = model;
	}

}
