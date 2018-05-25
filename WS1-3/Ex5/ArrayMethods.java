import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * A class which computes the minimum, the maximum, the mean (the average value), and the
median (that is the middle most value of all values) value of an array.
 */

/**
 * @author Ishmael Aqsar
 *
 */
public class ArrayMethods {

	/**
	 * Begin method to find the minimum value in a 2D array
	 */
	public static double min(double[][] a) {

		// If the array is not 2D throw an exception
		if (a.length == 1) {
			throw new IllegalArgumentException("Error");
		}

		// Set minimum value as first element in array
		double min = a[0][0];

		// if input is illegal throw IllegalArgumentException
		try {
			// go through each array comparing array element [i][j] with current min
			// if [i][j] is smaller than min, it is set to min
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[i].length; j++) {
					if (a[i][j] < min) {
						min = a[i][j];
					}
				}
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}

		return min;

	}

	/**
	 * Begin method to find the maximum value in a 2D array
	 */
	public static double max(double[][] a) {
		// set max value as first element
		double max = a[0][0];
		try {
			// go through each array comparing array element [i][j] with current max
			// if [i][j] is greater than max, it is set to max
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[i].length; j++) {
					if (a[i][j] > max) {
						max = a[i][j];
					}
				}
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}

		return max;

	}

	/**
	 * Begin method to find the mean value in a 2D array
	 */
	public static double mean(double[][] a) {
		// create variables for sum and counter and initialise them to 0
		double sum = 0;
		int count = 0;

		try {
			// go through every element in the array and add each value to sum
			// increment counter
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[i].length; j++) {
					sum += a[i][j];
					count++;
				}
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}
		// return mean as sum/count
		return sum / count;

	}

	/**
	 * Begin method to find the median value in a 2D array
	 */
	public static double median(double[][] a) {

		// create an Arraylist of doubles
		ArrayList<Double> list = new ArrayList<Double>();
		double median = 0;
		try {
			// go through each element of the 2D array and add it to the Arraylist
			// this converts the 2D array to a 1D Arraylist
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[i].length; j++) {
					list.add(a[i][j]);
				}
			}

			// Sort the Arraylist by size
			Collections.sort(list);

			// if list length is odd
			if (list.size() % 2 == 1) {
				// median is found by finding the middle element
				median = list.get(list.size() / 2);
			}
			// if list length is even
			else {
				// median is found by finding the average of the 2 middle elements
				median = (list.get(list.size() / 2) + list.get((list.size() / 2) - 1)) / 2.0;
			}

		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}
		return median;

	}

	public static void main(String[] args) {
		double[][] a = {{-2,4,-6},{-10,0}};
		System.out.println(Arrays.deepToString(a));
		System.out.println(min(a));
		System.out.println(max(a));
		System.out.println(mean(a));
		System.out.println(median(a));
	}

}
