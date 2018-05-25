package ex2;

public class ClubMember 
{
	private String name;
	private String dateOfBirth;
	private String registrationNumber;
	private String membershipType;
	
	public ClubMember(String name, String dateOfBirth, String registrationNumber, String membershipType)
	{
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.registrationNumber = registrationNumber;
		this.membershipType = membershipType;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDateOfBirth()
	{
		return dateOfBirth;
	}
	
	public String getRegistrationNumber()
	{
		return registrationNumber;
	}
	
	public String getMembershipType()
	{
		return membershipType;
	}
	
	public String toString() {
		return "[" + name + ", " + dateOfBirth + ", ID: " 
				+ registrationNumber + ", " + membershipType + "]";
	}
	
	
}
