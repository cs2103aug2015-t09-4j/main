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

public class FileStorage extends Parser {
    //private static final long serialVersionUID = -769626947865283;
	private ArrayList<Task> objectList = new ArrayList<Task>();
	private static final String MSG_WHEN_INVALID_FILENAME = "cannot find targeted file"; 
	private static final String MSG_WHEN_IOEXCEPTION = "cannot store information"; 
	private Logger storageLogger;	
	
	private String filename = "all.txt";
	
	public void clear() throws IOException {
		storageLogger.log(Level.INFO, "Going to clear existing file");
		File f = new File(filename);	
		assert(checkFileStatus(f));
		FileWriter fw = new FileWriter(f);
		String content = "";
		writeAndClose(fw, content);
	}
	
	private String convertArrayListToString(ArrayList<Task> p) {
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
	
	private ArrayList<ArrayList<Task>> separateTaskList(ArrayList<Task> taskList) {
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

	
	private String TaskToString(Task t){
		storageLogger.log(Level.INFO, "Going to convert task object to string");
		return t.toString();
	}
	
	private void printExceptionMessage(IOException e) {
		storageLogger.log(Level.INFO, "throw IOException");
		System.out.println(MSG_WHEN_IOEXCEPTION);
		e.printStackTrace();
	}
	
	private void printFileInvalidMessage() {
		storageLogger.log(Level.INFO, "Print invalid file message");
		System.out.println("MSG_WHEN_INVALID_FILENAME");
	}
	
	public void writeObjectAsString(ArrayList<Task> taskList) throws IOException{
		storageLogger.log(Level.INFO, "Going to write all task object to string");
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
	
	private FileWriter openFileForWrite(){
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

	private void writeAndClose(FileWriter fw, String s) throws IOException{
		storageLogger.log(Level.INFO, "Going to write string to the file and then close");
		try {
			fw.write(s);
		} catch (IOException e) {
    		printExceptionMessage(e);
    	} finally {
    		fw.flush();
    	}
	}
    
	public void writeStringAsString(String s) throws IOException{
		storageLogger.log(Level.INFO, "Going to write string object to a file");
    	FileWriter fw = openFileForWrite();  
    	try {
			writeAndClose(fw,s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			printExceptionMessage(e);
		} 
    }
	
	private boolean checkFileStatus(File f) throws IOException{
		storageLogger.log(Level.INFO, "check file status to see whether can execute");
		boolean status = false;
		if(f.exists() && f.isFile()) {
			status = true;
		} else {
			printFileInvalidMessage();
		}
		return status;
	}	
	
	private ArrayList<Task> createArrayList(File f, ArrayList<Task> a) throws IOException {
		storageLogger.log(Level.INFO, "Recreate arraylist of task from storage");
		try {
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader read = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(read); 
			String lineText = null;
			while((lineText = br.readLine()) != null) {
        	 a.add(createTaskFromInformation(lineText));
        	}
			read.close();		
		} catch (FileNotFoundException e) {
			printExceptionMessage(e);
		} finally {
			return a;
		}
	}
	

	public ArrayList<ArrayList<Task>> readStringAsObject(String path) throws IOException, ClassNotFoundException {
		storageLogger.log(Level.INFO, "convert string to object");
		//floating deadline event all done overdue
		ArrayList<ArrayList<Task>> newList = new ArrayList<ArrayList<Task>>();
		try {
			ArrayList<Task> tempObjectList = new ArrayList<Task>();
			File f = new File(filename);
			assert(checkFileStatus(f));
			tempObjectList = createArrayList(f, tempObjectList);	
			newList = separateTaskList(tempObjectList);
		} catch (IOException e) {
			printFileInvalidMessage();
			printExceptionMessage(e);
    	} finally {
    		return newList;
    	}		
	}

	
	private String createString(File f,String s) throws IOException {
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

	public String readStringAsString(String path) throws IOException, ClassNotFoundException {
		 storageLogger.log(Level.INFO, "read string from storage");
    	 String filecontent = "";
         File f = new File(filename);
         if(checkFileStatus(f)){
        	 filecontent = createString(f,filecontent);
         } else {
			printFileInvalidMessage();
         }
		 return filecontent;       
     }

}
    	

