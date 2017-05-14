import java.util.Collection;
import java.util.HashMap;

public class Candidate {

	// The word the candidate is mapped on and represents
	private String word;
	
	// The number of times the word has been typed in. Only used for candidates but defined at the start in case a non-candidate becomes a candidate
	private Integer confidence = 0;
	
	// The nodes this node links to
	// Hashed on the next possible fragment based on the current fragment since they won't repeat
	// Thi would contain this, thin, thie, etc...
	private HashMap<String, Candidate> links = new HashMap<String, Candidate>();
	
	// Whether or not this candidate is a valid candidate, i.e. a word used for possible typeahead words
	private boolean isCandidate;
	
	/**
	 * Candidate constructor used for nodeMap head. Initializes word to blank
	 */
	public Candidate() {
		this.word = "";
	}
	
	/**
	 * Candidate constructor used for fragment and word candidates
	 * Initializes confidence if it's a typeahead candidate
	 * @param word The fragment this candidate represents and is hashed on by its parent node
	 * @param isCandidate Whether or not this is a typeahead candidate
	 */
	public Candidate(String word, boolean isCandidate) {
		this.word = word;
		this.isCandidate = isCandidate;
		if (isCandidate) {
			confidence++;
		}
	}
	
	/**
	 * Adds a fragment candidate to the links, then returns the new candidate
	 * @param text The fragment hashed on by the link map
	 * @return The new candidate link
	 */
	public Candidate addLink(String text) {
		Candidate newNode = new Candidate(text, false);
		links.put(text, newNode);
		return newNode;
	}
	
	/**
	 * Adds a typeahead candidate to the links
	 * @param text The typeahead candidate word
	 */
	public void addCandidate(String text) {
		links.put(text, new Candidate(text, true));
	}
	
	/**
	 * Gets the candidate in the links that matches the fragment
	 * @param text The fragment or word hashed on
	 * @return The link that the hashMap contains, or null if it doesn't exist
	 */
	public Candidate getLink(String text) {
		return links.get(text);
	}
	
	/**
	 * Returns all linked candidates to this candidate
	 * @return All candidates hashed in this one
	 */
	public Collection<Candidate> getLinks() {
		return links.values();
	}
	
	/**
	 * Returns whether or not this is a typeahead candidate
	 * @return Whether or not this is a typeahead candidate
	 */
	public boolean isCandidate() {
		return isCandidate;
	}
	
	/**
	 * Gets the confidence level if this is a typeahead candidate or null
	 * @return The confidence level if this is a typeahead candidate, or null otherwise
	 */
	public Integer getConfidence() {
		return confidence;
	}
	
	/**
	 * Increases the confidence level by 1
	 */
	public void increaseConfidence() {
		confidence++;
	}
	
	/**
	 * Gets the word or fragment this candidate represents
	 * @return The word or fragment this candidate represents
	 */
	public String getWord() {
		return word;
	}
}
