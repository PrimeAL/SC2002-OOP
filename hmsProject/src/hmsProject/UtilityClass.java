package hmsProject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Input checking.
 */
public class UtilityClass {
	/**
	 * Check if email is in correct format.
	 * @param email email to be checked
	 * @return 0 if correct format, 2 if not.
	 */
	public static int checkEmail(String email) {
		String[] acceptedEmails = {"gmail.com", "hotmail.com", "e.ntu.edu.sg", "yahoo.com", "live.com", "msn.com"};
		for (String i : acceptedEmails) {
			if (email.endsWith(i)) {
				return 0;
			}
		}
		return 2;
	}

	/**
	 * Check if phone is in correct format.
	 * @param phoneNum phone number to be checked
	 * @return 0 if correct format, 1 if not.
	 */
	public static int checkPhone(String phoneNum) {
		if (phoneNum.matches("[0-9]+")) {
			return 0;
		}
		else return 1;
	}

	/**
	 * Check if date is in correct format.
	 * @param dateStr date to be checked
	 * @return boolean that indicates whether date in correct format.
	 */
	public static boolean isValidDate(String dateStr) {
		try {
			LocalDate.parse(dateStr);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

	/**
	 * Check if time is in correct format.
	 * @param timeStr time to be checked
	 * @return boolean that indicates whether time in correct format.
	 */
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
