/**
 * 
 */

/**
 * @author Ishmael Aqsar
 *
 */
public class Aeroplane extends Aircraft {

	private double range;

	/**
	 * @param maxSpeed
	 * @param maxWeight
	 * @param maxPersons
	 * @param range
	 */
	public Aeroplane(double maxSpeed, double maxWeight, int maxPersons, double range) {
		super(maxSpeed, maxWeight, maxPersons);
		if(range < 0) {
			throw new IllegalArgumentException("Error, range cannot be negative");
		}
		this.range = range;
	}

	/**
	 * @return the range
	 */
	public double getRange() {
		return range;
	}

	/**
	 * @param range
	 *            the range to set
	 */
	public void setRange(double range) {
		if(range < 0) {
			throw new IllegalArgumentException("Error, range cannot be negative");
		}
		this.range = range;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (getMaxPersons() == 1) {
			return super.toString() + " It has a range of " + range + " km.";
		} else {
			return super.toString() + " It has a range of " + range + " km.";
		}
	}

}
