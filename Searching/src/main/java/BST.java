/**
 * Created by S. Stefani on 2017-01-01.
 */
public class BST<Key extends Comparable<Key>, Value>  {
    private Node root;

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.n;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp > 0) return get(x.rightChild, key);
        else if (cmp < 0) return get(x.leftChild, key);
        else return x.val;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp > 0) x.rightChild = put(x.rightChild, key, val);
        else if (cmp < 0) x.leftChild = put(x.leftChild, key, val);
        else x.val = val;
        x.n = size(x.leftChild) + size(x.rightChild) + 1;
        return x;
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.leftChild == null) return x;
        return min(x.leftChild);
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return floor(x.leftChild, key);
        if (cmp == 0) return x;
        Node t = floor(x.rightChild, key);
        if (t != null) return t;
        else return x;
    }

    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.leftChild);
        if (t > k) return select(x.leftChild, k);
        else if (t < k) return select(x.rightChild, k-t-1);
        else return x;
    }

    private class Node {
        private Key key;
        private Value val;
        private Node leftChild;
        private Node rightChild;
        private int n;

        public Node(Key key, Value val, int n) {
            this.key = key;
            this.val = val;
            this.n = n;
        }
    }
}
