/*<exercise chapter="8" section="10" type="programming" number="3">*/
package KW.CH08;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * Program to test and time SortAlgorithms
 * @author Paul Wolfgang
 */
public class TestSorts {

    /** Array of SortAlgorithms */
    private static SortAlgorithm[] algorithms = {new SelectionSort(),
        new BubbleSort(),
        new InsertionSort(),
        new InsertionSortMod(),
        new ShellSort(),
        new ShellSortMod(),
        new MergeSort(),
        new HeapSort(),
        new QuickSort1(),
        new QuickSort2(),
        new QuickSort3()
    };
    /** Array of sorting times */
    double[] times = new double[algorithms.length];

    /**
     * Method to time a sort algorithm.  The algorithm is run 11 times.
     * The result of the first time is not kept and the average of the
     * other 10 is computed.
     * @param algorithm The algorithm to time
     * @param table The table of values to sort
     * @return average runtime in seconds
     */
    private static double timeSort(SortAlgorithm algorithm, Integer[] table) {
        System.out.println("Sorting using " + algorithm.getClass().getName());
        Integer[] copy = new Integer[table.length];
        System.arraycopy(table, 0, copy, 0, table.length);
        algorithm.sort(table);
        long totalTime = 0;
        for (int i = 0; i < 10; i++) {
            System.arraycopy(copy, 0, table, 0, table.length);
            long startTime = System.nanoTime();
            algorithm.sort(table);
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
        }
        return (double) (totalTime) / 10e9;
    }

    /**
     * Method to time the Arrays.sort algorithm.  The algorithm is run
     * 11 times. The result of the first time is not kept and the average 
     * of the other 10 is computed.
     * @param algorithm The algorithm to time
     * @param table The table of values to sort
     * @return average runtime in seconds
     */
    private static double timeArraysSort(Integer[] table) {
        Integer[] copy = new Integer[table.length];
        System.arraycopy(table, 0, copy, 0, table.length);
        Arrays.sort(table);
        long totalTime = 0;
        for (int i = 0; i < 10; i++) {
            System.arraycopy(copy, 0, table, 0, table.length);
            long startTime = System.nanoTime();
            Arrays.sort(table);
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
        }
        return (double) (totalTime) / 10e9;
    }

    /**
     * Verifies that the elements in array are in increasing order
     * @param test The array to verify
     * @return true if the elements are in increasing order
     */
    private static <T extends Comparable<T>>  boolean verify(T[] test) {
        boolean ok = true;
        for (int i = 1; ok && i < test.length; i++) {
            ok = test[i - 1].compareTo(test[i]) <= 0;
        }
        return ok;
    }

    /**
     * Main method to time and test sort algorithms.  A table of random
     * integers is generated and sorted using all of the sort algorithms.
     * Timing data is collected and compared to the timing for Arrays.sort.
     * @param args[0] If present is the size of array to test if it is an
     * integer.  It not an integer, it is then assumed to be the name of
     * a file that contains the data to be sorted
     */
    public static void main(String[] args) {
        int size = -1;
        Integer[] original = null;
        if (args.length > 0) {
            try {
                size = Integer.parseInt(args[0]);
            } catch (NumberFormatException ex) {
                try {
                    Scanner in = new Scanner(new FileReader(args[0]));
                    List<Integer> list = new ArrayList<Integer>();
                    while (in.hasNextInt()) {
                        list.add(in.nextInt());
                    }
                    size = list.size();
                    original = list.toArray(new Integer[size]);
                } catch (IOException ioex) {
                    System.err.println("Error reading from " + args[0]);
                    System.err.println(ioex);
                }
            }
        }
        if (size < 0) {
            Scanner scan = new Scanner(System.in);
            while (size < 0) {
                System.out.print("Enter the size of the array: ");
                try {
                    size = scan.nextInt();
                } catch (InputMismatchException ex) {
                    // Ignore this exception
                }
            }
        }
        //size >= 0
        if (original == null) {
            Random rand = new Random();
            original = new Integer[size];
            for (int i = 0; i < size; i++) {
                original[i] = rand.nextInt();
            }
        }
        double[] times = new double[algorithms.length];
        for (int j = 0; j < algorithms.length; j++) {
            Integer[] table = Arrays.copyOf(original, original.length);
            times[j] = timeSort(algorithms[j], table);
            if (!verify(table)) {
                System.err.println(algorithms[j].getClass() + " failed");
            }
        }
        double arraysSortTime = timeArraysSort(original);
        System.out.printf("Arrays.sort took %.3f seconds to sort %d integers%n",
                arraysSortTime, size);
        for (int j = 0; j < algorithms.length; j++) {
            System.out.printf("%s took %.3f seconds%n",
                    algorithms[j].getClass().getName(), times[j]);
        }
    }
}

/*</exercise>*/
