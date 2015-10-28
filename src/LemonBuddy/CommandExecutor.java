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
		if (!task.getTaskDescription().isEmpty()) {
			display = display + "Description: " + task.getTaskDescription() + "\n";
		}
		
		//if (!task.getTaskStartDate().isEmpty()) {
		if (!(task.getTaskStartDate()==-1)) {			
			display = display + "Start date " + task.getTaskStartDate() + "\n";
		}
		
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
	
	public void displayMain() {
		if (task.getTaskName().equals("")){
			return;
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
	
    public static void setList(ArrayList<Task> list) {
    	tasks = list;
    }
    
	public static void setCommand(String command) {
		commandType = command;
	}

	public static void setTask(Task newTask) {
		task = newTask;
	}

	public void setMainApp(lemonGUI mainApp) {
        this.mainApp = mainApp;
    }

	public static void setListType(String type) {
		listType[1] = type;
		
	}
	
	public void createTimeLine() {
		Text space = new Text("\n");
		Text timeline = new Text(timeLineStandard);
		timeline.setTextAlignment(TextAlignment.CENTER);
		timeLine.getChildren().add(space);
		timeLine.getChildren().add(space);
		timeLine.getChildren().add(timeline);
	}
}