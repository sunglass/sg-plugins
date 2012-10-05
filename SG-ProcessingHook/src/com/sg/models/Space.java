package com.sg.models;

import java.util.*;

public class Space extends Object{
	
	protected int parentId;
	protected int projectId;
	protected String href;
	protected ArrayList<Space> spacesList;
	protected ArrayList<Note> notesList;
	protected ArrayList<View> viewsList;
	protected ArrayList<User> collaboratorList;
	protected String projectLink;
	protected String subSpacesLink;
	protected String modelsLink;
	protected String viewsLink;
	/**
	 * Constructor
	 * 
	 */
	public Space(int id, int pId, int prId, String _href, ArrayList<Space>spList,ArrayList<Note> nList, ArrayList<View> vList, String pL, String ssL, String mL, String vL, String dC, String dM){
		this.UID = id;
		this.parentId = pId;
		this.projectId = prId;
		this.href = _href;
		this.spacesList = spList;
		this.notesList = nList;
		this.viewsList = vList;
		this.projectLink = pL;
		this.subSpacesLink = ssL;
		this.modelsLink = mL;
		this.viewsLink = vL;
		this.createdAt = dC;
		this.modifiedAt = dM;
	}
	/**
	 * 
	 * 
	 */
	public int getParentId(){
		return this.parentId;
	}
	/**
	 * 
	 * 
	 */
	public int getProjectId(){
		return this.projectId;
	}

 	public String getProjectLink(){
		return this.projectLink;
	}
	/**
	 * 
	 * 
	 */
	public String getSubSpacesLink(){
		return this.subSpacesLink;
	}
	/**
	 * 
	 * 
	 */
	public String getModelsLink(){
		return this.modelsLink;
	}
	/**
	 * 
	 * 
	 */
	public String getViewsLink(){
		return this.viewsLink;
	}

	/**
	 * Get the parent stage to this stage
	 * 
	 */
//	 public Space getParentSpace(){
//		Space parent = sgConnectMgr.getProject(this.projectId).getSpace(this.parentId);
//		return parent;
//	}
	/**
	 * Done
	 * 
	 */
	public boolean isRootSpace(){
		if(this.UID == this.parentId){
			return true;
		}
		return false;
	}
}
