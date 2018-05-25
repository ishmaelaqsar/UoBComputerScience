/**
 * Program to compute the angle between the hour hand and minute hand of an analogue clock.
 * The angles are measured anti-clockwise from the hour hand
*/

import java.util.Scanner;

public class ClockAngle //begin class
{

    public static void main(String[] args) //begin main method
    {
	Scanner input = new Scanner(System.in); //declare scanner object
	
	int hours, minutes;
	double hour_angle, minute_angle, angle, new_angle;

	System.out.println("Enter the current time");
	System.out.print("Hours: ");
	hours = input.nextInt() % 12; //reduce 24 hours to 12 hours e.g. 13%12 = 1
	System.out.print("Minutes: ");
	minutes = input.nextInt();

	hour_angle = (hours*30)+(minutes*0.5); //the second term accounts for the slight movement of the hour hand between hours
	minute_angle = (minutes*6);
	angle = hour_angle - minute_angle; //if minute hand goes past the hour hand it leads to a negative angle
	new_angle = (360 +  angle) % 360; //this gives the correct angle by finding the positive equivalent of any negative angles

	System.out.printf("The angle is %.0f degrees.%n", new_angle);
	
    }//end of main

}//end of class ClockAngle
