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
        artistPrefix = artistPrefix.toLowerCase();
        System.out.println("Searching for " + artistPrefix);
        Song Key = new Song(artistPrefix, "dummy", "dummy");
        Comparator<Song> cmp = new Song.CmpArtist();
        CmpCnt reset = new CmpCnt();
        reset.resetCmpCnt();

        int i2 = artistPrefix.length();
        int i = Arrays.binarySearch(songs, Key, cmp);

        System.out.println("index from binary search is: " + i);

        if (i < 0) {
            i = -i - 1;
        }

        ArrayList<Song> list = new ArrayList<>();

        if (i >= 0) {
            while (i >= 0 && i2 <= songs[i].getArtist().length()
                    && songs[i].getArtist().substring(0, i2).compareToIgnoreCase(artistPrefix) == 0) {
                //((CmpCnt) cmp).cmpCnt++;
                reset.cmpCnt++;
                i--;
            }

            i++;

            System.out.println("Front found at " + i);

            while (i < songs.length && i2 <= songs[i].getArtist().length()
                    && songs[i].getArtist().substring(0, i2).compareToIgnoreCase(artistPrefix) == 0) {
                //((CmpCnt) cmp).cmpCnt++;
                reset.cmpCnt++;
                list.add(songs[i]);
                i++;
            }

            // System.out.println("Comparisons to build the list: " + ((CmpCnt) cmp).getCmpCnt());
            System.out.println("Comparisons to build the list: " + reset.cmpCnt);

            // int actual = ((CmpCnt) cmp).getCmpCnt();
            int actual = reset.cmpCnt;
            System.out.printf("Actual complexity is: %d %n%n", actual);
            System.out.printf("%nk is %d log_2 n = 13%n", list.size());

            Song[] result = list.toArray(new Song[list.size()]);
            System.out.println("Theoretical complexity k+log n is: " + (result.length + 13));

            return result;
        } else {
            System.out.println("nothing found!!");
            return null;
        }

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
