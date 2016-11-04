/**
 * Created by S. Stefani on 2016-11-04.
 */
public class Driver {
    public static void main(String[] args) {
        RecursivePascal recPasc = new RecursivePascal();
        recPasc.upsideDown = true;
        recPasc.coeffTable = new int[51][51];
        recPasc.printPascal(10);
        System.out.println();

        IterativePascal iterPasc = new IterativePascal();
        iterPasc.printPascal(10);
        System.out.println();
        iterPasc.printPascal(-4);
        System.out.println();
    }
}
