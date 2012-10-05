package com.sg.models;

public class Object {

	int UID;
	protected String createdAt;
	protected String modifiedAt;
	
	/**
	 * The object constructor
	 * 		
	 */
	public Object(){
		
	}
	
	/**
	 * get the id of the space
	 * 
	 */
	public int getId(){
		
		return this.UID;
	}
	/**
	 * get the name of a project
	 * 
	 */
	public String getDateCreatedAt(){
		
		return this.createdAt;
	
	}
	/**
	 * get the name of a project
	 * 
	 */
	public String getDateModifiedAt(){
		
		return this.modifiedAt;
	
	}
	
}
