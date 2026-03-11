/*<exercise chapter="8" section="9" type="programming" number="1">*/
package KW.CH08;

import java.util.*;

public class QuickSort1WithTrace {

    public static <T extends Comparable<T>>  void sort(T[] table) {
        sort(table, 0, table.length - 1, 0);
    }

    private static <T extends Comparable<T>>  void sort(T[] table,
            int first,
            int last,
            int level) {
        for (int i = 0; i < level; i++) {
            System.out.print(" ");
        }
        System.out.printf("sort(%s, %d, %d)%n",
                arrayToString(table), first, last);
        if (first < last) {
            for (int i = 0; i < level; i++) {
                System.out.print(" ");
            }
            int pivIndex = partition(table, first, last);
            System.out.println("pivIndex: " + pivIndex);
            sort(table, first, pivIndex - 1, level + 1);
            for (int i = 0; i < level + 1; i++) {
                System.out.print(" ");
            }
            System.out.printf("table: %s%n", arrayToString(table));
            sort(table, pivIndex + 1, last, level + 1);
            for (int i = 0; i < level + 1; i++) {
                System.out.print(" ");
            }
            System.out.printf("table: %s%n", arrayToString(table));
        }
    }

    private static <T extends Comparable<T>>  int partition(T[] table,
            int first,
            int last) {
        T pivot = table[first];
        int up = first;
        int down = last;
        do {
            while ((up < last) && (pivot.compareTo(table[up]) >= 0)) {
                ++up;
            }
            while (pivot.compareTo(table[down]) < 0) {
                --down;
            }
            if (up < down) {
                swap(table, up, down);
            }
        } while (up < down);
        swap(table, first, down);
        return down;
    }

    private static <T> void swap(T[] table, int i, int j) {
        T temp = table[i];
        table[i] = table[j];
        table[j] = temp;
    }

    private static <T> String arrayToString(T[] array) {
        StringBuilder stb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            stb.append(array[i]);
            if (i < array.length - 1) {
                stb.append(", ");
            } else {
                stb.append("]");
            }
        }
        return stb.toString();
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
