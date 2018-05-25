/**
 * 
 */

/**
 * @author Ishmael Aqsar
 *
 */
public class Aircraft {

	private double maxSpeed;
	private double maxWeight;
	private int maxPersons;

	/**
	 * @param maxSpeed
	 * @param maxWeight
	 * @param maxPersons
	 */
	public Aircraft(double maxSpeed, double maxWeight, int maxPersons) {
		super();
		if (maxSpeed <= 0 && maxWeight <= 0 && maxPersons <= 0) {
			throw new IllegalArgumentException("Error, cannot have negative inputs");
		}
		this.maxSpeed = maxSpeed;
		this.maxWeight = maxWeight;
		this.maxPersons = maxPersons;
	}

	/**
	 * @return the maxSpeed
	 */
	public double getMaxSpeed() {
		return maxSpeed;
	}

	/**
	 * @param maxSpeed
	 *            the maxSpeed to set
	 */
	public void setMaxSpeed(double maxSpeed) {
		if (maxSpeed <= 0) {
			throw new IllegalArgumentException("Error, cannot have negative inputs");
		}
		this.maxSpeed = maxSpeed;
	}

	/**
	 * @return the maxWeight
	 */
	public double getMaxWeight() {
		return maxWeight;
	}

	/**
	 * @param maxWeight
	 *            the maxWeight to set
	 */
	public void setMaxWeight(double maxWeight) {
		if(maxWeight <= 0) {
			throw new IllegalArgumentException("Error, cannot have negative inputs");
		}
		this.maxWeight = maxWeight;
	}

	/**
	 * @return the maxPersons
	 */
	public int getMaxPersons() {
		return maxPersons;
	}

	/**
	 * @param maxPersons
	 *            the maxPersons to set
	 */
	public void setMaxPersons(int maxPersons) {
		if(maxPersons <= 0) {
			throw new IllegalArgumentException("Error, cannot have negative inputs");
		}
		this.maxPersons = maxPersons;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (maxPersons == 1) {
			return "The aircraft has a maximal speed of " + maxSpeed + " km/h and a maximal weight of " + maxWeight
					+ " kg. It can carry " + maxPersons + " person.";
		} else {
			return "The aircraft has a maximal speed of " + maxSpeed + " km/h and a maximal weight of " + maxWeight
					+ " kg. It can carry " + maxPersons + " persons.";
		}
	}

}
