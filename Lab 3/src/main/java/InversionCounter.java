/**
 * InversionCounter.java
 *
 * Created by S. Stefani on 2016-11-21.
 */

public class InversionCounter {
    public int count = 0;

    public InversionCounter() { }

    public SimpleLinkedList countInversion(SimpleLinkedList list) {
        if (list.getCount() < 2) {
            return list;
        }

        int center = list.getCount() / 2;

        SimpleLinkedList leftPart = new SimpleLinkedList(list.getFirstNode(), center);
        SimpleLinkedList.Node position = leftPart.getFirstNode();
        for (int i = 0; i < center - 1; i++) {
            position = position.next;
        }
        SimpleLinkedList rightPart = new SimpleLinkedList(position.next, list.getCount() - center);
        position.next = null;

//        System.out.print("\n\nLeft part: ");
//        leftPart.printList();
//        System.out.print("\nRight part: ");
//        rightPart.printList();

        SimpleLinkedList newLeft = countInversion(leftPart);
        SimpleLinkedList newRight = countInversion(rightPart);
        return merge(newLeft, newRight);
    }

    public SimpleLinkedList merge(SimpleLinkedList leftPart, SimpleLinkedList rightPart) {
//        System.out.print("\n\nMerge ");
//        leftPart.printList();
//        System.out.print("(" + leftPart.getCount() + ") with ");
//        rightPart.printList();
//        System.out.print("(" + rightPart.getCount() + ") => ");

        SimpleLinkedList resultList = new SimpleLinkedList();
        resultList.setCount(leftPart.getCount() + rightPart.getCount());
//        System.out.println(resultList.getCount());
        resultList.addFirst(-1);
        SimpleLinkedList.Node position = resultList.getFirstNode();
        SimpleLinkedList.Node leftPos = leftPart.getFirstNode();
        int leftCount = leftPart.getCount();
        SimpleLinkedList.Node rightPos = rightPart.getFirstNode();

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
//                    System.out.println("+ " + leftCount + " inversions");
                    count += leftCount;
                }
            }
        }
        resultList.removeFirst();
        resultList.setCount(resultList.getCount() - 1);
//        System.out.print("MERGED: ");
//        resultList.printList();
        return resultList;
    }

    public int getNumberInversion() {
        return count;
    }
}
