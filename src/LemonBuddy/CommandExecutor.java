package LemonBuddy;

import java.io.File;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import LemonBuddy.view.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;

public class CommandExecutor extends FileStorage {
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

	protected static String listType;

	private static ArrayList<Task> floatingTasks;
	private static ArrayList<Task> deadlineTasks;
	private static ArrayList<Task> eventTasks;
	private static ArrayList<Task> allTasks;
	private static ArrayList<Task> doneTasks;
	private static ArrayList<Task> overdueTasks;
	private static ArrayList<Task> listToDisplay;
	private static ArrayList<Task> listToTimeline;
	private static ArrayList<Task> searchResults;
	private static String[] date = { "", "" };
	private static CommandExecutor commandexecutor;
	private static Task selectedTask;

	public CommandExecutor() throws Exception {
		parser = Parser.getInstance();
		listType = "overdue";
		date[1] = parser.getCurrentDate();
		lastStates = new Stack<String>();
		undoneStates = new Stack<String>();
		lastState = "";
		path = "";
		floatingTasks = new ArrayList<Task>();
		deadlineTasks = new ArrayList<Task>();
		eventTasks = new ArrayList<Task>();
		allTasks = new ArrayList<Task>();
		doneTasks = new ArrayList<Task>();
		overdueTasks = new ArrayList<Task>();
		searchResults = new ArrayList<Task>();
	}

	public static CommandExecutor getInstance() throws Exception {
		if (commandexecutor == null) {
			commandexecutor = new CommandExecutor();
		}
		return commandexecutor;
	}

	public void updateLists() throws Exception {
		ArrayList<ArrayList<Task>> updatedLists = FileStorage.readStringAsObject(path);
		removeNewest(updatedLists);
		floatingTasks = updatedLists.get(0);
		deadlineTasks = updatedLists.get(1);
		eventTasks = updatedLists.get(2);
		allTasks = updatedLists.get(3);
		doneTasks = updatedLists.get(4);
		overdueTasks = updatedLists.get(5);
	}

	public void removeNewest(ArrayList<ArrayList<Task>> updatedLists) {
		for (int counter = 0; counter < updatedLists.size(); counter++) {
			for (int counter1 = 0; counter1 < updatedLists.get(counter).size(); counter1++) {
				if (updatedLists.get(counter).get(counter1).getTaskIsNewest()) {
					updatedLists.get(counter).get(counter1).removeTaskIsNewest();
				}
			}
		}
	}

	public void executeAdd(String[] commandParts) throws Exception {
		Task newTask = parser.parseTask(commandParts);
		newTask.setTaskIsNewest();
		addTaskToList(newTask);
		selectedTask = newTask;
		writeToFile();
	}

	public void executeEdit(String[] commandParts) throws Exception {
		int deleteIndex = Integer.valueOf(commandParts[1]);
		String[] stringToParse = getStringForParsing(commandParts);
		Task newTask = parser.parseTask(stringToParse);
		Task oldTask = deleteTaskFromList(deleteIndex);
		newTask.mergeTaskDetails(oldTask);
		addTaskToList(newTask);
		selectedTask = newTask;
		listType = newTask.getTaskType();
		writeToFile();
		updateLists();
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
		int deleteID = Integer.valueOf(commandParts[1]);
		deleteTaskFromList(deleteID);
		writeToFile();
	}

