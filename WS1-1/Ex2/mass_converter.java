/**
 *	Program to convert from Imperial units to kilograms
 */
import java.util.Scanner; //import class Scanner

public class mass_converter {

	// main method begins execution of Java application
	public static void main(String[] args) {

		// create Scanner to obtain input from command line
		Scanner input = new Scanner(System.in);

		System.out.print("1.ton \n2.hundredweight \n3.quarter \n4.stone \n5.ounce \n6.drachm \n7.grain \n8.pound \n"
			+ "\nEnter a number corresponding to one of the above units to convert to kilograms: ");
		int unit = input.nextInt();

		double ton=2240, hdw=112, quarter=28, stone=14, ozn=1, ozd=16, dran=1, drad=256, grain=1, graid=7000, pound=1, kg=0.45359237, weight;

		if (unit==1){
		System.out.print("\nEnter the number of tons to convert: ");
		weight = input.nextDouble();
		System.out.printf("%.4f ton(s) is equal to %.4fkg.%n", weight, weight*ton*kg);
		}

		else if (unit==2) {
		System.out.print("\nEnter the number of hundredweights to convert: ");
		weight = input.nextDouble();
		System.out.printf("%.4f hundredweight is equal to %.4fkg.%n", weight, weight*hdw*kg);
		}

		else if (unit==3) {
		System.out.print("\nEnter the number of quarters to convert: ");
		weight = input.nextDouble();
		System.out.printf("%.4f quarter(s) is equal to %.4fkg.%n", weight, weight*quarter*kg);
		}

		else if (unit==4) {
		System.out.print("\nEnter the number of stones to convert: ");
		weight = input.nextDouble();
		System.out.printf("%.4f stone is equal to %.4fkg.%n", weight, weight*stone*kg);
		}

		else if (unit==5) {
		System.out.print("\nEnter the number of ounces to convert: ");
		weight = input.nextDouble();
		System.out.printf("%.4f ounce(s) is equal to %.2fkg.%n", weight, weight*ozn/ozd*kg);
		}

		else if (unit==6) {
		System.out.print("\nEnter the number of drachms to convert: ");
		weight = input.nextDouble();
		System.out.printf("%.4f drachm is equal to %.4fkg.%n", weight, weight*dran/drad*kg);
		}

		else if (unit==7) {
		System.out.print("\nEnter the number of grains to convert: ");
		weight = input.nextDouble();
		System.out.printf("%.2f grain(s) is equal to %.4fkg.%n", weight, weight*grain/graid*kg);
		}

		else if (unit==8) {
		System.out.print("\nEnter the number of pounds to convert: ");
		weight = input.nextDouble();
		System.out.printf("%.4f pound(s) is equal to %.4fkg.%n", weight, weight*pound*kg);
		}

		else {
		System.out.println("Incorrect selection, program will now exit.");
		return;
		}

		System.out.printf("\nA person who weighs 11 stone and 6 pounds has a weight of %.4fkg.%n", ((11*stone)+6)*kg);
	}
}
