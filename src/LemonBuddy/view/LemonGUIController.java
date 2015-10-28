package LemonBuddy.view;

import java.io.IOException;
import java.util.ArrayList;

import LemonBuddy.CommandController;
import LemonBuddy.CommandExecutor;
import LemonBuddy.Parser;
import LemonBuddy.Task;
import LemonBuddy.lemonGUI;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class LemonGUIController {
	
	private String input = "";
	
	private Parser parser;
	private static String date;
	private static Task task = new Task();
	static ArrayList<Task> tasks;
	private static String commandType;
	private static String[] listType = {"", "all"};
	private static String[] timeLineDate = {"", ""};
	
	private static String timeLineStandard = "________________________________________________\n";
	private static String timeLineEvents = "________________________________________________";

	private static ArrayList<Task> timeLineEventList;
	private static ArrayList<Task> timeLineDeadLineList;
	
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
		if (parser == null) {
			parser = new Parser();
		}
		date = parser.getCurrentDate();
		timeLineDate[1] = date;
        generateSideList();
        mainConsole.setText("Welcome to LemonBuddy!!!!");
        createTimeLine();
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
			createTimeLine();
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
	
	public static void setTimeLineDate(String date) {
		timeLineDate[1] = date;
	}
	
	public static void setTimeLineEventList(ArrayList<Task> list) {
		timeLineEventList = list;
	}
	
	public static void setTimeLineDeadlineList(ArrayList<Task> list) {
		timeLineDeadLineList = list;
	}
	
	public void createTimeLine() throws ClassNotFoundException, IOException {
		timeLine.getChildren().clear();
		CommandExecutor.executeNavigate(timeLineDate);
		Text space = new Text("\n");
		Text timeline = new Text(timeLineStandard);
		timeline.setFont(Font.font ("Arial", 15));
		Text num = new Text("99. ");
		num.setFont(Font.font ("Arial", 15));
		num.setFill(Color.TRANSPARENT);
		timeLine.setTextAlignment(TextAlignment.CENTER);
		timeLine.getChildren().add(space);
		timeLine.getChildren().add(num);
		timeLine.getChildren().add(timeline);
		int counter = 0;
		while (counter != timeLineEventList.size()) {
			if (counter < 10) {
				num = new Text("0");
				num.setFill(Color.BLACK);
				timeLine.getChildren().add(num);
			}
			num = new Text(Integer.toString(counter + 1) + ". ");
			num.setFill(Color.BLACK);
			timeLine.getChildren().add(num);
			Text[] temp = createTimeLineEvent(timeLineEventList.get(counter));
			timeline = temp[0];
			timeline.setFont(Font.font ("Arial", 15));
			timeline.setFill(Color.TRANSPARENT);
			timeLine.getChildren().add(timeline);
			timeline = temp[1];
			timeline.setFont(Font.font ("Arial", 15));
			timeline.setFill(Color.BLACK);
			timeLine.getChildren().add(timeline);
			timeline = temp[2];
			timeline.setFont(Font.font ("Arial", 15));
			timeline.setFill(Color.TRANSPARENT);
			timeLine.getChildren().add(timeline);
			counter++;
		}
	}
	
	public Text[] createTimeLineEvent(Task task) {
		Text before = new Text();
		Text event = new Text();
		Text after = new Text();
		Text[] ans = new Text[3];
		int chosenDate = Integer.parseInt(timeLineDate[1]);
		int startDate = task.getTaskStartDate();
		int endDate = task.getTaskEndDate();
		if (startDate != chosenDate && endDate!= chosenDate) {
			event = new Text(timeLineEvents);
			System.out.println("hi" + timeLineEvents);
			before = new Text("");
			after = new Text("\n");
			ans[0] = before;
			ans[1] = event;
			ans[2] = after;
			return ans;
		}
		
		int startTime = roundDown(task.getTaskStartTime());
		int endTime = roundUp(task.getTaskEndTime());
		
		String finished = "";
		String add = "_";
		int time = 0;
		for (int counter = 0; counter < 48; counter ++) {
			if (time == startTime && startDate == chosenDate) {
				before = new Text(finished);
				System.out.println("before " + finished);
				finished = "";
			}
			if (time == endTime && endDate == chosenDate) {
				event = new Text(finished);
				System.out.println("duration " + finished);
				finished = "";
			}
			if(time % 100 == 0) {
				time = time + 30;
			} else {
				time = time - 30 + 100;
			}
			finished = finished + add;
		}
		after = new Text(finished + "\n");
		System.out.println(time);
		System.out.println("after " + finished);
		ans[0] = before;
		ans[1] = event;
		ans[2] = after;
		return ans;
	}
	
	private int roundDown(int time) {
		int ans = time;
		int temp = time;
		temp = temp % 100;
		if (temp == 0) {
			return ans;
		}
		if (temp > 30) {
			ans = ans +30 - temp;
		}
		if (temp < 30) {
			ans = ans - temp;
		}
		return ans;
	}
	
	private int roundUp(int time) {
		int ans = time;
		int temp = time;
		temp = temp % 100;
		if (temp == 0) {
			return ans;
		}
		if (temp > 30) {
			ans = ans + 100 - temp;
		}
		if (temp < 30) {
			ans = ans - temp + 30;
		}
		return ans;
	}
	
}
