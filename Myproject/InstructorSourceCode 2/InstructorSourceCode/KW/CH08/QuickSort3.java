package KW.CH08;

/**
 * Class to extend QuickSort implementing the second version of the
 * partition Algorithm
 * @author Paul Wolfgang
 */
public class QuickSort3 extends QuickSort2 {

    /*<exercise chapter="8" section="11" type="programming" number="1">*/
    /**
     * Sort a part of the table using the quicksort algorithm.
     * @ @post The part of table from first through last is sorted.
     * @param table The array to be sorted
     * @param first The index of the low bound
     * @param last The index of the high bound
     */
    @Override
    protected <T extends Comparable<T>>  void quickSort(T[] table,
            int first,
            int last) {
        if (first < last) { // There is data to be sorted.
            // Partition the table.
            int[] pivIndex = partition3(table, first, last);
            // Sort the left half.
            quickSort(table, first, pivIndex[0] - 1);
            // Sort the right half.
            quickSort(table, pivIndex[1] + 1, last);
        }
    }

    /**
     * Partition the table so that values from first to pivIndex[0]
     * are less than the pivot value, and values from
     * pivIndex[1] to last are greater than the pivot value.
     * This partitioning algorithm is based upon Dijkstra's
     * Dutch National Flag problem.
     * @param table The table to be partitioned
     * @param first The index of the low bound
     * @param last  The index of the high bound
     * @return The boundaries of the pivot value
     */
    protected <T extends Comparable<T>>  int[] partition3(T[] table,
            int first,
            int last) {
        // Put the median of table[first], table[middle], table[last]
        // int table[first] and use this value as the pivot
        bubbleSort3(table, first, last);
        // Pick the middle value for the pivot.
        T pivot = table[first + (last - first) / 2];
        int less = first;
        int equal = last;
        int greater = last;
        // Invariant:
        // for all i, 0 <= i <=last
        // 0 <= i < less ==> table[i] < pivot
        // less <= i <= equal table[i] is unknown
        // equal < i < greater table[i] == pivot
        // greater < i <= last table[i] > pivot
        while (less <= equal) {
            if (table[equal].compareTo(pivot) == 0) {
                equal--;
            } else if (table[equal].compareTo(pivot) < 0) {
                swap(table, less, equal);
                less++;
            } else {
                swap(table, equal, greater);
                equal--;
                greater--;
            }
        }
        return new int[]{less, greater};
    }
    /*</exercise>*/
}
