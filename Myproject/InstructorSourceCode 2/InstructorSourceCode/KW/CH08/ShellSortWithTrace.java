/*<exercise chapter="8" section="6" type="programming" number="2">*/
package KW.CH08;

import java.util.*;

public class ShellSortWithTrace {

    public static <T extends Comparable<T>>  void sort(T[] table) {
        int gap = table.length / 2;
        int pass = 0;
        CountingComparator<T> c = new CountingComparator<T>();
        int numExchanges = 0;
        boolean exchanges = false;
        System.out.print(gap + "\t");
        System.out.print(pass + "\t");
        printArray(table);
        System.out.println("\t" + c.getCount() + "\t" + numExchanges);
        while (gap > 0) {
            pass = 1;
            for (int nextPos = gap; nextPos < table.length; nextPos++) {
                c.clear();
                numExchanges = 0;
                T nextVal = table[nextPos];
                int j = nextPos;
                while (j > gap - 1 && c.compare(nextVal, table[j - gap]) < 0) {
                    ++numExchanges;
                    table[j] = table[j - gap];
                    j -= gap;
                }
                table[j] = nextVal;
                System.out.print(gap + "\t");
                System.out.print(pass + "\t");
                printArray(table);
                System.out.println("\t" + c.getCount() + "\t" + numExchanges);
                pass++;
            }
            if (gap == 2) {
                gap = 1;
            } else {
                gap = (int) (gap / 2.2);
            }
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
