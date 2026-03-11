/**
 * File: SongCollection.java
 ************************************************************************
 *                     Revision History (newest first)
 *
 ************************************************************************
 * 9.2023 - James Tedder - I added the constructor and edited the unit test
 * 9.2023 - Joel Kalala  - I added the unit testing method, getAllSongs methods, and JavaDoc
 * 8.2016 - Anne Applin - formatting and JavaDoc skeletons added
 *
 * 2015 -   Prof. Bob Boothe - Starting code and main for testing
 ************************************************************************
 */
package student;

import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import java.util.Comparator;
import java.util.Scanner;
import static student.PhraseRanking.rankPhrase;

/**
 * SongCollection.java Reads the specified data file and build an array of
 * songs.
 *
 * @author boothe
 *
 */
public class SongCollection {

    private Song[] songs;

    /**
     * Note: in any other language, reading input inside a class is simply not
     * done!! No I/O inside classes because you would normally provide
     * precompiled classes and I/O is OS and Machine dependent and therefore not
     * portable. Java runs on a virtual machine that IS portable. So this is
     * permissable because we are programming in Java and Java runs on a virtual
     * machine not directly on the hardware. Reads the file to a string and then
     * splits it into an array. It then sorts the array into Song objects and
     * adds them to an array of Songs then sorts.
     *
     * @author JamesTedder
     * @param filename The path and filename to the datafile that we are using
     * must be set in the Project Properties as an argument.
     */
    public SongCollection(String fileName) {

        ArrayList<Song> songList = new ArrayList<>();

        try {
            String fileString = Files.readString(Paths.get(fileName));
            String[] fileArray = fileString.split("\"");
            int i = 1;
            while (i < fileArray.length) {
                String artist = fileArray[i];
                i = i + 2;
                String title = fileArray[i];
                i = i + 2;
                String lyrics = fileArray[i];
                Song song = new Song(artist, title, lyrics);
                songList.add(song);
                i = i + 2;
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        songs = new Song[songList.size()];
        songs = songList.toArray(songs);
        Arrays.sort(songs);

        // sort the songs array using Arrays.sort (see the Java API)
        // this will use the compareTo() in Song to do the job.
        // Arrays.sort(songs, new SearchByArtistPrefix.CmpArtist());
    }

    /**
     * this is used as the data source for building other data structures return
     * songs
     *
     * @return the songs array
     */
    public Song[] getAllSongs() {
        return songs;
    }

    public static class CmpTitle implements Comparator<Song> {
        //private int comparisonCount = 0;

        private int totalComparisons;

        @Override
        public int compare(Song song1, Song song2) {
            totalComparisons++;
            return song1.getTitle().compareTo(song2.getTitle());
        }

        public int getTotalComparisons() {
            // your logic to return total comparisons
            return totalComparisons;
        }

        public void resettotalComparisons() {
            totalComparisons = 0;
        }
    }

    /**
     * unit testing method
     *
     * @author JamesTedder
     * @author JoelKalala
     * @param args
     */
    public static void main(String[] args) {
        // Make sure at least the name of the song file is provided
        if (args.length < 2) {
            System.err.println("Usage: java RankedSong <songFile> \"<lyricsPhrase>\"");
            System.exit(1);
        }

        SongCollection sc = new SongCollection(args[0]);
        Song[] allSongs = sc.getAllSongs();
        System.out.println("Total songs in collection: " + allSongs.length);

        SearchByLyricsWords sblw = new SearchByLyricsWords(sc);
        Song[] matches = sblw.search(args[1]);
        System.out.println("Length of matches: " + matches.length);

        ArrayList<RankedSong> rankedSongs = new ArrayList<>();
        for (Song song : matches) {
            int rank = rankPhrase(song.getLyrics(), args[1]);
            if (rank >= 0) {
                rankedSongs.add(new RankedSong(rank, song));
            }
        }

        System.out.println("Number of ranked songs: " + rankedSongs.size());

        // Sort the ranked songs based on their rank
        Collections.sort(rankedSongs, (rs1, rs2) -> Integer.compare(rs1.getRank(), rs2.getRank()));

        // Print each ranked song
        for (RankedSong rankedSong : rankedSongs) {
            System.out.println("Rank: " + rankedSong.getRank() + ", Song: " + rankedSong.getSong().getTitle() + " by " + rankedSong.getSong().getArtist());
        }
    }
}
