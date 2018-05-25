package predictive;

/**
 * @author Ishmael Aqsar
 */
public class Sigs2WordsMap {

    public static void main(String[] args) {

        MapDictionary list = new MapDictionary("words");
        for (String arg : args) {
            System.out.println(arg + " : " + list.signatureToWords(arg));
        }
    }
}
