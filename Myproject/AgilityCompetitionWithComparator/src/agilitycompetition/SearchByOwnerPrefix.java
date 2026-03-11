/************************************************************************
 * Revision History
 ************************************************************************
 * 2015: AA - to illustrate the use of the Comparator for the project.
 ************************************************************************/
package agilitycompetition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * A class to find all owners whose names start with the prefix string.
 * Must check to made sure that the owner name we are checking is not shorter
 * than the prefix string.
 * @author aapplin
 */
public class SearchByOwnerPrefix {

    private Competitor[] array; // declared, not created - local copy of 
                                // of the array.

    public SearchByOwnerPrefix(Competitor[] ccIn) {
        array = ccIn; // ccIn is an array sorted by owner
    }

    public Competitor[] search(String ownerPrefix) {
        // the thing we will return
        Competitor[] result = null;
        // we want to do this search case independent
        ownerPrefix = ownerPrefix.toLowerCase();
        // now create a Competitor with that as the owner name
        // and default values for everything else in the object.
        Competitor key = new Competitor(new Date(), "none", 0.0, "none",
                ownerPrefix, new ElapsedTime());
        // print the competitor to make sure we created it right for debugging
        // printing to the console gives you data!
        System.out.println(key);
        // create an object of Comparator
        Comparator<Competitor> cmp = new Competitor.CmpOwner();
        ((CmpCnt)cmp).resetCmpCnt(); // zero the count.
        int partLength = ownerPrefix.length();
        // call the Arrays.binarySearch with the array, key, the Comparator
        int i = Arrays.binarySearch(array, key, cmp);
        System.out.println(((CmpCnt)cmp).getCmpCnt() + " compares for search.");
        System.out.println("The result of the binary search " + i);
        // if it is actually a negative what we have is the index where it would
        // be if it existed. Since we are looking for a piece of a name it 
        // will not find one so it will be negative
        if (i < 0) {
            i = -i - 1;
        }
        System.out.println(i);
        System.out.println(array[i]);
        System.out.println(array[i].getOwner());
        // we don't know how many matches we will find so we will create an 
        // array list and convert it to an array to return it.
        ArrayList<Competitor> list = new ArrayList<>();
        if (i > 0) {// it should be, but it never hurts to be careful
            // find the front - the first partial match working backwards
            // Make sure that the owner name is longer than the substring
            // or it will blow up when we do the comparison
            while (i >= 0 && 
                    array[i].getOwner().length() >= partLength &&
                    array[i].getOwner().substring(0, partLength).
                    compareToIgnoreCase(ownerPrefix) == 0) {
                i--;
            }
            // we WILL go one too far back.
            i++;
            // now fill the list by walking forward as a linear search
            while (i < array.length && 
                    array[i].getOwner().length() >= partLength && 
                    array[i].getOwner().substring(0, partLength).
                    compareToIgnoreCase(ownerPrefix) == 0) {
                list.add(array[i]);
                i++;
            }
            result = new Competitor[list.size()];
            result = list.toArray(result);
            
        } 
        return result; // ONE return statement
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("usage: prog file [search string]");
            return;
        }

        // create a contest object
        AgilityContest contest = new AgilityContest("South Portland",
                "Blue Bison", new Date(5, 12, 2015), args[0]);
        // remember the constructor reads the file and sorts by 
        // elapsed time using the compareTo() in Competitor
        Competitor[] list = contest.getAllContestants();
        // we need the list sorted by owner name instead of elapsedTime
        // so we need to use the compare() in CmpOwner class in Competitor
        // instead. So we create an object of CmpOwner
        Comparator<Competitor> compOwner = new Competitor.CmpOwner();
        // reset the count of comparisons
        ((CmpCnt)compOwner).resetCmpCnt();
        
        Arrays.sort(list, compOwner);// uses the CmpOwner class in Competitor
        
        System.out.println("Comparisons to re-sort " 
                + ((CmpCnt)compOwner).getCmpCnt());
        System.out.printf("local list has %d elements\n", list.length);
        SearchByOwnerPrefix sbop = new SearchByOwnerPrefix(list);

        if (args.length > 1) {
            System.out.println("searching for: " + args[1]);
            Competitor[] byOwnerResult = sbop.search(args[1]);
            if (byOwnerResult == null) {
                System.out.println("no results");
            } else {
                printNItems(byOwnerResult, 10);
            }
        }
    }

    public static void printNItems(Competitor[] list, int n) {
        System.out.printf("Total competitors in this list =  %d, "
                + "first owners and dogs: \n", list.length);
        for (int i = 0; i < n && i < list.length; i++) {
            System.out.printf("%s   \"%s\"\n",
                    list[i].getOwner(), list[i].getName());
        }
    }
}
