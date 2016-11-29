/**
 * TrieIterator.java
 *
 * Created by S. Stefani on 2016-11-28.
 */

import java.util.Iterator;
import java.util.Map;

/**
 * Define iterator to visit all the node in the subtrees of a given prefix.
 */
public class TrieIterator implements Iterator<Map.Entry<String, Integer>> {
    private TrieNode startingNode;
    private String prefix;
    private int nodesToVisit;
    private int nodesVisited;

    public TrieIterator(TrieNode startNode, String prefix) {
        this.prefix = prefix;
        this.startingNode = startNode;
    }

    /**
     * Check if there are nodes left to visit in the tree.
     *
     * @return boolean
     */
    public boolean hasNext() {
        // Check if calling next() we are getting null
        Entry next = next();
        nodesToVisit--;

        return next != null;
    }

    /**
     * Helper method to call next() and advance the iterator.
     *
     * @return a Map.Entity with key-value for a
     */
    public Entry next() {
        nodesToVisit++;
        nodesVisited = -1;

        return next(startingNode, prefix);
    }

    /**
     * Visit a children node of a given node and return its entry with key-value.
     * Advance to a new children at every call.
     *
     * @param currentNode is the node to start from
     * @param key is the key of a node (built on the fly)
     * @return a Map.Entity with key-value for the node
     */
    private Entry next(TrieNode currentNode, String key) {
        // We are visiting one node
        nodesVisited++;

        // When we have visited the planned nodes to visit (one more than previous iteration)
        // then return an Entry with Key and Value to the recursive call


        if (nodesVisited == nodesToVisit) {
            return new Entry(key, currentNode.value);
        }

        // Visit recursively all the valid children of the node and return the entry
        for (char c = 0; c < 256; c++) {
            if (currentNode.children[c] != null) {
                Entry next = next(currentNode.children[c], key + c);

                if (next != null) {
                    return next;
                }
            }
        }

        // No more children nodes to visit return null
        return null;
    }

    public class Entry implements Map.Entry<String,Integer>, Comparable<Entry> {
        public String key;
        public Integer value;

        public Entry(String key, Integer value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public Integer getValue() {
            return value;
        }

        @Override
        public Integer setValue(Integer value) {
            this.value = value;
            return null;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }

        public int compareTo(Entry etr) {
            if (this.value < etr.value) return -1;
            else if (this.value == etr.value) return 0;
            else return 1;
        }
    }
}
