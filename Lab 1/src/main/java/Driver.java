/**
 * Driver.java
 *
 * Created by S. Stefani on 2016-11-04.
 */

import edu.princeton.cs.introcs.StdIn;

public class Driver {
    public static void main(String[] args) {

        System.out.println("|| ID1020 - Algorithms and Data Structures LAB 1 ||\n");

        System.out.print("How many levels of the Pascal's triangle do you want to print? ");
        int n = StdIn.readInt();
        System.out.print("Do you want to print the triangles upside-down? [y/n] ");
        String direction = StdIn.readString();
        System.out.println();

        boolean upsideDown = direction.equals("y");
        RecursivePascal recPasc = new RecursivePascal(upsideDown);
        IterativePascal iterPasc = new IterativePascal(upsideDown);

        System.out.print("Print recursive triangle:\n");
        recPasc.printPascal(n);
        System.out.print("\n \nPrint iterative triangle:\n");
        iterPasc.printPascal(n);
    }
}
