package KW.CH08;

/** Implements the insertion sort algorithm.
 *  @author Koffman and Wolfgang
 **/
public class InsertionSortMod implements SortAlgorithm {
    /*<exercise chapter="8" section="4" type="programming" number="1">*/

    /**
     * Sort the table using insertion sort algorithm.
     * @pre  table contains Comparable objects.
     * @post table is sorted.
     * @param table The array to be sorted
     */
    @Override
    public <T extends Comparable<T>>  void sort(T[] table) {
        for (int nextPos = 1; nextPos < table.length; nextPos++) {
            // Invariant: table[0 . . . nextPos - 1] is sorted.
            // Insert element at position nextPos
            // in the sorted subarray.
            T nextVal = table[nextPos]; // Element to insert.
            int nextInsert = nextPos;
            while (nextInsert > 0
                    && nextVal.compareTo(table[nextInsert - 1]) < 0) {
                table[nextInsert] = table[nextInsert - 1]; // Shift down.
                nextInsert--; // Check next smaller element.
            }
            // Insert nextVal at nextPos.
            table[nextInsert] = nextVal;
        } // End for.
    } // End sort.
    /*</exercise>*/
}
