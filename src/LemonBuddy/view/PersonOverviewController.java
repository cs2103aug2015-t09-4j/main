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

public class PersonOverviewController {
	
	private String input = "";
	
	private static Task task;
	private static int type;
	
	@FXML
	private TextArea listView;
	
	@FXML
	private TextArea mainConsole;
	
	@FXML
	private TextField inputField;
	
	@FXML
    private void initialize() throws Exception {
        listView.setText(generateList());
        mainConsole.setText("Welcome to LemonBuddy!!!!");
    }
	
	@FXML
	private void getInput() {
		input = inputField.getText();
		CommandController.processCommand(input);
		inputField.setText("");
	}
	
	private String generateList() throws Exception {
		ArrayList<Task> tasks = CommandExecutor.list();
		String toList = "";
		int a = 1;
		while (a - 1 < tasks.size()) {
			toList = toList + a + ". " + tasks.get(a - 1).getTaskName() + "\n";
			a++;
		}
		System.out.println(tasks);
		return toList;
	}
	
	@FXML
	private void onEnter(KeyEvent event) throws Exception {
		if (event.getCode() == KeyCode.ENTER) {
			getInput();
			displayMain();
			listView.setText(generateList());
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
		//if (task.getTaskType().equals("deadline")) {
			//display = display + "By " + task.getTaskEndDate();
		//}
		
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
		
	}
	
	public static void taskSelected(Task sTask, int num) {
		task = sTask;
		type = num;
	}

}
