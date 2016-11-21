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
        SimpleLinkedList myList = new SimpleLinkedList();
        myList.addAll(myArray);


        System.out.println("\nUnsorted list:");
        myList.printList();
        System.out.println("\n");

        Stopwatch timer = new Stopwatch();
        bubbleSort(myList);
        double time = timer.elapsedTime();

        System.out.println("Sorted list:");
        myList.printList();
        System.out.println("\nOrdered in " + time + " seconds\n");
    }

    public static void bubbleSort(SimpleLinkedList list) {
        int r = list.getCount() - 2;
        boolean swapped = true;

        while (r >= 0 && swapped) {
            swapped = false;
            SimpleLinkedList.Node position = list.getFirstNode();
            for (int i = 0; i <= r; i++) {
                if (list.currentValue(position) > list.nextValue(position)) {
                    swapped = true;
                    list.swap(position, position.next);
                }
                position = position.next;
            }
            r--;
        }
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
