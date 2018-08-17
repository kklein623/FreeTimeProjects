package parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailAddressParser {

	//Email Regular Expression. Only does simple emails. It won't capture anything with comments or quotes
	//Based on email address definition in RFC3696 https://tools.ietf.org/html/rfc3696
	private final static Pattern EMAIL_REGEX = Pattern.compile("[A-Za-z0-9!#$%&'*+\\-\\/=?^_`{|}~][A-Za-z0-9!#$%&'*+\\-\\/=?^_`{|}~\\.]+@[A-Za-z0-9\\-\\.]+");
	
	/**
	 * 
	 * @param document - The string it's currently trying to parse
	 * @return The first instance of an email it recognizes and finds
	 */
	public static String getEmail(String document) {
		Matcher matcher = EMAIL_REGEX.matcher(document); 
		while (matcher.find()) {
			return matcher.group();
		}
		return "None";
	}
}
