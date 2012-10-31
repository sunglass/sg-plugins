
import com.sg.*;
import com.sg.utils.*;
import com.sg.models.*;

String url = "https://www.sunglass.io/api/v1";
String sid = "";
String token = "";
boolean verbose = false;

ConnectionManager sg;

String filename = "pyramid.obj";
boolean record;

void setup() {
  size(400, 400,OPENGL);
  
  sg = new ConnectionManager(url, sid, token, verbose);
  
  

  
}

void draw() {

   pushMatrix();
  translate(width/2, height/2, 0);
  rotateX(30);
  rotateY(30);
  

  if (record) {
    beginRecord("com.sg.utils.OBJExport", filename); 
  }  

  drawShape();

  if (record) {
    endRecord();
    record = false;
    uploadModel();
  }
  
    popMatrix();
}

void drawShape(){
   beginShape();
    vertex(-100, -100, -100);
    vertex( 100, -100, -100);
    vertex( 0,  0,  100);
  
    vertex( 100, -100, -100);
    vertex( 100,  100, -100);
    vertex( 0,  0,  100);
  
    vertex( 100, 100, -100);
    vertex(-100, 100, -100);
    vertex( 0, 0,  100);
  
    vertex(-100,  100, -100);
    vertex(-100, -100, -100);
    vertex( 0,  0,  100);
   endShape();
}

void uploadModel(){

  Project newProject = sg.createProject("Pyramid");
  
  String projectId = newProject.getId();
  String message = "first commit";
  String endpoint = "/projects/"+projectId+"/models";
  File file = new File(sketchPath(filename));
  
  Space rootSpace = sg.getSpaces(projectId).getSpaces().get(0);
  
  Model myModel = sg.uploadModelAndMessage(endpoint, file, message ); 
  
  sg.createMetaModel(projectId, rootSpace.getId(), myModel.getId());
  
}

void mousePressed() {
  record = true;
}
