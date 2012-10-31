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
 
  getProjects();
  
}

void getProjects(){
  
  ProjectList projects = sg.getProjects();
  
  if(projects.getOwner().size() > 0){
    println("You are the owner of " + projects.getOwner().size() + " projects");  
    for(Project project : projects.getOwner()){
      println("Id: "+ project.getId() + " Name: " + project.getName() + " Visibility: " + project.getVisibility());
      println("Created At: " + project.getCreatedAt() + "  Modified At: " + project.getModifiedAt());
      println("");
    }
    println("");
    println("");
  }
  
  if(projects.getAdmin().size() > 0){
    println("You are the admin of " + projects.getAdmin().size() + " projects");  
    for(Project project : projects.getAdmin()){
      println("Id: "+ project.getId() + " Name: " + project.getName() + " Visibility: " + project.getVisibility());
      println("Created At: " + project.getCreatedAt() + "  Modified At: " + project.getModifiedAt());
      println("");
    }
    println("");
    println("");
  }
  
  if(projects.getEditor().size() > 0){
    println("You are the editor of " + projects.getEditor().size() + " projects");    
    for(Project project : projects.getEditor()){
      println("Id: "+ project.getId() + " Name: " + project.getName() + " Visibility: " + project.getVisibility());
      println("Created At: " + project.getCreatedAt() + "  Modified At: " + project.getModifiedAt());
      println("");
    }
    println("");
    println("");
  }
  
  if(projects.getGuest().size() > 0){
    println("You are the guest of " + projects.getGuest().size() + " projects");    
    for(Project project : projects.getGuest()){
      println("Id: "+ project.getId() + " Name: " + project.getName() + " Visibility: " + project.getVisibility());
      println("Created At: " + project.getCreatedAt() + "  Modified At: " + project.getModifiedAt());
      println("");
    }
    println("");
    println("");
  } 
}