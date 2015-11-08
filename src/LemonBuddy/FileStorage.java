package LemonBuddy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileStorage extends Parser {
    private static String EMPTY_CONTENT = "";
	public static String filename = "default.txt";
	
	public static void clear() throws IOException {
		File f = new File(filename);	
		assert(StorageFunction.checkFileStatus(f));
		FileWriter fw = new FileWriter(f);
		StorageFunction.writeAndClose(fw, EMPTY_CONTENT);
	}
	
	public void writeObjectAsString(ArrayList<Task> taskList) throws IOException{
    	FileWriter fw = null;
		fw = StorageFunction.openFileForWrite(filename);  
    	String content = StorageFunction.convertArrayListToString(taskList);
    	try {
    		StorageFunction.writeAndClose(fw,content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			StorageFunction.printExceptionMessage(e);
		}
    }	
    
	public static void writeStringAsString(String s) throws IOException{
    	FileWriter fw = StorageFunction.openFileForWrite(filename);  
    	try {
    		StorageFunction.writeAndClose(fw,s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			StorageFunction.printExceptionMessage(e);
		} 
    }
	

	public ArrayList<ArrayList<Task>> readStringAsObject(String path) throws IOException, ClassNotFoundException {
		//floating deadline event all done overdue
		ArrayList<ArrayList<Task>> newList = new ArrayList<ArrayList<Task>>();
		try {
			ArrayList<Task> tempObjectList = new ArrayList<Task>();
			File f = new File(filename);
			assert(StorageFunction.checkFileStatus(f));
			tempObjectList = StorageFunction.createArrayList(f);	
			newList = StorageFunction.separateTaskList(tempObjectList);
		} catch (IOException e) {
			StorageFunction.printFileInvalidMessage();
			StorageFunction.printExceptionMessage(e);
    	} finally {
    		
    	}		
		return newList;
	}

	public static String readStringAsString(String path) throws IOException, ClassNotFoundException {
    	 String filecontent = "";
         File f = new File(filename);
         if(StorageFunction.checkFileStatus(f)){
        	 filecontent = StorageFunction.createString(f,filecontent);
         } else {
        	 StorageFunction.printFileInvalidMessage();
         }
		 return filecontent;       
     }

}
    	

