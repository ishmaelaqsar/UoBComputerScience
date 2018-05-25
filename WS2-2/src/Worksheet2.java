/**
 * @author Ishmael Aqsar
 * <p>
 * This class contains the solution for Worksheet2
 */

public class Worksheet2 extends ListOpsGeneric implements Worksheet2Interface {

    // Exercise 1

    /**
     * Method to change the +/- signs of the integers in a tree.
     *
     * @param t - Tree of integers
     * @return tree t with the +/- flipped i.e. positives become negative and vice versa
     */
    public static Tree<Integer> negateAll(Tree<Integer> t) {

        if (t.isEmpty()) {
            return t;
        } else {
            return new Tree<>(-t.getValue(), negateAll(t.getLeft()), negateAll(t.getRight()));
        }
    }

    // Exercise 2

    /**
     * Method which checks if all the integers in a tree are positive
     *
     * @param a - Tree of integers
     * @return boolean
     */
    public static boolean allPositive(Tree<Integer> a) {
        if (a.isEmpty()) {
            return true;
        } else if (a.getValue() < 0) {
            return false;
        } else {
            return allPositive(a.getLeft()) && allPositive(a.getRight());
        }
    }

    // Exercise 3

    /**
     * Method to mirror a given tree
     *
     * @param t - Generic Tree
     * @return mirrored version of Tree t
     */
    public static <E> Tree<E> mirror(Tree<E> t) {
        if (t.isEmpty()) {
            return t;
        } else {
            return new Tree<>(t.getValue(), mirror(t.getRight()), mirror(t.getLeft()));
        }
    }

    // Exercise 4

    /**
     * Method to convert a tree to a list using post order traversal
     *
     * @param t - Generic Tree
     * @return List of data from Tree t
     */
    public static <E> List<E> postorder(Tree<E> t) {
        if (t.isEmpty()) {
            return new List<>();
        } else {
            return ListOpsGeneric.append(postorder(t.getLeft()),
                    append(postorder(t.getRight()), new List<>(t.getValue(), new List<>())));
        }
    }

    // Exercise 5

    /**
     * Method to check if a tree is a Binary Search Tree
     *
     * @param a - Tree of integers
     * @return boolean
     */
    public static boolean isSearchTree(Tree<Integer> a) {
        if (a.getLeft().getValue() > a.getValue()) {
            return false;
        }
        if (a.getRight().getValue() < a.getValue()) {
            return false;
        }
        if (!a.getLeft().getLeft().isEmpty()) {
            return isSearchTree(a.getLeft());
        } else if (!a.getRight().getRight().isEmpty()) {
            return isSearchTree(a.getRight());
        } else {
            return true;
        }
    }

    // Exercise 6

    /**
     * Method to the integers of a tree in descending order
     *
     * @param a - Tree of integers
     */
    public static void printDescending(Tree<Integer> a) {
        if (a.isEmpty()) {
            return;
        }
        printDescending(a.getRight());
        System.out.print(a.getValue() + " ");
        printDescending(a.getLeft());
    }

    // Exercise 7

    /**
     * Method to return the maximum integer value within a tree
     *
     * @param a - Tree of integers
     * @return Maximum integer
     */
    public static int max(Tree<Integer> a) {
        if (a.getRight().isEmpty()) {
            return a.getValue();
        } else {
            return max(a.getRight());
        }
    }

    // Exercise 8

    /**
     * Method to delete a specified value within a tree
     *
     * @param a - Tree of integers
     * @param x - value to remove from tree a
     * @return Tree a without x
     */
    public static Tree<Integer> delete(Tree<Integer> a, int x) {
        if (a.isEmpty()) {
            throw new IllegalArgumentException(x + " is not in the tree");
        }
        if (x < a.getValue()) {
            return new Tree<>(a.getValue(), delete(a.getLeft(), x), a.getRight());
        } else if (x > a.getValue()) {
            return new Tree<>(a.getValue(), a.getLeft(), delete(a.getRight(), x));
        } else {
            return new Tree<>();
        }
    }

    /**
     * Method to insert a specified value into a tree
     *
     * @param x - value to insert into tree
     * @param a - Tree of Integers
     * @return Tree a with x
     */
    public static Tree<Integer> insert(int x, Tree<Integer> a) {
        if (a.isEmpty()) {
            return new Tree<>(x, new Tree<>(), new Tree<>());
        } else if (x <= a.getValue()) {
            return new Tree<>(a.getValue(), insertHB(x, a.getLeft()), a.getRight());
        } else {
            return new Tree<>(a.getValue(), a.getLeft(), insertHB(x, a.getRight()));
        }
    }

