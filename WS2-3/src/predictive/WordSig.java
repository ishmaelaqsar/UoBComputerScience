package predictive;

/**
 * @author Ishmael Aqsar
 */
public class WordSig implements Comparable<WordSig> {
    private String words;
    private String signature;

    /**
     * Constructor
     *
     * @param words     - given word
     * @param signature - matching signature
     */
    public WordSig(String words, String signature) {
        this.words = words;
        this.signature = signature;
    }

    /**
     * Getter
     *
     * @return word
     */
    public String getWords() {
        return words;
    }

    /**
     * Getter
     *
     * @return signature
     */
    public String getSignature() {
        return signature;
    }

    @Override
    /**
     * compareTo to sort by signatures in ascending order
     * if signatures are the same, sort by word
     */
    public int compareTo(WordSig ws) {
        int check = signature.compareTo(ws.getSignature());

        if (check != 0) {
            return check;
        } else {
            return words.compareTo(ws.getWords());
        }
    }

    /**
     * if only a signature is given and no word
     * this method sorts by signatures only
     *
     * @param ws - WordSig object
     * @return int
     */
    public int compareToSig(WordSig ws) {
        return signature.compareTo(ws.getSignature());
    }
}
