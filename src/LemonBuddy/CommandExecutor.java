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
	private Parser parser;
	String lastState;
	Stack<String> lastStates;
	Stack<String> undoneStates;

	private String lastListType;

	private ArrayList<Task> floatingTasks;
	private ArrayList<Task> deadlineTasks;
	private ArrayList<Task> eventTasks;
	private ArrayList<Task> allTasks;
	private ArrayList<Task> doneTasks;
	private ArrayList<Task> overdueTasks;

	public CommandExecutor() {
		if (parser == null) {
			parser = new Parser();
		}
		lastStates = new Stack<String>();
		undoneStates = new Stack<String>();
		lastState = "";
		path = "";
		floatingTasks = new ArrayList<Task>();
		deadlineTasks = new ArrayList<Task>();
		eventTasks = new ArrayList<Task>();
		doneTasks = new ArrayList<Task>();
		allTasks = new ArrayList<Task>();
		overdueTasks = new ArrayList<Task>();
		lastListType = "overdue";
	}
	
	public void updateLists(){
		ArrayList<Task> newList = new ArrayList<Task>();
		newList.addAll(floatingTasks);
		newList.addAll(deadlineTasks);
		newList.addAll(eventTasks);
		newList = executeSort(newList);
		ArrayList<ArrayList<Task>> updatedLists = StorageFunction.separateTaskList(newList);
		floatingTasks = updatedLists.get(0);
		deadlineTasks = updatedLists.get(1);
		eventTasks = updatedLists.get(2);
		allTasks = updatedLists.get(3);
		doneTasks = updatedLists.get(4);
		overdueTasks = updatedLists.get(5);
	}
	
	// PRIORITY AND DESCRIPTION NOT DONE
	public void executeAdd(String[] commandParts) throws Exception {
		String commandType = commandParts[0];

		Task newTask = parser.parseTask(commandParts);
		newTask.setTaskIsNewest();
		addTaskToList(newTask);
		LemonGUIController.setTask(newTask);
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
	}

	// private void editTaskDetails(String[] commandParts, Task newTask) throws
	// IOException, ClassNotFoundException {
	// ArrayList<Task> fullList = FileStorage.readStringAsObject(path);
	// FileStorage.clear();
	// int taskToEditIndex = writeUntilTaskIndex(commandParts, fullList);
	// Task taskToEdit = fullList.get(taskToEditIndex);
	// FileStorage.writeObjectAsString(newTask.mergeTaskDetails(taskToEdit));
	// LemonGUIController.setTask(newTask);
	// writeRestOfList(fullList, taskToEditIndex);
	// }

	private String[] getStringForParsing(String[] commandParts) {
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
		// ArrayList<Task> array = FileStorage.readStringAsObject(path);
		// assert(array != null) : "unable to read from specified path";
		// if (Integer.valueOf(deleteId) > array.size() ||
		// Integer.valueOf(deleteId) <= 0) {
		// return;
		// }
		LemonGUIController.setCommand(commandType);
		Task deletedTask = deleteTaskFromList(deleteId);
		// removeTaskFromFile(commandParts);
	}

	private void addTaskToList(Task newTask) {

		switch (newTask.getTaskType()) {
		case TASKTYPE_FLOATING:
			floatingTasks.add(newTask);
			break;
		case TASKTYPE_DEADLINE:
			deadlineTasks.add(newTask);
			break;
		case TASKTYPE_EVENT:
			eventTasks.add(newTask);
			break;
		}
	}

	private Task deleteTaskFromList(int deleteId) throws IOException, ClassNotFoundException {
		Task deletedTask = new Task();
		switch (lastListType) {
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

	private Task removeTaskFromFloatingList(int deleteId) throws IOException, ClassNotFoundException {
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

	public void executeRecur(String[] commandParts) throws IOException, ClassNotFoundException {
		String recurType = commandParts[1];
		String recurID = commandParts[2];
		String recurFreq = commandParts[3];
		String recurEndDate = commandParts[4];

		ArrayList<Task> fullList = FileStorage.readStringAsObject(path);
		int taskTypeIndex = 1;

		for (int i = 0; i < fullList.size(); i++) {
			Task currentTask = fullList.get(i);

			if (currentTask.getTaskType().equals(recurType)) {
				if (recurID.equals(String.valueOf(taskTypeIndex))) {
					taskTypeIndex++;
					Task recurringTask = currentTask;
					if (recurType.equals(TASKTYPE_DEADLINE)) {
						// note: changed
						String currentRecurringDate = currentTask.getTaskEndDate();
						if (recurFreq.equals("yearly")) {
							currentRecurringDate = parser.addOneYear(currentRecurringDate);
							while (!parser.endDatePassed(currentRecurringDate, recurEndDate)) {
								recurringTask.setTaskEndDate(currentRecurringDate);
								FileStorage.writeObjectAsString(recurringTask);
								currentRecurringDate = parser.addOneYear(currentRecurringDate);
							}
						}

						/*
						 * while (!parser.endDatePassed(currentRecurringDate,
						 * recurEndDate)) { System.out.println(
						 * "While loop entered"); if
						 * (recurFreq.equals("yearly")) { currentRecurringDate =
						 * parser.addOneYear(currentRecurringDate);
						 * recurringTask.setTaskEndDate(currentRecurringDate);
						 * System.out.println(currentRecurringDate);
						 * FileStorage.write(recurringTask); } }
						 */

					} else if (recurType == TASKTYPE_EVENT) {

					}

				} else {
					taskTypeIndex++;
				}
			}
		}
	}

	public void executeNavigate(String[] commandParts) throws ClassNotFoundException, IOException, ParseException {
		// get days related to day
		String commandType = commandParts[0];
		String dateInput = commandParts[1];
		LemonGUIController.setTimeLineDate(dateInput);
		LemonGUIController.setCommand(commandType);
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
		Date dateToView = sdf.parse(dateInput);

		ArrayList<Task> eventsOnDate = new ArrayList<Task>();

		for (int index = 0; index < allTasks.size(); index++) {
			Task currentTask = allTasks.get(index);
			Date dateStart = sdf.parse(currentTask.getTaskStartDate());
			Date dateEnd = sdf.parse(currentTask.getTaskEndDate());
			if ((dateEnd.compareTo(dateToView) >= 0) && (dateStart.compareTo(dateToView) <= 0)) {
				eventsOnDate.add(currentTask);
			}
			if (currentTask.getTaskType().equals(TASKTYPE_DEADLINE)) {
				if (currentTask.getTaskEndDate().equals(dateInput)) {
					deadlineTasks.add(currentTask);
				}
			}

			deadlineTasks.addAll(eventTasks);

			ArrayList<Task> temp = Sort.sortByTime(deadlineTasks);
			LemonGUIController.setTimelineList(temp);
		}

		/*
		 * get what user wants to view date e.g. navigate 010101
		 */
	}

	public void executeHelp() {
		// GUIConsole.displayHelp();
	}

	/*public void executeUpdate() throws IOException, ClassNotFoundException {
		ArrayList<Task> array = FileStorage.readStringAsObject(path);
		assert(array != null) : "unable to read from specified path";

		String currentDate = parser.getCurrentDate();
		// System.out.println(currentDate);

		int i = 0;
		boolean anythingRemoved = false;

		for (i = 0; i < array.size(); i++) {
			Task overdueTask = array.get(i);
			String endDate = array.get(i).getTaskEndDate();
			// System.out.println(endDate);
			if (endDate.length() == 6) {
				if (parser.endDatePassed(currentDate, endDate) && (overdueTask.getTaskIsOverdue() == false)) {
					overdueTask.setTaskIsOverdue();
					array.set(i, overdueTask);
					anythingRemoved = true;
				}
			}
		}

		if (anythingRemoved) {
			FileStorage.clear();
			int j = 0;
			while (j < array.size()) {
				FileStorage.writeObjectAsString(array.get(j));
				j++;
			}
		}
	}*/
	
	public void executeUpdate() throws IOException, ClassNotFoundException {


		String currentDate = parser.getCurrentDate();

		for (int i = 0; i < deadlineTasks.size(); i++) {
			Task taskToCheck = deadlineTasks.get(i);
			String endDate = taskToCheck.getTaskEndDate();
				if (parser.endDatePassed(currentDate, endDate) && (taskToCheck.getTaskIsOverdue() == false)) {
					taskToCheck.setTaskIsOverdue();
				}
		}
			
		for (int j = 0; j < eventTasks.size(); j++) {
			Task taskToCheck = eventTasks.get(j);
			String endDate = taskToCheck.getTaskEndDate();
				if (parser.endDatePassed(currentDate, endDate) && (taskToCheck.getTaskIsDone() == false)) {
					taskToCheck.setTaskIsDone();
				}
		}
	}

	public void executeRemoveNewest() throws IOException, ClassNotFoundException {
		ArrayList<Task> array = FileStorage.readStringAsObject(path);
		assert(array != null) : "unable to read from specified path";
		for (int counter = 0; counter < array.size(); counter++) {
			Task task = array.get(counter);
			if (task.getTaskIsNewest()) {
				task.removeTaskIsNewest();
			}
		}
		FileStorage.clear();
		int j = 0;
		while (j < array.size()) {
			FileStorage.writeObjectAsString(array.get(j));
			j++;
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
			if (currentTask.getTaskIsDone() == false) {
				if (currentTask.getTaskType().equals(TASKTYPE_FLOATING)) {
					floatingTasks.add(currentTask);
				}
				if (currentTask.getTaskType().equals(TASKTYPE_DEADLINE)) {
					deadlineTasks.add(currentTask);
				}
				if (currentTask.getTaskType().equals(TASKTYPE_EVENT)) {
					eventTasks.add(currentTask);
				}
				if (currentTask.getTaskIsOverdue() == true) {
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

	public void executeDisplay(String[] commandParts) throws Exception {
		ArrayList<Task> fullList = new ArrayList<Task>();
		LemonGUIController.setCommand(commandParts[0]);
		Task currentTask;

		int id = Integer.parseInt(commandParts[1]);
		fullList = FileStorage.readStringAsObject(path);
		currentTask = fullList.get(id - 1);
		// LemonGUIController.setTask(currentTask);
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

	/*public void executeSortFloating() throws ClassNotFoundException, IOException {
		ArrayList<Task> fullList = FileStorage.readStringAsObject(path);
		// System.out.println("Hello");
		int sizeToCheck = fullList.size();
		for (int i = 0; i < sizeToCheck; i++) {
			Task currentTask = fullList.get(i);
			System.out.println("tasktype: " + currentTask.getTaskType());
			if (currentTask.getTaskType().equals(TASKTYPE_FLOATING)) {
				System.out.println("Entered if");
				fullList.add(fullList.remove(i));
				i--;
				sizeToCheck--;
			}
		}

		FileStorage.clear();
		for (int j = 0; j < fullList.size(); j++) {
			FileStorage.writeObjectAsString(fullList.get(j));
		}
	}*/

	public void executeClear(String[] commandParts) throws ClassNotFoundException, IOException {
		ArrayList<Task> fullList = new ArrayList<Task>();
		Task currentTask;
		fullList = FileStorage.readStringAsObject(path);
		FileStorage.clear();
		for (int j = 0; j < fullList.size(); j++) {
			currentTask = fullList.get(j);
			if (commandParts[1].equals(TASKTYPE_DONE)) {
				if (currentTask.getTaskIsDone()) {
					continue;
				}
			} else if (commandParts[1].equals(TASKTYPE_OVERDUE)) {
				if (currentTask.getTaskIsOverdue()) {
					continue;
				}
			}
			FileStorage.writeObjectAsString(currentTask);
		}
	}

	public ArrayList<Task> executeSort(ArrayList<Task> list) {
		Sort.sortByDateThenPriority(list);
		return list;
	}

		FileStorage.clear();
		for (int j = 0; j < fullList.size(); j++) {
			FileStorage.writeObjectAsString(fullList.get(j));
		}
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
}
