package com.sg.models;

import java.util.ArrayList;

public class SubSpaceList {

	private ArrayList<Space> descendants =	 new ArrayList<Space>();
	
	public ArrayList<Space> getSpaces() {
		return descendants;
	}
	
	public void setSpaces(ArrayList<Space> descendants) {
		this.descendants = descendants;
	}

}
