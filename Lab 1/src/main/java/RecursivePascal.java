/**
 * RecursivePascal.java
 *
 * Created by S. Stefani on 2016-11-03.
 */

public class RecursivePascal extends ErrorPascal {

    public RecursivePascal() { super(); }
    public RecursivePascal(boolean upsideDown) { super(upsideDown); }

    /**
     * Print a simple Pascal's triangle using recursion. Use upsideDown to toggle printing direction.
     *
     * @param n is the number of levels of the triangle
     */
    public void printPascal(int n) {
        validateParameters(n);
        if (n == 0) {
            System.out.format("%6d ",(binom(0, 0)));
        } else {
            if (upsideDown) {
                for (int i = 0; i <= n; i++) {
                    System.out.format("%6d ", (binom(n, i)));
                }
                System.out.print("\n");
                printPascal(n - 1);
            } else {
                printPascal(n - 1);
                System.out.print("\n");
                for (int i = 0; i <= n; i++) {
                    System.out.format("%6d ", (binom(n, i)));
                }
            }
        }
    }

    /**
     * Computes the binomial coefficient for a specific entry in Pascal's triangle.
     *
     * @param n is the row
     * @param k is the column
     * @return the binomial coefficient
     */
    public int binom(int n, int k) {
        validateParameters(n, k);

        String binomKey = getCoeffHashKey(n, k);
        if (coeffTable.containsKey(binomKey)) { return (Integer) coeffTable.get(binomKey); }

        if (k == 0 || n == k) {
            coeffTable.put(binomKey, 1);
            return 1;
        }
        int coeff = binom(n - 1, k - 1) + binom(n - 1, k);
        coeffTable.put(binomKey, coeff);
        return coeff;
    }
}
