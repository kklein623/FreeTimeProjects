package parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberParser {
	
	//Phone Number Regular Expression. Will only match standard formatted phone numbers and nothing with letters as numbers
	private final static Pattern PHONE_REGEX = Pattern.compile("(\\+?\\d{1,2})?[ \\-\\.]?\\(?(\\d{3})\\)?[ \\-\\.]?(\\d{3})[ \\-\\.]?(\\d{4})");
	private final static String SEPERATORS = "[\\+\\- \\.\\(\\)]";
	
	/**
	 * Uses a phone number regex to find and return the first phone number found
	 * Faster but slightly less accurate if fax or cell are written first
	 * @param document - The string it's currently trying to parse
	 * @return The first instance of an phone number it recognizes and finds
	 */

	public static String getFirstPhoneNumber(String document) {
		Matcher matcher = PHONE_REGEX.matcher(document); 
		while (matcher.find()) {
			return matcher.group().replaceAll("[\\+\\- \\.\\(\\)]", "");
		}
		return "No phone listed";
	}
	
	/**
	 * Uses a phone number regex to find and return the first non-Fax or Cell phone number found
	 * Slower but more accurate
	 * @param document - The string it's currently trying to parse
	 * @return The first instance of an phone number it recognizes and finds
	 */

	public static String getOfficePhoneNumber(String document) {
		//We save these in case we need a back up number
		String fax = "";
		String cell = "";
		for (String line : document.split("\\v")) {
			Matcher matcher = PHONE_REGEX.matcher(line); 
			while (matcher.find()) {
				if (line.toLowerCase().contains("fax")) 
					fax = matcher.group().replaceAll(SEPERATORS, "");
				else if (line.toLowerCase().contains("cell"))
					cell = matcher.group().replaceAll(SEPERATORS, "");
				else return matcher.group().replaceAll(SEPERATORS, "");
			}
		}
		
		//Cell first, then fax if there's no other number listed
		if (cell.length() > 0) return cell;
		if (fax.length() > 0) return fax;
		
		return "No phone listed";
	}
	
}
