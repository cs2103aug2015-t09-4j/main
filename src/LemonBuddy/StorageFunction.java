/*@@author LI YUMENG(A0133911N)*/
package LemonBuddy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class StorageFunction {

	private static final String MSG_WHEN_INVALID_FILENAME = "cannot find targeted file"; 
	private static final String MSG_WHEN_IOEXCEPTION = "cannot store information"; 

	/************************************************** Modify String 
	 * @throws Exception ******************************************************************/	
	public static Task createTaskFromString(String s) throws Exception {
		String[] array = s.split(";");
		Task t = new Task();
		for (int i = 0; i < array.length; i++) {
			String[] temp = array[i].split(":", 2);
			switch (temp[0]) {
			case "taskname":
				t.setTaskName(temp[1]);
				break;
			case "tasktype":
				t.setTaskType(temp[1]);
				break;
			case "taskIsNewest":
				if (temp[1].equals("true"))
					t.setTaskIsNewest();
				break;
			case "taskStartDate":
				t.setTaskStartDate(temp[1]);
				break;
			case "taskEndDate":
				t.setTaskEndDate(temp[1]);
				break;
			case "taskPriority":
				t.setTaskPriority(temp[1]);
				break;
			case "taskDescription":
				t.setTaskDescription(temp[1]);
				break;
			case "taskStartTime":
				t.setTaskStartTime(temp[1]);
				break;
			case "taskEndTime":
				t.setTaskEndTime(temp[1]);
				break;
			default:
				break;
			}
		}
		return t;
	}
	

/************************************************** Modify TaskList ******************************************************************/		

	
	protected static ArrayList<Task> createArrayList(File f) throws Exception {
		ArrayList<Task> result= new ArrayList<Task>();
		try {
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader read = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(read); 
			String lineText = null;
			while((lineText = br.readLine()) != null) {
        	 result.add(createTaskFromString(lineText));
        	}
			read.close();		
		} catch (FileNotFoundException e) {			
			printExceptionMessage(e);
		} finally {			
		}
		return result;
	}
	
	protected static String convertArrayListToString(ArrayList<Task> p) {
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
		}
		return s;
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
		String type;
		Task temptask = new Task();
		
		//separate allList
		for(int i = 0; i < size; i++) {
			
			temptask = taskList.get(i);
			
			type = temptask.getTaskType().toLowerCase();
			
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
				case "done":
					doneList.add(temptask);
					break;
				case "overdue":
					overdueList.add(temptask);
					break;
				default:
					break;									
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
		return t.toString();
	}

	/************************************************** Print Function ******************************************************************/
	
	public static void printExceptionMessage(IOException e) {
		System.out.println(MSG_WHEN_IOEXCEPTION);
		e.printStackTrace();
	}
	
	
	protected static void printFileInvalidMessage() {
		System.out.println(MSG_WHEN_INVALID_FILENAME);
	}
	
	/************************************************** File Funciton ******************************************************************/

	protected static boolean checkFileStatus(File f) throws IOException{
		boolean status = false;
		if(f.exists() && f.isFile()) {
			status = true;
		} else {
			printFileInvalidMessage();
		}
		return status;
	}	

	
}
