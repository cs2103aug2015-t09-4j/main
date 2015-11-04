package LemonBuddy;

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;

public class MainDisplayTask implements Serializable {
	private SimpleStringProperty taskId;
	private SimpleStringProperty taskName;
	private SimpleStringProperty taskStartDate;
	private SimpleStringProperty taskEndDate;
	private SimpleStringProperty taskStartTime;
	private SimpleStringProperty taskEndTime;
	private SimpleStringProperty taskPriority;
	private SimpleStringProperty taskDescription;
	private boolean mostRecent;
	
	public void setTaskId(String id) {
		this.taskId = new SimpleStringProperty(id);
	}

	public void setTaskName(String name) {
		this.taskName = new SimpleStringProperty(name);
	}

	public void setTaskStartDate(String startDate) {
		this.taskStartDate = new SimpleStringProperty(startDate);
	}

	public void setTaskEndDate(String endDate) {
		this.taskEndDate = new SimpleStringProperty(endDate);
	}

	public void setTaskStartTime(String startTime) {
		this.taskStartTime = new SimpleStringProperty(startTime);
	}

	public void setTaskEndTime(String endTime) {
		this.taskEndTime = new SimpleStringProperty(endTime);
	}

	public void setTaskPriority(String priority) {
		this.taskPriority = new SimpleStringProperty(priority);
	}

	public void setTaskDescription(String description) {
		this.taskDescription = new SimpleStringProperty(description);
	}
	
	public SimpleStringProperty getTaskId() {
		return this.taskId;
	}

	public SimpleStringProperty getTaskName() {
		return this.taskName;
	}

	public SimpleStringProperty getTaskStartDate() {
		return this.taskStartDate;
	}

	public SimpleStringProperty getTaskEndDate() {
		return this.taskEndDate;
	}

	public SimpleStringProperty getTaskStartTime() {
		return this.taskStartTime;
	}

	public SimpleStringProperty getTaskEndTime() {
		return this.taskEndTime;
	}

	public SimpleStringProperty getTaskPriority() {
		return this.taskPriority;
	}

	public SimpleStringProperty getTaskDescription() {
		return this.taskDescription;
	}

}
