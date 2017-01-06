/**
 * Created by S. Stefani on 2017-01-06.
 */
public class Digraph {
    private int V;
    private int E;
    private Bag<Integer>[] adj;

    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[V] = new Bag<Integer>();
        }
    }

    public int V() { return V; }

    public int E() { return E; }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public Digraph reverse() {
        Digraph rev = new Digraph(V);
        for (int i = 0; i < V; i++) {
            for (int w : adj(i)) {
                rev.addEdge(w, i);
            }
        }
        return rev;
    }
}
