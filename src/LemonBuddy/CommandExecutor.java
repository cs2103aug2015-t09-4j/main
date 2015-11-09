package LemonBuddy;

import java.io.File;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import LemonBuddy.view.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;

public class CommandExecutor extends FileStorage{
	private static final String TASKTYPE_EVENT = "event";
	private static final String TASKTYPE_DEADLINE = "deadline";
	private static final String TASKTYPE_OVERDUE = "overdue";
	private static final String TASKTYPE_DONE = "done";
	private static final String TASKTYPE_FLOATING = "floating";

	private static String path;
	private lemonGUI lemonGUI;
	private Parser parser;
	String lastState;
	Stack<String> lastStates;
	Stack<String> undoneStates;

	private static String listType;

	private static ArrayList<Task> floatingTasks;
	private static ArrayList<Task> deadlineTasks;
	private static ArrayList<Task> eventTasks;
	private static ArrayList<Task> allTasks;
	private static ArrayList<Task> doneTasks;
	private static ArrayList<Task> overdueTasks;
	private static ArrayList<Task> listToDisplay;
	private static ArrayList<Task> listToTimeline;
	private static String[] date = {"", ""};
	

	public CommandExecutor() throws ClassNotFoundException, IOException {
		if (parser == null) {
			parser = new Parser();
		}
		if (lemonGUI == null) {
			lemonGUI = new lemonGUI();
		}
		lastStates = new Stack<String>();
		undoneStates = new Stack<String>();
		lastState = "";
		path = "";
		ArrayList<ArrayList<Task>> temp = FileStorage.readStringAsObject(path);
		floatingTasks = new ArrayList<Task>(temp.get(0));
		deadlineTasks = new ArrayList<Task>(temp.get(1));
		eventTasks = new ArrayList<Task>(temp.get(2));
		allTasks = new ArrayList<Task>(temp.get(3));
		doneTasks = new ArrayList<Task>(temp.get(4));
		overdueTasks = new ArrayList<Task>(temp.get(5));
		listType = "overdue";
		date[1] = parser.getCurrentDate();
	}
	
	public void updateLists() throws IOException, ClassNotFoundException{
		ArrayList<ArrayList<Task>> updatedLists = FileStorage.readStringAsObject(path);
		floatingTasks = updatedLists.get(0);
		deadlineTasks = updatedLists.get(1);
		eventTasks = updatedLists.get(2);
		allTasks = updatedLists.get(3);
		doneTasks = updatedLists.get(4);
		overdueTasks = updatedLists.get(5);
	}
	
	public void executeAdd(String[] commandParts) throws Exception {
		String commandType = commandParts[0];
		System.out.println(commandType);
		Task newTask = parser.parseTask(commandParts);
		newTask.setTaskIsNewest();
		addTaskToList(newTask);
		LemonGUIController.setCommand(commandType);
	}

	public void executeEdit(String[] commandParts) throws Exception {
		String commandType = commandParts[0];
		int deleteIndex = Integer.valueOf(commandParts[1]);
		LemonGUIController.setCommand(commandType);
		String[] stringToParse = getStringForParsing(commandParts);
		Task newTask = parser.parseTask(stringToParse);
		Task oldTask = deleteTaskFromList(deleteIndex);
		newTask.mergeTaskDetails(oldTask);
		addTaskToList(newTask);
		writeToFile();
	}

	public String[] getStringForParsing(String[] commandParts) {
		String[] stringToParse = new String[commandParts.length - 1];
		for (int i = 1; i < commandParts.length; i++) {
			stringToParse[i - 1] = commandParts[i];
		}
		stringToParse[0] = "add";
		return stringToParse;
	}

	public void executeDelete(String[] commandParts) throws Exception {
		String commandType = commandParts[0];
		int deleteId = Integer.valueOf(commandParts[1]);
		LemonGUIController.setCommand(commandType);
		deleteTaskFromList(deleteId);
		writeToFile();
	}

