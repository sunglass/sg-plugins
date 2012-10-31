package run;

import com.sg.*;
import com.sg.utils.*;



import processing.core.PApplet;

public class Applet extends PApplet {

	public boolean record = false;
	
	public void setup() {
		  size(400, 400,OPENGL);
		}

	public	void draw() {
		  if (record) {
		    beginRecord("com.sg.utils.OBJExport", "filename.obj"); 
		  }  
		  box(100,100,100);
		
		 
		  
		  if (record) {
		    endRecord();
		    record = false;
		  }
		}

	public	void mousePressed() {
		  record = true;
		}
	
	
	
}
