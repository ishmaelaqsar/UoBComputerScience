package predictive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author Ishmael Aqsar
 */
public class ListDictionary implements Dictionary {

    /**
     * ArrayList to store the matches from the signatureToWords method
     */
    private ArrayList<WordSig> dictionary = new ArrayList<>();

    /**
     * Constructor
     *
     * @param path - file path to dictionary
     */
    public ListDictionary(String path) {

        try {
            Scanner sc = new Scanner(new File(path));
            while (sc.hasNextLine()) {
                String word = sc.nextLine();
                // check if word is valid
                if (PredictivePrototype.isValidWord(word)) {
                    addDictionary(word);
                }
            }
            sc.close();
            // sort List by signatures
            dictionary.sort(WordSig::compareTo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to add words to dictionary
     *
     * @param word - String to add
     */
    private void addDictionary(String word) {
        // store as lower case
        word = word.toLowerCase();
        dictionary.add(new WordSig(word, PredictivePrototype.wordToSignature(word)));
    }

    /**
     * Method to find the words which match a given signature
     *
     * @param signature - given signature
     * @return a String Set of matches to the signature
     */
    @Override
    public Set<String> signatureToWords(String signature) {
        Set<String> possibleMatches = new HashSet<>();

        // return the index of the first match found
        int index = Collections.binarySearch(dictionary,
                new WordSig(null, signature), WordSig::compareToSig);

        // negative index means signature was not found
        if (index < 0) return possibleMatches;

        int i = index;

        int size = dictionary.size();

        // add matches to Set above and including the index and check for OutOfBoundsException
        while (i < size && dictionary.get(i).getSignature().equals(signature)) {
            possibleMatches.add(dictionary.get(i).getWords());
            i++;
        }

        i = index - 1;

        // add matches to Set below the index and check for OutOfBoundsException
        while (i >= 0 && dictionary.get(i).getSignature().equals(signature)) {
            possibleMatches.add(dictionary.get(i).getWords());
            i--;
        }
        return possibleMatches;
    }

}
