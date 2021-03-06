/**
 * IterativePascal.java
 *
 * Created by S. Stefani on 2016-11-04.
 */

public class IterativePascal extends ErrorPascal {

    public IterativePascal() { super(); }
    public IterativePascal(boolean upsideDown) { super(upsideDown); }

    /**
     * Print a simple Pascal's triangle iteratively (two nested loops).
     *
     * @param n is the number of levels of the triangle
     */
    public void printPascal(int n) {
        validateParameters(n);
        if (!upsideDown) {
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= i; j++) {
                    System.out.format("%6d ", (binom(i, j)));
                }
                System.out.print("\n");
            }
        } else {
            for (int i = n; i >= 0; i--) {
                for (int j = 0; j <= i; j++) {
                    System.out.format("%6d ", (binom(i, j)));
                }
                System.out.print("\n");
            }
        }
    }

    /**
     * Computes the binomial coefficient for a specific entry in Pascal's triangle iteratively.
     *
     * @param n is the row
     * @param k is the column
     * @return the binomial coefficient
     */
    public int binom(int n, int k) {
        validateParameters(n, k);

        String binomKey = getCoeffHashKey(n, k);
        if (coeffTable.containsKey(binomKey)) { return (Integer) coeffTable.get(binomKey); }

        int[] coeffBinom = new int[n + 1];
        coeffBinom[0] = 1;

        for (int i = 1; i <= n; i++) {
            coeffBinom[i] = 1;

            for (int j = i - 1; j > 0; j--) {
                coeffBinom[j] += coeffBinom[j - 1];
            }
        }

        coeffTable.put(binomKey, coeffBinom[k]);
        return coeffBinom[k];
    }
}
