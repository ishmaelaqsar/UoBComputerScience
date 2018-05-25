package predictive;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author Ishmael Aqsar
 */
public class ListDictionaryTest {

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
