package LemonBuddy;

import java.io.Serializable;

public class Task implements Serializable {
	private String taskName = "";
	private String taskType = "";
	private int taskStartDate = -1;
	private int taskEndDate = -1;
	private String taskPriority = "";
	private String taskDescription = "";
	private boolean taskIsDone = false;
	private boolean taskIsOverdue = false;
	private boolean taskIsNewest = false;
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

	public Task merge(Task initialTask) {
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
	
	public void setTaskIsNewest(){
		this.taskIsNewest = true;
	}
	
	public void removeTaskIsNewest(){
		this.taskIsNewest = false;
	}
	
	public void setTaskStartDate(int taskStartDate){
		this.taskStartDate = taskStartDate;
	}
	
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
	
	public void setTaskEndTime(int taskEndTime) {
		this.taskEndTime = taskEndTime;
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
	
	public Boolean getTaskIsNewest() {
		return this.taskIsNewest;
	}
	
	public int getTaskStartDate() {
		return this.taskStartDate;
	}

	public int getTaskEndDate() {
		return this.taskEndDate;
	}
	
	public String getTaskStartDateString(){
		if (this.taskStartDate == -1) {
			return "-1";
		}
		String dateString = Integer.toString(this.taskStartDate);
		if(dateString.length()==5){
			dateString = "0" + dateString;
		}
		String newDate = dateString.substring(0,2) + "/" + dateString.substring(2,4) + "/" + dateString.substring(4,6);
		return newDate;
		
	}
	
	public String getTaskEndDateString(){
		if (this.taskEndDate == -1) {
			return "-1";
		}
		String dateString = Integer.toString(this.taskEndDate);
		if(dateString.length()==5){
			dateString = "0" + dateString;
		}
		String newDate = dateString.substring(0,2) + "/" + dateString.substring(2,4) + "/" + dateString.substring(4,6);
		return newDate;
	}
	
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
