import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * Trie.java
 *
 * Created by S. Stefani on 2016-11-21.
 */

public class Trie {
    private TrieNode root;
    private int numKeys;

    public Trie() {
        this.root = new TrieNode();
    }

    /**
     * Helper method to add a key to the structure
     *
     * @param key is the key to add
     */
    public void put(String key) {
        put(root, key, 0);
    }

    /**
     * Add recursively a new key to the structure.
     *
     * @param keyNode is the node to extend or visit
     * @param key is the key to add
     * @param charPointer keeps track of which char in the key we are analysing
     * @return the node where is stored the last char of the key
     */
    private TrieNode put(TrieNode keyNode, String key, int charPointer) {
        // If reach a null node, then add a new node
        if (keyNode == null) {
            keyNode = new TrieNode();
        }

        // When we have added nodes for all the chars in key increment the key value of one
        // or set it to 1 if it had no previous value (0)
        if (charPointer == key.length()) {
            if (keyNode.value == 0) {
                keyNode.value = 1;
            } else {
                keyNode.value += 1;
            }
            return keyNode;
        }

        // Iteratively add nodes in order to store the whole key
        char keyCharacter = key.charAt(charPointer);
        keyNode.children[keyCharacter] = put(keyNode.children[keyCharacter], key, charPointer + 1);

        return keyNode;
    }

    /**
     * Get a value for a specific key.
     *
     * @param key is the key
     * @return the value of the key or 0 if the key cannot be found
     */
    public int get(String key) {
        TrieNode keyNode = get(root, key, 0);
        if (keyNode == null) return 0;

        return keyNode.value;
    }

    /**
     * Search iteratively for a node containing the last char of the key.
     *
     * @param keyNode is the node to examine
     * @param key is the key to search
     * @param charPointer keeps track of which char in the key we are analysing
     * @return the node containing the last char in key or null if not found
     */
    private TrieNode get(TrieNode keyNode, String key, int charPointer) {
        // If reach a null node the key is not present
        if (keyNode == null) {
            return null;
        }

        // If we navigate the tree till the end of the key return the node
        // that contains the last character in the key
        if (charPointer == key.length()) {
            return keyNode;
        }

        // Iteratively search in the correct children
        char keyCharacter = key.charAt(charPointer);
        return get(keyNode.children[keyCharacter], key, charPointer + 1);
    }

    /**
     * Helper method to compute the values in the subtrees of a prefix.
     *
     * @param prefix is the given prefix
     * @return the sum of the values
     */
    public int count(String prefix) {
        TrieNode startingNode = get(root, prefix, 0);

        return count(startingNode);
    }

    /**
     * Compute the sum of the values of the nodes in the subtrees of a given
     * node (which is included in the count).
     *
     * @param keyNode is the starting node
     * @return the sum of the values
     */
    private int count(TrieNode keyNode) {
        // If the node is null there is no value
        if (keyNode == null) {
            return 0;
        }

        // Add value of current node
        int valueSum = keyNode.value;

        // Sum values recursively and for all the possible 256 ASCII chars
        for (char c = 0; c < 256; c++) {
            valueSum += count(keyNode.children[c]);
        }

        return valueSum;
    }

    /**
     * Helper method to compute the number of distinct keys with given prefix
     *
     * @param prefix is the prefix
     * @return the number of distinct keys
     */
    public int distinct(String prefix) {
        TrieNode startingNode = get(root, prefix, 0);

        return startingNode.value > 0 ? distinct(startingNode) - 1 : distinct(startingNode);
    }

    /**
     * Compute the number of distinct keys starting with a given prefix
     *
     * @param keyNode is the node of the prefix
     * @return the number of distinct keys
     */
    private int distinct(TrieNode keyNode) {
        // If the node is null there is no value
        if (keyNode == null) {
            return 0;
        }

        // If the node has a value greater than zero it represent a new distinct key
        int valueSum = 0;
        if (keyNode.value > 0) {
            valueSum++;
        }

        // Sum values recursively and for all the possible 256 ASCII chars
        for (char c = 0; c < 256; c++) {
            valueSum += distinct(keyNode.children[c]);
        }

        return valueSum;
    }


    public Iterator<Entry<String, Integer>> iterator(String prefix) {
        return new TrieIterator(prefix);
    }

    /**
     * Define iterator to visit all the node in the subtrees of a given prefix.
     */
    private class TrieIterator implements Iterator<Entry<String, Integer>> {
        private TrieNode startingNode;
        private String prefix;
        private int nodesToVisit;
        private int nodesVisited;

        public TrieIterator(String prefix) {
            this.prefix = prefix;
            this.startingNode = get(root, prefix, 0);
        }

        /**
         * Check if there are nodes left to visit in the tree.
         *
         * @return boolean
         */
        public boolean hasNext() {
            // Check if calling next() we are getting null
            Entry<String,Integer> next = next();
            nodesToVisit--;

            return next != null;
        }

        /**
         * Helper method to call next() and advance the iterator.
         *
         * @return a Map.Entity with key-value for a
         */
        public Entry<String, Integer> next() {
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
        private Entry<String,Integer> next(TrieNode currentNode, String key) {
            // We are visiting one node
            nodesVisited++;

            // When we have visited the planned nodes to visit (one more than previous iteration)
            // then return an Entry with Key and Value to the recursive call
            if (nodesVisited == nodesToVisit) {
                return new AbstractMap.SimpleEntry<String,Integer>(key, currentNode.value);
            }

            // Visit recursively all the valid children of the node and return the entry
            for (char c = 0; c < 256; c++) {
                if (currentNode.children[c] != null) {
                    Entry<String, Integer> next = next(currentNode.children[c], key + c);

                    if (next != null) {
                        return next;
                    }
                }
            }

            // No more children nodes to visit return null
            return null;
        }
    }
}
