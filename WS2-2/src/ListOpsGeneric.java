// The class ListOps defines a number of static methods to work with
// lists using the List class.

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

class ListOpsGeneric extends List {

    // SECTION 1: GENERIC EXAMPLES

    // append creates a new list by appending two given lists
    // It is generic in the element type E.

    static <E> List<E> append(List<E> a, List<E> b) {
        if (a.isEmpty())
            return b;
        else
            return new List(a.getHead(), append(a.getTail(), b));
    }

    // addtoend creates a new list by adding an element at the end of
    // the given list, also generic in the element type E.

    static <E> List<E> addtoend(List<E> a, E x) {
        return append(a, new List(x, new List()));
    }

    // reverse creates a reversed version of a given list, i.e., the
    // same elements appear but in reverse order, also generic in the
    // element type E.

    static <E> List<E> reverse(List<E> a) {
        if (a.isEmpty())
            return new List();
        else
            return addtoend(reverse(a.getTail()), a.getHead());
    }

    // max returns the maximum element in a non-empty list of Comparables.
    // throws an exception if the list is empty.

    // This is also generic in the element type E, but the element
    // type must be a subtype of Comparable<E>, i.e., it should have a
    // 'compareTo' operation.

    static <E extends Comparable<E>> E max(List<E> a) {
        if (a.isEmpty())
            throw new IllegalArgumentException();
        else if (a.getTail().isEmpty())
            return a.getHead();
        else
            return max2(a.getHead(), max(a.getTail()));
    }

    // binary max function, also generic in the type E.

    static <E extends Comparable<E>> E max2(E x, E y) {
        if (x.compareTo(y) <= 0)
            return y;
        else
            return x;
    }


    // SECTION 2: HIGHER-ORDER EXAMPLES

    // map: a higher-order function
    // Applies a function to all the elements of a list to obtain a new list.

    // This is generic in two types, E and R, the element types of the
    // input list and output list respectively. The first argument is
    // a function from E to R, which will get "mapped" to all the
    // elements of the input list.

    static <E, R> List<R> map(Function<E, R> fun, List<E> a) {
        if (a.isEmpty())
            return new List();
        else
            return new List(fun.apply(a.getHead()), map(fun, a.getTail()));
    }

    // filter: a higher-order function
    // Checks a predicate for all the elements of a list, and returns
    // a list of those elements that satisfy the predicate.
    // Note that the output list is of the type as the input list.

    static <E> List<E> filter(Predicate<E> pred, List<E> a) {
        if (a.isEmpty())
            return new List();
        else if (pred.test(a.getHead()))
            return new List(a.getHead(), ListOpsGeneric.filter(pred, a.getTail()));
        else
            return filter(pred, a.getTail());
    }

    // reduce: a higher-order function
    // Applies a binary operator between all the elements of a list,
    // and returns the resulting value.
    // The identity parameter is the expected result for an empty list.

    static <E> E reduce(BinaryOperator<E> op, List<E> a, E identity) {
        if (a.isEmpty())
            return identity;
        else
            return op.apply(a.getHead(), reduce(op, a.getTail(), identity));
    }

    // fold: a higher-order function, more flexible than reduce
    // Applies a binary operator between all the elements of a list,
    // and returns the resulting value.
    // The identity parameter is the expected result for an empty list.

    static <E, R> R fold(BiFunction<E, R, R> op, List<E> a, R identity) {
        if (a.isEmpty())
            return identity;
        else
            return op.apply(a.getHead(), fold(op, a.getTail(), identity));
    }

    // Sum all the elements of an integer list
    // This uses the higher-order function reduce.

    static Integer sumShort(List<Integer> a) {
        return reduce((x, y) -> x + y, a, 0);
    }

    // Select all the positive values of an integer list.
    // This uses the higher-order function filter.

    static List<Integer> positives(List<Integer> a) {
        return filter((x) -> x >= 0, a);
    }

    // Find the maximum of a generic list.
    // This uses the higher-order function reduce.

    static <E extends Comparable<E>> E maxShort(List<E> a) {
        return reduce((x, y) -> max2(x, y), a.getTail(), a.getHead());
        // Think: what does this return for a singleton list?
    }

    // Append two generic lists
    // This uses the higher-order function fold.

    static <E> List<E> appendShort(List<E> a, List<E> b) {
        return fold((x, y) -> new List(x, y), a, b);
    }

    // cons and nil are short hand for the two constructors of List class.
    // They are given here just for illustration.

    static <E> List<E> cons(E x, List<E> t) {
        return new List<E>(x, t);
    }

    static <E> List<E> nil() {
        return new List<E>();
    }


}
