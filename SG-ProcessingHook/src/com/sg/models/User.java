package com.sg.models;

public class User extends Object{
	
	private String name;
	private String email;
	private String href;
	private String projectsList;
		
	public User(int id, String n, String e, String dC, String dM){
		this.UID = id;
		this.name = n;
		this.email = e;
		this.createdAt = dC;
		this.modifiedAt = dM;
	}
	
	
	public User(int id, String n, String e, String h, String pL, String dC, String dM){
		this.UID = id;
		this.name = n;
		this.email = e;
		this.href = h;
		this.projectsList = pL;
		this.createdAt = dC;
		this.modifiedAt = dM;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getUserId(){
		return this.UID;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public String getHref(){
		return this.href;
	}
	
	public String projectsLink(){
		return this.projectsList;
	}
	
//	public String updateName(String name){
//		JSONObject jsonName = new JSONObject();
//		try {
//			jsonName.put("name",name);
//			JSONObject newName = sgConnectMgr.httpPutRequest(this.href, jsonName);
//			this.name = newName.getString("name");
//			return this.name;
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
	
}
