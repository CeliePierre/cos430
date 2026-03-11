/*<exercise chapter="8" section="2" type="programming" number="1">*/
package KW.CH08;

/** Implements the selection sort algorithm.
 *  @author Koffman and Wolfgang
 **/
public class ReverseSelectionSort implements SortAlgorithm {

    /**
     * Sort the array in reverse order using the Selection Sort algorithm.
     * @pre  table contains Comparable objects.
     * @post table is sorted.
     * @param table The array to be sorted
     */
    @Override
    public <T extends Comparable<T>>  void sort(T[] table) {
        int n = table.length;
        for (int fill = 0; fill < n - 1; fill++) {
            // Invariant: table[0 . . . fill - 1] is sorted.
            int posMax = fill;
            for (int next = fill + 1; next < n; next++) {
                // Invariant: table[posMax] is the largest item in
                // table[fill . . . next - 1].
                if (table[next].compareTo(table[posMax]) > 0) {
                    posMax = next;
                }
            }
            // assert: table[posMax] is the largest item in
            // table[fill . . . n - 1].
            // Exchange table[fill] and table[posMax].
            if (fill != posMax) {
                T temp = table[fill];
                table[fill] = table[posMax];
                table[posMax] = temp;
            }
            // assert: table[fill] is the largest item in
            // table[fill . . . n - 1].
        }
        // assert: table[0 . . . n - 1] is sorted in reverse order
    }
}
/*</exercise>*/
