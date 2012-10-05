package com.sg.models;

public class Project extends Object{
	
	protected String name;
	protected String description;
	protected String spaces;
	protected String rootSpace;
	
	/**
	 * Project Constructor
	 * 
	 */
	public Project(int id, String n, String d, String rootSp, String cA, String mA){
		UID = id;
		name = n;
		description = d;
		//spaces = sp;
		rootSpace = rootSp;
		createdAt = cA;
		modifiedAt = mA;
	}
	
	/**
	 * get the name of a project
	 * 
	 */
	public String getName(){
		
		return this.name;
	}
	/**
	 * get the description of a project
	 * 
	 */
	public String getDescription(){
		
		return this.description;
	
	}
	
	public String getRootSpaceLink(){
		return this.rootSpace;
	}
	
	public int getRootSpaceId() {
		int spaceId = Integer.parseInt(rootSpace.substring(rootSpace.lastIndexOf("/")+1, rootSpace.length()));
		return spaceId;
	}
	
	
	/**
	 * get all the Spaces in this project
	 * 
	 */
	/*
	public ArrayList<Space> getSpaces(){
		
		ArrayList<Space> spaceList = new ArrayList<Space>();
		
		JSONObject jsonSpaces = sgConnectMgr.httpGetRequest("/api/v1/projects/"+UID+"/spaces");
		
		try{
			JSONArray spaceArray = new JSONArray(jsonSpaces.get("spaces").toString());
			
			for(int i=0;i<spaceArray.length();i++){
				JSONObject row = spaceArray.getJSONObject(i);
				JSONObject links = new JSONObject(row.get("links").toString());
				
				ArrayList<Space> subSpaceList = new ArrayList<Space>();
				ArrayList<Note> noteList = new ArrayList<Note>();
				ArrayList<View> viewList = new ArrayList<View>();
				
				Space s = new Space(
					row.getInt("id"), 
					row.getInt("parentId"),
					row.getInt("projectId"),
					row.getString("href"),
					subSpaceList,
					noteList,
					viewList,
					links.getString("project"),
					links.getString("subSpaces"),
					links.getString("models"),
					links.getString("views"),
					row.getString("createdAt"),
					row.getString("modifiedAt")
				 );
				
				spaceList.add(s);
			}
		
		}catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return spaceList;
	}	
	
	/**
	 * get a Space by its unique identifier
	 * @param spaceId
	 * 		The unique id of the space to get
	 */
	/*
	public Space getSpace(int spaceId){
		
		JSONObject jsonSpace = sgConnectMgr.httpGetRequest("/api/v1/projects/"+this.UID+"/spaces/"+spaceId);
		
		ArrayList<Space> subSpaceList = new ArrayList<Space>();
		ArrayList<Note> noteList = new ArrayList<Note>();
		ArrayList<View> viewList = new ArrayList<View>();
		try {
			JSONObject	links = new JSONObject(jsonSpace.get("links").toString());
			
			System.out.println(links.toString());
			Space s = new Space(
					jsonSpace.getInt("id"), 
					jsonSpace.getInt("parentId"),
					jsonSpace.getInt("projectId"),
					jsonSpace.getString("href"),
					subSpaceList,
					noteList,
					viewList,
					links.getString("project"),
					links.getString("subSpaces"),
					links.getString("models"),
					links.getString("views"),
					jsonSpace.getString("createdAt"),
					jsonSpace.getString("modifiedAt")
				 );
			
			return s;
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}

	/**
	 * Set the name of a project
	 * @param n
	 * 		the new name to set
	 */
	/*
	public void setName(String n){
		JSONObject jsonName = new JSONObject();
		try {
			jsonName.put("name",n);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sgConnectMgr.httpPutRequest("/api/v1/projects/"+this.UID,jsonName);
		this.name = n;
	}
	
	/**
	 * Set the description of a project
	 * @param d
	 * 		the description to set
	 */	
	/*
	public void setDescription(String d){
		JSONObject jsonDescription = new JSONObject();
		try {
			jsonDescription.put("description",d);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sgConnectMgr.httpPutRequest("/api/v1/projects/"+this.UID,jsonDescription);
		this.description = d;
	}
	
	*/
	
}
