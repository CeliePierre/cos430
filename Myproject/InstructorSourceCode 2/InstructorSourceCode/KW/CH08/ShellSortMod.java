/*<listing chapter="8" number="4">*/
package KW.CH08;

/** Implements the Shell sort algorithm.
 *  @author Koffman and Wolfgang
 **/
public class ShellSortMod implements SortAlgorithm {
    /*<exercise chapter="8" section="6" type="programming" number="1">*/

    /**
     * Sort the table using Shell sort algorithm.
     * @pre  table contains Comparable objects.
     * @post table is sorted.
     * @param table The array to be sorted
     */
    @Override
    public <T extends Comparable<T>>  void sort(T[] table) {
        // Gap between adjacent elements.
        int gap = table.length / 2;
        while (gap > 0) {
            for (int nextPos = gap; nextPos < table.length;
                    nextPos++) {
                // Insert element at nextPos in its subarray.
                int nextInsert = nextPos;
                T nextVal = table[nextInsert]; // Element to insert.
                // Shift all values > nextVal in subarray down by gap.
                while ((nextInsert > gap - 1) // First element not shifted.
                        && (nextVal.compareTo(table[nextInsert - gap]) < 0)) {
                    table[nextInsert] = table[nextInsert - gap]; // Shift down.
                    nextInsert -= gap; // Check next position in subarray.
                }
                table[nextInsert] = nextVal; // Insert nextVal.
            } // End for.

            // Reset gap for next pass.
            if (gap == 2) {
                gap = 1;
            } else {
                gap = (int) (gap / 2.2);
            }
        } // End while.
    } // End sort.
    /*</exercise>*/
}
