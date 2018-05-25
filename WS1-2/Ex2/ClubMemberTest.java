/**
 * Class to test ClubMember.java
 */

public class ClubMemberTest {

    public static void main(String[] args) {

        ClubMember member = new ClubMember("John Smith", "5 October 1993", "C212121", "Gold"); //create ClubMember object

        System.out.println(member.toString()); //print member details using toString method
    }
}