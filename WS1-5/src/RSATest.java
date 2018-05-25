

/**
 * 
 */

/**
 * @author ixa444
 *
 */
public class RSATest {

	// Test 1
	@org.junit.Test
	public void test1() {
		RSA test1 = new RSA(65, 61, 53, 17);
		long actual1 = test1.Encrypt();
		long expected1 = 2790;

		assertArrayEquals(actual1, expected1);
	}

	private void assertArrayEquals(long actual1, long expected1) {
		// TODO Auto-generated method stub

	}

	// Test 2
	@org.junit.Test
	public void test2() {
		RSA test2 = new RSA(65, 293, 317, 97);
		long actual2 = test2.Encrypt();
		long expected2 = 38190;

		assertArrayEquals(actual2, expected2);
	}

	// Test 3
	@org.junit.Test
	public void test3() {
		RSA test3 = new RSA(65, 99991, 8999, 14983);
		long actual3 = test3.Encrypt();
		long expected3 = 892177137;

		assertArrayEquals(actual3, expected3);
	}

}
