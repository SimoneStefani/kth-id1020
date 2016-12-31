/**
 * Shell sort
 *
 * Created by S. Stefani on 2016-12-31.
 */
public class Shell extends Sort {
    public static void main(String[] args) {
        Integer[] a = {6, 3, 8, 1, 2, 4, 9, 7, 5};
        show(a);
        sort(a);
        assert isSorted(a);
        show(a);
    }

    public static void sort(Comparable[] a) {
        int n = a.length;
        int h = 1;
        while (h < n/3) h = 3*h + 1;
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                }
            }
            h = h/3;
        }
    }
}
