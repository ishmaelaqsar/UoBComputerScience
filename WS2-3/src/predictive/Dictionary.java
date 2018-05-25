package predictive;

import java.util.Set;

/**
 * @author Ishmael Aqsar
 */
public interface Dictionary {
    Set<String> signatureToWords(String signature);
}
