/*<exercise chapter="8" section="4" type="programming" number="2">*/
package KW.CH08;

import java.util.*;

public class InsertionSortWithTrace {

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
        for (int nextPos = 1; nextPos < table.length; nextPos++) {
            numExchanges = 0;
            T nextVal = table[nextPos];
            int j = nextPos;
            while (j > 0 && c.compare(nextVal, table[j - 1]) < 0) {
                ++numExchanges;
                table[j] = table[j - 1];
                j--;
            }
            table[j] = nextVal;
            System.out.print(pass + "\t");
            printArray(table);
            System.out.println("\t" + c.getCount() + "\t" + numExchanges);
            pass++;
            c.clear();
        }
    }

    private static <T> void printArray(T[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length + 1) {
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
