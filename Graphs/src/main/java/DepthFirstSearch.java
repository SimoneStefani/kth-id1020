/**
 * Created by S. Stefani on 2017-01-06.
 */
public class DepthFirstSearch {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;

    public DepthFirstSearch(Graph g, int s) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        dfs(g, s);
    }

    private void dfs(Graph g, int s) {
        marked[s] = true;

        for (int w : g.adj(s)) {
            if (!marked[w]) {
                edgeTo[w] = s;
                dfs(g, w);

            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;

        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }

        path.push(s);

        return path;
    }

    public boolean marked(int v) {
        return marked[v];
    }

}
