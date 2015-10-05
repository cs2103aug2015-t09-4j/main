
public class Task {
	private String taskName;
	private String taskType;
	private Integer taskDate;
	private String taskPriority;
	private String taskDescription;
	private Integer taskStartTime;
	private Integer taskEndTime;
	//for recurring
	private String recurType;
	private Integer recurStartDate;
	private int recurEndDate;
	
	public void setTaskName(String taskName){
		this.taskName = taskName;
	}	
	public void setTaskType(String taskType){
		this.taskType = taskType;
	}
	public void setTaskDate(Integer taskDate){
		this.taskDate = taskDate;
	}
	public void setTaskPriority(String taskPriority){
		this.taskPriority = taskPriority;
	}
	public void setTaskDescription(String taskDescription){
		this.taskDescription = taskDescription;
	}
	public void setTaskStartTime(Integer taskStartTime){
		this.taskStartTime = taskStartTime;
	}	
	public void setTaskEndTime(Integer taskEndTime){
		this.taskEndTime = taskEndTime;
	}
	public void setRecurType(String recurType){
		this.recurType = recurType;
	}
	public void setRecurStartDate(Integer recurStartDate){
		this.recurStartDate = recurStartDate;
	}
	public void setRecurEndDate(Integer recurEndDate){
		this.recurEndDate = recurEndDate;
	}
	
	
	public String getTaskName(){
		return this.taskName;
	}
	public String getTaskType(){
		return this.taskType;
	}
	public Integer getTaskDate(){
		return this.taskDate;
	}
	public String getTaskPriority(){
		return this.taskPriority;
	}
	public String getTaskDescription(){
		return this.taskDescription;
	}
	public Integer getStartTime(){
		return this.taskStartTime;
	}
	public Integer getTaskEndTime(){
		return this.taskEndTime;
	}
	public String getRecurType(String recurType){
		return this.recurType;
	}
	public Integer getRecurStartDate(Integer recurStartDate){
		return this.recurStartDate;
	}
	public Integer getRecurEndDate(Integer recurEndDate){
		return this.recurEndDate;
	}
}
