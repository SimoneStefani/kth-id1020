import se.kth.id1020.Edge;

/**
 * Created by S. Stefani on 2016-12-07.
 */
public class ShortestPath {
    private double[] distanceTo;
    private Edge[] edgeTo;

    private void relax(Edge edge) {
        int u = edge.from;
        int v = edge.to;

        if (distanceTo[v] > distanceTo[u] + edge.weight) {
            distanceTo[v] = distanceTo[u] + edge.weight;
            edgeTo[v] = edge;
        }
    }
}
