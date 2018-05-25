/**
 *	Program to find the area of a circle using the formula A=pi*r^2
 */
import java.util.Scanner; //import class Scanner

public class circle_area {

	// main method begins execution of Java application
	public static void main(String[] args) {

		// create Scanner to obtain input from command line
		Scanner input = new Scanner (System.in);

		//initialise variables for Area and radius
		double A, r;

		//prompt user for an input
		System.out.print("Enter the radius of the circle: ");
		r = input.nextInt();

		//calculate area and print value
		A = Math.PI*r*r;
		System.out.printf("The area of a circle with radius %.2f is %.2f%n", r, A);

	}
}
