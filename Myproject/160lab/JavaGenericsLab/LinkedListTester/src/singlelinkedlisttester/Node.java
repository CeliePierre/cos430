/*
 *              Revision History
 * ***************************************************************
 * Written to demonstrate writing a data structure
 */
package singlelinkedlisttester;

/**
 * a single linked Node for a linked List application
 * @author aapplin
 *
 */
public class Node <E extends Comparable<? super E>>{
    protected E data;
    protected Node next;
    /**
     * Constructor takes an int and creates a node.
     * @param e an int
     */
    public Node(E e){
        data = e;
        next = null;
    }
    /**
     * toString method for Node
     * @return the formatted String
     */
    @Override
    public String toString(){
        return String.format("%d", data);
    }
}