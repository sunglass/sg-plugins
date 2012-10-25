package com.sg;


import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;

import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.client.AuthCache;

import com.sg.json.JSONException;
import com.sg.json.JSONObject;
import com.sg.models.*;
import com.sg.utils.FileIO;
import org.apache.commons.codec.binary.Base64;

import com.google.gson.*;



/**
 * 
 * Connection Manager class encapsulates the methods using HTTP requests
 * for communicating with the Sunglass server
 *
 */
@SuppressWarnings("deprecation")
public class ConnectionManager {
	
	private boolean VERBOSE;	
	private String url;
	private String sid;
	private String token;
	
	private Gson gson = new Gson();
	
	/**
	 * Constructor
	 * 
	 * @param _url
	 * @param _sid
	 * @param _token
	 * @param _VERBOSE
	 * 
	 */
	public ConnectionManager(String _url, String _sid, String _token, Boolean _VERBOSE){
	
		this.url = _url;
		this.sid = _sid;
		this.token = _token;
		this.VERBOSE = _VERBOSE;
	}
	
	//------------------ API SPECIFIC FUNCTIONS
	
	// GET
	
	public boolean authenticate(){
		return true;
	}
	
	
	public void getUserDetails(){
		
	}
	/**
	 * get all of the projects for user
	 * 
	 * @throws Exception
	 * 
	 * 
	 */
	public ProjectList getProjects(){
		JSONObject projectJSON = this.httpGetRequest("/projects");
		ProjectList projects = gson.fromJson(projectJSON.toString(),ProjectList.class);
		return projects;
	}
	/**
	 * get a project by its unique id
	 * 
	 * @param projectId
	 *            the unique id of the project
	 *            
	 * @return Project
	 * 			  Sunglass project
	 * @throws Exception
	 * 
	 */
	public Project getProject(String id){
		JSONObject projectJSON = this.httpGetRequest("/projects/"+id);
		Project project = gson.fromJson(projectJSON.toString(),Project.class);
		return project;
	}
	
	public SpaceList getSpaces(String project_id){
		JSONObject spaceJSON = this.httpGetRequest("/projects/"+project_id+"/spaces");
		SpaceList spaces = gson.fromJson(spaceJSON.toString(), SpaceList.class);
		return spaces;
	}
	/**
	 * get a space by it unique id
	 * 
	 * @param projectId
	 * 			  the unique id of the project
	 * @param spaceId
	 *            the unique id of the space
	 * @return Space
	 * 			  Sunglass Space object
	 * @throws Exception
	 * 
	 */
	public Space getSpace(String project_id, String id){
		JSONObject spaceJSON = this.httpGetRequest("/projects/"+project_id+"/spaces/"+id);
		Space space = gson.fromJson(spaceJSON.toString(), Space.class);
		return space;
	}
	
	public SubSpaceList getSubSpaces(String project_id, String id){
		JSONObject spaceJSON = this.httpGetRequest("/projects/"+project_id+"/spaces/"+id+"/subspaces");
		SubSpaceList spaces = gson.fromJson(spaceJSON.toString(), SubSpaceList.class);
		return spaces;
	}
	/**
	 * get all models from a sunglass space
	 * 
	 * @param projectId
	 * 			  the unique id of the project
	 * @param spaceId
	 *            the unique id of the space
	 * 
	 * @return ArrayList<Model>
	 * 			  Sunglass Model objects
	 * 
	 * @throws Exception
	 * 
	 */
	public ModelList getModels(String project_id){
		JSONObject modelJSON = this.httpGetRequest("/projects/"+project_id+"/models");
		ModelList models = gson.fromJson(modelJSON.toString(), ModelList.class);
		return models;
	}
	/**
	 * get a model by it unique id
	 * 
	 * @param projectId
	 * 			  the unique id of the project
	 * @param spaceId
	 *            the unique id of the space
	 * @param modelId
	 * 			  the unique id of the model
	 * 
	 * @return Model
	 * 			  Sunglass Model object
	 * 
	 * @throws Exception
	 * 
	 */
	public Model getModel(String project_id, String id){
		JSONObject modelJSON = this.httpGetRequest("/projects/"+project_id+"/models/"+id);
		Model model = gson.fromJson(modelJSON.toString(), Model.class);
		return model;
	}
	
	public MetaModelList getMetaModels(String project_id, String space_id){
		JSONObject metaModelJSON = this.httpGetRequest("/projects/"+project_id+"/spaces/"+space_id+"/metamodels");
		MetaModelList metaModels = gson.fromJson(metaModelJSON.toString(), MetaModelList.class);
		return metaModels;
	}
	
	public MetaModel getMetaModel(String project_id, String space_id, String id){
		JSONObject metaModelJSON = this.httpGetRequest("/projects/"+project_id+"/spaces/"+space_id+"/metamodels/"+id);
		MetaModel metaModel = gson.fromJson(metaModelJSON.toString(), MetaModel.class);
		return metaModel;
	}
	
