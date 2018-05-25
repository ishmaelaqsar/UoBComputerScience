import static org.junit.Assert.*;

/**
 * Unit test for the PrimeSieve class
 */
public class PrimeTest {

	// Test 1
	@org.junit.Test(expected = IllegalArgumentException.class)
	public void test1() {
		int[] actual1 = PrimeNumbers.sieve(1);
		int[] expected1 = {};

		assertArrayEquals(actual1, expected1);
	}

	// Test 2
	@org.junit.Test
	public void test2() {
		int[] actual2 = PrimeNumbers.sieve(20);
		int[] expected2 = { 2, 3, 5, 7, 11, 13, 17, 19 };

		assertArrayEquals(actual2, expected2);
	}

	// Test 3
	@org.junit.Test
	public void test3() {
		int[] actual3 = PrimeNumbers.sieve(2);
		int[] expected3 = { 2 };

		assertArrayEquals(actual3, expected3);
	}

	// Test 4
	@org.junit.Test(expected = IllegalArgumentException.class)
	public void test4() {
		int[] actual4 = PrimeNumbers.sieve(0);
		int[] expected4 = {};

		assertArrayEquals(actual4, expected4);
	}

	// Test 5
	@org.junit.Test(expected = IllegalArgumentException.class)
	public void test5() {
		int max = -10;
		PrimeNumbers.sieve(max);
	}

	// Test 6
	@org.junit.Test
	public void test6() {
		int[] actual6 = PrimeNumbers.sieve(1000);
		int[] expected6 = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89,
				97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197,
				199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317,
				331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449,
				457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593,
				599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727,
				733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863,
				877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997 };

		assertArrayEquals(actual6, expected6);
	}
}
