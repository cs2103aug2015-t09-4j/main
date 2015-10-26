package LemonBuddy;

import java.io.File;
import java.io.IOException;

import LemonBuddy.view.*;
import java.util.ArrayList;

public class CommandExecutor {
	 private static String path ="C:\\eclipse\\Your sdk your majesty\\main\\test.txt";
	//private static String path = "C:\\Users\\user\\workspace\\main\\test.txt";
	private Parser parser;
	public CommandExecutor(){
		if (parser == null) {
			parser = new Parser();
		}
	}

	// PRIORITY AND DESCRIPTION NOT DONE
	public void executeAdd(String[] commandParts) throws Exception {
		// assume floating first
		
		Task newTask = parser.parseTask(commandParts);
		FileStorage.write(newTask);
		int i = 1;
		PersonOverviewController.taskSelected(newTask, i);
	}

	public void executeEdit(String[] commandParts) throws Exception {
		String editType = commandParts[1];
		String editId = commandParts[2];
		int taskTypeIndex = 1;
		Task newTask = parser.parseTask(commandParts);
		System.out.println(newTask.getTaskType());
		ArrayList<Task> fullList = FileStorage.read(path);
		FileStorage.clear();

		for (int j = 0; j < fullList.size(); j++) {
			Task currentTask = fullList.get(j);
			if (currentTask.getTaskType().equals(editType)) {
				if(editId.equals(String.valueOf(taskTypeIndex))){
					newTask.merge(currentTask);
					FileStorage.write(newTask);
					int i = 3;
					PersonOverviewController.taskSelected(newTask, i);
					taskTypeIndex++;
				}
				else{
					taskTypeIndex++;
					FileStorage.write(currentTask);
				}
			} else {

				FileStorage.write(currentTask);
			}
		}
	}

	public void executeDelete(String[] commandParts) throws Exception {
		int deleteIndex = Integer.valueOf(commandParts[1]) - 1;
		ArrayList<Task> array = FileStorage.read(path);
		assert(array != null) : "unable to read from specified path";
		if (deleteIndex > array.size() - 1) {
			return;
		}
		int i = 0;
		while (i < array.size()) {
			if (i == deleteIndex) {
				FileStorage.clear();
				int x = 2;
				PersonOverviewController.taskSelected(array.get(i), x);
				array.remove(i);
				//GUIConsole.successfulDelete(commandParts[1]);
				break;
			}
			i++;
		}
		int j = 0;
		while (j < array.size()) {
			FileStorage.write(array.get(j));
			j++;
		}
	}

	public void executeRecur(String[] commandParts) {
		// TODO get startdate

		/*
		 * get particular task retrieve taskDate get recurring frequency add
		 * task to respective dates and time (our own calendar?) e.g. recur eat
		 * daily
		 */
	}

	public void executeNavigate(String[] commandParts) {
		// TODO Auto-generated method stub

		/*
		 * get what user wants to view date e.g. navigate 01012001
		 */
	}

	public void executeHelp() {
		//GUIConsole.displayHelp();
	}
	
	public void executeUpdate() throws IOException, ClassNotFoundException {
		ArrayList<Task> array = FileStorage.read(path);
		assert(array != null) : "unable to read from specified path";
		
		String currentDate = parser.getCurrentDate();
		int currentDay = parser.parseInt(currentDate.substring(0,2));
		int currentMonth = parser.parseInt(currentDate.substring(2,4));
		int currentYear = parser.parseInt(currentDate.substring(4,6));
		
		int i = 0;
		
		for (i = 0; i<array.size(); i++) {
			String endDate = array.get(i).getTaskEndDate();
			if (endDate.length() == 6) {
				int endDay = parser.parseInt(endDate.substring(0,2));
				int endMonth = parser.parseInt(endDate.substring(2,4));
				int endYear = parser.parseInt(endDate.substring(4,6));
			
				if (!(currentDate == endDate) && ((endDay <= currentDay) || (endMonth <= currentMonth) ||
						(endYear <= currentYear)) && (endDate != "")) {
					FileStorage.clear();
					array.remove(i);
					i--;
				}
			
				int j = 0;
				while (j < array.size()) {
					FileStorage.write(array.get(j));
					j++;
				}
			}
			//GUI?>>?.successfulUpdate();
		}
	}
	
	public static ArrayList<Task> list() throws Exception {
		ArrayList<Task> floatingTasks = new ArrayList<Task>();
		ArrayList<Task> deadlineTasks = new ArrayList<Task>();
		ArrayList<Task> eventTasks = new ArrayList<Task>();
		
		ArrayList<Task> fullList = new ArrayList<Task>();
		Task currentTask;

		fullList = FileStorage.read(path);
		
		//3 types of arraylist here
		for (int j = 0; j < fullList.size(); j++) {
			currentTask = fullList.get(j);
			if (currentTask.getTaskType().equals("floating")) {
				floatingTasks.add(currentTask);
			}
			if (currentTask.getTaskType().equals("deadline")) {
				deadlineTasks.add(currentTask);
			}
			if (currentTask.getTaskType().equals("event")) {
				eventTasks.add(currentTask);
			}
		}
		
		//GUIConsole.displayFloatingTask();
		for (int j = 0; j < floatingTasks.size(); j++) {
			currentTask = floatingTasks.get(j);
			int taskIndex = j + 1;
			//int x = 4;
		}
		return fullList;
	}

	public static void display(String[] commandParts) throws Exception {
		ArrayList<Task> fullList = new ArrayList<Task>();
		Task currentTask;
		
		int id = Integer.parseInt(commandParts[1]);
		fullList = FileStorage.read(path);
		int x = 4;
		currentTask = fullList.get(id - 1);
		PersonOverviewController.taskSelected(currentTask, x);
	}

	public void parseInvalidCommand(String command) {
		//GUIConsole.displayErrorMessage(command);
	}

	public String[] splitCommand(String command) {

		String[] commandParts = command.split(" ");
		return commandParts;
	}

	public void executeUndo() {
		// TODO Auto-generated method stub
		
	}
}
