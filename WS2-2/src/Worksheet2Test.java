import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * @author Ishmael Aqsar
 */

public class Worksheet2Test {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    public void test1a() {
        Tree<Integer> t = new Tree<>(6, new Tree<>(-4, new Tree<>(-5), new Tree<>(2)),
                new Tree<>(10, new Tree<>(-8), new Tree<>(12)));
        Tree<Integer> actual = Worksheet2.negateAll(t);
        Tree<Integer> expected = new Tree<>(-6, new Tree<>(4, new Tree<>(5), new Tree<>(-2)),
                new Tree<>(-10, new Tree<>(8), new Tree<>(-12)));

        assertEquals(expected, actual);
    }

    @Test
    public void test1b() {
        Tree<Integer> t = new Tree<>();
        Tree<Integer> actual = Worksheet2.negateAll(t);
        Tree<Integer> expected = new Tree<>();

        assertEquals(expected, actual);
    }

    @Test
    public void test2a() {
        Tree<Integer> t = new Tree<>(6, new Tree<>(4, new Tree<>(5), new Tree<>(2)),
                new Tree<>(10, new Tree<>(8), new Tree<>(12)));
        boolean actual = Worksheet2.allPositive(t);
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void test2b() {
        Tree<Integer> t = new Tree<>(6, new Tree<>(-4, new Tree<>(-5), new Tree<>(2)),
                new Tree<>(10, new Tree<>(-8), new Tree<>(12)));
        boolean actual = Worksheet2.allPositive(t);
        boolean expected = false;

        assertEquals(expected, actual);
    }

    @Test
    public void test2c() {
        Tree<Integer> t = new Tree<>(10, new Tree<>(5, new Tree<>(2), new Tree<>(7)),
                new Tree<>(20, new Tree<>(-17), new Tree<>(30)));
        boolean actual = Worksheet2.allPositive(t);
        boolean expected = false;

        assertEquals(expected, actual);
    }

    @Test
    public void test2d() {
        Tree<Integer> t = new Tree<>(0);
        boolean actual = Worksheet2.allPositive(t);
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void test3a() {
        Tree<Integer> t = new Tree<>(6, new Tree<>(-4, new Tree<>(-5), new Tree<>(2)),
                new Tree<>(10, new Tree<>(-8), new Tree<>(12)));
        Tree<Integer> actual = Worksheet2.mirror(t);
        Tree<Integer> expected = new Tree<>(6, new Tree<>(10, new Tree<>(12), new Tree<>(-8)),
                new Tree<>(-4, new Tree<>(2), new Tree<>(-5)));

        assertEquals(expected, actual);
    }

    @Test
    public void test3b() {
        Tree<Integer> t = new Tree<>(5, new Tree<>(3, new Tree<>(1), new Tree<>(4)),
                new Tree<>(8, new Tree<>(6), new Tree<>()));
        Tree<Integer> actual = Worksheet2.mirror(t);
        Tree<Integer> expected = new Tree<>(5, new Tree<>(8, new Tree<>(), new Tree<>(6)),
                new Tree<>(3, new Tree<>(4), new Tree<>(1)));

        assertEquals(expected, actual);
    }

    @Test
    public void test4a() {
        Tree<Integer> t = new Tree<>(1, new Tree<>(2, new Tree<>(3), new Tree<>(5)),
                new Tree<>(4));
        List<Integer> actual = Worksheet2.postorder(t);
        List<Integer> expected = new List<>(3, new List<>(5, new List<>(2,
                new List<>(4, new List<>(1, new List<>())))));

        assertEquals(expected, actual);
    }

    @Test
    public void test4b() {
        Tree<Integer> t = new Tree<>(10, new Tree<>(6, new Tree<>(4), new Tree<>(8)),
                new Tree<>(15, new Tree<>(12), new Tree<>(18)));
        List<Integer> actual = Worksheet2.postorder(t);
        List<Integer> expected = new List<>(4, new List<>(8, new List<>(6,
                new List<>(12, new List<>(18, new List<>(15, new List<>(10, new List<>())))))));

        assertEquals(expected, actual);
    }

    @Test
    public void test5a() {
        Tree<Integer> t = new Tree<>(40, new Tree<>(20, new Tree<>(10), new Tree<>(30)),
                new Tree<>(60, new Tree<>(50), new Tree<>(70)));
        boolean actual = Worksheet2.isSearchTree(t);
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void test5b() {
        Tree<Integer> t = new Tree<>(1, new Tree<>(2, new Tree<>(3), new Tree<>(5)),
                new Tree<>(4));
        boolean actual = Worksheet2.isSearchTree(t);
        boolean expected = false;

        assertEquals(expected, actual);
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
    }


    @Test
    public void test6a() {
        Tree<Integer> t = new Tree<>(40, new Tree<>(20, new Tree<>(10), new Tree<>(30)),
                new Tree<>(60, new Tree<>(50), new Tree<>(70)));

        Worksheet2.printDescending(t);

        assertEquals("70 60 50 40 30 20 10 ", outContent.toString());
    }

    @Test
    public void test6b() {
        Tree<Integer> t = new Tree<>(4, new Tree<>(2, new Tree<>(1), new Tree<>(3)),
                new Tree<>(5));

        Worksheet2.printDescending(t);

        assertEquals("5 4 3 2 1 ", outContent.toString());
    }

    @Test
    public void test7a() {
        Tree<Integer> t = new Tree<>(40, new Tree<>(20, new Tree<>(10), new Tree<>(30)),
                new Tree<>(60, new Tree<>(50), new Tree<>(70)));
        int actual = Worksheet2.max(t);
        int expected = 70;

        assertEquals(expected, actual);
    }

    @Test
    public void test8a() {
        Tree<Integer> t = new Tree<>(40, new Tree<>(20, new Tree<>(10), new Tree<>(30)),
                new Tree<>(60, new Tree<>(50), new Tree<>(70)));
        Tree<Integer> actual = Worksheet2.delete(t, 70);
        Tree<Integer> expected = new Tree<>(40, new Tree<>(20, new Tree<>(10), new Tree<>(30)),
                new Tree<>(60, new Tree<>(50), new Tree<>()));

        assertEquals(expected, actual);
    }

    @Test
    public void test8b() {
        Tree<Integer> t = new Tree<>(4, new Tree<>(2, new Tree<>(1), new Tree<>(3)),
                new Tree<>(5));
        Tree<Integer> actual = Worksheet2.delete(t, 1);
        Tree<Integer> expected = new Tree<>(4, new Tree<>(2, new Tree<>(), new Tree<>(3)),
                new Tree<>(5));

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test8c() {
        Tree<Integer> t = new Tree<>(4, new Tree<>(2, new Tree<>(1), new Tree<>(3)),
                new Tree<>(5));
        Worksheet2.delete(t, 100);
    }

    @Test
    public void test9a() {
        Tree<Integer> t = new Tree<>(40, new Tree<>(20, new Tree<>(10), new Tree<>(30)),
                new Tree<>(60, new Tree<>(50), new Tree<>(70)));
        boolean actual = Worksheet2.isHeightBalanced(t);
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void test9b() {
        Tree<Integer> t = new Tree<>(10, new Tree<>(8, new Tree<>(6,
                new Tree<>(5), new Tree<>()), new Tree<>(9)), new Tree<>(15));
        boolean actual = Worksheet2.isHeightBalanced(t);
        boolean expected = false;

        assertEquals(expected, actual);
    }

    @Test
    public void test9c() {
        Tree<Integer> t = new Tree<>();
        boolean actual = Worksheet2.isHeightBalanced(t);
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void test10a() {
        Tree<Integer> t = new Tree<>(10, new Tree<>(8, new Tree<>(6), new Tree<>(9)),
                new Tree<>(15));
        boolean actual = Worksheet2.isHeightBalanced(Worksheet2.insertHB(5, t));
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void test10b() {
        Tree<Integer> t = new Tree<>(10, new Tree<>(8, new Tree<>(6), new Tree<>(9)),
                new Tree<>(15));
        Tree<Integer> actual = Worksheet2.insertHB(5, t);
        Tree<Integer> expected = new Tree<>(8, new Tree<>(6, new Tree<>(5), new Tree<>()),
                new Tree<>(10, new Tree<>(9), new Tree<>(15)));

        assertEquals(expected, actual);
    }

    @Test
    public void test10c() {
        Tree<Integer> t = new Tree<>(5, new Tree<>(2),
                new Tree<>(10, new Tree<>(8), new Tree<>(12)));
        Tree<Integer> actual = Worksheet2.insertHB(7, t);
        Tree<Integer> expected = new Tree<>(8, new Tree<>(5, new Tree<>(2), new Tree<>(7)),
                new Tree<>(10, new Tree<>(), new Tree<>(12)));

        assertEquals(expected, actual);
    }

    @Test
    public void test10d() {
        Tree<Integer> t = new Tree<>(12, new Tree<>(8, new Tree<>(6), new Tree<>(9)),
                new Tree<>(15));
        Tree<Integer> actual = Worksheet2.insertHB(10, t);
        Tree<Integer> expected = new Tree<>(9, new Tree<>(8, new Tree<>(6), new Tree<>()),
                new Tree<>(12, new Tree<>(10), new Tree<>(15)));

        assertEquals(expected, actual);
    }

    @Test
    public void test10e() {
        Tree<Integer> t = new Tree<>(10, new Tree<>(8, new Tree<>(6), new Tree<>(9)),
                new Tree<>(15));
        boolean actual = Worksheet2.isHeightBalanced(Worksheet2.deleteHB(t, 15));
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void test10f() {
        Tree<Integer> t = new Tree<>(10, new Tree<>(8, new Tree<>(6), new Tree<>(9)),
                new Tree<>(15));
        Tree<Integer> actual = Worksheet2.deleteHB(t, 15);
        Tree<Integer> expected = new Tree<>(9, new Tree<>(8, new Tree<>(6), new Tree<>()),
                new Tree<>(10));

        assertEquals(expected, actual);
    }

    @Test
    public void test10g() {
        Tree<Integer> t = new Tree<>(5, new Tree<>(2),
                new Tree<>(10, new Tree<>(8), new Tree<>(12, new Tree<>(11), new Tree<>(15))));
        Tree<Integer> actual = Worksheet2.deleteHB(t, 11);
        Tree<Integer> expected = new Tree<>(10, new Tree<>(5, new Tree<>(2), new Tree<>(8)),
                new Tree<>(12, new Tree<>(), new Tree<>(15)));

        assertEquals(expected, actual);
    }

    @Test
    public void test10h() {
        Tree<Integer> t = new Tree<>(5, new Tree<>(2),
                new Tree<>(10, new Tree<>(8, new Tree<>(6), new Tree<>(9)), new Tree<>(12)));
        Tree<Integer> actual = Worksheet2.deleteHB(t, 9);
        Tree<Integer> expected = new Tree<>(8, new Tree<>(5, new Tree<>(2), new Tree<>(6)),
                new Tree<>(10, new Tree<>(), new Tree<>(12)));

        assertEquals(expected, actual);
    }

}