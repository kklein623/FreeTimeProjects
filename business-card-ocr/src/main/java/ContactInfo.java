
public class ContactInfo {
	
	private String name = "No name listed";
	private String phoneNumber = "No phone listed";
	private String emailAddress = "No email listed";

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}	
	
	public ContactInfo() {}
	
	public String printContactInfo() {
		String contactInfo = "Name: " + name + "\n";
		contactInfo += "Phone: " + phoneNumber + "\n";
		contactInfo += "Email: " + emailAddress;
		return contactInfo;
	}
}