	public void addTaskToList(Task newTask) {
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

<<<<<<< HEAD
	public Task deleteTaskFromList(int deleteID) throws IOException, ClassNotFoundException {
		Task deletedTask = new Task();
		switch (listType) {
		case TASKTYPE_floating:
			deletedTask = removeTaskFromFloatingList(deleteID);
			break;
		case TASKTYPE_deadline:
			if (deleteID > overdueTasks.size()) {
				int temp = deleteID - overdueTasks.size();
				deletedTask = removeTaskFromDeadlineList(temp);
			} else {
				deletedTask = removeTaskFromOverdueList(deleteID);
			}
			break;
		case TASKTYPE_event:
			deletedTask = removeTaskFromEventList(deleteID);
			break;
		case TASKTYPE_overdue:
			deletedTask = removeTaskFromOverdueList(deleteID);
			break;
		case TASKTYPE_done:
			deletedTask = removeTaskFromDoneList(deleteID);
			break;
=======
	public Task deleteTaskFromList(int deleteId) throws Exception {
		Task deletedTask = new Task();
		try {

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
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new Exception("Invalid index");
>>>>>>> origin/master
		}
		selectedTask = deletedTask;
		return deletedTask;
	}

<<<<<<< HEAD
	public Task removeTaskFromFloatingList(int deleteID) throws IOException, ClassNotFoundException {
		Task taskToDelete = floatingTasks.remove(deleteID - 1);
		return taskToDelete;
	}

	public Task removeTaskFromDeadlineList(int deleteID) throws IOException, ClassNotFoundException {
		Task taskToDelete = deadlineTasks.remove(deleteID - 1);
		return taskToDelete;
	}

	private Task removeTaskFromEventList(int deleteID) throws IOException, ClassNotFoundException {
		Task taskToDelete = eventTasks.remove(deleteID - 1);
		return taskToDelete;
	}
	
	private Task removeTaskFromOverdueList(int deleteID) throws IOException, ClassNotFoundException {
		Task taskToDelete = overdueTasks.remove(deleteID - 1);
		return taskToDelete;
	}
	
	private Task removeTaskFromDoneList(int deleteID) throws IOException, ClassNotFoundException {
		Task taskToDelete = doneTasks.remove(deleteID - 1);
=======
	public Task removeTaskFromFloatingList(int deleteId) throws Exception {
		if (floatingTasks.size() <= deleteId) {
			throw new Exception("Invalid Index");
		}
		Task taskToDelete = floatingTasks.remove(deleteId - 1);
		return taskToDelete;
	}

	private Task removeTaskFromdeadlineList(int deleteId) throws Exception {
		if (deadlineTasks.size() <= deleteId) {
			throw new Exception("Invalid Index");
		}
		Task taskToDelete = deadlineTasks.remove(deleteId - 1);
		return taskToDelete;
	}

	private Task removeTaskFromeventList(int deleteId) throws Exception {
		if (eventTasks.size() <= deleteId) {
			throw new Exception("Invalid Index");
		}
		Task taskToDelete = eventTasks.remove(deleteId - 1);
		return taskToDelete;
	}

	private Task removeTaskFromoverdueList(int deleteId) throws Exception {
		if (overdueTasks.size() <= deleteId) {
			throw new Exception("Invalid Index");
		}
		Task taskToDelete = overdueTasks.remove(deleteId - 1);
		return taskToDelete;
	}

	private Task removeTaskFromdoneList(int deleteId) throws Exception {
		if (doneTasks.size() <= deleteId) {
			throw new Exception("Invalid Index");
		}
		Task taskToDelete = new Task();
		taskToDelete = doneTasks.remove(deleteId - 1);
>>>>>>> origin/master
		return taskToDelete;
	}

	/*
	 * get what user wants to view date e.g. navigate 010101
	 */

	public void executeNavigate(String[] commandParts) throws ClassNotFoundException, IOException, ParseException {
		listType = "date";
		date[1] = commandParts[1];
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

	public void executeList(String[] commandParts) throws Exception {
		listType = commandParts[1];
	}

	public void parseInvalidCommand(String command) {
		// GUIConsole.displayErrorMessage(command);
	}

	public void executeUndo() throws IOException, Exception {
		if (lastStates.isEmpty()) {
			throw new Exception("Already at last undo");
		}
		undoneStates.push(FileStorage.readStringAsString(path));
		FileStorage.clear();
		FileStorage.writeStringAsString(lastStates.pop());
	}

	public void executeDone(String[] commandParts) throws Exception, IOException {
		int deleteID = Integer.valueOf(commandParts[1]);
		deleteTaskFromList(deleteID);

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
		} else {
			// System.out.println("Already at current");
		}
	}

	public void executeSearch(String[] commandParts) throws ClassNotFoundException, IOException {
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
		}
		;
	}

	private void fillUpTime(Task newTask) {
		if (newTask.getTaskType().equals("floating")) {
			return;
		} else if (newTask.getTaskType().equals("event")) {
			int newStartTime = roundDownTime(newTask.getTaskStartTime());
			int newEndTime = roundUpTime(newTask.getTaskEndTime());
			for (int counter = newStartTime; counter < newEndTime; counter++) {
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
		ans = ans / 100;
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
		ans = ans / 100;
		temp = temp % 100;
		if (temp == 0) {
			return 2 * ans;
		}

		if (temp > 30) {
			ans = ans + 1;
			return ans * 2;
		} else {
			return ans * 2 + 1;
		}
	}

	public ArrayList<ArrayList<Task>> passListsToGUI() throws Exception {
		ArrayList<ArrayList<Task>> temp = new ArrayList<ArrayList<Task>>();
		ArrayList<Task> combinedList = new ArrayList<Task>();
		System.out.println(listType);
		if (listType.equals("overdue")) {
			executeNavigate(date);
			temp.add(listToTimeline);
			temp.add(overdueTasks);
			updateLists();
			listType = "overdue";
			return temp;
		} else if (listType.equals("All")) {
			executeNavigate(date);
			temp.add(listToTimeline);
			temp.add(allTasks);
			updateLists();
			listType = "All";
			return temp;
		} else if (listType.equals("floating")) {
			executeNavigate(date);
			temp.add(listToTimeline);
			temp.add(floatingTasks);
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
		} else if (listType.equals("search")) {
			executeNavigate(date);
			temp.add(listToTimeline);
			temp.add(searchResults);
			updateLists();
			System.out.println("search");
			listType = "search";
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
		Sort.normal_sort(temp);
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

	public Task passSelectedTask() {
		Task temp = selectedTask;
		System.out.println("hi" + temp);
		selectedTask = null;
		return temp;
	}
}