	private void addTaskToList(Task newTask) {

		switch (newTask.getTaskType()) {
		case TASKTYPE_FLOATING:
			floatingTasks.add(newTask);
			listToDisplay = floatingTasks;
			System.out.println(floatingTasks);
			listType = "floating";
			System.out.println(listType);
			break;
		case TASKTYPE_DEADLINE:
			fillUpTime(newTask);
			deadlineTasks.add(newTask);
			System.out.println("added " + newTask);
			listType = "date";
			date[1] = newTask.getTaskEndDate();
			break;
		case TASKTYPE_EVENT:
			fillUpTime(newTask);
			eventTasks.add(newTask);
			listType = "date";
			date[1] = newTask.getTaskStartDate();
			break;
		}
	}

	public Task deleteTaskFromList(int deleteId) throws IOException, ClassNotFoundException {
		Task deletedTask = new Task();
		switch (listType) {
		case TASKTYPE_FLOATING:
			deletedTask = removeTaskFromFloatingList(deleteId);
			break;
		case TASKTYPE_DEADLINE:
			deletedTask = removeTaskFromDeadlineList(deleteId);
			break;
		case TASKTYPE_EVENT:
			deletedTask = removeTaskFromEventList(deleteId);
			break;
		}
		return deletedTask;
	}

	public Task removeTaskFromFloatingList(int deleteId) throws IOException, ClassNotFoundException {
		Task taskToDelete = floatingTasks.remove(deleteId - 1);
		LemonGUIController.setTask(taskToDelete);
		return taskToDelete;
	}

	private Task removeTaskFromDeadlineList(int deleteId) throws IOException, ClassNotFoundException {
		Task taskToDelete = deadlineTasks.remove(deleteId - 1);
		LemonGUIController.setTask(taskToDelete);
		return taskToDelete;
	}

	private Task removeTaskFromEventList(int deleteId) throws IOException, ClassNotFoundException {
		Task taskToDelete = eventTasks.remove(deleteId - 1);
		LemonGUIController.setTask(taskToDelete);
		return taskToDelete;
	}

	/*
	 * get what user wants to view date e.g. navigate 010101
	 */
	
	public void executeNavigate(String[] commandParts) throws ClassNotFoundException, IOException, ParseException {
		// get days related to day
		String commandType = commandParts[0];
		String dateInput = commandParts[1];
		LemonGUIController.setTimeLineDate(dateInput);
		LemonGUIController.setCommand(commandType);
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
		Date dateToView = sdf.parse(dateInput);

		ArrayList<Task> tasksOnDate = new ArrayList<Task>();
		Task currentTask;
		for (int index = 0; index < eventTasks.size(); index++) {
			currentTask = eventTasks.get(index);
			fillUpTime(currentTask);
			Date dateStart = sdf.parse(currentTask.getTaskStartDate());
			Date dateEnd = sdf.parse(currentTask.getTaskEndDate());
			if ((dateEnd.compareTo(dateToView) >= 0) && (dateStart.compareTo(dateToView) <= 0)) {
				tasksOnDate.add(currentTask);
			}
		}
		
		for (int index = 0; index < deadlineTasks.size(); index++) {
			currentTask = deadlineTasks.get(index);
			fillUpTime(currentTask);
			if (currentTask.getTaskEndDate().equals(dateInput)) {
				tasksOnDate.add(currentTask);
			}
		}
		
		for (int index = 0; index < overdueTasks.size(); index++) {
			currentTask = overdueTasks.get(index);
			fillUpTime(currentTask);
			if (currentTask.getTaskEndDate().equals(dateInput)) {
				tasksOnDate.add(currentTask);
			}
		}
		
		listToTimeline = Sort.sortByTime(tasksOnDate);
	}

	public void executeHelp() {
		// GUIConsole.displayHelp();
	}
	
