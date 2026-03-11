/**
 * File: SearchByTitlePrefix.java
 *****************************************************************************
 *                       Revision History
 *****************************************************************************
 * 9.2023 -Joel Kalala i created SearchByArtistPrefix
 * - I Created a dummy Song object with the artist prefix for binary search
 * - I used a combination of binary search and linear search
 * - I Calculated the total number of comparisons
 * - I Calculated k, which is the number of songs with the artist prefix
 * - Calculate log base 2 of the total number of songs
 * - I added the  test method for this unit
 *
 * 9.2023 -  @author Yusra Mugorewase
 * - I Compare two Song objects based on their artist's name .
 * -I Compare the artist names of the two songs
 * -Created an object to count comparisons and reset its counter
 *
 * 8/2015 Anne Applin - Added formatting and JavaDoc
 * 2015 - Bob Boothe - starting code
 *****************************************************************************
 *
 */
package student;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/**
 * Search by Artist Prefix searches the artists in the song database for artists
 * that begin with the input String
 *
 * @author Bob Booth
 */
/**
 * raList: This is an instance of the RaggedArrayList class that will store the
 * songs. titleComparator: This is a comparator used to compare Song objects
 * based on their titles. totalComparisonsForFullRAList: This stores the total
 * number of comparisons made when constructing the RaggedArrayList.
 */
public class SearchByTitlePrefix {

    private RaggedArrayList ra;
    private Comparator<Song> cmp = new Song.CmpTitle();
    // private int totalComparisonsForFullRAList;
    // private Comparator<Song> titleComparator = new Song.CmpTitle();

    //private int totalComparisonsForFullRAList;
    public SearchByTitlePrefix(SongCollection sc) {
        Song[] songs = sc.getAllSongs();
        ((CmpCnt) cmp).resetCmpCnt();
        ra = new RaggedArrayList(cmp);
        for (Song song : songs) {
            ra.add(song);
            //dump(ra);
        }
        System.out.println("comparison to build the Full RaggedArrayList = "
                + ((CmpCnt) cmp).getCmpCnt());

        ra.stats();

    }

    /**
     * When an instance of SearchByTitlePrefix is created, it takes a
     * SongCollection object as an argument. This collection is then used to
     * populate the RaggedArrayList. The total comparisons made during the
     * addition are stored.
     */
    /**
     *
     * titlePrefix = titlePrefix.toLowerCase(); Song first = new Song("dummy",
     * titlePrefix, "dummy"); System.out.println("The song with the prefix: " +
     * first);
     *
     * //if(cmp instanceof CmpCnt) { ((CmpCnt) cmp).resetCmpCnt(); // Correct
     * the case and ensure your cmp implements CmpCnt int tpLength =
     * titlePrefix.length();
     *
     * char ch = titlePrefix.charAt(tpLength - 1); ch = (char) (ch + 1); String
     * endString = titlePrefix.substring(0, tpLength - 1) + ch; Song last = new
     * Song("dummy", endString, "dummy"); System.out.println("The song for the
     * end of the subList: " + last);
     *
     * RaggedArrayList<Song> res = ra.subList(first, last); res.stats();
     * System.out.println("Comparisons to build the sublist: " + ((CmpCnt)
     * cmp).getCmpCnt());
     *
     * Object[] result = new Song[res.size]; result = res.toArray(result);
     * return (Song[]) result;
     *
     * }
     */
   public Song[] search(String titlePrefix) {
    // Reset the comparison count
    ((CmpCnt) cmp).resetCmpCnt();  // Assuming your Comparator implements CmpCnt interface with resetCmpCnt method.

    // Convert prefix to lowercase
    titlePrefix = titlePrefix.toLowerCase();

    // Calculate the next prefix to create a range for the search
    String nextPrefix = titlePrefix.substring(0, titlePrefix.length() - 1)
            + (char) (titlePrefix.charAt(titlePrefix.length() - 1) + 1);

    // Create the 'from' dummy song object
    Song fromDummy = new Song("dummy", titlePrefix, "dummy");

    // Create the 'to' dummy song object
    Song toDummy = new Song("dummy", nextPrefix, "dummy");

    // Get the sublist of songs that match the given title prefix
    RaggedArrayList<Song> resultRAList = ra.subList(fromDummy, toDummy);

    // Call the stats on the resulting sublist
    resultRAList.stats();

    // Print out the value of cmpcnt
    
    System.out.println("comparison to build the sublist = "+
                   ((CmpCnt)cmp).getCmpCnt());

    // Create and return the array from the sublist
    return resultRAList.toArray(new Song[resultRAList.size()]);
}
    


    /**
     * Create an instance of SongCollection using a given song file (this is
     * assumed to have already been implemented elsewhere and will be loaded via
     * command-line arguments). Create an instance of SearchByTitlePrefix using
     * the SongCollection instance. Use the search method on the
     * SearchByTitlePrefix instance to perform the
     */
    public static void main(String[] args){
        if (args.length == 0) {
            System.err.println("usage: prog songfile [search string]");
            return;
        }
        SongCollection sc = new SongCollection(args[0]);
        SearchByTitlePrefix sbtp = new SearchByTitlePrefix(sc);
        
        if (args.length > 1) {
            System.out.println("searching for: " + args[1]);
            Song[] byTitleResult = sbtp.search(args[1]);
            if (byTitleResult == null){
                System.out.println("no results");
            }
            else{
                Stream.of(byTitleResult)
                .limit(10)
                .forEach(System.out::println);
            }
        
        }
    }
}