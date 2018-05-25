/**
 * 
 */

/**
 * @author Ishmael Aqsar
 *
 */
public class Car implements Sortable {

	private double maxSpeed;
	private String carNumber;
	private String make;

	/**
	 * @param maxSpeed
	 *            = Top speed
	 * @param carNumber
	 *            = Reg number
	 * @param make
	 *            = Manufacturer
	 */
	public Car(double maxSpeed, String carNumber, String make) {
		super();
		if (maxSpeed < 0) {
			throw new IllegalArgumentException("Error, negative speed.");
		}
		this.maxSpeed = maxSpeed;
		this.carNumber = carNumber;
		this.make = make;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Car [maxSpeed=" + maxSpeed + ", carNumber=" + carNumber + ", make=" + make + "]";
	}

	public double compareValue() {
		return maxSpeed;
	}

}
