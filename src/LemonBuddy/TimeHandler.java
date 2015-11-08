package LemonBuddy;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;

public class TimeHandler implements TimeHandlerInterface {

	public String getCurrentDate() {
		DateFormat df = new SimpleDateFormat("ddMMyy");
		Date dateobj = new Date();
		String currentDate = df.format(dateobj);
		return currentDate;
	}

	public String getCurrentTime() {
		DateFormat df = new SimpleDateFormat("HHmm");
		Calendar calobj = Calendar.getInstance();
		String time = df.format(calobj.getTime());
		return time;
	}

	public boolean isDateValid(String date) {
		try {
			DateFormat df = new SimpleDateFormat("ddMMyy");
			df.setLenient(false);
			df.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public boolean checkValidDateTimeInput(Task newTask) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
		Date date1 = new Date();
		Date date2 = new Date();;
		try {
			date1 = sdf.parse(newTask.getTaskStartDate());
			date2 = sdf.parse(newTask.getTaskEndDate());
		} catch (ParseException e) {
			throw new Exception("Error parsing valid date range");
		} finally {
			if (date1.after(date2)) {
				return false;
			}

			if (newTask.getTaskStartDate() == newTask.getTaskEndDate()) {
				if (Integer.parseInt(newTask.getTaskStartTime()) > Integer.parseInt(newTask.getTaskEndTime())) {
					return false;
				}
			}
		}
		return true;

	}

	public String[] addOneHour(String date, String time) {
		Calendar initialTime = Calendar.getInstance();
		initialTime.set(Integer.parseInt(date.substring(4, 6)), Integer.parseInt(date.substring(2, 4)) - 1,
				Integer.parseInt(date.substring(0, 2)), Integer.parseInt(time.substring(0, 2)),
				Integer.parseInt(time.substring(2, 4)), 0);
		initialTime.add(Calendar.HOUR, 1);
		SimpleDateFormat dateFormatter = new SimpleDateFormat("ddMMyy HHmm");
		String[] timeInfo = dateFormatter.format(initialTime.getTime()).split(" ");
		return timeInfo;
	}

	public String addOneDay(String date, String time) {
		Calendar initialTime = Calendar.getInstance();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("ddMMyy HHmm");
		time = getCurrentTime();
		initialTime.set(Integer.parseInt(date.substring(4, 6)), Integer.parseInt(date.substring(2, 4)) - 1,
				Integer.parseInt(date.substring(0, 2)), Integer.parseInt(time.substring(0, 2)),
				Integer.parseInt(time.substring(2, 4)), 0);
		initialTime.add(Calendar.HOUR, 24);
		String[] timeInfo = dateFormatter.format(initialTime.getTime()).split(" ");
		return timeInfo[0];
	}

	public String addOneYear(String date) {
		int day = parseInt(getDay(date));
		int month = parseInt(getMonth(date));
		int year = parseInt(getYear(date)) + 1;

		String newDate = "";
		newDate = newDate.concat(toTwoDigit(Integer.toString(day)));
		newDate = newDate.concat(toTwoDigit(Integer.toString(month)));
		newDate = newDate.concat((Integer.toString(year)));

		return newDate;
	}

	public String getDay(String date) {
		return date.substring(0, 2);
	}

	public String getMonth(String date) {
		return date.substring(2, 4);
	}

	public String getYear(String date) {
		return date.substring(4, 6);
	}

	public String toTwoDigit(String num) {
		if (num.length() == 1) {
			num = "0" + num;
		}

		return num;
	}

	public String toSixDigit(int num) {
		String numString = Integer.toString(num);
		if (numString.length() == 5) {
			numString = "0" + numString;
		}

		return numString;
	}

	public boolean endDatePassed(String currentDate, String endDate) {

		int currentDay = parseInt(getDay(currentDate));
		int currentMonth = parseInt(getMonth(currentDate));
		int currentYear = parseInt(getYear(currentDate));
		int endDay = parseInt(getDay(endDate));
		int endMonth = parseInt(getMonth(endDate));
		int endYear = parseInt(getYear(endDate));

		if ((currentYear > endYear)) {
			return true;
		} else if ((currentMonth > endMonth) && (currentYear == endYear)) {
			return true;
		} else if ((currentDay > endDay) && (currentMonth == endMonth) && (currentYear == endYear)) {
			return true;
		} else {
			return false;
		}
	}

	public int parseInt(String str) {
		int i = 0;
		int num = 0;
		boolean isNeg = false;

		// Check for negative sign; if it's there, set the isNeg flag
		if (str.charAt(0) == '-') {
			isNeg = true;
			i = 1;
		}

		// Process each character of the string;
		while (i < str.length()) {
			num *= 10;
			num += str.charAt(i++) - '0'; // Minus the ASCII code of '0' to get
											// the value of the charAt(i++).
		}

		if (isNeg)
			num = -num;
		return num;
	}
}
