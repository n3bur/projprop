package domini;

import java.util.ArrayList;

/**
 * Disjoint-sets structure, implementing de Union Search and some other operations.
 * @author pau
 */
public class DJSets {

    private ArrayList<Integer> arr;

    public DJSets(int nElements) {
        arr = new ArrayList<Integer>();
        for (int i = 0; i < nElements; ++i) arr.add(-1);
    }

    /**
     * @return The number of elements contained in the disjoints sets.
     */
    public int size() {
        return arr.size();
    }

    /**
     * Union of two sets with root r1 and r2.
     * Pre: r1 != r2 and they are roots of their respective sets
     * @param r1 The root of a set
     * @param r2 The rot of a set, r1 != r2
     * @return The number of elements of the merged set.
      */
    public int union(int r1, int r2) {
        if (arr.get(r2) < arr.get(r1)) {
            arr.set(r2, arr.get(r1) + arr.get(r2));
            arr.set(r1, r2);
            return -1*arr.get(r2);
        }
        else {
            arr.set(r1, arr.get(r1) + arr.get(r2));
            arr.set(r2, r1);
            return -1*arr.get(r1);
        }
    }

    /**
     * @param i an element of the disjoint-sets.
     * @return the size of the set whose root is i, or -1 if i is no root
     * @throws IllegalArgumentException
     */
    public int sizeOf(int i) throws IllegalArgumentException{
        if (i >= arr.size()) throw new IllegalArgumentException("Unexisting position");
        else {
            if (arr.get(i) >= 0) return -1;
            else return -arr.get(i);
        }
    }

    /**
     * Find operation.
     * @param x the element to find.
     * @return the root of the set that contains x. Performs path compression along the way.
     * @throws IllegalArgumentException
     */
    public int find(int x) throws IllegalArgumentException {
        if (x >= arr.size()) throw new IllegalArgumentException("Trying to find an unexisting position.");
        if (arr.get(x) < 0) return x;
        else {
            arr.set(x, find(arr.get(x)));
            return arr.get(x);
        }
    }

    /**
     * Add an element (belonging to its own new set) to the structure.
     * @return the new number of elements.
     */
    public int add() {
        arr.add(-1);
        return arr.size() - 1;
    }
}
