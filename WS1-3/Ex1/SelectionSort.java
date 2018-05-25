import java.util.Arrays;

/**
 * Java implementation of a selection sort algorithm
 */

/**
 * @author Ishmael Aqsar
 *
 */

// begin class SelectionSort
public class SelectionSort {

	// begin selectionSort method
	public static int[] selectionSort(int[] numbers) {
		for (int i = 0; i < numbers.length - 1; i++) {

			// initialise min variable to i
			int min = i;

			// begin loop to compare min against next element of the array
			// if j is less than min, j=min
			for (int j = i + 1; j < numbers.length; j++) {
				if (numbers[j] < numbers[min]) {
					min = j;
				}
			}

			// if min is not i, swap min with i
			// smallest number in array is now i'th element
			if (min != i) {
				int temp;
				temp = numbers[i];
				numbers[i] = numbers[min];
				numbers[min] = temp;
			}

			// print how the array currently looks
			System.out.println(Arrays.toString(numbers));
		}
		// return the result of the selectionSort method
		return numbers;

	}// end selectionSort method
}// end class SelectionSort
