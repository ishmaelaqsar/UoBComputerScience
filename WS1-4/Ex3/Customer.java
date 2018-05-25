/**
 * 
 */

/**
 * @author Ishmael Aqsar
 *
 */
public class Customer implements Sortable {

	private String name;
	private double totalMoneySpent;
	private String address;

	/**
	 * @param name
	 * @param totalMoneySpent
	 * @param address
	 */
	public Customer(String name, double totalMoneySpent, String address) {
		if (totalMoneySpent < 0) {
			throw new IllegalArgumentException("Error, negative spend");
		}
		this.name = name;
		this.totalMoneySpent = totalMoneySpent;
		this.address = address;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Customer [name=" + name + ", totalMoneySpent=" + totalMoneySpent + ", address=" + address + "]";
	}

	public double compareValue() {
		return totalMoneySpent;
	}

}
