import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Created by S. Stefani on 2016-11-20.
 */
public class MyLinkedList {
    private Node first;

    public MyLinkedList() {
        first = null;
    }

    public Object getFirst() {
        if (first == null) { throw new NoSuchElementException(); }
        return first.data;
    }

    public Object removeFirst() {
        if (first == null) { throw new NoSuchElementException(); }
        Object data = first.data;
        first = first.next;
        return data;
    }

    public void addFirst(Object data) {
        Node newNode = new Node();
        newNode.data = data;
        newNode.next = first;
        first = newNode;
    }

    public MyListIterator listIterator() {
        return new MyLinkedListIterator();
    }

    class Node {
        public Object data;
        public Node next;
    }

    class MyLinkedListIterator implements MyListIterator {
        private Node position;
        private Node previous;
        private boolean isAfterNext;

        public MyLinkedListIterator() {
            position = null;
            previous = null;
            isAfterNext = false;
        }

        public boolean hasNext() {
            if (position == null) {
                return first != null;
            } else {
                return position.next != null;
            }
        }

        public Object next() {
            if (!hasNext()) { throw new NoSuchElementException(); }
            previous = position;
            isAfterNext = true;

            if (position == null) {
                position = first;
            } else {
                position = position.next;
            }

            return position.data;
        }

        public void remove() {
            if (!isAfterNext) { throw new IllegalStateException(); }
            if (position == first) {
                removeFirst();
            } else {
                previous.next = position.next;
            }
            position = previous;
            isAfterNext = false;
        }

        public void set(Object data) {
            if (!isAfterNext) { throw new IllegalStateException(); }
            position.data = data;
        }

        public void add(Object data) {
            if (position == null) {
                addFirst(data);
                position = first;
            } else {
                Node newNode = new Node();
                newNode.data = data;
                newNode.next = position.next;
                position.next = newNode;
                position = newNode;
            }

            isAfterNext = false;
        }
    }
}
