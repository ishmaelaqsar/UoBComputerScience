package predictive;

/**
 * Class used to compare the efficiency of different methods
 */

/**
 * @author Ishmael Aqsar
 */
public class Efficiency {

    public static void main(String[] args) {

        final long startTime1 = System.currentTimeMillis();
        for (String arg : args) {
            ListDictionary list = new ListDictionary("words");
            list.signatureToWords(arg);
        }
        final long endTime1 = System.currentTimeMillis();
        System.out.println("List Dictionary :  " + (endTime1 - startTime1));

        System.out.println();

        final long startTime2 = System.currentTimeMillis();
        for (String arg : args) {
            MapDictionary list = new MapDictionary("words");
            list.signatureToWords(arg);
        }
        final long endTime2 = System.currentTimeMillis();
        System.out.println("Map Dictionary  :  " + (endTime2 - startTime2));

        System.out.println();

        final long startTime3 = System.currentTimeMillis();
        for (String arg : args) {
            TreeDictionary list = new TreeDictionary("words");
            list.signatureToWords(arg);
        }
        final long endTime3 = System.currentTimeMillis();
        System.out.println("Tree Dictionary  :  " + (endTime3 - startTime3));

    }
}
