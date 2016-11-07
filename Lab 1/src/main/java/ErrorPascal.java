/**
 * ErrorPascal.java
 *
 * Created by S. Stefani on 2016-11-04.
 */

import java.security.InvalidParameterException;
import java.util.HashMap;

public abstract class ErrorPascal implements Pascal {
    protected boolean upsideDown = false;
    protected HashMap coeffTable;

    public ErrorPascal() {
        this.coeffTable = new HashMap<String, Integer>();
    }

    public ErrorPascal(boolean upsideDown) {
        this.upsideDown = upsideDown;
        this.coeffTable = new HashMap<String, Integer>();
    }

    abstract public void printPascal(int n);
    abstract public int binom(int n, int k);

    protected void validateParameters(int n) {
        if (n < 0) {
            String e = "The parameter n must be a positive integer";
            throw new InvalidParameterException(e);
        }
    }

    protected void validateParameters(int n, int k) {
        validateParameters(n);

        if (k < 0 || k > n) {
            String e = "The parameter k must be a positive integer smaller than n";
            throw new InvalidParameterException(e);
        }
    }

    protected String getCoeffHashKey(int n, int k) {
        return n + "," + (k > (n / 2) ? (n - k) : k);
    }
}
