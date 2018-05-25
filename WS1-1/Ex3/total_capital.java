/**
 * Program which adds a fixed interest rate onto an inital capital
 */
import java.util.Scanner;

public class total_capital {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		System.out.print("Enter the inital capital in GBP(£): ");
		double capital = input.nextDouble();

		System.out.print("Enter the interest rate (%): ");
		double interest_rate = input.nextDouble();

		System.out.print("Enter the number of years: ");
		double years = input.nextDouble();

		System.out.printf("%nThe total balance after %.1f years is: £%.2f.%n",
			years, capital*java.lang.Math.pow(1+0.01*interest_rate,years));
	}
}
