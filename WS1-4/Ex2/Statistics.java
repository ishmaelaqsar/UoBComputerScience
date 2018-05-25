import java.util.function.Function;

/**
 * A class that computes the mean and the standard deviation of the application of a function to a non-
 * empty array of elements, respectively.
 */

/**
 * @author Ishmael Aqsar
 *
 */
public class Statistics {

	public static double mean(Function<Double, Double> f, double[] argumentValues) {
		double sum = 0;
		for (int i = 0; i < argumentValues.length; i++) {
			sum += f.apply(argumentValues[i]);
		}
		return sum / argumentValues.length;

	}

	public static double standardDeviation(Function<Double, Double> f, double[] argumentValues) {
		double sum = 0;
		double mean = mean(f, argumentValues);
		for (int i = 0; i < argumentValues.length; i++) {
			sum += Math.pow((f.apply(argumentValues[i]) - mean), 2);
		}
		return Math.sqrt(sum / argumentValues.length);

	}

	public static void main(String[] args) {
		Function<Double, Double> f = x -> Math.pow(x, 0.5);
		double[] argumentValues = new double[1000000];

		for (int i = 0; i < argumentValues.length; i++) {
			argumentValues[i] = i % 100;
		}
		System.out.println(mean(f, argumentValues));
		System.out.println(standardDeviation(f, argumentValues));
	}
}
