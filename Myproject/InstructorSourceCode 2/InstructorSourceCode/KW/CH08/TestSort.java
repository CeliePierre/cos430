/*<listing chapter="8" number="11">*/
package KW.CH08;

/**
 * Driver program to test sorting methods.
 * @author Koffman and Wolfgang
 */
import javax.swing.JOptionPane;
import java.util.Random;
import java.util.Arrays;
import java.io.FileReader;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class TestSort {

    /**
     * main method
     * @param args
     * arg[0] optionally contains the name of a data file
     */
    public static void main(String[] args) {
        Integer[] items = null;
        Integer[] copy = null;
        int size = 0;
        /*<exercise chapter="8" section="10" type="programming" number="2">*/
        if (args.length > 0 && args[0] != null) {
            try {
                List<Integer> list = new ArrayList<Integer>();
                Scanner in = new Scanner(new FileReader(args[0]));
                while (in.hasNextInt()) {
                    list.add(in.nextInt());
                }
                size = list.size();
                items = list.toArray(new Integer[size]);
                copy = Arrays.copyOf(items, items.length);
            } catch (IOException ioex) {
                System.err.println("Error reading from " + args[0]);
                System.err.println(ioex);
            }
        }
        if (items == null) {
            /*</exercise>*/
            size = Integer.parseInt(JOptionPane.showInputDialog("Enter Array size:"));
            items = new Integer[size]; // Array to sort.
            copy = new Integer[size]; // Copy of array.
            Random rInt = new Random(); // For random number generation

            // Fill the array and copy with random Integers.
            for (int i = 0; i < items.length; i++) {
                items[i] = rInt.nextInt();
                copy[i] = items[i];
            }
            /*<exercise chapter="8" section="10" type="programming" number="2">*/
        }
        /*</exercise>*/
        // Sort with utility method.
        long startTime = System.currentTimeMillis();
        Arrays.sort(items);
        System.out.println("Utility sort time is "
                + (System.currentTimeMillis()
                - startTime) + "ms");
        JOptionPane.showMessageDialog(null,
                "Utility sort successful (true/false): "
                + verify(items));

        // Reload array items from array copy.
        for (int i = 0; i < items.length; i++) {
            items[i] = copy[i];
        }

        // Sort with quicksort.
        startTime = System.currentTimeMillis();
        (new QuickSort3()).sort(items);
        System.out.println("QuickSort time is "
                + (System.currentTimeMillis()
                - startTime) + " ms");
        JOptionPane.showMessageDialog(null,
                "Your Sort successful (true/false): "
                + verify(items));

        dumpTable(items); // Display part of the array.
        System.exit(0);
    }

    /** Verifies that the elements in array test are
    in increasing order.
    @param test The array to verify
    @return true if the elements are in increasing order;
    false if any 2 elements are not in increasing order
     */
    private static boolean verify(Comparable[] test) {
        boolean ok = true;
        int i = 0;
        while (ok && i < test.length - 1) {
            ok = test[i].compareTo(test[i + 1]) <= 0;
            i++;
        }
        return ok;
    }

    /*<exercise chapter="8" section="10" type="programming" number="1">*/
    private static void dumpTable(Integer[] thetable) {
        if (thetable.length <= 20) {
            for (int i = 0; i < thetable.length; i++) {
                System.out.println(i + ": " + thetable[i]);
            }
        } else {
            int mid = 10;
            for (int i = 0; i < mid; i++) {
                System.out.println(i + ": " + thetable[i]);
            }
            if (mid == 10) {
                System.out.println(". . .");
            }
            for (int i = thetable.length - mid; i < thetable.length; i++) {
                System.out.println(i + ": " + thetable[i]);
            }
        }
    }
    /*</exercise>*/
}
/*</listing>*/
