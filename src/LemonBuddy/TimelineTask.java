package LemonBuddy;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;

public class TimelineTask implements Serializable {
	private SimpleStringProperty taskName;
	private String taskType = "";
	private String taskStartDate = "";
	private String taskEndDate = "";
	
	private ArrayList<SimpleStringProperty> timmings = new ArrayList<SimpleStringProperty>(48);

	public void setTaskName(String taskName) {
		this.taskName = new SimpleStringProperty(taskName);
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	
	public SimpleStringProperty getTaskName() {
		return this.taskName;
	}
	
	public void setTaskStartDate(String taskStartDate){
		this.taskStartDate = taskStartDate;
	}
	
	public void setTaskEndDate(String taskEndDate){
		this.taskEndDate = taskEndDate;
	}
	
	public void setTime(int time) {
		SimpleStringProperty temp = new SimpleStringProperty("1");
		this.timmings.add(time, temp);
	}
	
	public void setDeadline(int time) {
		SimpleStringProperty temp = new SimpleStringProperty("0");
		this.timmings.add(time, temp);
	}
	
	public void missTime(int time) {
		SimpleStringProperty temp = new SimpleStringProperty("-1");
		this.timmings.add(time, temp);
	}

	public SimpleStringProperty getTimmings(int num) {
		return this.timmings.get(num);
	}
	
}
