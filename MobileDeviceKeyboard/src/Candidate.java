import java.util.Collection;
import java.util.HashMap;

public class Candidate {

	private String word;
	private Integer confidence;
	private HashMap<String, Candidate> links = new HashMap<String, Candidate>();
	private boolean isCandidate = false;
	
	public Candidate() {
		this.word = "";
	}
	
	public Candidate(String word) {
		this.word = word;
	}
	
	public Candidate(String word, boolean isCandidate) {
		this.word = word;
		this.isCandidate = isCandidate;
		confidence = 1;
	}
	
	public Candidate addLink(String text) {
		Candidate newNode = new Candidate(text);
		links.put(text, newNode);
		return newNode;
	}
	
	public void addCandidate(String text) {
		links.put(text, new Candidate(text, true));
	}
	
	public Candidate getLink(String text) {
		return links.get(text);
	}
	
	public Collection<Candidate> getLinks() {
		return links.values();
	}
	
	public boolean isCandidate() {
		return isCandidate;
	}
	
	public Integer getConfidence() {
		return confidence;
	}
	
	public void increaseConfidence() {
		confidence++;
	}
	
	public String getWord() {
		return word;
	}
}
