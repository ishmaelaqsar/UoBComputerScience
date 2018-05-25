
/**
 * Class to test Distance.java
 */

import java.util.Scanner; //import scanner

public class DistanceTest {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in); //create Scanner object input

        //get input from user
        System.out.print("Enter distance in kilometres: ");
        Distance km = new Distance(input.nextDouble());
        System.out.println();

        //print results in different units
        System.out.printf("%.2f metres %n%.2f miles %n%.2f yards %n", km.getMetres(), km.getMiles(), km.getYards());
    }
}