package predictive;

/**
 * @author Ishmael Aqsar
 */
public class Words2SigProto {

    public static void main(String[] args) {

        for (String arg : args) {
            System.out.println(arg + " : " + PredictivePrototype.wordToSignature(arg));
        }
    }
}