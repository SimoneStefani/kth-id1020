import java.security.InvalidParameterException;

/**
 * Created by S. Stefani on 2016-11-04.
 */
public abstract class ErrorPascal implements Pascal {

    public void validateParameters(int n) {
        if (n < 0) {
            String e = "The parameter n must be a positive integer";
            throw new InvalidParameterException(e);
        }
    }

    public void validateParameters(int n, int k) {
        if (n < 0) {
            String e = "The parameter n must be a positive integer";
            throw new InvalidParameterException(e);
        }

        if (k < 0 || k > n) {
            String e = "The parameter k must be a positive integer smaller than n";
            throw new InvalidParameterException(e);
        }
    }

}
