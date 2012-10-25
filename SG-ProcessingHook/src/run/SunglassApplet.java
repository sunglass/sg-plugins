package run;

import java.util.Collection;

import processing.core.PApplet;
import processing.opengl.*;

import com.sg.*;
import com.sg.json.JSONException;
import com.sg.json.JSONObject;
import com.sg.models.Project;
import com.sg.models.ProjectList;
import com.sg.models_new.*;


public class SunglassApplet extends PApplet {

	private static final long serialVersionUID = 1L;
	
	ConnectionManager cm;
	
	String url = "https://sunglass.io/api/v1";
	String sid = "AChoAAVdthzr7s";
	String token = "K9bEJ%2FVZ61e9zn";
	Boolean verbose = true;
	
	public void setup(){
		size(200,200, OPENGL);
		
		cm = new ConnectionManager(url, sid, token, verbose);
				
		ProjectList projects = cm.getProjects();
		
		for(Project project : projects.getOwner()){
			println(project.getName());
			println(project.getLinks().getRootSpace());
		}
		
		//Upload a model
		
		//Download a model
		
		//Add Note Attachment
	
		
	}
	
	public void draw(){
		background(255,0,0);
	}
	
	public static void main(String _args[]) {
		PApplet.main(new String[] { run.SunglassApplet.class.getName() });
	}
}
