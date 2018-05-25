/**
 * 
 */

/**
 * @author Ishmael Aqsar
 *
 */
public class HotAirBalloon extends Aircraft {

	private double gasTemperature;

	/**
	 * @param maxSpeed
	 * @param maxWeight
	 * @param maxPersons
	 * @param gasTemperature
	 */
	public HotAirBalloon(double maxSpeed, double maxWeight, int maxPersons, double gasTemperature) {
		super(maxSpeed, maxWeight, maxPersons);
		if(gasTemperature < 0) {
			throw new IllegalArgumentException("Error, negative temperature");
		}
		this.gasTemperature = gasTemperature;
	}

	/**
	 * @return the gasTemperature
	 */
	public double getGasTemperature() {
		return gasTemperature;
	}

	/**
	 * @param gasTemperature
	 *            the gasTemperature to set
	 */
	public void setGasTemperature(double gasTemperature) {
		if(gasTemperature < 0) {
			throw new IllegalArgumentException("Error, negative temperature");
		}
		this.gasTemperature = gasTemperature;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (getMaxPersons() == 1) {
			return super.toString() + " It has a gas temperature of maximally " + gasTemperature + "\u00b0C.";
		} else {
			return super.toString() + " It has a gas temperature of maximally " + gasTemperature + "\u00b0C.";
		}

	}

}
