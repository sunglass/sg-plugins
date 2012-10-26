package com.sg;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.codec.binary.Base64;

import org.apache.http.HttpResponse;
import org.apache.http.HttpHost;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;

import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;

import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.protocol.BasicHttpContext;

import com.sg.json.*;
import com.sg.models.*;
import com.sg.utils.*;

import com.google.gson.*;

/**
 * 
 * Connection Manager class encapsulates the methods using HTTP requests
 * for communicating with the Sunglass server
 *
 */

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
	 * 		The base url to connect to
	 * @param _sid
	 * 		SID for authentication
	 * @param _token
	 * 		token for authentication
	 * @param _VERBOSE
	 * 		Print responses from server to the console
	 */
	public ConnectionManager(String _url, String _sid, String _token, Boolean _VERBOSE){
	
		this.url = _url;
		this.sid = _sid;
		this.token = _token;
		this.VERBOSE = _VERBOSE;
	}
	
	//------------------ API SPECIFIC FUNCTIONS
	
	// GET
	
//	public boolean authenticate(){
//		return true;
//	}
//	
//	
//	public void getUserDetails(){
//		
//	}
	/**
	 * get all of the projects for user
	 */
	public ProjectList getProjects(){
		JSONObject projectJSON = this.httpGetRequest("/projects");
		ProjectList projects = gson.fromJson(projectJSON.toString(),ProjectList.class);
		return projects;
	}
	/**
	 * get a project by its unique id
	 * 
	 * @param id
	 *            the unique id of the project
	 *            
	 * @return Project
	 * 			  Sunglass project
	 * 
	 */
	public Project getProject(String id){
		JSONObject projectJSON = this.httpGetRequest("/projects/"+id);
		Project project = gson.fromJson(projectJSON.toString(),Project.class);
		return project;
	}
	/**
	 * get a list of spaces in a project
	 * 
	 * @param project_id
	 * 			  the unique id of the project
	 * @return SpaceList
	 * 			  Sunglass Space Collection object
	 * 
	 */
	public SpaceList getSpaces(String project_id){
		JSONObject spaceJSON = this.httpGetRequest("/projects/"+project_id+"/spaces");
		SpaceList spaces = gson.fromJson(spaceJSON.toString(), SpaceList.class);
		return spaces;
	}
	/**
	 * get a space by its unique id
	 * 
	 * @param project_id
	 * 			  the unique id of the project
	 * @param id
	 *            the unique id of the space
	 * @return Space
	 * 			  Sunglass Space object
	 * 
	 */
	public Space getSpace(String project_id, String id){
		JSONObject spaceJSON = this.httpGetRequest("/projects/"+project_id+"/spaces/"+id);
		Space space = gson.fromJson(spaceJSON.toString(), Space.class);
		return space;
	}
	/**
	 * get a list of subspaces of a specific space by its unique id
	 * 
	 * @param project_id
	 * 			  the unique id of the project
	 * @param id
	 *            the unique id of the parent space
	 * @return SubSpaceList
	 * 			  Sunglass SubSpace Collection object
	 * 
	 */	
	public SubSpaceList getSubSpaces(String project_id, String id){
		JSONObject spaceJSON = this.httpGetRequest("/projects/"+project_id+"/spaces/"+id+"/subspaces");
		SubSpaceList spaces = gson.fromJson(spaceJSON.toString(), SubSpaceList.class);
		return spaces;
	}
	/**
	 * get all models from a sunglass project
	 * 
	 * @param project_id
	 * 			  the unique id of the project
	 * @return ModelList
	 * 			  Sunglass Model Collection object
	 */
	public ModelList getModels(String project_id){
		JSONObject modelJSON = this.httpGetRequest("/projects/"+project_id+"/models");
		ModelList models = gson.fromJson(modelJSON.toString(), ModelList.class);
		return models;
	}
	/**
	 * get a model by its unique id
	 * 
	 * @param project_id
	 * 			  the unique id of the project
	 * @param id
	 * 			  the unique id of the model
	 * 
	 * @return Model
	 * 			  Sunglass Model object
	 */
	public Model getModel(String project_id, String id){
		JSONObject modelJSON = this.httpGetRequest("/projects/"+project_id+"/models/"+id);
		Model model = gson.fromJson(modelJSON.toString(), Model.class);
		return model;
	}
	/**
	 * get a list of metamodel(model reference) in a specific space
	 * 
	 * @param project_id
	 * 			  the unique id of the project
	 * @param id
	 * 			  the unique id of the space
	 * 
	 * @return MetaModelModelList
	 * 			  Sunglass MetaModelModel Collection object
	 */
	public MetaModelList getMetaModels(String project_id, String space_id){
		JSONObject metaModelJSON = this.httpGetRequest("/projects/"+project_id+"/spaces/"+space_id+"/metamodels");
		MetaModelList metaModels = gson.fromJson(metaModelJSON.toString(), MetaModelList.class);
		return metaModels;
	}
	/**
	 * get a metamodel(model reference) in a specific space by its unique id
	 * 
	 * @param project_id
	 * 			  the unique id of the project
	 * @param space_id
	 * 			  the unique id of the space
	 * @param id
	 * 			  the unique id of the metamodel
	 * 
	 * @return MetaModelModel
	 * 			  Sunglass MetaModelModel object
	 */
	public MetaModel getMetaModel(String project_id, String space_id, String id){
		JSONObject metaModelJSON = this.httpGetRequest("/projects/"+project_id+"/spaces/"+space_id+"/metamodels/"+id);
		MetaModel metaModel = gson.fromJson(metaModelJSON.toString(), MetaModel.class);
		return metaModel;
	}
	/**
	 * get a list of versions for a model by its unique id
	 * 
	 * @param project_id
	 * 			  the unique id of the project
	 * @param model_id
	 * 			  the unique id of the model
	 * 
	 * @return VersionList
	 * 			  Sunglass Version Collection object
	 */
	public VersionList getModelVersions(String project_id, String model_id){
		JSONObject versionJSON = this.httpGetRequest("/projects/"+project_id+"/models/"+model_id+"/versions");
		VersionList versions = gson.fromJson(versionJSON.toString(), VersionList.class);
		return versions;
	}
	/**
	 * get a specific version of a model by its unique id
	 * 
	 * @param project_id
	 * 			  the unique id of the project
	 * @param model_id
	 * 			  the unique id of the model
	 * @param id
	 * 		      the unique id of the version
	 * @return Version
	 * 			  Sunglass Version object
	 */
	public Version getModelVersion(String project_id, String model_id, String id){
		JSONObject versionJSON = this.httpGetRequest("/projects/"+project_id+"/models/"+model_id+"/versions/"+id);
		Version version = gson.fromJson(versionJSON.toString(), Version.class);
		return version;
	}
	
	/**
	 * get a list of notes in a space by its unique id
	 * 
	 * @param project_id
	 * 			  the unique id of the project
	 * @param space_id
	 * 			  the unique id of the space
	 * @return NoteList
	 * 			  Sunglass Note Collection object
	 */
	public NoteList getNotes(String project_id, String space_id){
		JSONObject noteJSON = this.httpGetRequest("/projects/"+project_id+"/spaces/"+space_id+"/notes");
		NoteList notes = gson.fromJson(noteJSON.toString(),NoteList.class);
		return notes;
	}
	/**
	 * get a note in a space by its unique id
	 * 
	 * @param project_id
	 * 			  the unique id of the project
	 * @param space_id
	 * 			  the unique id of the space
	 * @param id
	 * 			  the unique id of the note
	 * @return Note
	 * 			  Sunglass Note object
	 */
	public Note getNote(String project_id, String space_id, String id){
		JSONObject noteJSON = this.httpGetRequest("/projects/"+project_id+"/spaces/"+space_id+"/notes/"+id);
		Note note = gson.fromJson(noteJSON.toString(),Note.class);
		return note;
	}
	/**
	 * get a list of attachments from a note by its unique id
	 * 
	 * @param project_id
	 * 			  the unique id of the project
	 * @param space_id
	 * 			  the unique id of the space
	 * @param note_id
	 * 			  the unique id of the note
	 * @return AttachmentList
	 * 			  Sunglass Attachment Collection object
	 */
	public AttachmentList getNoteAttachments(String project_id, String space_id, String note_id){
		JSONObject attachmentJSON = this.httpGetRequest("/projects/"+project_id+"/spaces/"+space_id+"/notes/"+note_id+"/attachments");
		AttachmentList attachments = gson.fromJson(attachmentJSON.toString(),AttachmentList.class);
		return attachments;
	}
	
	/**
	 * get a specific attachment from a note by its unique id
	 * 
	 * @param project_id
	 * 			  the unique id of the project
	 * @param space_id
	 * 			  the unique id of the space
	 * @param note_id
	 * 			  the unique id of the note
	 * @param id
	 * 			  the unique id of the attachment
	 * @return Attachment
	 * 			  Sunglass Attachment object
	 */
	public Attachment getNoteAttachment(String project_id, String space_id, String note_id, String id){
		JSONObject attachmentJSON = this.httpGetRequest("/projects/"+project_id+"/spaces/"+space_id+"/notes/"+note_id+"/attachments/"+id);
		Attachment attachment = gson.fromJson(attachmentJSON.toString(),Attachment.class);
		return attachment;
	}
	/**
	 * get a list of collaborators from a project by its unique id
	 * 
	 * @param project_id
	 * 			  the unique id of the project
	 * @return UserList
	 * 			  Sunglass User Collection object
	 */
	public UserList getCollaborators(String project_id){
		JSONObject userJSON = this.httpGetRequest("/projects/"+project_id+"/collaborators");
		UserList users = gson.fromJson(userJSON.toString(), UserList.class);
		return users;
	}
	
	
	//SET
	/**
	 * Set the name of a project
	 * 
	 * @param project_id
	 * 			  the unique id of the project
	 * @param name
	 * 			 the new name for the project
	 * @return Project
	 * 			  Sunglass Project object
	 */
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
	/**
	 * Set the description of a project
	 * 
	 * @param project_id
	 * 			  the unique id of the project
	 * @param description
	 * 			 the new description for the project
	 * @return Project
	 * 			  Sunglass Project object
	 */
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
	/**
	 * Set the name of a space
	 * 
	 * @param project_id
	 * 			  the unique id of the project
	 * @param id
	 * 			  the unique id of the space
	 * @param name
	 * 			 the new name for the space
	 * @return Space
	 * 			  Sunglass Space object
	 */
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
	/**
	 * Set the transformation matrix of a space
	 * 
	 * @param project_id
	 * 			  the unique id of the project
	 * @param id
	 * 			  the unique id of the space
	 * @param transformMatrix
	 * 			 the transformation matrix for the space [Mxx,Mxy,Mxz,Cx], [Myx,Myy,Myz,Cy], [Mzx,Mzy,Mzz,Cz], [Lx,Ly,Lz,W]
	 * @return Space
	 * 			  Sunglass Space object
	 */
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
	/**
	 * Set the name of a metamodel
	 * 
	 * @param project_id
	 * 			  the unique id of the project
	 * @param space_id
	 * 			  the unique id of the space
	 * @param id
	 * 			  the unique id of the metamodel
	 * @param name
	 * 			 the new name for the metamodel
	 * @return MetaModel
	 * 			  Sunglass MetaModel object
	 */
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
	/**
	 * Set the transformation matrix of a metamodel
	 * 
	 * @param project_id
	 * 			  the unique id of the project
	 * @param space_id
	 * 			  the unique id of the space
	 * @param id
	 * 			  the unique id of the metamodel
	 * @param transformMatrix
	 * 			 the transformation matrix for the metamodel [Mxx,Mxy,Mxz,Cx], [Myx,Myy,Myz,Cy], [Mzx,Mzy,Mzz,Cz], [Lx,Ly,Lz,W]
	 * @return MetaModel
	 * 			  Sunglass MetaModel object
	 */
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
	/**
	 * Set the data of a note
	 * 
	 * @param project_id
	 * 			  the unique id of the project
	 * @param space_id
	 * 			  the unique id of the space
	 * @param id
	 * 			  the unique id of the note
	 * @param data
	 * 			 the new data for the note
	 * @return Note
	 * 			  Sunglass Note object
	 */
	public Note setNoteData(String project_id, String space_id, String id, JSONObject noteData){
		
		JSONObject noteJSON = this.httpPostRequest("/projects/"+project_id+"/spaces/"+space_id+"/notes/"+id, noteData);
		Note note = gson.fromJson(noteJSON.toString(), Note.class);
		return note;
	}
	/**
	 * Set the data of a note attachment
	 * 
	 * @param project_id
	 * 			  the unique id of the project
	 * @param space_id
	 * 			  the unique id of the space
	 * @param note_id
	 * 			  the unique id of the note
	 * @param id
	 * 			  the unique id of the attachment
	 * @param data
	 * 			 the new data for the attachment
	 * @return Attachment
	 * 			  Sunglass Attachment object
	 */
	public Attachment setNoteAttachment(String project_id, String space_id, String note_id, String id, JSONObject attachmentData){
		
		JSONObject attachmentJSON = this.httpPostRequest("/projects/"+project_id+"/spaces/"+space_id+"/notes/"+note_id+"/attachments/"+id, attachmentData);
		Attachment attachment = gson.fromJson(attachmentJSON.toString(), Attachment.class);
		return attachment;
	}
	
	//CREATE
	/**
	 * create a new project
	 *
	 * @param name
	 * 			 the name for the new project
	 * @return Project
	 * 			  Sunglass Project object
	 */
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
	/**
	 * create a new project
	 *
	 * @param name
	 * 			 the name for the new project
	 * @param description
	 * 			 the description for the new project
	 * @return Project
	 * 			  Sunglass Project object
	 */
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
	/**
	 * create a new space
	 *
	 * @param project_id
	 * 			the unique id of the project to create the space in
	 * @param name
	 * 			 the name for the new space
	 * @return Space
	 * 			  Sunglass Space object
	 */
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
	/**
	 * create a new space
	 *
	 * @param project_id
	 * 			the unique id of the project to create the space in
	 * @param name
	 * 			 the name for the new space
	 * @param description
	 * 			 the description for the new space
	 * @return Space
	 * 			  Sunglass Space object
	 */
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
	
	/**
	 * create a new metamodel
	 *
	 * @param project_id
	 * 			the unique id of the project to create the metamodel in
	 * @param space_id
	 * 			the unique id of the space to create the metamodel in
	 * @param model_id
	 * 			 the unique id of the model to reference
	 * @return MetaModel
	 * 			  Sunglass MetaModel object
	 */
	public MetaModel createMetaModel(String project_id, String space_id, String model_id){
		JSONObject data = new JSONObject();
		try{
			data.put("modelId", model_id);
		}catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println(model_id);
		JSONObject metaModelJSON = this.httpPostRequest("/projects/"+project_id+"/spaces/"+space_id+"/metamodels", data);
		MetaModel metaModel = gson.fromJson(metaModelJSON.toString(), MetaModel.class);
		return metaModel;
	}
	/**
	 * create a new note
	 *
	 * @param project_id
	 * 			the unique id of the project to create the note in
	 * @param space_id
	 * 			 the unique id of the space to create the note in
	 * @param noteData
	 * 			 the data to create the new note from
	 * @return Note
	 * 			  Sunglass Note object
	 */
	public Note createNote(String project_id, String space_id, JSONObject noteData){
		JSONObject noteJSON = this.httpPostRequest("/projects/"+project_id+"/spaces/"+space_id+"/notes", noteData);
		Note note = gson.fromJson(noteJSON.toString(), Note.class);
		return note;
	}
	
	//DELETE
	/**
	 * delete a project
	 *
	 * @param id
	 * 			the unique id of the project to delete
	 */
	public void deleteProject(String id){
		this.httpDeleteRequest("/projects/"+id);
	}
	/**
	 * delete a space
	 *
	 * @param project_id
	 * 			the unique id of the project that the space is in
	 * @param id
	 * 			the unique id of the space to delete
	 */
	public void deleteSpace(String project_id, String id){
		this.httpDeleteRequest("/projects/"+project_id+"/spaces/"+id);
	}
	/**
	 * delete a model
	 *
	 * @param project_id
	 * 			the unique id of the project that the model is in
	 * @param id
	 * 			the unique id of the model to delete
	 */
	public void deleteModel(String project_id, String id){
		this.httpDeleteRequest("/projects/"+project_id+"/models/"+id);
	}
	/**
	 * delete a metamodel
	 *
	 * @param project_id
	 * 			the unique id of the project that the metamodel is in
	 * @param space_id
	 * 			the unique id of the space to that the metamodel is in
	 * @param id
	 * 			the unique id of the metamodel to delete
	 */
	public void deleteMetaModel(String project_id, String space_id, String id){
		this.httpDeleteRequest("/projects/"+project_id+"/spaces/"+space_id+"/metamodels/"+id);
	}
	/**
	 * delete a note
	 *
	 * @param project_id
	 * 			the unique id of the project that the note is in
	 * @param space_id
	 * 			the unique id of the space to that the note is in
	 * @param id
	 * 			the unique id of the note to delete
	 */
	public void deleteNote(String project_id, String space_id, String id){
		this.httpDeleteRequest("/projects/"+project_id+"/spaces/"+space_id+"/notes/"+id);
	}
	
	//UPLOAD AND DOWNLOAD
	/**
	 * upload a model and set its version
	 *
	 * @param endpoint
	 * 			the location to upload the model to (usually project/{id}/models)
	 * @param modelFile
	 * 			the model file to upload (.stl, .obj, .3ds, .sldprt, .sldasm, .step)
	 * @param coverImageFile
	 * 			an image file to represent the model in previews (.jpg .png)
	 * @param message
	 * 			a commit message to describe the state of the model
	 * @return Model
	 * 			a Sunglass Model Object
	 */
	public Model uploadModelandVersion(String endpoint, File modelFile, File coverImageFile, String message ){
	
		Map<String, Object> postParameters = new HashMap<String, Object>();
		
		postParameters.put("message", message);
		postParameters.put("file", modelFile);	
		postParameters.put("cover_image", coverImageFile);
		
		JSONObject modelJSON = this.postMultiPartFormData(endpoint, postParameters);
		
		ModelList models = gson.fromJson(modelJSON.toString(), ModelList.class);
		
		return models.getModels().get(0);
	}
	/**
	 * update a model and set its version
	 *
	 * @param endpoint
	 * 			the location to upload the model to (usually project/{id}/models/{id})
	 * @param modelFile
	 * 			the model file to upload (.stl, .obj, .3ds, .sldprt, .sldasm, .step)
	 * @param coverImageFile
	 * 			an image file to represent the model in previews (.jpg .png)
	 * @param message
	 * 			a commit message to describe the state of the model
	 */
	public Model updateModelandVersion(String endpoint, File modelFile, File coverImageFile, String message){
		
		Map<String, Object> postParameters = new HashMap<String, Object>();
		
		postParameters.put("message", message);
		postParameters.put("file", modelFile);	
		postParameters.put("cover_image", coverImageFile);
		
		JSONObject modelJSON = this.postMultiPartFormData(endpoint, postParameters);
		
		NewModel model = gson.fromJson(modelJSON.toString(), NewModel.class);
		return model.getModel();
		
	}
	/**
	 * download any file (models, textures, images etc) from the server
	 *
	 * @param endpoint
	 * 			the location of the file to download
	 * @param filepath
	 * 			the container to save the file to
	 * @return File
	 * 			Java File object
	 */
	public File downloadFile( String endpoint, File filepath){
		
		if(VERBOSE) System.out.println("[ConnMgr/saveToContainer] --Saving remote file: "+ endpoint + " to container:" + filepath.getName());
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
			
		try {
	
			HttpGet request = new HttpGet(this.url + endpoint);
			String auth = this.sid+":"+this.token;
			String encoding = Base64.encodeBase64String(auth.getBytes());
			request.setHeader("Authorization", "Basic " + encoding);
			HttpResponse response;

			if(VERBOSE) System.out.println("[ConnMgr/saveToContainer] --Executing request "+ request.getRequestLine());
			response = httpClient.execute(request);
			
			if (response.getStatusLine().getStatusCode() != 200)
			{
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			
			if(VERBOSE) System.out.println("[ConnMgr/saveToContainer] --Response details"+ response.getEntity().getContent());
			
			File f = FileIO.convertStreamToFile(response.getEntity().getContent(), filepath);

			return f;

		} catch (ClientProtocolException e) {e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();
		}

		return null;
		
	}
	
	//------------------ GENERIC TRANSFER FUNCTIONS

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
	
	//------------------ FILE TRANSFER FUNCTIONS
	
	public JSONObject postMultiPartFormData(String endpoint, Map<String, Object> postParameters){
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		
		try{
			//httpClient = (DefaultHttpClient) wrapClient(httpClient);
			
			
			HttpPost request = new HttpPost(this.url + endpoint);
			
			
			MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
			
			Iterator it = postParameters.entrySet().iterator();
			
			while(it.hasNext()){
				Map.Entry<String, Object> postParameter = (Map.Entry<String, Object>)it.next();
				
				//is it a file?
				if (postParameter.getValue() instanceof File) {	
					
					File file = (File)postParameter.getValue();
					FileBody fileBody = new FileBody(file, new MimetypesFileTypeMap().getContentType(file));
					entity.addPart(postParameter.getKey().toString(),fileBody);
					
				}else{
					
					StringBody stringBody = new StringBody( postParameter.getValue().toString(), "text/plain", Charset.forName( "UTF-8" ));	
					entity.addPart(postParameter.getKey().toString(), stringBody);
				}
				
				
			}
			
			
			request.setEntity(entity);
			
			String auth = this.sid+":"+this.token;
			String encoding = Base64.encodeBase64String(auth.getBytes());
			request.setHeader("Authorization", "Basic " + encoding);
			
			
			if(VERBOSE) System.out.println("[ConnMgr/upload] --Executing request " + request.getRequestLine());
			
			HttpResponse response = httpClient.execute(request);
			
			if (response.getStatusLine().getStatusCode() != 200) throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			
			
			JSONObject responseJSON = new JSONObject(FileIO.convertStreamToString(response.getEntity().getContent()));
			
			if (VERBOSE) {System.out.println("[ConnMgr/upload] --Response: " + responseJSON.toString());}
			
			return responseJSON;
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
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
	
	
	
}
