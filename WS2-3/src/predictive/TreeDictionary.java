package predictive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TreeDictionary implements Dictionary {

    private TreeDictionary[] treeDictionary = new TreeDictionary[8];
    private Set<String> words;

    /**
     * Constructor to create empty trees and set
     */
    public TreeDictionary() {
        int length = treeDictionary.length;
        for (int i = 0; i < length; i++) {
            treeDictionary[i] = null;
        }
        words = new HashSet<>();
    }

    /**
     * Constructor to initialize Trees and Set to store the words
     *
     * @param path - the path to the dictionary
     */
    public TreeDictionary(String path) {
        int length = treeDictionary.length;

        for (int i = 0; i < length; i++) {
            treeDictionary[i] = new TreeDictionary();
        }

        words = new HashSet<>();

        try {
            Scanner sc = new Scanner(new File(path));
            while (sc.hasNextLine()) {
                String word = sc.nextLine();
                // check if word is valid
                if (PredictivePrototype.isValidWord(word)) {
                    String signature = PredictivePrototype.wordToSignature(word);
                    // create dictionary for first character
                    int key = Integer.parseInt(signature.substring(0, 1));
                    // now move on to second character
                    treeDictionary[key - 2].addDictionary(word, signature, 1);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to add signature to the Tree nodes
     *
     * @param word             - word to add to dictionary
     * @param signature        - signature
     * @param currentCharacter - current letter being processed
     */
    private void addDictionary(String word, String signature, int currentCharacter) {
        words.add(word.toLowerCase());
        // check to see if current signature is at last character
        if (currentCharacter < signature.length()) {
            int key = Integer.parseInt(signature.substring(currentCharacter, currentCharacter + 1));
            if (treeDictionary[key - 2] == null) treeDictionary[key - 2] = new TreeDictionary();
            treeDictionary[key - 2].addDictionary(word, signature, ++currentCharacter);
        }
    }

    /**
     * Method to find the words which match a given signature
     *
     * @param signature - given signature
     * @return a String Set of exact matches (whole words) to the signature
     */
    @Override
    public Set<String> signatureToWords(String signature) {
        int length = signature.length();
        Set<String> wholeWords = new HashSet<>();
        // cut down the returned set to only those with the same length as the signature
        Set<String> temp = signatureToWordsHelper(signature);
        if (temp != null) {
            signatureToWordsHelper(signature).forEach((word -> wholeWords.add(word.substring(0, length))));
            return wholeWords;
        }
        return temp;
    }

    /**
     * Helper method to find words which match a given signature by traversing through the tree dictionary
     * using the key presses.
     *
     * @param signature
     * @return set of words and prefixes beginning with signature
     */
    public Set<String> signatureToWordsHelper(String signature) {
        // if empty return empty set
        if (signature.isEmpty()) return words;
        else {
            int key = Integer.parseInt(signature.substring(0, 1));
            if (treeDictionary[key - 2] == null) {
                return null;
            }
            return treeDictionary[key - 2].signatureToWordsHelper(signature.substring(1));
        }
    }

    public static void main(String[] args) {
        String path = "dictionary";
        String signature = "99";
        TreeDictionary list = new TreeDictionary(path);
        Set<String> actual = list.signatureToWords(signature);
    }
}