import processing.core.*; 
import processing.xml.*; 

import java.util.ArrayList; 
import java.util.HashMap; 
import java.util.Map.Entry; 
import java.util.Set; 
import processing.opengl.*; 
import saito.objloader.*; 
import controlP5.*; 
import com.sg.*; 
import com.sg.models.*; 
import java.util.HashMap; 
import java.util.Iterator; 
import java.util.Map; 
import java.util.Map.Entry; 
import com.sg.models.Project; 
import controlP5.Button; 
import controlP5.ControlEvent; 
import controlP5.ControlP5; 
import controlP5.DropdownList; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class sunglassProtoV4 extends PApplet {













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



public void setup() {
  size(800, 500, OPENGL);

  // Create UI object
  inf = new Interface(this);
  inf.setup();
}

public void draw() {
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

public void updateState() {
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

public void loadProjectModels(int id) throws Exception {
  println("downloading models from Sunglass Project " + id + " ...please wait");
  sgModelList = sg.downloadAllModels(sgProjList.get(inf.selProjectId), "data/");
  println(sgModelList.size() + " model[s] downloaded.");
}

public void mouseDragged()
{
  rotX += (mouseX - pmouseX) * 0.01f;
  rotY -= (mouseY - pmouseY) * 0.01f;
}

public void keyPressed() {
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
public void controlEvent(ControlEvent theEvent) {
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
    sc*= 0.5f;
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

public void buttonOK(int theValue) {
  inf.buttonOK(theValue);
}

public void buttonCancel(int theValue) {
  inf.buttonCancel(theValue);
}  

public void inputbox(String theString) {
  inf.inputbox(theString);
}













class Interface {

  PApplet app;
  ControlP5 cp5;
  
  // cp5
  DropdownList listProjects;
  Button btnSunglass;
  Button  btnLoadProject, 
          btnCreateProject, 
          btnEditProject, 
          btnSaveProject, 
          btnDeleteProject;
  Button  btnCameraZoomIn,
          btnCameraZoomOut;
          
  ControlGroup AuthBox;
  Textfield f, f2;
  int AuthBoxResult = -1;
  String AuthBoxStringSID = "";
  String AuthBoxStringTOKEN = "";

          
  // sunglass
  int selProjectId = -1;

  // color
  int colBk;
  int c1, c2;
  float n = 0;

  Interface(PApplet parent) {
    this.app = parent;
  }

  public void setup() {
    cp5 = new ControlP5(app);
    cp5.setAutoDraw(false);


    //project list
    listProjects = cp5.addDropdownList("ProjectList").setPosition(47, 16);
    listProjects.setId(1);
    customize(listProjects);

    //project load btn
    btnLoadProject = cp5.addButton("load").setValue(0).setPosition(198, 0).setSize(30, 15);
    btnLoadProject.setId(2);
    
    //sunglass btn
    btnSunglass = cp5.addButton("Sunglass").setValue(0).setPosition(0, 0).setSize(46, 15);
    btnSunglass.setColorBackground(color(255,222,89)).setColorCaptionLabel(0); 
    btnSunglass.setId(3);
    
    //camera + - 
    btnCameraZoomIn = cp5.addButton("Zoom+").setValue(0).setPosition(765, 100).setSize(35, 35).setColorBackground(color(255,222,89)).setColorCaptionLabel(0);
    btnCameraZoomIn.setId(4);
    btnCameraZoomOut = cp5.addButton("Zoom-").setValue(0).setPosition(765, 136).setSize(35, 35).setColorBackground(color(255,222,89)).setColorCaptionLabel(0);
    btnCameraZoomOut.setId(5);
    
    //box
    createAuthBox();
    f.setText("ACnrfkye0w+KEgkiOeZcDpD+LO2Wc=");
    f2.setText("wfnZ/t7fBLaguRnmGPk65jG/34M=");
    
  }

  public void draw() {
     //cam.beginHUD();
     cp5.draw();
     //cam.endHUD();
  }

  // PROJECT LIST
  public void customize(DropdownList ddl) {
      ddl.setBackgroundColor(color(0));
      ddl.setItemHeight(15);
      ddl.setBarHeight(15);
      ddl.captionLabel().set("ProjectList");
      ddl.captionLabel().style().marginTop = 3;
      ddl.captionLabel().style().marginLeft = 3;
      ddl.valueLabel().style().marginTop = 3;
      ddl.scroll(0);
      ddl.setWidth(150);
      ddl.setHeight(400);
      
    }

 public void addToProjList(HashMap<Integer, Project> projList) {
    Iterator<Entry<Integer, Project>> i = projList.entrySet().iterator();
    while (i.hasNext ()) {
      Map.Entry entry = (Map.Entry) i.next();
      System.out.print("[Project ID:" + entry.getKey() + ", ");
      System.out.print("Project Name: " + ((Project) entry.getValue()).getName() + ", ");
      System.out.println("RootSpace ID:" + ((Project) entry.getValue()).getRootSpaceId() + "]");
      listProjects.addItem("[" + entry.getKey() + "] "+ ((Project) entry.getValue()).getName(), Integer.parseInt(entry.getKey().toString()));
    }
  }
  
  public void createAuthBox() {
  // create a group to store the messageBox elements
  AuthBox = cp5.addGroup("AuthBox",width/2 - 150,150,240);
  AuthBox.setBackgroundHeight(150);
  AuthBox.setBackgroundColor(color(0,100));
  AuthBox.hideBar();
  
  // add a TextLabel to the messageBox.
  Textlabel l = cp5.addTextlabel("authBoxLabel","Enter your Sunglass SID/TOKEN",15,10);
  l.moveTo(AuthBox);
  
  // add a textfield-controller with named-id inputbox
  // this controller will be linked to function inputbox() below.
  f = cp5.addTextfield("SID",20,26,200,20);
  f.captionLabel().setVisible(true);
  f.moveTo(AuthBox);
  f.setColorForeground(color(20));
  f.setColorBackground(color(20));
  f.setColorActive(color(100));
  
  f2 = cp5.addTextfield("TOKEN",20,67,200,20);
  f2.captionLabel().setVisible(true);
  f2.moveTo(AuthBox);
  f2.setColorForeground(color(20));
  f2.setColorBackground(color(20));
  f2.setColorActive(color(100));
  f2.setValueLabel("Enter token");
  f2.valueLabel().setVisible(true);
  
  
  // add the OK button to the messageBox.
  // the name of the button corresponds to function buttonOK
  // below and will be triggered when pressing the button.
  Button b1 = cp5.addButton("buttonOK",0,141,100,80,34);
  b1.moveTo(AuthBox);
  b1.setColorBackground(color(40));
  b1.setColorActive(color(20));
  // by default setValue would trigger function buttonOK, 
  // therefore we disable the broadcasting before setting
  // the value and enable broadcasting again afterwards.
  // same applies to the cancel button below.
  b1.setBroadcast(false); 
  b1.setValue(1);
  b1.setBroadcast(true);
  b1.setCaptionLabel("   Submit");
  b1.setId(6);
  // centering of a label needs to be done manually 
  // with marginTop and marginLeft
  //b1.captionLabel().style().marginTop = -2;
  //b1.captionLabel().style().marginLeft = 26;
  
  // add the Cancel button to the messageBox. 
  // the name of the button corresponds to function buttonCancel
  // below and will be triggered when pressing the button.
//  Button b2 = cp5.addButton("buttonCancel",0,155,80,80,24);
//  b2.moveTo(AuthBox);
//  b2.setBroadcast(false);
//  b2.setValue(0);
//  b2.setBroadcast(true);
//  b2.setCaptionLabel("Dont have SID");
//  b2.setColorBackground(color(40));
//  b2.setColorActive(color(20));
//  b2.setId(7);
  //b2.captionLabel().toUpperCase(false);
  // centering of a label needs to be done manually 
  // with marginTop and marginLeft
  //b2.captionLabel().style().marginTop = -2;
  //b2.captionLabel().style().marginLeft = 16;
}

public void hideAuthBox() {
  AuthBox.hide();
}

//the OK button of the messageBox.
public void buttonOK(int theValue) {
  println("a button event from button OK.");
  AuthBoxStringSID = ((Textfield)cp5.controller("SID")).getText();
  AuthBoxStringTOKEN = ((Textfield)cp5.controller("TOKEN")).getText();
  AuthBoxResult = theValue;
}


// function buttonCancel will be triggered when pressing
// the Cancel button of the messageBox.
public void buttonCancel(int theValue) {
  println("a button event from button Cancel.");
  AuthBoxResult = theValue;
}

// inputbox is called whenever RETURN has been pressed 
// in textfield-controller inputbox 
public void inputbox(String theString) {
  println("got something from the authbox : "+theString);
  //AuthBoxString = theString;
}

}

  static public void main(String args[]) {
    PApplet.main(new String[] { "--present", "--bgcolor=#666666", "--stop-color=#cccccc", "sunglassProtoV4" });
  }
}
