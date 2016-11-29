/**
 * Driver.java
 *
 * Created by S. Stefani on 2016-11-22.
 */

import edu.princeton.cs.introcs.In;
import java.util.*;
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

        PriorityQueue<TrieIterator.Entry> highestFreq = new PriorityQueue<TrieIterator.Entry>(10);
        PriorityQueue<TrieIterator.Entry> lowestFreq = new PriorityQueue<TrieIterator.Entry>(10, Collections.reverseOrder());

        ArrayList<String> prefix2 = new ArrayList<String>();

        Iterator<TrieIterator.Entry> itr = dickensTrie.iterator("");
        while (itr.hasNext()) {
            TrieIterator.Entry entr = itr.next();
            if (entr.getValue() > 0) {
                highestFreq.add(entr);
                lowestFreq.add(entr);
                if (entr.getKey().length() > 2 && !prefix2.contains(entr.getKey())) {
                    prefix2.add(entr.getKey().substring(0, 2));
                }
                if (highestFreq.size() > 10) { highestFreq.poll(); }
                if (lowestFreq.size() > 10) { lowestFreq.poll(); }
            }
        }

        System.out.println("\nTen of the least common words are: " + lowestFreq);
        System.out.println("\nTen of the most common words are: " + highestFreq);

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
}
