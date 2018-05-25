/**
 * Class to test Complex.java
 */

public class ComplexTest {

    public static void main(String[] args) {

        //create Complex objects c1, c2, and c3
        Complex c1 = new Complex(1, 1);
        Complex c2 = new Complex(3, 7);
        Complex c3 = new Complex(1, 0);

        //print c1, c2, and c3 using toString method to give Complex form
        System.out.println("c1 = " + c1.toString());
        System.out.println("c2 = " + c2.toString());
        System.out.println("c3 = " + c3.toString());
        System.out.println();

        //print c1 + c2 using add method in complex form
        System.out.println("c1 + c2 = " + c2.add(c1).toString());

        //print c1*c2 using multiply method in complex form
        System.out.println("c1 * c2 = " + c2.multiply(c1).toString());

    }
}