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
        SimpleLinkedList sortingList = new SimpleLinkedList();
        SimpleLinkedList inversionsList = new SimpleLinkedList();
        sortingList.addAll(myArray);
        inversionsList.addAll(myArray);


        System.out.println("\nUnsorted list:");
        sortingList.printList();
        System.out.println("\n");

        InversionsCounter inv = new InversionsCounter();
        long numInversions = inv.countInversions(inversionsList);

        BubbleSort sorter = new BubbleSort();
        Stopwatch timer = new Stopwatch();
        long swapCount = sorter.sort(sortingList);
        double time = timer.elapsedTime();

        System.out.println("Sorted list:");
        sortingList.printList();
        System.out.println("\nOrdered in " + time + " seconds");
        System.out.println("Number of swaps: " + swapCount);
        System.out.println("Computed number of inversions: " + numInversions);

    }

    private static void shuffleArray(int[] array) {
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
