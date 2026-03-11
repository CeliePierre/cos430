/*<exercise chapter="8" section="7" type="programming" number="1">*/
package KW.CH08;

import java.util.*;

public class MergeSortWithTrace {

    public static void sort(int[] a, int level) {
        for (int kk = 0; kk < level; kk++) {
            System.out.print("\t");
        }
        System.out.printf("sort(%s)%n", arrayToString(a));
        if (a.length > 1) {
            int halfSize = a.length / 2;
            for (int kk = 0; kk < level + 1; kk++) {
                System.out.print("\t");
            }
            System.out.printf("halfSize = %d%n", halfSize);
            int[] leftTable = new int[halfSize];
            int[] rightTable = new int[a.length - halfSize];
            System.arraycopy(a, 0, leftTable, 0, halfSize);
            System.arraycopy(a, halfSize, rightTable, 0, a.length - halfSize);
            for (int kk = 0; kk < level + 1; kk++) {
                System.out.print("\t");
            }
            System.out.printf("leftTable = %s%n", arrayToString(leftTable));
            for (int kk = 0; kk < level + 1; kk++) {
                System.out.print("\t");
            }
            System.out.printf("rightTable = %s%n", arrayToString(rightTable));
            sort(leftTable, level + 1);
            sort(rightTable, level + 1);
            merge(leftTable, rightTable, a);
            for (int kk = 0; kk < level + 1; kk++) {
                System.out.print("\t");
            }
            System.out.printf("merge(%s, %s) = %s%n", arrayToString(leftTable),
                    arrayToString(rightTable), arrayToString(a));
        }
    }

    public static void merge(int[] left, int[] right, int[] a) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                a[k++] = left[i++];
            } else {
                a[k++] = right[j++];
            }
        }
        while (i < left.length) {
            a[k++] = left[i++];
        }
        while (j < right.length) {
            a[k++] = right[j++];
        }
    }

    public static String arrayToString(int[] a) {
        ArrayList<Integer> l = new ArrayList<Integer>();
        for (int x : a) {
            l.add(x);
        }
        return l.toString();
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        for (String s : args) {
            list.add(new Integer(s));
        }
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        sort(array, 0);
    }
}
/*</exercise>*/
