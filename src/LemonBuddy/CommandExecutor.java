
//@@author A0124209N
package LemonBuddy;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandExecutor extends FileStorage {
	private static final String TASKTYPE_EVENT = "event";
	private static final String TASKTYPE_DEADLINE = "deadline";
	private static final String TASKTYPE_overdue = "overdue";
	private static final String TASKTYPE_done = "done";
	private static final String TASKTYPE_FLOATING = "floating";
	private static final java.util.logging.Logger logger = Logger.getLogger("CommandExecutor");

	private static String path;
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
		logger.log(Level.INFO, "Executing Add");
		Task newTask = parser.parseTask(commandParts);
		newTask.setTaskIsNewest();
		addTaskToList(newTask);
		selectedTask = newTask;
		writeToFile();
	}

	public void executeEdit(String[] commandParts) throws Exception {
		logger.log(Level.INFO, "Executing edit");
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

	public void executeSearch(String[] commandParts) throws ClassNotFoundException, IOException {
		searchResults.clear();
		listType = "search";
		int phraseSize = commandParts.length - 1;
		String searchKeyword = commandParts[1];
		if (phraseSize > 1) {
			for (int i = 2; i < phraseSize + 1; i++) {
				searchKeyword += " ";
				searchKeyword += commandParts[i];
			}
		}

		searchKeyword.toLowerCase();

		for (int j = 0; j < allTasks.size(); j++) {
			Task searchedTask = allTasks.get(j);
			if (searchedTask.getTaskName().toLowerCase().contains(searchKeyword)
					|| searchedTask.getTaskDescription().toLowerCase().contains(searchKeyword)) {
				searchResults.add(searchedTask);
			}
		}
		;
	}

	public void executeDelete(String[] commandParts) throws Exception {
		logger.log(Level.INFO, "Executing Delete");
		int deleteId = Integer.valueOf(commandParts[1]);
		deleteTaskFromList(deleteId);
		writeToFile();
	}

	public void executeList(String[] commandParts) throws Exception {
		listType = commandParts[1];
	}

	public void executeUndo() throws IOException, Exception {
		if (lastStates.isEmpty()) {
			throw new Exception("Already at last undo");
		}
		undoneStates.push(FileStorage.readStringAsString(path));
		FileStorage.clear();
		FileStorage.writeStringAsString(lastStates.pop());
		updateLists();
	}

	public void executeDone(String[] commandParts) throws Exception, IOException {
		int deleteID = Integer.valueOf(commandParts[1]);
		Task doneTask = deleteTaskFromList(deleteID);
		doneTask.setTaskType("done");
		doneTasks.add(doneTask);
		writeToFile();
	}

	public void executeView(String[] commandParts) throws ClassNotFoundException, IOException, ParseException {
		logger.log(Level.INFO, "Executing View");
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

	//@@author A0127147H
	public void executeRedo() throws IOException, Exception {
		if (!undoneStates.isEmpty()) {
			lastStates.push(FileStorage.readStringAsString(path));
			FileStorage.clear();
			FileStorage.writeStringAsString(undoneStates.pop());
		} else {
			throw new Exception("Already at current");
		}
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

	public void addTaskToList(Task newTask) {
		switch (newTask.getTaskType()) {
		case TASKTYPE_FLOATING:
			floatingTasks.add(newTask);
			listType = "floating";
			break;
		case TASKTYPE_DEADLINE:
			this.fillUpTime(newTask);
			deadlineTasks.add(newTask);
			date[1] = newTask.getTaskEndDate();
			listType = "date";
			break;
		case TASKTYPE_EVENT:
			this.fillUpTime(newTask);
			eventTasks.add(newTask);
			date[1] = newTask.getTaskStartDate();
			listType = "date";
			break;
		}
	}

	public Task deleteTaskFromList(int deleteId) throws Exception {
		Task deletedTask = new Task();
		switch (listType) {
		case TASKTYPE_FLOATING:
			deletedTask = removeTaskFromFloatingList(deleteId);
			break;
		case TASKTYPE_DEADLINE:
			if (deleteId > overdueTasks.size()) {
				int temp = deleteId - overdueTasks.size();
				deletedTask = removeTaskFromDeadlineList(temp);
			} else {
				deletedTask = removeTaskFromOverdueList(deleteId);
			}
			break;
		case TASKTYPE_EVENT:
			deletedTask = removeTaskFromEventList(deleteId);
			break;
		case TASKTYPE_overdue:
			deletedTask = removeTaskFromOverdueList(deleteId);
			break;
		case TASKTYPE_done:
			deletedTask = removeTaskFromDoneList(deleteId);
			break;
		}
		selectedTask = deletedTask;
		return deletedTask;
	}

	public Task removeTaskFromFloatingList(int deleteId) throws Exception {
		if (floatingTasks.size() < deleteId) {
			throw new Exception("Invalid Index");
		}
		Task taskToDelete = floatingTasks.remove(deleteId - 1);
		return taskToDelete;
	}

	public Task removeTaskFromDeadlineList(int deleteId) throws Exception {
		if (deadlineTasks.size() < deleteId) {
			throw new Exception("Invalid Index");
		}
		Task taskToDelete = deadlineTasks.remove(deleteId - 1);
		return taskToDelete;
	}

	public Task removeTaskFromEventList(int deleteId) throws Exception {
		if (eventTasks.size() < deleteId) {
			throw new Exception("Invalid Index");
		}
		Task taskToDelete = eventTasks.remove(deleteId - 1);
		return taskToDelete;
	}

	public Task removeTaskFromOverdueList(int deleteId) throws Exception {
		if (overdueTasks.size() < deleteId) {
			throw new Exception("Invalid Index");
		}
		Task taskToDelete = overdueTasks.remove(deleteId - 1);
		return taskToDelete;
	}

	public Task removeTaskFromDoneList(int deleteId) throws Exception {
		if (doneTasks.size() < deleteId) {
			throw new Exception("Invalid Index");
		}
		Task taskToDelete = doneTasks.remove(deleteId - 1);
		return taskToDelete;
	}

	/*
	 * get what user wants to view date e.g. navigate 010101
	 */

	public void executeUpdate() throws IOException, ClassNotFoundException {
		logger.log(Level.INFO, "Executing Update");
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
	
	public void fillUpTime(Task newTask) {
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

	public int roundDownTime(String time) {
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

	public int roundUpTime(String time) {
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
		if (listType.equals("overdue")) {
			executeView(date);
			temp.add(listToTimeline);
			temp.add(overdueTasks);
			updateLists();
			listType = "overdue";
			return temp;
		} else if (listType.equals("all")) {
			executeView(date);
			temp.add(listToTimeline);
			temp.add(allTasks);
			updateLists();
			listType = "all";
			return temp;
		} else if (listType.equals("floating")) {
			executeView(date);
			temp.add(listToTimeline);
			temp.add(floatingTasks);
			updateLists();
			listType = "floating";
			return temp;
		} else if (listType.equals("deadline")) {
			executeView(date);
			temp.add(listToTimeline);
			combinedList.addAll(overdueTasks);
			combinedList.addAll(deadlineTasks);
			temp.add(combinedList);
			listType = "deadline";
			updateLists();
			return temp;
		} else if (listType.equals("event")) {
			executeView(date);
			temp.add(listToTimeline);
			temp.add(eventTasks);
			updateLists();
			listType = "event";
			return temp;
		} else if (listType.equals("done")) {
			executeView(date);
			temp.add(listToTimeline);
			temp.add(doneTasks);
			updateLists();
			listType = "done";
			return temp;
		} else if (listType.equals("search")) {
			executeView(date);
			temp.add(listToTimeline);
			temp.add(searchResults);
			updateLists();
			listType = "search";
			return temp;
		} else {
			executeView(date);
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
	}

	public String passDate() {
		return date[1];
	}

	public String passListType() {
		return listType;
	}

	public Task passSelectedTask() {
		Task temp = selectedTask;
		selectedTask = null;
		return temp;
	}
}
