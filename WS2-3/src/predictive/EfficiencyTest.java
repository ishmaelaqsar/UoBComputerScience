package predictive;

/**
 * Class used to compare the efficiency of different methods
 */

/**
 * @author Ishmael Aqsar
 */
public class EfficiencyTest {

    public static void main(String[] args) {

        ListDictionary list = new ListDictionary("words");
        MapDictionary map = new MapDictionary("words");
        TreeDictionary tree = new TreeDictionary("words");

        final long startTime1 = System.currentTimeMillis();
        for (String arg : args) {
            PredictivePrototype.signatureToWords(arg);
        }
        final long endTime1 = System.currentTimeMillis();
        System.out.println("Prototype :  " + (endTime1 - startTime1));

        System.out.println();

        final long startTime2 = System.currentTimeMillis();
        for (String arg : args) {
            list.signatureToWords(arg);
        }
        final long endTime2 = System.currentTimeMillis();
        System.out.println("List      :  " + (endTime2 - startTime2));

        System.out.println();

        final long startTime3 = System.currentTimeMillis();
        for (String arg : args) {
            map.signatureToWords(arg);
        }
        final long endTime3 = System.currentTimeMillis();
        System.out.println("Map       :  " + (endTime3 - startTime3));

        System.out.println();

        final long startTime4 = System.currentTimeMillis();
        for (String arg : args) {
            tree.signatureToWordsHelper(arg);
        }
        final long endTime4 = System.currentTimeMillis();
        System.out.println("Tree      :  " + (endTime4 - startTime4));

    }
}
