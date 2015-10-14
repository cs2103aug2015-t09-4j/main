import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class FileStorage extends StorageParser {
//	private static final long serialVersionUID = -769626947865283;
	static ArrayList<Task> objectList = new ArrayList<Task>();
	private static final String MSG_WHEN_INVALID_FILENAME = "cannot find targeted file"; 
	
	public static void clear(File f) throws Exception {
		OutputStream out = new FileOutputStream(f, false);
		MyObjectOutputStream oos = MyObjectOutputStream.newInstance(f, out);
		oos.close();
		out.flush();
	}
 
    public static void write(String path, Object p) throws IOException {  
    	File f = new File(path);
    	FileWriter fw = new FileWriter(f,true);
    	String content = p.toString();
    	fw.write(content);
    	fw.flush();
    }

     public static ArrayList<Task> read(String path) throws IOException, ClassNotFoundException {
    	 objectList = new ArrayList<Task>();
         File f = new File(path);
         if(f.isFile() && f.exists()) {
        	 FileInputStream fis = new FileInputStream(f);
        	 InputStreamReader read = new InputStreamReader(fis);
        	 BufferedReader br = new BufferedReader(read);
        	 String lineText = null;
        	 while((lineText = br.readLine()) != null) {
        		 objectList.add(createTaskFromInformation(lineText));
        	 }
        	 read.close();
         } else {
        	 System.out.println(MSG_WHEN_INVALID_FILENAME);
         }
		return objectList;       
     }
}

