/**
 * 
 */
package ex5;

/**
 * @author Ishmael Aqsar
 *
 */
public class Complex {
	
	private double realPart;
	private double imaginaryPart;
	
	public Complex(double realPart, double imaginaryPart) {
		this.realPart = realPart;
		this.imaginaryPart = imaginaryPart;
	}

	/**
	 * @return the realPart
	 */
	public double getRealPart() {
		return realPart;
	}

	/**
	 * @return the imaginaryPart
	 */
	public double getImaginaryPart() {
		return imaginaryPart;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return realPart + " + " + imaginaryPart + "i";
	}
	
	public Complex add(Complex summand) {
		return new Complex((this.realPart + summand.realPart), 
				(this.imaginaryPart + summand.imaginaryPart));
	}
	
	public Complex multiply(Complex factor) {
		return new Complex(
				((this.realPart * factor.realPart)-(this.imaginaryPart * factor.imaginaryPart)),
				 ((this.realPart * factor.imaginaryPart)+(factor.realPart * this.imaginaryPart))
				 );
	}
	

}
