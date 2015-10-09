import java.io.Serializable;

public class Task implements Serializable {
	private String taskName;
	private String taskType;
	private Integer taskDate;
	private String taskPriority;
	private String taskDescription;
	private Integer taskStartTime;
	private Integer taskEndTime;
	// for recurring
	private String recurType;
	private Integer recurStartDate;
	private int recurEndDate;
	// private String desc;

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public void setTaskDate(Integer taskDate) {
		this.taskDate = taskDate;
	}

	public void setTaskPriority(String taskPriority) {
		this.taskPriority = taskPriority;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public void setTaskStartTime(Integer taskStartTime) {
		this.taskStartTime = taskStartTime;
	}

	public void setTaskEndTime(Integer taskEndTime) {
		this.taskEndTime = taskEndTime;
	}

	public void setRecurType(String recurType) {
		this.recurType = recurType;
	}

	public void setRecurStartDate(Integer recurStartDate) {
		this.recurStartDate = recurStartDate;
	}

	public void setRecurEndDate(Integer recurEndDate) {
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

	public Integer getStartTime() {
		StringBuffer buffer = new StringBuffer(this.taskStartTime);
		return Integer.valueOf(buffer.toString());
	}

	public Integer getTaskEndTime() {
		StringBuffer buffer = new StringBuffer(this.taskEndTime);
		return Integer.valueOf(buffer.toString());
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

	/*
	 * public String getDesc() { return this.desc; }
	 */

	// I think need to distinguish based on different kind of operation
	// but all return buffer.toString(); so I think may be this one can be
	// modified
	/*
	 * public String toString() { //gives task in format of
	 * Name:name;TaskType:type;TaskDate:date;
	 * 
	 * StringBuffer buffer = new StringBuffer();
	 * 
	 * buffer.append("Name:"); buffer.append(getTaskName()); buffer.append(";");
	 * 
	 * buffer.append("TaskType:"); buffer.append(getTaskType());
	 * buffer.append(";");
	 */
	// buffer.append("TaskDate:");
	// buffer.append(getTaskDate());
	// buffer.append(";");
	/*
	 * //FOR LATER buffer.append("TaskStartTime:");
	 * buffer.append(getTaskStartTime()); buffer.append(";");
	 * 
	 * buffer.append("TaskEndTime:"); buffer.append(getTaskEndTime());
	 * buffer.append(";");
	 * 
	 * 
	 * return buffer.toString(); }
	 */
}