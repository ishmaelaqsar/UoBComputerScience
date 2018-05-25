import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

/**
 * @author Ishmael Aqsar
 */
public class ContainsTest {

    //public test
    @Test
    public void test1() {

        ArrayList<Integer> expected = new ArrayList<Integer>
            (Arrays.asList(23, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 43));
        ArrayList<Integer> actual = Contains.allIntegersWith(23, 53, 3);
			
        assertEquals(expected, actual);
			
    }
		
    //public test
    @Test
    public void test2() {

        ArrayList<Integer> expected = new ArrayList<Integer>
            (Arrays.asList(15, 25, 35, 45));
        ArrayList<Integer> actual = Contains.allIntegersWith(10, 50, 5);
			
        assertEquals(expected, actual);
			
    }
		
    //public test
    @Test
    public void test3() {

        ArrayList<Integer> expected = new ArrayList<Integer>
            (Arrays.asList(14, 24, 34, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49));
        ArrayList<Integer> actual = Contains.allIntegersWith(10,50,4);
			
        assertEquals(expected, actual);
			
    }

    //public test
    @Test
    public void test4() {

        ArrayList<Integer> expected = new ArrayList<Integer>
            (Arrays.asList(-54, -49, -48, -47, -46, -45, -44, -43, -42, -41, -40, -34, -24, -14));
        ArrayList<Integer> actual = Contains.allIntegersWith(-60,-10,4);
			
        assertEquals(expected, actual);
			
    }

    //public test
    @Test
    public void test5() {

        ArrayList<Integer> expected = new ArrayList<Integer>
            (Arrays.asList(0, 10, 20, 30, 40, 50, 60, 70, 80, 90));
        ArrayList<Integer> actual = Contains.allIntegersWith(0, 100, 0);
			
        assertEquals(expected, actual);
			
    }
	
}
