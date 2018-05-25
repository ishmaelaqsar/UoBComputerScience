import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Ishmael Aqsar This class contains the test cases for Worksheet1
 * solutions. <WRITE YOUR TEST CASES BELOW>
 */

public class Worksheet1Test {

    @Test
    public void test1a() {
        int actual1a = Worksheet1.power(2, 4);
        int expected1a = 16;

        assertEquals(expected1a, actual1a);
    }

    @Test
    public void test1b() {
        int actual1b = Worksheet1.fastPower(2, 4);
        int expected1b = 16;

        assertEquals(expected1b, actual1b);
    }

    @Test
    public void test1c() {
        int actual1b = Worksheet1.power(2, 0);
        int expected1b = 1;

        assertEquals(expected1b, actual1b);
    }

    @Test
    public void test1d() {
        int actual1b = Worksheet1.fastPower(2, 0);
        int expected1b = 1;

        assertEquals(expected1b, actual1b);
    }

    @Test
    public void test2a() {
        List<Integer> a = new List<Integer>(2, new List<>(-5, new List<>(8, new List<>(0, new List<>()))));
        List<Integer> actual2a = Worksheet1.negateAll(a);
        List<Integer> expected2a = new List<Integer>(-2, new List<>(5, new List<>(-8, new List<>(0, new List<>()))));

        assertEquals(expected2a, actual2a);
    }

    @Test
    public void test3a() {
        List<Integer> a = new List<Integer>(7, new List<>(5, new List<>(3, new List<>(8, new List<>()))));
        int x = 3;
        int actual3a = Worksheet1.find(x, a);
        int expected3a = 2;

        assertEquals(expected3a, actual3a);
    }

    @Test
    public void test4a() {
        List<Integer> a = new List<Integer>(1, new List<>(2, new List<>(3, new List<>(4, new List<>()))));
        boolean actual4a = Worksheet1.allPositive(a);
        boolean expected4a = true;

        assertEquals(expected4a, actual4a);
    }

    @Test
    public void test4b() {
        List<Integer> a = new List<Integer>(1, new List<>(-2, new List<>(3, new List<>(4, new List<>()))));
        boolean actual4b = Worksheet1.allPositive(a);
        boolean expected4b = false;

        assertEquals(expected4b, actual4b);
    }

    @Test
    public void test5a() {
        List<Integer> a = new List<Integer>(2, new List<>(3, new List<>(-5, new List<>(8, new List<>(-2, new List<>())))));
        List<Integer> actual5a = Worksheet1.positives(a);
        List<Integer> expected5a = new List<Integer>(2, new List<>(3, new List<>(8, new List<>())));

        assertEquals(expected5a, actual5a);
    }

    @Test
    public void test6a() {
        List<Integer> a = new List<Integer>(1, new List<>(2, new List<>(3, new List<>(4, new List<>()))));
        boolean actual6a = Worksheet1.sorted(a);
        boolean expected6a = true;

        assertEquals(expected6a, actual6a);
    }

    @Test
    public void test6b() {
        List<Integer> a = new List<Integer>(1, new List<>(2, new List<>(1, new List<>(4, new List<>()))));
        boolean actual6b = Worksheet1.sorted(a);
        boolean expected6b = false;

        assertEquals(expected6b, actual6b);
    }

    @Test
    public void test6c() {
        List<Integer> a = new List<Integer>();
        boolean actual6b = Worksheet1.sorted(a);
        boolean expected6b = true;

        assertEquals(expected6b, actual6b);
    }

    @Test
    public void test7a() {
        List<Integer> a = new List<Integer>(2, new List<>(5, new List<>(5, new List<>(8, new List<>()))));
        List<Integer> b = new List<Integer>(5, new List<>(7, new List<>(8, new List<>(9, new List<>()))));
        List<Integer> actual7a = Worksheet1.merge(a, b);
        List<Integer> expected7a = new List<Integer>(2, new List<>(5, new List<>(5, new List<>(5,
                new List<>(7, new List<>(8, new List<>(8, new List<>(9, new List<>()))))))));

        assertEquals(expected7a, actual7a);
    }

    @Test
    public void test8a() {
        List<Integer> a = new List<Integer>(2, new List<>(5, new List<>(5, new List<>(5,
                new List<>(7, new List<>(8, new List<>(8, new List<>(9, new List<>()))))))));
        List<Integer> actual8a = Worksheet1.removeDuplicates(a);
        List<Integer> expected8a = new List<Integer>(2, new List<>(5, new List<>(7, new List<>(8,
                new List<>(9, new List<>())))));

        assertEquals(expected8a, actual8a);
    }

    @Test
    public void test8b() {
        List<Integer> a = new List<Integer>();
        List<Integer> actual8a = Worksheet1.removeDuplicates(a);
        List<Integer> expected8a = new List<Integer>();

        assertEquals(expected8a, actual8a);
    }
}
