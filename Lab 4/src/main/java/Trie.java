/**
 * Trie.java
 *
 * Created by S. Stefani on 2016-11-21.
 */

public class Trie {
    private TrieNode root;
    private int numKeys;

    public Trie() { }

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
}