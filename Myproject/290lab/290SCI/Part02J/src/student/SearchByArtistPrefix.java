/**
 * File: SearchByArtistPrefix.java
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
public class SearchByArtistPrefix {

    // keep a local direct reference to the song array
    private final Song[] songs;

    /**
     * constructor initializes the property. [Done]
     *
     * @param sc a SongCollection object
     */
    public SearchByArtistPrefix(SongCollection sc) {
        songs = sc.getAllSongs();
        System.out.println("N for this data is " + songs.length);
        System.out.println("10,514 base 10 == 0010 1001 0001 0010 base 2");
        System.out.println(" normalise to get the exponent ");
        System.out.println("1.01000 1000 1001 1X 2^13");
        System.out.println("The exponent is log base 2");
    }

    /**
     * *
     * @search(String artistPrefix
     * @author Yusra Mugorewase I Created an object to count comparisons and
     * reset its counter. Created a dummy Song object to serve as a key for
     * binary search.
     * @author kalalajoel This method is designed to search for songs by the
     * artist's prefix i Created a comparator object to compare Song objects by
     * their artists Reset the comparison count for this search i Created a
     * dummy Song object with the artist prefix for binary search i Performed a
     * binary search to find a potential match i used a combination of binary
     * search and linear search If binarySearch returns a negative value, no
     * exact match is found First, the input string is transformed to lowercase.
     * Binary search is used to quickly find an approximate location of the
     * matching artist If the exact match is not found using binary search, it
     * adjusts to the insertion point Increment the additional comparison count
     * The method then iterates backward to find the starting point of the
     * matching songs Once the starting point is found, it iterates forward to
     * collect all the matches The method calculates the complexity of the
     * search (comparisons used in binary search and the loops Calculate the
     * total number of comparisons (binary search + additional) Calculate k,
     * which is the number of songs with the artist prefix Output statistics
     * Calculate log base 2 of the total number of songs Calculate the
     * theoretical complexity It prints out various statistics and then returns
     * an array of matching Song objects.
     *
     */
    public Song[] search(String artistPrefix) {
                 Song[] result;
        ArrayList<Song> list = new ArrayList<>();
        
        Song songKey = new Song(artistPrefix.toLowerCase(), "dymmy", "dymmy");
        Comparator<Song> cmp = new Song.CmpArtist();

        int compCount;
        int counter = 0;
        int i = Arrays.binarySearch(songs, songKey, cmp);

        // test outputs
        System.out.println("Index from binary search is: " + i);
        System.out.println("Binary search comparisons: " 
                + ((CmpCnt) cmp).getCmpCnt());

        // converts negative number from binary search in a usable index
        if (i < 0) {
            i = -i - 1;
        }
        // test output
        System.out.println("Front found at " + i);

        // goes back to first index
        if (i >= 0) {
            while (i >= 0
                    && songs[i].getArtist().length() >= artistPrefix.length()
                    && songs[i].getArtist().substring(0, artistPrefix.length()).
                            compareToIgnoreCase(artistPrefix) == 0) {

                counter++;
                i--;
            }

            i++;

            // moves through list to get remaining songs
            while (i < songs.length
                    && songs[i].getArtist().length() >= artistPrefix.length()
                    && songs[i].getArtist().substring(0, artistPrefix.length()).
                            compareToIgnoreCase(artistPrefix) == 0) {
                list.add(songs[i]);
                i++;
                counter++;
            }
        }
        // copies arraylist list to result array
        result = new Song[list.size()];
        result = list.toArray(result);

        // total comparions counter
        compCount = counter + ((CmpCnt) cmp).getCmpCnt();
        
        // calculate log_2(n)
        int n = (int) (Math.log(songs.length) / Math.log(2));

        //test outputs
        System.out.println("Comparisons to build the list: " + counter);
        System.out.println("Actual complexity is: "
                + compCount + "\n");
        System.out.println("k is " + list.size());
        System.out.println("log_2(n) = " + n);
        System.out.println("Theorectial complexity k + log(n): "
                + (list.size() + n));
        return result;

    }

    /**
     * testing method for this unit
     *
     * @param args command line arguments set in Project Properties - the first
     * argument is the data file name and the second is the partial artist name,
     * e.g. be which should return beatles, beach boys, bee gees, etc.
     * @author kalalajoel The main method serves as the entry point of your
     * program. If no arguments are passed, it prints out an error message. It
     * constructs a SongCollection object with the song data from the given
     * file. Then, if a search string is provided, it searches for songs with
     * artists that start with the given prefix using the search method. It
     * prints out the total number of matches found. If there are matches, it
     * prints out the first 10 matching songs. If not, it informs that no
     * matches were found.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("usage: prog songfile [search string]");
            return;
        }

        SongCollection sc = new SongCollection(args[0]);
        //Song[] mysongs= sc.getAllSongs();

        // System.out.println(mysongs[1000].toString());
        SearchByArtistPrefix sbap = new SearchByArtistPrefix(sc);

        if (args.length > 1) {
            System.out.println("searching for: " + args[1]);
            Song[] byArtistResult = sbap.search(args[1]);

            System.out.println("Total matches: " + byArtistResult.length);

            if (byArtistResult.length > 0) {
                System.out.println("First 10 matches:");
                Stream.of(byArtistResult)
                        .limit(10)
                        .forEach(song -> System.out.println("Artist: " + song.getArtist() + ", Title: " + song.getTitle() + ", Lyrics: " + song.getLyrics()));
            } else {
                System.out.println("No matches found.");
            }
        }
    }
}
