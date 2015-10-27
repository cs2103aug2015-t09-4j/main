package LemonBuddy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileStorage extends Parser{
//	private static final long serialVersionUID = -769626947865283;
	static ArrayList<Task> objectList = new ArrayList<Task>();
	private static final String MSG_WHEN_INVALID_FILENAME = "cannot find targeted file"; 
	private static Logger storageLog = Logger.getLogger("FileStorage");
	private static String filename = "default.txt";
	
	public static void clear() throws IOException {
		File f = new File(filename);	
		assert f.exists() == true;
		assert f.isFile() == true;
		FileWriter fw = new FileWriter(f);
		try{
			fw.write("");			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			fw.close();			
		}
	}
 
    public static void writeObjectAsString(Object p) throws IOException{
    	File f = new File(filename);
    	FileWriter fw = new FileWriter(f,true);
		String content = p.toString();
    	try {
    		fw.write(content);
    	} catch (IOException e) {
    		storageLog.log(Level.WARNING, "writing error", e);
    	} finally {
    		fw.flush();
    		storageLog.log(Level.INFO, "end of writing");
    	}  
    	fw.flush();
    }
    
    public static void writeStringAsString(String s) throws IOException{
    	File f = new File(filename);
    	FileWriter fw = new FileWriter(f,true);
    	try {
    		fw.write(s);
    	} catch (IOException e) {
    		storageLog.log(Level.WARNING, "writing error", e);
    	} finally {
    		fw.flush();
    		storageLog.log(Level.INFO, "end of writing");
    	}  
    	fw.flush();
    }

     public static ArrayList<Task> readStringAsObject(String path) throws IOException, ClassNotFoundException {
    	 objectList = new ArrayList<Task>();
         File f = new File(filename);
         if(f.isFile() && f.exists()) { 
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
        } else {
        	System.out.println(MSG_WHEN_INVALID_FILENAME);
        }
		return objectList;       
     }
     
     public static String readStringAsString(String path) throws IOException, ClassNotFoundException {
    	 String filecontent = "";
         File f = new File(filename);
         if(f.isFile() && f.exists()) { 
         	 assert f.isFile() == true;
         	 assert f.exists() == true;
        	 FileInputStream fis = new FileInputStream(f);
        	 InputStreamReader read = new InputStreamReader(fis);
        	 BufferedReader br = new BufferedReader(read);
        	 String lineText = null;
        	 while((lineText = br.readLine()) != null) {
        		 filecontent = filecontent + lineText;
        	 }
        	 read.close();
        } else {
        	System.out.println(MSG_WHEN_INVALID_FILENAME);
        }
		return filecontent;       
     }

     public static void RetrieveFile(String path) throws IOException {
		 FileInputStream input=new FileInputStream(filename);
		 FileOutputStream output=new FileOutputStream(path);   	 
    	 try {
    		 int in = input.read();
    		 while(in != -1) {
    			 output.write(in);
    			 in = input.read();
    		 }
    	 } catch (IOException e) {
    		 System.out.println(e.toString());
    	 } finally {
    		 input.close();
    		 output.close();
    	 }
     }
}
    	

