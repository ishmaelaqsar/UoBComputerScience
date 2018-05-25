package ex5;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**Public tests for Ex5.
 * 
 * @author Alexandros Evangelidis
 * 
 */

public class Ws2Ex5PublicTests {

	private Complex c1, c2,c3,c4,c5;
	private final static double EPSILON = 1e-6;
	
	@Before
	public void setUp() {
	     c1 = new Complex(1,2);
	     c2 = new Complex(3,7);
	     c3 = new Complex(1,0);
	     c4 = new Complex(0,1);
	     c5 = new Complex(5,11);
	}

	@Test
	public void test1() {

		Complex c6 = c1.add(c2);
		
		assertEquals(4.0, c6.getRealPart(), EPSILON);
		assertEquals(9.0, c6.getImaginaryPart(), EPSILON);
		
		assertEquals(1.0, c1.getRealPart(),EPSILON);
		assertEquals(2.0, c1.getImaginaryPart(),EPSILON);
		
		assertEquals(3.0, c2.getRealPart(),EPSILON);
		assertEquals(7.0, c2.getImaginaryPart(),EPSILON);
		
		String expected = "4.0 + 9.0i";
		assertEquals(expected, c6.toString());
	
	}
	

	@Test
	public void test2() {

		Complex c6 = c1.multiply(c2);
		
		assertEquals(-11.0, c6.getRealPart(), EPSILON);
		assertEquals(13.0, c6.getImaginaryPart(), EPSILON);
		
		assertEquals(1.0, c1.getRealPart(),EPSILON);
		assertEquals(2.0, c1.getImaginaryPart(),EPSILON);
		
		assertEquals(3.0, c2.getRealPart(),EPSILON);
		assertEquals(7.0, c2.getImaginaryPart(),EPSILON);
		
		String expected = "-11.0 + 13.0i";
		assertEquals(expected, c6.toString());
	
	}
	
	

	@Test
	public void test3(){

		Complex c6 = c3.add(c4);
		
		assertEquals(1.0, c6.getRealPart(), EPSILON);
		assertEquals(1.0, c6.getImaginaryPart(), EPSILON);
		
		assertEquals(1.0, c3.getRealPart(),EPSILON);
		assertEquals(0.0, c3.getImaginaryPart(),EPSILON);
		
		assertEquals(0.0, c4.getRealPart(),EPSILON);
		assertEquals(1.0, c4.getImaginaryPart(),EPSILON);
		
		String expected = "1.0 + 1.0i";
		assertEquals(expected, c6.toString());
	
	}
	

	@Test
	public void test4() {

		Complex c6 = c3.multiply(c4);
		
		assertEquals(0.0, c6.getRealPart(), EPSILON);
		assertEquals(1.0, c6.getImaginaryPart(), EPSILON);
		
		assertEquals(1.0, c3.getRealPart(),EPSILON);
		assertEquals(0.0, c3.getImaginaryPart(),EPSILON);
		
		assertEquals(0.0, c4.getRealPart(),EPSILON);
		assertEquals(1.0, c4.getImaginaryPart(),EPSILON);
		
		String expected = "0.0 + 1.0i";
		assertEquals(expected, c6.toString());
	
	}

	

	@Test
	public void test5() {
		
		
		Complex c6 = c2.add(c5);
		
		assertEquals(8.0, c6.getRealPart(), EPSILON);
		assertEquals(18.0, c6.getImaginaryPart(), EPSILON);
		
		assertEquals(3.0, c2.getRealPart(),EPSILON);
		assertEquals(7.0, c2.getImaginaryPart(),EPSILON);
		
		assertEquals(5.0, c5.getRealPart(),EPSILON);
		assertEquals(11.0, c5.getImaginaryPart(),EPSILON);
		
		String expected = "8.0 + 18.0i";
		assertEquals(expected, c6.toString());
	
	}

	@Test
	public void test6() {
		
		
		Complex c6 = c2.multiply(c5);
		
		assertEquals(-62.0, c6.getRealPart(), EPSILON);
		assertEquals(68, c6.getImaginaryPart(), EPSILON);
		
		assertEquals(3.0, c2.getRealPart(),EPSILON);
		assertEquals(7.0, c2.getImaginaryPart(),EPSILON);
		
		assertEquals(5.0, c5.getRealPart(),EPSILON);
		assertEquals(11.0, c5.getImaginaryPart(),EPSILON);
		
		String expected = "-62.0 + 68.0i";
		assertEquals(expected, c6.toString());
	
	}

	
}
