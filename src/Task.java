import java.io.Serializable;

public class Task implements Serializable {
	private String taskName;
	private String taskType;
	private String taskDate;
	private String taskPriority;
	private String taskDescription;
	private String taskStartTime;
	private String taskEndTime;
	// for recurring
	private String recurType;
	private String recurStartDate;
	private String recurEndDate;
	// private String desc;

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public void setTaskDate(String taskDate) {
		this.taskDate = taskDate;
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

	public Integer getTaskDate() {
		StringBuffer buffer = new StringBuffer(this.taskDate);
		return Integer.valueOf(buffer.toString());
	}

	public String getTaskPriority() {
		StringBuffer buffer = new StringBuffer(this.taskPriority);
		return buffer.toString();
	}

	public String getTaskDescription() {
		StringBuffer buffer = new StringBuffer(this.taskDescription);
		return buffer.toString();
	}

	public String getStartTime() {
		StringBuffer buffer = new StringBuffer(this.taskStartTime);
		String buffer2 = buffer.toString();
		return buffer2;
	}

	public String getEndTime() {
		StringBuffer buffer = new StringBuffer(this.taskEndTime);
		String buffer2 = buffer.toString();
		return buffer2;
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