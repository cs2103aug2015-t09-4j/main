package LemonBuddy;

import java.io.Serializable;

public class Task implements Serializable {
	private String taskName = "";
	private String taskType = "";
	private String taskStartDate = "";
	private String taskEndDate = "";
	private String taskPriority = "";
	private String taskDescription = "";
	private String taskStartTime = "";
	private String taskEndTime = "";
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
		
		if (!this.taskStartDate.equals("")) {
			initialTask.setTaskStartDate(this.taskStartDate);
			initialTask.setTaskEndDate(this.taskEndDate);
		}
		if (!this.taskStartTime.equals("")) {
			initialTask.setTaskStartDate(this.taskStartTime);
			initialTask.setTaskEndDate(this.taskEndTime);
		}

		return this;
	}
	
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public void setTaskStartDate(String taskStartDate) {
		this.taskStartDate = taskStartDate;
	}

	public void setTaskEndDate(String taskEndDate) {
		this.taskEndDate = taskEndDate;
	}

	public void setTaskPriority(String taskPriority) {
		this.taskPriority = taskPriority;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public void setTaskStartTime(String taskStartTime) {

		this.taskStartTime = taskStartTime;
	}

	public void setTaskEndTime(String taskEndTime) {
		this.taskEndTime = taskEndTime;
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

	public String getTaskStartDate() {
		if (!this.taskStartDate.equals("")) {
			StringBuffer buffer = new StringBuffer(this.taskStartDate);
			return buffer.toString();
		} else
			return "";
	}

	public String getTaskEndDate() {
		if (!this.taskStartDate.equals("")) {
			StringBuffer buffer = new StringBuffer(this.taskEndDate);
			return buffer.toString();
		} else
			return "";
	}

	public String getTaskPriority() {
		StringBuffer buffer = new StringBuffer(this.taskPriority);
		return buffer.toString();
	}

	public String getTaskDescription() {
		StringBuffer buffer = new StringBuffer(this.taskDescription);
		return buffer.toString();
	}

	public String getTaskStartTime() {
		if (!this.taskStartTime.equals("")) {
			StringBuffer buffer = new StringBuffer(this.taskStartTime);
			String buffer2 = buffer.toString();
			return buffer2;
		} else
			return "";
	}

	public String getTaskEndTime() {
		if (!this.taskEndTime.equals("")) {
			StringBuffer buffer = new StringBuffer(this.taskEndTime);
			String buffer2 = buffer.toString();
			return buffer2;
		} else
			return "";
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
