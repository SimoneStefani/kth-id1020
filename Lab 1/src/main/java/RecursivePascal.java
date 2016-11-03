/**
 * Created by S. Stefani on 2016-11-03.
 */
public class RecursivePascal {
    /**
     * Define if the triangle is to be printed upside down or right-side up.
     */
    private boolean upsideDown = false;

    /**
     * Print a simple Pascal's triangle using recursion. Use upsideDown to toggle printing direction.
     *
     * @param n is the number of levels of the triangle
     */
    private void printPascal(int n) {
        if (n == 0) {
            System.out.format("%6d",(binom(0, 0)));
        } else {
            if (upsideDown) {
                for (int i = 0; i <= n; i++) {
                    System.out.format("%6d", (binom(n, i)));
                }
                System.out.print("\n");
                printPascal(n - 1);
            } else {
                printPascal(n - 1);
                System.out.print("\n");
                for (int i = 0; i <= n; i++) {
                    System.out.format("%6d", (binom(n, i)));
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
    private int binom(int n, int k) {
        if (k == 0 || n == k) {
            return 1;
        }
        return binom(n - 1, k - 1) + binom(n - 1, k);
    }

    public static void main(String[] args) {
        RecursivePascal pasc = new RecursivePascal();
        pasc.upsideDown = false;
        pasc.printPascal(10);
    }
}
