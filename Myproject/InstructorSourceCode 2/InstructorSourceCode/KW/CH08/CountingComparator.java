package KW.CH08;

import java.util.Comparator;

public class CountingComparator<T extends Comparable<T>> 
     implements Comparator<T> {

    private int count;

    public void clear() {
        count = 0;
    }

    public int getCount() {
        return count;
    }

    @Override
    public int compare(T left, T right) {
        ++count;
        return left.compareTo(right);
    }
}
