import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AutocompleteProvider {
	
	private static Candidate nodeMap;
	
	public AutocompleteProvider() {
		nodeMap = new Candidate();
	}
	
	private ArrayList<Candidate> breath(Candidate curr) {
		Collection<Candidate> links = curr.getLinks();
		ArrayList<Candidate> candidates = new ArrayList<Candidate>();
		for(Candidate c: links) {
			if (c.isCandidate()) {
				candidates.add(c);
			} else {
				candidates.addAll(breath(c));
			}
		}
		return candidates;		
	}

	public List<Candidate> getWords(String fragment) {
		Candidate curr = nodeMap;
		for(int i = 0; i < fragment.length(); i++) {
			curr = curr.getLink(fragment.substring(0, i + 1));
			if (curr == null) {
				return null;
			}
		}
		
		ArrayList<Candidate> candidateList =  breath(curr);
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
