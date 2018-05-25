package Model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class shipTest {

	ship testShip1 = new ship("patrol boat");
	ship testShip2 = new ship("destroyer");
	ship testShip3 = new ship("aircraft carrier");

	
	//constructor
	@Test
	public void test1() {
		int actual = testShip1.getSize();
		int expected = 2;
		assertEquals(expected, actual);
	}
	
	@Test
	public void test2() {
		int actual = testShip2.getSize();
		int expected = 3;
		assertEquals(expected, actual);
	}
	
	@Test
	public void test3() {
		int actual = testShip3.getSize();
		int expected = 5;
		assertEquals(expected, actual);
	}
	
	//Hit
	@Test
	public void test4() {
		testShip1.hit();
		int actual = testShip1.getHealth();
		int expected = 1;
		assertEquals(expected, actual);
	}
	
	@Test
	public void test5() {
		testShip3.hit();
		int actual = testShip3.getHealth();
		int expected = 4;
		assertEquals(expected, actual);
	}
	
	@Test
	public void test6() {
		testShip1.hit();
		testShip1.hit();

		int actual = testShip1.getHealth();
		int expected = 0;
		assertEquals(expected, actual);
	}
	
	@Test
	public void test7() {
		testShip1.hit();
		testShip1.hit();
		testShip1.hit();

		int actual = testShip1.getHealth();
		int expected = 0;
		assertEquals(expected, actual);
	}
	
	@Test
	public void test8() {
		testShip1.hit();
		testShip1.hit();

		boolean actual = testShip1.getSunk();
		boolean expected = true;
		assertEquals(expected, actual);
	}

}
