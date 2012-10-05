import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import processing.opengl.*;
import saito.objloader.*;
import controlP5.*;

import com.sg.*;
import com.sg.models.*;

static final long serialVersionUID = 1L;
private SunglassConnect sg = null;
private Interface inf;
private HashMap<Integer, Project> sgProjList;
private HashMap<Integer, Model> sgModelList;
private ArrayList<OBJModel> sgOBJList = new ArrayList<OBJModel>();


public float rotX;
public float rotY;
public float sc=1;
boolean bTexture = true;
boolean bStroke = false;
boolean bMaterial = true;
int userId = -1;

boolean authenticated = false;



void setup() {
  size(800, 500, OPENGL);

  // Create UI object
  inf = new Interface(this);
  inf.setup();
}

void draw() {
  background(200);
  
  if (userId>0) {
    lights();
    pushMatrix();
    translate(width/2, height/2, 0);
    rotateX(rotY);
    rotateY(rotX);
    scale(sc);


    for (OBJModel model : sgOBJList) {
      model.draw();
    }
    popMatrix();
  }

    inf.draw();
}

void updateState() {
  sgOBJList.clear();
  if (sgModelList.size() > 0) {
    Set<Entry<Integer, Model>> set = sgModelList.entrySet();
    for (Entry <Integer, Model> entry: set) {
      Model m = (Model) entry.getValue();
      OBJModel o = new OBJModel(this, m.getLocalFilePath());
      o.scale(100);
      o.translateToCenter();
      sgOBJList.add(o);
    }
  }
}

void loadProjectModels(int id) throws Exception {
  println("downloading models from Sunglass Project " + id + " ...please wait");
  sgModelList = sg.downloadAllModels(sgProjList.get(inf.selProjectId), "data/");
  println(sgModelList.size() + " model[s] downloaded.");
}

void mouseDragged()
{
  rotX += (mouseX - pmouseX) * 0.01;
  rotY -= (mouseY - pmouseY) * 0.01;
}

void keyPressed() {
  for (OBJModel model : sgOBJList) {
    // turns on and off the texture listed in .mtl file
    if (key == 't') {
      if (!bTexture) {
        model.enableTexture();
        bTexture = true;
      } 
      else {
        model.disableTexture();
        bTexture = false;
      }
    }
    else if (key == 'm') {
      // turns on and off the material listed in .mtl file
      if (!bMaterial) {
        model.enableMaterial();
        bMaterial = true;
      } 
      else {
        model.disableMaterial();
        bMaterial = false;
      }
    }
    else if (key == 's') {
      if (!bStroke) {
        stroke(10, 10, 10);
        bStroke = true;
      } 
      else {
        noStroke();
        bStroke = false;
      }
    }

    // the follwing changes the render modes
    // POINTS mode is a little flakey in OPENGL (known processing bug)
    // the best one to use is the one you exported the obj as
    // when in doubt try TRIANGLES or POLYGON
    else if (key=='1') {
      stroke(10, 10, 10);
      bStroke = true;
      model.shapeMode(POINTS);
    }

    else if (key=='2') {
      stroke(10, 10, 10);
      bStroke = true;
      model.shapeMode(LINES);
    }

    else if (key=='3') {
      model.shapeMode(TRIANGLES);
    }

    else if (key=='4') {
      model.shapeMode(POLYGON);
    }

    else if (key=='5') {
      model.shapeMode(TRIANGLE_STRIP);
    }

    else if (key=='6') {
      model.shapeMode(QUADS);
    }

    else if (key=='7') {
      model.shapeMode(QUAD_STRIP);
    }
  }
}

//CONTROLP5 EVENTS AND CALLBACKS
void controlEvent(ControlEvent theEvent) {
  int controlId = -1;
  float value = -1;

  if (theEvent.isGroup()) {
    controlId = theEvent.getGroup().getId();
    value = theEvent.getGroup().getValue();
    //println("Group " + controlId + ": Value " + value);
  } 
  else if (theEvent.isController()) {
    controlId = theEvent.getController().getId();
    value = theEvent.getController().getValue();
    //println("Controller : " + controlId + ": Value " + value);
  }

  switch (controlId) {
  case 1:
    // ProjectList
    inf.selProjectId = (int) value;
    break;

  case 2:
    //ProjectLoad
    if (inf.selProjectId != -1) {
      try {
        loadProjectModels((int) value);
      } 
      catch (Exception e) {
        e.printStackTrace();
      }
      updateState();
    }
    break;
  case 3:
    link("https://sunglass.io"); 
    break;
  case 4:
    sc *= 2;
    break;
  case 5:
    sc*= 0.5;
    break;  
  case 6:
    //popup btn ok
    println(inf.AuthBoxStringSID);
    println(inf.AuthBoxStringTOKEN);


    try { 
      sg = new SunglassConnect(this, inf.AuthBoxStringSID, inf.AuthBoxStringTOKEN);
      userId = sg.authenticate();
      if (userId > 0) {
        inf.hideAuthBox();
        sgProjList = sg.getProjectList();   // list your existing projects
        inf.addToProjList(sgProjList);      // update the interface
      }
    } 
    catch (Exception e) {
      e.printStackTrace();
    }
    break; 
  case 7:
    //popupbtn cancel
    break; 
  default:
    break;
  }
}

void buttonOK(int theValue) {
  inf.buttonOK(theValue);
}

void buttonCancel(int theValue) {
  inf.buttonCancel(theValue);
}  

void inputbox(String theString) {
  inf.inputbox(theString);
}

