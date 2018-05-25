import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

/**
 * @author ishmael
 *
 */
public class SortableTest {

	public static final double EPSILON = 1e-6;

	private Sortable s1, s2, s3, s4, s5, s6;

	// public test
	@Test(expected = IllegalArgumentException.class)
	public void test2() {

		s2 = new Car(-150.0, "FG61 CWF", "BMW");
	}

	// public test
	@Test(expected = IllegalArgumentException.class)
	public void test3() {

		s4 = new Customer("James", -11.0, "22 Bristol Road");
	}

	// public test
	@Test
	public void test4() {

		s1 = new Car(Double.MAX_VALUE, "CF11 ACE", "McLaren");
		s2 = new Car(Double.MIN_VALUE, "UG08 TRF", "BMW");
		s3 = new Car(10.0, "YY12 YEC", "VW");

		Sortable[] a = { s2, s3, s1 };

		Sortable[] expected = { s2, s3, s1 };
		Sortable[] actual = Sorting.quickSort(a);

		assertArrayEquals(expected, actual);
	}

	// public test
	@Test
	public void test5() {

		s4 = new Customer("James", Double.MAX_VALUE, "12 Bristol Road");
		s5 = new Customer("Emilia", 11.05, "1 Cardiff Street");
		s6 = new Customer("Dorothy", Double.MIN_VALUE, "42 Wolly Way");

		Sortable[] a = { s4, s5, s6 };

		Sortable[] expected = { s6, s5, s4 };
		Sortable[] actual = Sorting.quickSort(a);

		assertArrayEquals(expected, actual);
	}

	// public test
	@Test
	public void test6() {

		s1 = new Car(1.233333, "CF11 ACE", "McLaren");
		s2 = new Car(6.54, "UG08 TRF", "BMW");
		s3 = new Car(1.2, "YY12 YEC", "VW");
		s4 = new Customer("James", Double.MAX_VALUE, "12 Bristol Road");
		s5 = new Customer("Emilia", 11.05, "1 Cardiff Street");
		s6 = new Customer("Dorothy", Double.MIN_VALUE, "42 Wolly Way");

		Sortable[] a = { s1, s2, s3, s4, s5, s6 };

		Sortable[] expected = { s6, s3, s1, s2, s5, s4 };
		Sortable[] actual = Sorting.quickSort(a);

		assertArrayEquals(expected, actual);
	}

}
