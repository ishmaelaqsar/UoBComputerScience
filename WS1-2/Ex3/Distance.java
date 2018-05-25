
package ex3;

/**
 * @author Ishmael Aqsar
 *	Program to convert between distances
 */

public class Distance {
	
	private double kilometres;
	
	public Distance (double km)
	{
		kilometres = km;
	}
	
	public double getMiles()
	{
		return kilometres / 1.60934;
	}
	
	public double getKilometres()
	{
		return kilometres;
	}
	
	public double getMetres()
	{
		return kilometres * 1000;
	}
	
	public double getYards()
	{
		return (kilometres / 1.60934) * 1760;
	}
	

}
