import java.io.IOException;
import java.util.List;

/**
 * @author Uday Reddy
 * <p>
 * This is the interface for your solutions for Worksheet4.
 * Do not modify this file.
 * Submit it along with your solution.
 */

public interface DictionaryModelInterface {

    List<String> getMessage();

    void addCharacter(char key) throws IOException;

    void removeLastCharacter();

    void nextMatch();

    void acceptWord();
}