	public VersionList getModelVersions(String project_id, String model_id){
		JSONObject versionJSON = this.httpGetRequest("/projects/"+project_id+"/models/"+model_id+"/versions");
		VersionList versions = gson.fromJson(versionJSON.toString(), VersionList.class);
		return versions;
	}
	
	public Version getModelVersion(String project_id, String model_id, String id){
		JSONObject versionJSON = this.httpGetRequest("/projects/"+project_id+"/models/"+model_id+"/versions/"+id);
		Version version = gson.fromJson(versionJSON.toString(), Version.class);
		return version;
	}
	
	public NoteList getNotes(String project_id, String space_id){
		JSONObject noteJSON = this.httpGetRequest("/projects/"+project_id+"/spaces/"+space_id+"/notes");
		NoteList notes = gson.fromJson(noteJSON.toString(),NoteList.class);
		return notes;
	}
	
	public Note getNote(String project_id, String space_id, String id){
		JSONObject noteJSON = this.httpGetRequest("/projects/"+project_id+"/spaces/"+space_id+"/notes/"+id);
		Note note = gson.fromJson(noteJSON.toString(),Note.class);
		return note;
	}
	
	public AttachmentList getNoteAttachments(String project_id, String space_id, String note_id){
		JSONObject attachmentJSON = this.httpGetRequest("/projects/"+project_id+"/spaces/"+space_id+"/notes/"+note_id+"/attachments");
		AttachmentList attachments = gson.fromJson(attachmentJSON.toString(),AttachmentList.class);
		return attachments;
	}
	
	public Attachment getNoteAttachment(String project_id, String space_id, String note_id, String id){
		JSONObject attachmentJSON = this.httpGetRequest("/projects/"+project_id+"/spaces/"+space_id+"/notes/"+note_id+"/attachments/"+id);
		Attachment attachment = gson.fromJson(attachmentJSON.toString(),Attachment.class);
		return attachment;
	}
	
	public UserList getCollaborators(String project_id){
		JSONObject userJSON = this.httpGetRequest("/projects/"+project_id+"/collaborators");
		UserList users = gson.fromJson(userJSON.toString(), UserList.class);
		return users;
	}
	
	
	//SET
	
	public Project setProjectName(String id, String name){
		JSONObject data = new JSONObject();
		try{
			data.put("name", name);
		}catch (JSONException e) {
			e.printStackTrace();
		}
		
		JSONObject projectJSON = this.httpPostRequest("/projects/"+id, data);
		Project project = gson.fromJson(projectJSON.toString(), Project.class);
		return project;
	}
	
	public Project setProjectDescription(String id, String description){
		JSONObject data = new JSONObject();
		try{
			data.put("description", description);
		}catch (JSONException e) {
			e.printStackTrace();
		}
		
		JSONObject projectJSON = this.httpPostRequest("/projects/"+id, data);
		Project project = gson.fromJson(projectJSON.toString(), Project.class);
		return project;
	}
	
	public Space setSpaceName(String project_id, String id, String name ){
		JSONObject data = new JSONObject();
		try{
			data.put("name", name);
		}catch (JSONException e) {
			e.printStackTrace();
		}
		
		JSONObject spaceJSON = this.httpPostRequest("/projects/"+project_id+"/spaces/"+id, data);
		Space space = gson.fromJson(spaceJSON.toString(), Space.class);
		return space;
	}
	
	public Space setSpaceTransformationMatrix(String project_id, String id, ArrayList<Integer> transformMatrix){
		JSONObject data = new JSONObject();
		try{
			data.put("transformMatrix", transformMatrix);
		}catch (JSONException e) {
			e.printStackTrace();
		}
		
		JSONObject spaceJSON = this.httpPostRequest("/projects/"+project_id+"/spaces/"+id, data);
		Space space = gson.fromJson(spaceJSON.toString(), Space.class);
		return space;
	}
	
	public MetaModel setMetaModelName(String project_id, String space_id, String id, String name){
		JSONObject data = new JSONObject();
		try{
			data.put("name", name);
		}catch (JSONException e) {
			e.printStackTrace();
		}
		
		JSONObject metaModelJSON = this.httpPostRequest("/projects/"+project_id+"/spaces/"+space_id+"/metamodels/"+id, data);
		MetaModel metaModel = gson.fromJson(metaModelJSON.toString(), MetaModel.class);
		return metaModel;
	}
	
