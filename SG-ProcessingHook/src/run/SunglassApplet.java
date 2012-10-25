package run;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import processing.core.PApplet;
import processing.opengl.*;

import com.sg.*;
import com.sg.json.JSONException;
import com.sg.json.JSONObject;
import com.sg.models.*;


public class SunglassApplet extends PApplet {

	private static final long serialVersionUID = 1L;
	
	ConnectionManager cm;
	
	String url = "https://sunglass.io/api/v1";
	String sid = "";
	String token = "";
	Boolean verbose = true;
	
	public void setup(){
		size(200,200, OPENGL);
		
		cm = new ConnectionManager(url, sid, token, verbose);
		
		
		String project_id = "866";
		
		//ProjectList projects = cm.getProjects();
		
		//for(Project project : projects.getOwner()){
		//	println(project.getName());
		//	println(project.getLinks().getRootSpace());
		//}
		
		//Upload a model and set version
		
		//Lets pick a project and a space
		Project myProject = cm.getProject(project_id);
		Space rootSpace = cm.getSpaces(project_id).getSpaces().get(0);
		
		//lets check out the metamodels
		MetaModelList metaModels = cm.getMetaModels(project_id, rootSpace.getId());
		
		File modelFilepath = new File("/Users/rwillis/Desktop/upload_test/5tt.stl");
		File coverImageFilePath = new File("/Users/rwillis/Desktop/upload_test/coverImage.png");
		String commitMessage = "Testing File Uploading from Processing Library";
		String endpoint = "/projects/"+project_id+"/models";
		
		//upload the model
		Model myModel = cm.uploadModelandVersion(endpoint, modelFilepath, coverImageFilePath, commitMessage);
		
		//create a reference to it so we can see it on the stage
		cm.createMetaModel("857", rootSpace.getId(), myModel.getId());
		
		//Update a model and set version
		
		String modelId = myModel.getId();
		
		File newModelFilePath = new File("/Users/rwillis/Desktop/upload_test/MB-Circus.stl");
		File newCoverImageFilePath  = new File("/Users/rwillis/Desktop/upload_test/coverImage.png");
		commitMessage = "Testing File Upadating from Processing Library";
		endpoint = "/projects/"+project_id+"/models/" + modelId;
		
		Model myNewModel = cm.updateModelandVersion(endpoint, newModelFilePath, newCoverImageFilePath, commitMessage);
		
		
		//generic upload a series of files ie models and textures, commit messages or note attachments;
		Map<String, Object> myFiles = new HashMap<String, Object>();
		String myEndpoint = "";
		//myFiles.put("", "");
		//myFiles.put("", "");
		//myFiles.put("", "");
		//myFiles.put("", "");
		
		//cm.postMultiPartFormData(myEndpoint, myFiles);

		
		//Download a model
		
		String filepath = "/Users/rwillis/Desktop/test.stl";
		File file = new File(filepath);
		String url = "/projects/857/models/2cc8bc10-db0c-4ab0-96d2-d5a6ba5cdbbd/data?format=original";
		
		//cm.downloadFile(url, file);
		
		
		//Add Note Attachment
	
		
	}
	
	public void draw(){
		background(255,0,0);
	}
	
	public static void main(String _args[]) {
		PApplet.main(new String[] { run.SunglassApplet.class.getName() });
	}
}
