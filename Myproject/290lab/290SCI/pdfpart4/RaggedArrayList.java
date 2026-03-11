/**
 * File: RaggedArrayList.java
 * ****************************************************************************
 *                           Revision History
 * ****************************************************************************
 * 2023-09-28 - wbrown - rewrote findFront() to remove nested loop and return
 *                       the position at the end of the previous subarray if
 *                       possible. passes all tests
 * 9/2023 joel kalala I Initialized indices for Level 1 (L1) and Level 2 (L2)
 *        arrays for both classes
 * 9/2023 - Joel kalala - I added the Loop through the L1 array until the end is reached or 
 * until a null element is encountered Obtain the current L2 array from the L1 array
 * the second Loop through the L2 array to find the correct position for the item
 * or until the end of the L2 array is reached.
 * I added If we find the item in the L2 array, return its location
 * If we've reached this point, the item is not in the current L2 array
 * but if the current index (i2) hasn't reached the end of the L2 array,
 * If the item isn't in the current L2 array and i2 has reached its end,
 * reset i2 and proceed to the next element in the L1 array.
 * 
 * 9/2023 - William brown - fixed findEnd() and findFront() methods, fixing tests
 *                          #10, #13, #16-#18, #20-#28, code satisfies all tests
 *  revised documentation for findEnd() and findFront(),
 *                       minor formatting changes
 * 
 *  
 * 8/2015 - Anne Applin - Added formatting and JavaDoc
 * 2015 - Bob Boothe - starting code
 * ****************************************************************************
 */
package student;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

/**
 * *
 * The RaggedArrayList is a 2 level data structure that is an array of arrays.
 *
 * It keeps the items in sorted order according to the comparator. Duplicates
 * are allowed. New items are added after any equivalent items.
 *
 * NOTE: normally fields, internal nested classes and non API methods should all
 * be private, however they have been made public so that the tester code can
 * set them
 *
 * @author Bob Booth
 * @param <E> A generic data type so that this structure can be built with any
 * data type (object)
 */
public class RaggedArrayList<E> implements Iterable<E> {

    // must be even so when split get two equal pieces
    private static final int MINIMUM_SIZE = 4;
    /**
     * The total number of elements in the entire RaggedArrayList
     */
    public int size;
    /**
     * really is an array of L2Array, but compiler won't let me cast to that
     */
    public Object[] l1Array;
    /**
     * The number of elements in the l1Array that are used.
     */
    public int l1NumUsed;
    /**
     * a Comparator object so we can use compare for Song
     */
    private Comparator<E> comp;

    /**
     * create an empty list always have at least 1 second level array even if
     * empty, makes code easier (DONE - do not change)
     *
     * @param c a comparator object
     */
    public RaggedArrayList(Comparator<E> c) {
        size = 0;
        // you can't create an array of a generic type
        l1Array = new Object[MINIMUM_SIZE];
        // first 2nd level array
        l1Array[0] = new L2Array(MINIMUM_SIZE);
        l1NumUsed = 1;
        comp = c;
    }

    /**
     * ***********************************************************
     * nested class for 2nd level arrays read and understand it. (DONE - do not
     * change)
     */
    public class L2Array {

        /**
         * the array of items
         */
        public E[] items;
        /**
         * number of items in this L2Array with values
         */
        public int numUsed;

        /**
         * Constructor for the L2Array
         *
         * @param capacity the initial length of the array
         */
        public L2Array(int capacity) {
            // you can't create an array of a generic type
            items = (E[]) new Object[capacity];
            numUsed = 0;
        }
    }// end of nested class L2Array

    // ***********************************************************
    /**
     * total size (number of entries) in the entire data structure (DONE - do
     * not change)
     *
     * @return total size of the data structure
     */
    public int size() {
        return size;
    }

    /**
     * null out all references so garbage collector can grab them but keep
     * otherwise empty l1Array and 1st L2Array (DONE - Do not change)
     */
    public void clear() {
        size = 0;
        // clear all but first l2 array
        Arrays.fill(l1Array, 1, l1Array.length, null);
        l1NumUsed = 1;
        L2Array l2Array = (L2Array) l1Array[0];
        // clear out l2array
        Arrays.fill(l2Array.items, 0, l2Array.numUsed, null);
        l2Array.numUsed = 0;
    }

