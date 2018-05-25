package predictive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author Ishmael Aqsar
 */
public class MapDictionary implements Dictionary {

    private Map<String, Set<String>> dictionary = new HashMap<>();

    public MapDictionary(String path) {
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
        String signature = PredictivePrototype.wordToSignature(word);
        if (dictionary.containsKey(signature)) {
            Set<String> words = dictionary.get(signature);
            if (words == null) {
                words = new HashSet<>();
                dictionary.put(signature, words);
            }
            words.add(word.toLowerCase());
        } else {
            Set<String> words = new HashSet<>();
            dictionary.put(signature, words);
            words.add(word.toLowerCase());
        }
    }

    /**
     * Method to find the words which match a given signature
     *
     * @param signature - given signature
     * @return a String Set of matches to the signature
     */
    @Override
    public Set<String> signatureToWords(String signature) {
        // check if dictionary contains any possible words
        if (dictionary.containsKey(signature)) return dictionary.get(signature);
            // no matches
        else return new HashSet<>();
    }
}
