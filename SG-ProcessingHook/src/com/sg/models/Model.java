package com.sg.models;

public class Model extends Object{
	
	protected String name;
	protected String description;
	protected String remoteFilePath;
	protected String remoteFilePathMtl;
	protected String localFilePath;
	protected String localFilePathMtl;
	protected ModelFormat dataFormat;
	protected String dataFormatString;
	private int projectId;
	private int spaceId;
	protected float[] transformationMatrix;
	protected String metadata;
	

	protected enum ModelFormat {
		xOBJ, x3DS, xSTL, xDAE, xMTL
	}
	
	public Model() {
		
	}
	
	public Model(int id,int pId, int sId, String n, String d,float[] tM, String dP, String dC, String dM) {
		this.UID = id;
		this.setProjectId(pId);
		this.setSpaceId(sId);
		this.description = d;
		this.transformationMatrix = tM;
		this.remoteFilePath = stripExtension(dP, 5);
		this.remoteFilePathMtl = extractMtlPath(remoteFilePath);
		this.localFilePath = generateLocalFilePath(remoteFilePath);
		this.localFilePathMtl = extractMtlPath(localFilePath);
		this.name = stripExtension(n, 4);
		this.dataFormatString = getFormat();
		this.createdAt = dC;
		this.modifiedAt = dM;
		
	}
	
	protected String generateLocalFilePath(String remoteFileP) {
		String localFileP = "sunglassdata/project";
		String[] splitData = remoteFileP.split("/");
		String projIndex =  splitData[4];
		String modelName = splitData[7];
		localFileP += (projIndex + "/" + modelName);
		return localFileP;
	}
	
	protected String stripExtension(String path, int n) {
		path = path.substring(0,path.length()-n);
		//System.out.println("*[Model/stripExension] --" +path);
		return path;
	}
	
	protected String extractMtlPath(String path) {
		path = path.substring(0,path.length()-3) + "mtl";
		//System.out.println("*[Model/extractMtlPath] --" +path);
		return path;
	}
	
	public int getId(){
		return this.UID;
	}

	public String getName(){
		return this.name;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public float[] getTransformationMatrix(){
		return this.transformationMatrix;
	}
	
	public String getRemoteFilePath() {
		return this.remoteFilePath;
	}
	
	public String getRemoteFilePathMtl() {
		return this.remoteFilePathMtl;
	}
	public String getLocalFilePath() {
		return localFilePath;
	}

	public void setLocalFilePath(String localFilePath) {
		this.localFilePath = localFilePath;
	}
	
	public String getLocalFilePathMtl() {
		return localFilePathMtl;
	}

	public void setLocalFilePathMtl(String localFilePathMtl) {
		this.localFilePathMtl = localFilePathMtl;
	}
	
	public String getFormat(){
		String ext = this.remoteFilePath.substring(this.remoteFilePath.lastIndexOf(".") + 1, this.remoteFilePath.length()).toLowerCase(); 
		this.dataFormat = ModelFormat.valueOf("x" + ext.toUpperCase());

		switch(dataFormat) {
		case xOBJ: return "obj"; 
		case x3DS: return "3ds";
		case xSTL: return "stl";
		case xDAE: return "dae";
		case xMTL: return "mtl";
		default : 
			System.out.println("[Model.error] --Unknown model extension found : " + ext); 
			return ext;
		}
	}

	protected void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
	public int getProjectId() {
		return projectId;
	}

	public int getSpaceId() {
		return spaceId;
	}

	protected void setSpaceId(int spaceId) {
		this.spaceId = spaceId;
	}
}
