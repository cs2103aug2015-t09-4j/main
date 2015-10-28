package LemonBuddy.view;

<<<<<<< HEAD
import java.io.Serializable;

public class LemonGUIController implements Serializable {
	private String taskName = "";
	private String taskType = "";
	private int taskStartDate = -1;
	private int taskEndDate = -1;
	private String taskPriority = "";
	private String taskDescription = "";
	private boolean taskIsDone = false;
	private boolean taskIsOverdue = false;
	private int taskStartTime = -1;
	private int taskEndTime = -1;
	// for recurring
	private String recurType = "";
	private String recurStartDate = "";
	private String recurEndDate = "";
	// private String desc= "";
	/*
	 * public void Task(){ this.taskName = ""; this.taskType = "";
	 * this.taskStartDate = ""; this.taskEndDate = ""; this.taskPriority = "";
	 * this.taskDescription = ""; this.taskStartTime = ""; this.taskEndTime =
	 * ""; // for recurring this.recurType = ""; this.recurStartDate = "";
	 * this.recurEndDate = "";
	 * 
	 * // private String desc= ""; }
	 */

	public String toString() {
		String temp = "";
		temp = temp + "taskname:" + taskName + ";";
		temp = temp + "tasktype:" + taskType + ";";
		temp = temp + "taskIsDone:" + taskIsDone + ";";
		temp = temp + "taskIsOverdue:" + taskIsOverdue + ";";
		temp = temp + "taskStartDate:" + taskStartDate + ";";
		temp = temp + "taskEndDate:" + taskEndDate + ";";
		temp = temp + "taskPriority:" + taskPriority + ";";
		temp = temp + "taskDescription:" + taskDescription + ";";
		temp = temp + "taskStartTime:" + taskStartTime + ";";
		temp = temp + "taskEndTime:" + taskEndTime + ";";
		temp = temp + "recurType:" + recurType + ";";
		temp = temp + "recurStartDate:" + recurStartDate + ";";
		temp = temp + "recurEndDate:" + recurEndDate + ";";
		temp = temp + "\n";
		return temp;
	}

	public LemonGUIController merge(LemonGUIController initialTask) {
		this.taskName = initialTask.getTaskName();
	/*	
		if((this.taskStartDate.equals(""))&&(this.taskEndDate.equals(""))
				&&(this.taskStartTime.equals(""))&&(this.taskEndTime.equals(""))){
			this.taskType = initialTask.getTaskType();
		}
		
		if ((this.taskStartDate.equals(""))&&(this.taskEndDate.equals(""))) {
			this.taskStartDate= initialTask.getTaskStartDate();
			this.taskEndDate= initialTask.getTaskEndDate();
		}
		if ((this.taskStartTime.equals(""))&&(this.taskEndTime.equals(""))) {
			this.taskStartTime= initialTask.getTaskStartTime();
			this.taskEndTime= initialTask.getTaskEndTime();
		}
		if(this.taskPriority.equals("")){
			this.taskPriority = initialTask.getTaskPriority();
		}
		if(this.taskDescription.equals("")){
			this.taskDescription = initialTask.getTaskDescription();
		}*/
		return this;
	}
=======
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
>>>>>>> origin/master
	
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	
	public void setTaskIsDone(){
		this.taskIsDone = true;
	}
	
	public void setTaskIsOverdue(){
		this.taskIsOverdue = true;
	}
	
<<<<<<< HEAD
	public void setTaskStartDate(int taskStartDate){
		this.taskStartDate = taskStartDate;
	}
=======
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
>>>>>>> origin/master
	
	public void setTaskStartDate(String taskStartDate) {
		this.taskStartDate = Integer.valueOf(taskStartDate);
	}
	
	public void setTaskEndDate(int taskEndDate){
		this.taskEndDate = taskEndDate;
	}
	public void setTaskEndDate(String taskEndDate) {
		this.taskEndDate = Integer.valueOf(taskEndDate);
	}
		
	public void setTaskStartTime(int taskStartTime) {
		this.taskStartTime = taskStartTime;
	}
	
	public void setTaskStartTime(String taskStartTime) {
		this.taskStartTime = Integer.valueOf(taskStartTime);
	}
	
<<<<<<< HEAD
	public void setTaskEndTime(int taskEndTime) {
		this.taskEndTime = taskEndTime;
=======
	@FXML
	void onEnter(KeyEvent event) throws Exception {
		if (event.getCode() == KeyCode.ENTER) {
			getInput();
			displayMain();
			generateSideList();
			createTimeLine();
		}
>>>>>>> origin/master
	}
	
	public void setTaskEndTime(String taskEndTime) {
		this.taskEndTime = Integer.valueOf(taskEndTime);
	}

	public void setTaskPriority(String taskPriority) {
		this.taskPriority = taskPriority;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}


	public void setRecurType(String recurType) {
		this.recurType = recurType;
	}

	public void setRecurStartDate(String recurStartDate) {
		this.recurStartDate = recurStartDate;
	}

	public void setRecurEndDate(String recurEndDate) {
		this.recurEndDate = recurEndDate;
	}
	/*
	 * public void addDesc(String desc){ this.desc += desc; }
	 */

	public String getTaskName() {
		StringBuffer buffer = new StringBuffer(this.taskName);
		return buffer.toString();
	}

	public String getTaskType() {
		StringBuffer buffer = new StringBuffer(this.taskType);
		return buffer.toString();
	}
	public Boolean getTaskIsDone() {
		return this.taskIsDone;
		
	}public Boolean getTaskIsOverdue() {
		return this.taskIsOverdue;
	}
	public int getTaskStartDate() {
		return this.taskStartDate;
	}

	public int getTaskEndDate() {
		return this.taskEndDate;
	}
	
<<<<<<< HEAD
	public int getTaskStartTime() {
		return this.taskStartTime;
	}

	public int getTaskEndTime() {
		return this.taskEndTime;
	}

	public String getTaskPriority() {
		StringBuffer buffer = new StringBuffer(this.taskPriority);
		return buffer.toString();
	}

	public String getTaskDescription() {
		StringBuffer buffer = new StringBuffer(this.taskDescription);
		return buffer.toString();
	}



	public String getRecurType(String recurType) {
		StringBuffer buffer = new StringBuffer(this.recurType);
		return buffer.toString();
	}

	public Integer getRecurStartDate(Integer recurStartDate) {
		StringBuffer buffer = new StringBuffer(this.recurStartDate);
		return Integer.valueOf(buffer.toString());
	}

	public Integer getRecurEndDate(Integer recurEndDate) {
		StringBuffer buffer = new StringBuffer(this.recurEndDate);
		return Integer.valueOf(buffer.toString());
	}

}
=======
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
>>>>>>> origin/master
