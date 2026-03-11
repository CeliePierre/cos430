package primitivelinkelist;

/**
 *
 * @author kalalajoel
 */
public class Linkedlist {

    private Node head = null;
    private int size = 0;

    public boolean add(int e) {
        // declare new node name item
        Node item = new Node(e); // this is what is going in the node e

        if (head == null) {
            head = item;
        } else {
            Node current = head; // now we are pointing to the head
            while (current.next != null) { // the head is not empty
                current = current.next;

            }
            current.next = item;

        }

        size++;

        return true;

    }

    public void add(int index, int e) {
        Node item = new Node(e);
        if (index >= 0 && index < size) {
            if (head == null) {
                head = item;
            } else if (index == 0) {
                item.next = head;
                head = item;
            } else {
                Node current = head;
                Node prev = head;
                for (int i = 0; i < index; i++) { // As long as the current is not empty and the i
                    prev = current;
                    current = current.next; // we go forward
                }
                item.next = current;
                prev.next = item;
            }

            size++;
        }
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public int indexOf(int e) {
        int i = 0;
        Node current = head;

        while (i < size && current.data != e) {
            i++;
            current = current.next; // move our pointer
        }

        if (i == size) {
            i = -1;
        }

        return i;

    }

    public boolean contains(int e) {
        int index = indexOf(e);
        if (index == -1) {
            return false;
        } else {
            return true;
        }
    }

    public int get(int index) {
        int e = -1;
        if (index < size) {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;

            }
            e = current.data;

        }

        return e;
    }

    public int remove(int index) {
        int e = -1;

        if (index > 0 && index < size) {
            Node current = head;
            Node prev = head;
            for (int i = 0; i < index; i++) {
                prev = current;
                current = current.next;

            }

            e = current.data;

            prev.next = current.next;

            size--;

        }
        return e;
    }

    public int remove(Integer o) {
        int e = -1;
        int index = indexOf(o);

        if (index != -1) {
            e = remove(index);
        }

        return e;

    }

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