	public void executeUpdateOverdueAndDone() throws IOException, ClassNotFoundException {
		String currentDate = parser.getCurrentDate();
		for (int i = 0; i < deadlineTasks.size(); i++) {
			Task taskToCheck = deadlineTasks.get(i);
			String endDate = taskToCheck.getTaskEndDate();
				if (parser.endDatePassed(currentDate, endDate) && (!taskToCheck.getTaskType().equals(TASKTYPE_OVERDUE))) {
					taskToCheck.setTaskType(TASKTYPE_OVERDUE);
				}
		}
			
		for (int j = 0; j < eventTasks.size(); j++) {
			Task taskToCheck = eventTasks.get(j);
			String endDate = taskToCheck.getTaskEndDate();
				if (parser.endDatePassed(currentDate, endDate) && (!taskToCheck.getTaskType().equals(TASKTYPE_DONE))) {
					taskToCheck.setTaskType(TASKTYPE_DONE);
				}
		}
	}

	public void executeList(String[] commandParts) throws Exception {
		String listType = commandParts[1];
		LemonGUIController.setCommand(commandParts[0]);
		LemonGUIController.setListType(listType);
		ArrayList<Task> floatingTasks = new ArrayList<Task>();
		ArrayList<Task> deadlineTasks = new ArrayList<Task>();
		ArrayList<Task> eventTasks = new ArrayList<Task>();
		ArrayList<Task> overdueTasks = new ArrayList<Task>();
		ArrayList<Task> doneTasks = new ArrayList<Task>();

		ArrayList<Task> fullList = new ArrayList<Task>();
		Task currentTask;

		//fullList = FileStorage.readStringAsObject(path);

		// 3 types of arraylist here
		for (int j = 0; j < fullList.size(); j++) {
			currentTask = fullList.get(j);
			if (!currentTask.getTaskType().equals(TASKTYPE_DONE)) {
				if (currentTask.getTaskType().equals(TASKTYPE_FLOATING)) {
					floatingTasks.add(currentTask);
				}
				if (currentTask.getTaskType().equals(TASKTYPE_DEADLINE)) {
					deadlineTasks.add(currentTask);
				}
				if (currentTask.getTaskType().equals(TASKTYPE_EVENT)) {
					eventTasks.add(currentTask);
				}
				if (currentTask.getTaskType().equals(TASKTYPE_OVERDUE)) {
					overdueTasks.add(currentTask);
				}
			} else {
				doneTasks.add(currentTask);
			}
		}

		if (listType.equals(TASKTYPE_FLOATING)) {
			LemonGUIController.setList(floatingTasks);
		} else if (listType.equals(TASKTYPE_DEADLINE)) {
			LemonGUIController.setList(deadlineTasks);
		} else if (listType.equals(TASKTYPE_EVENT)) {
			LemonGUIController.setList(eventTasks);
		} else if (listType.equals("all")) {
			LemonGUIController.setList(fullList);
		} else if (listType.equals(TASKTYPE_OVERDUE)) {
			LemonGUIController.setList(overdueTasks);
		} else if (listType.equals(TASKTYPE_DONE)) {
			LemonGUIController.setList(doneTasks);
		}
	}

	public void parseInvalidCommand(String command) {
		// GUIConsole.displayErrorMessage(command);
	}

	public void executeUndo() throws IOException, Exception {
		// System.out.println(FileStorage.readStringAsString(path));
		if (lastStates.isEmpty()) {
			throw new Exception("Already at last undo");
		}
		undoneStates.push(FileStorage.readStringAsString(path));
		FileStorage.clear();
		FileStorage.writeStringAsString(lastStates.pop());
		LemonGUIController.setCommand("undo");
	}

	public void executeDone(String[] commandParts) throws Exception, IOException {
		LemonGUIController.setCommand(TASKTYPE_DONE);
		int deleteId = Integer.valueOf(commandParts[1]);
		deleteTaskFromList(deleteId);

	}

	public ArrayList<Task> executeSort(ArrayList<Task> list) {
		Sort.sortByDateThenPriority(list);
		return list;
	}

	public void saveLastState() throws Exception, IOException {
		String currentState = FileStorage.readStringAsString(path);

		if (!currentState.equals(lastState)) {
			lastStates.push(currentState);
			lastState = currentState;
			undoneStates = new Stack<String>();
		}
	}

