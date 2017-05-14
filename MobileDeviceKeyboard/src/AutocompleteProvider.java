import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AutocompleteProvider {
	
	//The data structure holding the entire typeahead library
	//Begins with a blank string and branches out based on the next character
	//Used a node-link structure to prevent any data redundancy
	private static Candidate nodeMap;
	
	/**
	 * Constructor. Initializes the nodeMap as a blank Candidate
	 */
	public AutocompleteProvider() {
		nodeMap = new Candidate();
	}
	
	/**
	 * Helper function for getWords. If a candidate is found, adds to the list and returns the list.
	 * If not, dives into the next node and repeats until all candidates are found
	 * @param curr The current node being searched on
	 * @return A list of all candidates within the curr node
	 */
	private ArrayList<Candidate> candidateSearch(Candidate curr) {
		Collection<Candidate> links = curr.getLinks();
		ArrayList<Candidate> candidates = new ArrayList<Candidate>();
		if (curr.isCandidate()) {
			candidates.add(curr);
		}
		for(Candidate c: links) {
			if (c.isCandidate()) {
				candidates.add(c);
			} else {
				candidates.addAll(candidateSearch(c));
			}
		}
		return candidates;		
	}

	/**
	 * Goes into the nodeMap and grabs the candidate that matches the given fragment
	 * If there is no candidate, returns null
	 * Uses candidateSearch to search on that node to find all candidates
	 * Sorts the candidates by confidence level, then prints and returns them
	 * @param fragment The string being searched on
	 * @return A list of possible typeahead options for the fragment
	 */
	public List<Candidate> getWords(String fragment) {
		Candidate curr = nodeMap;
		for(int i = 0; i < fragment.length(); i++) {
			curr = curr.getLink(fragment.substring(0, i + 1));
			if (curr == null) {
				return null;
			}
		}
		
		ArrayList<Candidate> candidateList =  candidateSearch(curr);
		Collections.sort(candidateList, new Comparator<Candidate>() {
			@Override
			public int compare(Candidate c1, Candidate c2) {
				return c2.getConfidence().compareTo(c1.getConfidence());
			}
			
		});
		for(Candidate c: candidateList) {
			System.out.print(c.getWord() + "(" + c.getConfidence() + ") ");
		}
		System.out.print("\n");
		return candidateList;
	}
	
	/**
	 * Given a passage, adds to the nodeMap based on each word in the passage
	 * Breaks up the passage into each word, removing all punctuation, then adds each word character by character into the nodeMap
	 * Increases the confidence of the word if it's already a candidate, otherwise, adds it as a new candidate
	 * @param passage The phrase or passage training on
	 */
	public void train(String passage) {
		String[] words = passage.toLowerCase().replaceAll("\\p{Punct}", "" ).split(" ");
		for(int i = 0; i < words.length; i++) {
			Candidate curr = nodeMap;
			Candidate next;
			String fragment = "";
			
			for(int j = 0; j < words[i].length() - 1; j++) {
				fragment = words[i].substring(0, j + 1);
				next = curr.getLink(fragment);
				
				if (next != null) {
					curr = next;
				} else {
					curr = curr.addLink(fragment);
				}				
			}
			
			fragment = words[i].substring(0, words[i].length());
			next = curr.getLink(fragment);
			
			if (next != null) {
				next.increaseConfidence();
			} else {
				curr.addCandidate(fragment);
			}
		}
	}
}
