/**
 * Created by S. Stefani on 2017-01-03.
 */
public class LinearProbing<Key, Value> {
    private int N;
    private int M = 16;
    private Key[] keys;
    private Value[] vals;

    public LinearProbing() {
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    // private void resize() { }

    public void put(Key key, Value val) {
        if (N >= M/2) resize(M*2);

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
            keys[i] = key;
            vals[i] = val;
            N++;
        }
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) { return vals[i]; }
        }
        return null;
    }
}
