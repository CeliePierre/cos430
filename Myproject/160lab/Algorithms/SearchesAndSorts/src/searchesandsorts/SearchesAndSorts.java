    /*
 *                        Revision History
 *  ==========================================================
 *  4/20/2019 -- Combined bits from labs written back in 2005 
 *               and labs done in Spring 2019 AA
 */
package searchesandsorts;

import java.util.Arrays;

/**
 *
 * @author Anne
 */
public class SearchesAndSorts {

    final static int ARRAY_SIZE = 16384;    //double for each trial
    final static int RANGE = 50000;      // range of numbers to be generated
    final static int LOW = 1;            // starting number
    private int iterationCount;          // will indicate the work done

    /**
     * Generates an array of random numbers in the range of RANGE with a
     * starting number of LOW
     *
     * @param a an integer array of ARRAY_SIZE
     */
    public void generateArrayElements(int[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * RANGE + LOW);
        }
    }

    /**
     * Almost all sorts use a swap algorithm, so a swap method saves lots of
     * lines of code! This method accepts an array and the indeces of the
     * elements to be swapped and swaps them.
     *
     * @param list an integer array
     * @param i the index of one element to be moved
     * @param j the index of the other element to be moved
     */
    public void swap(int[] list, int i, int j) {
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }

    /**
     * Insertion sort work a bit like what we do when we sort a hand of cards.
     *
     * @param a
     */
    public void insertionSort(int[] a) {
        int j, temp;
        for (int i = 1; i < a.length - 1; i++) {
            temp = a[i];
            j = i;
            iterationCount++;
            while (j > 0 && a[j - 1] > temp) {
                iterationCount++;
                a[j] = a[j - 1];
                j = j - 1;
            }
            a[j] = temp;
        }
    }

    /**
     * Selection Sort finds the smallest element and puts it in first element of
     * the unsorted array. Decreases the size of the array to search at each
     * iteration.
     *
     * @param a the integer array
     */
    public void selectionSort(int[] a) {

        int i, j, iMin;
        /* advance the position through the entire array */
        for (j = 0; j < a.length - 1; j++) {
            iterationCount++;
            /* find the min element in the unsorted a[j .. n-1] */
            /* assume the min is the first element initially */
            iMin = j;
            /* test against elements after j to find the smallest */
            for (i = j + 1; i < a.length; i++) {
                iterationCount++;
                /* if this element is less, then it is the new minimum */
                if (a[i] < a[iMin]) {
                    /* found new minimum; remember its index */
                    iMin = i;
                }
            }
            if (iMin != j) {
                swap(a, j, iMin);
            }
        }
    }

    /**
     * Top level merge sort method. does setup for the other one by creating a
     * second array and initializing right and left.
     *
     * @param a an unsorted integer array
     */
    public void mergeSort(int[] a) {
        int[] tempArray = new int[a.length];
        mergeSort(a, tempArray, 0, a.length - 1);
    }

    /**
     * MergeSort method that partitions the array into halves and calls itself
     * to continue that process.
     *
     * @param a an integer array
     * @param tempArray an integer array for intermediate results
     * @param left the low index
     * @param right the high index
     */
    public void mergeSort(int[] a, int[] tempArray,
            int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tempArray, left, center);
            mergeSort(a, tempArray, center + 1, right);
            merge(a, tempArray, left, center + 1, right);
        }
    }

    /**
     * Merge method merges two sorted lists into one There is some REALLY
     * interesting code here. Notice that the indeces are being incremented
     * after the assignment statement.
     *
     * @param a The original unsorted integer array
     * @param tempArray an integer array for intermediate results
     * @param leftPos the starting index for the left half of the array
     * @param rightPos the starting index for the right half of the array
     * @param rightEnd the index for the end of the right half of the array
     */
    public void merge(int[] a, int[] tempArray,
            int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tempPos = leftPos;
        int numElements = rightEnd - leftPos + 1;
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            iterationCount++;
            if (a[leftPos] < a[rightPos]){ 
            // first the assignment happens, then both indeces
            // are incremented.
                tempArray[tempPos++] = a[leftPos++];
            } else {
                tempArray[tempPos++] = a[rightPos++];
            }
        }
        // ahead there be dragons ....
        // only 1 of these 2  loops will execute.
        while (leftPos <= leftEnd){ // copy the rest of the first half
            iterationCount++;
            tempArray[tempPos++] = a[leftPos++];
        }
        while (rightPos <= rightEnd){ // copy the rest of the right half
            iterationCount++;
            tempArray[tempPos++] = a[rightPos++];
        }
        //copy tempArray back to the original array
        for (int i = 0; i < numElements; i++, rightEnd--) {
            //iterationCount++;
            a[rightEnd] = tempArray[rightEnd];
        }
    }
    /**
     * Classic Linear Search
     * @param lookingAt the array we are searching
     * @param lookingFor the element we are trying to find
     * @return the index of lookingFor or a -1 if not found
     */
    public int linearSearch(int[] lookingAt, int lookingFor){
        int index = 0;
        while(index < lookingAt.length && lookingAt[index] != lookingFor){
            iterationCount++;
            index++;
        }
        if (index == lookingAt.length)
            index = -1;
        return index;
    }
    /**
     * Classic binary search
     * @param lookingAt the array we are searching
     * @param lookingFor the element we are looking for
     * @return the index of looking for or the negative index of where it 
     *         would be if it were in the array.
     */
    public int binarySearch(int[] lookingAt, int lookingFor){
        int low = 0,
            high = lookingAt.length-1,
            mid = 0;
        boolean found = false;
        while (low < high && !found){
            iterationCount++;
            mid = (high - low)/ 2 + low;
            if (lookingAt[mid] == lookingFor){
                found = true;
            }else if (lookingAt[mid] > lookingFor){
                high = mid -1;
            }else{
                low = mid + 1;
            }
        }
        if (!found){
            mid = -(mid-1);
        }
        return mid;
    }
    public void runSorts(){
        int[] original = new int[ARRAY_SIZE];
        generateArrayElements(original);
        int[]selSortArray = Arrays.copyOf(original,ARRAY_SIZE );
        int[]mergeSortArray = Arrays.copyOf(original,ARRAY_SIZE );
        int[]insertSortArray = Arrays.copyOf(original,ARRAY_SIZE );
        System.out.println("N for this trial is " + ARRAY_SIZE);
        System.out.println("N squared is " + Math.pow(ARRAY_SIZE, 2));
        double logN = Math.log((double)ARRAY_SIZE)/Math.log(2.0);
        System.out.printf("log(n) is %.5f\n", logN);
        System.out.println("and nlog(n) is " + (ARRAY_SIZE * logN) );
        iterationCount = 0;
        selectionSort(selSortArray);
        System.out.printf("Selection Sort required %d iterations.\n",
                  iterationCount);
        iterationCount = 0;
        insertionSort(insertSortArray);
        System.out.printf("Insertion Sort required %d iterations.\n",
                   iterationCount);
        iterationCount = 0;
        mergeSort(mergeSortArray);
        System.out.printf("Merge Sort     required %d iterations.\n",
                  iterationCount);
        
    }
    public void runSearches(){
        int[] original = new int[ARRAY_SIZE];
        generateArrayElements(original);
        int toFind = (int) (Math.random() * RANGE + LOW);
        int unsorted[] = Arrays.copyOf(original,ARRAY_SIZE );
        int sorted[] = Arrays.copyOf(original,ARRAY_SIZE );
        mergeSort(sorted);
        iterationCount = 0;
        int result = linearSearch(unsorted, toFind);
        System.out.print("The element was ");
        if (result >= 0){
            System.out.println("found. ");
        }else{
            System.out.println("not found.");
        }
        System.out.printf("Linear search required %d iterations.\n",
                iterationCount);       
        
        iterationCount = 0;
        result = binarySearch(sorted, toFind);
        System.out.printf("Binary search required %d iterations "
                + "and reported a result of %d\n", iterationCount, result);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SearchesAndSorts sas = new SearchesAndSorts();
        sas.runSorts(); 
        sas.runSearches();
    }

}
