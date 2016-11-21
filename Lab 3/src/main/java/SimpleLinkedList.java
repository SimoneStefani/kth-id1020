/**
 * SimpleLinkedList.java
 *
 * Created by S. Stefani on 2016-11-21.
 */

import java.util.NoSuchElementException;

public class SimpleLinkedList {
    private Node first;
    private int length;

    public SimpleLinkedList() {
        first = null;

        length = 0;
    }

    public SimpleLinkedList(Node first, int numNodes) {
        this.first = first;
        length = numNodes;
    }

    /**
     * Provides a hook to the first node on the list.
     *
     * @return the first node in the list
     */
    public Node getFirstNode() {
        if (first == null) { throw new NoSuchElementException(); }
        return first;
    }

    /**
     * Add node with specify data (integer).
     *
     * @param data of the new node
     */
    public void addFirst(int data) {
        first = new Node(data, first);
        length++;
    }

    /**
     * Add all the integers in an array to the linked list. Preserve the order.
     *
     * @param numbers is the array of integers
     */
    public void addAll(int[] numbers) {
        for (int i = numbers.length - 1; i >= 0; i--) {
            addFirst(numbers[i]);
        }
    }

    public int removeFirst() {
        if (first == null) { throw new NoSuchElementException(); }
        int data = first.data;
        first = first.next;
        return data;
    }

    /**
     * Get the length of the list.
     *
     * @return the number of nodes in the list.
     */
    public int getLength() {
        return length;
    }

    /**
     * Se the length of the list.
     *
     * @param length of the list
     */
    public void setCount(int length) {
        this.length = length;
    }

    /**
     * Return the value stored in a node.
     *
     * @param node is the node
     * @return the integer stored in the node
     */
    public int getValue(Node node) {
        if (node == null) { throw new NoSuchElementException(); }

        return node.data;
    }

    /**
     * Swap the content of two nodes.
     *
     * @param current is the first node
     * @param next is the following node
     */
    public void swap(Node current, Node next) {
        if (current == null || current.next == null) { throw new NoSuchElementException(); }

        int temp = current.data;
        current.data = next.data;
        next.data = temp;
    }

    /**
     * Print the entire linked list.
     */
    public void printList() {
        if (first == null) { throw new NoSuchElementException(); }
        Node position = first;
        while (position != null) {
            System.out.print(position.data + " ");
            position = position.next;
        }
    }
}
