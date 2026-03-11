/*<exercise chapter="8" section="3" type="programming" number="1">*/
package KW.CH08;

import java.util.*;

public class BubbleSortWithTrace {

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

        do {
            exchanges = false;
            numExchanges = 0;
            c.clear();

            for (int i = 0; i < (table.length - pass); i++) {
                if (c.compare(table[i], table[i + 1]) > 0) {
                    ++numExchanges;

                    T temp = table[i];
                    table[i] = table[i + 1];
                    table[i + 1] = temp;
                    exchanges = true;
                }
            }

            System.out.print(pass + "\t");
            printArray(table);
            System.out.println("\t" + c.getCount() + "\t"
                    + numExchanges);
            pass++;
        } while (exchanges);
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
