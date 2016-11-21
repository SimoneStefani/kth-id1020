/**
 * BubbleSort.java
 *
 * Created by S. Stefani on 2016-11-21.
 */

public class BubbleSort {
    public BubbleSort() { }

    public long sort(SimpleLinkedList list) {
        int r = list.getLength() - 2;
        boolean swapped = true;
        long swapCount = 0;
        Node position;

        while (r >= 0 && swapped) {
            swapped = false;
            position = list.getFirstNode();
            for (int i = 0; i <= r; i++) {
                if (list.getValue(position) > list.getValue(position.next)) {
                    swapped = true;
                    list.swap(position, position.next);
                    swapCount++;
                }
                position = position.next;
            }
            r--;
        }

        return swapCount;
    }
}
