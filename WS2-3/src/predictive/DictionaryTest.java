package predictive;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author Ishmael Aqsar
 */
public class DictionaryTest {

    @Test
    public void test1a() {
        String word = "home";
        String actual = PredictivePrototype.wordToSignature(word);
        String expected = "4663";

        assertEquals(expected, actual);
    }

    @Test
    public void test1b() {
        String word = "h0me";
        String actual = PredictivePrototype.wordToSignature(word);
        String expected = "4 63";

        assertEquals(expected, actual);
    }

    @Test
    public void test1c() {
        String word = "2D";
        String actual = PredictivePrototype.wordToSignature(word);
        String expected = " 3";

        assertEquals(expected, actual);
    }

    @Test
    public void test1d() {
        String word = "HOME";
        String actual = PredictivePrototype.wordToSignature(word);
        String expected = "4663";

        assertEquals(expected, actual);
    }

    @Test
    public void test2a() {
        String path = "dictionary";
        String signature = "4663";
        ListDictionary list = new ListDictionary(path);
        Set<String> actual = list.signatureToWords(signature);
        Set<String> expected = new HashSet<>();
        expected.add("home");
        expected.add("homf");
        expected.add("homd");
        expected.add("iome");

        assertEquals(expected, actual);
    }

    @Test
    public void test2b() {
        String path = "dictionary";
        String signature = "253926337";
        ListDictionary list = new ListDictionary(path);
        Set<String> actual = list.signatureToWords(signature);
        Set<String> expected = new HashSet<>();
        expected.add("alexander");

        assertEquals(expected, actual);
    }

    @Test
    public void test2c() {
        String path = "dictionary";
        String signature = "";
        ListDictionary list = new ListDictionary(path);
        Set<String> actual = list.signatureToWords(signature);
        Set<String> expected = new HashSet<>();

        assertEquals(expected, actual);
    }

    @Test
    public void test3a() {
        String path = "dictionary";
        String signature = "4663";
        MapDictionary list = new MapDictionary(path);
        Set<String> actual = list.signatureToWords(signature);
        Set<String> expected = new HashSet<>();
        expected.add("home");
        expected.add("homf");
        expected.add("homd");
        expected.add("iome");

        assertEquals(expected, actual);
    }

    @Test
    public void test3b() {
        String path = "dictionary";
        String signature = "253926337";
        MapDictionary list = new MapDictionary(path);
        Set<String> actual = list.signatureToWords(signature);
        Set<String> expected = new HashSet<>();
        expected.add("alexander");

        assertEquals(expected, actual);
    }

    @Test
    public void test3c() {
        String path = "dictionary";
        String signature = "";
        MapDictionary list = new MapDictionary(path);
        Set<String> actual = list.signatureToWords(signature);
        Set<String> expected = new HashSet<>();

        assertEquals(expected, actual);
    }

    @Test
    public void test4a() {
        String path = "dictionary";
        String signature = "4663";
        TreeDictionary list = new TreeDictionary(path);
        Set<String> actual = list.signatureToWords(signature);
        Set<String> expected = new HashSet<>();
        expected.add("home");
        expected.add("homf");
        expected.add("homd");
        expected.add("iome");

        assertEquals(expected, actual);
    }

    @Test
    public void test4b() {
        String path = "dictionary";
        String signature = "253926337";
        TreeDictionary list = new TreeDictionary(path);
        Set<String> actual = list.signatureToWords(signature);
        Set<String> expected = new HashSet<>();
        expected.add("alexander");

        assertEquals(expected, actual);
    }

    @Test
    public void test4c() {
        String path = "dictionary";
        String signature = "";
        TreeDictionary list = new TreeDictionary(path);
        Set<String> actual = list.signatureToWords(signature);
        Set<String> expected = new HashSet<>();

        assertEquals(expected, actual);
    }

    @Test
    public void test4d() {
        String path = "dictionary";
        String signature = "99";
        TreeDictionary list = new TreeDictionary(path);
        Set<String> actual = list.signatureToWords(signature);
        Set<String> expected = null;

        assertEquals(expected, actual);
    }

}
