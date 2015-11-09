package LemonBuddy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileStorage {
    private static String EMPTY_CONTENT = "";
	public static String filename = "default.txt";

	/************************************************** Write To Storage ******************************************************************/	
	public static void writeObjectAsString(ArrayList<Task> taskList) throws IOException{
		File f = new File(filename);
		FileWriter fw = new FileWriter(f, false);
    	String content = StorageFunction.convertArrayListToString(taskList);
    	try {
    		fw.write(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			StorageFunction.printExceptionMessage(e);
		} finally {
			fw.flush();
		}
    }	
   
	public static void writeStringAsString(String s) throws IOException{
		File f = new File(filename);
		FileWriter fw = new FileWriter(f, false);
    	try {
    		fw.write(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			StorageFunction.printExceptionMessage(e);
		} finally {
			fw.flush();
		}
    }
	
	/**************************************************** Clear Storage ********************************************************************/
	
	public static void clear() throws IOException {
		File f = new File(filename);	
		assert(StorageFunction.checkFileStatus(f));
		FileWriter fw = new FileWriter(f);
		try {
    		fw.write(EMPTY_CONTENT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			StorageFunction.printExceptionMessage(e);
		} finally {
			fw.flush();
		}
	}
	

	/************************************************** Read From Storage ******************************************************************/
	public static ArrayList<ArrayList<Task>> readStringAsObject(String path) throws IOException, ClassNotFoundException {
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
    	

