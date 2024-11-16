package hmsProject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class UtilityClass {
	
	public static int checkEmail(String email) {
		String[] acceptedEmails = {"gmail.com", "hotmail.com", "e.ntu.edu.sg", "yahoo.com", "live.com", "msn.com"};
		for (String i : acceptedEmails) {
			if (email.endsWith(i)) {
				return 0;
			}
		}
		return 2;
	}
	
	public static int checkPhone(String phoneNum) {
		if (phoneNum.matches("[0-9]+")) {
			return 0;
		}
		else return 1;
	}
	
	public static boolean isValidDate(String dateStr) {
		try {
			LocalDate.parse(dateStr);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

	public static boolean isValidTime(String timeStr) {
		if (!timeStr.matches("\\d{2}:\\d{2}")) {
			return false;
		}
		try {
			LocalTime inputTime = LocalTime.parse(timeStr);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}
}
