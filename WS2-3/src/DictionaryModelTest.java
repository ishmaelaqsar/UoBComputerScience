import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Ishmael Aqsar
 */
public class DictionaryModelTest {

    @Test
    public void test1() throws IOException {
        List<String> expected = new ArrayList<>();
        expected.add("home");

        DictionaryModel model = new DictionaryModel();
        model.addCharacter('4');
        model.addCharacter('6');
        model.addCharacter('6');
        model.addCharacter('3');
        model.nextMatch();
        model.nextMatch();
        model.acceptWord();

        List<String> actual = model.getMessage();

        assertEquals(expected, actual);
    }

    @Test
    public void test2() throws IOException {
        List<String> expected = new ArrayList<>();
        expected.add("hello");
        expected.add("world");
        expected.add("this");
        expected.add("is");
        expected.add("the");
        expected.add("input");

        DictionaryModel model = new DictionaryModel();
        model.addCharacter('4');
        model.addCharacter('3');
        model.addCharacter('5');
        model.addCharacter('5');
        model.addCharacter('6');
        model.nextMatch();
        model.acceptWord();
        model.addCharacter('9');
        model.addCharacter('6');
        model.addCharacter('7');
        model.addCharacter('5');
        model.addCharacter('3');
        model.acceptWord();
        model.addCharacter('8');
        model.addCharacter('4');
        model.addCharacter('4');
        model.addCharacter('7');
        model.acceptWord();
        model.addCharacter('4');
        model.addCharacter('7');
        model.acceptWord();
        model.addCharacter('8');
        model.addCharacter('4');
        model.addCharacter('3');
        model.acceptWord();
        model.addCharacter('4');
        model.addCharacter('6');
        model.addCharacter('7');
        model.addCharacter('8');
        model.addCharacter('8');
        model.nextMatch();
        model.acceptWord();

        List<String> actual = model.getMessage();

        assertEquals(expected, actual);
    }

    @Test(expected = IOException.class)
    public void test3() throws IOException {
        DictionaryModel model = new DictionaryModel("error");
    }

    @Test
    public void test4() throws IOException {
        List<String> expected = new ArrayList<>();
        expected.add("w");

        DictionaryModel model = new DictionaryModel();
        // "99" does not match any words in the dictionary
        model.addCharacter('9');
        model.addCharacter('9');
        model.acceptWord();

        List<String> actual = model.getMessage();

        assertEquals(expected, actual);

    }

}
