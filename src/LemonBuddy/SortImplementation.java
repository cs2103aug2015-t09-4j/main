package LemonBuddy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortImplementation {
	
	private static Parser parser;
	
	public SortImplementation() {
		if (parser == null) {
			parser = new Parser();
		}
	}
	
/************************************************** Convert Function for Sorting ******************************************************************/	
	private static int convertPriority(Task t){
		String tempPriority = t.getTaskPriority();
		switch(tempPriority) {
			case "high":
				return 1;
			case "medium":
				return 0;
			case "low":
				return -1;
			default:
				return -1;
		}
	}	
	
	private static Integer convertType(Task t){
		String tempType = t.getTaskType();
		switch(tempType) {
			case "deadline":
				return 1;
			case "event":
				return 0;
			case "floating":
				return -1;
			default:
				return -1;
		}
	}
	
	private static Integer convertDoneAndOverdue(Task t){
		boolean DoneStatus = t.getTaskIsDone();
		boolean OverDueStatus = t.getTaskIsOverdue();
		if(DoneStatus&&OverDueStatus){
			return 1;
		} else if (OverDueStatus) {
			return 0;
		} else if (DoneStatus) {
			return -1;
		} else {
			return -2;
		}
	}
	
/***************************************************** Sort By Condition ******************************************************************/	
	
	protected static ArrayList<Task> SortEndDate(ArrayList<Task> list) {
		Comparator<Task> comparator = new Comparator<Task>(){
			public int compare(Task t1, Task t2) {					
				 Integer end1, end2, p1, p2;
				 end1 = Integer.parseInt(t1.getTaskEndDate());
				 end2 = Integer.parseInt(t2.getTaskEndDate());
				 p1 = convertPriority(t1);
				 p2 = convertPriority(t2);

				if(end1 > end2){
					return 1;
				} else if (end1 == end2) {
					if(p1 > p2){
						return 1;
					} else if (p1 == p2) {
						return 0;
					} else {
						return -1;
					}
				} else {
					return -1;
			    }					
			}
		};				
		Collections.sort(list, comparator);
		return list;
	}

  protected static ArrayList<Task> SortTime(ArrayList<Task> sortList) {
	  Comparator<Task> comparator = new Comparator<Task>(){
		public int compare(Task t1, Task t2) {
			
			String deadline = "deadline";
			String event = "event";
			
			String type1, type2;
			int time1, time2;
			type1 = t1.getTaskType();
			type2 = t2.getTaskType();	
				
			if(type1.equals(deadline)) {
				time1 = Integer.parseInt(t1.getTaskEndTime());
			} else if(type1.equals(event)) {
				time1 = Integer.parseInt(t1.getTaskStartTime());
			} else {
				time1 = 0;
			}
	 
			if(type2.equals(deadline)) {
				time2 = Integer.parseInt(t2.getTaskEndTime());
			} else if(type2.equals(event)) {
				time2 = Integer.parseInt(t2.getTaskStartTime());
			} else {
				time2 = 0;
			}

			if(time1 > time2){
				return 1;
			} else if (time1 == time2) {
				return 0;
			} else {
				return -1;
			}
		 
		}
	};	
	
	Collections.sort(sortList, comparator);
	return sortList;
 }
  
	protected static ArrayList<Task> SortName(ArrayList<Task> list) {
		Comparator<Task> comparator = new Comparator<Task>(){
			public int compare(Task t1, Task t2) {
				String n1, n2;
				int p1, p2;
				n1 = t1.getTaskName();
				n2 = t2.getTaskName();
				p1 = convertPriority(t1);
				p2 = convertPriority(t2);
				
				int flag = n1.compareTo(n2);
				
				if(flag == 1){
					return 1;
				} else if (flag == 0) {
					if(p1 > p2){
						return 1;
					} else if (p1 == p2) {
						return 0;
					} else {
						return -1;
					}
				} else {
					return -1;
			    }
			}
		};				
		Collections.sort(list, comparator);
		return list;
	}
	
	protected static ArrayList<Task> SortType(ArrayList<Task> list) {
		Comparator<Task> comparator = new Comparator<Task>(){
			public int compare(Task t1, Task t2) {
				Integer p1, p2;
				p1 = convertType(t1);
				p2 = convertType(t2);
				
				return p1.compareTo(p2);
			}
		};				
		Collections.sort(list, comparator);
		return list;
	}

	protected static ArrayList<Task> SortStartDate(ArrayList<Task> list) {
		Comparator<Task> comparator = new Comparator<Task>(){
			public int compare(Task t1, Task t2) {
				Integer sd1, sd2;
				sd1 = Integer.parseInt(t1.getTaskStartDate());
				sd2 = Integer.parseInt(t2.getTaskStartDate());
				return sd1.compareTo(sd2);
			}
		};				
		Collections.sort(list, comparator);
		return list;
	}
	
	protected static ArrayList<Task> SortStatus(ArrayList<Task> list) {
		Comparator<Task> comparator = new Comparator<Task>(){
			public int compare(Task t1, Task t2) {
				Integer st1, st2;
				st1 = convertDoneAndOverdue(t1);
				st2 = convertDoneAndOverdue(t2);
				return st1.compareTo(st2);
			}
		};				
		Collections.sort(list, comparator);
		return list;
	}
	
	protected static ArrayList<Task> SortDateThenPriority(ArrayList<Task> list) {
		Comparator<Task> comparator = new Comparator<Task>(){
			public int compare(Task t1, Task t2) {					
				String date1 = null;
				String date2 = null;
				Integer p1, p2;
				p1 = convertPriority(t1);
				p2 = convertPriority(t2);
				
				String deadline = "deadline";
				String event = "event";
				
				String type1 = t1.getTaskType();
				String type2=  t2.getTaskType();
				
				if (type1.equals(deadline)) {
					date1 = t1.getTaskEndDate(); 
				} else if (type1.equals(event)) {
					date1 = t1.getTaskStartDate();
				}
				
				if (type2.equals(deadline)) {
					date2 = t1.getTaskEndDate(); 
				} else if (type1.equals(event)) {
					date2 = t1.getTaskStartDate();
				}
				
				if(parser.endDatePassed(date1, date2)) {
					return 1;
				} else if (date1.equals(date2)) {
					if(p1 > p2){
						return 1;
					} else if (p1 == p2) {
						return 0;
					} else {
						return -1;
					}
				} else {
					return -1;
			    }					
			}
		};				
		Collections.sort(list, comparator);
		return list;
	}
}