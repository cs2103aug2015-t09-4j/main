package LemonBuddy;

import java.io.File;
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
		LemonGUIController.setTask(newTask);
		LemonGUIController.setCommand(commandParts[0]);
	}

	public void executeEdit(String[] commandParts) throws Exception {
		String editType = commandParts[1];
		String editId = commandParts[2];
		LemonGUIController.setCommand(commandParts[0]);
		int taskTypeIndex = 1;
		Task newTask = parser.parseTask(commandParts);
		//System.out.println(newTask.getTaskType());
		ArrayList<Task> fullList = FileStorage.read(path);
		FileStorage.clear();

		for (int j = 0; j < fullList.size(); j++) {
			Task currentTask = fullList.get(j);
			if (currentTask.getTaskType().equals(editType)) {
				if(editId.equals(String.valueOf(taskTypeIndex))){
					newTask.merge(currentTask);
					FileStorage.write(newTask);
					LemonGUIController.setTask(newTask);
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
		LemonGUIController.setCommand(commandParts[0]);
		ArrayList<Task> array = FileStorage.read(path);
		assert(array != null) : "unable to read from specified path";
		if (deleteIndex > array.size() - 1 || deleteIndex == -1) {
			return;
		}
		int i = 0;
		while (i < array.size()) {
			if (i == deleteIndex) {
				FileStorage.clear();
				LemonGUIController.setTask(array.get(i));
				array.remove(i);
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
	
	public static void executeList(String[] commandParts) throws Exception {
		String listType = commandParts[1];
		LemonGUIController.setCommand(commandParts[0]);
		LemonGUIController.setListType(listType);
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
		
		if(listType.equals("floating")) {
			LemonGUIController.setList(floatingTasks);
		} else if(listType.equals("deadline")) {
			LemonGUIController.setList(deadlineTasks);
		} else if(listType.equals("event")) {
			LemonGUIController.setList(eventTasks);
		} else if(listType.equals("all")) {
			LemonGUIController.setList(fullList);
		}
	}

	public void executeDisplay(String[] commandParts) throws Exception {
		ArrayList<Task> fullList = new ArrayList<Task>();
		LemonGUIController.setCommand(commandParts[0]);
		Task currentTask;
		
		int id = Integer.parseInt(commandParts[1]);
		fullList = FileStorage.read(path);
		currentTask = fullList.get(id - 1);
		LemonGUIController.setTask(currentTask);
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
