package LemonBuddy;

import java.io.Serializable;

public class Task implements Serializable {
	private String taskName;
	private String taskType;
	private String taskStartDate;
	private String taskEndDate;
	private String taskStartTime;
	private String taskEndTime;
	private String taskPriority;
	private String taskDescription;
	private boolean taskIsNewest;
	
	public String toString() {
		String temp = "";
		temp = temp + "taskname:" + taskName + ";";
		temp = temp + "tasktype:" + taskType + ";";
		temp = temp + "taskIsNewest:" + taskIsNewest + ";";
		temp = temp + "taskStartDate:" + taskStartDate + ";";
		temp = temp + "taskEndDate:" + taskEndDate + ";";
		temp = temp + "taskStartTime:" + taskStartTime + ";";
		temp = temp + "taskEndTime:" + taskEndTime + ";";
		temp = temp + "taskPriority:" + taskPriority + ";";
		temp = temp + "taskDescription:" + taskDescription + ";";
		temp = temp + "\n";
		return temp;
	}

	//initialiser
	public Task(){
		taskName = "";
		taskType = "";
		taskStartDate = "-1";
		taskEndDate = "-1";
		taskPriority = "";
		taskDescription = "";
		taskIsNewest = false;
		taskStartTime = "-1";
		taskEndTime = "-1";
	}
	
	//TASK DATA MODIFIERS//
	
	public Task mergeTaskDetails(Task initialTask) {
		if (this.taskName.equals("")) {
			this.taskName = initialTask.getTaskName();
		}
		if (this.taskType.equals("floating")) {
			this.taskType = initialTask.getTaskType();
			this.taskStartDate = initialTask.getTaskStartDate();
			this.taskEndDate = initialTask.getTaskEndDate();
			this.taskStartTime = initialTask.getTaskStartTime();
			this.taskEndTime = initialTask.getTaskEndTime();
		}
		if (this.taskPriority.equals("")) {
			this.taskPriority = initialTask.getTaskPriority();
		}
		if (this.taskDescription.equals("")) {
			this.taskDescription = initialTask.getTaskDescription();
		}
		return this;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public void setTaskIsNewest() {
		this.taskIsNewest = true;
	}

	public void removeTaskIsNewest() {
		this.taskIsNewest = false;
	}

	public void setTaskStartDate(String taskStartDate) {
		this.taskStartDate = taskStartDate.replaceAll("[^\\d-]", "");
	}

	public void setTaskEndDate(String taskEndDate) {
		this.taskEndDate = taskEndDate.replaceAll("[^\\d-]", "");
	}

	public void setTaskStartTime(String taskStartTime) {
		this.taskStartTime = taskStartTime.replaceAll("[^\\d-]", "");
	}

	public void setTaskEndTime(String taskEndTime) {
		this.taskEndTime = taskEndTime.replaceAll("[^\\d-]", "");
	}

	public void setTaskPriority(String taskPriority) {
		this.taskPriority = taskPriority;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	/*
	 * public void addDesc(String desc){ this.desc += desc; }
	 */

	
	
	//RETRIEVERS//
	
	public String getTaskName() {
		StringBuffer buffer = new StringBuffer(this.taskName);
		return buffer.toString();
	}

	public String getTaskType() {
		StringBuffer buffer = new StringBuffer(this.taskType);
		return buffer.toString();
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

}
