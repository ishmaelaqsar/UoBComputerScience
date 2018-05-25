package ex2;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/** Public tests for Ex2.
 * 
 * @author David McDonald
 * 
 */

public class Ws2Ex2PublicTests {

	private ClubMember c1;
	
	@Before
	public void setUp() {
		
		c1 = new ClubMember("John", "6 October 1993", "05721", "Gold");
	}
	
	@Test
	public void test1() {

		assertEquals("John", c1.getName());
		assertEquals("05721", c1.getRegistrationNumber());
		assertEquals("Gold", c1.getMembershipType());
	}

	@Test
	public void test2() {
		
		assertEquals("6 October 1993", c1.getDateOfBirth());	
	}
	
	@Test
	public void test3() {
		
		String expected = "[John, 6 October 1993, ID: 05721, Gold]";
		assertEquals(expected, c1.toString());
	}

	@Test
	public void test4() {
		
		c1 = new ClubMember("David", "6 October 1993", "05721", "Gold");

		String expected = "[David, 6 October 1993, ID: 05721, Gold]";
		assertEquals(expected, c1.toString());
	}
	
	@Test
	public void test5() {
		
		c1 = new ClubMember("David", "6 October 1993", "05721", "Bronze");
		String expected = "[David, 6 October 1993, ID: 05721, Bronze]";
		assertEquals(expected, c1.toString());
	}
	
	@Test
	public void test6() {
		
		c1 = new ClubMember("David", "13 September 1993", "985632", "Platinum");
		String expected = "[David, 13 September 1993, ID: 985632, Platinum]";
		assertEquals(expected, c1.toString());
	}
}
