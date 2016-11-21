/**
 * SimpleLinkedList.java
 *
 * Created by S. Stefani on 2016-11-21.
 */

import java.util.NoSuchElementException;

public class SimpleLinkedList {
    private Node first;
    private int count;

    public SimpleLinkedList() {
        first = null;
        count = 0;
    }

    public SimpleLinkedList(Node first, int numNodes) {
        this.first = first;
        count = numNodes;
    }

    /**
     * Add node with specify data (integer).
     *
     * @param data of the new node
     */
    public void addFirst(int data) {
        Node newNode = new Node();
        newNode.data = data;
        newNode.next = first;
        first = newNode;
        count++;
    }

    public int removeFirst() {
        if (first == null) { throw new NoSuchElementException(); }
        int data = first.data;
        first = first.next;
        return data;
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

    /**
     * Return the value stored in a node.
     *
     * @param node is the node
     * @return the integer stored in the node
     */
    public int currentValue(Node node) {
        if (node == null) { throw new NoSuchElementException(); }

        return node.data;
    }

    /**
     * Return the value of the next node.
     *
     * @param node is the node
     * @return the integer in the next node
     */
    public int nextValue(Node node) {
        if (node.next == null) { throw new NoSuchElementException(); }

        return node.next.data;
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

    /**
     * Get the count of the nodes in the list.
     *
     * @return the number of nodes in the list.
     */
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Provides a hook to the first node on the list.
     *
     * @return the first node in the list
     */
    public Node getFirstNode() {
        return first;
    }

    /**
     * Internal class to model a Node.
     */
    class Node {
        public int data;
        public Node next;
    }
}
