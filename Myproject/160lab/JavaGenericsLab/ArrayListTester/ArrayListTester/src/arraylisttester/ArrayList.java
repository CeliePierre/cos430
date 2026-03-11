/** *********************************************************************
 * Revision History
 ***********************************************************************
 * 2018 - aa -  rewritten with all of the necessary documentation
 * written in class every semester since 2012
 ********************************************************************* */
package arraylisttester;

import java.util.Arrays;

/**
 * A class that mimics the Java ArrayList. To demonstrate how the ArrayList in
 * Java is actually written (or at least how it might be) to show that you can
 * write your own data structure if you know what you want it to do and how to
 * store the data.
 *
 * @author aapplin
 */
public class ArrayList <E extends Comparable<? super E>>{
    /**
     * the length of the underlying array;
     */
    private int capacity;
    /**
     * the data will be stored in a static array
     */
    private Object[] array;
    /**
     * the size is the actual number of elements in the array
     */
    private int size;

    /**
     * Constructs an empty list with an initial capacity of ten
     */
    public ArrayList() {
        this(10);
    }

    /**
     *  Constructs an empty list with the specified initial capacity.
     *
     * @param capacity the initial capacity of the arrayList
     */
    public ArrayList(int capacity) {
        this.capacity = capacity;
        array = new Object[capacity];
        size = 0;
    }
    /**
     * to make this more like the Java ArrayList we need this method.
     * @return the size of the list.
     */
    public int size(){
        return size;
    }
    /*
     * private validIndex returns true if the index is valid
     * and false otherwise
     */
    private boolean isValidIndex(int index) {
        return (index >= 0 && index < size);
    }

    /**
     * Appends the specified element to the end of the list
     *
     * @param element an element to be added
     * @return true
     */
    public boolean add(E element) {
        if (size == array.length) {
            resize();
        }
        array[size] = element;
        size++;

        return true;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     *
     * @param index the position in the list where the element should be added
     * @param element the element to be added
     * @return true if the element is added and false otherwise.
     */
    public boolean add(int index, E element) {
        if (!isValidIndex(index)) {
            return false;
        }
        if (size == array.length) {
            resize();
        }
        // move everything up one space
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = element;
        size++;
        return true;
    }

    /*  
     * private resize -- doubles the size of the array
     */
    private void resize() {
        array = Arrays.copyOf(array, array.length * 2);
        capacity = array.length;

    }

    /**
     * Removes all of the elements from this list.
     */
    public void clear() {
        array = new Object[10];
        capacity = array.length;
    }

    /**
     * Returns true if this list contains the specified element.
     *
     * @param element element whose presence in this list is to be tested
     * @return true this list contains the specified element
     */
    public boolean contains(E element) {
        int i = 0;
        while (i < size && element.compareTo((E)array[i]) != 0) {
            i++;
        }
        if (i == size) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index the index of the element to return
     * @return the  element at the specified position or -1
     */
    public E get(int index) {
        if (isValidIndex(index)) {
            return (E)array[index];
        } else {
            return null;
        }

    }

    /**
     * Finds the element specified and returns the index if found or -1 if not
     * found. performs a standard linear search if the array contains the
     * element.
     *
     * @param element the element to search for
     * @return the index of the element or -1 if the element is in in the list
     */
    public int indexOf(E element) {
        int index = 0;
        if (contains(element)) {
            while (index < size && element.compareTo((E)array[index]) != 0) {
                index++;
            }
        } else {
            index = -1;
        }
        return index;
    }

    /**
     * Checks to see if there is anything in the list
     *
     * @return true if size is 0 and false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * if the index is valid it saves the element at that location and then
     * moves all of the elements "down" on in the array and reduces the list
     * size by one.
     *
     * @param index the index of the element to be removed
     * @return the value of the element at the specified index.
     */
    public E remove(int index) {
        if (isValidIndex(index)) {
            E element = (E)array[index];
            for (int i = index; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
            size--;
            return element;
        } else {
            return null; // we are done
        }

    }

    /**
     * If the index is valid the current element is replaced with the value of
     * the parameter.
     *
     * @param index the element location to be changed.
     * @param element the new value of the element at the specified location
     * @return the value of the element that was replaced or null if the index is
     * invalid
     */
    public E set(int index, E element) {
        if (isValidIndex(index)) {
            E replaced = (E)array[index];
            array[index] = element;
            return replaced;
        } else {
            return null;
        }

    }
    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence(from first to last)
     * @return an array of type E elements
     */
    public  E[] toArray(E[] a){
        if (a.length < size){
            return null;
        }else{
            for (int i = 0; i < size; i++){
                a[i] = (E)array[i];
            }
            return a;
        }
       
    }
    /**
     * Trims the capacity of the ArrayLIst instance to be the
     * list's current size
     */
    public void trimToSize(){
        Object[] copy = new Object[size];
        copy = Arrays.copyOf(array, size);
        array = copy;
        capacity = array.length;
    }
    /**
     * Prints the list 20 items per line if there are elements in the list.
     * Prints empty list otherwise.
     *
     * @return a string representing the contents of the list.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        if (isEmpty()) {
            str.append("[]");
        } else {
            str.append("[");
            for (int i = 0; i < size - 1; i++) {
                str.append((E)array[i].toString()).append(", ");
                if (i > 0 && i % 20 == 0) {
                    str.append(System.lineSeparator());
                }
            }
            str.append(array[size - 1]);
            str.append("]");
        }
        return str.toString();
    }
}