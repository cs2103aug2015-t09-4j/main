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
	private static final String TASKTYPE_event = "event";
	private static final String TASKTYPE_deadline = "deadline";
	private static final String TASKTYPE_overdue = "overdue";
	private static final String TASKTYPE_done = "done";
	private static final String TASKTYPE_floating = "floating";

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
	private static ArrayList<Task> searchResults;
	private static String[] date = {"", ""};
	

	public CommandExecutor() throws Exception {
		if (parser == null) {
			parser = new Parser();
		}
		listType = "overdue";
		date[1] = parser.getCurrentDate();
		lastStates = new Stack<String>();
		undoneStates = new Stack<String>();
		lastState = "";
		path = "";
		floatingTasks= new ArrayList<Task>();
		deadlineTasks = new ArrayList<Task>();
		eventTasks = new ArrayList<Task>();
		allTasks = new ArrayList<Task>();
		doneTasks = new ArrayList<Task>();
		overdueTasks = new ArrayList<Task>();
		searchResults = new ArrayList<Task>();
	}
	
	public void updateLists() throws Exception{
//		ArrayList<Task> newList = new ArrayList<Task>();
//		newList.addAll(floatingTasks);
//		newList.addAll(deadlineTasks);
//		newList.addAll(eventTasks);
//		newList = executeSort(newList);
//		ArrayList<ArrayList<Task>> updatedLists = StorageFunction.separateTaskList(newList);
		ArrayList<ArrayList<Task>> updatedLists = FileStorage.readStringAsObject(path);
		for (int counter = 0; counter < updatedLists.size(); counter++) {
			for (int counter1 = 0; counter1 < updatedLists.get(counter).size(); counter1++) {
				if (updatedLists.get(counter).get(counter1).getTaskIsNewest()) {
					updatedLists.get(counter).get(counter1).removeTaskIsNewest();
				}
			}
		}
		floatingTasks = updatedLists.get(0);
		deadlineTasks = updatedLists.get(1);
		eventTasks = updatedLists.get(2);
		allTasks = updatedLists.get(3);
		doneTasks = updatedLists.get(4);
		overdueTasks = updatedLists.get(5);
	}
	
	public void executeAdd(String[] commandParts) throws Exception {
		String commandType = commandParts[0];
		Task newTask = parser.parseTask(commandParts);
		newTask.setTaskIsNewest();
		addTaskToList(newTask);	
		LemonGUIController.setCommand(commandType);
		System.out.println(newTask);
		writeToFile();
	}

	public void executeEdit(String[] commandParts) throws Exception {
		String commandType = commandParts[0];
		int deleteIndex = Integer.valueOf(commandParts[1]);
		LemonGUIController.setCommand(commandType);
		String[] stringToParse = getStringForParsing(commandParts);
		Task newTask = parser.parseTask(stringToParse);
		Task oldTask = deleteTaskFromList(deleteIndex);
		newTask.mergeTaskDetails(oldTask);
		System.out.println(oldTask);
		System.out.println(newTask);
		addTaskToList(newTask);
		listType = newTask.getTaskType();
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
		case TASKTYPE_floating:
			floatingTasks.add(newTask);
			listType = "floating";
			break;
		case TASKTYPE_deadline:
			this.fillUpTime(newTask);
			deadlineTasks.add(newTask);
			date[1] = newTask.getTaskEndDate();
			listType = "date";
			break;
		case TASKTYPE_event:
			this.fillUpTime(newTask);
			eventTasks.add(newTask);
			date[1] = newTask.getTaskStartDate();
			listType = "date";
			break;
		}
	}

	public Task deleteTaskFromList(int deleteId) throws IOException, ClassNotFoundException {
		Task deletedTask = new Task();
		System.out.println("type: " + listType);
		switch (listType) {
		case TASKTYPE_floating:
			deletedTask = removeTaskFromFloatingList(deleteId);
			break;
		case TASKTYPE_deadline:
			if (deleteId > overdueTasks.size()) {
				int temp = deleteId - overdueTasks.size();
				deletedTask = removeTaskFromdeadlineList(temp);
			} else {
				deletedTask = removeTaskFromoverdueList(deleteId);
			}
			break;
		case TASKTYPE_event:
			deletedTask = removeTaskFromeventList(deleteId);
			break;
		case TASKTYPE_overdue:
			deletedTask = removeTaskFromoverdueList(deleteId);
			break;
		case TASKTYPE_done:
			deletedTask = removeTaskFromdoneList(deleteId);
			break;
		}
		return deletedTask;
	}

	public Task removeTaskFromFloatingList(int deleteId) throws IOException, ClassNotFoundException {
		Task taskToDelete = floatingTasks.remove(deleteId - 1);
		LemonGUIController.setTask(taskToDelete);
		return taskToDelete;
	}

	private Task removeTaskFromdeadlineList(int deleteId) throws IOException, ClassNotFoundException {
		Task taskToDelete = deadlineTasks.remove(deleteId - 1);
		LemonGUIController.setTask(taskToDelete);
		return taskToDelete;
	}

	private Task removeTaskFromeventList(int deleteId) throws IOException, ClassNotFoundException {
		Task taskToDelete = eventTasks.remove(deleteId - 1);
		LemonGUIController.setTask(taskToDelete);
		return taskToDelete;
	}
	
	private Task removeTaskFromoverdueList(int deleteId) throws IOException, ClassNotFoundException {
		Task taskToDelete = overdueTasks.remove(deleteId - 1);
		LemonGUIController.setTask(taskToDelete);
		return taskToDelete;
	}
	
	private Task removeTaskFromdoneList(int deleteId) throws IOException, ClassNotFoundException {
		Task taskToDelete = doneTasks.remove(deleteId - 1);
		LemonGUIController.setTask(taskToDelete);
		return taskToDelete;
	}

	/*
	 * get what user wants to view date e.g. navigate 010101
	 */
	
	public void executeNavigate(String[] commandParts) throws ClassNotFoundException, IOException, ParseException {
		String commandType = commandParts[0];
		listType = "date";
		date[1] = commandParts[1];
		LemonGUIController.setTimeLineDate(date[1]);
		LemonGUIController.setCommand(commandType);
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
		Date dateToView = sdf.parse(date[1]);

		ArrayList<Task> tasksOnDate = new ArrayList<Task>();
		Task currentTask;
		for (int index = 0; index < eventTasks.size(); index++) {
			currentTask = eventTasks.get(index);
			Date dateStart = sdf.parse(currentTask.getTaskStartDate());
			Date dateEnd = sdf.parse(currentTask.getTaskEndDate());
			if ((dateEnd.compareTo(dateToView) >= 0) && (dateStart.compareTo(dateToView) <= 0)) {
				fillUpTime(currentTask);
				tasksOnDate.add(currentTask);
			}
		}
		
		ArrayList<Task> temp = new ArrayList<Task>();
		temp.addAll(deadlineTasks);
		temp.addAll(overdueTasks);
		for (int index = 0; index < temp.size(); index++) {
			currentTask = temp.get(index);
			if (currentTask.getTaskEndDate().equals(date[1])) {
				fillUpTime(currentTask);
				tasksOnDate.add(currentTask);
			}
		}
		listToTimeline = Sort.sortByTime(tasksOnDate);
	}

	public void executeHelp() {
		// GUIConsole.displayHelp();
	}
	
	public void executeUpdate() throws IOException, ClassNotFoundException {
		String currentDate = parser.getCurrentDate();
		for (int i = 0; i < deadlineTasks.size(); i++) {
			Task taskToCheck = deadlineTasks.get(i);
			String endDate = taskToCheck.getTaskEndDate();
				if (parser.endDatePassed(currentDate, endDate) && (!taskToCheck.getTaskType().equals(TASKTYPE_overdue))) {
					taskToCheck.setTaskType(TASKTYPE_overdue);
				}
		}
			
		for (int j = 0; j < eventTasks.size(); j++) {
			Task taskToCheck = eventTasks.get(j);
			String endDate = taskToCheck.getTaskEndDate();
				if (parser.endDatePassed(currentDate, endDate) && (!taskToCheck.getTaskType().equals(TASKTYPE_done))) {
					taskToCheck.setTaskType(TASKTYPE_done);
				}
		}
	}

