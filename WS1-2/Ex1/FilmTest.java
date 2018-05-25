/**
*   A class to test Film.java
*/
public class FilmTest {

    public static void main(String[] args) {
        Film movie = new Film("Blade Runner", 1982, 118); //create Film object movie

        System.out.println(movie.toString()); //print sentence using toString method

        movie.setLength(128); //use setLength to change length from 118 to 128
        System.out.println(movie.toString()); //print using toString
    }
}
