/**
 * Bubble sort
 *
 * Created by S. Stefani on 2016-12-31.
 */

public class Bubble extends Sort {
    public static void main(String[] args) {
        Integer[] a = {6, 3, 8, 1, 2, 9, 4, 5, 7};
        show(a);
        sort(a);
        assert isSorted(a);
        show(a);
    }

    public static void sort(Comparable[] a) {
        int n = a.length;
        boolean swapped = true;

        while (n >= 0 && swapped) {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (less(a[i + 1], a[i])) {
                    swapped = true;
                    exch(a, i+1, i);
                }
            }
        }
    }
}
