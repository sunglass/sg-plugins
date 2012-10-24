/*!
 * Sunglass Connection Manager v1.0
 * http://sunglass.io
 *
 * Copyright 2012 Design Play Technologies
 * https://sunglass.io/tnc.html
 *
 * Date: Tues Oct 23 2012
 */

var Sunglass = {};

/**
* @namespace Sunglass
* @class ConnectionManager
*/
Sunglass.ConnectionManager =  {
	
	/**
	* @property url
	* @type {String}
	* @default "https://sunglass.io/api/v1"
	*/	
	url : "https://sunglass.io/api/v1",
	
	/**
	* @property sid
	* @type {String}
	* @default ""
	*/
	sid : "",
	
	/**
	* @property token
	* @type {String}
	* @default ""
	*/
	token : "",
	
	/**
	* @property b64
	* @type {String}
	* @default ""
	*/
	b64 : "",
	
	/**
	* @property content_type
	* @type {String}
	* @default "application/json"
	*/
	content_type : "application/json",
	
	/**
	* @property dev
	* @type {Boolean}
	* @default true
	*/
	dev : true,
	
	/**
	* @property version
	* @type {Number}
	* @default 1.0
	*/
	version : 1.0,
	
	/**
	* @method init
	* @param {String} url
	* @param {String} sid
	* @param {String} token
	* @param {String} content_type
	* @param {Boolean} dev Are we gonna use developer mode
	*/
	init : function (url, sid, token, content_type, dev){		

		Sunglass.ConnectionManager.url = url;
		Sunglass.ConnectionManager.sid = sid;
		Sunglass.ConnectionManager.token = token;
		Sunglass.ConnectionManager.content_type = content_type;
		Sunglass.ConnectionManager.dev = dev;	
		Sunglass.ConnectionManager.b64 = "Basic " + this.encode(this.sid+":"+this.token);
	},
	
	//Misc
	
	encode : function (text) {
		if(typeof(btoa) === 'function'){
			return window.btoa(unescape(encodeURIComponent(text)));
		}else{
			return Base64.encode(text);
		}
	},
	
	authenticate : function (callback){
		return true;
	},
	
	//GET
	
	/**
	* @method getUserDetails
	* @param {Method} callback
	*/
	getUserDetails : function (callback){this.ajax.ajaxGET(this.b64, this.url + "/users", callback)},
	
	/**
	* @method getProjects
	* @param {Method} callback
	*/
	getProjects : function (callback){this.ajax.ajaxGET(this.b64, this.url + "/projects", callback)},
	
	/**
	* @method getProject
	* @param {Method} callback
	*/
	getProject : function (project_id, callback){this.ajax.ajaxGET(this.b64, this.url + "/projects/"+project_id, callback)},
	
	/**
	* @method getSpaces
	* @param {Method} callback
	*/
	getSpaces : function (project_id, callback){this.ajax.ajaxGET(this.b64, this.url + "/projects/"+project_id+"/spaces", callback)},
	
	/**
	* @method getSpace
	* @param {Method} callback
	*/
	getSpace : function (project_id, space_id, callback){this.ajax.ajaxGET(this.b64, this.url + "/projects/"+project_id+"/spaces/"+space_id, callback)},
	
	/**
	* @method getSubSpaces
	* @param {Method} callback
	*/
	getSubSpaces : function (project_id, parent_space_id, callback){this.ajax.ajaxGET(this.b64, this.url + "/projects/"+project_id+"/spaces/"+parent_space_id+"/subspaces", callback)},
	
	/**
	* @method getSubSpace
	* @param {Method} callback
	*/
	getSubSpace : function (project_id, parent_space_id, space_id, callback){this.ajax.ajaxGET(this.b64, this.url + "/projects/"+project_id+"/spaces/"+parent_space_id+"/subspaces/"+space_id, callback)},
	
	/**
	* @method getModels
	* @param {Method} callback
	*/
	getModels : function (project_id, callback){this.ajax.ajaxGET(this.b64, this.url + "/projects/"+project_id+"/models", callback)},
	
	/**
	* @method getModel
	* @param {Method} callback
	*/
	getModel : function (project_id, model_id, callback){this.ajax.ajaxGET(this.b64, this.url + "/projects/"+project_id+"/models/"+model_id, callback)},
	
	/**
	* @method getMetaModels
	* @param {Method} callback
	*/
	getMetaModels : function (project_id, space_id, callback){this.ajax.ajaxGET(this.b64, this.url + "/projects/"+project_id+"/spaces/"+space_id+"/metamodels", callback)},
	
	/**
	* @method getMetaModel
	* @param {Method} callback
	*/		
	getMetaModel : function (project_id, space_id, meta_model_id, callback){this.ajax.ajaxGET(this.b64, this.url + "/projects/"+project_id+"/spaces/"+space_id+"/metamodels/"+meta_model_id, callback)},
	
	/**
	* @method getModelVersions
	* @param {Method} callback
	*/
	getModelVersions : function (project_id, model_id, callback){this.ajax.ajaxGET(this.b64, this.url + "/projects/"+project_id+"/models/"+model_id+"/versions", callback)},
	
	/**
	* @method getModelVersion
	* @param {Method} callback
	*/
	getModelVersion : function (project_id, model_id, callback){this.ajax.ajaxGET(this.b64, this.url + "/projects/"+project_id+"/models/"+model_id+"/versions/"+version_id, callback)},
	
	/**
	* @method getNotes
	* @param {Method} callback
	*/
	getNotes : function (project_id, space_id, callback){this.ajax.ajaxGET(this.b64, this.url + "/projects/"+project_id+"/spaces/"+space_id+"/notes", callback)},
	
	/**
	* @method getNote
	* @param {Method} callback
	*/
	getNote : function (project_id, space_id, note_id, callback){this.ajax.ajaxGET(this.b64, this.url + "/projects/"+project_id+"/spaces/"+space_id+"/notes/"+note_id, callback)},
	
	/**
	* @method getNoteAttachments
	* @param {Method} callback
	*/
	getNoteAttachments : function (project_id, space_id, note_id, callback){this.ajax.ajaxGET(this.b64, this.url + "/projects/"+project_id+"/spaces/"+space_id+"/notes/"+note_id+"/attachments", callback)},
	
	/**
	* @method getNoteAttachment
	* @param {Method} callback
	*/
	getNoteAttachment : function (project_id, space_id, note_id, attachment_id, callback){this.ajax.ajaxGET(this.b64, this.url + "/projects/"+project_id+"/spaces/"+space_id+"/notes/"+note_id+"/attachments/"+attachment_id, callback)},
	
	/**
	* @method getCollaborators
	* @param {Method} callback
	*/
	getCollaborators : function (project_id, callback){this.ajax.ajaxGET(this.b64, this.url + "/projects/"+project_id+"/collaborators", callback)},
	
	/**
	* @method getCollaborators
	* @param {Method} callback
	*/
	getCollaborators : function (project_id, space_id, callback){this.ajax.ajaxGET(this.b64, this.url + "/projects/"+project_id+"/spaces/"+space_id+"/collaborators", callback)},
	
	/**
	* @method getCollaborator
	* @param {Method} callback
	*/
	getCollaborator : function (project_id, collaborator_id, callback){this.ajax.ajaxGET(this.b64, this.url + "/projects/"+project_id+"/collaborators/"+collaborator_id, callback)},
	
	/**
	* @method getCollaborator
	* @param {Method} callback
	*/
	getCollaborator : function (project_id, space_id, collaborator_id, callback){this.ajax.ajaxGET(this.b64, this.url + "/projects/"+project_id+"/spaces/"+space_id+"/collaborators/"+collaborator_id, callback)},
	
	//SET
	/**
	* @method setProjectName
	* @param {Method} callback
	*/
	setProjectName : function (project_id, project_name, callback){
		data = { "name" : project_name };
		this.ajax.ajaxPOST(this.b64, this.url + "/projects/"+project_id, data, callback);
	},
	/**
	* @method setProjectDescription
	* @param {Method} callback
	*/
	setProjectDescription : function (project_id, project_description, callback){
		data = { "description" : project_description };
		this.ajax.ajaxPOST(this.b64, this.url + "/projects/"+project_id, data, callback);
	},
	
	/**
	* @method setSpaceName
	* @param {Method} callback
	*/
	setSpaceName : function (project_id, space_id, space_name, callback){
		data = { "name" : space_name };
		this.ajax.ajaxPOST(this.b64, this.url + "/projects/"+project_id+"/spaces/"+space_id, data, callback);
	},
	/**
	* @method setSpaceTransformationMatrix
	* @param {Method} callback
	*/
	setSpaceTransformationMatrix : function (project_id, space_id, transformation_matrix, callback){
		data = {"transformMatrix" : transformation_matrix};
		this.ajax.ajaxPOST(this.b64, this.url + "/projects/"+project_id+"/spaces/"+space_id, data, callback);
	},
	/**
	* @method setMetaModelName
	* @param {Method} callback
	*/
	setMetaModelName : function (project_id, space_id, meta_model_id, meta_model_name, callback){
		data = {"name" : meta_model_name};
		this.ajax.ajaxPOST(this.b64, this.url + "/projects/"+project_id+"/spaces/"+space_id+"/metamodels/"+meta_model_id, data, callback);
	},
	/**
	* @method setMetaModelTransformationMatrix
	* @param {Method} callback
	*/
	setMetaModelTransformationMatrix : function (project_id, space_id, meta_model_id, tran){
		data = {"transformMatrix" : transformation_matrix};
		this.ajax.ajaxPOST(this.b64, this.url + "/projects/"+project_id+"/spaces/"+space_id+"/metamodels/"+meta_model_id, data, callback);
	},
	/**
	* @method setNoteData
	* @param {Method} callback
	*/
	setNoteData : function (project_id, space_id, note_id, note_data, callback){
		this.ajax.ajaxPOST(this.b64, this.url + "/projects/"+project_id+"/spaces/"+space_id+"/notes/"+note_id, note_data, callback);
	},
	/**
	* @method setNoteAttachment
	* @param {Method} callback
	*/
	setNoteAttachment : function (project_id, space_id, note_id, attachment_id, attachment_data, callback){
		this.ajax.ajaxPOST(this.b64, this.url + "/projects/"+project_id+"/spaces/"+space_id+"/notes/"+note_id+"/attachments/"+attachment_id, attachment_data, callback);
	},
	
	//CREATE
	/**
	* @method createProject
	* @param {Method} callback
	*/
	createProject : function (project_name, callback){
		data = { "name" : project_name };
		this.ajax.ajaxPOST(this.b64, this.url + "/projects", data, callback);
	},
	/**
	* @method createProject
	* @param {Method} callback
	*/
	createProject : function (project_name, project_description, callback){
		data = { "name" : project_name, "description" : project_description };
		this.ajax.ajaxPOST(this.b64, this.url + "/projects", data, callback);
	},
	/**
	* @method createSpace
	* @param {Method} callback
	*/
	createSpace : function (project_id, parent_space_id, space_name, callback){
		data = { "name" : space_name };
		this.ajax.ajaxPOST(this.b64, this.url + "/projects/"+project_id+"/spaces/"+parent_space_id+"/subSpaces", data, callback);
	},
	/**
	* @method createMetaModel
	* @param {Method} callback
	*/
	createMetaModel : function (project_id, space_id, model_id, callback){
		data = { "modelId" : model_id };
		this.ajax.ajaxPOST(this.b64, this.url + "/projects/"+project_id+"/spaces/"+space_id+"/metamodels", data, callback);
	},
	/**
	* @method createNote
	* @param {Method} callback
	*/
	createNote : function (project_id, space_id, note_data, callback){
		
		this.ajax.ajaxPOST(this.b64, this.url + "/projects/"+project_id+"/spaces/"+space_id+"/notes", note_data, callback);
	},
		
	//DELETE
	/**
	* @method deleteProject
	* @param {Method} callback
	*/
	deleteProject : function (project_id, callback){this.ajax.ajaxDELETE(this.b64, this.url + "/projects/"+project_id, callback)},
	/**
	* @method deleteSpace
	* @param {Method} callback
	*/
	deleteSpace : function (project_id, space_id, callback){this.ajax.ajaxDELETE(this.b64, this.url + "/projects/"+project_id+"/spaces/"+space_id, callback)},
	/**
	* @method deleteModel
	* @param {Method} callback
	*/
	deleteModel : function (project_id, model_id, callback){this.ajax.ajaxDELETE(this.b64, this.url + "/projects/"+project_id+"/models/"+model_id, callback)},
	/**
	* @method deleteMetaModel
	* @param {Method} callback
	*/
	deleteMetaModel : function (project_id, space_id, meta_model_id, callback){this.ajax.ajaxDELETE(this.b64, this.url + "/projects/"+project_id+"/spaces/"+space_id+"/metamodels/"+meta_model_id, callback)},
	
	/**
	* @method deleteNote
	* @param {Method} callback
	*/
	deleteNote : function (project_id, space_id, note_id, callback){this.ajax.ajaxDELETE(this.b64, this.url + "/projects/"+project_id+"/spaces/"+space_id+"/notes/"+note_id, callback)},
	
	//todo
	//UPLOAD AND DOWNLOAD
	
	downloadModel : function (project_id, model_id, file_path, callback){
		
	},
	
	downloadModel : function (project_id, model_id, file_name, file_path, model_type, callback){
		
	},
	
	downloadNoteAttachment : function (project_id, space_id, note_id, attachment_id, callback){
		
	},
	
	uploadNoteAttachment : function (project_id, space_id, note_id, attachment_data){
		
	},
	
	uploadModelAndVersion : function (project_id, file_path, file_name, message, cover_image_path, cover_image_name, callback){
		
	},
	
	uploadModelAndVersionAndReference : function (project_id, space_id, file_path, file_name, message, cover_image_path, cover_image_name, callback){
		
	}, 
	
	updateModelAndVersion : function (project_id, model_id, message, file_name, file_path, cover_image_name, cover_image_path, callback) {
		
	},
	
	
	ajax : {
		ajaxGET : function(credentials, url, callback){

			var request = $.ajax({
				url : url,
				type : "GET",
				dataType : "json",
				crossDomain: true,
				beforeSend : function(xhr){xhr.setRequestHeader("Authorization", credentials);},
				error:function (xhr, textStatus, thrownError){console.log("Request error: "+xhr.status+" "+thrownError)}    
			});
			
			request.success(callback);
			request.fail(function(xhr, textStatus, thrownError) {console.log( "Request failed: "+xhr.status+" "+ thrownError )});
			request.done(function(msg) {});
			
			
		},
		
		ajaxPOST : function(credentials, url, post_data, callback){
			
			var request = $.ajax({
				url : url,
				type : "POST",
				dataType : "json",
				data : JSON.stringify(post_data),
				crossDomain: true,
				beforeSend : function(xhr){xhr.setRequestHeader("Authorization", credentials);},
				error:function (xhr, textStatus, thrownError){console.log("Request error: "+xhr.status+" "+thrownError)}    
			});
			
			request.success(callback);
			request.fail(function(xhr, textStatus, thrownError) { console.log( "Request failed: "+xhr.status+" "+ thrownError )});
			request.done(function(msg){});
				
		},
		 
		ajaxDELETE : function(credentials, url, post_data, callback){
			var request = $.ajax({
				url : url,
				type : "DELETE",
				dataType : "json",
				data : JSON.stringify(post_data),
				crossDomain: true,
				beforeSend : function(xhr){xhr.setRequestHeader("Authorization", credentials);},
				error:function (xhr, textStatus, thrownError){console.log("Request error: "+xhr.status+" "+thrownError)}    
			});
			
			request.success(callback);
			request.fail(function(xhr, textStatus, thrownError) { console.log( "Request failed: "+xhr.status+" "+ thrownError )});
			request.done(function(msg){});
		}
		 
	}
}
