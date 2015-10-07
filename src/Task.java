import java.io.Serializable;

public class Task implements Serializable{
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
	
	// I think need to distinguish based on different kind of operation 
	// but all return buffer.toString(); so I think may be this one can be 
	// modified
	 public String toString() {
	    	//gives task in format of Name:name;TaskType:type;TaskDate:date;
	    	
	        StringBuffer buffer = new StringBuffer();
	        
	        buffer.append("Name:");
	        buffer.append(getTaskName());
	        buffer.append(";");
	        
	        buffer.append("TaskType:");
	        buffer.append(getTaskType());
	        buffer.append(";");
	        
//	        buffer.append("TaskDate:");
//	        buffer.append(getTaskDate());
//	        buffer.append(";");
	       /*//FOR LATER 
	        buffer.append("TaskStartTime:");
	        buffer.append(getTaskStartTime());
	        buffer.append(";");
	        
	        buffer.append("TaskEndTime:");
	        buffer.append(getTaskEndTime());
	        buffer.append(";");
	        */
	        
	        return buffer.toString();
	    }
	}