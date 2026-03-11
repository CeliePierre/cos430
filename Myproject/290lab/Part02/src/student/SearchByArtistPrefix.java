/**
 * File: SearchByArtistPrefix.java
 *****************************************************************************
 *                       Revision History
 *****************************************************************************
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
    private Song[] songs;

    /**
     * constructor initializes the property. [Done]
     *
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
     * @param artistPrefix all or part of the artist's name
     * @return an array of songs by artists with substrings that match the
     * prefix
     */
    public Song[] search(String artistPrefix) {
        // write this method

        // Convert prefix to lowercase
        artistPrefix = artistPrefix.toLowerCase();
        // Create a dummy song for binary search
        Song dummySong = new Song(artistPrefix, "", "");

        // Use Arrays.binarySearch to find any matching song
        int index = Arrays.binarySearch(songs, dummySong, (a, b) -> a.getArtist().compareTo(b.getArtist()));

        // If no exact match is found, binarySearch returns (-(insertion point) - 1)
        if (index < 0) {
            index = -index - 1; // Convert to insertion point
        }

        List<Song> matches = new ArrayList<>();

        // Walk back to find the first match
        while (index > 0 && songs[index - 1].getArtist().toLowerCase().startsWith(artistPrefix)) {
            index--;
        }

        // Walk forward adding to the list until it finds the last match
        while (index < songs.length && songs[index].getArtist().toLowerCase().startsWith(artistPrefix)) {
            matches.add(songs[index]);
            index++;
        }
        return matches.toArray(new Song[0]);
    }

    /**
     * testing method for this unit
     *
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

            System.out.println("Total matches: " + byArtistResult.length);
            System.out.println("First 10 matches:");

            Stream.of(byArtistResult).limit(10).forEach(System.out::println);
        }
    }
}
