import java.io.IOException;
import java.util.Scanner;

import parsers.*;

public class BusinessCardParser {
	
	public static ContactInfo getContactInfo(String document) throws IOException {
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setEmailAddress(EmailAddressParser.getEmail(document));
		contactInfo.setPhoneNumber(PhoneNumberParser.getFirstPhoneNumber(document));
		contactInfo.setName(NameParser.getName(document));
		return contactInfo;
	}

	public static void main(String[] args) throws IOException {
		
		boolean inputMode = false;
		String input = "";
		
		final String DIALOGUE = "Welcome. Please type a command: \n"
				+ "Input - Use the command line to input business card text blocks.\n"
				+ "Exit  - Exit the Business Card OCR.\n";
		System.out.print(DIALOGUE);

		Scanner scanIn = new Scanner(System.in);
		String command = "";
		
		System.out.println("Enter command");   
		while (!command.toLowerCase().equals("exit") | inputMode) {
			command = scanIn.nextLine();
			
			if (inputMode) {
				if (command.equals("")){
					inputMode = false;
					System.out.println("Out of Input Mode");
					ContactInfo temp = BusinessCardParser.getContactInfo(input);
					System.out.println(temp.printContactInfo());
					input = "";
				}
				else input += command + "\n";
			}
			
			else if (command.toLowerCase().equals("input")){
				System.out.println("In Input Mode");
				System.out.println("Please enter your business card strings. To finish, line break on an empty line");
				inputMode = true;
			}
			
			else {
				System.out.println("Command doesn't exist");  
			}
			
		}
		scanIn.close();
		System.out.println("Exiting");
	}

}
