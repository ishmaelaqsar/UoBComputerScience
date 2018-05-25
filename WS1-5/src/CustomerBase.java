import java.util.ArrayList;

public class CustomerBase {

	private ArrayList<CustomerWithGoods> allCustomers;

	/**
	 * 
	 * @param allCustomers
	 *            ArrayList of all CustomerWithGoods objects
	 */
	public CustomerBase(ArrayList<CustomerWithGoods> allCustomers) {
		this.allCustomers = allCustomers;
	}

	/**
	 * Method to add Customers to ArrayList allCustomers
	 * 
	 * @param customer
	 */
	public void addCustomer(CustomerWithGoods customer) {
		allCustomers.add(customer);
	}

	/**
	 * @return allCustomers
	 */
	public ArrayList<CustomerWithGoods> getAllCustomers() {
		return allCustomers;
	}

	/**
	 * Method to return an ArrayList of all customers with a greater number of
	 * orders than the average
	 * 
	 * @return ArrayList of all 'Loyal' customers
	 */
	public ArrayList<CustomerWithGoods> filterLoyal() {
		double avorders = 0.0;
		ArrayList<CustomerWithGoods> loyal = new ArrayList<>();

		for (CustomerWithGoods i : allCustomers) {
			avorders += i.getGoods().size();
		}

		avorders = avorders / allCustomers.size();

		for (CustomerWithGoods i : allCustomers) {
			if (i.getGoods().size() > avorders) {
				loyal.add(i);
			}
		}
		return loyal;
	}

	/**
	 * Method to return an ArrayList of all customers with an average value of
	 * orders greater than average
	 * 
	 * @return ArrayList of all 'Valued' customers
	 */
	public ArrayList<CustomerWithGoods> filterValued() {
		double avvalue = 0.0;
		ArrayList<CustomerWithGoods> valued = new ArrayList<>();

		for (CustomerWithGoods i : allCustomers) {
			avvalue += i.valueOfGoods();
		}

		avvalue = avvalue / allCustomers.size();

		for (CustomerWithGoods i : allCustomers) {
			if (i.valueOfGoods() > avvalue) {
				valued.add(i);
			}
		}
		return valued;
	}

	/**
	 * Print all customers as String, line by line
	 */
	public String toString() {
		String x = "";
		for (CustomerWithGoods c : getAllCustomers()) {
			x += c.getCustomerName() + "\n";
		}
		return x;
	}

	/**
	 * 
	 * @return String of all valued customers line by line
	 */
	public String valuedToString() {
		String x = "";
		for (CustomerWithGoods c : filterValued()) {
			x += c.getCustomerName() + "\n";
		}
		return x;
	}

	/**
	 * 
	 * @return String of all loyal customers line by line
	 */
	public String loyalToString() {
		String x = "";
		for (CustomerWithGoods c : filterLoyal()) {
			x += c.getCustomerName() + "\n";
		}
		return x;
	}

}
