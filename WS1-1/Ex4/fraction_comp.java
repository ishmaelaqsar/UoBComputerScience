/**
*Program to add and multiply fractions
*/
import java.util.Scanner;

public class fraction_comp {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int e1, e2, d1, d2;

		System.out.println("?/X + X/X\n"
			+ "Enter the numerator for the first fraction: ");
		e1 = input.nextInt();

		System.out.println(e1 + "/? + X/X\n"
			+ "Enter the denominator for the first fraction: ");
		d1 = input.nextInt();

		System.out.println(e1 + "/" + d1 + " + " + "?/X\n"
			+ "Enter the numerator for the second fraction: ");
		e2 = input.nextInt();

		System.out.println(e1 + "/" + d1 + " + " + e2 + "/? \n"
			+ "Enter the denominator for the second fraction: ");
		d2 = input.nextInt();

		int ds = d1*d2;
		int es = (e1*d2)+(e2*d1);
		int ep = e1*e2;

		System.out.println();
		System.out.println(e1 + "/" + d1 + " + " + e2 +"/" + d2 +" = "+ es + "/" + ds);
		System.out.println(e1 + "/" + d1 + " * " + e2 + "/" + d2 + " = " + ep + "/" + ds);
	}
}
