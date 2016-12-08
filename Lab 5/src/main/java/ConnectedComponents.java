/**
 * ConnectedComponents.java
 *
 * Created by S. Stefani on 2016-12-08.
 */

import se.kth.id1020.Edge;
import se.kth.id1020.Graph;

public class ConnectedComponents {
    private int numberOfComponents;
    private boolean[] visited;

    /**
     * Constructor for the class ConnectedComponents. Takes a graph and returns
     * the number of connected components.
     *
     * @param graph is the graph
     */
    public ConnectedComponents(Graph graph) {
        this.numberOfComponents = 0;
        this.visited = new boolean[graph.numberOfVertices()];

        for (int i = 0; i < graph.numberOfVertices(); i++) {
            if (!this.visited[i]) {
                computeComponents(graph, i);
                this.numberOfComponents++;
            }
        }
    }

    /**
     * Given a graph and a vertex check connectivity with all other vertices
     * by means of depth-first-search.
     *
     * @param graph is the graph
     * @param vertex is the starting vertex
     */
    private void computeComponents(Graph graph, int vertex) {
        this.visited[vertex] = true;
        for (Edge edg : graph.adj(vertex)) {
            int w = edg.to == vertex ? edg.from : edg.to;
            if (!this.visited[w]) {
                computeComponents(graph, w);
            }
        }
    }

    /**
     * Get the number of components in a graph. The graph is fully connected
     * if it has only one component.
     *
     * @return the number of components
     */
    public int getNumberOfComponents() {
        return numberOfComponents;
    }
}
