package LemonBuddy;

import java.io.Serializable;

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
	// for recurring
	private String recurType;
	private String recurStartDate;
	private String recurEndDate;
	// private String desc= "";
	/*
	 * public void Task(){ 
	 * this.taskName = ""; 
	 * this.taskType = "";
	 * this.taskStartDate = ""; 
	 * this.taskEndDate = ""; 
	 * this.taskPriority = "";
	 * this.taskDescription = ""; 
	 * this.taskStartTime = ""; 
	 * this.taskEndTime =
	 * ""; // 
	 * for recurring this.recurType = ""; 
	 * this.recurStartDate = "";
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
		temp = temp + "taskIsNewest:" + taskIsNewest + ";";
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

	//initialiser
	public Task(){
		taskName = "";
		taskType = "";
		taskStartDate = "-1";
		taskEndDate = "-1";
		taskPriority = "";
		taskDescription = "";
		taskIsDone = false;
		taskIsOverdue = false;
		taskIsNewest = false;
		taskStartTime = "-1";
		taskEndTime = "-1";
		// for recurring
		recurType = "";
		recurStartDate = "";
		recurEndDate = "";
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
