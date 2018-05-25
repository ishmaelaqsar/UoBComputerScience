package ex4;

public class Employee {

	private String name;
	private double hourlySalary;
	private int numberOfHours;

	public Employee(String name, double hourlySalary, int numberOfHours) {
		this.name = name;
		this.hourlySalary = hourlySalary;
		this.numberOfHours = numberOfHours;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the hourlySalary
	 */
	public double getHourlySalary() {
		return hourlySalary;
	}

	/**
	 * @param hourlySalary
	 *            the hourlySalary to set
	 */
	public void setHourlySalary(double hourlySalary) {
		this.hourlySalary = hourlySalary;
	}

	/**
	 * @return the numberOfHours
	 */
	public int getNumberOfHours() {
		return numberOfHours;
	}

	/**
	 * @param numberOfHours
	 *            the numberOfHours to set
	 */
	public void setNumberOfHours(int numberOfHours) {
		this.numberOfHours = numberOfHours;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name + " has an hourly salary of " + hourlySalary + " £ and has worked last month for " + numberOfHours
				+ " hours.";
	}

	public double monthlySalary(double taxRate) {
		double salaryBeforeTax = (hourlySalary * numberOfHours);
		double salaryAfterTax = salaryBeforeTax - (salaryBeforeTax*(taxRate/100));
		return salaryAfterTax;
	}

	public void increaseSalary(double percentage)
	{ 
		hourlySalary += hourlySalary*(percentage/100);
	}

}
