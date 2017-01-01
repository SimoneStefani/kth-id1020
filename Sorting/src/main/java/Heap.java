/**
 * Heap sort
 *
 * Created by S. Stefani on 2017-01-01.
 */

public class Heap extends Sort {

    public static void sort(Comparable[] a) {
        int n = a.length;

        for (int k = n/2; k >= 1; k--) {
            sink(a, k, n);
        }

        while (n > 1) {
            exch(a, 1, n--);
            sink(a, 1, n);
        }
    }

    private static void sink(Comparable[] a, int k, int n) {
        while (2 * k <= n) {
            int j = 2*k;
            if (j < n && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(a, j, k);
            k = j;
        }
    }
}
