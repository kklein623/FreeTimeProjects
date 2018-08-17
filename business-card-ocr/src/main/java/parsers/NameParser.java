package parsers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

public class NameParser {
	
	/**
	 * Utilizes Apache OpenNLP https://opennlp.apache.org/
	 * Uses their name finder to find the same in the business card
	 * We break down the document into lines, then words
	 * @param document - The business card we want to parse out
	 * @return The name - listed on the business card, or "No name listed"
	 * @throws IOException
	 */
	
	public static String getName(String document) throws IOException {
		
		//Apache OpenNLP Initializing Code
		
		//Grabs the model that contains the trained name finder data. It is possible to swap out or create a custom one
		InputStream inputStream = new FileInputStream("src/main/resources/en-ner-person.bin");
		
		TokenNameFinderModel model = new TokenNameFinderModel(inputStream);
		
		inputStream.close();
		
		NameFinderME nameFinder = new NameFinderME(model);
		
		String name = "";
		
		//Split on line first. This is so we can find the line with the most name instances as that will most likely be the full name
		String[] lines = document.split("\\v");
		for (String line : lines) {
			
			//We then split on spaces because the OpenNLP NameFinder relies on a StringArray
			String[] words = line.split(" ");
			Span nameSpans[] = nameFinder.find(words);
			
			for(Span s : nameSpans) {
				//Length two is used because having two or more found names next to each usually designates an actual name
				//There are instances where a name could be in the company, like a law firm, but they're usually separated by commas or it's just a last name
				//We could also go by returning the longest word instance, but that could return as a street name
				if (s.getEnd() - s.getStart() >= 2) {
					for (int i = s.getStart(); i < s.getEnd(); i++) 
						name += words[i] + " ";
					return name;
				}
			}			
		}
		
		return "No name detected";
	}
}
