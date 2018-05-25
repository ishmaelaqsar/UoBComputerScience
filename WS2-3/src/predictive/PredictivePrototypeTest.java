package predictive;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author Ishmael Aqsar
 */
public class PredictivePrototypeTest {

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

}
