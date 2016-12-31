/**
 * Helper methods used by all the sorting algorithms
 *
 * Created by S. Stefani on 2016-12-31.
 */

public abstract class Sort {
    /**
     * Check if element v is less than element w
     *
     * @param v is element v
     * @param w is element w
     * @return true if v is less than w, else false
     */
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * Exchange two elements in an array of comparable
     *
     * @param a is an array of Comparable elements
     * @param i is an element
     * @param j is another element
     */
    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * Print on a line all the elements in an array
     *
     * @param a is an array of Comparable elements
     */
    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    /**
     * Check if all the elements in an array are in sorted order
     *
     * @param a is an array of Comparable elements
     * @return true if the array is sorted, otherwise false
     */
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }
}