package ex1;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**Public tests for Ex1.
 *
 * @author @author David McDonald
 * 
 */

public class Ws2Ex1PublicTests {

	private Film f1;
	@Before
	public void setUp() {
	 f1 = new Film("Rambo", 1982 , 95);	
	}
	
	@Test
	public void test1() {
	
		f1.setLength(5);
	
		assertEquals("Rambo", f1.getTitle());
		assertEquals(1982, f1.getYear());
		assertEquals(5, f1.getLength());
	}

	@Test
	public void test2() {
		
		f1.setLength(0);
		
		assertEquals("Rambo", f1.getTitle());
		assertEquals(1982, f1.getYear());
		assertEquals(0, f1.getLength());
	}

	@Test
	public void test3() {
		
		f1.setLength(100);
		
		assertEquals("Rambo", f1.getTitle());
		assertEquals(1982, f1.getYear());
		assertEquals(100, f1.getLength());
	}
	
	@Test
	public void test4() {
		
		f1.setLength(100);
		
		assertEquals("Rambo", f1.getTitle());
		assertEquals(1982, f1.getYear());
		assertEquals(100, f1.getLength());
	}

	
	@Test
	public void test5() {
	
		f1.setLength(120);
		
		assertEquals("Rambo", f1.getTitle());
		assertEquals(1982, f1.getYear());
		assertEquals(120, f1.getLength());
	}

	@Test
	public void test6() {
		
		f1.setLength(85);
		
		assertEquals("Rambo", f1.getTitle());
		assertEquals(1982, f1.getYear());
		assertEquals(85, f1.getLength());
	}
}
