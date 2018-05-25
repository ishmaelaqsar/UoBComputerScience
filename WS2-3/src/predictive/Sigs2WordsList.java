package predictive;

/**
 * @author Ishmael Aqsar
 */
public class Sigs2WordsList {

    public static void main(String[] args) {

        ListDictionary list = new ListDictionary("words");
        for (String arg : args) {
            System.out.println(arg + " : " + list.signatureToWords(arg));
        }
    }
}