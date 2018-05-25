import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * A class to implement the RSA encryption method. The RSA method works by first randomly 
 * selecting two distinct prime numbers p and q. Their product is called n. In order
 * to work in the type long this means that the prime numbers p and q must be small enough
 * that their product is smaller than Long.MAX VALUE, that is, 9223372036854775807. 
 * Furthermore, you determine lambda as the least common multiple (lcm) of p-1 and q-1,
 * and a third prime number e, so that e does not divide lambda. The last number playing a 
 * role is a number d which is the inverse of e with respect to lambda. A sender takes from 
 * the recipient the numbers n and e. In order to encrypt now a number plainNumber the sender
 * takes it to the power e modulo n. The so calculated number encryptedNumber can then be sent
 * in plain to the recipient. Only the recipient would know the secret decryption number d.
 * Decrypting is done by taking the encryptedNumber to the power d modulo n.
 */

/**
 * @author Ishmael Aqsar
 *
 */

public class RSA {

	private final long plainNumber;
	private final long p;
	private final long q;
	private long e;
	private long d;
	private long n;
	private long encryptedNumber;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RSA test1 = new RSA(65, 61, 53, 17);
		System.out.println("Encrypted Number: " + test1.Encrypt());
		System.out.println("Plain Number: " + test1.Decrypt());

		RSA test2 = new RSA(65, 293, 317, 97);
		System.out.println("Encrypted Number: " + test2.Encrypt());
		System.out.println("Plain Number: " + test2.Decrypt());

		RSA test3 = new RSA(65, 99991, 8999, 14983);
		System.out.println("Encrypted Number: " + test3.Encrypt());
		System.out.println("Plain Number: " + test3.Decrypt());
	}

	/**
	 * @param plainNumber
	 *            you want encrypt
	 * @param p
	 *            is a prime number
	 * @param q
	 *            is a prime number != p
	 */
	public RSA(long plainNumber, long p, long q, long e) {
		this.plainNumber = plainNumber;
		this.p = p;
		this.q = q;
		this.e = e;
	}

	/**
	 * Method to encrypt a plainNumber method uses an RSA object consisting of a
	 * plainNumber, a value for p, and a value for q
	 * 
	 * @return encryptedNumber
	 */
	public long Encrypt() {

		SecureRandom r = new SecureRandom();

		/*
		 * Convert p and q to BigIntegers and see if their product is greater
		 * than Long.MAX_VALUE, if it's bigger throw an exception
		 */
		BigInteger bigInt = BigInteger.valueOf(p).multiply(BigInteger.valueOf(q));
		if (bigInt.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0)
			throw new IllegalArgumentException("Product of p and q is too large");

		// modulus N is the product of p and q
		n = p * q;

		// find the lowest common multiple (lcm) of (p-1)(q-1)
		long lambda = LCM(p - 1, q - 1);

		/*
		 * if e is not set in constructor find a random coprime of e with lambda
		 * such that 1 < e < lambda If gcd(a, b) = 1, then a and b are said to
		 * be coprime
		 */
		if (e == 0) {
			do {
				e = r.nextInt((int) (lambda - 3)) + 2;
			} while (GCD(e, lambda) != 1);
		}

		d = inverse(e, lambda); // multiplicative inverse of e(mod lambda)
		encryptedNumber = power(plainNumber, e, n); // public key

		return encryptedNumber;
	}

	public long Decrypt() {
		return power(encryptedNumber, d, n);
	}

	/**
	 * find the lcm of two numbers using reduction by the gcd
	 * 
	 * @param a
	 * @param b
	 *            the method finds lcm by dividing the product of a and b by
	 *            their gcd. formula is not valid when both a and b are 0.
	 * @return lcm
	 */
	public long LCM(long a, long b) {
		return (a * b) / GCD(a, b);
	}

	/**
	 * find the gcd of two numbers using Euclidean algorithm
	 * 
	 * @param a
	 * @param b
	 *            The method calls itself until b is equal to 0, when this
	 *            happens a is the gcd
	 * @return a
	 */
	public long GCD(long a, long b) {
		if (b == 0)
			return a;
		return GCD(b, a % b);
	}

	/**
	 * find the modular multiplicative inverse of 'a' using extended the
	 * euclidean algorithm
	 * 
	 * @param a
	 * @param n
	 *            - modulus
	 * @return inverse of a with respect to n
	 */
	public long inverse(long a, long n) {
		long t = 0;
		long new_t = 1;
		long r = n;
		long new_r = a;

		long temp;

		while (new_r != 0) {
			long quotient = r / new_r;

			// swap t and newt
			temp = t;
			t = new_t;
			new_t = temp - quotient * new_t;

			// swap r and newr
			temp = r;
			r = new_r;
			new_r = temp - quotient * new_r;
		}
		if (r > 1)
			return -1; // a is not invertible
		if (t < 0)
			t = t + n;
		return t;
	}

	/**
	 * The method calculates x^a mod modulus converts x from a long to a BigInt
	 * before doing the calculation. The value is then converted to a long
	 * before returning
	 * 
	 * @param x
	 * @param a
	 *            - exponent
	 * @param modulus
	 */
	public long power(long x, long a, long modulus) {
		BigInteger message = BigInteger.valueOf(x);
		return message.modPow(BigInteger.valueOf(a), BigInteger.valueOf(modulus)).longValue();
	}

}
