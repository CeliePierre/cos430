/**
 * File: SearchByArtistPrefix.java
 *****************************************************************************
 *                       Revision History
 *****************************************************************************
 * 09.17.2023 Courtney Jackson and Nathaniel Kinney added code to keep track of 
 *  complexity of search method
 * 09.14.2023 Courtney Jackson and Nathaniel Kinney added code to count the 
 *  number of comparisons
 * 09.13.2023 Courtney Jackson and Nathaniel Kinney wrote search method
 * 8/2015 Anne Applin - Added formatting and JavaDoc
 * 2015 - Bob Boothe - starting code
 *****************************************************************************
 *
 */
package student;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;
import static java.lang.Math.log;

/**
 * Search by Artist Prefix searches the artists in the song database for artists
 * that begin with the input String
 *
 * @author Bob Booth, Courtney Jackson, and Nathaniel Kinney
 */

public class SearchByArtistPrefix {

    // keep a local direct reference to the song array
    private Song[] songs;

    /**
     * constructor initializes the property. [Done]
     * @author Courtney Jackson and Nathaniel Kinney
     * @param sc a SongCollection object
     */
    public SearchByArtistPrefix(SongCollection sc) {
        songs = sc.getAllSongs();
    }

    /**
     * find all songs matching artist prefix uses binary search should operate
     * in time log n + k (# matches) converts artistPrefix to lowercase and
     * creates a Song object with artist prefix as the artist in order to have a
     * Song to compare. walks back to find the first "beginsWith" match, then
     * walks forward adding to the arrayList until it finds the last match.
     *
     * @author Courtney Jackson and Nathaniel Kinney
     * @param artistPrefix all or part of the artist's name
     * @return an array of songs by artists with substrings that match the
     * prefix
     */
    public Song[] search(String artistPrefix) {
        Song[] result = null;
        artistPrefix = artistPrefix.toLowerCase();
        Song key = new Song(artistPrefix, "n/a", "n/a");
        Comparator<Song> cmp = new Song.CmpArtist();
        ((CmpCnt)cmp).resetCmpCnt();
        int partLength = artistPrefix.length();
        int i = Arrays.binarySearch(songs, key, cmp);
        System.out.println("index from binary search is: " + i);
        System.out.println("Binary search comparisons: " + ((CmpCnt)cmp).getCmpCnt());
        ((CmpCnt)cmp).resetCmpCnt();
        if (i < 0) {
            i = -i - 1;
        }
        System.out.println("Front found at " + i);

        ArrayList<Song> list = new ArrayList<>();
        int count = 0;
        if (i >= 0) {
            while (i >= 0
                    && songs[i].getArtist().length() >= partLength
                    && songs[i].getArtist().substring(0, partLength).
                            compareToIgnoreCase(artistPrefix) == 0) {
                i--;
                count++;
            }

            i++;

            while (i < songs.length
                    && songs[i].getArtist().length() >= partLength
                    && songs[i].getArtist().substring(0, partLength).
                            compareToIgnoreCase(artistPrefix) == 0) {
                list.add(songs[i]);
                i++;
                count++;
            }
            System.out.println("Comparisons to build the list: " + count);
            System.out.println("Actual complexity is: " + (count + 
                ((CmpCnt)cmp).getCmpCnt()));
            result = new Song[list.size()];
            System.out.println("\nk is " + list.size());
            System.out.println("log_2(n) = " + (int)(log(songs.length) / log(2)));
            System.out.println("Theoretical complexity k+log n is: " + (result.length + 
                (int)(log(songs.length) / log(2))));
            result = list.toArray(result);

        }
        return result;
    }

    /**
     * testing method for this unit
     * @author Courtney Jackson and Nathaniel Kinney
     * @param args command line arguments set in Project Properties - the first
     * argument is the data file name and the second is the partial artist name,
     * e.g. be which should return beatles, beach boys, bee gees, etc.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("usage: prog songfile [search string]");
            return;
        }

        SongCollection sc = new SongCollection(args[0]);
        SearchByArtistPrefix sbap = new SearchByArtistPrefix(sc);

        if (args.length > 1) {
            System.out.println("searching for: " + args[1]);
            Song[] byArtistResult = sbap.search(args[1]);

            System.out.println("Total Number of Matches: " + byArtistResult.length);
            Stream.of(byArtistResult).limit(10).forEach(System.out::println);
        }

    }
}
