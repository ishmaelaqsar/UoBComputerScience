import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * @author ishmael
 */
public class AircraftTest {

	private Aircraft aircraft1, aircraft2, aircraft3;
	private Aeroplane aeroplane;
	private HotAirBalloon hotAirBaloon1;
	@Before
	public void setUp() {
		aircraft1 = new Aircraft(900.0, 799, 300);
		aircraft2 = new Aeroplane(600.0, 1499, 600, 1300);
		aircraft3 = new HotAirBalloon(100.0, 400, 5, 60);

		aeroplane = new Aeroplane(600.0, 1499, 600, 1300);
		hotAirBaloon1 = new HotAirBalloon(100.0, 400, 5, 60);
		new HotAirBalloon(100.0, 400, 0, 60);
	}

	// public test
	@Test
	public void test1() {

		String expected1 = "The aircraft has a maximal speed of 900.0 km/h and a maximal weight of 799.0 kg."
				+ " It can carry 300 persons.";
		String actual1 = aircraft1.toString();

		assertEquals(expected1, actual1);

		String expected2 = "The aircraft has a maximal speed of 600.0 km/h and a maximal weight of 1499.0 kg."
				+ " It can carry 600 persons. It has a range of 1300.0 km.";
		String actual2 = aircraft2.toString();

		assertEquals(expected2, actual2);

		String expected3 = "The aircraft has a maximal speed of 100.0 km/h and a maximal weight of 400.0 kg. It can carry 5 persons."
				+ " It has a gas temperature of maximally 60.0\u00B0C.";
		String actual3 = aircraft3.toString();

		assertEquals(expected3, actual3);
	}

	// public test
	@Test
	public void test2() {

		aircraft1.setMaxPersons(1);
		aircraft3.setMaxPersons(aircraft1.getMaxPersons());

		String expected1 = "The aircraft has a maximal speed of 900.0 km/h and a maximal weight of 799.0 kg."
				+ " It can carry 1 person.";
		String actual1 = aircraft1.toString();

		assertEquals(expected1, actual1);

		String expected2 = "The aircraft has a maximal speed of 600.0 km/h and a maximal weight of 1499.0 kg."
				+ " It can carry 600 persons. It has a range of 1300.0 km.";
		String actual2 = aircraft2.toString();

		assertEquals(expected2, actual2);

		String expected3 = "The aircraft has a maximal speed of 100.0 km/h and a maximal weight of 400.0 kg. It can carry 1 person."
				+ " It has a gas temperature of maximally 60.0\u00B0C.";
		String actual3 = aircraft3.toString();

		assertEquals(expected3, actual3);

	}

	// public test
	@Test
	public void test3() {

		aeroplane.setMaxSpeed(1400);
		aeroplane.setRange(300);
		hotAirBaloon1.setGasTemperature(150);
		hotAirBaloon1.setMaxSpeed(204.2);
		
		hotAirBaloon1.setMaxPersons(1);
		
		String expected2 = "The aircraft has a maximal speed of 204.2 km/h and a maximal weight of 400.0 kg. It can carry 1 person."
				+ " It has a gas temperature of maximally 150.0\u00b0C.";
		String actual2 = hotAirBaloon1.toString();

		assertEquals(expected2, actual2);

		aeroplane.setMaxPersons(2);

		String expected3 = "The aircraft has a maximal speed of 1400.0 km/h and a maximal weight of 1499.0 kg. It can carry 2 persons."
				+ " It has a range of 300.0 km.";
		String actual3 = aeroplane.toString();

		assertEquals(expected3, actual3);

	}

	// public test
	@Test(expected = IllegalArgumentException.class)
	public void test4() {
		aeroplane.setRange(-300);
		hotAirBaloon1.setGasTemperature(-200);
		aircraft2.setMaxSpeed(-1400);
	}

}
