import com.sg.*;
import com.sg.utils.*;
import com.sg.models.*;
  
//Varibles for Connection Manager
String url = "https://www.sunglass.io/api/v1";

//PASTE YOUR SID AND TOKEN HERE : see https://www.sunglass.io/dev.html
String sid = "";
String token = "";
boolean verbose = false;

//Variables for File Downloads

//The id of the project that contains the model
String projectId = "";
//The id of the model
String modelId = "";  
//The api endpoint for retrieving the model in its original format
String endpoint = "/projects/"+projectId+"/models/"+modelId+"/data?format=original";
//the name to save the model as
String filename = "Model.obj";

ConnectionManager sg;

OBJModel obj;
  
float rotX, rotY;

//Switch to make sure that the model is loaded
boolean objLoaded = false;

  
void setup(){
  size(600,600, OPENGL);

  //Create a Connection Manager
  sg = new ConnectionManager(url, sid, token, verbose);
  
  //Setup the camera
  float fov = (float) (PI/3.0);
  float cameraZ = (float) ((height/2.0) / tan((float) (fov/2.0)));
  perspective(fov, (float)width/height, (float)cameraZ/100, (float)cameraZ*100);
  noStroke();

  //Download the model    
  downloadModel();
   
  }
  
  void draw(){
    //Scene settings
    background(0);
    lights();
    pushMatrix();
    translate(width/2, height/2, 0);
    rotateX(rotY);
    rotateY(rotX);
    
    //If the model is loaded then draw it
    if(objLoaded){
      obj.draw();
    }
    
    popMatrix();
  }
    
  public void downloadModel(){
    
    //The filepath to save the file to
    File filepath = new File(dataPath(filename));
    
    //Download the model using the generic file downloader method
    File model = sg.downloadFile(endpoint, filepath);
    
    //Get th extension of the downloaded file
    int dotposition= filepath.getName().lastIndexOf(".");
    String ext = model.getName().substring(dotposition + 1, model.getName().length()); 
    
    //Check to make sure that the file is an obj file
    if(ext.equals("obj") || ext.equals("OBJ")){
      
      //Load the file into an obj model for Processing
      obj = new OBJModel(this, dataPath(model.getName()));
      //Scale and center the model in the scene
      obj.scale((float)25);
      obj.translateToCenter();
      
      //The model is loaded lets draw it
      objLoaded = true;
      
    }else{
      println(ext + " files arent supported. Only Alias obj models are supported in Sunglass for Processing");
    }
  }
  
  //Rotate on mouseDrag
  public void mouseDragged()
  {
      rotX += (mouseX - pmouseX) * 0.01;
      rotY -= (mouseY - pmouseY) * 0.01;
  }


