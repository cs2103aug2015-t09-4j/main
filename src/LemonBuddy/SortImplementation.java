package LemonBuddy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortImplementation{
	
/************************************************** Convert Function for Sorting ******************************************************************/	
	private static int convertPriority(Task t){
		String tempPriority = t.getTaskPriority();
		switch(tempPriority) {
			case "high":
				return 1;
			case "medium":
				return 0;		
			default:
				return -1;
		}
	}	
	
	private static Integer convertType(Task t){
		String tempType = t.getTaskType().toLowerCase();
		switch(tempType) {
			case "overdue":
				return -2;
			case "deadline":
				return -1;
			case "event":
				return 0;
			case "done":
				return 2;
			default:
				return 1;
		}
	}
	
	private static int convertDate(int d) {
		int year = d % 100;
		d = d / 100;
		int month = d % 100;
		d = d / 100;
		int day = d;
		
		int newdate = (year * 10000) + (month * 100) + day;
		return newdate;
	}
	
/***************************************************** Sort By Condition ******************************************************************/	
	
	protected static ArrayList<Task> SortEndDate(ArrayList<Task> list) {
		Comparator<Task> comparator = new Comparator<Task>(){
			public int compare(Task t1, Task t2) {
				 String type1, type2;
				 int end1, end2;
				 int p1, p2;
				 p1 = convertPriority(t1);
				 p2 = convertPriority(t2);
				 
				String deadline = "deadline";
				String event = "event";

				type1 = t1.getTaskType().toLowerCase();
				type2 = t2.getTaskType().toLowerCase();	
					
				if(type1.equals(deadline)) {
					end1 = Integer.valueOf(t1.getTaskEndDate()).intValue();
				} else if(type1.equals(event)) {
					end1 = Integer.valueOf(t1.getTaskStartDate()).intValue();
				} else {
					end1 = -1;
				}
			
				if(type2.equals(deadline)) {
					end2 = Integer.valueOf(t2.getTaskEndDate()).intValue();
				} else if(type2.equals(event)) {
					end2 = Integer.valueOf(t2.getTaskStartDate()).intValue();
				} else {
					end2 = -1;
				}
				 
				end1 = convertDate(end1);
				end2 = convertDate(end2);

				if(end1 > end2){
					return 1;
				} else if (end1 == end2) {
					if(p1 < p2){
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

	public static ArrayList<Task> SortTime(ArrayList<Task> sortList) {
		  Comparator<Task> comparator = new Comparator<Task>(){
			public int compare(Task t1, Task t2) {
				
				String deadline = "deadline";
				String event = "event";
				
				String type1, type2;
				int time1, time2;
				type1 = t1.getTaskType().toLowerCase();
				type2 = t2.getTaskType().toLowerCase();	
					
				if(type1.equals(deadline)) {
					time1 = Integer.valueOf(t1.getTaskEndTime()).intValue();
				} else if(type1.equals(event)) {
					time1 = Integer.valueOf(t1.getTaskStartTime()).intValue();
				} else {
					time1 = -1;
				}
		 
				if(type2.equals(deadline)) {
					time2 = Integer.valueOf(t2.getTaskEndTime()).intValue();
				} else if(type2.equals(event)) {
					time2 = Integer.valueOf(t2.getTaskStartTime()).intValue();
				} else {
					time2 = -1;
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
				n1 = t1.getTaskName();
				n2 = t2.getTaskName();
				return n1.compareTo(n2);
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
				sd1 = Integer.valueOf(t1.getTaskStartDate()).intValue(); 
				sd2 = Integer.valueOf(t2.getTaskStartDate()).intValue(); 
				return sd1.compareTo(sd2);
			}
		};				
		Collections.sort(list, comparator);
		return list;
	}

}