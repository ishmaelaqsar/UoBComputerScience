import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * 
 * @author Ishmael Aqsar
 *
 */
public class ArrayMethodsTest {
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	private final static double EPSILON = 1e-6;

	// public test
	@Test
	public void test1() {

		double[][] a = { { 2, 4, 6 }, { 8, 10, 12 } };

		double expectedMin = 2;
		double expectedMax = 12;
		double expectedMean = 7;
		double expectedMedium = 7;

		assertEquals(expectedMin, ArrayMethods.min(a), EPSILON);
		assertEquals(expectedMax, ArrayMethods.max(a), EPSILON);
		assertEquals(expectedMean, ArrayMethods.mean(a), EPSILON);
		assertEquals(expectedMedium, ArrayMethods.median(a), EPSILON);
	}

	// public test
	@Test
	public void test2() {

		double[][] a = { { 1, 2, 3 }, { 4, 5, 6 } };

		double expectedMin = 1;
		double expectedMax = 6;
		double expectedMean = 3.5;
		double expectedMedium = 3.5;

		assertEquals(expectedMin, ArrayMethods.min(a), EPSILON);
		assertEquals(expectedMax, ArrayMethods.max(a), EPSILON);
		assertEquals(expectedMean, ArrayMethods.mean(a), EPSILON);
		assertEquals(expectedMedium, ArrayMethods.median(a), EPSILON);
	}

	// public test
	@Test
	public void test3() {

		double[][] a = { {} };
		exception.expect(IllegalArgumentException.class);
		ArrayMethods.min(a);
	}

	// public test
	@Test
	public void test4() {

		double[][] a = {{-2,4,-6},{-10,0}};

		double expectedMin = -10;
		double expectedMax = 4.0;
		double expectedMean = -2.8;
		double expectedMedian = -2.0;

		double actualMin = ArrayMethods.min(a);
		double actualMax = ArrayMethods.max(a);
		double actualMean = ArrayMethods.mean(a);
		double actualMedian = ArrayMethods.median(a);

		assertEquals(expectedMin, actualMin, EPSILON);
		assertEquals(expectedMax, actualMax, EPSILON);
		assertEquals(expectedMean, actualMean, EPSILON);
		assertEquals(expectedMedian, actualMedian, EPSILON);
	}

}
