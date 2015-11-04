package LemonBuddy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileStorage extends Parser {
//	private static final long serialVersionUID = -769626947865283;
	private static ArrayList<Task> objectList = new ArrayList<Task>();
	private static final String MSG_WHEN_INVALID_FILENAME = "cannot find targeted file"; 
	private static String filename = "default.txt";
	
	protected static void clear() throws IOException {
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
	
	/*
	protected static void writeObjectAsString(Task p) throws IOException{
    	File f = new File(filename);
    	FileWriter fw = new FileWriter(f); 
    	Sort mysort = new Sort();
    	try {
    		objectList.add(p);
    		Collections.sort(objectList, mysort);
    		for(int i = 0; i < objectList.size(); i++) {
    			String content = objectList.get(i).toString();
    			fw.write(content);
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	} finally {
    		fw.flush();
    	} 
    }
    */

	
	///*
	protected static void writeObjectAsString(Task p) throws IOException{
    	File f = new File(filename);
    	FileWriter fw = new FileWriter(f,true);   	
		String content = p.toString();
    	try {
    		fw.write(content);
    	} catch (IOException e) {
    		e.printStackTrace();
    	} finally {
    		fw.flush();
    	}
    }
   // */
    
	protected static void writeStringAsString(String s) throws IOException{
    	File f = new File(filename);
    	FileWriter fw = new FileWriter(f,true);
    	try {
    		fw.write(s);
    	} catch (IOException e) {
    		e.printStackTrace();
    	} finally {
    		fw.flush();
    	}  
    	fw.flush();
    }
	
	/*
	protected static ArrayList<Task> readStringAsObject(String path) throws IOException, ClassNotFoundException {
		return objectList;       
    }	
	*/


	protected static ArrayList<Task> readStringAsObject(String path) throws IOException, ClassNotFoundException {
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

     
	protected static String readStringAsString(String path) throws IOException, ClassNotFoundException {
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
        		 filecontent = filecontent + lineText + System.getProperty("line.separator");
        	 }
        	 read.close();
        } else {
        	System.out.println(MSG_WHEN_INVALID_FILENAME);
        }
		return filecontent;       
     }

	protected static void RetrieveFile(String path) throws IOException {
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
    	

