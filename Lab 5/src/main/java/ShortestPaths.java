/**
 * ShortestPaths.java
 *
 * Created by S. Stefani on 2016-12-07.
 */

import edu.princeton.cs.algs4.IndexMinPQ;
import se.kth.id1020.Edge;
import se.kth.id1020.Graph;
import se.kth.id1020.Vertex;
import java.security.InvalidParameterException;
import java.util.Stack;

public class ShortestPaths {
    private Graph graph;
    private boolean weighted;
    private double minDistance[];
    private Edge path[];

    public ShortestPaths(Graph graph, boolean weighted) {
        this.graph = graph;
        this.weighted = weighted;
    }

    public void findPath(String source, String endpoint) {
        int sourceId = parseVertex(source);
        int endId = parseVertex(endpoint);

        dijkstra(sourceId);

        String wg = weighted ? "(weighted)" : "(unweighted)";

        System.out.println("The shortest " + wg + " path between " + source + " and " + endpoint + " is: ");
        System.out.print(source);

        Stack<Edge> edgesPath = new Stack<Edge>();
        for (Edge e = path[endId]; e != null; e = path[e.from]) {
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
            System.out.println("\nThe minimum distance is: " + minDistance[endId]);
        }
        System.out.println();
    }

    private int parseVertex(String label) {
        int vertexId = -1;
        for (Vertex vrt : this.graph.vertices()) {
            if (vrt.label.equals(label)) {
                vertexId = vrt.id;
                break;
            }
        }
        if (vertexId < 0) {
            throw new InvalidParameterException("ERROR: vertex \"" + label + "\" not found!");
        }
        return vertexId;
    }

    private void dijkstra(int sourceId) {
        minDistance = new double[this.graph.numberOfVertices()];
        path = new Edge[this.graph.numberOfVertices()];
        for (int v = 0; v < minDistance.length; v++) {
            minDistance[v] = Double.POSITIVE_INFINITY;
        }
        minDistance[sourceId] = 0.0;

        IndexMinPQ<Double> verticesQueue = new IndexMinPQ<Double>(this.graph.numberOfVertices());
        verticesQueue.insert(sourceId, minDistance[sourceId]);

        while (!verticesQueue.isEmpty()) {
            for (Edge edge : graph.adj(verticesQueue.delMin())) {
                int uId = edge.from;
                int vId = edge.to;
                if (minDistance[vId] > minDistance[uId] + getWeight(edge)) {
                    minDistance[vId] = minDistance[uId] + getWeight(edge);
                    path[vId] = edge;
                    if (verticesQueue.contains(vId)) {
                        verticesQueue.decreaseKey(vId, minDistance[vId]);
                    } else {
                        verticesQueue.insert(vId, minDistance[vId]);
                    }
                }
            }
        }
    }

    private double getWeight(Edge edge) {
        if (this.weighted) {
            return edge.weight;
        }
        return 1.0;
    }
}
