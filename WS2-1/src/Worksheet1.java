/**
 * @author Ishmael Aqsar
 * This class contains the solution for Worksheet1
 */

public class Worksheet1 extends List<Object> {

    // Exercise 1

    /**
     * Method of n complexity
     *
     * @param m - an integer
     * @param n - a positive integer
     * @return m raised to the power n
     */
    public static int power(int m, int n) {
        if (n < 0) throw new IllegalArgumentException("Method only works for positive powers.");
        if (n == 0) return 1;
        else return m * power(m, n - 1);
    }

    /**
     * Method of log2(n) complexity
     *
     * @param m - an integer
     * @param n - a positive integer
     * @return m raised to the power n
     */
    public static int fastPower(int m, int n) {
        if (n < 0) throw new IllegalArgumentException("Method only works for positive powers.");
        int x;
        if (n == 0) return 1;
        else if (n == 1) return m;
        else {
            x = fastPower(m, n / 2);
            x = x * x;
            if (n % 2 == 0) return x;
        }
        return m * x;
    }

    // Exercise 2

    /**
     * Method to reverse the signs of a list
     *
     * @param a - a list of integers
     * @return integer list with the signs reversed
     */
    public static List<Integer> negateAll(List<Integer> a) {
        if (a.isEmpty()) return a;
        else return new List<>(-a.getHead(), negateAll(a.getTail()));
    }

    // Exercise 3

    /**
     * Method to find the position of a specified number in a list
     *
     * @param x - integer to find
     * @param a - list of integers
     * @return position of integer within list a
     */
    public static int find(int x, List<Integer> a) {
        if (a.isEmpty()) throw new IllegalArgumentException("Number not in the list.");
        if (x == a.getHead()) return 0;
        else return 1 + find(x, a.getTail());
    }

    // Exercise 4

    /**
     * Method to find out whether a list is composed of only positive integers
     *
     * @param a - list of integers
     * @return true or false
     */
    public static boolean allPositive(List<Integer> a) {
        if (a.isEmpty()) return true;
        if (a.getHead() < 0) return false;
        else return allPositive(a.getTail());
    }

    // Exercise 5

    /**
     * Method that returns only the positive integers of a given list
     *
     * @param a - list of integers
     * @return new list of positive integers
     */
    public static List<Integer> positives(List<Integer> a) {
        if (a.isEmpty()) return a;
        if (a.getHead() >= 0) return new List<>(a.getHead(), positives(a.getTail()));
        else return positives(a.getTail());
    }

    // Exercise 6

    /**
     * Method to check whether a list is sorted in ascending order
     *
     * @param a - list of integers
     * @return true or false
     */
    public static boolean sorted(List<Integer> a) {
        if (a.isEmpty() || a.getTail().isEmpty()) return true;
        if (a.getHead() > a.getTail().getHead()) return false;
        else return sorted(a.getTail());
    }

    // Exercise 7

    /**
     * Method to merge two sorted integer lists with the new list sorted in ascending order
     *
     * @param a - list of integers
     * @param b - list of integers
     * @return new sorted list of integers
     */
    public static List<Integer> merge(List<Integer> a, List<Integer> b) {
        /*
         *  at the end of the first linked list
         *  return the second list
         */
        if (a.isEmpty()) return b;
        /*
         *   at the end of the second list
         *   return the first list
         */
        if (b.isEmpty()) return a;
        if (a.getHead() < b.getHead()) return new List<>(a.getHead(), merge(a.getTail(), b));
        else return new List<>(b.getHead(), merge(a, b.getTail()));
    }

    // Exercise 8

    /**
     * Method to remove duplicate entries from a list
     *
     * @param a - list of integers
     * @return new list of integers with no duplicates
     */
    static List<Integer> removeDuplicates(List<Integer> a) {
        if (a.isEmpty() || a.getTail().isEmpty()) return a;
        if (a.getHead() == a.getTail().getHead()) {
            return removeDuplicates(a.getTail());
        }
        else {
            return new List<>(a.getHead(), removeDuplicates(a.getTail()));
        }
    }

}