import java.util.Iterator;

/**
 * Created by S. Stefani on 2016-11-22.
 */
public class Driver {
    public static void main(String[] args) {
        Trie tester = new Trie();

        tester.put("abc");
        tester.put("acf");
        tester.put("ac");
        tester.put("acfd");
        tester.put("acfe");
        tester.put("acfd");
        tester.put("bcfd");

        System.out.println(tester.get("acf"));

        System.out.println(tester.distinct("a"));

        Iterator iter = tester.iterator("ac");
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}
