import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileStorage extends StorageParser {
//	private static final long serialVersionUID = -769626947865283;
	static ArrayList<Task> objectList = new ArrayList<Task>();
	private static final String MSG_WHEN_INVALID_FILENAME = "cannot find targeted file"; 
	private static Logger storageLog = Logger.getLogger("FileStorage");
	
	public static void clear(String path) throws IOException {
		File f = new File(path);	
		assert f.exists() == true;
		FileWriter fw = new FileWriter(f);
		try{
			fw.write("");			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			fw.close();			
		}
	}
 
    public static void write(String path, Object p) throws IOException{  
    	storageLog.log(Level.ALL, "goint to start writing in an existing file");
    	File f = new File(path);
    	FileWriter fw = new FileWriter(f,true);
    	try {
    		String content = p.toString();
    		fw.write(content);
    	} catch (IOException e) {
    		storageLog.log(Level.WARNING, "writing error", e);
    	} finally {
    		fw.flush();
    		storageLog.log(Level.INFO, "end of writing");
    	}  
    	String content = p.toString();
    	fw.write(content);
    	fw.flush();
    }

     public static ArrayList<Task> read(String path) throws IOException, ClassNotFoundException {
    	 objectList = new ArrayList<Task>();
         File f = new File(path);
         //if(f.isFile() && f.exists()) { 
         	 assert f.isFile() == true;
         	 assert f.exists() == true;
        	 FileInputStream fis = new FileInputStream(f);
        	 InputStreamReader read = new InputStreamReader(fis);
        	 BufferedReader br = new BufferedReader(read);
        	 String lineText = null;
        	 while((lineText = br.readLine()) != null) {
        		 objectList.add(createTaskFromInformation(lineText));
        	 }
        	 read.close();
        // } else {
        	// System.out.println(MSG_WHEN_INVALID_FILENAME);
        // }
		return objectList;       
     }
}

