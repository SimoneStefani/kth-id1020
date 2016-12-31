/**
 * Insertion sort
 *
 * Created by S. Stefani on 2016-12-31.
 */

public class Insertion extends Sort {
    public static void main(String[] args) {
        Integer[] a = {6, 8, 3, 1, 2, 4, 9, 5, 7};
        show(a);
        sort(a);
        assert isSorted(a);
        show(a);
    }

    public static void sort(Comparable[] a) {
        int n = a.length;

        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    }
}