//	public void executeRemoveNewest() throws IOException, ClassNotFoundException {
//		ArrayList<Task> array = FileStorage.readStringAsObject(path);
//		assert(array != null) : "unable to read from specified path";
//		for (int counter = 0; counter < array.size(); counter++) {
//			Task task = array.get(counter);
//			if (task.getTaskIsNewest()) {
//				task.removeTaskIsNewest();
//			}
//		}
//		FileStorage.clear();
//		int j = 0;
//		while (j < array.size()) {
//			FileStorage.writeObjectAsString(array.get(j));
//			j++;
//		}
//	}

	public void executeList(String[] commandParts) throws Exception {
		listType = commandParts[1];
		System.out.println("listing: " + listType);
		LemonGUIController.setCommand(commandParts[0]);
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
		LemonGUIController.setCommand(TASKTYPE_done);
		int deleteId = Integer.valueOf(commandParts[1]);
		deleteTaskFromList(deleteId);

	}

	public ArrayList<Task> executeSort(ArrayList<Task> list) {
		list = Sort.normal_sort(list);
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
		searchResults.clear();
		listType = "search";

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
				searchResults.add(searchedTask);
				System.out.println("result: " + searchResults);
			}
		};
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
			return 2 * ans;
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
			return 2 * ans;
		}
		
		if (temp > 30) {
			ans = ans + 1;
			return ans * 2;
		} else{
			return ans * 2 + 1;
		}
	}
	
	public ArrayList<ArrayList<Task>> passListsToGUI() throws Exception {
//		lemonGUI.setCommand();
		ArrayList<ArrayList<Task>> temp = new ArrayList<ArrayList<Task>>();
		ArrayList<Task> combinedList = new ArrayList<Task>();
		System.out.println(listType);
		if (listType.equals("overdue")){
			executeNavigate(date);
			temp.add(listToTimeline);
			temp.add(overdueTasks);
			updateLists();
			listType = "overdue";
			return temp;
		} else if (listType.equals("All")) {
//			System.out.println("floating: " + floatingTasks);
			executeNavigate(date);
			temp.add(listToTimeline);
			temp.add(allTasks);
			updateLists();
			listType = "All";
			return temp;
		} else if (listType.equals("floating")) {
//			System.out.println("floating: " + floatingTasks);
			executeNavigate(date);
			temp.add(listToTimeline);
			temp.add(floatingTasks);
//			System.out.println("full: " + temp);
			updateLists();
			listType = "floating";
			return temp;
		} else if (listType.equals("deadline")) {
			executeNavigate(date);
			temp.add(listToTimeline);
			combinedList.addAll(overdueTasks);
			combinedList.addAll(deadlineTasks);
			System.out.println("combine: " + combinedList);
			temp.add(combinedList);
			listType = "deadline";
			updateLists();
			return temp;
		} else if (listType.equals("event")) {
			executeNavigate(date);
			temp.add(listToTimeline);
			temp.add(eventTasks);
			updateLists();
			listType = "event";
			return temp;
		} else if (listType.equals("done")) {
			executeNavigate(date);
			temp.add(listToTimeline);
			temp.add(doneTasks);
			updateLists();
			listType = "done";
			return temp;
		} else if (listType.equals("Search")) {
			executeNavigate(date);
			temp.add(listToTimeline);
			temp.add(searchResults);
			updateLists();
			System.out.println("search");
			listType = "Search";
			return temp;
		} else {
			executeNavigate(date);
			temp.add(listToTimeline);
			temp.add(listToTimeline);
			updateLists();
			return temp;
		}
	}
	
	public void writeToFile() throws IOException, ClassNotFoundException {
		executeUpdate();
		ArrayList<Task> temp = new ArrayList<Task>();
		temp.addAll(floatingTasks);
		temp.addAll(deadlineTasks);
		temp.addAll(eventTasks);
		temp.addAll(overdueTasks);
		temp.addAll(doneTasks);
		//sort temp here!!!!!!!
		FileStorage.clear();
		FileStorage.writeObjectAsString(temp);
		System.out.println("temp" + temp);
	}

	public String passDate() {
		return date[1];
	}
	
	public String passListType() {
		return listType;
	}
}
