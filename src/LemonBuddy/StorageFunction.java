package LemonBuddy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StorageFunction extends FileStorage {
	private static final String MSG_WHEN_INVALID_FILENAME = "cannot find targeted file"; 
	private static final String MSG_WHEN_IOEXCEPTION = "cannot store information"; 

	private static Logger storageLogger;	
	
	protected static String convertArrayListToString(ArrayList<Task> p) {
		storageLogger.log(Level.INFO, "Going to convert arraylist to string");
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

	
	protected static String createString(File f,String s) throws IOException {
		storageLogger.log(Level.INFO, "convert storage to string");
		try {
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader read = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(read); 
			String lineText = null;
			while((lineText = br.readLine()) != null) {
				s = s + lineText + System.getProperty("line.separator");
			}
			read.close();		
		} catch (FileNotFoundException e) {
			printExceptionMessage(e);
		} finally {
			return s;
		}
	}

	
	protected static ArrayList<ArrayList<Task>> separateTaskList(ArrayList<Task> taskList) {
		storageLogger.log(Level.INFO, "Going to separate arraylist of task into arraylist of arraylist of task");
		
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
	
	protected static String TaskToString(Task t){
		storageLogger.log(Level.INFO, "Going to convert task object to string");
		return t.toString();
	}
	
	protected static void printExceptionMessage(IOException e) {
		storageLogger.log(Level.INFO, "throw IOException");
		System.out.println(MSG_WHEN_IOEXCEPTION);
		e.printStackTrace();
	}
	
	
	protected static void printFileInvalidMessage() {
		storageLogger.log(Level.INFO, "Print invalid file message");
		System.out.println(MSG_WHEN_INVALID_FILENAME);
	}
	
	protected static FileWriter openFileForWrite(String filename){
		storageLogger.log(Level.INFO, "Going to open file use for writing");
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

	protected static void writeAndClose(FileWriter fw, String s) throws IOException{
		storageLogger.log(Level.INFO, "Going to write string to the file and then close");
		try {
			fw.write(s);
		} catch (IOException e) {
    		printExceptionMessage(e);
    	} finally {
    		fw.flush();
    	}
	}

	protected static boolean checkFileStatus(File f) throws IOException{
		storageLogger.log(Level.INFO, "check file status to see whether can execute");
		boolean status = false;
		if(f.exists() && f.isFile()) {
			status = true;
		} else {
			printFileInvalidMessage();
		}
		return status;
	}	
	
	protected static ArrayList<Task> createArrayList(File f) throws IOException {
		storageLogger.log(Level.INFO, "Recreate arraylist of task from storage");
		ArrayList<Task> result= new ArrayList<Task>();
		try {
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader read = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(read); 
			String lineText = null;
			while((lineText = br.readLine()) != null) {
        	 result.add(createTaskFromInformation(lineText));
        	}
			read.close();		
		} catch (FileNotFoundException e) {
			printExceptionMessage(e);
		} finally {
			return result;
		}
	}
	
}