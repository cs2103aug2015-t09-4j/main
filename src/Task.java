
public class Task {
	private String taskName;
	private String taskType;
	private String taskDate;
	private String taskPriority;
	private String taskDescription;
	private int taskStartTime;
	private int taskEndTime;
	
	public void setTaskName(String taskName){
		this.taskName = taskName;
	}	
	public void setTaskType(String taskType){
		this.taskType = taskType;
	}
	public void setTaskDate(String taskDate){
		this.taskDate = taskDate;
	}
	public void setTaskPriority(String taskPriority){
		this.taskPriority = taskPriority;
	}
	public void setTaskDescription(String taskDescription){
		this.taskDescription = taskDescription;
	}
	public void setTaskStartTime(int taskStartTime){
		this.taskStartTime = taskStartTime;
	}
	
	public void setTaskEndTime(int taskEndTime){
		this.taskEndTime = taskEndTime;
	}
	
	public String getTaskName(){
		return this.taskName;
	}
	public String getTaskType(){
		return this.taskType;
	}
	public String getTaskDate(){
		return this.taskDate;
	}
	public String getTaskPriority(){
		return this.taskPriority;
	}
	public String getTaskDescription(){
		return this.taskDescription;
	}
	public int getStartTime(){
		return this.taskStartTime;
	}
	public int getTaskEndTime(){
		return this.taskEndTime;
	}
}
