/**
 * File: SongCollection.java
 ************************************************************************
 *                     Revision History (newest first)
 ************************************************************************
 * 11/21/2023 - James Tedder and Courtney Jackson finished search method
 * and updated main
 * 11/14/2023 - James Tedder and Courtney Jackson finished statistics
 * 11/12/2023 - Courtney Jackson - Created constructor and class
 * 12/12/2023 - Courtney Jackson and James Tedder fixed search method to use set
 * operations 
 ************************************************************************
 */
package student;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;
import static java.lang.Math.log;

public class SearchByLyricsWords {

    Map<String, TreeSet<Song>> tm = new TreeMap<>();

    /**
     * Constructs the data structure
     *
     * @author Courtney Jackson
     * @param sc a song collection object
     */
    public SearchByLyricsWords(SongCollection sc) {
        String commonWords = "the of and a to in is you that it he for was on\n"
                + " are as with his they at be this from I have or\n"
                + " by one had not but what all were when we there\n"
                + " can an your which their if do will each how them\n"
                + " then she many some so these would into has more\n"
                + " her two him see could no make than been its now\n"
                + " my made did get our me too";
        String[] wordsArray = commonWords.split("\\s+");
        TreeSet<String> commonWordsSet = new TreeSet<>(Arrays.asList(wordsArray));
        Song[] songs = sc.getAllSongs();
        for (Song s : songs) {
            String[] words = s.getLyrics().toLowerCase().split("[^a-zA-Z]+");
            TreeSet<String> allWords = new TreeSet<>();
            for (String word : words) {
                if (word.length() > 1) {
                    allWords.add(word);
                }
            }
            allWords.removeAll(commonWordsSet);
            for (String word : allWords) {
                if (tm.containsKey(word)) {
                    tm.get(word).add(s);
                } else {
                    TreeSet<Song> ts = new TreeSet<>();
                    ts.add(s);
                    tm.put(word.toLowerCase(), ts);
                }
            }

        }
    }

    /**
     * Prints out the statistics for the data structure
     *
     * @author James Tedder and Courtney Jackson
     */
    public void statistics() {
        int count = 0;
        System.out.println("Statistics:");
        System.out.println("The number of keys in the map is: " + tm.size());
        Set<String> x = tm.keySet();
        TreeSet<Song> y;
        for (String word : x) {
            y = tm.get(word);
            count += y.size();
        }
        System.out.println("The total number of Song references is: " + count);
        System.out.println("The space used by the number of keys is: " + (4 * tm.keySet().size()));
        System.out.println("The space used by all the sets is: " + (4 * count));
        double z = ((double) ((4 * tm.keySet().size()) + (4 * count)) / 1000000);
        System.out.println("The total space used by the data structure is: " + z + " megabytes");
        System.out.println("The space usage as a funtion of N is: 4 times N");
    }

    /**
     *
     * @param lyricWords the lyrics that you are looking for
     * @author Courtney Jackson and James Tedder
     * @return Song[] that contains all the songs that contains those lyrics
     */
    public Song[] search(String lyricWords) {
        String commonWords = "the of and a to in is you that it he for was on\n"
                + " are as with his they at be this from I have or\n"
                + " by one had not but what all were when we there\n"
                + " can an your which their if do will each how them\n"
                + " then she many some so these would into has more\n"
                + " her two him see could no make than been its now\n"
                + " my made did get our me too";
        String[] wordsArray = commonWords.split("\\s+");
        TreeSet<String> commonWordsSet = new TreeSet<>(Arrays.asList(wordsArray));
        String[] lyricWordsSplit = lyricWords.toLowerCase().split("[^a-zA-Z]+");
        TreeSet<String> lyricWordsSet = new TreeSet<>(Arrays.asList(lyricWordsSplit));
        lyricWordsSet.removeAll(commonWordsSet);
        TreeSet<Song> values = new TreeSet<>();
        TreeSet<Song> newValues = new TreeSet<>();

        for (String word : lyricWordsSplit) {
            if (word.length() > 1) {
                if (values.size() < 1) {
                    if (tm.containsKey(word)) {
                        values = tm.get(word);
                    }
                }
                if (lyricWordsSplit.length > 1) {
                    newValues.clear();
                    for (Song s : values) {
                        if (tm.get(word).contains(s)) {
                            newValues.add(s);
                        }
                    }
                    values = (TreeSet<Song>) newValues.clone();
                }
            }
        }
        Song[] retArray = new Song[values.size()];
        for (int i = 0; i < retArray.length; i++) {
            retArray[i] = values.pollFirst();
        }

        return retArray;
    }

    /**
     * @author Courtney Jackson
     * @param args
     */
    public static void main(String[] args) {
        SongCollection sc = new SongCollection(args[0]);
        SearchByLyricsWords sblw = new SearchByLyricsWords(sc);
        sblw.statistics();
        if (args.length > 1) {
            System.out.println("searching for: " + args[1]);
            Song[] byLyricWords = sblw.search(args[1]);

            System.out.println("Total Number of Matches: " + byLyricWords.length);
            Stream.of(byLyricWords).limit(10).forEach(System.out::println);
        }
    }
}
