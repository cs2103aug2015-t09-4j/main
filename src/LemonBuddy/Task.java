package LemonBuddy;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;

public class Task implements Serializable {
	private String taskName;
	private String taskType;
	private String taskStartDate;
	private String taskEndDate;
	private String taskPriority;
	private String taskDescription;
	private boolean taskIsDone;
	private boolean taskIsOverdue;
	private boolean taskIsNewest;
	private String taskStartTime;
	private String taskEndTime;

	private SimpleStringProperty displayId;
	private SimpleStringProperty displayName;
	private SimpleStringProperty displayStartDate;
	private SimpleStringProperty displayEndDate;
	private SimpleStringProperty displayStartTime;
	private SimpleStringProperty displayEndTime;
	private SimpleStringProperty displayPriority;
	private SimpleStringProperty displayDescription;
	private SimpleStringProperty[] displayTimmings = new SimpleStringProperty[48];
	
	public String toString() {
		String temp = "";
		temp = temp + "taskname:" + taskName + ";";
		temp = temp + "tasktype:" + taskType + ";";
		temp = temp + "taskIsDone:" + taskIsDone + ";";
		temp = temp + "taskIsOverdue:" + taskIsOverdue + ";";
		temp = temp + "taskIsNewest:" + taskIsNewest + ";";
		temp = temp + "taskStartDate:" + taskStartDate + ";";
		temp = temp + "taskEndDate:" + taskEndDate + ";";
		temp = temp + "taskPriority:" + taskPriority + ";";
		temp = temp + "taskDescription:" + taskDescription + ";";
		temp = temp + "taskStartTime:" + taskStartTime + ";";
		temp = temp + "taskEndTime:" + taskEndTime + ";";
		temp = temp + "\n";
		return temp;
	}

	//initializer
	public Task(){
		taskName = "";
		taskType = "Floating";
		taskStartDate = "-1";
		taskEndDate = "-1";
		taskPriority = "low";
		taskDescription = "";
		taskIsDone = false;
		taskIsOverdue = false;
		taskIsNewest = false;
		taskStartTime = "-1";
		taskEndTime = "-1";
	}
	
	//TASK DATA MODIFIERS//
	
	public Task mergeTaskDetails(Task initialTask) {
		if (this.taskName.equals("")) {
			this.taskName = initialTask.getTaskName();
		}
		if (this.taskType.equals("Floating")) {
			this.taskType = initialTask.getTaskType();
			this.taskStartDate = initialTask.getTaskStartDate();
			this.taskEndDate = initialTask.getTaskEndDate();
			this.taskStartTime = initialTask.getTaskStartTime();
			this.taskEndTime = initialTask.getTaskEndTime();
		}
		if (this.taskPriority.equals("low")) {
			this.taskPriority = initialTask.getTaskPriority();
		}
		if (this.taskDescription.equals("")) {
			this.taskDescription = initialTask.getTaskDescription();
		}
		return this;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
		this.displayName = new SimpleStringProperty(taskName);
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public void setTaskIsDone() {
		this.taskIsDone = true;
	}

	public void setTaskIsOverdue() {
		this.taskIsOverdue = true;
	}

	public void setTaskIsNewest() {
		this.taskIsNewest = true;
	}

	public void removeTaskIsNewest() {
		this.taskIsNewest = false;
	}

	public void setTaskStartDate(String taskStartDate) {
		this.taskStartDate = taskStartDate.replaceAll("[^\\d-]", "");
		this.displayStartDate = new SimpleStringProperty(this.taskStartDate);
	}

	public void setTaskEndDate(String taskEndDate) {
		this.taskEndDate = taskEndDate.replaceAll("[^\\d-]", "");
		this.displayEndDate = new SimpleStringProperty(this.taskEndDate);
	}

	public void setTaskStartTime(String taskStartTime) {
		this.taskStartTime = taskStartTime.replaceAll("[^\\d-]", "");
		this.displayStartTime = new SimpleStringProperty(this.taskStartTime);
	}

	public void setTaskEndTime(String taskEndTime) {
		this.taskEndTime = taskEndTime.replaceAll("[^\\d-]", "");
		this.displayEndTime = new SimpleStringProperty(this.taskEndTime);
	}

	public void setTaskPriority(String taskPriority) throws Exception {
		if(!(taskPriority.equals("low")||taskPriority.equals("medium")||taskPriority.equals("high"))){
			throw new Exception("Invalid priority type");
		}
		this.taskPriority = taskPriority;
		this.displayPriority = new SimpleStringProperty(this.taskPriority);
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
		this.displayDescription = new SimpleStringProperty(this.taskDescription);
	}
	
	public void setDisplayId(String index) {
		this.displayId = new SimpleStringProperty(index);
	}
	
	public void setEventTime(int time) {
		SimpleStringProperty temp = new SimpleStringProperty("1");
		this.displayTimmings[time] = temp;
	}
	
	public void setDeadlineTime(int time) {
		SimpleStringProperty temp = new SimpleStringProperty("0");
		this.displayTimmings[time] = temp;
	}

	//RETRIEVERS//
	
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

	}

	public Boolean getTaskIsOverdue() {
		return this.taskIsOverdue;
	}

	public Boolean getTaskIsNewest() {
		return this.taskIsNewest;
	}

	public String getTaskStartDate() {
		return this.taskStartDate;
	}

	public Boolean taskStartDateIsEmpty() {
		if (this.taskStartDate == "-1") {
			return true;
		} else {
			return false;
		}
	}

	public String getTaskEndDate() {
		return this.taskEndDate;
	}

	public Boolean taskEndDateIsEmpty() {
		if (this.taskEndDate == "-1") {
			return true;
		} else {
			return false;
		}
	}

	public String getTaskStartTime() {
		return this.taskStartTime;
	}
	
	public Boolean taskStartTimeIsEmpty(){
		if (this.taskStartTime == "-1") {
			return true;
		} else {
			return false;
		}
	}
	
	public String getTaskEndTime() {
		return this.taskEndTime;
	}

	public Boolean taskEndTimeIsEmpty(){
		if (this.taskEndTime == "-1") {
			return true;
		} else {
			return false;
		}
	}
	
	public String getTaskPriority() {
		StringBuffer buffer = new StringBuffer(this.taskPriority);
		return buffer.toString();
	}

	public String getTaskDescription() {
		StringBuffer buffer = new StringBuffer(this.taskDescription);
		return buffer.toString();
	}

	public SimpleStringProperty getDisplayName() {
		return this.displayName;
	}
	
	public SimpleStringProperty getDisplayStartDate() {
		return this.displayStartDate;
	}
	
	public SimpleStringProperty getDisplayEndDate() {
		return this.displayEndDate;
	}
	
	public SimpleStringProperty getDisplayStartTime() {
		return this.displayStartTime;
	}
	
	public SimpleStringProperty getDisplayEndTime() {
		return this.displayEndTime;
	}
	
	public SimpleStringProperty getDisplayPriority() {
		return this.displayPriority;
	}
	
	public SimpleStringProperty getDisplayDescription() {
		return this.displayDescription;
	}

	public SimpleStringProperty getDisplayId() {
		return this.displayId;
	}
	
	public SimpleStringProperty getTimmings(int num) {
		return this.displayTimmings[num];
	}

}
