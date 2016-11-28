import edu.princeton.cs.introcs.In;

import java.util.*;

/**
 * Created by S. Stefani on 2016-11-22.
 */
import java.net.URL;

public class Driver {

    public static void main(String[] args) {
        System.out.println("|| ID1020 - Algorithms and Data Structures LAB 4 ||\n");

        URL url = ClassLoader.getSystemResource("kap1.txt");

        Trie dickensTrie = new Trie();

        if (url != null) {
            System.out.println("Reading from: " + url);
        } else {
            System.out.println("Couldn't find file: kap1.txt");
        }

        In input = new In(url);

        while (!input.isEmpty()) {
            String line = input.readLine().trim();
            String[] words = line.split("(\\. )|:|,|;|!|\\?|( - )|--|(\' )| ");
            String lastOfLine = words[words.length - 1];

            if (lastOfLine.endsWith(".")) {
                words[words.length - 1] = lastOfLine.substring(0, lastOfLine.length() - 1);
            }

            for (String word : words) {
                String word2 = word.replaceAll("\"|\\(|\\)\\“|\\”|\\’|\\“", "");

                if (word2.isEmpty()) {
                    continue;
                }

                word2 = word2.toLowerCase();

                // System.out.println(word2);
                dickensTrie.put(word2);
            }
        }

        Map<String, Integer> words = new TreeMap<String, Integer>();
        ArrayList<String> prefix2 = new ArrayList<String>();

        Iterator<Map.Entry<String, Integer>> maxIter = dickensTrie.iterator("");
        while (maxIter.hasNext()) {
            Map.Entry<String, Integer> entr = maxIter.next();
            if (entr.getValue() > 0) {
                words.put(entr.getKey(), entr.getValue());
                if (entr.getKey().length() > 2 && !prefix2.contains(entr.getKey())) {
                    prefix2.add(entr.getKey().substring(0, 2));
                }
            }
        }



        ArrayList<Map.Entry<String,Integer>> ar = new ArrayList<Map.Entry<String,Integer>>();
        for (Map.Entry<String, Integer> et : entriesSortedByIntegeralues(words)) { ar.add(et); }

        System.out.println("\nTen of the least common words are: " + ar.subList(0, 10));
        List<Map.Entry<String,Integer>> biggest = ar.subList(ar.size()-10, ar.size());
        Collections.reverse(biggest);
        System.out.println("\nTen of the most common words are: " + biggest);

        String commonPref = prefix2.get(0);
        int max = 0;
        for (String str : prefix2) {
            int count = dickensTrie.count(str);
            if (count > max) {
                max = count;
                commonPref = str;
            }
        }
        System.out.println("\nThe most common 2-letters prefix is: " + commonPref + " (appears " + max + " times)");

        char mostDifferentLetter = dickensTrie.mostDifferentKeyLetter();
        System.out.println("\nThe letter that the most different words start with is: " + String.valueOf(mostDifferentLetter));



    }


    private static <String,Integer extends Comparable<? super Integer>>
    SortedSet<Map.Entry<String,Integer>> entriesSortedByIntegeralues(Map<String,Integer> map) {
        SortedSet<Map.Entry<String,Integer>> sortedEntries = new TreeSet<Map.Entry<String,Integer>>(
            new Comparator<Map.Entry<String,Integer>>() {
                @Override public int compare(Map.Entry<String,Integer> e1, Map.Entry<String,Integer> e2) {
                    int res = e1.getValue().compareTo(e2.getValue());
                    return res != 0 ? res : 1;
                }
            }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }
}
