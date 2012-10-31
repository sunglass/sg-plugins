import com.sg.*;

String sid = "";
String token = "";

ConnectionManager cm = new ConnectionManager(sid, token);

cm.sunglassWrite(dataPath("test.stl"), "Simple Upload Test");
println("Finished Uploading");