    // Exercise 9

    /**
     * Method to check if a Tree is a height balanced AVL Tree
     *
     * @param a - Generic Tree
     * @return boolean
     */
    public static <E> boolean isHeightBalanced(Tree<E> a) {
        if (a.isEmpty()) {
            return true;
        } else {
            return (Math.abs(getBalance(a)) > 1 ? false : true);
        }
    }

    /**
     * Method to calculate the balance of a tree (Balance = rightHeight - leftHeight)
     *
     * @param a - Generic Tree
     * @return balance
     */
    public static <E> int getBalance(Tree<E> a) {
        return a.getRight().getHeight() - a.getLeft().getHeight();
    }

    // Exercise 10

    /**
     * Method to insert a specified value into a Tree while maintaining its AVL Tree property
     *
     * @param x - Value to insert
     * @param a - Tree of integers
     * @return AVL Tree with x
     */
    public static Tree<Integer> insertHB(int x, Tree<Integer> a) {
        a = insert(x, a);

        // check if tree needs to be balanced
        if (isHeightBalanced(a)) {
            // already balanced
            return a;
        } else if (getBalance(a) < -1) {
            // left heavy tree
            return rightRotate(a);
        } else {
            // right heavy tree
            return leftRotate(a);
        }
    }

    /**
     * Method to remove a specified value from a Tree while maintaining its AVL Tree property
     *
     * @param a - Tree of integers
     * @param x - value to remove
     * @return AVL Tree without x
     */
    public static Tree<Integer> deleteHB(Tree<Integer> a, int x) {
        a = delete(a, x);

        // check if tree needs to be balanced
        if (isHeightBalanced(a)) {
            // already balanced
            return a;
        } else if (getBalance(a) < -1) {
            // left heavy tree
            return rightRotate(a);
        } else {
            // right heavy tree
            return leftRotate(a);
        }
    }


    /**
     * Method to left rotate a right heavy tree
     *
     * @param a - right heavy tree
     * @return balanced AVL tree
     */
    public static Tree<Integer> leftRotate(Tree<Integer> a) {
        if (getBalance(a.getRight()) > 0) {
            // right-right tree

            // old right tree
            Tree<Integer> r = a.getRight();

            // new right tree
            Tree<Integer> n = new Tree<>(a.getValue(), a.getLeft(), r.getLeft());

            // new tree combining the new right tree and the old right tree
            Tree<Integer> l = new Tree<>(r.getValue(), n, r.getRight());

            // return balanced AVL tree
            return l;
        } else {
            // right-left tree

            // old right tree
            Tree<Integer> r = a.getRight();
            // old left tree of old right tree
            Tree<Integer> l = r.getLeft();

            // new right tree combining old right tree and old left tree of said right tree
            Tree<Integer> nr = new Tree<>(r.getValue(), l.getRight(), r.getRight());

            // new left tree combining old left tree of old right tree and new right tree
            Tree<Integer> nl = new Tree<>(l.getValue(), l.getLeft(), nr);

            // new tree combining old left tree and new left tree
            Tree<Integer> L = new Tree<>(a.getValue(), a.getLeft(), nl);

            // return a right-right
            return leftRotate(L);
        }
    }

    /**
     * Method to right rotate a left heavy tree
     *
     * @param a - left heavy tree
     * @return balanced AVL tree
     */
    public static Tree<Integer> rightRotate(Tree<Integer> a) {
        if (getBalance(a.getLeft()) < 0) {
            // left-left tree

            // old left tree
            Tree<Integer> l = a.getLeft();

            // new left tree
            Tree<Integer> n = new Tree<>(a.getValue(), l.getRight(), a.getRight());

            // new tree combining old left tree and new left tree
            Tree<Integer> r = new Tree<>(l.getValue(), l.getLeft(), n);

            // return balanced AVL tree
            return r;
        } else {
            // left-right tree

            // old left tree
            Tree<Integer> l = a.getLeft();
            // old right tree
            Tree<Integer> r = l.getRight();

            // new left tree combining old left tree and old right tree
            Tree<Integer> nl = new Tree<>(l.getValue(), l.getLeft(), r.getLeft());

            // new right tree combining new left tree and old right tree
            Tree<Integer> nr = new Tree<>(r.getValue(), nl, r.getRight());

            // new tree combining new right tree and old right tree
            Tree<Integer> R = new Tree<>(a.getValue(), nr, a.getRight());

            // return a left-left tree
            return rightRotate(R);
        }
    }
}
