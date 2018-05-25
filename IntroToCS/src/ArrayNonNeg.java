import java.util.Arrays;

public class ArrayNonNeg {

    private int[] a;

    /**
     * Constructor.
     * Initializes a to an array of parameter length elements,
     * with each a[i] == i.
     * requires: length >= 0
     *
     * @param length value for length of array
     * @return Array of length elements
     * @throws NegativeArraySizeException     unless length >= 0
     * @throws ArrayIndexOutOfBoundsException unless index does not go out of bounds
     */
    public ArrayNonNeg(int length) {
        try {
            a = new int[length];
        } catch (NegativeArraySizeException e1) {
            throw new NegativeArraySizeException("Array length must be greater than or equal to 0");
        }

        try {
            int i = 0;
            while (i < a.length) {
                a[i] = i;
                i++;
            }
        } catch (ArrayIndexOutOfBoundsException e2) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
    //invariant:
    //   a != null
    //   every a[i] (0 <= i < a.length) is >= 0

    public static void main(String[] args) {
        ArrayNonNeg a = new ArrayNonNeg(8);
        System.out.println(Arrays.toString(a.getA()));
    }

    public int[] getA() {
        return a;
    }
}