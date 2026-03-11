/*
  * tests the data structure that we created
 */
package arraylisttester;

import java.util.Arrays;

/**
 *
 * @author aapplin
 */
public class ArrayListTester {

        /**
     * Tester for our  ArrayList
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // test default constructor
        ArrayList<Integer> a = new ArrayList<>();
        System.out.println("List A: \n" + a);
        // test parameterized constructor
        ArrayList<Integer> b = new ArrayList<>(20);
        // fill the list with some values
        for (int i = 0; i < 15; i ++){
            b.add(i);
        }
        System.out.println("List B: \n" + b);
        System.out.println("List contains 5: " + b.contains(5));
        b.set(6, 89);
        System.out.println("List B: \n" + b);
        System.out.println("Index of element 89: " + b.indexOf(89));
        System.out.println("value of the element at index 6:  " + b.get(6));
        int j = (int) b.remove(6);
        System.out.println("List B: after remove element 6 \n" + b);
       
        ArrayList<Date> dates = new ArrayList<>();
        dates.add(new Date(3, 23, 2019));
        dates.add(new Date(2, 27, 2018));
        dates.add(new Date(2, 6, 1957));
        System.out.println(dates);
        Date[] dateArray = new Date[dates.size()];
        dateArray = dates.toArray(dateArray);
        Arrays.sort(dateArray);
        for (Date d: dateArray)
            System.out.println(d);
       
    }
   
}