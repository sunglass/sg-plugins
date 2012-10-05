import com.sg.json.*;
import com.sg.utils.*;
import com.sg.*;
import com.sg.models.*;

void setup() {
  size(100, 100, P3D);

  //Create an Sunglass object
  SunglassConnect sg = new SunglassConnect(this,"ACnrfkye0w+KEgkiOeZcDpD+LO2Wc=", "wfnZ/t7fBLaguRnmGPk65jG/34M=");
  sg.cm.VERBOSE = false;
  
  try {
    //Authenticate
    int userId = sg.authenticate();

    //list your existing projects
    HashMap sgProjList = sg.getProjectList();
    Iterator i = sgProjList.entrySet().iterator(); 
    while (i.hasNext ()) {
      Map.Entry entry = (Map.Entry)i.next();
      System.out.print("[Project ID:" + entry.getKey() + ", "); 
      System.out.print("Project Name: " + ((Project) entry.getValue()).getName() + ", ");
      System.out.println("RootSpace ID:" +((Project) entry.getValue()).getRootSpaceId() + "]");
    }
    
    //Create a project
    Project proj = sg.createProject("My Processing Project");
    System.out.print("[New project created with ID:" + proj.getId() + "]");

    //Upload model to the Project
    Model m = sg.uploadModelToProject(proj);

    //Download model from the Project
    //The file path string is relative to your Processing application
    sg.downloadModel(proj, m.getId(), "MySunglassProjects/");
    } 
  catch (Exception e) {
    e.printStackTrace();
  }
}

