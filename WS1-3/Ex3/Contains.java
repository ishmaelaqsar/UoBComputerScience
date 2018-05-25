import java.util.ArrayList;

/**
 * a method that returns an ArrayList of all those integers in a range 
 * between the integer from inclusively to the integer to exclusively which do contain a particular digit
 */

/**
 * @author Ishmael Aqsar
 *
 */
public class Contains {
	public static ArrayList<Integer> allIntegersWith(int from, int to, int containedDigit) {
		
		// Create a blank ArrayList of Integer type
		ArrayList<Integer> list = new ArrayList<Integer>();

		//Iterate through the ArrayList from the value 'from' to the value 'to'
		for (int i = from; i < to; i++) {
			//Convert the current value of 'i' to a String and use the method '.contains'
			//to see if it contains the 'containedDigit' which has also been converted to a String
			if (Integer.toString(i).contains(Integer.toString(containedDigit))) {
				list.add(i);
			}
		}

		return list;

	}

	public static void main(String[] args) {
		System.out.println(allIntegersWith(-60,-10,4));
	}
}
