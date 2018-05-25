package predictive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author Ishmael Aqsar
 */
public class PredictivePrototype {

    /**
     * HashMap used to convert from words to signatures, works for upper and lower case characters
     */
    private final static Map<Character, Character> t9Keyboard = new HashMap<Character, Character>() {
        {
            put('a', '2');
            put('b', '2');
            put('c', '2');
            put('A', '2');
            put('B', '2');
            put('C', '2');

            put('d', '3');
            put('e', '3');
            put('f', '3');
            put('D', '3');
            put('E', '3');
            put('F', '3');

            put('g', '4');
            put('h', '4');
            put('i', '4');
            put('G', '4');
            put('H', '4');
            put('I', '4');

            put('j', '5');
            put('k', '5');
            put('l', '5');
            put('J', '5');
            put('K', '5');
            put('L', '5');

            put('m', '6');
            put('n', '6');
            put('o', '6');
            put('M', '6');
            put('N', '6');
            put('O', '6');

            put('p', '7');
            put('q', '7');
            put('r', '7');
            put('s', '7');
            put('P', '7');
            put('Q', '7');
            put('R', '7');
            put('S', '7');

            put('t', '8');
            put('u', '8');
            put('v', '8');
            put('T', '8');
            put('U', '8');
            put('V', '8');

            put('w', '9');
            put('x', '9');
            put('y', '9');
            put('z', '9');
            put('W', '9');
            put('X', '9');
            put('Y', '9');
            put('Z', '9');

        }
    };

    /**
     * Method to convert word to a signature i.e. home -> 4663
     * Using a StringBuffer for concatenation is faster than using a String.
     * This is because String objects are immutable so a temporary StringBuffer object is used for concatenation.
     * A new String object is then created from the temporary StringBuffer.
     * The creation of this temporary StringBuffer object and its subsequent conversion back into a String object
     * is very expensive. Using StringBuffer means there is no need to create a temporary StringBuffer Object.
     *
     * @param word - word to convert
     * @return signature of type String
     */
    public static String wordToSignature(String word) {
        StringBuffer sBuffer = new StringBuffer(word);
        for (int i = 0; i < word.length(); i++) {
            sBuffer.setCharAt(i, (Character.isAlphabetic(sBuffer.charAt(i)) ?
                    t9Keyboard.get((sBuffer.charAt(i))) : ' '));
        }
        return sBuffer.toString();
    }

    /**
     * Method to find the words which match a given signature
     *
     * @param signature - given signature
     * @return a String Set of matches to the signature
     */
    public static Set<String> signatureToWords(String signature) {
        Set<String> possibleWords = new HashSet<>();

        String path = "words";
        File file = new File(path);

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                // check if word is valid
                if (isValidWord(s) && Objects.equals(wordToSignature(s), signature)) {
                    // store as lower case
                    s = s.toLowerCase();
                    if (!possibleWords.contains(s)) {
                        possibleWords.add(s);
                    }
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return possibleWords;
    }

    /**
     * Method to check if given word only contains alphabetical characters
     *
     * @param word - word to check
     * @return true or false
     */
    public static boolean isValidWord(String word) {
        char[] chars = word.toCharArray();

        for (char c : chars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }

}