	public MetaModel setMetaModelTransformationMatrix(String project_id, String space_id, String id, ArrayList<Integer> transformMatrix){
		JSONObject data = new JSONObject();
		try{
			data.put("transformMatrix", transformMatrix);
		}catch (JSONException e) {
			e.printStackTrace();
		}
		
		JSONObject metaModelJSON = this.httpPostRequest("/projects/"+project_id+"/spaces/"+space_id+"/metamodels/"+id, data);
		MetaModel metaModel = gson.fromJson(metaModelJSON.toString(), MetaModel.class);
		return metaModel;
		
	}
	
	public Note setNoteData(String project_id, String space_id, String id, JSONObject noteData){
		
		JSONObject noteJSON = this.httpPostRequest("/projects/"+project_id+"/spaces/"+space_id+"/notes/"+id, noteData);
		Note note = gson.fromJson(noteJSON.toString(), Note.class);
		return note;
	}
	
	public Attachment setNoteAttachment(String project_id, String space_id, String note_id, String id, JSONObject attachmentData){
		
		JSONObject attachmentJSON = this.httpPostRequest("/projects/"+project_id+"/spaces/"+space_id+"/notes/"+note_id+"/attachments/"+id, attachmentData);
		Attachment attachment = gson.fromJson(attachmentJSON.toString(), Attachment.class);
		return attachment;
	}
	
	//CREATE
	
	public Project createProject(String name){
		JSONObject data = new JSONObject();
		try{
			data.put("name", name);
		}catch (JSONException e) {
			e.printStackTrace();
		}
		
		JSONObject projectJSON = this.httpPostRequest("/projects", data);
		Project project = gson.fromJson(projectJSON.toString(), Project.class);
		return project;
	}
	
	public Project createProject(String name, String description){
		JSONObject data = new JSONObject();
		try{
			data.put("name", name);
			data.put("description", description);
		}catch (JSONException e) {
			e.printStackTrace();
		}
		
		JSONObject projectJSON = this.httpPostRequest("/projects", data);
		Project project = gson.fromJson(projectJSON.toString(), Project.class);
		return project;
	}
	
	public Space createSpace(String project_id, String name){
		JSONObject data = new JSONObject();
		try{
			data.put("name", name);
		}catch (JSONException e) {
			e.printStackTrace();
		}
		
		JSONObject spaceJSON = this.httpPostRequest("/projects/"+project_id+"/spaces", data);
		Space space = gson.fromJson(spaceJSON.toString(), Space.class);
		return space;
	}
	
	public Space createSpace(String project_id, String space_id, String name){
		JSONObject data = new JSONObject();
		try{
			data.put("name", name);
		}catch (JSONException e) {
			e.printStackTrace();
		}
		
		JSONObject spaceJSON = this.httpPostRequest("/projects/"+project_id+"/spaces/"+space_id+"/subspaces", data);
		Space space = gson.fromJson(spaceJSON.toString(), Space.class);
		return space;
		
	}
	
	
	public MetaModel createMetaModel(String project_id, String space_id, String model_id){
		JSONObject data = new JSONObject();
		try{
			data.put("modelId", model_id);
		}catch (JSONException e) {
			e.printStackTrace();
		}
		
		JSONObject metaModelJSON = this.httpPostRequest("/projects/"+project_id+"/spaces/"+space_id+"/metamodels", data);
		MetaModel metaModel = gson.fromJson(metaModelJSON.toString(), MetaModel.class);
		return metaModel;
	}
	
	public Note createNote(String project_id, String space_id, JSONObject noteData){
		JSONObject noteJSON = this.httpPostRequest("/projects/"+project_id+"/spaces/"+space_id+"/notes", noteData);
		Note note = gson.fromJson(noteJSON.toString(), Note.class);
		return note;
	}
	
	//DELETE
	
	public void deleteProject(String id){
		this.httpDeleteRequest("/projects/"+id);
	}
	
	public void deleteSpace(String project_id, String id){
		this.httpDeleteRequest("/projects/"+project_id+"/spaces/"+id);
	}
	
	public void deleteModel(String project_id, String id){
		this.httpDeleteRequest("/projects/"+project_id+"/models/"+id);
	}
	
	public void deleteMetaModel(String project_id, String space_id, String id){
		this.httpDeleteRequest("/projects/"+project_id+"/spaces/"+space_id+"/metamodels/"+id);
	}
	
	public void deleteNote(String project_id, String space_id, String id){
		this.httpDeleteRequest("/projects/"+project_id+"/spaces/"+space_id+"/notes/"+id);
	}
	
	//UPLOAD AND DOWNLOAD
	
	//TODO
	
	//------------------ GENERIC TRANSFER FUNCTIONS
	//need to use https
	//need to use credentials
	
