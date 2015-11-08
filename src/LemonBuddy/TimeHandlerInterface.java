package LemonBuddy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface TimeHandlerInterface {
	public String getCurrentDate();
	public String getCurrentTime();
	
	public boolean isDateValid(String date);
	public boolean checkValidDateTimeInput(Task newTask) throws Exception;
	
	
	public String[] addOneHour(String date, String time);
	public String addOneDay(String date, String time);
	public String addOneYear(String date);
	
	public String getDay(String date);
	public String getMonth(String date);
	public String getYear(String date);
	
	public String toTwoDigit(String num);
	public String toSixDigit(int num);

	public boolean endDatePassed(String currentDate, String endDate);
	public int parseInt(String str) ;
}
