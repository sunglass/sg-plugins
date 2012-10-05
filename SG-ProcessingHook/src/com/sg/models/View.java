package com.sg.models;

import java.util.*;

public class View extends Object{
	
	protected boolean isPersonal;
	protected HashMap<String,String> viewData;
	protected int spaceId;
	protected int userId;
	
	public View(int id, boolean iP, HashMap<String,String> vD, int sId, int uId, String dC, String dM){
		this.UID = id;
		this.isPersonal = iP;
		this.viewData = vD;
		this.spaceId = sId;
		this.userId = uId;
		this.createdAt = dC;
		this.modifiedAt = dM;
	}
	
}
