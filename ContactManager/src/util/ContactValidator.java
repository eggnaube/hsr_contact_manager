package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ContactValidator {

	public static boolean isValidEmail (String s) {
		// check for abc@def.ghi
		return (s.equals("") || s.matches(".+@.+\\.[a-z]+"));
	}

	public static boolean isValidTelNr (String s) {
		//checks for any combination with numbers, (, ), -, .(can be empty)
		return (s.matches("\\+?[\\d ()\\-\\.]*"));
	}

	public static boolean isValidDate (String s) {
		//checks for correct dates, allows empty
		if (s.equals("")) return true;
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		sdf.setLenient(false);
		try {
			return sdf.parse(s)!=null;
		} catch (ParseException e) {
			return false;
		}
	}
}
