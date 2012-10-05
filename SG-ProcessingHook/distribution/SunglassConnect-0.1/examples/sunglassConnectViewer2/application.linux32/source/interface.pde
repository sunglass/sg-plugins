import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.sg.models.Project;

import controlP5.Button;
import controlP5.ControlEvent;
import controlP5.ControlP5;
import controlP5.DropdownList;

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

  void setup() {
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

  void draw() {
     //cam.beginHUD();
     cp5.draw();
     //cam.endHUD();
  }

  // PROJECT LIST
  void customize(DropdownList ddl) {
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

 void addToProjList(HashMap<Integer, Project> projList) {
    Iterator<Entry<Integer, Project>> i = projList.entrySet().iterator();
    while (i.hasNext ()) {
      Map.Entry entry = (Map.Entry) i.next();
      System.out.print("[Project ID:" + entry.getKey() + ", ");
      System.out.print("Project Name: " + ((Project) entry.getValue()).getName() + ", ");
      System.out.println("RootSpace ID:" + ((Project) entry.getValue()).getRootSpaceId() + "]");
      listProjects.addItem("[" + entry.getKey() + "] "+ ((Project) entry.getValue()).getName(), Integer.parseInt(entry.getKey().toString()));
    }
  }
  
  void createAuthBox() {
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

void hideAuthBox() {
  AuthBox.hide();
}

//the OK button of the messageBox.
void buttonOK(int theValue) {
  println("a button event from button OK.");
  AuthBoxStringSID = ((Textfield)cp5.controller("SID")).getText();
  AuthBoxStringTOKEN = ((Textfield)cp5.controller("TOKEN")).getText();
  AuthBoxResult = theValue;
}


// function buttonCancel will be triggered when pressing
// the Cancel button of the messageBox.
void buttonCancel(int theValue) {
  println("a button event from button Cancel.");
  AuthBoxResult = theValue;
}

// inputbox is called whenever RETURN has been pressed 
// in textfield-controller inputbox 
void inputbox(String theString) {
  println("got something from the authbox : "+theString);
  //AuthBoxString = theString;
}

}

