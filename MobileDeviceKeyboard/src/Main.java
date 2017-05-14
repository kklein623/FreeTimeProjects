import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String inputString = " ";
		Scanner scanIn = new Scanner(System.in);
		AutocompleteProvider autoComplete = new AutocompleteProvider();
		
		while (!inputString.toLowerCase().equals("exit")) {
			System.out.println("Enter command");   
			inputString = scanIn.nextLine();
			
			if (inputString.toLowerCase().equals("train")) { 
				System.out.print("Train: ");
				inputString = scanIn.nextLine();
				autoComplete.train(inputString);
				
			} else if (inputString.toLowerCase().equals("input")) {
				System.out.print("Input: ");
				inputString = scanIn.nextLine();
				autoComplete.getWords(inputString);	
				
			} else if (inputString.toLowerCase().equals("exit")) {
				System.out.println("Exiting");
			} else {
				System.out.println("Command doesn't exist");  
			}
			
		}
		scanIn.close();
	}

}
