package LemonBuddy;

import java.util.Comparator;

class Sort implements Comparator<Task> {
	 public int compare(Task t1, Task t2) {
		 int end1, end2, p1, p2;
		 end1 = Integer.valueOf(t1.getTaskEndDate()).intValue();
		 end2 = Integer.valueOf(t2.getTaskEndDate()).intValue();
		 p1 = Integer.valueOf(t1.getTaskPriority()).intValue();
		 p2 = Integer.valueOf(t2.getTaskPriority()).intValue();
		 if (end1 > end2) {
			 return 1;
		 } else if(end1 == end2) {
			 if(p1 > p2) {
				 return 1;
			 } 
			 return 0;
		 } else {
			 return 0;
		 }		 
	 }
}
