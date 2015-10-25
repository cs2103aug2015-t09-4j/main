package LemonBuddy.view;

import java.util.ArrayList;

import LemonBuddy.CommandController;
import LemonBuddy.CommandExecutor;
import LemonBuddy.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class PersonOverviewController {
	
	private String input = "";
	
	private static Task task;
	private static int type;
	private ArrayList<Task> tasks;
	
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
		tasks = CommandExecutor.list();
		int a = 1;
		while (a < tasks.size() + 1) {
			Task temp = tasks.get(a - 1);
			Text t = new Text(getTask(a));
			if (temp.getTaskPriority().equals("high")) {
				t.setFill(Color.RED);
			}
			listView.getChildren().add(t);
			a++;
		}
		
	}
	
	private String getTask(int id) throws Exception {
		String toList = "";
		toList = toList + id + ". " + tasks.get(id - 1).getTaskName() + "\n";
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
		
		if (!task.getTaskStartDate().isEmpty()) {
			display = display + "Start date " + task.getTaskStartDate() + "\n";
		}
		
		if (!task.getTaskEndDate().isEmpty()) {
			display = display + "End date " + task.getTaskEndDate() + "\n";
		}
		
		if (!task.getTaskStartTime().isEmpty()) {
			display = display + "Start time " + task.getTaskStartTime() + "\n";
		}
		
		if (!task.getTaskEndTime().isEmpty()) {
			display = display + "End time " + task.getTaskEndTime() + "\n";
		}

		return display;
	}
	
	public void displayMain() {
		if (type == 1) {
			mainConsole.setText("Added\n" + displayTask(task));
		}
		
		if (type == 2) {
			mainConsole.setText("Deleted\n" + displayTask(task));
		}
		
		if (type == 3) {
			mainConsole.setText("Edited\n" + displayTask(task));
		}
		
		if (type == 4) {
			mainConsole.setText("Displaying\n" + displayTask(task));
		}
		
		type = 0;
		
	}
	
	public static void taskSelected(Task sTask, int num) {
		task = sTask;
		type = num;
	}

}