	private JSONObject properHttpGetRequest(String endpoint){
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		
		try {
        	URL url = new URL(this.url + endpoint);
    		
        	HttpHost httpHost = new HttpHost(url.getHost(), url.getPort(), url.getProtocol());
    		httpClient.getCredentialsProvider().setCredentials(
    					new AuthScope(url.getHost(), url.getPort()),
    					new UsernamePasswordCredentials(this.sid, this.token));
    		
    		AuthCache authCache = new BasicAuthCache();
    		BasicScheme basicAuth = new BasicScheme();
            authCache.put(httpHost, basicAuth);
            BasicHttpContext localContext = new BasicHttpContext();
            localContext.setAttribute(ClientContext.AUTH_CACHE,authCache);
            
			HttpGet request = new HttpGet(this.url + endpoint);

			request.setHeader("Content-Type", "application/json");
			HttpResponse response = httpClient.execute(httpHost, request, localContext);

			if (response.getStatusLine().getStatusCode() != 200)
			{
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
		
			JSONObject responseJSON = new JSONObject(FileIO.convertStreamToString(response.getEntity().getContent()));
			if (VERBOSE) System.out.println(responseJSON.toString());

			return responseJSON;
			
		} catch (Exception e){
			if(VERBOSE) System.out.println("[ConnMgr/httpGetRequest] --Connection Failed");
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		
		return null;
		
	}
	
	/**
	 * makes an http get request to the api
	 * 
	 * @param endpoint
	 *            the url endpoint for the httprequest
	 */
	private JSONObject httpGetRequest(String endpoint){
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		
		try {


			HttpGet request = new HttpGet(this.url + endpoint);
			request.setHeader("Content-Type", "application/json");
			String auth = this.sid+":"+this.token;
			String encoding = Base64.encodeBase64String(auth.getBytes());
			request.setHeader("Authorization", "Basic " + encoding);
			HttpResponse response = httpClient.execute(request);
			
			if (response.getStatusLine().getStatusCode() != 200)
			{
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
		
			JSONObject responseJSON = new JSONObject(FileIO.convertStreamToString(response.getEntity().getContent()));
			if (VERBOSE) System.out.println(responseJSON.toString());

			return responseJSON;
			
		} catch (Exception e){
			if(VERBOSE) System.out.println("[ConnMgr/httpGetRequest] --Connection Failed");
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		
		return null;
	}
	
	/**
	 * makes an http post request to the api
	 * 
	 * @param endpoint
	 *            the url endpoint for the httprequest
	 */
	private JSONObject httpPostRequest(String endpoint, JSONObject jsonEntity) {
	
		DefaultHttpClient httpClient = new DefaultHttpClient();

		
		try {
			
			
			HttpPost request = new HttpPost(this.url + endpoint);
			StringEntity entity = new StringEntity(jsonEntity.toString());
			String auth = this.sid+":"+this.token;
			String encoding = Base64.encodeBase64String(auth.getBytes());
			request.setHeader("Authorization", "Basic " + encoding);
			request.setHeader("Content-Type", "application/json");
			request.setEntity(entity);
			
			if (VERBOSE) System.out.println("[ConnMgr/httpPostRequest] --Executing request " + request.getRequestLine());
			
			HttpResponse response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() != 200)
			{
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			
			if (VERBOSE) System.out.println("[ConnMgr/httpPostRequest] --Response details " + response.getEntity().getContent());
			
			JSONObject responseJSON = new JSONObject(FileIO.convertStreamToString(response.getEntity().getContent()));
			if (VERBOSE) System.out.println(responseJSON.toString());
		
			return responseJSON;

		} catch (Exception e) {

			if(VERBOSE) System.out.println("[ConnMgr/httpPostRequest] --Connection Failed");
			e.printStackTrace();

		} finally {

			httpClient.getConnectionManager().shutdown();
		}

		return null;

	}
	
	/**
	 * makes an http delete request to the api
	 * 
	 * @param endpoint
	 *            the url endpoint for the httprequest
	 */
	private JSONObject httpDeleteRequest(String endpoint) {
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		
		try {

			HttpDelete request = new HttpDelete(this.url + endpoint);
			request.setHeader("Content-Type", "application/json");
			String auth = this.sid+":"+this.token;
			String encoding = Base64.encodeBase64String(auth.getBytes());
			request.setHeader("Authorization", "Basic " + encoding);

			HttpResponse response = httpClient.execute(request);
			
			if (response.getStatusLine().getStatusCode() != 200)
			{
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
		
			JSONObject responseJSON = new JSONObject(FileIO.convertStreamToString(response.getEntity().getContent()));
			if (VERBOSE) System.out.println(responseJSON.toString());

			return responseJSON;
			
		} catch (Exception e){
			if(VERBOSE) System.out.println("[ConnMgr/httpGetRequest] --Connection Failed");
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		
		return null;
	}
	
	/**
	 * Called when the parent applet shuts down
	 * 
	 */
	private void dispose() {
		// anything in here will be called automatically when
		// the parent applet shuts down. for instance, this might
		// shut down a thread used by this library.
	}
	
	//------------------ FILE TRANSFER FUNCTIONS
	
}
