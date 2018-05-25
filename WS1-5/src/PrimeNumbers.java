import java.util.Arrays;
import java.util.Scanner;

/**
 * An implementation of Eratosthenes' method of determining all prime numbers up to a maximum of n.
 */

/**
 * @author Ishmael Aqsar
 *
 */
public class PrimeNumbers {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("n = ");
		int n = input.nextInt();
		System.out.println(Arrays.toString(sieve(n)));
		input.close();
	}

	/**
	 * Method to return prime numbers up to and including max (if prime)
	 * 
	 * @param max
	 * @return integer array containing prime numbers from 2
	 */
	public static int[] sieve(int max) {
		if (max < 2) {
			throw new IllegalArgumentException("n must be greater than 1");
		}

		boolean[] sieve = new boolean[max + 1];

		/*
		 * create a sieve array in which all values other than 0 and 1 are set
		 * to true because they are prime number candidates
		 */
		for (int i = 2; i < max + 1; i++) {
			sieve[i] = true;
		}

		int count = max - 1; // number of prime number candidates

		/*
		 * Goes through array normal comparing values with the current prime
		 * number, if number is a multiple of the current prime number then it
		 * is set to false in sieve
		 */
		for (int i = 2; i < sieve.length; i++) {
			int j = i + 1;
			while (j < sieve.length) {
				// second condition ensures duplicates aren't subtracted from
				// count
				if (j % i == 0 && sieve[j] == true) {
					sieve[j] = false;
					count--; // minus 1 from prime number candidates
				}
				j++;
			}
			if (i >= Math.sqrt(max)) {
				break;
			}
		}

		int[] primes = new int[count]; // creates an array of exact size

		int prime_count = 0; // counter for position in array primes

		/*
		 * goes through array sieve, if value is true adds the corresponding
		 * value in 'normal' to array primes
		 */
		for (int i = 2; i < sieve.length; i++) {
			if (sieve[i] == true) {
				primes[prime_count] = i;
				prime_count++;
			}
		}

		return primes;
	}

}