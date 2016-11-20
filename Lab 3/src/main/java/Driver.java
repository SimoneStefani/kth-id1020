import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by S. Stefani on 2016-11-20.
 */
public class Driver {
    public static void main(String[] args) {
        LinkedList<String> myList = new LinkedList<String>();

        String[] alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

        Collections.addAll(myList, alphabet);
        Collections.shuffle(myList);

        System.out.println(myList);
        System.out.println();

        bubbleSortIterative(myList);

        System.out.println(myList);
        System.out.println();
    }


    public static void bubbleSortIterative(LinkedList<String> list) {

        int r = list.size() - 2;
        boolean swapped = true;

        while (r >= 0 && swapped) {
            swapped = false;
            for (int i = 0; i <= r; i++) {
                if (list.get(i).compareTo(list.get(i + 1)) >= 1) {
                    swapped = true;
                    String temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                }
            }
            r--;
        }
    }
}