    /**
     * *********************************************************
     * nested class for a list position used only internally 2 parts: level 1
     * index and level 2 index
     */
    public class ListLoc {

        /**
         * Level 1 index
         */
        public int level1Index;

        /**
         * Level 2 index
         */
        public int level2Index;

        /**
         * Parameterized constructor DONE (Do Not Change)
         *
         * @param level1Index input value for property
         * @param level2Index input value for property
         */
        public ListLoc(int level1Index, int level2Index) {
            this.level1Index = level1Index;
            this.level2Index = level2Index;
        }

        /**
         * test if two ListLoc's are to the same location (done -- do not
         * change)
         *
         * @param otherObj the other listLoc
         * @return true if they are the same location and false otherwise
         */
        public boolean equals(Object otherObj) {
            // not really needed since it will be ListLoc
            if (getClass() != otherObj.getClass()) {
                return false;
            }
            ListLoc other = (ListLoc) otherObj;

            return level1Index == other.level1Index
                    && level2Index == other.level2Index;
        }

        /**
         * move ListLoc to next entry when it moves past the very last entry it
         * will be one index past the last value in the used level 2 array. Can
         * be used internally to scan through the array for sublist also can be
         * used to implement the iterator.
         */
        public void moveToNext() {
            // TO DO IN PART 5 and NOT BEFORE
        }
    }

    /**
     * find 1st matching entry
     *
     * I Initialized indices for the primary array (L1Array)
     * and secondary array (L2Array). I Iterate through L1Array Cast the current
     * element of L1Array to L2Array for access through L2Array until an item
     * that's not less than the target is found If an item is found that matches
     * the target, return its location
     * @author joelkalala
     * I Initialized indices for Level 1 (L1) and Level 2 (L2) arrays
     * Loop through the L1 array until the end is reached or until a null
     * element is encountered
     * @param item the thing we are searching for a place to put.
     * @return ListLoc of 1st matching item, or 1st item greater than the item
     * if no match. This might be an unused slot at the end of a level 2 array
     * Revised by: wbrown
     */
    public ListLoc findFront(E item) {
        // part 3
        int i1 = 0;
        int i2 = 0;
        
        if (size > 0) {
            L2Array l2Array = (L2Array) l1Array[i1];
            
            // search l1Array
            while (i1 < l1NumUsed - 1 && comp.compare(
                    item, l2Array.items[l2Array.numUsed - 1]) > 0) {
                i1++;
                l2Array = (L2Array) l1Array[i1];
            }
            
            // search within l2Array
            while (i2 < l2Array.numUsed && comp.compare(
                    l2Array.items[i2], item) < 0) {
                    i2++;
            }
            
            // if possible, return the position of end of the previous subarray
            // instead of the beginning of next subarray
            if(i1 > 0 && i2 == 0 && comp.compare(l2Array.items[i2], item) > 0) {
                i1--;
                i2 = ((L2Array) l1Array[i1]).numUsed;
            }  
        }
        return new ListLoc(i1, i2);
    }
   
    /**
     * find location after the last matching entry. if no match is found, finds
     * the next suitable position to add a new entry.
     * this might be an unused slot at the end of a level 2 array
     *
     * @author joelkalala
     * I  Initialized indices for Level 1 (L1) and Level 2 (L2) arrays
     * I added Only proceed if there are items in the data structure
     * I addedLoop through L1 array to find the appropriate L2Array that could contain the item.
     * This is determined by checking if the first item in each L2Array is less
     * than or equal to the target item.
     * Once the loop ends, i1 might have moved one step too far, so adjust it if necessary
     * I Retrieved the determined L2Array from the L1 array
     * I added a second Loop through the determined L2Array to find the right position for the item 
     * or until a larger item is found, or the end of the L2Array is reached.
     *  Return the determined position within the L1 and L2 arrays.
     * @param item the thing we are searching for a place to put.
     * @return the location where this item should go
     * Revised by: wbrown
     */
    public ListLoc findEnd(E item) {
        // part 3
        int i1 = 0;
        int i2 = 0;

        if (size > 0) {
            // Loop to find the right L1Array
            while (i1 < l1Array.length && l1Array[i1] != null && comp.compare(((L2Array) l1Array[i1]).items[0], item) <= 0) {
                    i1++;
            }
            
            // Move back to the correct L1Array
            if (i1 > 0){
                i1--;  
            }

            // loop to find the right position in the L2Array 
            L2Array l2Array = (L2Array) l1Array[i1];
            while ((l2Array != null && i2 < l2Array.numUsed) && (comp.compare(
                    item, l2Array.items[i2]) >= 0)) {
                i2++;
            }
        }
        return new ListLoc(i1, i2);
    }

