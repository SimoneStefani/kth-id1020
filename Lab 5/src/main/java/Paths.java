/**
 * Paths.java
 *
 * Created by S. Stefani on 2016-12-07.
 */

import se.kth.id1020.DataSource;
import se.kth.id1020.Graph;

public class Paths {

    public static void main(String[] args) {
        System.out.println("|| ID1020 - Algorithms and Data Structures LAB 5 ||\n");
        Graph g = DataSource.load();
        ConnectedComponents cc = new ConnectedComponents(g);
        System.out.println();

        if (cc.getNumberOfComponents() == 1) {
            System.out.println("The graph is fully connected!");
        } else if (cc.getNumberOfComponents() > 1) {
            System.out.println("The graph is formed by " + cc.getNumberOfComponents() + " connected subgraphs.");
        } else {
            System.out.println("The graph is not valid!");
        }

        System.out.println();

        ShortestPaths sp2 = new ShortestPaths(g, false);
        sp2.findPath("Renyn", "Parses");

        ShortestPaths sp1 = new ShortestPaths(g, true);
        sp1.findPath("Renyn", "Parses");
    }
}