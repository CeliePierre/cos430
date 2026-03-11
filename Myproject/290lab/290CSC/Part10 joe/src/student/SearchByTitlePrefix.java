/**
 * File: SearchByTitlePrefix.java
 *****************************************************************************
 *                       Revision History
 *****************************************************************************
 * 10.23.2023 Courtney Jackson created class, made constructor and main
 *****************************************************************************
 *
 */
package student;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;
import static java.lang.Math.log;

/**
 * Constructor
 *
 * @author Courtney Jackson
 */
public class SearchByTitlePrefix {

    private Song[] songs;
    private RaggedArrayList<Song> RALlist;
    private Comparator<Song> cmp;

    public SearchByTitlePrefix(SongCollection sc) {
        songs = sc.getAllSongs();
        cmp = new Song.CmpTitle();
        ((CmpCnt) cmp).resetCmpCnt();
        RALlist = new RaggedArrayList<Song>(cmp);

        int count = 0;

        for (Song song : songs) {
            RALlist.add(song);
            count++;
        }
        RALlist.stats();

    }

    /**
     * Finds all of the songs with the given title prefix and returns it in a
     * Song array
     *
     * @author JamesTedder
     * @param titlePrefix
     * @return temp
     */
    public Song[] search(String titlePrefix) {
        Song song1 = new Song(null, titlePrefix, null);
        char lastChar = titlePrefix.charAt(titlePrefix.length() - 1);
        lastChar++;
        String tempPrefix = titlePrefix.substring(0, titlePrefix.length() - 1)
                + lastChar;
        Song song2 = new Song(null, tempPrefix, null);
        RaggedArrayList<Song> subList = RALlist.subList(song1, song2);
        subList.stats();
        Song[] temp = new Song[subList.size];
        subList.toArray(temp);
        return temp;
    }

    /**
     * testing method for this unit
     *
     * @author Courtney Jackson
     */
    public static void main(String[] args) {
        SongCollection sc = new SongCollection(args[0]);
        SearchByTitlePrefix sbtp = new SearchByTitlePrefix(sc);

        if (args.length > 1) {
            System.out.println("searching for: " + args[1]);
            Song[] byTitleResult = sbtp.search(args[1]);

            System.out.println("Total Number of Matches: " + byTitleResult.length);
            Stream.of(byTitleResult).limit(10).forEach(System.out::println);
        }

    }
}
