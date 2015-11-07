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
    //private static final long serialVersionUID = -769626947865283;
	private static ArrayList<Task> objectList = new ArrayList<Task>();
	private static final String MSG_WHEN_INVALID_FILENAME = "cannot find targeted file"; 
	private static final String MSG_WHEN_IOEXCEPTION = "cannot store information"; 
	
	private static String filename = "all.txt";
	
	/*
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
	*/

	
	private static String convertArrayListToString(ArrayList<Task> p) {	

		int size = p.size();
		Task temptask = new Task();
		String content = "";
		String tempContent;
		
		for(int i = 0; i < size; i++) {
			temptask = p.get(i);
			tempContent = TaskToString(temptask);
			content = content + tempContent;
		}		
		return content;
	}
	
	protected static ArrayList<ArrayList<Task>> separateTaskList(ArrayList<Task> taskList) {
		
		ArrayList<ArrayList<Task>> separateList = new  ArrayList<ArrayList<Task>>();
		ArrayList<Task> floatingList = new ArrayList<Task>();
		ArrayList<Task> deadlineList = new ArrayList<Task>();
		ArrayList<Task> eventList = new ArrayList<Task>();
		ArrayList<Task> allList = taskList;
		ArrayList<Task> doneList = new ArrayList<Task>();
		ArrayList<Task> overdueList = new ArrayList<Task>();
		
		int size = allList.size();
		boolean isDone;
		boolean isOverdue;
		String type;
		Task temptask = new Task();
		
		//separate allList
		for(int i = 0; i < size; i++) {
			
			temptask = taskList.get(i);
			
			type = temptask.getTaskType();
			isDone = temptask.getTaskIsDone();
			isOverdue = temptask.getTaskIsOverdue();
			
			switch(type){
				case "floating":
					floatingList.add(temptask);
					break;
				case "deadline":
					deadlineList.add(temptask);
					break;
				case "event":
					eventList.add(temptask);
					break;
				default:
					floatingList.add(temptask);
					break;									
			}
			
			if(isDone) {
				doneList.add(temptask);
			} else {
				
			}
			
			if(isOverdue) {
				overdueList.add(temptask);
			} else {
				
			}
		}	
		
		separateList.add(floatingList);
		separateList.add(deadlineList);
		separateList.add(eventList);
		separateList.add(allList);
		separateList.add(doneList);
		separateList.add(overdueList);
		
		return separateList;
	}

	
	private static String TaskToString(Task t){
		return t.toString();
	}
	
	private static void printExceptionMessage(IOException e) {
		System.out.println(MSG_WHEN_IOEXCEPTION);
		e.printStackTrace();
	}
	
	private static void printFileInvalidMessage() {
		System.out.println("MSG_WHEN_INVALID_FILENAME");
	}
	
	public static void writeObjectAsString(ArrayList<Task> taskList) throws IOException{
    	FileWriter fw = null;
		fw = openFileForWrite();  
    	String content = convertArrayListToString(taskList);
    	try {
			writeAndClose(fw,content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			printExceptionMessage(e);
		}
    }	
	
	private static FileWriter openFileForWrite(){
		File f = new File(filename);
		FileWriter fw = null;
		try {
			fw = new FileWriter(f, false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			printExceptionMessage(e);
		}
		return fw;		
	}

	private static void writeAndClose(FileWriter fw, String s) throws IOException{
		try {
			fw.write(s);
		} catch (IOException e) {
    		printExceptionMessage(e);
    	} finally {
    		fw.flush();
    	}
	}
    
	public static void writeStringAsString(String s) throws IOException{
    	FileWriter fw = openFileForWrite();  
    	try {
			writeAndClose(fw,s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			printExceptionMessage(e);
		} 
    }
	
	private static boolean checkFileStatus(File f) throws IOException{
		boolean status = false;
		if(f.exists() && f.isFile()) {
			status = true;
		} else {
			printFileInvalidMessage();
		}
		return status;
	}	
	
	//private static BufferedReader create
	

	public static ArrayList<ArrayList<Task>> readStringAsObject(String path) throws IOException, ClassNotFoundException {
		//floating deadline event all done overdue
		ArrayList<ArrayList<Task>> newList = new ArrayList<ArrayList<Task>>();
		try {
			objectList = new ArrayList<Task>();
			File f = new File(filename);
			assert(checkFileStatus(f));
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader read = new InputStreamReader(fis);
			 BufferedReader br = new BufferedReader(read); 
        	 String lineText = null;
        	 while((lineText = br.readLine()) != null) {
        		 objectList.add(createTaskFromInformation(lineText));
        	 }
        	 read.close();
        	 newList = separateTaskList(objectList);
		} catch (IOException e) {
    		e.printStackTrace();
    	} finally {
    		
    	}
		return newList;
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
        		 filecontent = filecontent + lineText + System.getProperty("line.separator");
        	 }
        	 read.close();
        } else {
        	System.out.println(MSG_WHEN_INVALID_FILENAME);
        }
		return filecontent;       
     }

}
    	

