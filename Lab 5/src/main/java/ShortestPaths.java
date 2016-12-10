/**
 * ShortestPaths.java
 *
 * Created by S. Stefani on 2016-12-07.
 */

import se.kth.id1020.Edge;
import se.kth.id1020.Graph;
import se.kth.id1020.Vertex;
import java.security.InvalidParameterException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

public class ShortestPaths {
    private Graph graph;
    private boolean weighted;
    private double minDistance[];
    private Edge predecessor[];

    public ShortestPaths(Graph graph, boolean weighted) {
        this.graph = graph;
        this.weighted = weighted;
    }

    /**
     * Print distance and vertices for the shortest path between two vertices
     *
     * @param sourceStr is the starting point
     * @param destinationStr is the ending point
     */
    public void findPath(String sourceStr, String destinationStr) {
        Vertex source = parseVertex(sourceStr);
        Vertex destination = parseVertex(destinationStr);

        dijkstra(source);

        String wg = weighted ? "(weighted)" : "(unweighted)";

        System.out.println("The shortest " + wg + " path between " + sourceStr + " and " + destinationStr + " is: ");

        System.out.print(sourceStr);
        Stack<Edge> edgesPath = new Stack<Edge>();
        for (Edge e = predecessor[destination.id]; e != null; e = predecessor[e.from]) {
            edgesPath.push(e);
        }
        while (!edgesPath.isEmpty()) {
            int toId = edgesPath.pop().to;
            for (Vertex vrt : graph.vertices()) {
                if (vrt.id == toId) {
                    System.out.print(" -> " + vrt.label);
                    break;
                }
            }
        }

        if (weighted) {
            System.out.println("\nThe minimum distance is: " + minDistance[destination.id]);
        }
        System.out.println("\n");
    }

    /**
     * Get the Vertex object given its label
     *
     * @param label is the label of the vertex
     * @return the vertex object
     */
    private Vertex parseVertex(String label) {
        Vertex vertex = null;
        for (Vertex vrt : this.graph.vertices()) {
            if (vrt.label.equals(label)) {
                vertex = vrt;
                break;
            }
        }
        if (vertex == null) {
            throw new InvalidParameterException("ERROR: vertex \"" + label + "\" not found!");
        }
        return vertex;
    }

    /**
     * Compute the shortest paths from a source vertex to all the other vertices.
     *
     * @param source is the source vertex
     */
    private void dijkstra(Vertex source) {
        minDistance = new double[this.graph.numberOfVertices()];
        predecessor = new Edge[this.graph.numberOfVertices()];

        // Set distances to all vertices to infinity except source
        for (int v = 0; v < minDistance.length; v++) {
            minDistance[v] = Double.POSITIVE_INFINITY;
        }
        minDistance[source.id] = 0.0;

        // Prepare priority queue and add source vertex
        VertexComparator cmp = new VertexComparator(minDistance);
        PriorityQueue<Vertex> verticesQueue = new PriorityQueue<Vertex>(cmp);
        verticesQueue.add(source);

        while (!verticesQueue.isEmpty()) {
            for (Edge edge : graph.adj(verticesQueue.poll().id)) {

                // Relaxation step
                if (minDistance[edge.to] > minDistance[edge.from] + getWeight(edge)) {
                    minDistance[edge.to] = minDistance[edge.from] + getWeight(edge);
                    predecessor[edge.to] = edge;
                    if (verticesQueue.contains(graph.vertex(edge.to))) {
                        verticesQueue.remove(graph.vertex(edge.to));
                        verticesQueue.add(graph.vertex(edge.to));
                    } else {
                        verticesQueue.add(graph.vertex(edge.to));
                    }
                }
            }
        }
    }

    /**
     * Provide the weight of an edge is the graph is wighted, otherwise 1.0
     *
     * @param edge is the edge to get the weight for
     * @return the weight of the edge or 1
     */
    private double getWeight(Edge edge) {
        if (this.weighted) {
            return edge.weight;
        }
        return 1.0;
    }

    /**
     * Simple comparator for the priority queue. Compare vertices based on a distance property
     */
    private class VertexComparator implements Comparator<Vertex> {
        double minDistance[];

        public VertexComparator(double[] minDistance) {
            this.minDistance = minDistance;
        }

        public int compare(Vertex o1, Vertex o2) {
            if (minDistance[o1.id] > minDistance[o2.id]) {
                return 1;
            } else if (minDistance[o1.id] == minDistance[o2.id]){
                return 0;
            } else {
                return -1;
            }
        }
    }
}
