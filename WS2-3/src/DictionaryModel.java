import predictive.TreeDictionary;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author Ishmael Aqsar
 */
public class DictionaryModel extends Observable implements DictionaryModelInterface {

    private TreeDictionary treeDictionary;
    private StringBuffer signature = new StringBuffer("");
    private Set<String> matches = new HashSet<>();
    private List<String> message = new ArrayList<>();
    private Iterator<String> iterator;
    private String currentMatch;

    /**
     * Constructor
     *
     * @param dictionaryFile - path to dictionary file
     * @throws java.io.IOException - dictionary not found
     */
    public DictionaryModel(String dictionaryFile) throws java.io.IOException {
        if (!(new File(dictionaryFile).exists())) throw new IOException("Dictionary not found.");
        treeDictionary = new TreeDictionary(dictionaryFile);
    }

    /**
     * Empty Constructor
     *
     * @throws java.io.IOException - dictionary not found
     */
    public DictionaryModel() throws java.io.IOException {
        this("dictionary");
    }

    /**
     * Returns a list of words typed in so far including the current prefix which has not yet been accepted
     *
     * @return list of Strings containing message
     */
    @Override
    public List<String> getMessage() {
        ArrayList<String> local = new ArrayList<>(message);
        if (currentMatch != null) local.add(currentMatch);
        return local;
    }

    /**
     * Extends the current signature by the numeric key typed in by the user and updates the current set of matches
     *
     * @param key - numeric input
     */
    @Override
    public void addCharacter(char key) {
        signature.append(key);
        matches = treeDictionary.signatureToWords(signature.toString());
        // if there are no matches for the current signature remove last character to avoid errors
        if (matches == null) removeLastCharacter();
        iterator = matches.iterator();
        currentMatch = iterator.next();
        setChanged();
        notifyObservers();
    }

    /**
     * Removes the last character of the current signature and updates the current set of matches
     */
    @Override
    public void removeLastCharacter() {
        if (signature.length() > 0) {
            signature.deleteCharAt(signature.length() - 1);
            matches = treeDictionary.signatureToWords(signature.toString());
            iterator = matches.iterator();
            currentMatch = iterator.next();
            setChanged();
            notifyObservers();
        }
    }

    /**
     * Cycles through the possible matches for the current signature
     */
    @Override
    public void nextMatch() {
        if (iterator.hasNext()) {
            currentMatch = iterator.next();
        } else {
            iterator = matches.iterator();
            currentMatch = iterator.next();
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Accepts the current matched word and adds it to the message
     */
    @Override
    public void acceptWord() {
        message.add(currentMatch);
        signature = new StringBuffer("");
        currentMatch = null;
    }
}
