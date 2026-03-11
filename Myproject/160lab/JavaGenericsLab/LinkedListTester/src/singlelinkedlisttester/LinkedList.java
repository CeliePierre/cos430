/*
 *              Revision History
 * ***************************************************************
 * Written to demonstrate writing a data structure
 */
package singlelinkedlisttester;
/**
 * A data structure that uses addresses to "point" to the next element
 * in the list.
 * @author aapplin
 */
public class LinkedList <E extends Comparable<? super E>> {

    /**
     * An external pointer (reference) to the list.
     */
    private Node head;
    /**
     * The number of elements in the list.
     */
    private int size;

    /**
     * Constructor for the linked list. Initializes head to null and size to 0.
     */
    public LinkedList() {
        head = null;
        size = 0;
    }

    /**
     * Method to add to the end of the list.
     *
     * @param e the item to be added.
     * @return always returns true since there is no length limit on a linked
     * list
     */
    public boolean add(E e) {
        Node item = new Node(e);
        if (head == null) {
            head = item;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = item;
        }
        size++;
        return true;
    }

    /**
     * Method to add an element at a specific location in the list.
     *
     *
     *
     * @param index the 0-based index of the element to be added.
     * @param e the element to be added to the list.
     */
    public void add(int index, E e) {
        Node item = new Node(e);
        if (index >= 0 && index < size) {
            if (head == null) { // nothing in the list
                head = item;
            } else if (index == 0) { // add to the beginning
                item.next = head;
                head = item;
            } else { // add somewhere else
                Node current = head;
                Node previous = head;
                for (int i = 0; i < index; i++) {
                    previous = current;
                    current = current.next;
                }
                item.next = current;
                previous.next = item;
            }
            size++;
        } else {
            System.out.println("Index out of bounds");
        }
    }

    /**
     * A method to clear the current list. Head is set to null and size is set
     * to 0.
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * This method uses indexOf() to see if an element is contained in the list.
     *
     * @param e the element to be searched for
     * @return true if the element is found and false otherwise.
     */
    public boolean contains(E e) {
        int index = indexOf(e);
        if (index == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * A method that returns the value in the data element of the node at the
     * 0-based index specified.
     *
     * @param index the 0-based index of the element to return.
     * @return the element at the index location or null if the index is
     * invalid.
     */
    public E get(int index) {
        E e = null;
        if (index < size) {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            e = (E)current.data;
        }
        return e;
    }

    /**
     * This method does a linear search of the linked list to find the specified
     * element s position in the list.
     *
     * @param o the element to search for.
     * @return the 0-based index of the element or -1 if not found.
     */
    public int indexOf(E o) {
        int index = 0;
        Node current = head;
        while (index < size && o.compareTo((E)current.data) != 0) {
            index++;
            current = current.next;
        }
        if (index == size) {
            index = -1;
        }
        return index;
    }

    /**
     * This method removes the element at the specified location if that
     * location is valid.
     *
     * @param index the 0-based index of the element to be removed from the
     * linked list.
     * @return the element that was removed or -1 if the index was invalid.
     */
    public E remove(int index) {
        E e = null;
        if (index < size) {
            if (index == 0) {
                e = (E)head.data;
                head = head.next;
            } else {
                Node current = head;
                Node previous = head;
                for (int i = 0; i < index; i++) {
                    previous = current;
                    current = current.next;
                }
                e = (E)current.data;
                previous.next = current.next;
            }
            size--;
        }
        return e;
    }

    /**
     * This method removes the specified element from the list if it is in the
     * list.
     *
     * @param o the element to be removed. Declared as an object of the wrapper
     * class to differentiate this one from the one that finds an index and
     * removes that node.
     * @return the element that was removed or null if the element is not in the
     * list.
     */
    public E remove(E o) {
        E e = null;
        int i = indexOf(o); // find the element
        if (i != -1) {
            e = remove(i); // remove(i) decrements size
        }
        return e;
    }

    /**
     * This method overwrites the value at the location specified by the 0-based
     * index if it is valid.
     *
     * @param index the 0-based location of the node to receive new data.
     * @param e the new data element that will replace the current one.
     * @return the value of the element that was overwritten or null if the
     * index was invalid.
     */
    public E set(int index, E e) {
        E item = null;
        if (index >= 0 && index < size) {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            item = (E)current.data;
            current.data = e;
        }
        return item;
    }

    /**
     * The count of the elements in the linked list.
     *
     * @return the current number of elements in the list.
     */
    public int size() {
        return size;
    }

    /**
     * Method that places the elements in the linked list into an array of int
     * and returns that.
     *
     * @return an array of elements or null if the list is empty.
     */
    public Object[] toArray() {
        Object[] array = null;
        if (size > 0) {
            array = new Object[size];
            Node current = head;
            int index = 0;
            while (current != null) {
                array[index] = current.data;
                index++;
                current = current.next;
            }
        }
        return array;
    }

    /**
     * returns a formatted string in the form: [x, x, x]
     *
     * @return a formatted string representing the elements in the list. NOTE:
     * For more complicated objects this format will be really bad.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        Node current = head;
        while (current.next != null) {
            str.append(current.data).append(", ");
            current = current.next;
        }
        str.append(current.data).append("]");
        return str.toString();
    }
}