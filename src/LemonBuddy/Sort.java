package LemonBuddy;

import java.util.Comparator;

class Sort implements Comparator<Object> {
	 public int compare(Object o1, Object o2) {
		 Task t1 = (Task)o1;
		 Task t2 = (Task)o2;
		 Integer end1, end2, p1, p2;
		 end1 = Integer.valueOf(t1.getTaskEndDate()).intValue();
		 end2 = Integer.valueOf(t2.getTaskEndDate()).intValue();
		 p1 = Integer.valueOf(t1.getTaskPriority()).intValue();
		 p2 = Integer.valueOf(t2.getTaskPriority()).intValue();
		 int flag = end1.compareTo(end2);
		 if(flag == 0) {
			 return p1.compareTo(p2);
		 } else {
			 return flag;
		 }
	 }
}
