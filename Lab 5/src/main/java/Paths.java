/**
 * Paths.java
 *
 * Created by S. Stefani on 2016-12-07.
 */

import se.kth.id1020.DataSource;
import se.kth.id1020.Edge;
import se.kth.id1020.Graph;

public class Paths {

    public static void main(String[] args) {
        Graph g = DataSource.load();
        // work on g

        System.out.println(connectedComponents(g));
    }

    public static int connectedComponents(Graph g) {
        int numberOfComponents = 0;
        boolean[] visited = new boolean[g.numberOfVertices()];

        for (int i = 0; i < g.numberOfVertices(); i++) {
            if (!visited[i]) {
                dfs(g, i, visited);
                numberOfComponents++;
            }
        }

        return numberOfComponents;
    }

    public static void dfs(Graph g, int vertex, boolean[] visited) {
        visited[vertex] = true;
        for (Edge edg : g.adj(vertex)) {
            int w = edg.to == vertex ? edg.from : edg.to;
            if (!visited[w]) {
                dfs(g, w, visited);
            }
        }
    }

}