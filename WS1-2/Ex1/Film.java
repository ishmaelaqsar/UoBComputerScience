package ex1;

/**
A class to store the title, year of release, 
and length of different films
*/

public class Film
{
    //initialise instance variables
    private String title;
    private int year;
    private int length;

    //initialise constructor
    public Film (String title, int year, int length)
    {
        this.title = title;
        this.year = year;
        this.length = length;
    }

    //initialise getter methods
    public String getTitle()
    {
        return this.title;
    }

    public int getYear()
    {
        return this.year;
    }

    public int getLength()
    {
        return this.length;
    }

    //initialise setter methods
    public void setLength(int newLength)
    {
        length = newLength;
    }
    
    //initialise toString method to print line by line
    public String toString()
    {
        return String.format("Title: %s%nYear: %d%nLength: %d minutes%n", title, year, length);
    }
}