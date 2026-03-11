/*<exercise chapter="8" section="2" type="programming" number="2">*/
package KW.CH08;

import java.util.*;

public class SelectionSortWithTrace {

    public static <T extends Comparable<T>>  void sort(T[] table) {
        CountingComparator<T> c = new CountingComparator<T>();
        int pass = 0;
        c.clear();

        int numExchanges = 0;
        boolean exchanges = false;
        System.out.print(pass + "\t");
        printArray(table);
        System.out.println("\t" + c.getCount() + "\t" + numExchanges);
        pass++;

        int n = table.length;

        for (int fill = 0; fill < (n - 1); fill++) {
            numExchanges = 0;
            c.clear();

            int posMin = fill;

            for (int next = fill + 1; next < n; next++) {
                if (c.compare(table[next], table[posMin]) < 0) {
                    posMin = next;
                }
            }

            T temp = table[fill];
            table[fill] = table[posMin];
            table[posMin] = temp;
            ++numExchanges;
            System.out.print(pass + "\t");
            printArray(table);
            System.out.println("\t" + c.getCount() + "\t"
                    + numExchanges);
            pass++;
        }
    }

    private static <T> void printArray(T[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);

            if (i < (array.length + 1)) {
                System.out.print(" ");
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();

        for (String s : args) {
            list.add(new Integer(s));
        }

        Integer[] array = list.toArray(new Integer[list.size()]);
        sort(array);
    }
}
/*</exercise>*/
