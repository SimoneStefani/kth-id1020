/**
 * Selection sort
 *
 * Created by S. Stefani on 2016-12-31.
 */

public class Selection extends Sort {
    public static void main(String[] args) {
        Integer[] a = {6, 3, 8, 1, 2, 4, 9, 5, 7};
        show(a);
        sort(a);
        assert isSorted(a);
        show(a);
    }

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
        }
    }
}
