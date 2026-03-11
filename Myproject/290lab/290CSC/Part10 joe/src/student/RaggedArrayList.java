/**
 * File: RaggedArrayList.java
 * ****************************************************************************
 *                           Revision History
 * ****************************************************************************
 * 10/23/2023 - Courtney Jackson - added stats method
 * 10/22/2023 - James Tedder - Updated toArray
 * 10/17/2023 - Courtney Jackson added hasNext() and next()
 * 10/17/2023 - James Tedder - added toArray
 * 10/11/2023 - Courtney Jackson finished contains method
 * 09/30/2023 - Courtney Jackson finished add method
 * 09/29/2023 - Courtney Jackson worked on first half of getting add method to
 * work
 * 09/28/2023 - Courtney Jackson started add method
 * 9/25/23 - Sam G - finished l1arrap loop, finished findFront method,
 * added javadoc
 * 9/24/23 - Sam G -  worked on l1array loop, finished l2array loop
 * 09/24/2023 Courtney Jackson finished findEnd method
 * 9/23/23 - Sam G -  started findFront method
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
         *
         * @James Tedder
         */
        public void moveToNext() {
            L2Array tempArray = ((L2Array) l1Array[level1Index]);
            if (level2Index >= tempArray.numUsed - 1 && level1Index < l1NumUsed - 1) {
                level1Index++;
                level2Index = 0;
            } else {
                level2Index++;
            }
        }
    }

    /**
     * find 1st matching entry
     *
     * @author Sam Gatchell
     * @param item the thing we are searching for a place to put.
     * @return ListLoc of 1st matching item or of 1st item greater than the item
     * if no match this might be an unused slot at the end of a level 2 array
     */
    public ListLoc findFront(E item) {
        int endIndex = 0;
        int i1 = 0;
        int i2 = 0;
        L2Array l2Array = null;
        //checks to make sure l1array isn't empty
        if (l1NumUsed - 1 > 0) {
            l2Array = (L2Array) l1Array[i1];
            endIndex = l2Array.numUsed - 1;
            //finds correct l2array within l1array
            while (comp.compare(l2Array.items[endIndex], item)
                    < 0 && i1 < l1NumUsed - 1) {
                i1++;
                l2Array = (L2Array) l1Array[i1];
                endIndex = l2Array.numUsed - 1;
            }
            //finds correct index in l2array
            while (comp.compare(l2Array.items[i2], item)
                    < 0 && i2 < l2Array.numUsed - 1) {
                i2++;
            }
            if (comp.compare(l2Array.items[i2], item) < 0) {
                i2++;
            }
        }
        return new ListLoc(i1, i2);
    }

    /**
     * find location after the last matching entry or if no match, it finds the
     * index of the next larger item this is the position to add a new entry
     * this might be an unused slot at the end of a level 2 array
     *
     * @author Courtney Jackson
     * @param item the thing we are searching for a place to put.
     * @return the location where this item should go
     */
    public ListLoc findEnd(E item) {
        int i1 = 0;
        int i2 = 0;
        //checks to make sure l1array isn't empty

        if (size > 0) {
            L2Array l2Array = (L2Array) l1Array[i1];
            while (l2Array != null && comp.compare(item, l2Array.items[0]) >= 0) {
                i1++; // this could get us to NumUsed-1 but not above
                l2Array = (L2Array) l1Array[i1];
            }  // found the right L2Array
            i1--;
            if (i1 < 0) {
                i1 = 0;
            }
            l2Array = (L2Array) l1Array[i1];
            while (l2Array.items[i2] != null && comp.compare(l2Array.items[i2], item) <= 0) {
                i2++;
            }
        }
        return new ListLoc(i1, i2);
    }

    /**
     * add object after any other matching values findEnd will give the
     * insertion position
     *
     * @author Courtney Jackson and Joel Kalala
     * @param item the thing we are searching for a place to put.
     * @return
     */
    public boolean add(E item) {
        ListLoc location = findEnd(item);
        L2Array currentL2 = (L2Array) l1Array[location.level1Index];

        //inserts item into the correct location
        System.arraycopy(currentL2.items, location.level2Index, currentL2.items, location.level2Index + 1, currentL2.numUsed
                - location.level2Index); //Shifts
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
     * @author Courtney Jackson
     * @param item the thing we are looking for.
     * @return true if the item is already in the data structure
     */
    public boolean contains(E item) {
        ListLoc location = findEnd(item);
        L2Array l2Array = (L2Array) l1Array[location.level1Index];
        if (comp.compare(item, l2Array.items[0]) >= 0) {
            return true;
        }
        return false;
    }

    /**
     * copy the contents of the RaggedArrayList into the given array
     *
     * @param a - an array of the actual type and of the correct size
     * @return the filled in array
     * @author James Tedder
     */
    public E[] toArray(E[] a) {
        if (size <= a.length) {
            int currPos = 0;
            Iterator<E> itr = iterator();
            while (itr.hasNext()) {
                a[currPos] = itr.next();
                currPos++;
            }
        }
        return a;
    }

    /**
     * returns a new independent RaggedArrayList whose elements range from
     * fromElemnt, inclusive, to toElement, exclusive. The original list is
     * unaffected findStart and findEnd will be useful here
     *
     * @author James Tedder
     * @param fromElement the starting element
     * @param toElement the element after the last element we actually want
     * @return the sublist
     */
    public RaggedArrayList<E> subList(E fromElement, E toElement) {
        ListLoc frontLoc = findFront(fromElement);
        ListLoc endLoc = findFront(toElement);
        RaggedArrayList<E> result = new RaggedArrayList<E>(comp);
        while (!frontLoc.equals(endLoc)) {
            result.add(((L2Array) l1Array[frontLoc.level1Index]).items[frontLoc.level2Index]);
            frontLoc.moveToNext();
        }

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
         *
         * @author Courtney Jackson
         */
        public boolean hasNext() {
            L2Array l2Array = (L2Array) l1Array[loc.level1Index];
            return loc.level2Index < l2Array.numUsed;
        }

        /**
         * return item and move to next throws NoSuchElementException if off end
         * of list. An exception is thrown here because calling next() without
         * calling hasNext() shows a certain level or stupidity on the part of
         * the programmer, so it can blow up. They deserve it.
         *
         * @author Courtney Jackson
         */
        public E next() {
            L2Array l2Array = (L2Array) l1Array[loc.level1Index];
            if (loc.level2Index >= l2Array.numUsed) {
                throw new IndexOutOfBoundsException();
            }
            E value = l2Array.items[loc.level2Index];
            loc.moveToNext();
            return value;
        }

        /**
         * Remove is not implemented. Just use this code. (DONE)
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
     /**
     * Written by Bob Booth as part of the testing harness for 
     * Project Part 5, this stats method allows any RaggedArrayList
     * object to print its own statistics.  You must remember to 
     * reset the Comparator object's count field before creating 
     * a ragged array list.
     * Modified to be a RAL method by Anne Applin
     */
    public  void stats() {
        System.out.println("STATS:");
        int size = this.size();
        System.out.println("list size N = " + size);

        // level 1 array
        int l1NumUsed = this.l1NumUsed;
        System.out.println("level 1 array " + l1NumUsed + " of "
                + this.l1Array.length + " used.");

        // level 2 arrays
        int minL2size = Integer.MAX_VALUE, maxL2size = 0;
        for (int i1 = 0; i1 < this.l1NumUsed; i1++) {
            RaggedArrayList<Song>.L2Array l2array
                    = (RaggedArrayList<Song>.L2Array)
                    (this.l1Array[i1]);
            minL2size = Math.min(minL2size, l2array.numUsed);
            maxL2size = Math.max(maxL2size, l2array.numUsed);
        }
        System.out.printf("level 2 array sizes: min = %d used, avg = %.1f "
                + "used, max = %d used.%n%n",
                minL2size, 
                (double) size / l1NumUsed, maxL2size);
    }
}
