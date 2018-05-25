package predictive;

/**
 * @author Ishmael Aqsar
 */
public class Sigs2WordsTree {

    public static void main(String[] args) {

        TreeDictionary list = new TreeDictionary("words");
        for (String arg : args) {
            System.out.println(arg + " : " + list.signatureToWords(arg));
        }
    }
}
