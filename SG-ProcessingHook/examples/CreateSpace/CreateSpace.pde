import com.sg.*;
import com.sg.utils.*;
import com.sg.models.*;

//Varibles for Connection Manager
String url = "https://www.sunglass.io/api/v1";

//PASTE YOUR SID AND TOKEN HERE : see https://www.sunglass.io/dev.html
String sid = "";
String token = "";
boolean verbose = false;

ConnectionManager sg;

void setup(){
  
  //Create a Connection Manager
  sg = new ConnectionManager(url, sid, token, verbose);
 
  createSpace();
  
}

void createSpace(){

  //Get the first Project that we own
  Project myProject = sg.getProjects().getOwner().get(0);
  
  //Create a name for the Space
  String name = "Processing Space";
  
  //Get the Project's Root Space
  Space rootSpace = sg.getRootSpace(myProject.getId());
  
  //Create a space
  Space mySpace = sg.createSpace(myProject.getId(),rootSpace.getId(),name);
  
  println(mySpace.getId());
  println(mySpace.getName());

}