/**
 * InversionsCounter.java
 *
 * Created by S. Stefani on 2016-11-21.
 */

public class InversionsCounter {
    private long inversionsCount = 0;

    public InversionsCounter() { }

    /**
     * Operate mergeSort on a provided list and count the number of inversions.
     * The whole operation takes O(NlogN) time and modifies the provided list.
     *
     * @param list to mergeSort
     * @return the number of inversions
     */
    public long countInversions(SimpleLinkedList list) {
        mergeSort(list);

        return inversionsCount;
    }

    private SimpleLinkedList mergeSort(SimpleLinkedList list) {

        // If the list is composed by one node return the node
        if (list.getLength() < 2) {
            return list;
        }

        // Get the center of the list
        int center = list.getLength() / 2;

        // Split the list in two strands in the middle and attach to new
        // lists leftList and rightList with proper count of nodes
        Node position = list.getFirstNode();
        for (int i = 0; i < center - 1; i++) {
            position = position.next;
        }
        SimpleLinkedList leftPart = new SimpleLinkedList(list.getFirstNode(), center);
        SimpleLinkedList rightPart = new SimpleLinkedList(position.next, list.getLength() - center);
        position.next = null;

        // Recursively call to split the list until single-node lists are obtained
        SimpleLinkedList newLeft = mergeSort(leftPart);
        SimpleLinkedList newRight = mergeSort(rightPart);

        // Return the merged left and right lists
        return merge(newLeft, newRight);
    }

    private SimpleLinkedList merge(SimpleLinkedList leftPart, SimpleLinkedList rightPart) {

        // Create new list for the merged result and set proper length.
        // Add a first "dummy" node to facilitate operations.
        SimpleLinkedList resultList = new SimpleLinkedList();
        resultList.addFirst(-1);
        resultList.setCount(leftPart.getLength() + rightPart.getLength());

        // Initialise pointers to first element of the left, right and result lists
        Node position = resultList.getFirstNode();
        Node leftPos = leftPart.getFirstNode();
        Node rightPos = rightPart.getFirstNode();

        // Initialise variable to count how many nodes are left to merge from leftList
        // It is important to count the inversions
        int leftCount = leftPart.getLength();

        // Merge the right and left lists. When an element from leftList is inserted in
        // the resultList increment the inversions count of number equal to the elements
        // left to merge in the leftList.
        while (leftPos != null || rightPos != null) {
            if (leftPos == null) {
                position.next = rightPos;
                break;
            } else if (rightPos == null){
                position.next = leftPos;
                break;
            } else {
                if (leftPos.data <= rightPos.data){
                    position.next = leftPos;
                    leftPos = leftPos.next;
                    leftCount--;
                    position = position.next;
                } else {
                    position.next = rightPos;
                    rightPos = rightPos.next;
                    position = position.next;
                    inversionsCount += leftCount;
                }
            }
        }

        // Remove first dummy element
        resultList.removeFirst();

        return resultList;
    }
}
