/**
 * Program that interprets any rating 1 ≤ x ≤ 5
 * (which could be interpreted as the rating averaged over several individual ratings).
 */

/**
 * @author Ishmael Aqsar
 *
 */
public class StarRating {

	private static String s;

	public static String interpret(double rating) {

		if (rating == 5.0) {
			s = "[HAS ONLY ONE REVIEW]";
		} else if (rating < 5.0 && rating >= 4.5) {
			s = "EXCELLENT";
		} else if (rating < 4.5 && rating >= 4.0) {
			s = "OK";
		} else if (rating < 4.0 && rating >= 1.0) {
			s = "CRAP";
		} else {
			throw new IllegalArgumentException("Inavlid rating, please insert a value between 1.0 and 5.0");
		}
		return s;

	}
}