    /**
     * add object after any other matching values findEnd will give the
     * insertion position
     *
     * @param item the thing we are searching for a place to put.
     * @return
     */

   public boolean add(E item) {
        ListLoc location = findEnd(item);
        L2Array currentL2 = (L2Array) l1Array[location.level1Index];

        //inserts item into the correct location
        System.arraycopy(currentL2.items, location.level2Index, currentL2.items, location.level2Index + 1, currentL2.numUsed - location.level2Index); //Shifts
      currentL2.items[location.level2Index] = item; //actually inserts item
      currentL2.numUsed++;
      size++;

        //Did that make L2 become full?
        if (currentL2.numUsed < currentL2.items.length) { //l2 did not become full 
            return true;
        } else {
            //if it becomes full then split it or double it
            if (currentL2.items.length < l1Array.length) { //Double it
                currentL2.items = Arrays.copyOf(currentL2.items, currentL2.items.length * 2);
            } else { //Split
                L2Array temp = new L2Array(currentL2.items.length); // Create a temp array to hold data
                System.arraycopy(currentL2.items, currentL2.items.length / 2, temp.items, 0, currentL2.items.length / 2);
                Arrays.fill(currentL2.items, currentL2.numUsed / 2, currentL2.numUsed, null);
                currentL2.numUsed = currentL2.items.length / 2; // Update numUsed
                temp.numUsed = currentL2.items.length / 2;
                System.arraycopy(l1Array, location.level1Index + 1, l1Array, location.level1Index + 2, l1NumUsed - (location.level1Index + 1));
                l1Array[location.level1Index + 1] = temp;
                l1NumUsed++;

                // If l1 became full, double its size
                if (l1Array.length == l1NumUsed) {
                     l1Array = Arrays.copyOf(l1Array, l1Array.length * 2);
                }
            }
        }

        return true;
    }
    
    /**
     * check if list contains a match
     *
     * @param item the thing we are looking for.
     * @return true if the item is already in the data structure
     */
    public boolean contains(E item) {
        // TO DO in part 5 and NOT BEFORE

        return false;
    }

    /**
     * copy the contents of the RaggedArrayList into the given array
     *
     * @param a - an array of the actual type and of the correct size
     * @return the filled in array
     */
    public E[] toArray(E[] a) {
        // TO DO in part 5 and NOT BEFORE

        return a;
    }

    /**
     * returns a new independent RaggedArrayList whose elements range from
     * fromElemnt, inclusive, to toElement, exclusive. The original list is
     * unaffected findStart and findEnd will be useful here
     *
     * @param fromElement the starting element
     * @param toElement the element after the last element we actually want
     * @return the sublist
     */
    public RaggedArrayList<E> subList(E fromElement, E toElement) {
        // TO DO in part 5 and NOT BEFORE

        RaggedArrayList<E> result = new RaggedArrayList<E>(comp);
        return result;
    }

    /**
     * returns an iterator for this list this method just creates an instance of
     * the inner Itr() class (DONE)
     *
     * @return an iterator
     */
    public Iterator<E> iterator() {
        return new Itr();
    }

    /**
     * Iterator is just a list loc. It starts at (0,0) and finishes with index2
     * 1 past the last item in the last block
     */
    private class Itr implements Iterator<E> {

        private ListLoc loc;

        /*
         * create iterator at start of list
         * (DONE)
         */
        Itr() {
            loc = new ListLoc(0, 0);
        }

        /**
         * check to see if there are more items
         */
        public boolean hasNext() {
            // TO DO in part 5 and NOT BEFORE

            return false;
        }

        /**
         * return item and move to next throws NoSuchElementException if off end
         * of list. An exception is thrown here because calling next() without
         * calling hasNext() shows a certain level or stupidity on the part of
         * the programmer, so it can blow up. They deserve it.
         */
        public E next() {
            // TO DO in part 5 and NOT BEFORE

            throw new IndexOutOfBoundsException();
        }

        /**
         * Remove is not implemented. Just use this code. (DONE)
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}