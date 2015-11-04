package LemonBuddy.view;

import java.util.ArrayList;

import LemonBuddy.CommandController;
import LemonBuddy.CommandExecutor;
import LemonBuddy.Task;
import LemonBuddy.lemonGUI;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class LemonGUIController {
	
	private String input = "";
	
	private static Task task;
	static ArrayList<Task> tasks;
	private static String commandType;
	private static String[] listType = {"", "all"};
	
	private static String timeLineStandard = "________________________________________________";
	
	private lemonGUI mainApp;
	
	@FXML
	private TextFlow timeLine;
	
	@FXML
	private TextFlow listView;
	
	@FXML
	private TextArea mainConsole;
	
	@FXML
	private TextField inputField;
	
	@FXML
    private void initialize() throws Exception {
        generateSideList();
        mainConsole.setText("Welcome to LemonBuddy!!!!");
    }
	
	@FXML
	private void getInput() {
		input = inputField.getText();
		CommandController.processCommand(input);
		inputField.clear();
	}
	
	@FXML
	private void generateSideList() throws Exception {
		listView.getChildren().clear();
		CommandExecutor.executeList(listType);
		Text t;
		if (listType[1].equals("all")) {
			t = new Text("All Tasks\n\n");
			t.setStyle("-fx-font: 18 arial;");
			listView.getChildren().add(t);
		}
		if (listType[1].equals("floating")) {
			t = new Text("Floating Tasks\n\n");
			t.setStyle("-fx-font: 18 arial;");
			listView.getChildren().add(t);
		}
		if (listType[1].equals("deadline")) {
			t = new Text("Deadlines\n\n");
			t.setStyle("-fx-font: 18 arial;");
			listView.getChildren().add(t);
		}
		if (listType[1].equals("event")) {
			t = new Text("Events\n\n");
			t.setStyle("-fx-font: 18 arial;");
			listView.getChildren().add(t);
		}

		for (int counter = 1; counter < tasks.size() + 1; counter++) {
			Task temp = tasks.get(counter - 1);
			t = new Text(getTask(temp, counter));
			if (temp.getTaskPriority().equals("high")) {
				t.setFill(Color.RED);
			}
			listView.getChildren().add(t);
		}
		
	}
	
	private String getTask(Task temp, int id) throws Exception {
		String toList = "";
		toList = toList + id + ". " + temp.getTaskName() + "\n";
		return toList;
	}
	
	@FXML
	void onEnter(KeyEvent event) throws Exception {
		if (event.getCode() == KeyCode.ENTER) {
			getInput();
			displayMain();
			generateSideList();
		}
	}
	

	private String displayTask(Task task) {
		String display = "";
		display = display + "Name: " + task.getTaskName() + "\n";
		display = display + "Type: " + task.getTaskType()  + "\n";
		if (!task.getTaskPriority().isEmpty()) {
			display = display + "Priority: " + task.getTaskPriority() + "\n";
		}
<<<<<<< HEAD
		if (!task.getTaskDescription().isEmpty()) {
			display = display + "Description: " + task.getTaskDescription() + "\n";
		}
		
		//if (!task.getTaskStartDate().isEmpty()) {
		if (!(task.getTaskStartDate()==-1)) {			
			display = display + "Start date " + task.getTaskStartDate() + "\n";
		}
=======
	}

	public static void executeNavigate(String[] commandParts) throws ClassNotFoundException, IOException {
		// get days related to day
		LemonGUIController.setTimeLineDate(commandParts[1]);
		int timelineDate = Integer.valueOf(commandParts[1]);
		
		int dayTimeLine = timelineDate / 10000;
		int monthTimeLine = (timelineDate / 100) % 100;
		int yearTimeLine = timelineDate % 100;
		int comparedTimeline = dayTimeLine + monthTimeLine * 100 + yearTimeLine * 10000;
		
		ArrayList<Task> deadlineTasks = new ArrayList<Task>();
		ArrayList<Task> eventTasks = new ArrayList<Task>();
		ArrayList<Task> fullList = new ArrayList<Task>();
		Task currentTask;

		fullList = FileStorage.readStringAsObject(path);
		for (int j = 0; j < fullList.size(); j++) {
			currentTask = fullList.get(j);
			int dateStart = currentTask.getTaskStartDate();
			int dateEnd = currentTask.getTaskEndDate();

			int dayStart = dateStart / 10000;
			int monthStart = (dateStart / 100) % 100;
			int yearStart = dateStart % 100;
			int comparedStartDate = dayStart + monthStart * 100 + yearStart * 10000;

			int dayEnd = dateEnd / 10000;
			int monthEnd = (dateEnd / 100) % 100;
			int yearEnd = dateEnd % 100;
			int comparedEndDate = dayEnd + monthEnd * 100 + yearEnd * 10000;
			if (currentTask.getTaskType().equals("deadline")) {
				if (currentTask.getTaskEndDate() == timelineDate) {
					deadlineTasks.add(currentTask);
					System.out.println("deadline: " + currentTask);
				}
			}
			if (currentTask.getTaskType().equals("event")) {
				if (comparedEndDate >= comparedTimeline && comparedStartDate <= comparedTimeline) {
					eventTasks.add(currentTask);
					System.out.println("event fromto: " + currentTask);
				}
			}
		}
		
		LemonGUIController.setTimeLineDeadlineList(deadlineTasks);
		LemonGUIController.setTimeLineEventList(eventTasks);
>>>>>>> origin/master
		
		//if (!task.getTaskEndDate().isEmpty()) {
		if (!(task.getTaskEndDate()==-1)) {
			display = display + "End date " + task.getTaskEndDate() + "\n";
		}
		
		//if (!task.getTaskStartTime().isEmpty()) {
		if (!(task.getTaskStartTime()==-1)) {
			display = display + "Start time " + task.getTaskStartTime() + "\n";
		}
		
		//if (!task.getTaskEndTime().isEmpty()) {
		if (!(task.getTaskEndTime()==-1)) {
			display = display + "End time " + task.getTaskEndTime() + "\n";
		}

		return display;
	}
<<<<<<< HEAD
	
	public void displayMain() {
		if (task.getTaskName().equals("")){
			return;
=======

	public void executeUpdate() throws IOException, ClassNotFoundException {
		ArrayList<Task> array = FileStorage.readStringAsObject(path);
		assert(array != null) : "unable to read from specified path";

		String currentDate = parser.getCurrentDate();
		System.out.println(currentDate);

		int i = 0;
		boolean anythingRemoved = false;

		for (i = 0; i < array.size(); i++) {
			Task overdueTask = array.get(i);
			String endDate = parser.toSixDigit(array.get(i).getTaskEndDate());
			System.out.println(endDate);
			if (endDate.length() == 6) {
				if (parser.endDatePassed(currentDate, endDate) && (overdueTask.getTaskIsOverdue() == false)) {
					FileStorage.clear();
					overdueTask.setTaskIsOverdue();
					array.add(overdueTask);
					array.remove(i);
					anythingRemoved = true;
					i--;
				}
			}
>>>>>>> origin/master
		}
		if (commandType.equals("add")) {
			mainConsole.setText("Added\n" + displayTask(task));
			mainConsole.setStyle("-fx-background-color: green");
		}
		
		if (commandType.equals("delete")) {
			mainConsole.setText("Deleted\n" + displayTask(task));
			mainConsole.setStyle("-fx-background-color: red");
		}
		
		if (commandType.equals("edit")) {
			mainConsole.setText("Edited\n" + displayTask(task));
		}
		
		if (commandType.equals("display")) {
			mainConsole.setText("Displaying\n" + displayTask(task));
		}
		
		if (commandType.equals("list")) {
			mainConsole.setText("Listing " + listType[1] + " tasks.");
		}
		
		commandType = "";
		task = new Task();
		
	}
<<<<<<< HEAD
	
    public static void setList(ArrayList<Task> list) {
    	tasks = list;
    }
    
	public static void setCommand(String command) {
		commandType = command;
=======

	public void executeDisplay(String[] commandParts) throws Exception {
		ArrayList<Task> fullList = new ArrayList<Task>();
		LemonGUIController.setCommand(commandParts[0]);
		Task currentTask;

		int id = Integer.parseInt(commandParts[1]);
		fullList = FileStorage.readStringAsObject(path);
		currentTask = fullList.get(id - 1);
		LemonGUIController.setTask(currentTask);
	}

	public void parseInvalidCommand(String command) {
		// GUIConsole.displayErrorMessage(command);
	}

	public void executeUndo() throws IOException, Exception {
		// System.out.println(FileStorage.readStringAsString(path));
		undoneStates.push(FileStorage.readStringAsString(path));
		FileStorage.clear();
		FileStorage.writeStringAsString(lastStates.pop());
		LemonGUIController.setCommand("undo");
>>>>>>> f01c5874dd0710235d2995f5c3606cff4b381346
	}

<<<<<<< HEAD
	public static void setTask(Task newTask) {
		task = newTask;
=======
	public void executeDone(String[] commandParts) throws Exception, IOException {
		ArrayList<Task> fullList = FileStorage.readStringAsObject(path);
		FileStorage.clear();
		int taskDoneIndex = writeUntilTaskIndex(commandParts, fullList);
		Task taskDone = fullList.get(taskDoneIndex);
		taskDone.setTaskIsDone();
		FileStorage.writeObjectAsString(taskDone);
		writeRestOfList(fullList, taskDoneIndex);

>>>>>>> origin/master
	}
	
	public void executeSortFloating () throws ClassNotFoundException, IOException {
		ArrayList<Task> fullList = FileStorage.readStringAsObject(path);
		System.out.println("Hello");
			int sizeToCheck = fullList.size();
			for (int i = 0; i < sizeToCheck; i++) {
				Task currentTask = fullList.get(i);
				System.out.println("tasktype: " + currentTask.getTaskType());
				if (currentTask.getTaskType().equals("floating")) {
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
	}
	
	public void executeSort () throws ClassNotFoundException, IOException {
		ArrayList<Task> fullList = FileStorage.readStringAsObject(path);
		for (int i = 0; i < fullList.size() - 1; i++) {
			Task currentTask = fullList.get(i);
				for (int j = i + 1; j < fullList.size(); j++) {
					Task nextTask = fullList.get(j);
					if (currentTask.getTaskType().equals("deadline")) {
						String currentDate = parser.toSixDigit(currentTask.getTaskEndDate());
						if (nextTask.getTaskType().equals("deadline")) {
							String nextDate = parser.toSixDigit(nextTask.getTaskEndDate());
							if (parser.endDatePassed(currentDate, nextDate)) {
								//fullList.add(j, fullList.remove(i));
								fullList.add(i, nextTask);
								fullList.add(j+1, currentTask);
								fullList.remove(i+1);
								fullList.remove(j+1);
							} else if (currentDate.equals(nextDate)) {
								String currentPriority = currentTask.getTaskPriority();
								String nextPriority = nextTask.getTaskPriority();
								if (parser.nextPriorityIsHigher(currentPriority, nextPriority)) {
									//fullList.add(j, fullList.remove(i));
									fullList.add(i, nextTask);
									fullList.add(j+1, currentTask);
									fullList.remove(i+1);
									fullList.remove(j+1);
								}
							}
						} else if (nextTask.getTaskType().equals("event")) {
							String nextDate = parser.toSixDigit(nextTask.getTaskStartDate());
							if (parser.endDatePassed(currentDate, nextDate)) {
								//fullList.add(j, fullList.remove(i));
								fullList.add(i, nextTask);
								fullList.add(j+1, currentTask);
								fullList.remove(i+1);
								fullList.remove(j+1);
							} else if (currentDate.equals(nextDate)) {
								String currentPriority = currentTask.getTaskPriority();
								String nextPriority = nextTask.getTaskPriority();
								if (parser.nextPriorityIsHigher(currentPriority, nextPriority)) {
									//fullList.add(j, fullList.remove(i));
									fullList.add(i, nextTask);
									fullList.add(j+1, currentTask);
									fullList.remove(i+1);
									fullList.remove(j+1);
								}
							}
						}
					} else if (currentTask.getTaskType().equals("event")) {
						String currentDate = parser.toSixDigit(currentTask.getTaskStartDate());
						if (nextTask.getTaskType().equals("event")) {
							String nextDate = parser.toSixDigit(nextTask.getTaskStartDate());
							if (parser.endDatePassed(currentDate, nextDate)) {
								//fullList.add(j, fullList.remove(i));
								fullList.add(i, nextTask);
								fullList.add(j+1, currentTask);
								fullList.remove(i+1);
								fullList.remove(j+1);
							} else if (currentDate.equals(nextDate)) {
								String currentPriority = currentTask.getTaskPriority();
								String nextPriority = nextTask.getTaskPriority();
								if (parser.nextPriorityIsHigher(currentPriority, nextPriority)) {
									//fullList.add(j, fullList.remove(i));
									fullList.add(i, nextTask);
									fullList.add(j+1, currentTask);
									fullList.remove(i+1);
									fullList.remove(j+1);
								}
							}
						} else if (nextTask.getTaskType().equals("deadline")) {
							String nextDate = parser.toSixDigit(nextTask.getTaskEndDate());
							if (parser.endDatePassed(currentDate, nextDate)) {
								//fullList.add(j, fullList.remove(i));
								fullList.add(i, nextTask);
								fullList.add(j+1, currentTask);
								fullList.remove(i+1);
								fullList.remove(j+1);
							} else if (currentDate.equals(nextDate)) {
								String currentPriority = currentTask.getTaskPriority();
								String nextPriority = nextTask.getTaskPriority();
								if (parser.nextPriorityIsHigher(currentPriority, nextPriority)) {
									//fullList.add(j, fullList.remove(i));
									fullList.add(i, nextTask);
									fullList.add(j+1, currentTask);
									fullList.remove(i+1);
									fullList.remove(j+1);
								}
							}
						}
					}
				}
			}
			
			FileStorage.clear();
			for (int j = 0; j < fullList.size(); j++) {
				FileStorage.writeObjectAsString(fullList.get(j));
			}
	}

	public void setMainApp(lemonGUI mainApp) {
        this.mainApp = mainApp;
    }

	public static void setListType(String type) {
		listType[1] = type;
		
	}
<<<<<<< HEAD
	
	public void createTimeLine() {
		Text space = new Text("\n");
		Text timeline = new Text(timeLineStandard);
		timeline.setTextAlignment(TextAlignment.CENTER);
		timeLine.getChildren().add(space);
		timeLine.getChildren().add(space);
		timeLine.getChildren().add(timeline);
=======

	public void executeRedo() throws IOException, Exception {
		if (!undoneStates.isEmpty()) {
			lastStates.push(FileStorage.readStringAsString(path));
			FileStorage.clear();
			FileStorage.writeStringAsString(undoneStates.pop());
			LemonGUIController.setCommand("redo");
		} else {
			System.out.println("Already at current");
			LemonGUIController.setCommand("redo maxed");
		}
>>>>>>> f01c5874dd0710235d2995f5c3606cff4b381346
	}
}
