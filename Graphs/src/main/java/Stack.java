import java.util.Iterator;

/**
 * Created by S. Stefani on 2017-01-06.
 */
public class Stack<Item> implements Iterable<Item> {
    private Node first;
    private int N;

    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private class Node {
        Item item;
        Node next;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() { return current != null; }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
