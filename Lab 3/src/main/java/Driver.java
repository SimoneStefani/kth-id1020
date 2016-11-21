/**
 * Driver.java
 *
 * Created by S. Stefani on 2016-11-21.
 */

import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.introcs.StdIn;
import java.util.ArrayList;
import java.util.Collections;

public class Driver {
    public static void main(String[] args) {
        System.out.println("|| ID1020 - Algorithms and Data Structures LAB 3 ||\n");

        System.out.print("How many numbers should contain the list? ");
        int n = StdIn.readInt();

        int[] myArray = new int[n];
        for (int i = 0; i < n; i++) {
            myArray[i] = i;
        }
        shuffleArray(myArray);
        SimpleLinkedList myList1 = new SimpleLinkedList();
        SimpleLinkedList myList2 = new SimpleLinkedList();
        myList1.addAll(myArray);
        myList2.addAll(myArray);


        System.out.println("\nUnsorted list:");
        myList1.printList();
        System.out.println("\n");

        InversionCounter inv = new InversionCounter();
        inv.countInversion(myList2);

        Stopwatch timer = new Stopwatch();
        int swapCount = bubbleSort(myList1);
        double time = timer.elapsedTime();

        System.out.println("Sorted list:");
        myList1.printList();
        System.out.println("\nOrdered in " + time + " seconds");
        System.out.println("Number of swaps: " + swapCount);
        System.out.println("Computed number of inversions: " + inv.getNumberInversion());

    }

    public static int bubbleSort(SimpleLinkedList list) {
        int r = list.getCount() - 2;
        boolean swapped = true;
        int swapCount = 0;

        while (r >= 0 && swapped) {
            swapped = false;
            SimpleLinkedList.Node position = list.getFirstNode();
            for (int i = 0; i <= r; i++) {
                if (list.currentValue(position) > list.nextValue(position)) {
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

    public static void shuffleArray(int[] array) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i : array) {
            list.add(i);
        }

        Collections.shuffle(list);

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
    }
}
