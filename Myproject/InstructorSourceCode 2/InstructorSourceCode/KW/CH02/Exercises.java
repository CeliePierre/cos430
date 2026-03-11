package KW.CH02;

import java.util.ArrayList;

/**
 * Class to contain static method exercise solutions
 */
public class Exercises {

    /*<exercise chapter="2" section="1" type="programming" number="1">*/
    /**
     * Method to replace each occurence of oldItem with newItem
     * in an ArrayList<String>
     * @param aList The arraylist in which items are to be replaced
     * @param oldItem The item to be replaced
     * @param newItem The item to rplace oldItem
     * @post All occurences of oldItem have been replaced with newItem
     */
    public static void replace(ArrayList<String> aList,
            String oldItem,
            String newItem) {
        int index = aList.indexOf(oldItem);
        while (index != -1) {
            aList.set(index, newItem);
            index = aList.indexOf(oldItem);
        }
    }
    /*</exercise>*/

    /*<exercise chapter="2" section="1" type="programming" number="2">*/
    /**
     * Method to delete the first occurence of target from an
     * ArrayList<String>
     * @param aList Thie array list to remove target from
     * @param target The object to be removed
     * @post First occurence of target is no longer in aList
     */
    public static void delete(ArrayList<String> aList, String target) {
        int index = aList.indexOf(target);
        if (index != -1) {
            aList.remove(index);
        }
    }
    // Note this could also be written as
    // public static delete(ArrayList<String> aList, String target) {
    //     aList.remove(target);
    // }
    /*</exercise>*/
}