	public void executeRedo() throws IOException, Exception {
		if (!undoneStates.isEmpty()) {
			lastStates.push(FileStorage.readStringAsString(path));
			FileStorage.clear();
			FileStorage.writeStringAsString(undoneStates.pop());
			LemonGUIController.setCommand("redo");
		} else {
			// System.out.println("Already at current");
			LemonGUIController.setCommand("redo maxed");
		}
	}

	public void executeSearch(String[] commandParts) throws ClassNotFoundException, IOException {
		LemonGUIController.setCommand("search");
		LemonGUIController.setListType(commandParts[0]);
		ArrayList<Task> searchResult = new ArrayList<Task>();

		int phraseSize = commandParts.length - 1;
		String searchKeyword = commandParts[1];
		// System.out.println("phrase size: " + phraseSize);

		if (phraseSize > 1) {
			for (int i = 2; i < phraseSize + 1; i++) {
				searchKeyword += " ";
				searchKeyword += commandParts[i];
				System.out.println("key phrase: " + searchKeyword);
			}
		}

		searchKeyword.toLowerCase();

		for (int j = 0; j < allTasks.size(); j++) {
			Task searchedTask = allTasks.get(j);
			if (searchedTask.getTaskName().toLowerCase().contains(searchKeyword)
					|| searchedTask.getTaskDescription().toLowerCase().contains(searchKeyword)) {
				searchResult.add(searchedTask);
				// System.out.println("result: " + searchedTask);
			}
		}
		LemonGUIController.setList(searchResult);
	}
	
	private void fillUpTime(Task newTask) {
		if (newTask.getTaskType().equals("floating")) {
			return;
		} else if (newTask.getTaskType().equals("event")) {
			int newStartTime = roundDownTime(newTask.getTaskStartTime());
			int newEndTime = roundUpTime(newTask.getTaskEndTime());
			for (int counter = newStartTime; counter< newEndTime; counter++) {
				newTask.setEventTime(counter);
			}
		} else {
			int newEndTime = roundDownTime(newTask.getTaskEndTime());
			newTask.setDeadlineTime(newEndTime);
		}
	}
	
	private int roundDownTime(String time) {
		int ans = Integer.parseInt(time);
		int temp = Integer.parseInt(time);
		ans = ans/100;
		temp = temp % 100;
		if (temp == 0) {
			return ans * 2;
		} else if (temp > 30) {
			return 2 * ans + 1;
		} else {
			return 2 * ans;
		}
	}
	
	private int roundUpTime(String time) {
		int ans = Integer.parseInt(time);
		int temp = Integer.parseInt(time);
		ans = ans/100;
		temp = temp % 100;
		if (temp == 0) {
			return ans * 2;
		}
		
		if (temp > 30) {
			ans = ans + 1;
			return ans * 2;
		} else{
			return ans * 2 + 1;
		}
	}
	
	public void passToGUI() throws Exception {
		if (listType.equals("overdue")){
			lemonGUI.setListForDisplay(overdueTasks);
		} else if (listType.equals("floating")) {
			lemonGUI.setListForDisplay(floatingTasks);
			System.out.println(floatingTasks);
		} else if (listType.equals("deadline")) {
			lemonGUI.setListForDisplay(deadlineTasks);
		} else if (listType.equals("event")) {
			lemonGUI.setListForDisplay(eventTasks);
		} else if (listType.equals("done")) {
			lemonGUI.setListForDisplay(doneTasks);
		}
		System.out.println("pass to GUI " + date[1]);
		executeNavigate(date);
		System.out.println(deadlineTasks);
		lemonGUI.setListForTimeline(listToTimeline);
		System.out.println(listToTimeline);
		if (listType.equals("date")) {
			lemonGUI.setListForDisplay(listToTimeline);
		}
		writeToFile();
	}
	
	public void writeToFile() throws IOException, ClassNotFoundException {
		executeUpdateOverdueAndDone();
		ArrayList<Task> temp = floatingTasks;
		temp.addAll(deadlineTasks);
		temp.addAll(eventTasks);
		temp.addAll(overdueTasks);
		temp.addAll(doneTasks);
		//sort temp here!!!!!!!
		FileStorage.clear();
		FileStorage.writeObjectAsString(temp);
	}
}
